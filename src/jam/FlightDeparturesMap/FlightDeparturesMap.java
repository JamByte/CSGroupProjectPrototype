package jam.FlightDeparturesMap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.LabelUI;

public class FlightDeparturesMap implements Runnable
{
    public static JFrame jframe;
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
        MapImage.addMouseListener(new HoverOver());
        panel.add(MapImage,c);
        for(int i = 0; i < Airports.length; i++){
            JLabel label1 = new JLabel("SampleText");
            
            label1.setSize(label1.getPreferredSize());
            label1.setBackground(new Color(0,0,0,1));
            label1.setHorizontalAlignment(SwingConstants.LEFT);
            label1.setFont(new Font("Arial", Font.BOLD, 10));
            
            MapImage.add(label1);
            label1.setText(Airports[i].name);
            label1.setLocation(Airports[i].x, Airports[i].y);
            label1.addMouseListener(new HoverOver());
        }

        JLabel label2 = new JLabel();
        c = new GridBagConstraints();
        
        label2.setBackground(Color.white);
        label2.setBorder(BorderFactory.createLineBorder(Color.black,2));
        label2.setPreferredSize(new Dimension(250,250));
        c.weightx=0.5;
        c.insets = new Insets(10,0,0,0);
        c.gridx =0;
        c.gridy =1;
        panel.add(label2,c);
        JLabel label3 = new JLabel();
        
        label3.setBackground(Color.white);
        label3.setBorder(BorderFactory.createLineBorder(Color.black,2));
        label3.setPreferredSize(new Dimension(250,250));
        c.weightx=0.5;
        c.gridx =1;
        c.gridy =1;
        panel.add(label3,c);

        
        JLabel label4 = new JLabel();
        label4.setBackground(Color.white);
        label4.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black,2),"LOL"));
        label4.setPreferredSize(new Dimension(250,250));
        c.weightx=0.5;
        c.gridx =2;
        c.gridy =1;
        panel.add(label4,c);

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(960, 1000);
        frame.setVisible(true);
        
        

    }
}