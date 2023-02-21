import java.util.*;
import java.io.File;

/**
 * This is an implementation of the VoteProcessor class for processing closed party elections.  
 * It defines the run, GenMediaReport, and PrintResults methods for VoteProcessor.
 *
 * @author Ashton Koversky, CSCI 5801 Team 10, University of Minnestota
 */

public class ClosedPartyProcessor extends VoteProcessor {
    /**
     * This map defines vote counts for each party. The String index
     * represents the party name, and the Integer represents the vote
     * count.
     */
    //private Map<String, Integer> partyVotes;

    /** This map defines a list of candidates associated with each party.*/
    private Map<String, List<String>> candidates;

    /**
     * Creates a new instance of an ClsoedPartyProcessor using InputFile i.
     * @param i : InputFile
     */
    public ClosedPartyProcessor(InputFile i) {
        /**
         * Set the instance variables for VoteProcessor
         */
        input = i;
        parties = i.GetParties();
        votes = new HashMap<String, Integer>();
        seats = new HashMap<String, Integer>();
        ranFlag = false;

        /**
         * Set up the candidate order per party (candidates) and the map containing
         * the vote count per candidate.
         */
        candidates = new HashMap< String, List<String> >();
       // partyVotes = new HashMap<String, Integer>();
        /** Initialize the lists of candidates and parties, and initialize the party vote counts to 0 */
        for (int x=0; x<parties.size(); x++) {
            candidates.put(parties.get(x), i.GetCandidates(parties.get(x)));
            votes.put(parties.get(x), 0);
        }
    }
    
    /**
     * Processes closed party election. Assumes the input file exists and
     * contains no errors.
     */
    public void Run() {
        /** Create new audit file. */
        CreateAudit();
        /** Write the input file's path */
        audit.writeLn("Input File: " + input.GetPath());
        /** Write the election type. */
        if (input.GetElectiontype() == EType.OPL) {
            audit.writeLn("Open Party List Election");
	} else if (input.GetElectiontype() == EType.CPL) {
            audit.writeLn("Closed Party List Election");
        }
        /** Write the number of the seats available to the report. */
        audit.writeLn("Seats Available: " + input.GetSeats());
        /** Write the number of cast ballots to the report. */
        audit.writeLn("Ballots Cast: " + input.GetBallots().size() + "\n");

        /** The list of ballots is retrieved from the input file. */
        List<String> ballots = input.GetBallots();

        /**
         * Each ballot is checked for where the '1' is in relation to the commas, the
         * corresponding party's vote count is incremented, recording the process
         * into the audit file.
         */
        for (int i=0; i<ballots.size(); i++) {
           String ballot = ballots.get(i);
           String party = parties.get(ballot.indexOf('1'));
           //partyVotes.put(party, (partyVotes.get(party) + 1));
           votes.put(party, votes.get(party) + 1);
           audit.WriteVote(party);
        }

        /** Sort the list of parties by their vote count. */
        parties = OrderParties(parties);

        /** Count the election as processed. */
        ranFlag = true;

        /** Distribute the seats amongst parties. */
        seats = DistributeSeats(input.GetSeats(), input.GetBallots().size(), parties, votes);
        /** Write the results to the audit file. */
        audit.writeLn("\n" + PrintResults());
    }
    
    /**
     * Orders the parties by vote counts. It is assumed that the processor has been
     * run and that votes contains the final vote counts for each party.
     * @param p - The desired party.  It is assumes that p is one of the parties contained in the
     *          input file.
     * @return ordParty - The ordered list of strings of the parties, from lowest count to highest
     */
    private List<String> OrderParties(List<String> p) {
    	List<String> ordParties = new ArrayList<String>();

        while (p.size() > 0 ) {
            /**
             * Set the initial maximum value and party to arbitrarily low values
             * (No party can have negative votes)
             */
            int max = -1;
            String maxParty = "n/a";

            /**
             * Find the party with the most votes.  If there is a tie, use random number
             * generation to determine the winner.
             */
            for(int i = 0; i < p.size(); i++) {
                String c = parties.get(i);
                if (votes.get(c) == max) {
                    audit.writeLn("Tie detected between " + maxParty + " and " + c + "!");
                    maxParty = TieBreak(Arrays.asList(new String[]{maxParty, c}));
                    audit.writeLn("Tie broken, Winner: " + maxParty);
                } else if (votes.get(c) > max) {
                    max = votes.get(c);
                    maxParty = c;
                }
            }
            ordParties.add(maxParty);
            p.remove(maxParty);
        }

        return ordParties;
    }
    
    /**
     * Creates string representation of the results of election. Assumes the input
     * file exists and contains no errors, and that the Run() method has been called and completed.
     * @return String
     */
    public String PrintResults() {
        /**
         * If the processor has yet to run, do not execute this procedure.
         */
        if (ranFlag == false) { return "No results, the processor has yet to run"; }

        /**
         * Define the output string.
         */
        String output = "";

        output += "Party Results:\n";
        for (int i = 0; i < parties.size(); i ++) {
            /**
             * Add each specific party's results with the format
             * " - <party>: X votes, Y seats"
             * where X and Y are integers.
             */
		
           // output += parties.get(i) + ":" +
            output += " - " + parties.get(i) + ":" + votes.get(parties.get(i)) + " votes, " +
                    seats.get(parties.get(i)) + " seats\n";
        }

        output += "\nCandidate Results:\n";
        for (int i = 0; i < parties.size(); i++) {
           // List<String> newCandidates = new ArrayList<String>(partyVotes.get(i).keySet());
            /**
             * Add each specific candidates's results with the format
             * " - <candidates> (<party>): Ranked X, appointed to seat"
             * where X is an integer.
             */
        	for(int j = 0; j < candidates.get(parties.get(i)).size(); j ++ )
        	{
	            output += " - " + candidates.get(parties.get(i)).get(j) + "(" + parties.get(i) + "):" +
	                   " Ranked " + (j+1);
	            /**
	             * If the candidate is not appointed, the "appointed" portion is not displayed.
	             */
	            if(seats.get(parties.get(i)) != null)
	            {
		            if ( j < seats.get(parties.get(i))) {
		                output += ", appointed to seat";
		            } 
	            }

	            output += "\n";
	            
	            
        	}  
        }
        return output;
    }
}
