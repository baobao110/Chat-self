import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import jdk.internal.util.xml.impl.Input;


public class Client {
	public void run() throws Exception{
		final Socket socket=new Socket("192.168.8.178",3560);
		new Thread(new Runnable(){
			public void run(){
				Scanner b=new Scanner(System.in);
				try {
					PrintStream c= new PrintStream(socket.getOutputStream());
					String msg=null;//这里用一个字符串变量接收服务器的发送信息,不然的接收信息不完整，这里因为没有用变量接收，所以一开始出现服务器那边没有数据接收信息
					while(null!=(msg=b.next())){
						c.println(msg);//这里的方法是换行的作用，不然客户端无法连续的输出
						c.flush();//这里数据传输完要刷新缓存的数据，在写代码的过程中发现一开始没有写该方法出现的数据发送后客户端没有收到服务器的回应。
				}
			}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
		},"输出").start();
		
		new Thread(new Runnable(){
			public void run(){
				try {
					Scanner a=new Scanner(socket.getInputStream());
					String msg=null;
					while(null!=(msg=a.next())){
						System.out.println(msg);
						}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"输入").start();//这里用多线程的概念建立两个线程用于处理客户端的收发问题。
			
			
	}	
	public static void main(String[] args) throws Exception {
		new Client().run();
	}
}