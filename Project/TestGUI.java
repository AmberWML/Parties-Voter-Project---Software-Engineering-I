import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * TEST CLASS: GUI <br>
 * The purpose of this class is to test the functionality of GUI.java. <br>
 * Running "java TestGUI" will automatically run the tests outlined here.
 *
 * @author Henry Olson, CSCI 5801 Team 10, University of Minnestota
 */
public class TestGUI {
    public static void main(String args[]) {
        System.out.println("-----------------------------\n" +
                "--------  GUI Tests ---------\n" +
                "-----------------------------\n");

        /** Test the constructor */
        GUI g = new GUI();
        System.out.println("Called the GUI constructor to create a new GUI g." +
                "  No fields should equal null.");
        System.out.println("Does g == null?: " + (boolean) (g == null));
        System.out.println("Does g.btnCancel == null?: " + (boolean) (g.btnCancel == null));
        System.out.println("Does g.btnInput == null?: " + (boolean) (g.btnInput == null));
        System.out.println("Does g.btnProcess == null?: " + (boolean) (g.btnProcess == null));
        System.out.println("Does g.btnMediaReport == null?: " +
                (boolean) (g.btnMediaReport == null));
        System.out.println("Does g.lblInstruct == null?: " + (boolean) (g.lblInstruct == null));
        System.out.println("Does g.lblFilePath == null?: " + (boolean) (g.lblFilePath == null));
        System.out.println("Does g.lblResults == null?: " + (boolean) (g.lblResults == null));
        System.out.println("Does g.txtIn == null?: " + (boolean) (g.txtIn == null));


        testOpen(g);
        testGoToInput(g);
        testGoToProcess(g);
        testGoToResults(g);
        testPack(g);
        testGetPnl(g);
        testClose(g);
    }

    /**
     * Test GUI.open()
     */
    public static void testOpen(GUI g) {
        g.open();
        System.out.println("\nCalled g.open().\nThis should display the input screen (only btnCancel," +
                " btnInput, lblIntruct, and txtIn should be visible) and make the window visible.");

        /** Check the visibility of all controls.  Only btnCancel, btnInput, lblIntruct,
         * and txtIn should be visible. */
        System.out.println("Is g.btnCancel visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.btnCancel));
        System.out.println("Is g.btnInput visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.btnInput));
        System.out.println("Is g.btnProcess visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.btnProcess));
        System.out.println("Is g.btnMediaReport visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.btnMediaReport));
        System.out.println("Is g.lblInstruct visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.lblInstruct));
        System.out.println("Is g.lblFilePath visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.lblFilePath));
        System.out.println("Is g.lblResults visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.lblResults));
        System.out.println("Is g.txtIn visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.txtIn));
    }

    /**
     * Test GUI.goToInput()
     */
    public static void testGoToInput(GUI g) {
        g.goToInput();
        System.out.println("\nCalled g.goToInput().\nThis should display the input screen " +
                "(only btnCancel, btnInput, lblIntruct, and txtIn should be visible).");

        /** Check the visibility of all controls.  Only btnCancel, btnInput, lblIntruct,
         * and txtIn should be visible. */
        System.out.println("Is g.btnCancel visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.btnCancel));
        System.out.println("Is g.btnInput visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.btnInput));
        System.out.println("Is g.btnProcess visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.btnProcess));
        System.out.println("Is g.btnMediaReport visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.btnMediaReport));
        System.out.println("Is g.lblInstruct visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.lblInstruct));
        System.out.println("Is g.lblFilePath visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.lblFilePath));
        System.out.println("Is g.lblResults visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.lblResults));
        System.out.println("Is g.txtIn visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.txtIn));
    }

    /**
     * Test GUI.goToProcess()
     */
    public static void testGoToProcess(GUI g) {
        g.goToProcess();
        System.out.println("\nCalled g.goToProcess().\nThis should display the input screen " +
                "(only btnProcess and lblFilePath should be visible).");

        /** Check the visibility of all controls.  Only btnProcess and lblFilePath
         * should be visible */
        System.out.println("Is g.btnCancel visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.btnCancel));
        System.out.println("Is g.btnInput visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.btnInput));
        System.out.println("Is g.btnProcess visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.btnProcess));
        System.out.println("Is g.btnMediaReport visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.btnMediaReport));
        System.out.println("Is g.lblInstruct visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.lblInstruct));
        System.out.println("Is g.lblFilePath visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.lblFilePath));
        System.out.println("Is g.lblResults visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.lblResults));
        System.out.println("Is g.txtIn visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.txtIn));
    }

    /**
     * Test GUI.goToResults()
     */
    public static void testGoToResults(GUI g) {
        g.goToResults();
        System.out.println("\nCalled g.goToResults().\nThis should display the input screen " +
                "(only btnMediaReport and lblResults) should be visible).");

        /** Check the visibility of all controls.  Only btnMediaReport and lblResults
         * should be visible */
        System.out.println("Is g.btnCancel visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.btnCancel));
        System.out.println("Is g.btnInput visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.btnInput));
        System.out.println("Is g.btnProcess visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.btnProcess));
        System.out.println("Is g.btnMediaReport visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.btnMediaReport));
        System.out.println("Is g.lblInstruct visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.lblInstruct));
        System.out.println("Is g.lblFilePath visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.lblFilePath));
        System.out.println("Is g.lblResults visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.lblResults));
        System.out.println("Is g.txtIn visible?: " +
                Arrays.asList(g.getPnl().getComponents()).contains(g.txtIn));
    }

    /**
     * Test GUI.pack()
     */
    public static void testPack(GUI g) {
        g.pack();
        System.out.println("\nCalled g.pack().\nAll elements on screen should fit evenly.");
    }

    /**
     * Test GUI.alert
     */
    public static void testAlert(GUI g) {
        /** Test a arbitrary string */
        g.alert("Hi");
        System.out.println("\nCalled g.alert(\"Hi\").\nA dialog window containing the text Hi " +
                "should appear.");

        /** Test a empty string */
        g.alert("");
        System.out.println("\nCalled g.alert(\"\").\nA dialog window containing no text should" +
                " appear.");

        /** Test a string with a non-string expression in it */
        g.alert("2 + 2 = " + (2 + 2));
        System.out.println("\nCalled g.alert(\"2 + 2 = \" + (2+2)).\nA dialog window containing " +
                "the text 2 + 2 = 4 should appear.");
    }

    /**
     * Test GUI.getPnl()
     */
    public static void testGetPnl(GUI g) {
        /** create a replica of g.pnl after goToResults is called */
        JPanel testPnl = new JPanel();
        testPnl.setLayout(new FlowLayout());
        testPnl.add(g.lblResults);
        testPnl.add(g.btnMediaReport);

        /** set g.pnl to its values after goToResults is called */
        g.goToResults();

        /** compare the JPanels, output the result */
        System.out.println("\nDoes g.getPnl() equal A recreation of its current state?: " +
                g.getPnl().equals(testPnl));
    }

    /**
     * Test GUI.close()
     */
    public static void testClose(GUI g) {
        g.close();
        System.out.println("\nCalled g.close().\nThe visible GUI window should close.");
    }
}