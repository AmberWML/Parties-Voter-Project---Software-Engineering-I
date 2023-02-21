import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * TEST CLASS: AuditFile<br>
 * The purpose of this class is to test the functionality of AuditFile.java. <br>
 * Running "java TestAudit" will automatically run the tests outlined here.
 *
 * @author Amber Wong, CSCI 5801 Team 10, University of Minnestota
 */
public class TestAudit {
    public static void main(String args[]) {
        System.out.println("-----------------------------\n" +
                "--------  AuditFile Tests ---------\n" +
                "-----------------------------\n");

        /** Test the constructor */
        AuditFile af= new AuditFile("/Users/wangruohan/desktop/CSCI5801/Test/Auditfile.txt");
        System.out.println("Called the Auditfile constructor to create a new Auditfile Auditfile." +
                "af should equal null. File's path should equals what we have and return True");
        System.out.println("Does af == null ? " + (boolean) (af == null));
        System.out.println("Does af.path == The path we have ?: " + (boolean) (af.path == "/Users/wangruohan/desktop/CSCI5801/Test/Auditfile.txt"));
      

        testwriteHeader(af);
        testwritevote(af);
       
    
    }

    public static void testwriteHeader(AuditFile af) {
        af.WriteHeader();
        System.out.println("\nCalled af.WriteHeader().\nThe writeln window should write the \"~~~~ Audit File ~~~~\"to the outputfile.");
      }

    public static void testwritevote(AuditFile af) {
        af.WriteVote("D");
        System.out.println("\nCalled af.WriteVote(string party).\nThe writeln window should write the \"Vote for D\" to the outputfile.");
        af.WriteVote("R");
        System.out.println("\nCalled af.WriteVote(string party).\nThe writeln window should write the \"Vote for R\" to the outputfile.");
        af.WriteVote("G");
        System.out.println("\nCalled af.WriteVote(string party).\nThe writeln window should write the \"Vote for G\" to the outputfile.");
        af.WriteVote("I");
        System.out.println("\nCalled af.WriteVote(string party).\nThe writeln window should write the \"Vote for I\" to the outputfile.");
        
        af.WriteVote("D","Foster");
        System.out.println("\nCalled af.WriteVote(string party).\nThe writeln window should write the \"Vote for D\" to the outputfile.");
        af.WriteVote("R","Deutsch");
        System.out.println("\nCalled af.WriteVote(string party).\nThe writeln window should write the \"Vote for R\" to the outputfile.");
        af.WriteVote("G","Smith");
        System.out.println("\nCalled af.WriteVote(string party).\nThe writeln window should write the \"Vote for G\" to the outputfile.");
        af.WriteVote("I","Perez");
        System.out.println("\nCalled af.WriteVote(string party).\nThe writeln window should write the \"Vote for I\" to the outputfile.");

      }
}
     

