package jam.FlightDeparturesMap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.xml.stream.events.Comment;

import jam.Util.Flight;
import jam.Util.FlightData;

public class HoverOver implements MouseListener{

    public HoverOver() {
    }

    public void mouseEntered(MouseEvent e) {
        JLabel source = (JLabel)e.getSource();
        int count = 0;
        source.setForeground(Color.MAGENTA);
        String airport = source.getText();
        System.out.println(airport);
        Iterator<Entry<String, Flight>> entrySet = FlightData.FlightsToday.entrySet().iterator();
        FlightDeparturesMap.SelectedAirportListModel.clear();
        while (entrySet.hasNext())
        {
            Entry<String, Flight> entry = entrySet.next();
            int compare = entry.getValue().DepartureAirport.compareTo(airport);
           
            if(compare == 0){
                
                StringBuilder sb = new StringBuilder();
                sb.append(entry.getValue().FlightNumber);
                sb.append(",");
                sb.append(entry.getValue().DepartureTime);
                sb.append(",");
                sb.append(entry.getValue().ArrivalCity);
                sb.append(",");
                sb.append(entry.getValue().ArrivalAirport);
                
                FlightDeparturesMap.SelectedAirportListModel.addElement(sb.toString());
            }
        

        }
        System.out.println(count);




    }

    public void mouseExited(MouseEvent e) {

        JLabel source = (JLabel)e.getSource();
        source.setForeground(Color.black);
    }

    public void mousePressed(MouseEvent e){}//System.out.println(e.getX() + "," + e.getY());}
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e) {}
}