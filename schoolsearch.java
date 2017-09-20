import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class schoolsearch {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		Scanner fsl = new Scanner(new File("list.txt"));
		Scanner fst = new Scanner(new File("teachers.txt"));

	

		ArrayList<Teacher> teachers = new ArrayList();
		ArrayList<Student> students = new ArrayList();

		String fullCommand, key, stLastName, stFirstName, tLastName, tFirstName;
		int grade, classroom, bus;
		double gpa;
		char action;

		while (fsl.hasNextLine()) {

			Scanner fsl2 = new Scanner(fsl.nextLine()).useDelimiter(",");
			stLastName = fsl2.next();
			stFirstName = fsl2.next();
			grade = fsl2.nextInt();
			classroom = fsl2.nextInt();
			bus = fsl2.nextInt();
			gpa = fsl2.nextDouble();

			Student tempStudent = new Student(stLastName, (stFirstName.substring(1)), grade, classroom, bus, gpa);

			tempStudent.print();
			students.add(tempStudent);
		}

		while (fst.hasNextLine()) {

			Scanner fst2 = new Scanner(fst.nextLine()).useDelimiter(", ");
			tLastName = fst2.next();
			tFirstName = fst2.next();
			classroom = fst2.nextInt();

			Teacher tempTeacher = new Teacher(tLastName, tFirstName, classroom);

			//tempTeacher.print();
			teachers.add(tempTeacher);
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
							SearchStudentBus(students, teachers, key);
						} else {
							key = fullCommand.substring(3);
							SearchStudent(students, teachers, key);
						}
						break;

					case 'T':
						key = fullCommand.substring(3);
						SearchTeacher(students, teachers, key);
						break;

					case 'B':
						key = fullCommand.substring(3);
						SearchBus(students, Integer.parseInt(key));
						break;

					case 'G':
						if (fullCommand.charAt(fullCommand.length() - 1) == 'H') {
							key = fullCommand.substring(3, 4);
							SearchGradeHigh(students, teachers,  Integer.parseInt(key));
						} else if (fullCommand.charAt(fullCommand.length() - 1) == 'L') {
							key = fullCommand.substring(3, 4);
							SearchGradeLow(students, teachers, Integer.parseInt(key));
						} else {
							key = fullCommand.substring(3);
							SearchGrade(students, Integer.parseInt(key));
						}
						break;

					/*case 'A':
						key = fullCommand.substring(3);
						GetAverage(students, Integer.parseInt(key));
						break;

					case 'I':
						Info(students);
						break; */
					case 'Q':
						System.exit(0);
						break;
					default:
						System.out.println("Invalid Command...");
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Invalid Command...");
			}
		}
	}

	public static void SearchStudent(ArrayList<Student> students, ArrayList<Teacher> teachers, String lastName) {
		for (Student student : students) {
			if (student.getStLastName().equals(lastName)) {
				for (Teacher teacher : teachers) {
					if (teacher.getClassroom() == student.getClassroom()) {
						System.out.println(student.getStLastName() + ", " + student.getStFirstName() + ", " + student.getGrade()
						+ ", " + student.getClassroom() + ", " + teacher.getTLastName() + ", "
						+ teacher.getTFirstName());
					}
				}
			}
		}
		System.out.println("---------------------------");
	}

	public static void SearchStudentBus(ArrayList<Student> students, ArrayList<Teacher> teachers, String key) {
		for (Student student : students) {
			if (student.getStLastName().equals(key)) {
				System.out.println(student.getStLastName() + ", " + student.getStFirstName() + ", " + student.getBus());
			}
		}
		System.out.println("---------------------------");
	}

	public static void SearchTeacher(ArrayList<Student> students, ArrayList<Teacher> teachers, String key) {
		int classroom = 0;
		for (Teacher teacher : teachers) {
			if (teacher.getTLastName().equals(key)) {
				classroom = teacher.getClassroom();
			}
		}
		for (Student student : students) {
			if (student.getClassroom() == classroom) {
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

	private static void SearchGradeLow(ArrayList<Student> students, ArrayList<Teacher> teachers, int key) {
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
		for (Teacher teacher : teachers) {
			if (teacher.getClassroom() == lStudent.getClassroom()) {
				System.out.println(lStudent.getStLastName() + ", " + lStudent.getStFirstName() + ", " + lStudent.getGpa() + ", "
				+ teacher.getTLastName() + ", " + teacher.getTFirstName() + ", " + lStudent.getBus());
			}
		}
		System.out.println("---------------------------");
	}

	private static void SearchGradeHigh(ArrayList<Student> students, ArrayList<Teacher> teachers, int key) {

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
		for (Teacher teacher : teachers) {
			if (teacher.getClassroom() == hStudent.getClassroom()) {
				System.out.println(hStudent.getStLastName() + ", " + hStudent.getStFirstName() + ", " + hStudent.getGpa() + ", "
				+ teacher.getTLastName() + ", " + teacher.getTFirstName() + ", " + hStudent.getBus());
			}
		}
		System.out.println("---------------------------");
	}

	/*private static void GetAverage(ArrayList<Student> students, int key) {
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
	}*/
}
