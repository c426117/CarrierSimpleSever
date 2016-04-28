package per.c426117.carriersimplesever;

import java.net.ServerSocket;

public class loginThread implements Runnable {

	@Override
	public void run() 
	{
		ServerSocket serverSocket = null;
		try
		{
			serverSocket = new ServerSocket(2333);
			serverSocket.accept();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

}
