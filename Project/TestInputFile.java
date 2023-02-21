import java.io.*;
import java.util.*;

/**
 * TEST CLASS: GUI <br>
 * The purpose of this class is to test the functionality of InputFile.java. <br>
 * Running "java TestInputFile" will automatically run the tests outlined here.
 *
 * @author Guogeng Li, CSCI 5801 Team 10, University of Minnestota
 */

public class TestInputFile{

    public static void main(String[] args) {
         System.out.println("-----------------------------\n" +
                "--------  InputFile Tests ---------\n" +
                "-----------------------------\n");
    
             /** Test each getter() methods */
         InputFile f = new InputFile("../testing/CPL.csv");
        
             System.out.println("Does f == null?: " + (boolean) (f == null));
             
             if(f.GetPath().contains("CPL")){
                System.out.println("Does f.GetElectiontype == CPL?: " + (boolean) (f.GetElectiontype().toString() == "CPL"));
                String str1 = f.GetParties().get(0);
                String str2 = f.GetParties().get(1);
                String str3 = f.GetParties().get(2);
                String str4 = f.GetParties().get(3);

                List<String> lst1 = new ArrayList<String>();
                lst1.add("Pike");
                lst1.add("Foster");
                lst1.add("Floyd");
                lst1.add("Jones");
                lst1.add("Mallory");

                List<String> lst2 = new ArrayList<String>();
                lst2.add("Deutsch");
                lst2.add("Wong");
                lst2.add("Walters");
                lst2.add("Keller");
                lst2.add("Borg");

                List<String> lst3 = new ArrayList<String>();
                lst3.add("Jones");
                lst3.add("Smith");
                lst3.add("Lewis");
                lst3.add("Smith");
                lst3.add("Li");
                
                List<String> lst4 = new ArrayList<String>();
                lst4.add("Perez");

                System.out.println("Does f.GetCandidates(f.GetParties().get(0)) == [Pike, Foster, Floyd, Jones, Mallory]?: " 
                        + (boolean) (f.GetCandidates(str1).equals(lst1)));
                System.out.println("Does f.GetCandidates(f.GetParties().get(1)) == [Deutsch, Wong, Walters, Keller, Borg]?: " 
                        + (boolean) (f.GetCandidates(str2).equals(lst2)));
                System.out.println("Does f.GetCandidates(f.GetParties().get(2)) == [Jones, Smith, Lewis, Smith, Li]?: " 
                        + (boolean) (f.GetCandidates(str3).equals(lst3)));
                System.out.println("Does f.GetCandidates(f.GetParties().get(3)) == [Perez]?: " 
                        + (boolean) (f.GetCandidates(str4).equals(lst4)));
                
                List<String> lst_party = new ArrayList<String>();
                lst_party.add("D");
                lst_party.add("R");
                lst_party.add("G");
                lst_party.add("I");

                System.out.println("Does f.GetParties() == [D, R, G, I]?: " + (boolean) (f.GetParties().equals(lst_party)));
                List<String> lst_ballots = new ArrayList<String>();
                lst_ballots.add("1,,,");
                lst_ballots.add("1,,,");
                lst_ballots.add(",1,,");
                lst_ballots.add(",1,,");
                lst_ballots.add(",,1,");
                lst_ballots.add(",,,1");
                lst_ballots.add(",,1,");
                lst_ballots.add("1,,,");
                lst_ballots.add(",1,,");
                lst_ballots.add(",,1,");
                lst_ballots.add(",,1,");
                lst_ballots.add(",1,,");
                lst_ballots.add(",1,,");
      
                System.out.println("Does f.GetBallots() == [1,,,, 1,,,, ,1,,, ,1,,, ,,1,, ,,,1, ,,1,, 1,,,, ,1,,, ,,1,, ,,1,, ,1,,, ,1,,]?: "
                 + (boolean) (f.GetBallots().equals(lst_ballots)));
                System.out.println("Does f.GetSeats() == false?: " + (boolean) (f.GetSeats() == 7));
                System.out.println("Does f.GetPath() == ../testing/CPL.csv?: " 
                        + (boolean) (f.GetPath() == "../testing/CPL.csv"));
                
                // System.out.println();
                // System.out.println(f.GetCandidates(str1));
                // System.out.println(f.GetCandidates(str2));
                // System.out.println(f.GetCandidates(str3));
                // System.out.println(f.GetCandidates(str4));
                // System.out.println(f.GetParties());
                // System.out.println(f.GetBallots());
                // System.out.println(f.GetSeats());
                // System.out.println(f.GetPath());  
            }
            else if(f.GetPath().contains("OPL")){
                System.out.println("Does f.GetElectiontype == OPL?: " + (boolean) (f.GetElectiontype().toString() == "OPL"));
                String str1 = f.GetParties().get(0);
                String str2 = f.GetParties().get(1);
                String str3 = f.GetParties().get(2);

                List<String> lst1 = new ArrayList<String>();
                lst1.add("Pike");
                lst1.add("Foster");
                
                List<String> lst2 = new ArrayList<String>();
                lst2.add("Deutsch");
                lst2.add("Borg");
                
                List<String> lst3 = new ArrayList<String>();
                lst3.add("Jones");
                lst3.add("Smith");
                
                System.out.println("Does f.GetCandidates(f.GetParties().get(0)) == [Pike, Foster]?: " 
                        + (boolean) (f.GetCandidates(str1).equals(lst1)));
                System.out.println("Does f.GetCandidates(f.GetParties().get(1)) == [Deutsch, Borg]?: " 
                        + (boolean) (f.GetCandidates(str2).equals(lst2)));
                System.out.println("Does f.GetCandidates(f.GetParties().get(2)) == [Jones, Smith]?: " 
                        + (boolean) (f.GetCandidates(str3).equals(lst3)));
                List<String> lst_party = new ArrayList<String>();
                lst_party.add("D");
                lst_party.add("R");
                lst_party.add("I");
                System.out.println("Does f.GetParties() == [D, R, I]?: " + (boolean) (f.GetParties().equals(lst_party)));
                List<String> lst_ballots = new ArrayList<String>();
                lst_ballots.add("1,,,,,");
                lst_ballots.add(",,,,1,");
                lst_ballots.add("1,,,,,");
                lst_ballots.add(",1,,,,");
                lst_ballots.add(",,1,,,");
                lst_ballots.add(",,,,,1");
                lst_ballots.add(",,,1,,");
                lst_ballots.add(",,,,1,");
                lst_ballots.add(",,1,,,");
      
                System.out.println("Does f.GetBallots() == [1,,,,,, ,,,,1,, 1,,,,,, ,1,,,,, ,,1,,,, ,,,,,1, ,,,1,,, ,,,,1,, ,,1,,,]?: "
                 + (boolean) (f.GetBallots().equals(lst_ballots)));
                System.out.println("Does f.GetSeats() == 3?: " + (boolean) (f.GetSeats() == 3));
                System.out.println("Does f.GetPath() == ../testing/OPL.csv?: " 
                        + (boolean) (f.GetPath() == "../testing/OPL.csv"));

                // System.out.println();
                // System.out.println(f.GetCandidates(str1));
                // System.out.println(f.GetCandidates(str2));
                // System.out.println(f.GetCandidates(str3));
                // System.out.println(f.GetParties());
                // System.out.println(f.GetBallots());
                // System.out.println(f.GetSeats());
                // System.out.println(f.GetPath()); 
            }
               
    }
}