package Final191;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/** 
 * Purpose: manage student objects, provide methods to add, delete, 
 * sort, save, and load student data
 */
public class manageStudent
{
	// list to store student objects
	private ArrayList<Student> students;
	private int currentMaxId = 0; // track highest ID of student

	// constructor to initialize manageStudent object and its student list
	public manageStudent()
	{
		students = new ArrayList<>();
	}

	// get current max ID
	public int getCurrentMaxId()
	{
		return currentMaxId; 
	}

	public int incrementAndGetCurrentMaxId()
	{
		return ++currentMaxId; // increment and return new ID
	}

	public void addStudent(Student student)
	{
		// add student to the list and update current max ID
		currentMaxId = Math.max(currentMaxId, student.getId());
		students.add(student);
	}

	// delete student from the list based on index
	public void deleteStudent(int index)
	{
		if (index >= 0 && index < students.size())
		{
			students.remove(index);
		}
	}

	public void sortStudentsByGrade()
	{
		Grade.sortStudentByGrade(students);
	}

	// save list of students to a CSV file
	public void saveToFile(String filename)
	{
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter(filename)))
		{
			writer.write(
					"ID,Sex,Age,Parent Status,Mother's Job,Father's Job,Weekly Study Time,Failures,"
							+ "Internet Access,Free Time,Go Out,Absences,Grade 1,Grade 2,Grade 3\n");
			for (Student student : students)
			{
				writer.write(student.getId() + "," + student.getSex() + "," + student.getAge() + "," + student.getParentStatus()
						+ "," + student.getMotherJob() + "," + student.getFatherJob() + ","
						+ student.getWeeklyStudyTime() + "," + student.getFailures() + ","
						+ student.isInternetAccess() + "," + student.getFreeTime() + "," + student.getGoOut() + ","
						+ student.getAbsences() + "," + student.getGrade1() + "," + student.getGrade2() + "," + student.getGrade3() + "\n");
			}
		}
		catch (IOException e)
		{
			System.err.println("Error saving to file: " + e.getMessage());
		}
	}

	// load student data from a CSV file
	public void loadFromCSV(String filename)
	{
		try (BufferedReader reader = new BufferedReader(
				new FileReader(filename)))
		{
			String line;
			boolean isFirstLine = true; // skip header row

			int nextId = students.stream().mapToInt(Student::getId).max()
					.orElse(0) + 1;

			while ((line = reader.readLine()) != null)
			{
				if (isFirstLine)
				{
					isFirstLine = false;
					continue;
				}
				String[] parts = line.split(",");
				if (parts.length >= 15)
				{
					try
					{
						// parse fields from CSV line
						String sex = parts[1].trim();
						int age = Integer.parseInt(parts[2].trim());
						String parentStatus = parts[3].trim();
						String motherJob = parts[4].trim();
						String fatherJob = parts[5].trim();
						int weeklyStudyTime = Integer.parseInt(parts[6].trim());
						int failures = Integer.parseInt(parts[7].trim());
						boolean internetAccess = parts[8].trim()
								.equalsIgnoreCase("yes");
						int freeTime = Integer.parseInt(parts[9].trim());
						int goOut = Integer.parseInt(parts[10].trim());
						int absences = Integer.parseInt(parts[11].trim());
						int grade1 = Integer.parseInt(parts[12].trim());
						int grade2 = Integer.parseInt(parts[13].trim());
						int grade3 = Integer.parseInt(parts[14].trim());

						// create new Student then add to the list
						Student student = new Student(nextId, sex, age,
								parentStatus, motherJob, fatherJob,
								weeklyStudyTime, failures, true, freeTime,
								goOut, absences, grade1, grade2, grade3);

						addStudent(student);
						nextId++;
					}
					catch (NumberFormatException e)
					{
						System.err.println(
								"Error parsing numeric values in line: "
										+ line);
					}
				}
				else
				{
					System.err.println("Invalid line format: " + line);
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.err.println("File not found: " + filename);
		}
		catch (IOException e)
		{
			System.err.println("Error reading file: " + e.getMessage());
		}
	}

	// retrieve list of students
	public ArrayList<Student> getStudents()
	{
		return students;
	}
}
