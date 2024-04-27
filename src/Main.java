import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
//AJ Test
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main { //main class where all the classes and methods will be called
    static studentLinkedList school= new studentLinkedList();
    public static void main(String[] args) {
        //Linked List holds all the students
        //not sure where to initialize this based on menu objects
        try {
            Menus menu = new MainMenu();
            File textFile = new File("lec.txt");
            Scanner mainScan = new Scanner(textFile);
            String data = mainScan.nextLine();
            System.out.println(data);
        }
        catch(FileNotFoundException e){
            System.out.println("exception!");
        }
    }
}
class MyException extends Throwable {

}
abstract class Menus<T> {//abstract class for all menus to be based off
    abstract public T menuPromptAndSelect(); // method that prints out the menu prompt and store the user's input selection

    abstract public void menuSelectCheck(); //takes in the user's input preformed by the first class and preforms and action based on the user's input
}

class MainMenu extends Menus<Integer> {
    StudentManagement studentManagementMenu = new StudentManagement(); //studentMenu object for the Mainmenu class to access the student menu overriden method
    CourseManagement courseManagementMenu = new CourseManagement(); //studentMenu object for the Mainmenu class to access the student menu overriden method
    boolean whileToggle = true; // another boolean variable to maintain a while loop until a certain breakpoint ??why this is never used??



    //ADDED BREAK STATEMENTS 
    @Override
    public void menuSelectCheck() { //takes in the user input stored in the menuPrompt class
        int userSelection  = -1;
        userSelection = menuPromptAndSelect(); //internal variable is set to the user input(which is stored in the prompt class
        switch (userSelection){
            case 0->{ System.out.println("Goodbye!"); //case where user selects the exit option and the program is terminated
                System.exit(0);break;}
            case 1->{studentManagementMenu.menuSelectCheck();
                break;
            }//case where student selects the student management option, uses the student object
            case 2->{courseManagementMenu.menuSelectCheck();break;}//case where student selects the course management option, uses the course object
            default -> {System.out.println("fatal error! program will shutdown");System.exit(1);}// this default should never be reached as the main code block catches all mismatched user inputs and exceptions, therefore if it is somehow reached it will be treated as an error and the program will be closed
        }

    }
    @Override
    public Integer menuPromptAndSelect() {
        boolean toggle = true;
        int mainSelection = -1;
        Scanner mainScan = new Scanner(System.in);

      while (toggle) {
          try {
              System.out.println("-------------------------------------------\nMain Menu\n\n1 : Student Management\n2 : Course Management\n0 : Exit");
              mainSelection = mainScan.nextInt();
              // mainScan.close();//stop closing these scanner it breaks it
              if (2<mainSelection || mainSelection<0) {
                  throw new MyException();
              }
          } catch (MyException | Exception e) {
              System.out.println("invalid input!");
          } finally {
              if (mainSelection >= 0 && mainSelection <= 2) {
                  toggle = false;
              }
          }
      }
        return mainSelection;
    }

}
class StudentManagement extends Menus<Character> {
    //student management initial prompt code

    //why are we looping through this??
    @Override
    public Character menuPromptAndSelect() {
        boolean toggle = true;
        char studentSelection = ' ';

        //create the scanner outside the while loop FIX
        Scanner studentScan = new Scanner(System.in);

        try {
            System.out.println("Student management Menu:\nChoose one of:\nA - add a student\nB - delete a student\nC - print Fee Invoice\nD - Print List of Students\nX - Back to Main menu"); // will change
            studentSelection = studentScan.next().charAt(0);
            studentSelection = Character.toLowerCase(studentSelection);
            //studentScan.close(); //stop closing the scanner

            switch (studentSelection) {
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'x':
                    break; // valid input
                default:
                    throw new MyException(); // invalid input
            }
        }
        catch (MyException | Exception e) {
            System.out.println("invalid input!");
        }
        finally {
            //this isn't necessary
            //if(studentSelection == 'a'  studentSelection != 'b' && studentSelection != 'c' && studentSelection != 'd' && studentSelection != 'e') ) { // will change
            //    toggle = false;
            // }
        }
        return studentSelection;

    }

