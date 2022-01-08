package jam.FlightDeparturesMap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.xml.stream.events.Comment;

public class HoverOver implements MouseListener{

    public HoverOver() {
    }

    public void mouseEntered(MouseEvent e) {
        JLabel source = (JLabel)e.getSource();
        source.setForeground(Color.MAGENTA);

    }

    public void mouseExited(MouseEvent e) {

        JLabel source = (JLabel)e.getSource();
        source.setForeground(Color.black);
    }

    public void mousePressed(MouseEvent e){}//System.out.println(e.getX() + "," + e.getY());}
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e) {}
}