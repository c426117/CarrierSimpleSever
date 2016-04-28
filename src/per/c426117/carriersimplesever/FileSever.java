package per.c426117.carriersimplesever;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class FileSever implements Runnable 
{

	private ServerSocket fileServerSocket = null;
	private Socket fileClient = null;
	private byte[] filebuf = new byte[1024];
	@Override
	public void run() 
	{
		while(true)
		{
			try
			{
				fileServerSocket = new ServerSocket(2335);
				System.out.println("�ļ�����ȴ�����");
				fileClient =fileServerSocket.accept();
				System.out.println("���ӳɹ�");
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			BufferedReader in = null;
			FileInputStream fin = null;
			DataOutputStream dout = null;
			try
			{
				in=new BufferedReader(new InputStreamReader(fileClient.getInputStream()));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			String pathname = null;
			try
			{
				 pathname = in.readLine();
				 System.out.println("����"+pathname);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			File file = new File(pathname);
			
			try
			{
				fin = new FileInputStream(file);
				dout = new DataOutputStream(fileClient.getOutputStream());
				int i = 0;
				while((i=fin.read(filebuf))>0)
				{
					dout.write(filebuf,0,i);
					dout.flush();
					//System.out.println("���� "+ i);
				}
				fin.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("�����ļ�����");
			
			try
			{
				fin.close();
				fileClient.close();
				fileServerSocket.close();
				in.close();
				dout.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}
	
	}
}
