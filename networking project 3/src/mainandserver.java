import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class mainandserver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* if (args.length != 1) {
        System.err.println("syntax is java server <port number here>");
       System.exit(1);
  }*/
	Path path =Paths.get("C:\\Users\\Kiera_2\\Desktop\\test search");
	//Path path =Paths.get("/home/hoover/u3/kcrist/Desktop/test search");
	synchedthings list=new synchedthings(20);
	System.out.println("input queries here");
	Scanner query=new Scanner(System.in);
	String q=query.next();
	//queryhandler i =new queryhandler(q, list,path);
	//InetAddress[] neighbors= new InetAddress[10];
    int portNumber = 31000;
    String x=getIP();
    //System.out.println(x);
    queryhandler i =new queryhandler(q, list,path);
	   try {
            ServerSocket serverSocket = new ServerSocket(portNumber); 
           // System.out.println("hi there!");
			for(;;){
				Socket clientSocket = serverSocket.accept();	
				new Thread(new thread(clientSocket,list,path)).start();
			}
    }
    catch (IOException e) {
        System.out.println("Exception caught when trying to listen on port "
            + portNumber + " or listening for a connection");
        System.out.println(e.getMessage());
    }
	   
}
	private static String getIP()
	{
	    // This try will give the Public IP Address of the Host.
	    try
	    {
	        URL url = new URL("http://automation.whatismyip.com/n09230945.asp");
	        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	        String ipAddress = new String();
	        ipAddress = (in.readLine()).trim();
	        /* IF not connected to internet, then
	         * the above code will return one empty
	         * String, we can check it's length and
	         * if length is not greater than zero, 
	         * then we can go for LAN IP or Local IP
	         * or PRIVATE IP
	         */
	        if (!(ipAddress.length() > 0))
	        {
	            try
	            {
	                InetAddress ip = InetAddress.getLocalHost();
	                System.out.println((ip.getHostAddress()).trim());
	                return ((ip.getHostAddress()).trim());
	            }
	            catch(Exception ex)
	            {
	                return "ERROR";
	            }
	        }
	        System.out.println("IP Address is : " + ipAddress);

	        return (ipAddress);
	    }
	    catch(Exception e)
	    {
	        // This try will give the Private IP of the Host.
	        try
	        {
	            InetAddress ip = InetAddress.getLocalHost();
	            System.out.println((ip.getHostAddress()).trim());
	            return ((ip.getHostAddress()).trim());
	        }
	        catch(Exception ex)
	        {
	            return "ERROR";
	        }
	    }
	}
	}


