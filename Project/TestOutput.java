import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * TEST CLASS: OutputFile<br>
 * The purpose of this class is to test the functionality of OutputFile.java. <br>
 * Running "java TestOutput" will automatically run the tests outlined here.
 *
 * @author Amber Wong, CSCI 5801 Team 10, University of Minnestota
 */
public class TestOutput {
    public static void main(String args[]) {
        System.out.println("-----------------------------\n" +
                "--------  OutputFile Tests ---------\n" +
                "-----------------------------\n");

        /** Test the constructor */
        OutputFile f= new OutputFile("../testing/OutputFile.txt");
        System.out.println("Called the OutputFile constructor to create a new outputfile outputfile." +
                " NOne of f and f.filerep should equal null. File's path should equals what we have and return True");
        System.out.println("Does f == null ? " + (boolean) (f == null));
        System.out.println("Does f.path == The path we have ?: " + (boolean) (f.path == "../testing/OutputFile.txt"));
        System.out.println("Does f.fileRep == null?: " + (boolean) (f.fileRep == null));
       

        testwriteLn(f);
    
    }

    public static void testwriteLn(OutputFile f) {
        f.writeLn("Hi");
        System.out.println("\nCalled f.writeLn(\"Hi\").\nThe writeln window should write the \"HI\" to the outputfile.");
        f.writeLn("Test1");
        System.out.println("\nCalled f.writeLn(\"Test1\").\nThe writeln window should write the \"Test1\" to the outputfile.");
        f.writeLn("Test2");
        System.out.println("\nCalled f.writeLn(\"Test2\").\nThe writeln window should write the \"Test2\" to the outputfile.");
 }
}
