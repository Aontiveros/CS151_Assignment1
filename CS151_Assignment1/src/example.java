import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class example {

public static void main (String[] args){    
  JFrame frame = new JFrame("Test");
  frame.setVisible(true);
  frame.setSize(500,200);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  JPanel panel = new JPanel();
  frame.add(panel);
  JButton button = new JButton("hello agin1");
  panel.add(button);
  button.addActionListener (new Action1());

  JButton button2 = new JButton("hello agin2");
  panel.add(button2);
  button.addActionListener (new Action2()); 
}
static class Action1 implements ActionListener {        
  public void actionPerformed (ActionEvent e) {     
    JFrame frame2 = new JFrame("Clicked");
    frame2.setVisible(true);
    frame2.setSize(200,200);
    JLabel label = new JLabel("you clicked me");
    JPanel panel = new JPanel();
    frame2.add(panel);
    panel.add(label);       
  }
}   
static class Action2 implements ActionListener {        
  public void actionPerformed (ActionEvent e) {     
    JFrame frame3 = new JFrame("OKNO 3");
    frame3.setVisible(true);
    frame3.setSize(200,200);

    JLabel label = new JLabel("kliknales");
    JPanel panel = new JPanel();
    frame3.add(panel);
    panel.add(label);
  }
}   
}