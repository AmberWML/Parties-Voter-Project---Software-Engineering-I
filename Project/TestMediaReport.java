import java.io.*;

/**
 * TEST CLASS: GUI <br>
 * The purpose of this class is to test the functionality of MediaReport.java. <br>
 * Running "java TestMediaReport" will automatically run the tests outlined here.
 *
 * @author Guogeng Li, CSCI 5801 Team 10, University of Minnestota
 */

public class TestMediaReport{

	public static void main(String[] args) {
		System.out.println("-----------------------------\n" +
                "--------  MediaReport Tests ---------\n" +
                "-----------------------------\n");
		MediaReport f = new MediaReport("../testing/CPL.csv");
		System.out.println("Does f == null ? " + (boolean) (f == null));
        System.out.println("Does f.path == The path we have ?: " + (boolean) (f.path == "../testing/CPL.csv"));
        System.out.println("Does f.fileRep == null?: " + (boolean) (f.fileRep == null));
       
        testWriteHeader(f);
	}
	public static void testWriteHeader(MediaReport r){
		r.WriteHeader();
        System.out.println("\nCalled r.WriteHeader().\nThe writeln window should write the \"~~~~ Media Report File ~~~~\"to the outputfile.");
	}
}