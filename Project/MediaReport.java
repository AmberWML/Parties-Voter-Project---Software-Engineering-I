import java.io.*;

/**
 * This class, much like AuditFile, inherits from OuputClass and defines a function
 * to create a header for the file.  The media report is created after an election
 * is concluded, and the 'Generate Media Report' button is pressed.
 *
 * @author Henry Olson, CSCI 5801 Team 10, University of Minnestota
 */

public class MediaReport extends OutputFile {

    public MediaReport(String filename) {
        super(filename);
    }


    public void WriteHeader() {
            writeLn("~~~ Media Report ~~~~");
    }
}
