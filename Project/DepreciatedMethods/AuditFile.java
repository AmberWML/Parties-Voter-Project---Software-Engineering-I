import java.util.*;
import java.io.*;

/** 
This class implements the general functionality needed to write data to the file, 
such as is needed for the audit file and media report.  It has two inherited children, 
AuditFile and MediaReport.  AuditFile adds functionality for recording the election�s audit 
file through a function that writes a header, a closed-party list vote, and an open-party list vote.  
MediaReport implements a function that writes a header to the audit file.
* @author Amber Wong, CSCI 5801 Team 10, University of Minnestota
*/

public class AuditFile extends OutputFile{
	private String votetype;
	private List<String> candidates;
	private List<String> parties;
	private List<String> ballots;
	private List<String> seats;

	public File AuditFile(String filename)
	{
		InputFile votefile = new InputFile(filename);
		this.votetype = votefile.GetElectiontype();
		this.candidates = votefile.GetCandidates();
		this.parties = votefile.GetParties();
		this.ballots = votefile.GetBallots();
		this.seats = vitefile.GetSeats();
	}
	public void writeHeader(){
		 // adding header to file
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
	public void writeVote(String party){
		 // add data to csv if type is OPL
		File file = new File(path);
		FileWriter fileRep = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fileRep);
		String[] oplVotelist = parties;
		//find the selected party's information
		for(int i=0; i < length(parties); i++){
			if(party==parties[i]){
				bw.writeNext(parties[i]);
			}
		}

		}
	public void writeVote(String party, String cand){
		 // add data to csv if type is CPL
		File file = new File(path);
		FileWriter fileRep = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fileRep);
		String[] cplVotelist = parties;
		//find the selected party's information
		for(int i=0; i < length(parties); i++){
			if((party==parties[i])&&(cand==candidates)){
				bw.writeNext(parties[i]);
			}
		}
		bw.close();
	}
}