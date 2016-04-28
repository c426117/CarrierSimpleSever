package per.c426117.carriersimplesever;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class UpLoadThread implements Runnable 
{
	private String filename = null;
	
	@Override
	public void run() 
	{
		while(true)
		{
			try
			{
				ServerSocket serverSocket = new ServerSocket(2336);
				Socket client = null;
				
				client = serverSocket.accept();
				
				
				
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				filename = in.readLine();
				System.out.println(filename);
				in.close();
				serverSocket.close();
				client.close();
				
				serverSocket = new ServerSocket(2336);
				client = serverSocket.accept();
				
				FileOutputStream fout = new FileOutputStream(filename);
				DataInputStream din = new DataInputStream(client.getInputStream());
				
				byte[] buf = new byte[1024];
				int l = 0;
				while((l = din.read(buf))>0)
				{
					fout.write(buf,0,l);
					fout.flush();
				}
				
				fout.close();
				din.close();
				client.close();
				serverSocket.close();
				System.out.println("ÉÏ´«½áÊø");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
