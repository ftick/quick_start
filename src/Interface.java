import java.awt.*; 
import java.awt.event.*;

import javax.swing.*;

public class Interface extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	// Create objects
	JLabel titleLabel = new JLabel( "                        Quick Start                        " );
	JLabel urlLabel = new JLabel( "Enter Website Name: " );
	JTextField urlText = new JTextField(30);
	JLabel searchLabel = new JLabel( "Search for: " );
	JTextField searchText = new JTextField(30);
	
	public Interface( String title )
	{
		super( title );
		
		// Set layout
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		// Add elements
		c.weightx = 0.5;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10,10,0,10);
	    c.gridx = 0;
	    c.gridy = 0;
		pane.add( urlLabel, c );
		
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 0;
		pane.add( urlText, c );
		
		c.fill = GridBagConstraints.BOTH;
	    c.gridx = 0;
	    c.gridy = 1;
		pane.add( searchLabel, c );
		
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridwidth = 2;
	    c.insets = new Insets(10,10,0,10);
	    c.gridx = 1;
	    c.gridy = 1;
		pane.add( searchText, c );
		
		// Add JPanel
		add(pane);
		
		// Set font sizes
		titleLabel.setFont(titleLabel.getFont().deriveFont(32.0f));
		
		// Have elements be their own actionListeners
		urlText.addActionListener(this);
		searchText.addActionListener(this);
		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}

	@Override
	public void actionPerformed( ActionEvent e )
	{
		// TODO Auto-generated method stub
		if(!urlText.getText().equals("")){
			BrowserControl.openURL("http://" + urlText.getText() + ".com");
		}else if(!searchText.getText().equals("")){
			BrowserControl.openURL("https://www.google.ca/webhp?sourceid=chrome-instant&ion=1&espv=2&es_th=1&ie=UTF-8#safe=off&q=" + searchText.getText());
		}
	}
	
	public static void main( String[] args )
	{
		Interface frame  = new Interface( "Quick Start" );
	    
   		frame.setSize( 480, 120 );
    	frame.setVisible(true);  
	}
}
