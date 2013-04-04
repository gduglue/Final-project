import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;



public class icePort extends JFrame{
	JMenuBar menuBar;
	JMenu function;
	JMenuItem help,about,quit, REFRESH_INTERVAL_item;
	JDesktopPane desktop;
	static int offsetMultiplier = 0;
	
	public icePort(){
		super("IcePort");
		Toolkit toolkit = Toolkit.getDefaultToolkit(); // get screen size
		Dimension screensize = toolkit.getScreenSize(); // get screen size
		desktop = new JDesktopPane();
		desktop.setBackground(Color.WHITE);
		setContentPane(desktop);
		setJMenuBar(setGUI());
		setPreferredSize(screensize);
		addListener();
		
		desktop.setDragMode(JDesktopPane.LIVE_DRAG_MODE); 
	}
	
	
	public static void main(String[] args) 
	{
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() 
		{
            public void run() 
            {
                createAndShowGUI();
            }
        });
	}
	
	//Setting of GUI to actually show the help and author
	protected JMenuBar setGUI(){
		
		menuBar = new JMenuBar();
		function = new JMenu("File");
		help = new JMenuItem ("Help"); // to be done by nad
		about = new JMenuItem ("About"); // to be done by chubby
		quit = new JMenuItem("Exit");
		REFRESH_INTERVAL_item= new JMenuItem("Refresh interval");
		
		function.add(help);
		function.addSeparator();
		function.add(about);
		function.addSeparator();
		function.add(quit);
		function.add(REFRESH_INTERVAL_item);
		
		help.setAccelerator(KeyStroke.getKeyStroke("F1"));
		
		menuBar.add(function);
		
		return menuBar;
	}
	
	// create and show GUI allowing it to close
	protected static void createAndShowGUI()
	{
		icePort initialFrame = new icePort();
		initialFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initialFrame.pack();
		initialFrame.setVisible(true);
	}
	
	
	// create the basic frame with background 
	protected void createFrame()
	{
		MyInternalFrame frame = new MyInternalFrame();
		ImageIcon image = new ImageIcon("Users/ZethaRia/Documents/Chat.jpg"); //adding the splash screen
		
		 frame.setFrameIcon(image);
		
	     frame.pack();
	    frame.setVisible(true);
        desktop.add(frame);
        try 
        {
            frame.setSelected(true);
        } 
        catch (java.beans.PropertyVetoException e) {}
	}
	
	
	// ensure that multiple frame can be created
	 	public static class MyInternalFrame extends JInternalFrame
	{
	    static int openFrameCount = 0;
	    static final int xOffset = 30, yOffset = 30;
	 
	   public MyInternalFrame() 
	    {
	        super("Untitled-"+(++openFrameCount),true,true,true,true);
	        setSize(600,600);
	        ++offsetMultiplier;
	        setLocation(xOffset*offsetMultiplier, yOffset*offsetMultiplier);
	    }
	
	}



	private void addListener() {
	
		//quitting the program
		quit.addActionListener(new MyHandler());
		help.addActionListener(new MyHandler());
		about.addActionListener(new MyHandler());
		REFRESH_INTERVAL_item.addActionListener(new MyHandler());
		
	}
	
	class MyHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			if(e.getSource() ==quit){
				System.exit(0);
			}
			
			if(e.getSource()== about){
				JDialog aboutDialog = new JDialog();
				aboutDialog.setBounds(10,10,300,300);
				aboutDialog.setVisible(true);
			}
			
			if(e.getSource()== help){
				JDialog helpDialog = new JDialog();	
				helpDialog.setBounds(10,10,300,300);
				helpDialog.setModal(true);
				helpDialog.setLocationRelativeTo(null);
				helpDialog.setVisible(true);
				}
			
			if(e.getSource()== REFRESH_INTERVAL_item){
				// Open an internal frame when the item is selected :
				JInternalFrame RIframe = new JInternalFrame();	
				RIframe.setBounds(10,10,300,300);
				JPanel panel= (JPanel) RIframe.getContentPane();
				
				// In the frame, we can select the refresh interval via a combo box : 
				JTextArea text = new JTextArea("Select the refresh interval of the ICE World :"); 
				DefaultComboBoxModel mdc = new DefaultComboBoxModel(); 
				final JComboBox combo = new JComboBox();
				combo.setModel(mdc);
				combo.setBounds(new Rectangle(19, 36, 129, 23)); 
				
				for(int i=1; i<=10; i++){
				mdc.addElement(""+i); 
				combo.addItem(""+i); 	// adding the elements of the combo			
				}	
				
				combo.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						int new_value= (Integer) combo.getSelectedItem();
						StateFetching.setREFRESH_INTERVAL(new_value);
					}
				});
				
				
				panel.add(text, BorderLayout.CENTER);
				panel.add(combo, BorderLayout.CENTER);
				RIframe.setVisible(true);
			}	
		}
				
			
					

	}
}


