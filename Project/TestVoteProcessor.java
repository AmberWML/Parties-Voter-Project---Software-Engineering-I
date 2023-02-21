import java.io.File;

/**
 * TEST CLASS: VoteProcessor <br>
 * The purpose of this class is to test the functionality of VoteProcessor.java. <br>
 * Running "java TestVoteProcessor.java" will automatically run the tests outlined here.
 *
 * @author Ashton Koversky, CSCI 5801 Team 10, University of Minnesota
 */
public class TestVoteProcessor{
    public static void main(String[] args)
    {
        System.out.println("-----------------------------\n" +
                "--------  VoteProcessor Tests ---------\n" +
                "-----------------------------\n");
        
        InputFile i = new InputFile("../testing/CPL.csv");
        
        /** Test the constructor */
        ClosedPartyProcessor vp = new ClosedPartyProcessor(i);
        System.out.println("Called the ClosedPartyProcessor constructor to create a new ClosedPartyProcessor vp." +
                "  No fields should equal null.");
        System.out.println("Does vp == null?: " + (boolean) (vp == null));
        System.out.println("Does vp.input == null?: " + (boolean) (vp.input == null));
        System.out.println("Does vp.parties == null?: " + (boolean) (vp.parties == null));
        System.out.println("Does vp.votes == null?: " + (boolean) (vp.votes == null));
        System.out.println("Does vp.seats == null?: " + (boolean) (vp.seats == null));
        System.out.println("Does vp.ranFlag == false?: " + (vp.ranFlag == false));
        System.out.println("Does vp.audit == null?: " + (boolean) (vp.audit == null));
        
        File testFile = new File("");
        String currentPath = testFile.getAbsolutePath();
        System.out.println("current path is: " + currentPath); 
    }
}
