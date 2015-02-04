import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class proxyserver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* if (args.length != 1) {
	            System.err.println("syntax is java server <port number here>");
	           System.exit(1);
	      }*/
	        
	        int portNumber = 31000;
	        		//Integer.parseInt(args[0]);
	    	   try {
	                ServerSocket serverSocket = new ServerSocket(portNumber); 
	              //  OutputStream out = clientSocket.getOutputStream();
 				//InputStream clientInputStream = clientSocket.getInputStream();
 				for(;;){
 					Socket clientSocket = serverSocket.accept();  
 					new Thread(new workerthread(clientSocket)).start();
 				}
	        }
	        catch (IOException e) {
	            System.out.println("Exception caught when trying to listen on port "
	                + portNumber + " or listening for a connection");
	            System.out.println(e.getMessage());
	        }
	}
	

}
