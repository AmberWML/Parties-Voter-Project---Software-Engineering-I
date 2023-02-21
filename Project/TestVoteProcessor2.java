import java.util.*;
import java.util.Random;
import java.io.File;
import java.io.InterruptedIOException;

/**
 * TEST CLASS: VoteProcessor <br>
 * The purpose of this class is to test the functionality of VoteProcessor.java. <br>
 * Running "java TestVoteProcessor" will automatically run the tests outlined here.
 *
 * @author Henry Olson, Ashton Koversky CSCI 5801 Team 10, University of Minnestota
 */
public class TestVoteProcessor2 {
    public static void main(String args[]) {

        // ****************** //
        System.out.println("-----------------------------\n" +
                "--------  VoteProcessor Tests ---------\n" +
                "-----------------------------\n");

       // InputFile i = new InputFile("CPL.txt");

        /** Test the constructor CPL constructor */
        ClosedPartyProcessor vp2 = new ClosedPartyProcessor(new InputFile("../testing/CPL.csv"));
        System.out.println("Called the ClosedPartyProcessor constructor to create a new ClosedPartyProcessor vp2." +
                "  No fields should equal null.");
        System.out.println("Does vp2 == null?: " + (boolean) (vp2 == null));
        System.out.println("Does vp2.input == null?: " + (boolean) (vp2.input == null));
        System.out.println("Does vp2.parties == null?: " + (boolean) (vp2.parties == null));
        System.out.println("Does vp2.votes == null?: " + (boolean) (vp2.votes == null));
        System.out.println("Does vp2.seats == null?: " + (boolean) (vp2.seats == null));
        System.out.println("Does vp2.ranFlag == false?: " + (vp2.ranFlag == false));
        System.out.println("Does vp2.audit == null?: " + (boolean) (vp2.audit == null));
        System.out.println("- - - - - - - - - - - - - - - - - -");

        File testFile = new File("");
        String currentPath = testFile.getAbsolutePath();
        System.out.println("current path is: " + currentPath);

        System.out.println("- - - - - - - - - - - - - - - - - -");


        OpenPartyProcessor vp = new OpenPartyProcessor(new InputFile("../testing/test.csv"));
        System.out.println("Called the OpenPartyProcessor constructor to create a new OpenPartyProcessor vp." +
                "  No fields should equal null.");
        System.out.println("Does vp == null?: " + (boolean) (vp == null));
        System.out.println("Does vp.input == null?: " + (boolean) (vp.input == null));
        System.out.println("Does vp.parties == null?: " + (boolean) (vp.parties == null));
        System.out.println("Does vp.votes == null?: " + (boolean) (vp.votes == null));
        System.out.println("Does vp.seats == null?: " + (boolean) (vp.seats == null));
        System.out.println("Does vp.ranFlag == false?: " + (vp.ranFlag == false));
        System.out.println("Does vp.audit == null?: " + (boolean) (vp.audit == null));
        

        System.out.println("- - - - - - - - - - - - - - - - - -");
        
        // Reset the VoteProcessor object for the next round of testing
        vp2 = new ClosedPartyProcessor(new InputFile("../testing/CPL.csv"));
        vp = new OpenPartyProcessor(new InputFile("../testing/test.csv"));

        System.out.println("\nTieBreak() tests");
        testTieBreak(vp);
        
        System.out.println("- - - - - - - - - - - - - - - - - -");


        System.out.println("- - - - - - - - - - - - - - - - - -");


        System.out.println("\nDistributeSeats() tests - OpenParty: ");
        testDistributeSeats(vp);
        

        
        // Reset the VoteProcessor object for the next round of testing
      
        vp = new OpenPartyProcessor(new InputFile("../testing/test.csv"));

        System.out.println("\nGenMediaReport() tests - OpenParty: ");
        testGenMediaReport(vp2);
       
        // **************************//
    }

