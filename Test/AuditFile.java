import java.io.*;

/**
 This class implements the general functionality needed to write data to the file,
 such as is needed for the audit file and media report.  It has two inherited children,
 AuditFile and MediaReport.  AuditFile adds functionality for recording the election�s audit
 file through a function that writes a header, a closed-party list vote, and an open-party list vote.
 MediaReport implements a function that writes a header to the audit file.
 * @author Henry Olson, CSCI 5801 Team 10, University of Minnestota
 */

public class AuditFile extends OutputFile {

    public AuditFile(String filename) {
        super(filename);
    }

    public void WriteHeader() {
        /** adding header to file*/
        writeLn("~~~~ Audit File ~~~~\n");
    }

    public void WriteVote(String party) {
        /**find the selected party's information
        *if the party is democracy then return the party and its' situation
        */
        writeLn("Vote for " + party);
    }

    public void WriteVote(String party, String cand) {
        /**find the selected party's information
        *if the party is democracy then return the party and its' situation
        */
        writeLn("Vote for " + cand + " (" + party + ")");
    }
}