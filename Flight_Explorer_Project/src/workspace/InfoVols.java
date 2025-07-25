package workspace;

import java.util.*;

import utilitaires.TabFileReader;

public class InfoVols {

	/*Private function that convert the time into an integer; the time should be given with the a specific format : "hh:mm"*/
	private static int toMinutes(String heure){
		String [] tokens=heure.split(":");
		int h=Integer.parseInt(tokens[0]);
		int m=Integer.parseInt(tokens[1]);
		return h*60+m;
	}
	
	/*Extract all city names and put them into a list*/
	protected static Set<String> All_cities_list(){
		String filename="departs.txt";
		TabFileReader.readTextFile(filename , '\t' , "data");
		Set<String> city_names_lists = new TreeSet<>();

		for (int i = 0 ; i < TabFileReader.nrow() ; i++){
			city_names_lists.add(TabFileReader.wordAt(i , 1));
			city_names_lists.add(TabFileReader.wordAt(i , 2));
		}

		return city_names_lists;
	}

	/*Check if the departure and the destination name exist*/
	private static boolean nameCheck(String city_name){
		Set<String> nomExistant = new TreeSet<>(All_cities_list());

		if(nomExistant.contains(city_name))
			return true;
		else
			return false;
	}
	

	/*Display all flights from a given airport*/
	public static void displayAirportFlights(String airport){
		String filename="departs.txt";
		TabFileReader.readTextFile(filename , '\t' , "data");
		boolean departure_founded = false;

		if(!nameCheck(airport)){
			System.out.println ("ERROR: City doesn't exist");
			return;
		}

		/*Display the departure flights*/
		System.out.print("Departure Flights :\n");
		for (int i = 0 ; i<TabFileReader.nrow() ; i++) { 
			if(airport.equals(TabFileReader.wordAt(i,1))) {
				for (int j=0; j<TabFileReader.ncol();j++)
					System.out.print(TabFileReader.wordAt(i,j)+"\t\t");
				System.out.print("\n");
				departure_founded = true;
			}
		}
		if (!departure_founded)
			System.out.print("No departure flights from this city\n");

		/*Display the arrival flights*/
		System.out.print("\n\nArrival Flights :\n"); 
		for (int i = 0 ; i<TabFileReader.nrow() ; i++) {
			if (airport.equals(TabFileReader.wordAt(i,2))){
				for (int j=0; j<TabFileReader.ncol();j++)
					System.out.print(TabFileReader.wordAt(i,j)+"\t\t");
				System.out.print("\n");
			}
		}
	}
	
	/**	
	* Displays all available connecting flights between the given departure and destination airports,
 	* The stopover duration should be over than 1 hour for corresponding flight
 	*/
    public static void displayConnectingFlights(String depart, String destination){
    	String filename = "departs.txt"; 
    	String corres;
    	TabFileReader.readTextFile(filename, '\t', "data");
	
		if(!nameCheck(depart)){
			System.out.println ("ERROR: Departure city doesn't exist");
			return;
		}

		if(!nameCheck(destination)){
			System.out.println ("ERROR: Arrival city doesn't exist");
			return;
		}


    	int nbLignes = TabFileReader.nrow();
    	int nbColonnes = TabFileReader.ncol();

		for (int i = 0; i < nbLignes; i++) {
        	if (depart.equalsIgnoreCase(TabFileReader.wordAt(i, 1))) {
			/**	Departure founded
			 * Display all directs flights at the begining
			 */
				if(destination.equalsIgnoreCase(TabFileReader.wordAt(i, 2))){
					System.out.print("Direct flight :\n");
					for (int j = 0 ; j < nbColonnes ; j++)
						System.out.print(TabFileReader.wordAt(i, j) + "\t");
					System.out.print("\n--------------------------\n");
				}

				/*Displaying now flights with correspondence*/
            	corres = TabFileReader.wordAt(i, 2);
            	int arrivalTime_firstFlight = InfoVols.toMinutes(TabFileReader.wordAt(i, 4));
            	for (int j = 0; j < nbLignes; j++) {
                	if (corres.equalsIgnoreCase(TabFileReader.wordAt(j, 1)) &&
                    	destination.equalsIgnoreCase(TabFileReader.wordAt(j, 2))) {
                    	int departureTime_secondFlight = InfoVols.toMinutes(TabFileReader.wordAt(j, 3));
                    	if (departureTime_secondFlight - arrivalTime_firstFlight >= 60) {
							System.out.print("Flight with correspondance :\n");
                        	for (int k = 0; k < nbColonnes; k++) {
                            	System.out.print(TabFileReader.wordAt(i, k) + "\t");
                        	}
                        	System.out.println();

                        	for (int k = 0; k < nbColonnes; k++) {
                            	System.out.print(TabFileReader.wordAt(j, k) + "\t");
                        	}
                        	System.out.println("\n--------------------------");
                    	}
                	}
            	}
        	}
    	}
   	}
}
