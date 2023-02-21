import java.util.*;
import java.util.Random;
import java.io.File;
import java.io.InterruptedIOException;

/**
 * TEST CLASS: ClosedPartyProcessor <br>
 * The purpose of this class is to test the functionality of ClosedPartyProcessor.java. <br>
 * Running "java TestClosedPartyProcessor" will automatically run the tests outlined here.
 *
 * @author Ashton Koversky CSCI 5801 Team 10, University of Minnestota
 */
 
 public class TestClosedPartyProcessor {
    public static void main(String args[]) {
         /** Test the constructor CPL constructor */
        ClosedPartyProcessor vp = new ClosedPartyProcessor(new InputFile("../testing/CPL.csv"));
        // ClosedPartyProcessor vp2 = new ClosedPartyProcessor(new InputFile("../testing/CPL2.csv"));
        // ClosedPartyProcessor vp3 = new ClosedPartyProcessor(new InputFile("../testing/CPL3.csv"));
        System.out.println("Called the ClosedPartyProcessor constructor to create a new ClosedPartyProcessor vp." +
                "  The object should not equal null. Remaining fields are covered in VoteProcessor or are private.");
        System.out.println("Does vp == null?: " + (boolean) (vp == null));
        
        System.out.println("- - - - - - - - - - - - - - - - - -");
        
        System.out.println("Run() tests: ");
        // testRun(vp);
        
        // testRun(vp2);
        // testRun(vp3);
        System.out.println("- - - - - - - - - - - - - - - - - -");
        // Reset the VoteProcessor object for the next round of testing
        vp = new ClosedPartyProcessor(new InputFile("../testing/CPL.csv"));
        // vp2 = new ClosedPartyProcessor(new InputFile("../testing/CPL2.csv"));
        // vp3 = new ClosedPartyProcessor(new InputFile("../testing/CPL3.csv"));
        
        System.out.println("PrintResults() tests:");
        System.out.println("testing vp");
        System.out.println(v.PrintResults());
        vp.run();
        // System.out.println("testing vp2");
        // testPrintResults(vp2);
        // System.out.println("testing vp3");
        // testPrintResults(vp3);
 
 	}
    
    // public static void testRun(ClosedPartyProcessor v)
    // {
    // 	// Checks to see if the election will be processed properly
    // 	v.Run();
    // }
    
    // public static void testPrintResults(ClosedPartyProcessor v)
    // {
    // 	// Results before calling Run()
    // 	// System.out.println(v.PrintResults());
    // 	v.Run();
    // 	// Results after calling Run()
    // 	System.out.println(v.PrintResults());
    // }
 }