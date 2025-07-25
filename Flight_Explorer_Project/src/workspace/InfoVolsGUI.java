package workspace;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Set;

public class InfoVolsGUI extends JFrame {

    private JTextField departFieldDirect;
    private JTextArea resultAreaDirect;
    private JButton directButton;
    private JList<String> villeList;
    private DefaultListModel<String> villeListModel;

    private JTextField departFieldConnex;
    private JTextField arriveeFieldConnex;
    private JTextArea resultAreaConnex;
    private JButton connexButton;

    public InfoVolsGUI() {
        setTitle("Flight Management");
        setSize(700, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panelDirect = new JPanel(new BorderLayout(10, 10));
        JPanel inputPanelDirect = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanelDirect.add(new JLabel("Serching a city:"));
        departFieldDirect = new JTextField(15);
        inputPanelDirect.add(departFieldDirect);

        directButton = new JButton("Search");
        inputPanelDirect.add(directButton);

        resultAreaDirect = new JTextArea();
        resultAreaDirect.setEditable(false);
        JScrollPane scrollDirect = new JScrollPane(resultAreaDirect);

        panelDirect.add(inputPanelDirect, BorderLayout.NORTH);
        panelDirect.add(scrollDirect, BorderLayout.CENTER);

        
        villeListModel = new DefaultListModel<>();
        villeList = new JList<>(villeListModel);
        JScrollPane listScrollPane = new JScrollPane(villeList);
        listScrollPane.setPreferredSize(new Dimension(180, 0));
        panelDirect.add(listScrollPane, BorderLayout.WEST);

        Set<String> cities = InfoVols.All_cities_list();
        for (String city : cities) {
            villeListModel.addElement(city.toUpperCase());
        }

        villeList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selected = villeList.getSelectedValue();
                if (selected != null) {
                    departFieldDirect.setText(selected);
                    resultAreaDirect.setText("");
                    captureSystemOut(resultAreaDirect, () -> InfoVols.displayAirportFlights(selected));
                }
            }
        });

        tabbedPane.addTab("Airports List", panelDirect);

        JPanel panelConnex = new JPanel(new BorderLayout(10, 10));
        JPanel inputPanelConnex = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanelConnex.add(new JLabel("Departure city:"));
        departFieldConnex = new JTextField(12);
        inputPanelConnex.add(departFieldConnex);

        inputPanelConnex.add(new JLabel("Destination city:"));
        arriveeFieldConnex = new JTextField(12);
        inputPanelConnex.add(arriveeFieldConnex);

        connexButton = new JButton("Search Flights");
        inputPanelConnex.add(connexButton);

        resultAreaConnex = new JTextArea();
        resultAreaConnex.setEditable(false);
        JScrollPane scrollConnex = new JScrollPane(resultAreaConnex);

        panelConnex.add(inputPanelConnex, BorderLayout.NORTH);
        panelConnex.add(scrollConnex, BorderLayout.CENTER);

        tabbedPane.addTab("Connecting Flights", panelConnex);

        add(tabbedPane);

        directButton.addActionListener(e -> {
            String depart = departFieldDirect.getText().trim().toUpperCase();
            resultAreaDirect.setText("");

            captureSystemOut(resultAreaDirect, () -> InfoVols.displayAirportFlights(depart));
        });

        connexButton.addActionListener(e -> {
            String depart = departFieldConnex.getText().trim().toUpperCase();
            String arrivee = arriveeFieldConnex.getText().trim().toUpperCase();
            resultAreaConnex.setText("");

            captureSystemOut(resultAreaConnex, () -> InfoVols.displayConnectingFlights(depart, arrivee));
        });
    }

    private void captureSystemOut(JTextArea area, Runnable task) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(baos);
        System.setOut(newOut);

        task.run();

        System.out.flush();
        System.setOut(originalOut);
        area.append(baos.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InfoVolsGUI().setVisible(true));
    }
}