    @Override
    public void menuSelectCheck() {


        char sMenuSelection;
        sMenuSelection = menuPromptAndSelect();
        Scanner addStudent= new Scanner(System.in);


        switch (sMenuSelection){
            case 'a'->{

                String name,id, type;
                double gpa;
                int classes;




                //getting the students Info try statement if the ID is not in the format specified
                //try{
                System.out.println("What Is The Student's ID:");
                id=addStudent.nextLine();

                //}


                System.out.println("What Is The Student's Name:");
                name=addStudent.nextLine();
                System.out.println("What Is The Students GPA");
                gpa=addStudent.nextDouble();
                addStudent.nextLine();//discard '\n'

                System.out.println("What Type of Student (PhD, MS or Undergrad):");
                type=addStudent.nextLine();

                if(type.equals("Undergrad")){
                    System.out.println("ADDING AN : Undergrad Student");
                    System.out.println("How Many CRNS Are They Taking");
                    classes=addStudent.nextInt();
                    addStudent.nextLine();//discard '\n'
                    int undergradCrnsTaken[]=new int[classes];
                    for (int i=0; i<classes;i++){
                        System.out.println("What Class Number "+(i+1)+" Code");
                        undergradCrnsTaken[i]=addStudent.nextInt();
                        addStudent.nextLine();//discard '\n'
                    }
                    System.out.println("Are They A Resident");
                    //not sure what this is?


                    //creating student
                    Student newStudent= new UndergraduateStudent(name,id,undergradCrnsTaken,gpa,false);
                    Main.school.addNewStudent(newStudent);
                }
                //different inputs for different types of students 
                if (type.equals("PhD")){
                    System.out.println("ADDING AN : PHD Student");
                    String subject, advisorName;
                    int labAmount, labNumbers;
                    System.out.println("What Is The name of advisor");
                    advisorName=addStudent.nextLine();
                    System.out.println("What Is Their Reseach Subject");
                    subject=addStudent.nextLine();

                    System.out.println("What is the amount of labs");
                    labAmount=addStudent.nextInt();
                    addStudent.nextLine();//discard '\n'
                    int labsSupervised[] = new int [labAmount];
                    for (int i = 0; i<labAmount;i++){
                        System.out.println("The" +(i+1)+"Lab Number is :");
                        labsSupervised[i]=addStudent.nextInt();
                        addStudent.nextLine();//discard '\n'
                    }

                    Student newPhdStudent= new PHDStudent(name, id, labsSupervised,advisorName,subject);
                    Main.school.addNewStudent(newPhdStudent);
                }

                if(type.equals("MS")){
                    System.out.println("ADDING AN : Master Student");

                    System.out.println("How Many CRNS Are They Taking");

                    classes=addStudent.nextInt();
                    addStudent.nextLine();//discard '\n'
                    int gradCrnsTaken[]=new int[classes];
                    for (int i=0; i<classes;i++){
                        System.out.println("What Class Number "+(i+1)+" Code");
                        gradCrnsTaken[i]=addStudent.nextInt();
                        addStudent.nextLine();//discard '\n'
                    }


                    Student newMasterStudent= new MSStudent(name, id, gradCrnsTaken);
                    Main.school.addNewStudent(newMasterStudent);
                }

                System.out.println(name + " ADDED");

            }//Add studeent
            case 'b'->{
                String id;
                System.out.println("What Is The Student's ID:");
                id=addStudent.nextLine();

                Main.school.searchStudent(id);
            }//Search Student
            case 'c'->{
                //get input w/ scanner
                String id;
                System.out.println("What Is The Student's ID:");
                id=addStudent.nextLine();

                Main.school.deleteStudent(id);
            }//Delete a student
            case 'd'->{

                String id;
                System.out.println("What Is The Student's ID:");
                id=addStudent.nextLine();

                Main.school.printFeeInvoice(id);
            }//Print Fee invoice
            case 'e'->{Main.school.printListStudents();}//Print all students

            default -> {}
        }
    }
}

class CourseManagement extends Menus<Integer> {
    @Override
    public Integer menuPromptAndSelect() {
        return 0;
    }

    @Override
    public void menuSelectCheck() {
        System.out.println("Works");
    }
}

// delete these comments later
// All students: name and ID
// Undergrad: undergrad courses taken, gpa, resident or not

// MS: grad courses taken[],
// PHD: labs supervised (discount), advisor, research subject
// separate research fee of 700 for PHD students

abstract class Student {

    // Data
    private String name;
    private String id;

    // Setters and Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Constructor
    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return id.toUpperCase() + "-" + name.toUpperCase();
    }

    abstract public void printInvoice();
}

class UndergraduateStudent extends Student{

    // Data
    private int[] undergradCrnsTaken;
    private double gpa;
    private boolean resident;

