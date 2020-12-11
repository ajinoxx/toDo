import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Task
{
	final Color txtHL = new Color(0xDCDCDC);
	final Color txtFG = new Color(0xCFCFCF);
	final Color txtBG = new Color(0x797979);
	final Color grayFG = new Color(0x363636);
	final Color grayBG = new Color(0x232323);
	
	String date;
	String name;
	String[] tags;
	boolean priority;
	boolean status;
	
	JPanel panel;
	JLabel lName;
	JLabel lDate;
	JButton b;
	
	Task(String d, String n, String[] t)
	{
		date = d;
		name = n;
		tags = t;
		priority = false;
		status = false; // false for not completed
	}
	
	public void createPanel()
	{
		panel = new JPanel();
		panel.setBackground(grayFG);
		
		b = new JButton("O"); // Set this to check icon
		b.setFont(new Font("Courier New",Font.BOLD,12)); // Font
		b.setForeground(txtHL);
		b.setBackground(grayFG);
//		b.setOpaque(true);
		b.setBorderPainted(false);
		b.setMargin(new Insets(0,0,0,0));
		panel.add(b);
		
		lName = new JLabel(name);
		lName.setForeground(txtFG);
		lName.setFont(new Font("Courier New",Font.PLAIN,12)); // Font
		panel.add(lName);

//		b.addActionListener(this); // Can't do this b/c I need to be able to remove the panel from the taskList in ToDo and the mFrame in ToDo
	}
	
	public void complete()
	{
		status = true;
		//TODO: update the look of the label to be crossed out/grayed out
		b.setText("X"); // Set this to X icon
		
		lName.setForeground(txtBG);
	}
}