public class Teacher {
   public String getTLastName() {
      return tLastName;
   }

   public void setTLastName(String tLastName) {
      this.tLastName = tLastName;
   }

   public String getTFirstName() {
      return tFirstName;
   }

   public void setTFirstName(String tFirstName) {
      this.tFirstName = tFirstName;
   }

   public int getClassroom() {
      return classroom;
   }

   public void setClassroom(int classroom) {
      this.classroom = classroom;
   }

   public void print() {
      System.out.println("Last Name is - " + tLastName);
      System.out.println("First Name is - " + tFirstName);
      System.out.println("Classroom is - " + classroom);
      System.out.println("----------------------------------");
   }

   public Teacher(String tLastName, String tFirstName, int classroom) {
      super();
      this.tLastName = tLastName;
      this.tFirstName = tFirstName;
      this.classroom = classroom;
   }

   String tLastName;
   String tFirstName;
   int classroom;
}