    // Setters and getters
    public int[] getUndergradCrnsTaken() {
        return undergradCrnsTaken;
    }

    public void setUndergradCrnsTaken(int[] undergradCrnsTaken) {
        this.undergradCrnsTaken = undergradCrnsTaken;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public boolean isResident() {
        return resident;
    }

    public void setResident(boolean resident) {
        this.resident = resident;
    }

    // Constructor
    public UndergraduateStudent(String name, String id, int[] undergradCrnsTaken, double gpa, boolean resident) {
        super (name, id);
        this.undergradCrnsTaken = undergradCrnsTaken.clone();
        this.gpa = gpa;
        this.resident= resident;
    }

    // Function to get cost for credit hours depending on if OOS resident or not
    // ...

    @Override
    public void printInvoice() {
        double price= 0;
        double baseprice=120.25;
        double health= 35.00;


        System.out.println("\nVALENCE COLLEGE\nORLANDO FL 10101");
        System.out.println("-----------------------------");
        System.out.println(getId()+"-" + getName());
        System.out.println("\n1 Credit Hour = $"+baseprice);
        System.out.println("CRN\tCR_PREFIX\tCR_HOURS");

        //loop through the classes they are taking
        for (int i=0;i<this.undergradCrnsTaken.length;i++){
            System.out.printf("\n%d\t%s\t\t%d",undergradCrnsTaken[i],undergradCrnsTaken[i],undergradCrnsTaken[i]);
        }
        //
        System.out.println("\n\t\tHealth & id Fees  $ "+health);
        System.out.println("\n---------------------------------");
        if(gpa>=3.5){

        }else{
            System.out.println("\t\tTOTAL PAYMENTS\t\t$ "+ price);
        }


    }
}

abstract class GraduateStudent extends Student {

    public GraduateStudent (String name, String id) {
        super(name, id);
    }
}

class PHDStudent extends GraduateStudent {

    // Data
    private int[] labsSupervised;
    private String advisor;
    private String researchSubject;

    // Setters and Getters
    public int[] getlabsSupervised() {
        return labsSupervised;
    }

    public void setLabsSupervised(int[] labsSupervised) {
        this.labsSupervised = labsSupervised;
    }

    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    public String getResearchSubject() {
        return researchSubject;
    }

    public void setResearchSubject(String researchSubject) {
        this.researchSubject = researchSubject;
    }

    // Constructor
    public PHDStudent(String name, String id, int[] labsSupervised, String advisor, String researchSubject) {
        super(name, id);
        this.labsSupervised = labsSupervised.clone();
        this.advisor = advisor;
        this.researchSubject = researchSubject;
    }

    // Function to get total cost for a PHD student
    // ...

    @Override
    public void printInvoice() {
        // ...
    }
}
class  MSStudent extends GraduateStudent {

    // Data
    private int[] gradCrnsTaken;

    // Setter and getter
    public int[] getGradCrnsTaken() {
        return gradCrnsTaken;
    }

    public void setGradCrnsTaken(int[] gradCrnsTaken) {
        this.gradCrnsTaken = gradCrnsTaken;
    }

    // Constructor
    public MSStudent(String name, String id, int[] gradCrnsTaken) {
        super(name, id);
        this.gradCrnsTaken = gradCrnsTaken.clone();

    }
    public void printInvoice(){
        // ...
    }
}



//Node class that houses individual student 
//Points to next student
class studentNode {

    private Student e;
    private studentNode next;

    public studentNode(Student e){
        this.e=e;
        this.next=null;
    }

    public Student getStudent(){
        return e;
    }

    public studentNode getNext(){
        return next;
    }

    public void setNext(studentNode next) {
        this.next = next;
    }

}


//Linked List Class 
class studentLinkedList{
    studentNode school;

    public studentLinkedList(){
        school=null;
    }

    //Adds a new student to the linked list 
    public void addNewStudent(Student e){
        studentNode newNode=new studentNode(e);

        //checks if the school is null if the newNode is the first student
        if(school==null){
            school=newNode;
            return;
        }
        //Always keep the pointer to the beginning of the LinkedList
        studentNode current=school;
        //Iterates through the linked list and finds the next empty position
        //Then sets the newStudent as that next empty position
        while(current!=null){
            if(current.getNext()==null){
                current.setNext(newNode);
                return;
            }
            current=current.getNext();
        }
    }

