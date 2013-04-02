import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.*;


public class StateFetching extends JFrame implements Runnable {

	protected Thread thread;
	protected int REFRESH_INTERVAL;
	
	JSON weather, 
	weather_time,
	userID,
	username,
	icetizen_type,
	IP_address,
	ice_port_ID,
	intended_destination,
	time,
	position;
	
	public StateFetching() {
		if(thread==null){
			thread=new Thread(this);
			thread.start();
		}
	}
	
	public void init(){
		thread=null;
		REFRESH_INTERVAL=10000;
	}



	public void run(){
		while(Thread.currentThread()==thread){
			StateFetching refresh = new StateFetching();
			try {
				refresh.URLtester();
			} catch (Exception e) {	}
			
			
			try{Thread.currentThread().sleep(REFRESH_INTERVAL);			
			}catch(InterruptedException e){}
			
			}
		}

	
		
		//for testing URL connect
		public void URLtester() throws Exception {
		    String locat = "http://iceworld.sls-atl.com/api/explore";

		    try {
		    	/*
		    	System.out.println("..Checking for connection..");
		    	System.out.println("Press CTRL + Z for QUIT");
		    	*/
		    	
		        URL url = new URL(locat);
		        HttpURLConnection uconn = (HttpURLConnection) url.openConnection();
		        uconn.connect();

		        //check if connected if connect show can be reached
		        assertEquals(HttpURLConnection.HTTP_OK, uconn.getResponseCode());
		       
		    } catch (IOException e) {
		    	
		    	JDialog warning = new JDialog();
				warning.setBounds(10,10,300,300);
				warning.setModal(true);
				Container container= warning.getContentPane();
				JOptionPane.showMessageDialog(container,
					    "The ICE World cannot be reached",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
				warning.setVisible(true);
		        
		   
		    } 
		}
		
		
		public void setREFRESH_INTERVAL(int interval){
			interval=interval*1000;
			this.REFRESH_INTERVAL=interval;
		}		
}
