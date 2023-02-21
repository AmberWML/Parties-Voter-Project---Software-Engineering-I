import java.util.*;
import java.io.File;

/**
 * This is an implementation of the VoteProcessor class designed to process open-party list
 * elections.  It defines the run, GenMediaReport, and PrintResults methods for VoteProcessor.
 *
 * Open party list elections rank the candidates through the election.
 *
 * @author Henry Olson, CSCI 5801 Team 10, University of Minnestota
 */

public class OpenPartyProcessor extends VoteProcessor {
    /**
     * This map defines vote counts for each of the candidates.  The first string index
     * represents the party, and the second represents the candidate's name ( ie.
     * < party, <candidate, int> > )
     */
    private Map<String, Map<String, Integer>> candVotes;

    /** This map defines candidate order for each party. */
    private Map<String, List<String>> candidates;

    /** This list defines the order of all candidates as they would appear in the CSV
     *  representation of the ballot. */
    private List<String> allcandidates;

    /**
     * Creates a new instance of an OpenPartyProcessor from an InputFile i.
     * @param i : InputFile
     */
    public OpenPartyProcessor( InputFile i ) {
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
        candVotes = new HashMap<String, Map<String, Integer>>();
        allcandidates = new ArrayList<String>();
        for (int x=0; x<parties.size(); x++) {
            candidates.put(parties.get(x), new ArrayList<String>(i.GetCandidates(parties.get(x))));
            candVotes.put(parties.get(x), new HashMap<String, Integer>());
            allcandidates.addAll(i.GetCandidates(parties.get(x)));
            votes.put(parties.get(x), 0);
            /** Initialize the vote count per candidate */
            for (int y=0; y<candidates.get(parties.get(x)).size(); y++) {
                candVotes.get(parties.get(x)).put(candidates.get(parties.get(x)).get(y), 0);
            }
        }
    }

    /**
     * Processes an open party election.  It is assumed that the input file both exists and
     * contains no errors.
     */
    public void Run() {
        /** A new audit file is created. */
        CreateAudit();
        /** Write the input file's path */
        audit.writeLn("Input File: " + input.GetPath());
        /** Write the election type. */
        if (input.GetElectiontype() == EType.OPL) {
            audit.writeLn("Open Party List Election");
	    }else if (input.GetElectiontype() == EType.CPL) {
            audit.writeLn("Closed Party List Election");
        }
        /** Write the number of the seats available to the report. */
        audit.writeLn("Seats Availible: " + input.GetSeats());
        /** Write the number of cast ballots to the report. */
        audit.writeLn("Ballots Cast: " + input.GetBallots().size() + "\n");


        /** The list of ballots is retrieved from the input file. */
        List<String> ballots = input.GetBallots();

        /**
         * Each ballot is checked for where the ‘1’ is in relation to the commas, and
         * the appropriate candidate and party’s vote count is incremented.  This vote
         * is recorded in the audit file.
         */
        for (int i=0; i<ballots.size(); i++) {
           String ballot = ballots.get(i);
           String candidate = allcandidates.get(ballot.indexOf('1'));
           candVotes.get(GetParty(candidate)).put(candidate,
                             candVotes.get(GetParty(candidate)).get(candidate) + 1);
           votes.put(GetParty(candidate), votes.get(GetParty(candidate)) + 1);
           audit.WriteVote(GetParty(candidate), candidate);
        }

        // /** Sort each party’s list of candidates by vote count. */
        for (int i=0; i<candidates.size(); i++) {
            candidates.replace(parties.get(i), OrderCandidates(parties.get(i)));
        }

        /** Count the election as processed. */
        ranFlag = true;

        /** Distribute the seats amongst parties. */
        seats = DistributeSeats(input.GetSeats(), input.GetBallots().size(), parties, votes);
        /** Write the results to the audit file. */
        audit.writeLn("\n" + PrintResults());
    }

    /**
     * Orders the candidates of a party by vote.  It is assumed that the processor has been
     * run and that candVotes contains the total votes for the candidates.
     * @param p - The desired party.  It is assumes that p is one of the parties contained in the
     *          input file.
     * @return The list of candidates for the party, ordered by vote.
     */
    public List<String> OrderCandidates( String p ) {
        // System.out.println("ccccccccccc" + candidates);
        List<String> ordCand = new ArrayList<String>();
        List<String> temp = candidates.get(p);
        // System.out.println("+++++++++++" + temp);
        // System.out.println("-----------" + candidates.get(p));
        while (candidates.get(p).size() > 0) {
            /**
             * Set the initial maximum value and candidate to arbirarily low values
             * (No candidate can get negative votes)
             */
            int max = -1;
            String maxCand = "n/a";

            /**
             * Find the highest voted candidate.  If there is a tie, use random number
             * generation to determine the winner.
             */
            for (int i = 0; i < temp.size(); i++) {
                String c = temp.get(i);
                if (temp.indexOf(c) == max) {
                    audit.writeLn("Tie detected between " + maxCand + " and " + c + "!");
                    maxCand = TieBreak(Arrays.asList(new String[]{maxCand, c}));
                    audit.writeLn("Tie broken, Winner: " + maxCand);
                } else if (candVotes.get(p).get(c) > max) {
                    max = candVotes.get(p).get(c);
                    maxCand = c;
                }
            }
            temp.remove(maxCand);
            ordCand.add(maxCand);
        }
        for(String s : ordCand){
            candidates.get(p).add(s);
        }
        return ordCand;
    }
    /**
     * Determine's which political party a candidate is associated with.  It is assumed that the
     * lists of candidates and the list of parties has been defined from the input file.
     * @param candidate - The name of a candidate.
     * @return The political party that candidate is a member of.
     */
    public String GetParty(String candidate) {
        for (int i=0; i<candidates.size(); i++) {
            if (candidates.get(parties.get(i)).contains(candidate)) { return parties.get(i); }
        }
        return "Party not Found";
    }

    /**
     * Creates a string representation of the results of an election.  It is assumed that the input
     * file exists and contains no errors, and that the processor has been run.
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
        for (int i=0; i<votes.size(); i++) {
            /**
             * Add each specific party's results with the format
             * " - <party>: X votes, Y seats"
             * where X and Y are integers.
             */
            output += " - " + parties.get(i) + ":" + votes.get(parties.get(i)) + " votes, " +
                    seats.get(parties.get(i)) + " seats\n";
        }

        output += "\nCandidate Results:\n";
        for (int i=0; i<candVotes.size(); i++) {
            for (int j=0; j<candVotes.get(parties.get(i)).size(); j++) {
                /**
                 * Add each specific candidates's results with the format
                 * " - <candidates> (<party>): X votes, appointed to seat"
                 * where X is an integer.
                 */
                String candidate = candidates.get(parties.get(i)).get(j);

                output += " - " + candidate + "(" + parties.get(i) + "): " +
                        candVotes.get(parties.get(i)).get(candidate) + " votes";
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
