package jam.FlightDeparturesMap;
import java.awt.*;  
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.LabelUI;

import jam.Util.DateUtil;
import jam.Util.Flight;
import jam.Util.FlightData;

public class FlightDeparturesMap implements Runnable 
{
    public static JFrame jframe;
    public static DefaultListModel<String> SelectedAirportListModel;

    public static void InitWindow()
    {
        FlightDeparturesMap example = new FlightDeparturesMap();
        // schedule this for the event dispatch thread (edt)
        SwingUtilities.invokeLater(example);
    }

    public void run() 

    {
        Airport[] Airports = {
            new Airport(191,145,"JFK"),
            new Airport(442,86,"AMS"),
            new Airport(386,165,"RAK"),
            new Airport(414,138,"MAD"),
            new Airport(488,362,"JNB"),
            new Airport(103,199,"MEX"),
            new Airport(531,93,"SVO"),
            new Airport(502,180,"CAI"),
            new Airport(725,214,"BKK"),
            new Airport(393,93,"DUB"),
            new Airport(570,192,"DXB"),
            new Airport(414,88,"BCL"),
            new Airport(78,146,"LVS"),
            new Airport(647,176,"DEL"),
            new Airport(390,139,"LIS"),
            new Airport(459,136,"FCO"),
            new Airport(765,181,"HKG"),
            new Airport(482,144,"ATH"),
            new Airport(189,114,"YYZ"),
            new Airport(117,160,"DFW"),
            new Airport(817,144,"HND"),
            new Airport(433,240,"LOS"),
            new Airport(426,110,"CDG"),
            new Airport(861,374,"SYD"),
            new Airport(455,58,"ARN"),
            new Airport(261,350,"GRU")

        };


        Font font = new Font("Arial", Font.BOLD, 10);

        
        JFrame frame = new JFrame("My JFrame Example");
        JPanel panel = new JPanel(new GridBagLayout());

        ImageIcon Map = new ImageIcon("UI\\FlightMaps\\map.png");
        JLabel MapImage = new JLabel(Map);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx =0;
        c.gridy =0;
        c.gridwidth =3;
        //MapImage.addMouseListener(new HoverOver()); debug
        panel.add(MapImage,c);
        for(int i = 0; i < Airports.length; i++){
            JLabel label1 = new JLabel("SampleText");
            
            label1.setSize(label1.getPreferredSize());
            label1.setBackground(new Color(0,0,0,1));
            label1.setHorizontalAlignment(SwingConstants.LEFT);
            label1.setFont(font);
            
            MapImage.add(label1);
            label1.setText(Airports[i].name);
            label1.setLocation(Airports[i].x, Airports[i].y);
            label1.addMouseListener(new HoverOver());
        }

        c = new GridBagConstraints();
        
        Dimension LabelSize = new Dimension(200,20);
        
        JLabel DelaysLabel = new JLabel("Delays");
        DelaysLabel.setPreferredSize(LabelSize);
        c.weightx=0.5;
        c.insets = new Insets(10,0,0,0);
        c.gridx =0;
        c.gridy =1;
        panel.add(DelaysLabel,c);

        JLabel SelectedAirportLabel = new JLabel("Flights From Selected Airport");
        SelectedAirportLabel.setPreferredSize(LabelSize);
        c.weightx=0.5;
        c.gridx =1;
        c.gridy =1;
        panel.add(SelectedAirportLabel,c);

        JLabel BCLLabel = new JLabel("Flights from BCL");
        BCLLabel.setPreferredSize(LabelSize);
        c.weightx=0.5;
        c.gridx =2;
        c.gridy =1;
        panel.add(BCLLabel,c);



        //scrollable areas
        
        Dimension ScrollSize = new Dimension(300,400);

        DefaultListModel<String> DelaysListModel = new DefaultListModel<>();
        JList<String> DelaysList = new JList<String>(DelaysListModel);
        JScrollPane DelaysListPane = new JScrollPane(DelaysList);
        DelaysListPane.setPreferredSize(ScrollSize);
        c.weightx=0.5;
        c.gridx =0;
        c.gridy =2; 
        panel.add(DelaysListPane, c);
        

        DefaultListModel<String> SelectedListModel = new DefaultListModel<>();
        JList<String> SelectedList = new JList<String>(SelectedListModel);
        JScrollPane  SelectedListPane = new JScrollPane( SelectedList);
        SelectedListPane.setPreferredSize(ScrollSize);
        c.weightx=0.5;
        c.gridx =1;
        c.gridy =2; 
        panel.add(SelectedListPane, c);
        SelectedAirportListModel = SelectedListModel;


        DefaultListModel<String> BCLListModel = new DefaultListModel<>();
        JList<String> BCLList = new JList<String>(BCLListModel);
        JScrollPane BCLListPane = new JScrollPane(BCLList);
        BCLListPane.setPreferredSize(ScrollSize);
        c.weightx=0.5;
        c.gridx =2;
        c.gridy =2; 
        panel.add(BCLListPane, c);


        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(960, 1000);
        frame.setVisible(true);
        
        
        //Delayed 
        Iterator<Entry<String, Flight>> entrySet = FlightData.FlightsToday.entrySet().iterator();
        while (entrySet.hasNext())
        {
            Entry<String, Flight> entry = entrySet.next();
            if(entry.getValue().Delay.toMinutes() > 30){
                
                StringBuilder sb = new StringBuilder();
                sb.append(entry.getValue().FlightNumber);
                sb.append(",");
                sb.append(entry.getValue().DepartureTime);
                sb.append(",");
                sb.append(entry.getValue().DepartureAirport);
                sb.append(",");
                sb.append(entry.getValue().ArrivalAirport);
                sb.append(",");
                sb.append(entry.getValue().ArrivalTime);
                sb.append(",");
                sb.append(DateUtil.DurationToString(entry.getValue().Delay));
                sb.append(",");
                LocalTime cache = LocalTime.of(entry.getValue().ArrivalTime.getHour(),entry.getValue().ArrivalTime.getMinute(),0);
                cache = cache.plus(entry.getValue().Delay);
                sb.append(cache);
                
                DelaysListModel.addElement(sb.toString());
            }
        

        }

                                //5 seconds *60 for 5 mins
        Timer timer = new Timer(5000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("lol");
                Iterator<Entry<String, Flight>> entrySet = FlightData.FlightsToday.entrySet().iterator();
                SelectedAirportListModel.clear();
                BCLListModel.clear();
                
                LocalDateTime currenttime = LocalDateTime.now();
                while (entrySet.hasNext())
                {
                    Entry<String, Flight> entry = entrySet.next();
                    int compare = entry.getValue().DepartureAirport.compareTo("BCL");
                   
                    if(compare == 0){
                        
                        StringBuilder sb = new StringBuilder();
                        sb.append(entry.getValue().FlightNumber);
                        sb.append(",");
                        sb.append(entry.getValue().DepartureTime);
                        sb.append(",");
                        sb.append(entry.getValue().ArrivalCity);
                        sb.append(",");
                        sb.append(entry.getValue().ArrivalAirport);
                        

                        LocalDateTime datetime = LocalDateTime.of(entry.getValue().DateOfFlight.getYear(),entry.getValue().DateOfFlight.getMonth(), entry.getValue().DateOfFlight.getDayOfMonth(),   entry.getValue().DepartureTime.getHour(), entry.getValue().DepartureTime.getMinute(), 0);
                        long time = currenttime.until(datetime, ChronoUnit.MINUTES);


                        System.out.println(time);
                        System.out.println(datetime);
                        System.out.println(currenttime);
                        if(time< 120 ){
                            
                            BCLListModel.addElement("<html><FONT COLOR=RED>"+sb.toString()+"</html>");
                            System.out.println(datetime.until(currenttime, ChronoUnit.MINUTES));
                        }
                        else{

                            BCLListModel.addElement(sb.toString());
                        }
                    }
                

                }
            }
        
            
        });
        timer.getActionListeners()[0].actionPerformed(null);
        timer.start();

    }
}