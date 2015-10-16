import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Random;


public class queryhandler {

	/**
	 * @param args
	 */
	private static int ttl;//time to live
	private static InetAddress source;//source address
	private static int queryid;
	private static Path directory;//source directory to search
	private static String query;
	private static int hops;
	private static String packet;
	private static boolean old;
	private static boolean found;
	Random random=new Random();
	private final synchedthings list;
	private static String filename;
	static String ip="128.151.69.86"; //to connect to cs lab
	//static String ip="10.5.1.151"; //for lab to connect to me
	/*
	 * 
	 * constructor for new query*/
	public queryhandler(String q,synchedthings list,Path dir){
		query=q;
		ttl=0;
		source=null;
		queryid=random.nextInt();
		old=false;
		hops=0;
		this.list=list;
		directory=dir;
		System.out.println("searching");
		packets();
	}
	
	public queryhandler(int queryid,int ttl,int hops,InetAddress source,
							String query,synchedthings list,Path dir){
		this.ttl=ttl;
		this.source=source;
		this.queryid=queryid;
		this.query=query;
		old=true;
		this.list=list;
		directory=dir;
		System.out.println("searching");
		packets();
	}
	
	//search for query
	private static boolean searchfiles(){
		System.out.println("Searching...");
		found=false;
		LinkOption opt = LinkOption.valueOf("NOFOLLOW_LINKS");
		if(Files.exists(directory,opt)){
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
			 for (Path file: stream) {
				String x=file.toString();
				System.out.println(x);
			    if(x.contains(query)){
					filename=x;
					found=true;
					return found;
				}
			}
		}
		catch (IOException | DirectoryIteratorException x) {
		    System.err.println(x);
		}
		}
		return found;
	}
	//make packet if found and old
	//return to user if found and new
	//make packet if not found and old 
	//make packet if not found and new
	
	/*this method is to modify what header values need modifying, depending on case
	and to concatenate them into a packet/string to return
	*/
	//packet form
	//queryid\n ttl\n hops\n source\n query\n
	private int port=31000;
	
	public static void packets(){
		searchfiles();
		if(old){
			if(found){//old and found, return to source
				System.out.println("old query found,returning to source");
				ttl-=1;
				hops+=1;
				//filename added to query
				packet=Integer.toString(queryid)+"\n"
						+Integer.toString(ttl)+"\n"
						+Integer.toString(hops)+"\n"
						+ip+"\n"+
						query+" "+filename+"\n";
				try{
				Socket site=new Socket(ip,31000);
				InputStream insite = site.getInputStream();
				PrintWriter out= new PrintWriter(site.getOutputStream());
				out.print(packet);
				out.flush();
				site.close();
				}
				catch(IOException e){
					System.out.println(e.getMessage());
				}
			}
			else{//old and not found, pass along
				if(ttl==0){
					System.out.println("ttl=0, so not passing along");
				}
				else{
				System.out.println("old query not found, passing along");
				ttl-=1;
				hops+=1;
				packet=Integer.toString(queryid)+"\n"
						+Integer.toString(ttl)+"\n"
						+Integer.toString(hops)+"\n"
						+source.toString()+"\n"+
						query+"\n";
				
				try{
					Socket site=new Socket(ip,31000);
					InputStream insite = site.getInputStream();
					PrintWriter out= new PrintWriter(site.getOutputStream());
					out.print(packet);
					out.flush();
					site.close();
					}
					catch(IOException e){
						System.out.println(e.getMessage());
					}
				}
			}
		}
		else{
			if(found){//new and found, return to user
				System.out.println("new query found, "+filename);
			}
			else{//new and not found, pass along
				System.out.println("new query not found, passing along");	
				ttl=0;
				hops=0;
				packet=Integer.toString(queryid)+"\n"
						+Integer.toString(ttl)+"\n"
						+Integer.toString(hops)+"\n"
						+ip+"\n"+
						query+"\n";
				try{
					Socket site=new Socket(ip,31000);
					InputStream insite = site.getInputStream();
					PrintWriter out= new PrintWriter(site.getOutputStream());
					out.print(packet);
					out.flush();
					site.close();
					}
					catch(IOException e){
						System.out.println(e.getMessage());
					}
			}
		}
	}
	
}
