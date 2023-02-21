import java.io.*;
import java.util.*;

/**
 * This class implements the general functionality needed to write data to the file,
 * such as is needed for the audit file and media report.  It has two inherited children,
 * AuditFile and MediaReport.  AuditFile adds functionality for recording the election’s audit
 * file through a function that writes a header, a closed-party list vote, and an open-party list vote.
 * MediaReport implements a function that writes a header to the audit file.
 *
 * @author Amber Wong, CSCI 5801 Team 10, University of Minnestota
 * @author Henry Olson, CSCI 5801 Team 10, University of Minnestota
 */

public class OutputFile {
    protected String path;
    protected File fileRep;

    /**
     * This is the constructor for an Output File
     * @param p - The path that the file should be created at.
     */
    public OutputFile(String p) {
        this.path = p;
        /**
        *first create file object for file placed at location 
        * specified by filepath 
        */
        this.fileRep = new File(path);
        /** If file doesn't exists, then create it*/
        try {
            if (!fileRep.exists()) {
                fileRep.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This writes a given string to a output file.  This string is appended to the end of the
     * file.
     * @param content - The string to be written to file.
     */
    public void writeLn(String content) {
        try {
            /** Create a new file writer for the given file path, with the append flag
             * set to true. */
            FileWriter writer = new FileWriter(fileRep, true);
            BufferedWriter bw = new BufferedWriter(writer);

            /** Write in file*/
            bw.write(content);
            bw.write(System.getProperty( "line.separator" ));

           /** Close connection*/
            bw.close();
        } catch (Exception e) {
            /** Print the thrown exception to the error window. */
            e.printStackTrace();
        }
    }
}