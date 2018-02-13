
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Server {
	ArrayList<PrintStream> x=new ArrayList<PrintStream>();//����֮�����õ����ϵĸ�����Ϊ�˶��û��Ľ��룬�Լ����ݵĹ㲥
	public void run() throws Exception {
			ServerSocket a=new ServerSocket(3560);
		while(true){//while �������������������Ľ��գ���Ȼֻ�ܽ���һ��
			final Socket b=a.accept();//���տͻ��˵������� ������������Բ鿴API;
			new Thread(new Runnable(){
				public void run(){
			try {
				Scanner c= new Scanner(b.getInputStream());
				x.add(new PrintStream(b.getOutputStream()));//����ÿ����һ���ͻ��˵����ݾͽ���һ��BufferedWriterΪ�˺����
				//�㲥�������Ϳ���ʵ�ֶ��û���Ⱥ��
				String msg=null;
				while(null!=(msg=c.next())){
					System.out.println(msg);
					broadcast(msg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}).start();
		}
}
	public synchronized void broadcast(String msg){//����ʹ��ͬ������Ϊ�˷�ֹ���û�ͬʱ����ʱ��������Ƿ��ܹ�ȫ������ת��
		Iterator<PrintStream> a=x.iterator();//��ȡ֮ǰ�Ŀͻ��˵Ľ�����������Ϊ��ʵ�ֹ㲥�Ĺ��ܣ������������õ������ֱ��ȡ������������
		while(a.hasNext()){
			PrintStream b=a.next();
			b.println(msg);
			b.flush();//����ͬ����ÿ�η�����Ҫ����ˢ�²����������������
		}
				
	}
	public static void main(String[] args) throws Exception {
		new Server().run();
	}
}
/*
 * z������Ҫ�����������ͽ��̵�֪ʶ �����ͨ��������������ݵķ��ͺͽ��գ��ý��̵�֪ʶ������ͺͽ��յ���ͬһ�˵�����,���о������ʵ�ֶ��û������칦�������ü��ϵķ�����
 * ͨ�����ϴ洢�ͻ��˵�����������Ϊ�˷�ֹ���û�ͬʱ����ĳ�ͻ����һ����ͬ����
 */