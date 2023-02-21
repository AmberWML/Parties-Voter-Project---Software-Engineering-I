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

    /** Write a Audit file header to the File representation of the audit file */
    public void WriteHeader() {
        writeLn("~~~~ Audit File ~~~~");
    }

    /**
     * This version of write vote is used for a Closed-party vote.
     * It writes a vote to the output file
     * @param party - The party the vote is for.
     */
    public void WriteVote(String party) {
        writeLn("Vote for " + party);
    }

    /**
     * This version of write vote is used for a Open-party vote.
     * It writes a vote to the output file
     * @param party - The party the vote is for.
     * @param cand  - The candidate the vote is for.
     */
    public void WriteVote(String party, String cand) {
        writeLn("Vote for " + cand + " (" + party + ")");
    }
}
