package tube;

/* This class specifies, for each pair of stations, the metro line that connects them. */
public class Step {
	private String station1, station2 , ligne; 
	
	public Step(String station1, String ligne, String station2)
	{
		this.station1 = station1;
		this.station2 = station2;
		this.ligne = ligne;
	}
	
	public String getFirstStation(){
		return this.station1;
	}

	public String getSecondStation(){
		return this.station2;
	}
	
	public String getligne(){
		return this.ligne ; 
	}
	
	
	public String getNext(String station)
	{
		if(station.equals(station1))
			return station2;
		else if(station.equals(station2))
			return station1; 
		else {
			return null;
		}
	}
}
