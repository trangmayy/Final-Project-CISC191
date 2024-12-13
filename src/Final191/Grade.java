package Final191;

import java.util.ArrayList;

/**
 * Purpose: Abstract class Grade represents grades of students, provides 
 * functionality for setting, retrieving, and calculating average grade
 */

public abstract class Grade
{
	// private fields storing grades
	private int grade1;
	private int grade2;
	private int grade3;
	
	// default constructor
	public Grade()
	{
	}

	// initialize grade object with specific grades
	public Grade(int grade1, int grade2, int grade3)
	{
		this.grade1 = grade1;
		this.grade2 = grade2;
		this.grade3 = grade3;
	}

	// getter and setter for each grade
	public int getGrade1()
	{
		return grade1;
	}

	public void setGrade1(int grade1)
	{
		this.grade1 = grade1;
	}

	public int getGrade2()
	{
		return grade2;
	}

	public void setGrade2(int grade2)
	{
		this.grade2 = grade2;
	}

	public int getGrade3()
	{
		return grade3;
	}

	public void setGrade3(int grade3)
	{
		this.grade3 = grade3;
	}
	
	// calculate average grade
	public abstract int calculateAveGrade();

	// sort students in descending based on their average grade
    public static void sortStudentByGrade(ArrayList<Student> students) {
        students.sort((s1, s2) -> s2.calculateAveGrade() - s1.calculateAveGrade());
    }
}
