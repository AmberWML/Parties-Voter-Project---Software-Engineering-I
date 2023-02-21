import java.io.*;

/** This enumerator defines indicators for the types of voting supported
 *  by the PLVS.  CPL represents closed-party list, and OPL represents open-party list.
 */
enum EType {
    CPL, OPL;
}

/**
 * This is the driver class for the PLVS application.  It completes interactions between the
 * View (GUI.java) and the Model (The vote processing classes) by transferring data between
 * the two. Functionalities are defined for each of the 4 buttons from GUI.
 *
 * @author Henry Olson, CSCI 5801 Team 10, University of Minnestota
 */

public class PLVS {

    /** Defines the input file currently used by the PLVS application. */
    static InputFile input;
    /** Defines the input file currently used by the PLVS application.
     *  When the eletion needs to be processed, this is instantiated
     *  as either a ClosedPartyProcessor or an OpenPartyProcessor*/
    static VoteProcessor processor;

    /** Starts a new instance of the PLVS application.  This is what is is executed by the
     * terminal command "java PLVS".
     * @param args - Any command line arguments.  This application does not use any arguments.
     */
    public static void main(String args[]) {
        /** Create and open a new GUI window. */
        GUI g = new GUI();
        g.open();
    }

    /**
     * This function takes a file path, verifies that it exists, and passes it back
     * to the GUI's process screen.  If the file exists, create a new InputFile to be
     * used by the PLVS software.
     * @param path - The file path.
     * @param g - The GUI the file path needs to be passed back to.
     * @return A boolean value indicating if TakeInputFile completed successfully.
     */
    public static boolean TakeInputFile(String path, GUI g) {
        /** Trim the whitespace off the end of the path */
        path = path.trim();

        /** Check if the file at the given path exists */
        if (new File(path).exists()) {
            g.goToProcess();
            g.lblFilePath.setText(path);
        } else {
            g.alert("File not found!");
            return false;
        }

        /** Attempt to create the PLVS's input file */
        try {
            input = new InputFile(path);
        } catch (Exception e) {
            /** If the InputFile can't be created, then print an error message. */
            g.alert("Error loading in file!");
            e.printStackTrace();
            return false;
        }

        /** Fit all elements onto the GUI screen */
        g.pack();
        return true;
    }

    /**
     * Creates a processor based on the election type specified in the input file, and runs
     * it.  Upon completion, pass the results of the election to the GUI's results screen.
     * @param g - THe GUI to display results and error messages.
     * @return A boolean value indicating if ProcessElection completed successfully.
     */
    public static boolean ProcessElection(GUI g) {

        /** Determine the input file's election type, and create the appropriate procesor. */
        if (input.GetElectiontype() == EType.CPL) {
            processor = new ClosedPartyProcessor(input);
        } else if (input.GetElectiontype() == EType.OPL){
            processor = new OpenPartyProcessor(input);
        } else {
            g.alert("Bad election type!");
            return false;
        }

        /** Run the vote processor */
        try {
            processor.Run();
        } catch (Exception e) {
            /** If there's an issue with the election processor, alert the user and halt this
             * function. */
            g.alert("Election processor encountered an error!");
            e.printStackTrace();
            return false;
        }

        /** Push the results to the GUI's results screen */
        g.goToResults();
        g.lblResults.setText("<html>" + processor.PrintResults().replaceAll("\n", "<br>") + "</html>");
        /** Fit the controls to the GUI's window. */
        g.pack();

        g.alert("Election Processed!");
        return true;
    }

    /**
     * Attempts to create a media file, and prints an error message to the GUI if not.
     * @param g - The GUI to print error messages to.
     * @return A boolean value indicating if CreateMediaFile completed successfully.
     */
    public static boolean CreateMediaFile(GUI g) {
        /** Attempt to create a media report. */
        try {
            processor.GenMediaReport();
            g.alert("Media Summary created!");
        } catch (Exception e) {
            /** If the media report can't be created, print an error message. */
            g.alert("Failed to create media summary!");
            return false;
        }
        return true;
    }
}
