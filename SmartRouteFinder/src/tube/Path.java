package tube;

import tube.gui.TubeView;
import utilitaires.TermList;

public class Path {

TermList visited; 
Network L ;

public Path(Network L){
	this.visited = new TermList() ;
	this.L = L;
}

public TermList searchingItinerary(String depart , String destination) {
	String current = depart;
	String next;
	boolean fini = false;

	while(fini == false)	{
		visited.add(current);
		if(current.equals(destination))
			return visited;
		else
		{
			next = nextStation(destination , current);
			if (next == null)
				fini = true;
			else 
				current = next;
		}
	}
	return null; //return visited?
}

public String nextStation(String destination , String current) {
	String next = null;
	String nextS;
	TubeView T = new TubeView(L);
	double alpha ; 
	double angleMin = 6.284;

	for(int i = 0 ; i < L.getStepListSize() ; i++) {
		nextS = L.stepAt(i).getNext(current);
		if(nextS != null && !visited.exists(nextS)) {
			alpha = T.angle(current, nextS, destination);
			if (alpha < angleMin) {
				angleMin = alpha;
				next = nextS;
			}
		}
	}
	return next;
}

}
