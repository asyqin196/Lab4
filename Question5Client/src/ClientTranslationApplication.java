/**
 * This is the client translation to translate word enter by user.
 * 
 * @author asyqinharis
 *
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientTranslationApplication {

	static ClientTranslatorGUI client = new ClientTranslatorGUI();
	
	public static void main(String[] args) 
	{	
		client.setVisible(true);

	}
	
	static public void btnPressed()
	{
		try
		{		
			Socket socket = new Socket(InetAddress.getLocalHost(), 4348);
			
			client.updateConnectionStatus(socket.isConnected());
			
			DataInputStream datainputstream = new DataInputStream(socket.getInputStream());
			
			String textinput = client.getText();
			String language = client.getLanguage();
			
			DataOutputStream dataoutputStream = new DataOutputStream(socket.getOutputStream());
			
			if(client.ispressed() == true)
			{
				dataoutputStream.writeUTF(textinput);
				dataoutputStream.writeUTF(language);
				
				String translated = datainputstream.readUTF();
				
				client.setAnsLbl(translated);
				client.updatebtn(false);
			}
			
			
			datainputstream.close();
			dataoutputStream.close();
			socket.close();
			
			textinput = "";
			language = "";
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		} 
	}

}
