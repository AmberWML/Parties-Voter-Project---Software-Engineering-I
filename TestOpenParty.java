/**
 * TEST CLASS: OpenPartyProcessor <br>
 * The purpose of this class is to test the functionality of OpenPartyProcessor.java. <br>
 * Running "java TestOpenParty" will automatically run the tests outlined here.
 *
 * @author Henry Olson, CSCI 5801 Team 10, University of Minnestota
 */
public class TestOpenParty {
    public static void main(String args[]) {
        System.out.println("-----------------------------\n" +
                           "-  OpenPartyProcessor Tests -\n" +
                           "-----------------------------\n");
        TestConstructor();
        TestOrderCandidates();
        TestGetParty();
        TestPrintResults();
        TestRun();
       
        
    }

    public static void TestConstructor() {
        /** Test that no exceptions are thrown during the constructor. */
        System.out.println("Testing the OpenPartyProcessor constructor:");
        OpenPartyProcessor vp = new OpenPartyProcessor(new InputFile("../testing/test.csv"));
        System.out.println("OpenPartyProcessor created.");

        System.out.println("Is the newly created processor null?: " + (boolean)(vp == null));
    }

   

    public static void TestOrderCandidates() {
        /** Test that ordercandidates outputs the correct results for an input. */
        System.out.println("Testing the OpenPartyProcessor \"OrderCandidates\" function:");

        OpenPartyProcessor vp = new OpenPartyProcessor(new InputFile("../testing/OPLtest1.csv"));
        System.out.println("OpenPartyProcessor created for \"../testing/OPLtest1.csv\".");
        // vp.Run();
        System.out.println("D party order: " + vp.OrderCandidates("D").toString());
        System.out.println("R party order: " + vp.OrderCandidates("R").toString());
        System.out.println("I party order: " + vp.OrderCandidates("I").toString());

        vp = new OpenPartyProcessor(new InputFile("../testing/OPLctie3.csv"));
        System.out.println("OpenPartyProcessor created for \"../testing/OPLctie3.csv\".");
        // vp.Run();
        System.out.println("I party order #1: " + vp.OrderCandidates("I").toString());
        System.out.println("I party order #2: " + vp.OrderCandidates("I").toString());
        System.out.println("I party order #3: " + vp.OrderCandidates("I").toString());
    }

    public static void TestGetParty() {
        /** Test that no exceptions are thrown during the running of several files. */
        System.out.println("Testing the OpenPartyProcessor \"GetParty\" function:");

        OpenPartyProcessor vp = new OpenPartyProcessor(new InputFile("../testing/OPLtest1.csv"));
        System.out.println("OpenPartyProcessor created for \"../testing/OPLtest1.csv\".");

        System.out.println("Smith's Party: " + vp.GetParty("Smith"));
        System.out.println("Borg's Party: " + vp.GetParty("Borg"));
    }

    public static void TestPrintResults() {
        System.out.println("Testing the OpenPartyProcessor \"PrintResults\" function:");

        OpenPartyProcessor vp = new OpenPartyProcessor(new InputFile("../testing/OPLtest1.csv"));
        System.out.println("OpenPartyProcessor created for \"../testing/OPLtest1.csv\".");
        System.out.println("Results: \n" + vp.PrintResults() );
    }

    public static void TestRun() {
        /** Test that no exceptions are thrown during the running of several files. */
        System.out.println("Testing the OpenPartyProcessor \"run\" function:");

        OpenPartyProcessor vp = new OpenPartyProcessor(new InputFile("../testing/OPLtest1.csv"));
        System.out.println("OpenPartyProcessor created for \"../testing/OPLtest1.csv\".");
        vp.Run();
        System.out.println("Processor ran successfully");
       
    }

    
   
}