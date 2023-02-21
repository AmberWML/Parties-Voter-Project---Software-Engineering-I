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
        
        System.out.println("Called the ClosedPartyProcessor constructor to create a new ClosedPartyProcessor vp." +
                "  The object should not equal null. Remaining fields are covered in VoteProcessor or are private.");
        System.out.println("Does vp == null?: " + (boolean) (vp == null));
        
        System.out.println("- - - - - - - - - - - - - - - - - -");
        
        System.out.println("Run() tests: ");
      
        System.out.println("- - - - - - - - - - - - - - - - - -");
     
        vp = new ClosedPartyProcessor(new InputFile("../testing/CPL.csv"));
      
        System.out.println("PrintResults() tests:");
        System.out.println("testing vp");
       
        vp.Run();
        System.out.println(vp.PrintResults());
      
 	}
 }
    
  