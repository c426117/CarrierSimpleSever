package per.c426117.carriersimplesever;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main 
{
	public static void main (String[] args) 
	{ 
		ServerSocket serverSocket = null;
		System.out.println("服务器启动");
		System.out.println("主进程启动");
		
		Thread fileThread = new Thread(new FileSever());
		fileThread.start();
		System.out.println("文件传输进程启动");
		
		Thread login = new Thread(new loginThread());
		login.start();
		System.out.println("登陆进程启动");
		
		Thread upLoad = new Thread(new UpLoadThread());
		upLoad.start();
		System.out.println("文件上传进程启动");
		
		while (true)
		{
			try 
			{
				serverSocket = new ServerSocket(2334);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Socket client = null;
			System.out.println("主进程等待链接中");
			try
			{
				client = serverSocket.accept();
				System.out.println("主进程链接成功");
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			BufferedReader in =null;
			PrintStream out =null;
			try 
			{
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				out = new PrintStream(client.getOutputStream());
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			File file = null;
			File[] filenames = null;
			String con = null;
			try
			{
				con = in.readLine();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			System.out.println(con);
			
			file = new File(con);
			System.out.println(file.toString());
			if(file.exists())
			{
				filenames = file.listFiles();
				out.print(String.valueOf(filenames.length)+"\n");
				for(int i = 0;i!=filenames.length;i++)
				{
					if(filenames[i].isDirectory())
					{
						out.print(filenames[i]+"#\n");
					}
					else
					{
						out.print(filenames[i]+"\n");
					}
				}
				
			}
			try
			{
				in.close();
				out.close();
				client.close();
				serverSocket.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}
	}
}
