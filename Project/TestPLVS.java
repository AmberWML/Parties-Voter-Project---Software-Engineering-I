/**
 * TEST CLASS: PLVS <br>
 * The purpose of this class is to test the functionality of PLVS.java. <br>
 * Running "java TestPLVS" will automatically run the tests outlined here.
 *
 * @author Henry Olson, CSCI 5801 Team 10, University of Minnestota
 */
public class TestPLVS {
    public static void main(String args[]) {
        System.out.println("-----------------------------\n" +
                "--------  PLVS Tests --------\n" +
                "-----------------------------\n");
        TestTakeInputFile();
        
    }

    public static void TestTakeInputFile() {
        GUI g = new GUI();
        System.out.println("\nTesting PLVS.TakeInputFile():\n");
        System.out.println("Does the PLVS system accept a valid file path?");
        System.out.println("Accepted ../testing/OPLtest1.csv?: " +
                PLVS.TakeInputFile("../testing/OPLtest1.csv", g));
        System.out.println("Accepted ../testing/OPLtest4.csv?: " +
                PLVS.TakeInputFile("../testing/OPLtest4.csv", g));
        System.out.println("Accepted ../testing/CPLtest1.csv?: " +
                PLVS.TakeInputFile("../testing/CPLtest1.csv", g));

        System.out.println("\nDoes the PLVS system reject a bad file path?");
        System.out.println("Accepted ../besting/OPLtest1.csv?: " +
                PLVS.TakeInputFile("../besting/OPLtest1.csv", g));
        System.out.println("Accepted bird?: " +
                PLVS.TakeInputFile("bird", g));
        System.out.println("Accepted badtest.csv?: " +
                PLVS.TakeInputFile("badtest.csv", g));


    }

    //Testing logs for the PLVS is in testing folder, more detailed version.
}