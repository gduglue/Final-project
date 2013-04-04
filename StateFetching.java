import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.swing.*;


public class StateFetching extends JFrame implements Runnable {

	protected Thread thread;
	protected static int REFRESH_INTERVAL;
	private java.io.File file;
	
	
	public StateFetching() {
		if(thread==null){
			thread=new Thread(this);
			thread.start();
		}
	}
	
	public void init() throws IOException{
		thread=null;
		REFRESH_INTERVAL=10000;	
		
		// creating a file that will contain the states of the ICE_World
		try{
		file = new java.io.File("Refreshed_States.txt"); 
		file.createNewFile();  
		} catch(IOException e){};
	}



	public void run(){
		while(Thread.currentThread()==thread){
			StateFetching refresh = new StateFetching();
			try {
				refresh.URLtester();
				refresh.HTTPrequest();
			} catch (Exception e) {	}
						
			try{Thread.currentThread();
			Thread.sleep(REFRESH_INTERVAL);			
			}catch(InterruptedException e){}
			
			}
		}

	
		
		//method to connect to the url
		public void URLtester() throws Exception {
		    String locat = "http://iceworld.sls-atl.com/api/explore";

		    try {
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
		
		
		public static void setREFRESH_INTERVAL(int interval){
			REFRESH_INTERVAL=interval;
		}
		
		
		
		public void HTTPrequest() throws IOException{
			boolean exit = false;
			
			while(!exit){
				// url request	
			    URL myURL = new URL("http://iceworld.sls-atl.com/api/&cmd=states");
			    URLConnection oc = myURL.openConnection();
							
				try
				{
					//erase the content of the file, in order to write the new states in it
					PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, false))); 							pw.print("");
					pw.close();
					
					// opening the file again in order to write the new states :
					FileWriter fw = new FileWriter(file, false);
					BufferedWriter output = new BufferedWriter(fw);
				
					// Reading the answer :
			        BufferedReader in = new BufferedReader(new InputStreamReader(oc.getInputStream()));
			        String inputLine;
			        String response = "";
			       
					while ((inputLine = in.readLine()) != null) {
						response += inputLine+"\n";							
						output.write(response);// writing in the buffer							
						output.flush();  // sending the text in the file
					}
			        
			        	System.out.println(response);
			        
			        in.close();
			        output.close();
			        break;
				}
				catch(IOException ioe){
					ioe.printStackTrace();
					}
		    }
		}
				
				
				
				
			
}