    //search the student by id
    public void searchStudent(String id){

        studentNode current=school;
        while (current != null){
            if (current.getStudent().getId().equals(id)){
                System.out.println(current.getStudent());
                return;
            }
            current=current.getNext();
        }
        System.out.println("Student Not Found");
        return;
    }

    //Deletes the student by id
    public void deleteStudent(String id){

        //if there are no students in the school
        if(school==null){
            return;
        }

        //if the first student is being deleted
        if(school.getStudent().getId().equals(id)){
            school=school.getNext();
            return;
        }

        studentNode prev=school;
        studentNode current=school.getNext();

        while(current!=null){
            if (current.getStudent().getId().equals(id)){
                prev.setNext(current.getNext());
                System.out.println(current.getStudent().getName()+" Has Been Successfully Deleted");
                return;
            }
            prev=current;
            current=current.getNext();
        }
        System.out.println("Student Not Found");
        return;
    }


    //Prints all students in the linked list
    //implementation for grouping based on class
    public void printListStudents(){
        if (school== null){
            return;
        }

        studentNode current=school;
        while(current!= null){
            System.out.println("- "+current.getStudent().getName());
            current=current.getNext();
        }
        return;
    }

    //print fee invoice based on Id
    public void printFeeInvoice(String id){
        if (school == null){
            return;
        }

        studentNode current= school;
        while (current != null){
            if(current.getStudent().getId().equals(id)){
                current.getStudent().printInvoice();
                return;
            }
            current=current.getNext();
        }
        return;
    }
}

enum LectureType {
    GRAD, UNDERGRAD;
}

enum LectureMode {
    F2F, MIXED, ONLINE;
}
//_______________________________________________________________________________

class Lab {
    private String crn;
    private String classroom;

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        return crn + "," + classroom;
    }

    public Lab(String crn, String classroom) {
        this.crn = crn;
        this.classroom = classroom;
    }

}//end of class Lab

class FileInteractions{
    File lecfile = new File("lec.txt");
    public void initialize(){

    }
}

//_______________________________________________________________________________
class Lecture {

    private String crn;
    private String prefix;
    private String lectureName;

    private LectureType lectureType; //Grad or UnderGrad
    private LectureMode lectureMode; //F2F, Mixed or Online
    private String classroom;
    private boolean hasLabs;
    private int creditHours;

    ArrayList<Lab> labs;


    // _________________

    //Helper method-used in constructors to set up the common fields
    private void LectureCommonInfoSetUp (String crn, String prefix, String lectureName, LectureType lectureType, LectureMode lectureMode) {
        this.crn = crn;
        this.prefix = prefix;
        this.lectureName = lectureName;
        this.lectureType = lectureType;
        this.lectureMode = lectureMode;
    }

    // Non-online with Labs
    public Lecture(	String crn,
                       String prefix,
                       String lectureName,
                       LectureType lectureType,
                       LectureMode lectureMode,
                       String classroom,
                       boolean hasLabs,
                       int creditHours,
                       ArrayList<Lab> labs ) {

        LectureCommonInfoSetUp(crn,prefix,lectureName,lectureType,lectureMode);
        this.classroom = classroom;
        this.hasLabs = hasLabs;
        this.creditHours = creditHours;
        this.labs = labs;
    }



    // Constructor for Non-online without Labs
    public Lecture( String crn, String prefix, String lectureName, LectureType lectureType, LectureMode lectureMode, String classroom,
                    boolean hasLabs, int creditHours) {

        LectureCommonInfoSetUp(crn,prefix,lectureName,lectureType,lectureMode);
        this.classroom = classroom;
        this.hasLabs = hasLabs;
        this.creditHours = creditHours;




    }

    // Constructor for Online Lectures
    public Lecture(String crn, String prefix, String lectureName, LectureType lectureType, LectureMode lectureMode, int creditHours) {
        LectureCommonInfoSetUp(crn,prefix,lectureName,lectureType,lectureMode);
        this.classroom = classroom;
        this.hasLabs = hasLabs;
        this.creditHours = creditHours;

    }
//________

    @Override
    public String toString() {
        String lectureAndLabs = crn + "," + prefix + "," + lectureName + "," + lectureType + ","
                + lectureMode + "," + hasLabs + "," + creditHours+"\n";

        if ( labs != null ) {//printing corresponding labs
            //lectureAndLabs+="\n";
            for (Lab lab: labs)
                lectureAndLabs+= lab +"\n";
        }
        return lectureAndLabs;

    }



}