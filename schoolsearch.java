import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class schoolsearch {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		Scanner fs = new Scanner(new File("students.txt"));
	

		ArrayList<Student> students = new ArrayList();

		String fullCommand, key, stLastName, stFirstName, tLastName, tFirstName;
		int grade, classroom, bus;
		double gpa;
		char action;

		while (fs.hasNextLine()) {

			Scanner fs2 = new Scanner(fs.nextLine()).useDelimiter(",");
			stLastName = fs2.next();
			stFirstName = fs2.next();
			grade = fs2.nextInt();
			classroom = fs2.nextInt();
			bus = fs2.nextInt();
			gpa = fs2.nextDouble();
			tLastName = fs2.next();
			tFirstName = fs2.next();

			Student tempStudent = new Student(stLastName, stFirstName, grade, classroom, bus, gpa, tLastName,
					tFirstName);

			students.add(tempStudent);
		}
		while (true) {
			try {
				System.out.println("Enter next search");
				fullCommand = sc.nextLine();
				action = fullCommand.charAt(0);
				if (fullCommand.length() > 0) {
					switch (action) {
					case 'S':
						if (fullCommand.charAt(fullCommand.length() - 1) == 'B') {
							key = fullCommand.substring(3, fullCommand.length() - 2);
							SearchStudentBus(students, key);
						} else {
							key = fullCommand.substring(3);
							SearchStudent(students, key);
						}
						break;

					case 'T':
						key = fullCommand.substring(3);
						SearchTeacher(students, key);
						break;

					case 'B':
						key = fullCommand.substring(3);
						SearchBus(students, Integer.parseInt(key));
						break;

					case 'G':
						if (fullCommand.charAt(fullCommand.length() - 1) == 'H') {
							key = fullCommand.substring(3, 4);
							SearchGradeHigh(students, Integer.parseInt(key));
						} else if (fullCommand.charAt(fullCommand.length() - 1) == 'L') {
							key = fullCommand.substring(3, 4);
							SearchGradeLow(students, Integer.parseInt(key));
						} else {
							key = fullCommand.substring(3);
							SearchGrade(students, Integer.parseInt(key));
						}
						break;

					case 'A':
						key = fullCommand.substring(3);
						GetAverage(students, Integer.parseInt(key));
						break;

					case 'I':
						Info(students);
						break;
					case 'Q':
						System.exit(0);
						break;
					default:
						System.out.println("Invalid Command...");
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Invalid Command");
			}
		}
	}

	public static void SearchStudent(ArrayList<Student> students, String lastName) {
		for (Student student : students) {
			if (student.getStLastName().equals(lastName)) {
				System.out.println(student.getStLastName() + ", " + student.getStFirstName() + ", " + student.getGrade()
						+ ", " + student.getClassroom() + ", " + student.gettLastName() + ", "
						+ student.gettFirstName());
			}
		}
		System.out.println("---------------------------");
	}

	public static void SearchStudentBus(ArrayList<Student> students, String key) {
		for (Student student : students) {
			if (student.getStLastName().equals(key)) {
				System.out.println(student.getStLastName() + ", " + student.getStFirstName() + ", " + student.getBus());
			}
		}
		System.out.println("---------------------------");
	}

	public static void SearchTeacher(ArrayList<Student> students, String key) {
		for (Student student : students) {
			if (student.gettLastName().equals(key)) {
				System.out.println(student.getStLastName() + ", " + student.getStFirstName());
			}
		}
		System.out.println("---------------------------");
	}

	private static void SearchGrade(ArrayList<Student> students, int key) {
		for (Student student : students) {
			if (student.getGrade() == key) {
				System.out.println(student.getStLastName() + ", " + student.getStFirstName());
			}
		}
		System.out.println("---------------------------");
	}

	private static void SearchBus(ArrayList<Student> students, int key) {
		for (Student student : students) {
			if (student.getBus() == key) {
				System.out.println(student.getStLastName() + ", " + student.getStFirstName());
			}
		}
		System.out.println("---------------------------");
	}

	private static void SearchGradeLow(ArrayList<Student> students, int key) {
		Student lStudent = null;
		for (Student student : students) {
			if (student.getGrade() == key) {
				if (lStudent == null) {
					lStudent = student;
				} else {
					if (student.getGpa() < lStudent.getGpa()) {
						lStudent = student;
					}
				}
			}
		}
		System.out.println(lStudent.getStLastName() + ", " + lStudent.getStFirstName() + ", " + lStudent.getGpa() + ", "
				+ lStudent.gettLastName() + ", " + lStudent.gettFirstName() + ", " + lStudent.getBus());
		System.out.println("---------------------------");
	}

	private static void SearchGradeHigh(ArrayList<Student> students, int key) {

		Student hStudent = null;
		for (Student student : students) {
			if (student.getGrade() == key) {
				if (hStudent == null) {
					hStudent = student;
				} else {
					if (student.getGpa() > hStudent.getGpa()) {
						hStudent = student;
					}
				}
			}
		}
		System.out.println(hStudent.getStLastName() + ", " + hStudent.getStFirstName() + ", " + hStudent.getGpa() + ", "
				+ hStudent.gettLastName() + ", " + hStudent.gettFirstName() + ", " + hStudent.getBus());
		System.out.println("---------------------------");
	}

	private static void GetAverage(ArrayList<Student> students, int key) {
		double total = 0;
		int numStudents = 0;
		for (Student student : students) {
			if (student.getGrade() == key) {
				total = total + student.getGpa();
				numStudents++;
			}
		}
		System.out.println("The average GPA for grade " + key + " is " + (total / numStudents));
		System.out.println("---------------------------");
	}

	private static void Info(ArrayList<Student> students) {
		int kindergarten = 0, first = 0, second = 0, third = 0, fourth = 0, fifth = 0, sixth = 0;
		for (Student student : students) {
			switch (student.getGrade()) {
			case 0:
				kindergarten++;
				break;
			case 1:
				first++;
				break;
			case 2:
				second++;
				break;
			case 3:
				third++;
				break;
			case 4:
				fourth++;
				break;
			case 5:
				fifth++;
				break;
			case 6:
				sixth++;
				break;
			default:
				break;
			}
		}
		System.out.println("0: " + kindergarten);
		System.out.println("1: " + first);
		System.out.println("2: " + second);
		System.out.println("3: " + third);
		System.out.println("4: " + fourth);
		System.out.println("5: " + fifth);
		System.out.println("6: " + sixth);
		System.out.println("---------------------------");
	}
}
