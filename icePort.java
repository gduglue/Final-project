import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



public class icePort extends JFrame{
  JMenuBar menuBar;
	JMenu function;
	JMenuItem help,about,quit;
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
		help = new JMenuItem ("Help(F1)"); // to be done by nad
		about = new JMenuItem ("About"); // to be done by chubby
		quit = new JMenuItem("Exit");
		
		function.add(help);
		function.add(about);
		function.add(quit);
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
	/*	help.setMnemonic(KeyEvent.VK_F1);
		//accelerator() needed else setMnemonic would fire new menu as well as the menuitem wanted
		help.setAccelerator(KeyStroke.getKeyStroke
				(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
		help.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//put in the Help() 
			}
		});
		
		about.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			// open up the about page
			}
			});
		

	
	}*/
	
		//quitting the program
		quit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
			});
	
	}

}
