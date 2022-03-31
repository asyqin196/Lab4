package exercise3;

	/* This is the client text counter to count the number of word.
* 
* @author asyqinharis
*
*/

import java.io.*;                                                               
import java.net.*; 
public class WordCountClient {
   public static void main(String args[])throws Exception                  
    {                                                                       
            String line, newLine;                                           
            try                                                             
            {                                                               
                    DataInputStream in=new DataInputStream(System.in);      
                    // Communication Endpoint for client and server         
                    Socket soc=new Socket("LocalHost",6789);                 
                    System.out.println("Client Started...");          
                    // DataInputStream to read data from input stream       
                    DataInputStream inp=new DataInputStream (soc.getInputStream());
                    // DataOutputStream to write data on output stream   
                    DataOutputStream out=new DataOutputStream(soc.getOutputStream());
                    while(true) {                                           
                     newLine = in.readLine();                               
                     if (newLine.equals("q")) {                             
                        out.writeBytes("Client is down..." +'\n');          
                        return;                                             
                     } else {                                               
                       out.writeBytes(newLine + '\n');                      
                     }                                                      
                     line = inp.readLine();                                 
                     System.out.println("Received from server: "+line);     
                    }                                                       
            }                                                               
            catch(Exception e)                                              
            {                                                               
            }                                                               
    }                   
}
