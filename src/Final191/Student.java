package Final191;

/**
 * Purpose: represent a student with many personal data, provide methods to access and modify student details, 
 * calculate average grade, and retrieve student's name
 */
public class Student extends Grade implements mainInformation
{
	// private fields to store student attributes
	private int id;
	private String sex;
	private int age;
	private String parentStatus;
	private String motherJob;
	private String fatherJob;
	private int weeklyStudyTime;
	private int failures;
	private boolean internetAccess;
	private int freeTime;
	private int goOut;
	private int absences;

	// initialize student object with default values
	public Student()
	{
		super();
	}

	// constructor to initialize student object
	public Student(int id, String sex, int age, String parentStatus,
			String motherJob, String fatherJob, int weeklyStudyTime,
			int failures, boolean internetAccess, int freeTime, int goOut,
			int absences, int grade1, int grade2, int grade3)
	{
		super(grade1, grade2, grade3);
		this.id = id;
		this.sex = sex;
		this.age = age;
		this.parentStatus = parentStatus;
		this.motherJob = motherJob;
		this.fatherJob = fatherJob;
		this.weeklyStudyTime = weeklyStudyTime;
		this.failures = failures;
		this.internetAccess = internetAccess;
		this.freeTime = freeTime;
		this.goOut = goOut;
		this.absences = absences;
	}

	// getters and setters for all attributes
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getParentStatus()
	{
		return parentStatus;
	}

	public void setParentStatus(String parentStatus)
	{
		this.parentStatus = parentStatus;
	}

	public String getMotherJob()
	{
		return motherJob;
	}

	public void setMotherJob(String motherJob)
	{
		this.motherJob = motherJob;
	}

	public String getFatherJob()
	{
		return fatherJob;
	}

	public void setFatherJob(String fatherJob)
	{
		this.fatherJob = fatherJob;
	}

	public int getWeeklyStudyTime()
	{
		return weeklyStudyTime;
	}

	public void setWeeklyStudyTime(int weeklyStudyTime)
	{
		this.weeklyStudyTime = weeklyStudyTime;
	}

	public int getFailures()
	{
		return failures;
	}

	public void setFailures(int failures)
	{
		this.failures = failures;
	}

	public boolean isInternetAccess()
	{
		return internetAccess;
	}

	public void setInternetAccess(boolean internetAccess)
	{
		this.internetAccess = internetAccess;
	}

	public int getFreeTime()
	{
		return freeTime;
	}

	public void setFreeTime(int freeTime)
	{
		this.freeTime = freeTime;
	}

	public int getGoOut()
	{
		return goOut;
	}

	public void setGoOut(int goOut)
	{
		this.goOut = goOut;
	}

	public int getAbsences()
	{
		return absences;
	}

	public void setAbsences(int absences)
	{
		this.absences = absences;
	}

	// implement getName method from interface
	@Override
	public String getName()
	{
		return "Student_" + id;
	}

	// implement calculate average grade method from grade class
	@Override
	public int calculateAveGrade()
	{
		return (getGrade1() + getGrade2() + getGrade3()) / 3;
	}

}
