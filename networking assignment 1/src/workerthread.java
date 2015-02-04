import java.io.*;
import java.net.*;
import java.lang.*;


public class workerthread implements Runnable{
	private final Socket clientSocket;
	public workerthread(Socket client) {
		// TODO Auto-generated constructor stub
		clientSocket=client;
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
			String lines[] = null;
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			OutputStream send= clientSocket.getOutputStream();
			String line;
			int i=0;
			while ((line = in.readLine())!=null) {
				lines[i]=line;
				i++;
			}
			CharSequence x ="GET";
			if(!lines[0].contains(x)){
				throw new IOException("Bad Method");
			}
			String line1[]=lines[0].split("");
			Socket site = new Socket(line1[1],80);
			
			OutputStream out = site.getOutputStream();
			String request="get / http/1.0\r\n";
			out.write(request.getBytes());
			out.flush();
			InputStream insite = site.getInputStream();
			int chi;
			while (( chi=insite.read())!=-1){
				send.write(chi);
			}
			
			site.close();
			clientSocket.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 System.err.println("IO Error");
		            System.exit(1);
		}
		
	} 

}
