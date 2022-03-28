package log;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.opencsv.CSVReader;


public class LogParser {	
	/**
	 * Returns a list of SuspectEntries corresponding to the CSV data supplied by the given Reader.
	 * 
	 * The data contains one or more lines of the format:
	 * 
	 * Marc,413-545-3061,1234567890
	 * 
	 * representing a name, phone number, and passport number.
	 * 
	 * @param r an open Reader object
	 * @return a list of SuspectEntries
	 * @throws IOException
	 */
	public static List<SuspectEntry> parseLog(Reader r) throws IOException {
		CSVReader read = new CSVReader(r);
		  List<SuspectEntry> suspectList = new ArrayList<SuspectEntry>();
		  List<String[]> entry = read.readAll();
		  String newName = "";
		  String phone = "";
		  String passport = "";
		  
		  if(entry.isEmpty() || entry.equals(null)){
		   read.close();
		   return new ArrayList<SuspectEntry>();
		  }
		  else{
		  
		   for(String[] k : entry){
		    int i =0;
		    newName = k[i];
		    phone = k[i+1];
		    passport = k[i+2];
		    
		    if(newName != null || phone != null || passport != null){
		     SuspectEntry newSuspect = new SuspectEntry(newName, phone, passport);
		     suspectList.add(newSuspect);
		    }
		   }
		   read.close();
		  }
		  return suspectList;
	}
	/**
	 * Returns a sorted list of SuspectEntries whose passport numbers are common to all 
	 * of the supplied entryLists.
	 * 
	 * The list is sorted lexicographically by passport number, breaking ties by name 
	 * and then by phone number.
	 * 
	 * @param entryLists a list of lists of SuspectEntries
	 * @return a sorted list of SuspectEntries whose passport numbers are common to all 
	 * of the supplied entryLists
	 */
	
	
	public static List<SuspectEntry> findCommonEntries(List<List<SuspectEntry>> entryLists) {
		  List<String> suspect = new ArrayList<>();
		  Set<String> matchedPassports = new HashSet<>();
		  Set<SuspectEntry> done = new HashSet<>();
		  List<SuspectEntry> finalList = new ArrayList<>();
		  
		  if(entryLists.size() == 0){
		   return finalList;
		  }
		  if(entryLists.size() == 1){
		   for(List<SuspectEntry> b : entryLists){
		    done.addAll(b);
		   }
		   finalList.addAll(done);
		   finalList.sort(null);
		   return finalList;
		  }
		  
		  for(List<SuspectEntry> k : entryLists){
		   for(SuspectEntry j : k){
		    suspect.add(j.getPassportNumber()); 
		   }
		  }
		  for(List<SuspectEntry> m : entryLists){
		   for(SuspectEntry n : m){
		    suspect.remove(n.getPassportNumber());
		    if(suspect.contains(n.getPassportNumber())){
		     matchedPassports.add(n.getPassportNumber());  
		    }
		   }
		  }
		  for(List<SuspectEntry> x : entryLists){
		   for(SuspectEntry z : x){
		    if(matchedPassports.contains(z.getPassportNumber())){
		     done.add(z);
		    }
		   }
		  }
		  finalList.addAll(done);
		  finalList.sort(null);
		  return finalList;
		
//		for(int i=0; i<entryLists.size()-1; i++) {
//			List<SuspectEntry> store = entryLists.get(i);
//			for(int j=0; j<entryLists.get(i).size()-1; j++) {
//				map.put(i, entryLists.get(j));
//			}
//		}
//		
//		String sub1;
		
		
//		for(int i=0; i<entryLists.size()-1; i++) {
//			for(int j=0; j<entryLists.get(i).size()-1; j++) {
//				anotherList.add(i, (SuspectEntry) entryLists.get(i));
//				for(int k=0; k<entryLists.get(j).size()-1; k++) {
//					if(entryLists.get(j).toString().compareTo(anotherList)) {
//						
//					}
//				}
//			}
//		}

	}
}
