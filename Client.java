package gritnessApp;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.JFrame;
public class Client implements Runnable{
	final String LOCAL_HOST = "192.168.0.100";
    final int PORT = 4323;
    Socket clientSocket;
    PrintWriter output;
    BufferedReader input;
    String serverData;
    Queue<String> dataQ = new LinkedList<>();
    static LoginScreen login;
    public static void main(String[] args) throws Exception {
    	login = new LoginScreen();
    	login.run(login);
    	Client client = new Client();
     	Thread connectionThread = new Thread(new Client());
     	connectionThread.start();
     	client.start();
     	client.stop();
    }
    public void start() throws Exception {
        //create a socket with the local IP address and attempt a connection
        System.out.println("Attempting to establish a connection ...");
        clientSocket = new Socket(LOCAL_HOST, PORT);          //create and bind a socket, and request connection
        output = new PrintWriter(clientSocket.getOutputStream());
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Connection to server established!");

        
        //send data
        while(true) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //sending data
            if(login.userLoggedIn()) {
            	output.println("login" + " " +login.getEnteredUsername() + " " + login.getEnteredPassword());
            	
            }
//            output.println("");
            output.flush();
            
            
            //receiving data
            if (input.ready()) {
                serverData = input.readLine(); //get a response from the server
                dataQ.add(serverData);
            }
            if(!dataQ.isEmpty()) {
                serverData = dataQ.poll();
                String[] Data = serverData.split("-");
                
                }
            }
            
            // move ball

        }
	    public void stop() throws Exception {
	        input.close();
	        output.close();
	        clientSocket.close();
	    }
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
    }

