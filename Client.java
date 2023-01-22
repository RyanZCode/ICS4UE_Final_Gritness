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

public class Client implements Runnable {
	final String LOCAL_HOST = "192.168.0.32";
	final int PORT = 10111;
	Socket clientSocket;
	PrintWriter output;
	BufferedReader input;
	String serverData;
	Queue<String> dataQ = new LinkedList<>();
	
	Client(){
		Thread connectionThread = new Thread(this);
		connectionThread.start();
	}

//	public static void main(String[] args) throws Exception {
//		Client client = new Client();
//		Thread connectionThread = new Thread(new Client());
//		connectionThread.start();
//		client.start();
//		client.stop();
//	}

	public void start() throws Exception {
		// create a socket with the local IP address and attempt a connection
		System.out.println("Attempting to establish a connection ...");
		clientSocket = new Socket(LOCAL_HOST, PORT); // create and bind a socket, and request connection
		output = new PrintWriter(clientSocket.getOutputStream());
		input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		System.out.println("Connection to server established!");

		// send data
		//while (true) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			// sending data
//            if(login.userLoggedIn()) {
//            	output.println("login" + " " +login.getEnteredUsername() + " " + login.getEnteredPassword());
//            	
//            }
//            output.println("");
			//output.flush();

			// receiving data
//			if (input.ready()) {
//				serverData = input.readLine(); // get a response from the server
//				dataQ.add(serverData);
//			}
//			if (!dataQ.isEmpty()) {
//				serverData = dataQ.poll();
//				String[] Data = serverData.split("-");
//			}
		//}
	}
	// maybe make the identifiers "login", "signup", etc. into constants
	public String sendLogin(String username, String password) throws IOException {
		output.println("login" + " " + username + " " + password);
		output.flush();
		String serverMessage = null;
		while (serverMessage == null) {
			if (input.ready()) {
				serverMessage = input.readLine(); // get a response from the server
			}
		}
		return(serverMessage);
	}
	
	public String sendSignUp(String username, String password) throws IOException {
		output.println("signup" + " " + username + " " + password);
		output.flush();
		String serverMessage = null;
		while (true) {
			if (input.ready()) {
				serverMessage = input.readLine(); // get a response from the server
				break;
			}
		}
		return(serverMessage);
	}

	public void stop() throws Exception {
		input.close();
		output.close();
		clientSocket.close();
	}

	@Override
	public void run() {
	}
}
