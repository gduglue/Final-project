package iceWorldpeek;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import static org.junit.Assert.*;
import java.io.*;

public class iceWorldPeek { 
  
	public static void main (String[]args){
		iceWorldPeek run = new iceWorldPeek();
		try {
			run.URLtester();
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//for testing URL connect
	public void URLtester() throws Exception {
	    String locat = "http://iceworld.sls-atl.com/api/explore";

	    try {
	    	System.out.println("..Checking for connection..");
	    	System.out.println("Press CTRL + Z for QUIT");
	    	
	        URL url = new URL(locat);
	        HttpURLConnection uconn = (HttpURLConnection) url.openConnection();
	        uconn.connect();

	        //check if connected if connect show can be reached
	        assertEquals(HttpURLConnection.HTTP_OK, uconn.getResponseCode());
	        System.out.println("ICE WORLD can be reached.");
	        HTTPrequest();
	    } catch (IOException e) {
	        System.out.println("ICE WORLD cannot be connected");
	   
	    } 
	}
	
	

	
	
	
	// choice after connection to HTTP has been built
		public static void HTTPrequest() throws IOException{
		
		boolean exit = false;
			while(!exit){
			System.out.println("Select choice of your request (1-6):\n<1> Time\n<2> " +
					"States\n<3> Actions\n<4> gResources\n<5> gURL\n<6> EXIT");
			 Scanner kb = new Scanner(System.in);
	    	int key = kb.nextInt();
	    	 
			String req ="";
			
			if(key==1) req+="time";
			else if (key==2) req+="states";
			else if (key==3) req+="actions&from=";
			else if (key==4) req+="gresources&uid=";
			else if (key==5) req+="gurl&gid=";
			else if (key==6){
			System.out.println("Program is terminated");
			System.exit(0);
			}
			
	        URL myURL = new URL("http://iceworld.sls-atl.com/api/&cmd="+req);
	        URLConnection oc = myURL.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(oc.getInputStream()));
	        String inputLine;
	        String response = "";
	       
	        // read the API contents
			while ((inputLine = in.readLine()) != null) {
				response += inputLine+"\n";
			}
	        
	        	System.out.println(response);
	        
	        in.close();
	        //return response;
	        break;
	    }
		}
}
	


	
