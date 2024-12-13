package Final191;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class studentGUIView extends JFrame
{
	private JTextField sex_Field;
	private JTextField age_Field;
	private JTextField parentStatus_Field;
	private JTextField motherJob_Field;
	private JTextField fatherJob_Field;
	private JTextField weeklyStudyTime_Field;
	private JTextField failures_Field;
	private JTextField freeTime_Field;
	private JTextField goOut_Field;
	private JTextField absences_Field;
	private JTextField grade1_Field, grade2_Field, grade3_Field;
	// table and model to display student data
	private JTable studentTable;
	private DefaultTableModel model;
	private manageStudent manage; // to handle student operations

	public studentGUIView()
	{
		manage = new manageStudent();
		// set up frame properties
		setTitle("Student Management System");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// create panel for adding student details
		JPanel addStudentPanel = new JPanel(new GridLayout(0, 2, 7, 7));
		addStudentPanel.setBorder(BorderFactory.createTitledBorder("Add Student Information"));

		// add input fields
		addStudentPanel.add(new JLabel("Sex:"));
		sex_Field = new JTextField();
		addStudentPanel.add(sex_Field);

		addStudentPanel.add(new JLabel("Age:"));
		age_Field = new JTextField();
		addStudentPanel.add(age_Field);

		addStudentPanel.add(new JLabel("Parent Status:"));
		parentStatus_Field = new JTextField();
		addStudentPanel.add(parentStatus_Field);

		addStudentPanel.add(new JLabel("Mother's Job:"));
		motherJob_Field = new JTextField();
		addStudentPanel.add(motherJob_Field);

		addStudentPanel.add(new JLabel("Father's Job:"));
		fatherJob_Field = new JTextField();
		addStudentPanel.add(fatherJob_Field);

		addStudentPanel.add(new JLabel("Weekly Study Time:"));
		weeklyStudyTime_Field = new JTextField();
		addStudentPanel.add(weeklyStudyTime_Field);

		addStudentPanel.add(new JLabel("Failures:"));
		failures_Field = new JTextField();
		addStudentPanel.add(failures_Field);

		addStudentPanel.add(new JLabel("Free Time:"));
		freeTime_Field = new JTextField();
		addStudentPanel.add(freeTime_Field);

		addStudentPanel.add(new JLabel("Go Out:"));
		goOut_Field = new JTextField();
		addStudentPanel.add(goOut_Field);

		addStudentPanel.add(new JLabel("Absences:"));
		absences_Field = new JTextField();
		addStudentPanel.add(absences_Field);

		addStudentPanel.add(new JLabel("Grade 1:"));
		grade1_Field = new JTextField();
		addStudentPanel.add(grade1_Field);

		addStudentPanel.add(new JLabel("Grade 2:"));
		grade2_Field = new JTextField();
		addStudentPanel.add(grade2_Field);

		addStudentPanel.add(new JLabel("Grade 3:"));
		grade3_Field = new JTextField();
		addStudentPanel.add(grade3_Field);

		// button to add new student
		JButton addButton = new JButton("Add Student");
		addButton.addActionListener(new AddStudentListener());
		addStudentPanel.add(addButton);
		add(addStudentPanel, BorderLayout.NORTH);

		// create table to display student info
		model = new DefaultTableModel(new String[] { "ID", "Sex", "Age", "Parent Status", "Mother's Job", "Father's Job",
				"Weekly Study Time", "Failures", "Free Time", "Go Out", "Absences", "Grade 1", "Grade 2", "Grade 3", "Average" }, 0);
		studentTable = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(studentTable);
		add(scrollPane, BorderLayout.CENTER);

		// create control panel with buttons for other actions
		JPanel controlPanel = new JPanel();
		JButton sortButton = new JButton("Sort by Grade");
		sortButton.addActionListener(e -> sortStudents());
		JButton saveButton = new JButton("Save to File");
		saveButton.addActionListener(e -> saveStudents());
		JButton loadButton = new JButton("Load from CSV");
		loadButton.addActionListener(e -> loadStudents());
		JButton deleteButton = new JButton("Delete Student");
		deleteButton.addActionListener(e -> deleteStudent());
		JButton exitButton = new JButton("Exit Program");
		exitButton.addActionListener(e -> exitProgram());

		controlPanel.add(sortButton);
		controlPanel.add(saveButton);
		controlPanel.add(loadButton);
		controlPanel.add(deleteButton);
		controlPanel.add(exitButton);
		add(controlPanel, BorderLayout.SOUTH);
	}

	// add new student and update table
	private void addStudent()
	{
		try
		{
			int id = manage.incrementAndGetCurrentMaxId();
			String sex = sex_Field.getText();
			int age = Integer.parseInt(age_Field.getText());
			String parentStatus = parentStatus_Field.getText();

			// check parent status input
			if (!parentStatus.equalsIgnoreCase("A") && !parentStatus.equalsIgnoreCase("T"))
			{
				JOptionPane.showMessageDialog(this, "Parent Status must be 'A' or 'T'.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// get other info
			String motherJob = motherJob_Field.getText();
			String fatherJob = fatherJob_Field.getText();
			int weeklyStudyTime = Integer.parseInt(weeklyStudyTime_Field.getText());
			int failures = Integer.parseInt(failures_Field.getText());
			int freeTime = Integer.parseInt(freeTime_Field.getText());
			int goOut = Integer.parseInt(goOut_Field.getText());
			int absences = Integer.parseInt(absences_Field.getText());
			int grade1 = Integer.parseInt(grade1_Field.getText());
			int grade2 = Integer.parseInt(grade2_Field.getText());
			int grade3 = Integer.parseInt(grade3_Field.getText());

			// create new student
			Student student = new Student(id, sex, age, parentStatus, motherJob, fatherJob, weeklyStudyTime, failures, true, freeTime, goOut,
					absences, grade1, grade2, grade3);
			manage.addStudent(student);

			// update table after creating new student
			model.addRow(new Object[] { id, sex, age, parentStatus, motherJob, fatherJob, weeklyStudyTime, failures, freeTime, goOut,
					absences, grade1, grade2, grade3, student.calculateAveGrade() });

			// clear input fields
			sex_Field.setText("");
			age_Field.setText("");
			parentStatus_Field.setText("");
			motherJob_Field.setText("");
			fatherJob_Field.setText("");
			weeklyStudyTime_Field.setText("");
			failures_Field.setText("");
			freeTime_Field.setText("");
			goOut_Field.setText("");
			absences_Field.setText("");
			grade1_Field.setText("");
			grade2_Field.setText("");
			grade3_Field.setText("");
		}
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(this, "Enter valid numeric values where required.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
		}
	}

	// sort students by average grade then update table
	private void sortStudents()
	{
		manage.sortStudentsByGrade();
		model.setRowCount(0);
		for (Student student : manage.getStudents())
		{
			model.addRow(new Object[] { student.getId(), student.getSex(),
					student.getAge(), student.getParentStatus(),
					student.getMotherJob(), student.getFatherJob(),
					student.getWeeklyStudyTime(), student.getFailures(),
					student.getFreeTime(), student.getGoOut(),
					student.getAbsences(), student.getGrade1(),
					student.getGrade2(), student.getGrade3(),
					student.calculateAveGrade() });
		}
	}

	private void saveStudents()
	{
		manage.saveToFile("students.txt");
	}

	// load student data from a CSV file
	private void loadStudents()
	{
		manage.loadFromCSV("/Users/trangmayy/Desktop/final project/StudentDataa.csv");
		model.setRowCount(0); // clear exist rows in the table to avoid duplicate
		// insert to table with the loaded student data
		for (Student student : manage.getStudents())
		{
			model.addRow(new Object[] { student.getId(), student.getSex(),
					student.getAge(), student.getParentStatus(),
					student.getMotherJob(), student.getFatherJob(),
					student.getWeeklyStudyTime(), student.getFailures(),
					student.getFreeTime(), student.getGoOut(),
					student.getAbsences(), student.getGrade1(),
					student.getGrade2(), student.getGrade3(),
					student.calculateAveGrade() });
		}
	}

	// delete selected student from table and student list
	private void deleteStudent()
	{
		int chooseRow = studentTable.getSelectedRow(); // get selected row index
		if (chooseRow >= 0)
		{
			// remove student from manage and table
			manage.getStudents().remove(chooseRow);
			model.removeRow(chooseRow);
			JOptionPane.showMessageDialog(this, "Student is deleted"); // if student is deleted -> show confirmation
		}
		else
		{
			// if no row is selected -> show message
			JOptionPane.showMessageDialog(this, "Please select a student to delete.");
		}
	}

	// close program if user want to exit
	private void exitProgram()
	{
		int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit the program?",
				"Exit Confirmation", JOptionPane.YES_NO_OPTION);
		if (confirmation == JOptionPane.YES_OPTION)
		{
			System.exit(0);
		}
	}

	// inner class to handle "Add Student" button 
	private class AddStudentListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			addStudent();
		}
	}

	// method to run student management system GUI
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() -> {
			studentGUIView gui = new studentGUIView(); // create GUI instance
			gui.setVisible(true); // set GUI visible
		});
	}
}
