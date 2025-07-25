package tube;

import java.util.Set;
import java.util.TreeSet;

import utilitaires.TabFileReader;
import utilitaires.TermList;

public class Lines {
	private Network L ;
	TermList lignes = new TermList();
	
	public Lines(Network L){
		this.L = L;
		linesList() ;
	}

	private void linesList() {
		Set<String> line_names = new TreeSet<>();
		for (int i=0 ; i < L.getStepListSize() ; i++ ) {
			String line = L.stepAt(i).getligne();
				line_names.add(line);
		}
		for (String Line : line_names)
			lignes.add(Line);
	}
	
	public TermList AfficheStation(int numLigne) {
		String choice = lignes.termAt(numLigne);
		TermList StationLignes = new TermList();

		for (int i=0 ; i < L.getStepListSize() ; i++ ) {
			if(choice.equals(L.stepAt(i).getligne())) {
				String station1 = L.stepAt(i).getFirstStation();
				String station2 = L.stepAt(i).getSecondStation();
				if (!StationLignes.exists(station1)) 
					StationLignes.add(station1);
				if (!StationLignes.exists(station2))
					StationLignes.add(station2);
			}	
		}
		return StationLignes ;
	}
		
	
	public TermList getLignes() {
		return lignes;
	}

	public String getLigneName(int index) {
		return lignes.termAt(index);
	}
}
