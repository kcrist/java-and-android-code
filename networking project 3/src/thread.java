import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Path;
import java.util.Random;


public class thread implements Runnable{

	/**
	 * @param args
	 */
	private final Socket clientSocket;
	private final synchedthings list;
	private final Path doc;
	public thread(Socket client,synchedthings list,Path doc) {
		// TODO Auto-generated constructor stub
		clientSocket=client;
		this.list=list;
		this.doc=doc;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String lines[]= null;
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			OutputStream send= clientSocket.getOutputStream();
			String line;
			int i=0;
			//System.out.println("connecting");
			/*while ((line = in.readLine())!=null) {
				lines[i]=line;
				i++;
			}
			*/
			
			//int id=Integer.parseInt(lines[0]);
			Random random=new Random();
			int id=random.nextInt();
			if(!list.search(id)){
				System.out.println("duplicate packet recieved");
				clientSocket.close();
			}
			//int ttl=Integer.parseInt(lines[1]);
			//int hops=Integer.parseInt(lines[2]);
			InetAddress source;
			//if(hops==0){
				source=clientSocket.getInetAddress();
			//}
			//else{
				 //source=InetAddress.getByName(lines[3]);
			//}
			System.out.println("debugging");
			source=null;
			//queryhandler qi= new queryhandler(id,ttl,hops,source,lines[4],list,doc);
			queryhandler qi= new queryhandler(id,0,5,source,"net",list,doc);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 System.err.println("IO Error");
		            System.exit(1);
		}
		
	} 
}
