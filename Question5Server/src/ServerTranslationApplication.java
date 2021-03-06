/**
 * This is the server translation to translate word enter by user.
 * 
 * @author asyqinharis
 *
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTranslationApplication {

	public static void main(String[] args) throws IOException
	{
		setUpData setup = new setUpData();
		setup.createData();
		
		ServerSocket serverSocket = null;
		TranslateText translate = new TranslateText();
		
		serverFrame frame = new serverFrame();
		frame.setVisible(true);
		
		try
		{
			int totalRequest = 0;
			int portNo = 4348;
			serverSocket = new ServerSocket(portNo);
			
			frame.updateServerStatus(false);
			
			while(true)
			{
				//Accept client request for connection
				Socket clientSocket = serverSocket.accept();
				
				frame.updateServerStatus(clientSocket.isConnected());
				
				DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
				String text = inputStream.readUTF();
				String language = inputStream.readUTF();
				String translated = "";
				String bm = "malay";
				String arb = "arab";
				String krn = "korean";
				
				if(language.equals(bm))
				{
					translated = translate.translate2BM(text);
				}
				if(language.equals(arb))
				{
					translated = translate.translate2Arb(text);
				}
				if(language.equals(krn))
				{
					translated = translate.translate2Krn(text);
				}
				

				//Create stream to write data on the network
				DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
				
				//send current text back to the client
				outputStream.writeUTF(translated);
				
				
				//close the socket
				outputStream.flush();
				outputStream.close();
				clientSocket.close();
				
				frame.updateRequestStatus("Data sent to the client: " + text + " is translated to " + language);
				frame.updateRequestStatus("Accepted connection to from the " + "client. Total request = " + ++totalRequest);
				
				text = "";
				language = "";
			}
			
		}
		catch(IOException ioe)
		{
			if(serverSocket != null)
			{
				serverSocket.close();
			}
			
			ioe.printStackTrace();
		}

	}

}
