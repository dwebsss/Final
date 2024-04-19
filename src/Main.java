import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    boolean mainChecker = true;
    Scanner mainScan = new Scanner(System.in);
    while(mainChecker){
            try{
                throw new myExcept();
            }
            catch(myExcept e){

            }
        }
    }
}
class myExcept extends Throwable{

}
abstract class Menus<T>{
   abstract public T menuPromptAndSelect();

  abstract public void menuSelectCheck();
}

class MainMenu extends Menus{
    StudentManagement studentManagementMenu = new StudentManagement();
    CourseManagement courseManagementMenu = new CourseManagement();
    boolean whileToggle = true;
    @Override
    public void menuSelectCheck(){
        int userSelection  = -1;
        userSelection = menuPromptAndSelect();
        switch (userSelection){
            case 0->{ System.out.println("Goodbye!");
            System.exit(0);}
            case 1->{studentManagementMenu.menuSelectCheck();}
            case 2->{courseManagementMenu.menuSelectCheck();}
            default -> {System.out.println("fatal error! program will shutdown");System.exit(1);}
        }

    }
    @Override
    public int menuPromptAndSelect(){
        boolean toggle = true;
        int mainSelection = 0;
        while (toggle) {
            try {
                Scanner mainScan = new Scanner(System.in);
                System.out.println("-------------------------------------------\nMain Menu\n\n1 : Student Management\n2 : Course Management\n0 : Exit");
                mainSelection = mainScan.nextInt();
                mainScan.close();
                if(mainSelection>2 || mainSelection<0){
                    throw new myExcept();
                }
            }
            catch (myExcept | Exception e){
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
class StudentManagement extends Menus{
    //student management initial prompt code
    @Override
    public int menuPromptAndSelect(){
        boolean toggle = true;
        char studentSelection = ' ';
        while (toggle) {
            try {
                Scanner studentScan = new Scanner(System.in);
                System.out.println("a. add a student\nb. search for a student by id\nc. delete a student\nd. print the fee invoice of a student by id.\ne. Print all students");
                studentSelection = studentScan.next().charAt(0);
                studentScan.close();
                if(studentSelection != ('a'|'b'|'c'|'d'|'e')){
                    throw new myExcept();
                }
            }
            catch (myExcept | Exception e){
                System.out.println("invalid input!");
            }
            finally {
                if(studentSelection ==('a'|'b'|'c'|'d'|'e') ){
                    toggle = false;
                }
            }
        }
        return studentSelection;

    }

    @Override
    public void menuSelectCheck() {

    }
}

class CourseManagement extends Menus{
    @Override
    public int menuPromptAndSelect() {
        return 0;
    }

    @Override
    public void menuSelectCheck() {

    }
}
abstract class Student{
abstract public void printInvoice();
}

class UndergraduateStudent extends Student{
    public void printInvoice(){

    }
}

abstract class GraduateStudent extends Student{

}

class PHDStudent extends GraduateStudent{
    public void printInvoice(){

    }
}
class  MSStudent extends GraduateStudent{
    public void printInvoice(){

    }
}