This repository represents two java-based application developped for learning purposes. The first one called **Flight Management Applciation** and the second one called **Smart Route Finder**.

#  Flight Management Application

This Application allows users to explore flight data between cities, search for direct or connecting flights, and view airport departures and arrivals through a *graphical user interfaces*.

##  Features

- Load and parse flight data from a `.txt` file.
- List all available cities (departures and arrivals).
- Show *all flights (departures and arrivals)* from a selected airport.
- Search for *direct* flights between two cities.
- Search for *connecting* flights with valid stopovers (≥ 1 hour layover).
- Simple and interactive *Swing GUI*.

## Data File (`data/departs.txt`)

Each line in the data file follows this format:
- FlightNumber DepartureCity ArrivalCity DepartureTime ArrivalTime

Example:
- AF7383 NANTES PARIS CDG 06:45 08:00
- A54103 NANTES LYON 07:30 09:25

## Classes Overview

  ### TabFileReader.java (src/utilitaires) : 
    - Reads tab-separated data into a 2D array.
    - Provides access to flight data row-by-row and column-by-column.

  ### InfoVols.java (src/workspace) : 
    Provides:
      - City list extraction.
      - Validity checks.
      - Display of direct/connecting flights.
      - Flight time parsing logic.

  ### InfoVolsGUI.java (src/workspace) : 
    Swing GUI with two main features:
      - List cities and show departures/arrivals.
      - Search for direct and connecting flights.

## GUI applications

The application interface consists of two main tabs, illustrated in the photos below:

  ### Airport Overview Tab (photo 1)

  - This tab displays a list of all airports found in the data file.
  - When a user selects an airport from the list, the application shows all departing and arriving flights related to that airport.
  - The flights are presented in a structured format, making it easy to explore traffic from a specific location.

  ### Flight Search Tab (photo 2)

  - This tab allows the user to search for flights between a selected departure and destination  airport.
  - The results include both direct flights and connecting flights (with layovers of at least one hour).
  - The interface provides clear details about flight numbers, departure/arrival times, and stopover information if applicable.

## How to Compile & Run

  ### 1. Compile
javac -d bin -sourcepath src src/utilitaires/TabFileReader.java src/workspace/InfoVols.java src/workspace/InfoVolsGUI.java

  ### 2. Run the Application
  java -cp bin workspace.InfoVolsGUI
  


# Smart Route Finder
This project provides an interactive map of the London Underground. Users can visualize lines and stations, click on stations to compute an itinerary between two points, and view the shortest route based on direction. The GUI is supported with a real map image for better usability.

## Main Features:

- Display an interactive London Underground map.
- Show all stations for a selected metro line.
- Allow users to click two stations to get an itinerary.
- Compute shortest path by minimizing angle deviation at each step (heuristic approach).
- Graphical feedback with animated highlights on the map.

## Data Files  :

### stations.txt : 
Contains coordinates for each station on the map:
- Station Name X Position Y Position

  Example:
- Baker Street	425	126
- Waterloo	592	534

### steps.txt :
Defines metro line connections between stations:
- Station A Line Name Station B

Example :
- Paddington	Bakerloo_line	Marylebone
- Victoria 	Circle_line	Westminster

### londonTube.jpg
A graphical map image of the London Tube network that serves as the visual background of the GUI.

## Key Classes

### TubeView.java : 	
    - Main JFrame GUI class
    - Handles mouse clicks:
	- Click on line icons → displays stations for that line
	- Click on stations → sets departure/arrival and computes path
	- Visualizes selected stations with nested colored ovals

### Control.java
	- Manages high-level actions (reset map, draw line stations, draw itinerary)

### Path.java
	- Implements greedy pathfinding based on minimizing the angle toward the destination
	- Avoids previously visited stations

### Network.java
	- Loads and stores steps (edges) and stations from data files
	- Provides access to network structure

### Lines.java
	- Parses unique lines and retrieves associated stations

### Step.java
	- Models a single edge (station1, station2, line)

### Station.java
	- Stores 2D position and station name
	- Computes vector angle for navigation logic

### TabFileReader.java
	- Reads tab-separated values from text files

### TermList.java
	- Manages dynamic lists of station names or lines

## How to Run

1. Compile all .java files in src/
2. Ensure data/ is at the root of the project and contains:
	- stations.txt
	- steps.txt
	- londonTube.jpg
3. Run the GUI:
	- java -cp bin tube.gui.TubeView

## The GUI features:
- Highlighted selected station
- Show all stations for selected line (photo 1)
- Show the shortest path between two selected stations (photo 2)
   
## Technologies Used

- Java SE (Swing GUI)
- File I/O (text parsing)
- Greedy algorithm (angle minimization)
- Object-Oriented Design

## Author

Project developed by [Hamzeh KASSAN & Mor-Talla Niang]
Java project for learning purposes
