import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ToDo implements ActionListener
{
	JFrame mFrame;
	JPanel mPanel;
	
//	JButton submitTaskBTN;
	JTextField inputTXT;
	
	JScrollPane scrollPane;
	
	JPanel listPanel;
	
//	ArrayList<JPanel> panelList = new ArrayList<JPanel>();
	ArrayList<Task> taskList = new ArrayList<Task>();
	
	final Color txtFG = new Color(0xCFCFCF);
	final Color txtBG = new Color(0x797979);
	final Color grayFG = new Color(0x363636);
	final Color grayBG = new Color(0x232323);
	
	public static void main(String[] args) 
	{
		new ToDo();
	}
	
	public ToDo()
	{
		// Main Frame & Panel
		mFrame = new JFrame("To-Do");
		mPanel = (JPanel)mFrame.getContentPane();
		mPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		mPanel.setBackground(grayBG);
		mFrame.setMinimumSize(new Dimension(500, 600));
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Scroll Pane
		scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(480,520));
		
		// Panel to go into the scrollPane
		JPanel scrollListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		scrollListPanel.setBackground(grayBG);
		scrollPane.setViewportView(scrollListPanel);
		
		// Panel to go into the panel that's in the scollPane
		listPanel = new JPanel(new GridBagLayout());
		scrollListPanel.add(listPanel);
		GridBagConstraints cl = new GridBagConstraints();
		cl.anchor = GridBagConstraints.WEST;
		listPanel.setBackground(grayBG);
		
		mPanel.add(scrollPane, c);
		
		// Input Panel
		JPanel inPanel = new JPanel();
		inPanel.setBackground(grayFG);
		
		// Text field
		inputTXT = new JTextField(60);
		inputTXT.setBackground(grayFG); // Box color
		inputTXT.setForeground(txtFG); // Text color
		inputTXT.setCaretColor(txtFG); // Caret color
		inputTXT.setFont(new Font("Courier New",Font.PLAIN,12)); // Font
		inputTXT.setBorder(new MatteBorder(0,0,1,0,txtFG)); // One sided border
		inPanel.add(inputTXT);
		inputTXT.addActionListener(this);
		
		c.gridy = 1;
		mPanel.add(inPanel, c);
		
		mFrame.setVisible(true); // Do this at the end to refresh the components! (Don't have to resize each time)
	}
	public void actionPerformed(ActionEvent e) 
	{
		Object control = e.getSource();
		if(control == inputTXT && inputTXT.getText().length() > 2)
		{
			String[] tags = {"Math", "Today"};
			taskList.add(new Task(null, inputTXT.getText(), tags));
			taskList.get(taskList.size() - 1).createPanel();
			JButton b = (JButton)taskList.get(taskList.size() - 1).panel.getComponent(0);
			b.addActionListener(this);
			GridBagConstraints c = new GridBagConstraints();
			c.gridy = taskList.size() - 1;
			listPanel.add(taskList.get(taskList.size() - 1).panel, c);
			inputTXT.setText("");
			mFrame.revalidate();
		}
		else
		{
			for(int i = 0; i < taskList.size(); i++)
			{
				if(control == taskList.get(i).panel.getComponent(0) && taskList.get(i).status) // If it's the panel's button, and the status is completed
				{
					listPanel.remove(taskList.get(i).panel);
					listPanel.revalidate();
					scrollPane.revalidate();
					taskList.remove(i);
					mFrame.revalidate();
				}
				else if(control == taskList.get(i).panel.getComponent(0)) // If it's the panel's button, and the status is incomplete
				{
					taskList.get(i).complete(); // Sets the status to complete and changes the look of the panel
					mFrame.revalidate();
				}
			}
		}
	}
}
