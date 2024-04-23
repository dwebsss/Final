import java.util.InputMismatchException;
import java.util.Scanner;
//AJ Test
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main { //main class where all the classes and methods will be called
    public static void main(String[] args) {
        boolean mainChecker = true; //boolean variable to toggle the while loop for the main menu
        Scanner mainScan = new Scanner(System.in);  //main scanner for user input
        while(mainChecker){ // while loop for main body to maintain the main menu until user exits the program.
            try{
                throw new MyException();
            }
            catch(MyException e){

            }
        }
    }
}
class MyException extends  Throwable {

}
abstract class Menus<T> {//abstract class for all menus to be based off
    abstract public T menuPromptAndSelect(); // method that prints out the menu prompt and store the user's input selection

    abstract public void menuSelectCheck(); //takes in the user's input preformed by the first class and preforms and action based on the user's input
}

class MainMenu extends Menus<Integer> {
    StudentManagement studentManagementMenu = new StudentManagement(); //studentMenu object for the Mainmenu class to access the student menu overriden method
    CourseManagement courseManagementMenu = new CourseManagement(); //studentMenu object for the Mainmenu class to access the student menu overriden method
    boolean whileToggle = true; // another boolean variable to maintain a while loop until a certain breakpoint
    @Override
    public void menuSelectCheck() { //takes in the user input stored in the menuPrompt class
        int userSelection  = -1;
        userSelection = menuPromptAndSelect(); //internal variable is set to the user input(which is stored in the prompt class
        switch (userSelection){
            case 0->{ System.out.println("Goodbye!"); //case where user selects the exit option and the program is terminated
                System.exit(0);}
            case 1->{studentManagementMenu.menuSelectCheck();}//case where student selects the student management option, uses the student object
            case 2->{courseManagementMenu.menuSelectCheck();}//case where student selects the course management option, uses the course object
            default -> {System.out.println("fatal error! program will shutdown");System.exit(1);}// this default should never be reached as the main code block catches all mismatched user inputs and exceptions, therefore if it is somehow reached it will be treated as an error and the program will be closed
        }

    }
    @Override
    public Integer menuPromptAndSelect() {
        boolean toggle = true;
        int mainSelection = 0;
        while (toggle) {
            try {
                Scanner mainScan = new Scanner(System.in);
                System.out.println("-------------------------------------------\nMain Menu\n\n1 : Student Management\n2 : Course Management\n0 : Exit");
                mainSelection = mainScan.nextInt();
                mainScan.close();
                if(mainSelection>2 || mainSelection<0){
                    throw new MyException();
                }
            }
            catch (MyException | Exception e) {
                System.out.println("invalid input!");
            }
            finally {
                if(mainSelection>=0 && mainSelection<= 2){
                    toggle = false;
                }
            }
        }
        return mainSelection;

    }

}
class StudentManagement extends Menus<Character> {
    //student management initial prompt code
    @Override
    public Character menuPromptAndSelect() {
        boolean toggle = true;
        char studentSelection = ' ';
        while (toggle) {
            try {
                Scanner studentScan = new Scanner(System.in);
                System.out.println("a. add a student\nb. search for a student by id\nc. delete a student\nd. print the fee invoice of a student by id.\ne. Print all students"); // will change
                studentSelection = studentScan.next().charAt(0);
                studentScan.close();
                if(studentSelection != ('a'|'b'|'c'|'d'|'e')){ // will change
                    throw new MyException();
                }
            }
            catch (MyException | Exception e) {
                System.out.println("invalid input!");
            }
            finally {
                if(studentSelection ==('a'|'b'|'c'|'d'|'e') ) { // will change
                    toggle = false;
                }
            }
        }
        return studentSelection;

    }

    @Override
    public void menuSelectCheck() {
        char sMenuSelection;
        sMenuSelection = menuPromptAndSelect();
        switch (sMenuSelection){
            case 'a'->{}
            case 'b'->{}
            case 'c'->{}
            case 'd'->{}
            case 'e'->{}
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
        /// ...
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
            if (current.getStudent().getId()== id){
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
        if(school.getStudent().getId()== id){
            school=school.getNext();
            return;
        }

        studentNode prev=school;
        studentNode current=school.getNext();

        while(current!=null){
            if (current.getStudent().getId()==id){
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


    //Implement print fee invoice 
}