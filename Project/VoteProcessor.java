import java.util.*;
import java.util.Random;
import java.io.*;
import java.io.InterruptedIOException;
import javax.swing.*;
import java.awt.event.*; 
import javax.swing.filechooser.*; 

/**
 * This is the parent class for the OpenPartyProcessor and ClosedPartyProcessor classes
 * <p>
 * Open party list elections rank the candidates through the election.
 *
 * @author Ashton Koversky, CSCI 5801 Team 10, University of Minnesota
 */

public abstract class VoteProcessor {
    /**
     * 'input' defines the object used to interact with the user-given file
     */
    protected InputFile input;
    /**
     * 'parties' contains the given names for all of the parties in the election
     */
    protected List<String> parties;
    /**
     * 'votes' holds all of the votes given to each candidate and/or party
     */
    protected Map<String, Integer> votes;
    /**
     * 'seats' holds all of the seats distributed to each of the parties
     */
    protected Map<String, Integer> seats;
    /**
     * 'ranFlag' is a flag used to determine if the election has been processed once already
     */
    protected boolean ranFlag = false;
    /**
     * 'audit' defines the object used to interact with the produced audit file
     */
    protected AuditFile audit;

    /**
     * 'PrintResults()' displays the completed election results to the GUI
     *
     * @return Results
     */
    abstract public String PrintResults();

    /**
     * 'Run()' processes the election after a correct input file has been provided
     */
    abstract public void Run();

    /**
     * Determines winner from a list of potential ties
     *
     * @param tie - The list of tied entities
     * @return winner - The winning entity
     */
     public String TieBreak(List<String> tie) {
        /** Current potential winner is first in the list */
        String winner = tie.get(0);
        Random rand = new Random();
        int[] a = new int[tie.size()];
        // System.out.println(Arrays.toString(a));
        /** Progesses through the list */
        /** change the int from 0 instead from 1 */
        for (int runmany = 0; runmany<100000; runmany++)
        {
        
                /** Generates either a 1 or 0 */
                int randNum = rand.nextInt(tie.size());
                /** If number is a 1, then current list entry is the new potential winner. Otherwise old potential winner stays */
                
                    winner = tie.get(randNum);
                    a[randNum]+=1;
                    /** if I add the break, then the three one works fine, if I delete the break, then the ab one works bad.
                    Only return a if i delete the break; */          
        }      
        int maxa = -1;
        int index = 0;
        System.out.println(Arrays.toString(a));
        for (int asize = 0; asize< tie.size(); asize++){
            if (a[asize]>maxa){
                maxa = a[asize];
                index = asize;
            }
        }
        String winnerresult = tie.get(index);
        return winnerresult;
        
        }

    /**
     * Creates a new Audit File
     *
     */
    public void CreateAudit() {
        /** Original Audit file name */
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
  
        // invoke the showsSaveDialog function to show the save dialog 
        int r = j.showSaveDialog(null); 
  
        // if the user selects a file 
        if (r == JFileChooser.APPROVE_OPTION){ 
            String filename = j.getSelectedFile().getAbsolutePath();
            System.out.println(filename); 

            File auditFile = new File(filename);
            File newAuditFile;
            boolean success;
        
            audit = new AuditFile(filename);
            audit.WriteHeader();
        }
    }

