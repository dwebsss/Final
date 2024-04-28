import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main { //main class where all the classes and methods will be called
    static studentLinkedList school= new studentLinkedList();
    public static void main(String[] args) {
        //Linked List holds all the students
        //not sure where to initialize this based on menu objects
    }
}

class MyException extends Throwable {

}

// Abstract class for all menus to be based off
abstract class Menus<T> {
    
    // Prints out menu prompt and stores user's input selection
    abstract public T menuPromptAndSelect();

    // Takes user input performed by first class and performs action based on user input
    abstract public void menuSelectCheck();
}

class MainMenu extends Menus<Integer> {

    // Objects for class to access overridden methods
    StudentManagement studentManagementMenu = new StudentManagement();
    CourseManagement courseManagementMenu = new CourseManagement();

    // Takes user input stored in the Menu Class
    @Override
    public void menuSelectCheck() {
        int userSelection = -1;
        userSelection = menuPromptAndSelect();
        switch (userSelection) {

            // User selects "Exit"
            case 0->{ System.out.println("Take Care!");
                System.exit(0);break;}

            // User selects "Student Management"
            case 1->{studentManagementMenu.menuSelectCheck();
                break;
            }

            // User selects "Course Management"
            case 2->{courseManagementMenu.menuSelectCheck();break;}

            // Error, should never be reached
            default -> {System.out.println("fatal error! program will shutdown");System.exit(1);}
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

    @Override
    public Character menuPromptAndSelect() {
        boolean toggle = true;
        char studentSelection = ' ';

        Scanner studentScan = new Scanner(System.in);

        try {
            System.out.println("Student management Menu:\nChoose one of:\nA - Search add a student\nB - Delete a student\nC - Print Fee Invoice\nD - Print List of Students\nX - Back to Main menu");
            studentSelection = studentScan.next().charAt(0);
            studentSelection = Character.toLowerCase(studentSelection);

            switch (studentSelection) {
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'X':
                    break; // Valid input
                default:
                    throw new MyException(); // Invalid input
            }
        }
        catch (MyException | Exception e) {
            System.out.println("invalid input!");
        }
        finally {
            //this isn't necessary
            //if(studentSelection == 'a'  studentSelection != 'b' && studentSelection != 'c' && studentSelection != 'd' && studentSelection != 'e') ) { // will change
            //    toggle = false;
            // ...
        }
        return studentSelection;
    }

    // Need to fix formatting for this class
    // ...
    @Override
    public void menuSelectCheck() {

        char sMenuSelection;
        sMenuSelection = menuPromptAndSelect();
        Scanner addStudent= new Scanner(System.in);

        switch (sMenuSelection){
            case 'A'->{

                String name,id, type;
                double gpa;
                int classes;

                //getting the students Info try statement if the ID is not in the format specified
                //try {
                System.out.println("Enter student ID:");
                id=addStudent.nextLine();

                //}
                
                System.out.println("Enter the student's name:");
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

abstract class Student {

    private String name;
    private String id;

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
    
    private int[] undergradCrnsTaken;
    private double gpa;
    private boolean resident;
    
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

    public UndergraduateStudent(String name, String id, int[] undergradCrnsTaken, double gpa, boolean resident) {
        super (name, id);
        this.undergradCrnsTaken = undergradCrnsTaken.clone();
        this.gpa = gpa;
        this.resident= resident;
    }

    @Override
    public void printInvoice() {
        double price = 0;
        double basePrice = 120.25;
        double health = 35.00;

        if (isResident())
            basePrice *= 2;

        System.out.println("\nVALENCE COLLEGE\nORLANDO FL 10101");
        System.out.println("-----------------------------");
        System.out.println(getId() + "-" + getName());
        System.out.println("\n1 Credit Hour = $" + basePrice);
        System.out.println("CRN\tCR_PREFIX\tCR_HOURS");

        // Loop through classes
        for (int i = 0; i < this.undergradCrnsTaken.length; i++) {
            System.out.printf("\n%d\t%s\t\t%d", undergradCrnsTaken[i], undergradCrnsTaken[i], undergradCrnsTaken[i]);
        }
        System.out.println("\n\t\tHealth & id Fees  $ " + health);
        System.out.println("\n---------------------------------");
        double discount = 0;
        if (getGpa() >= 3.5 && price > 500) { // For some reason, it says that this is always false?
            discount = price * 0.25;
            // print total minus discounted prices 
        } else {
            System.out.println("\t\tTOTAL PAYMENTS\t\t$ " + price);
        }
    }
}

abstract class GraduateStudent extends Student {

    public GraduateStudent (String name, String id) {
        super(name, id);
    }
}

class PHDStudent extends GraduateStudent {
    
    private int[] labsSupervised;
    private String advisor;
    private String researchSubject;

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

    private int[] gradCrnsTaken;

    public int[] getGradCrnsTaken() {
        return gradCrnsTaken;
    }

    public void setGradCrnsTaken(int[] gradCrnsTaken) {
        this.gradCrnsTaken = gradCrnsTaken;
    }

    public MSStudent(String name, String id, int[] gradCrnsTaken) {
        super(name, id);
        this.gradCrnsTaken = gradCrnsTaken.clone();

    }
    
    public void printInvoice() {
        double price = 0;
        double basePrice = 300.00;
        double health = 35.00

        System.out.println("\nVALENCE COLLEGE\nORLANDO FL 10101");
        System.out.println("-----------------------------");
        System.out.println(getId() + "-" + getName());
        System.out.println("\n1 Credit Hour = $" + basePrice);
        System.out.println("CRN\tCR_PREFIX\tCR_HOURS");

        for (int i = 0; i < this.gradCrnsTaken.length; i++) {
            System.out.printf(); //...
        }
        System.out.println("\n\t\tHealth & id Fees  $ " + health);
        System.out.println("\n---------------------------------");
        System.out.println("\t\tTOTAL PAYMENTS\t\t$ " + price);
}

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

class studentLinkedList{
    studentNode school;

    public studentLinkedList(){
        school=null;
    }

    //Adds a new student to the linked list 
    public void addNewStudent(Student e){
        studentNode newNode=new studentNode(e);

        // Checks if the school is null if the newNode is the first student
        if(school==null){
            school=newNode;
            return;
        }
        
        studentNode current=school;
        
        while(current!=null){
            if(current.getNext()==null){
                current.setNext(newNode);
                return;
            }
            current=current.getNext();
        }
    }

    // Search the student by ID
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

    // Deletes the student by id
    public void deleteStudent(String id){

        // If there are no students in the school, return
        if(school==null){
            return;
        }

        // If the first student is being deleted, return
        if(school.getStudent().getId().equals(id)){
            school=school.getNext();
            return;
        }

        studentNode prev=school;
        studentNode current=school.getNext();

        while(current!=null){
            if (current.getStudent().getId().equals(id)){
                prev.setNext(current.getNext());
                System.out.println(current.getStudent().getName() +" Has Been Successfully Deleted");
                return;
            }
            prev=current;
            current=current.getNext();
        }
        System.out.println("Student Not Found");
        return;
    }
    
    // Prints all students in the linked list
    // Implementation for grouping based on class
    public void printListStudents(){
        if (school== null){
            return;
        }

        studentNode current=school;
        while(current!= null){
            System.out.println("- " + current.getStudent().getName());
            current=current.getNext();
        }
        return;
    }

    // Print fee invoice based on Id
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
}

class FileInteractions{
    File lecfile = new File("lec.txt");
    public void initialize(){

    }
}

class Lecture {

    // Make setters and getters 
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

    // Helper method-used in constructors to set up the common fields
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
    
    // Constructor for non-online without labs
    public Lecture( String crn, String prefix, String lectureName, LectureType lectureType, LectureMode lectureMode, String classroom,
                    boolean hasLabs, int creditHours) {

        LectureCommonInfoSetUp(crn,prefix,lectureName,lectureType,lectureMode);
        this.classroom = classroom;
        this.hasLabs = hasLabs;
        this.creditHours = creditHours;
    }

    // Constructor for online lectures
    public Lecture(String crn, String prefix, String lectureName, LectureType lectureType, LectureMode lectureMode, int creditHours) {
        LectureCommonInfoSetUp(crn,prefix,lectureName,lectureType,lectureMode);
        this.classroom = classroom;
        this.hasLabs = hasLabs;
        this.creditHours = creditHours;
    }

    @Override
    public String toString() {
        String lectureAndLabs = crn + "," + prefix + "," + lectureName + "," + lectureType + ","
                + lectureMode + "," + hasLabs + "," + creditHours+"\n";

        // Printing corresponding labs
        if (labs != null) {

            //lectureAndLabs+="\n";
            for (Lab lab : labs)
                lectureAndLabs += lab + "\n";
        }
        return lectureAndLabs;
    }
}
