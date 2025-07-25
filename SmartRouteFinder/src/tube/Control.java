package tube;

import javax.swing.JOptionPane;

import tube.gui.TubeView;
import utilitaires.TermList;

public class Control {
	private TubeView tv;
	private String begin, end;
	
	public Control(Network tub, TubeView tuv){
		tv=tuv;
		begin=end=null;
	}
	public void clearItinerary(){
      	System.out.println("USER ACTION: clear the map");
		begin=end=null;
	}
	 /* -----------------------------------------------------------
	  * show the stations belonging to a line.
	  * -----------------------------------------------------------
	  */
	  public void showLine(int numLigne){
     	System.out.println("USER ACTION: line selection, index= "+numLigne);
     	TermList list=null;// remplir par les noms des stations d une ligne donne
     	Network N = new Network();
     	Lines L = new Lines(N);
     	list = L.AfficheStation(numLigne);
     	tv.show(list);
	  }
	/* -----------------------------------------------------------
	 * show an itinerary between two stations.
	 * -----------------------------------------------------------
	 */	  
	  public void showItinerary(int x, int y){
     	String station=tv.findClosestStation(x,y);
     	System.out.println("USER ACTION: station selection = "+station);
     	TermList sel=new TermList();
		Network L = new Network();
		Path P = new Path(L);

     	if (begin==null) {
     		begin=station;
     		sel.add(begin);
     		tv.show(sel);
     	}
     	else if (end==null) {
     		end=station;
    		TermList selection=P.searchingItinerary(begin, end);

     		if (selection==null) {
     			JOptionPane.showMessageDialog(tv, "No path has been found.");
     		}
     		tv.show(selection);
			sel=null;
     		begin=null;
     		end=null;
     	}
	  }
}