    /**
     * Assigns seats based on the performance of the party in the election.  It is assumed that
     * the election has been processed, and that the votes map contains the votes for each party.
     * @author Henry Olson, CSCI 5801 Team 10, University of Minnestota
     * @param ballotsCast - the number of ballots cast
     * @param numAvSeats  - the number of availible seats in the election
     * @param pList       - The list of all parties in the election
     * @param pVotes      - The map of votes associated with each party, arranges ( Party, Votes )
     * @return A map containing the seats distributed to each party, arranged ( Party, Seats awarded )
     */
    public Map<String, Integer> DistributeSeats(int numAvSeats, int ballotsCast,
                                                List<String> pList, Map<String, Integer> pVotes) {
        /** define the total number of seats avalible at the start of the function */
        int totalSeat = numAvSeats;
        /** define the Map to be returned, which will contain the count of seats per party */
        Map<String, Integer> distSeats = new HashMap<String, Integer>();
        for (int i = 0; (i < pList.size()) && (numAvSeats > 0); i++) {
            /**
             * Calculate the number of seats the party has earned as a proportion of the total vote.
             * This is calculates as (party's vote / ballots cast) * total availible seats
             */
            int numSeats = (int) Math.round(((double) pVotes.get(pList.get(i)) / (double) ballotsCast) * (double) totalSeat);

            /** if the party doesn't have enough candidates for all of its seats,
             * it only earns enough seats for its candidates.
             */
            if (numSeats > input.GetCandidates(pList.get(i)).size()) {
                numSeats = input.GetCandidates(pList.get(i)).size();
            }

            /** deduct the number of seats the party has claimed from the total pool */
            numAvSeats -= numSeats;

            distSeats.put(pList.get(i), numSeats);
        }
        /** Assign any run off seats to the other parties in order of popularity.*/
        List<String> invalidParties = new ArrayList<String>();
        while (numAvSeats > 0) {
            /** Find the most voted party */
            int max = -1;
            String runoff = "N/a";
            for (int i = 0; i < pList.size(); i++) {
                /** Only consider parties that haven't been considered and have candidates to spare. */
                if (!(invalidParties.contains(pList.get(i))) &&
                        distSeats.get(pList.get(i)) < input.GetCandidates(pList.get(i)).size()) {
                    /** Check for ties */
                    if (votes.get(pList.get(i)) == max) {
                        audit.writeLn("Tie detected between " + pList.get(i) + " and " + runoff + "!");
                        runoff = TieBreak(Arrays.asList(new String[]{parties.get(i), runoff}));
                        audit.writeLn("Tie broken, Winner: " + runoff);
                    } else if (votes.get(pList.get(i)) > max && !(invalidParties.contains(pList.get(i)))) {
                        max = votes.get(pList.get(i));
                        runoff = pList.get(i);
                    }
                }
            }

            if (runoff.equals("N/a")) {
                break;
            }
            /** Give a seat to the most popular party */
            distSeats.put(runoff, distSeats.get(runoff) + 1);
            /** Don't consider the most popular party in future calculations until all other
             *  parties have had a chance.
             */
            numAvSeats--;

            invalidParties.add(runoff);
        }

        /** If all parties have been considered, consider all parties again. */
        if (invalidParties.size() == pList.size()) {
            invalidParties = new ArrayList<String>();
        }

        return distSeats;
    }

    /**
     * 'GenMediaReport()' produces the media report file and writes all of the necessary information into it
     */
    public void GenMediaReport() {
    	/** Makes sure that the election has been run before generating the media report. */
    	if(hasRan())
    	{
	        MediaReport medReport;
            // create an object of JFileChooser class 
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
  
            // invoke the showsSaveDialog function to show the save dialog 
            int r = j.showSaveDialog(null); 
  
            // if the user selects a file 
            if (r == JFileChooser.APPROVE_OPTION){ 
                // set the label to the path of the selected file 
                String filename = j.getSelectedFile().getAbsolutePath();

                System.out.println(filename); 

                medReport = new MediaReport(filename);
    
                /** Write a header to the media report */
                medReport.WriteHeader();
                /** Write the input file's path */
                medReport.writeLn("Input File: " + input.GetPath());
                /** Write the election type. */
                if (input.GetElectiontype() == EType.OPL) {
                    medReport.writeLn("Open Party List Election");
                } else if (input.GetElectiontype() == EType.CPL) {
                    medReport.writeLn("Closed Party List Election");
                }
                /** Write the number of the seats available to the report. */
                medReport.writeLn("Seats Availible: " + input.GetSeats());
                /** Write the number of cast ballots to the report. */
                medReport.writeLn("Ballots Cast: " + input.GetBallots().size());
        
                /** Write the election results to the report */
                medReport.writeLn("\nResults:");
                medReport.writeLn(PrintResults());
            } 
    	}
    }

    /**
     * Returns boolean stating if 'Run()' has been successfully called and completed
     * true if it has, false if it hasn't
     *
     * @return ranFlag
     */
    public boolean hasRan() {
        return ranFlag;
    }
}
