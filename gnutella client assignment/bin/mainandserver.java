import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
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
	//Path path =Paths.get("C:\\Users\\Kiera_2\\Desktop\\test search");
	Path path =Paths.get("/home/hoover/u3/kcrist/Desktop/test search");
	synchedthings list=new synchedthings(20);
	System.out.println("input queries here");
	Scanner query=new Scanner(System.in);
	String q=query.next();
	queryhandler i =new queryhandler(q, list,path);
	InetAddress[] neighbors= new InetAddress[10];
    int portNumber = 31000;
    		//Integer.parseInt(args[0]);
	   try {
            ServerSocket serverSocket = new ServerSocket(portNumber); 
          //  OutputStream out = clientSocket.getOutputStream();
			//InputStream clientInputStream = clientSocket.getInputStream();
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
	}


