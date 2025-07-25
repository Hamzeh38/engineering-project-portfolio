package tube;

import java.awt.List;
import java.util.Set;
import java.util.TreeSet;

import utilitaires.TabFileReader;
import utilitaires.TermList;

public class Network {	
	private Step [] steps; 
	private TermList names = new TermList();

	public Network(){
		readfile("steps.txt", "data");
		stationList();
	}
	
	private void readfile(String fileName, String packageName) {
		TabFileReader.readTextFile(fileName,'\t',  packageName);
		int steps_size = TabFileReader.nrow();
		steps = new Step[steps_size];		
			for(int i = 0 ;  i < steps_size ; i++ ) {
				String A = TabFileReader.wordAt(i,0);
				String L = TabFileReader.wordAt(i,1);
				String B = TabFileReader.wordAt(i,2);
				steps[i] = new Step(A,L,B);
			}
	}
	
	private void stationList() {
		Set<String> station_names = new TreeSet<>();
		for (int i=0 ; i < steps.length ; i++ ) {
			String station1 = steps[i].getFirstStation();
			String station2 = steps[i].getSecondStation();
				station_names.add(station1);
				station_names.add(station2);
		}
		for (String station : station_names)
			names.add(station);
	}

	public Step [] getStep() { return steps; }
	public TermList getStationNamesList() { return names; }
	public int getStepListSize() { return steps.length; }
	public int numberOfStations() { return names.size(); } 
	public Step stepAt(int index) { return steps[index]; }
}
