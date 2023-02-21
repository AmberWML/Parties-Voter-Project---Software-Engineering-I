public class MediaReport extends OutputFile{

/**
 * This class, much like AuditFile, inherits from OuputClass and defines a function
 * to create a header for the file.  The media report is created after an election
 * is concluded, and the 'Generate Media Report' button is pressed.
 * @author Guogeng Li, CSCI 5801 Team 10, University of Minnestota
 */

    /** adding header to file */
    public void writeHeader(String type, String path){
        File file = new File(path);
        FileWriter fileRep = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fileRep);
        if(votetype=="CPL"){
            String[] header = {"parties","seats","ballots","candidates"};
            bw.writeNext(header);
        }
        else if(votetype=="OPL"){
            String[] header = {"seats","ballots","candidates"};
            bw.writeNext(header);
        }

    }
}