    public static void testDistributeSeats(VoteProcessor vp) {

        // Run the proceessor to make sure all values are initialized
        // vp.Run();

        List<String> alphabet = new ArrayList<String>();
        alphabet.addAll(Arrays.asList(new String[]{"D", "R", "I"}));

        // Set up the test seat array
        Map<String, Integer> testSeats = new HashMap<String, Integer>();
        testSeats.put("D", 0);
        testSeats.put("R", 0);
        testSeats.put("I", 0);

        System.out.println("Standard distribution of seats - D:2, R:1, I:0");
        Map<String, Integer> testVotes = new HashMap<String, Integer>();
        testVotes.put("D", 5);
        testVotes.put("R", 3);
        testVotes.put("I", 1);
        testSeats = vp.DistributeSeats(3, 9, alphabet, testVotes);
        System.out.println("Party \"D\" - " + testSeats.get("D"));
        System.out.println("Party \"R\" - " + testSeats.get("R"));
        System.out.println("Party \"I\" - " + testSeats.get("I"));

        System.out.println("Standard distribution of seats 2 - D:1 or 0, R:2, I:1 or 0");
        testVotes = new HashMap<String, Integer>();
        testVotes.put("D", 2);
        testVotes.put("R", 5);
        testVotes.put("I", 2);
        testSeats = vp.DistributeSeats(3, 9, alphabet, testVotes);
        System.out.println("Party \"D\" - " + testSeats.get("D"));
        System.out.println("Party \"R\" - " + testSeats.get("R"));
        System.out.println("Party \"I\" - " + testSeats.get("I"));

        System.out.println("Even distribution of seats - D:1, R:1, I:1");
        testVotes.replace("D", 3);
        testVotes.replace("R", 3);
        testVotes.replace("I", 3);
        testSeats = vp.DistributeSeats(3, 9, alphabet, testVotes);
        System.out.println("Party \"D\" - " + testSeats.get("D"));
        System.out.println("Party \"R\" - " + testSeats.get("R"));
        System.out.println("Party \"I\" - " + testSeats.get("I"));

        System.out.println("Even distribution of seats 2 - D:1, R:1, I:1");
        testVotes.replace("D", 7);
        testVotes.replace("R", 6);
        testVotes.replace("I", 7);
        testSeats = vp.DistributeSeats(3, 20, alphabet, testVotes);
        System.out.println("Party \"D\" - " + testSeats.get("D"));
        System.out.println("Party \"R\" - " + testSeats.get("R"));
        System.out.println("Party \"I\" - " + testSeats.get("I"));

        
    }
    
    public static void testTieBreak(VoteProcessor vp)
    {
    	// Initialize the list of a 3-way tie
        List<String> alphabet = new ArrayList<String>();
        alphabet.addAll(Arrays.asList(new String[]{"D", "R", "I"}));
        // Complete a series of tests
        System.out.println(vp.TieBreak(alphabet));
        System.out.println(vp.TieBreak(alphabet));
        System.out.println(vp.TieBreak(alphabet));
        System.out.println(vp.TieBreak(alphabet));
        System.out.println(vp.TieBreak(alphabet));
        System.out.println(vp.TieBreak(alphabet));
        System.out.println(vp.TieBreak(alphabet));
        System.out.println(vp.TieBreak(alphabet));
        // Initialize list of a 2-way tie
        List<String> ab = new ArrayList<String>();
        ab.addAll(Arrays.asList(new String[]{"a", "b"}));
        //Complete a series of tests
        System.out.println(vp.TieBreak(ab));
        System.out.println(vp.TieBreak(ab));
        System.out.println(vp.TieBreak(ab));
        System.out.println(vp.TieBreak(ab));
        System.out.println(vp.TieBreak(ab));
        System.out.println(vp.TieBreak(ab));
    }
    
   
    public static void testGenMediaReport(VoteProcessor vp)
    {
    	// Try to generate the media report without running the election
    	vp.GenMediaReport();
    	// Run the election
    	vp.Run();
    	// Generate the media report
    	vp.GenMediaReport();
    }
    
}