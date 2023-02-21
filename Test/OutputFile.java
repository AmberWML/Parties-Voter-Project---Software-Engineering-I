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
 */

public class OutputFile {
    protected String path;
    protected File fileRep;

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

    public void writeLn(String content) {
        try {
            FileWriter writer = new FileWriter(fileRep, true);
            BufferedWriter bw = new BufferedWriter(writer);

            /** Write in file*/
            bw.write(content);
            bw.write(System.getProperty( "line.separator" ));

           /** Close connection*/
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}