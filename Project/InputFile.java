import java.io.*;
import java.util.*;

/** 
  * This class stores a Java.File representation of the input file and its path, 
  * and contains functions to parse out the election type, candidates, parties, 
  * the number of seats, and the ballots portions of the input file.  
  * The input file is loaded in prior to the election being processed.  
  * Input files are assuemed to be CSV formatted, and to contain no errors.
  * @author Guogeng Li, CSCI 5801 Team 10, University of Minnestota
*/

public class InputFile {
	/** 'votetype' defines whether it use CPL or OPL vote algorithm*/
	private EType votetype;
	/** 'candidates' defines a list of candidates' name*/
	private Map<String, List<String>> candidates;
	/** 'parties' defines a list of party's name*/
	private List<String> parties;
	/** 'balloats' defines a list of balloats. In OPL, only a single 1 will be placed in 
	 * the position of the given candidate selected. In CPL, only a single 1 will be 
	 * placed in the position of the given party selected.
	*/
	private List<String> ballots;
	/** 'seats' define the number of seats*/
	private int seats;

	private String path;

	/**
     * read input file and grad infomation such as votetype,candidates,parties,ballots,seats
     * @param filename - The name of a file you want to read.
     */
	public InputFile(String filename){
		this.path = filename;

		Scanner input = new Scanner("");
		try {
			input = new Scanner(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String electionType = input.next();

		int num_ballots = 0;
		int num_candidates = 0;

		if(electionType.equals("OPL")){
			this.seats = Integer.parseInt(input.next());
			num_ballots = Integer.parseInt(input.next());
			num_candidates = Integer.parseInt(input.next());
			votetype = EType.OPL;

			parties = new ArrayList<String>();
			candidates = new HashMap<String, List<String>>();
			for(int i=0;i<num_candidates;i++){
				String str = input.next();
				
				String[] strArr = str.split("\\[|\\]|,");
				//int x = str.indexOf("[");
				//int y = str.indexOf(",");
				//int z = str.indexOf("]");

				String candidate = strArr[1];//str.substring(x+1,y);
				String party = strArr[2];//str.substring(y+1, z);

				if (candidates.containsKey(party)) {
					candidates.get(party).add(candidate);
				} else {
					candidates.put(party, new ArrayList<String>());
					candidates.get(party).add(candidate);
					parties.add(party);
				}
			}
			this.ballots = new ArrayList<String>();
			for(int i=0; i<num_ballots; i++){
				this.ballots.add(input.next());
			}

		} else if(electionType.equals("CPL")){
			int num_party = Integer.parseInt(input.next());
			String party_order = input.next();
			this.seats = Integer.parseInt(input.next());
			num_ballots = Integer.parseInt(input.next());
			num_candidates = Integer.parseInt(input.next());
			votetype = EType.CPL;
			parties = new ArrayList<String>();
			candidates = new HashMap<String, List<String>>();

			for(int i=0;i<num_candidates;i++){
				String str = input.next();
				//int x = str.indexOf("[");
				//int y = str.indexOf(",");
				//int z = str.indexOf("]");
				String[] strArr = str.split("\\[|\\]|,");

				String candidate = strArr[1];//str.substring(x+1,y);
				String party = strArr[2];//str.substring(y+1, y+2);

				if (candidates.containsKey(party)) {
					candidates.get(party).add(candidate);
				} else {
					//candidate += ',';
					//candidate += strArr[2];//str.substring(y+3, z);
					candidates.put(party, new ArrayList<String>());
					candidates.get(party).add(candidate);
					parties.add(party);
				}
			}
			this.ballots = new ArrayList<String>();
			for(int i=0; i<num_ballots; i++){
				this.ballots.add(input.next());
			}
		}


	}

	/**
	 * get the votetype
	 * @return The election's type (OPL or CPL)
	 */
	public EType GetElectiontype(){
		return votetype ;
	}
	/**
	 * get the list of candidates associated with a party
	 * @param party - the given party
	 * @return The list of candidates associated with the given party
	 */
	public List<String>  GetCandidates(String party){
		return candidates.get(party);
	}	
	/**
	 * get the parties list
	 * @return Returns a list of parties contained in the input file
	 */
	public List<String> GetParties(){
		return parties;
	}
	/**
	 * get the ballost list
	 * @return Returns a list of ballots contianed in the input file
	 */
	public List<String> GetBallots(){
		return ballots;
	}
	/**
	 * get the number of seat
	 * @return the number of seats specified in the input file
	 */
	public int GetSeats(){
		return seats;
	}

	/** get the files path
	 * @return the path of the actual input file
	 */
	public String GetPath(){
		return path;
	}

}
