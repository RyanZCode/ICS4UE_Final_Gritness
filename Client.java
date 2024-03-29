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
	private String username;

	Client() {
	}

	public void start() throws Exception {
		// create a socket with the local IP address and attempt a connection
		System.out.println("Connecting to server...");

		// create and bind a socket, and request connection
		clientSocket = new Socket(LOCAL_HOST, PORT);
		output = new PrintWriter(clientSocket.getOutputStream());
		input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		System.out.println("Connected succesfully!");

		try {Thread.sleep(5);} catch (InterruptedException e) {throw new RuntimeException(e);}
	}

	// maybe make the identifiers "login", "signup", etc. into constants
	// Send login data to server, receive whether login was successful or not
	public String sendLogin(String username, String password) throws IOException {
		// Send message to server
		output.println("login" + "$$" + username + "$$" + password);
		output.flush();
		String serverMessage = null;
		while (serverMessage == null) {
			if (input.ready()) {
				// Get a response from the server
				serverMessage = input.readLine(); 
			}
		}
		return (serverMessage);
	}

	// Send signup data to server, receive whether signup was successful or not
	public String sendSignUp(String username, String password) throws IOException {
		// Send message to server
		output.println("signup" + "$$" + username + "$$" + password);
		output.flush();
		String serverMessage = null;
		while (true) {
			if (input.ready()) {
				// Get a response from the server
				serverMessage = input.readLine();
				break;
			}
		}
		return (serverMessage);
	}
	
	// Get profile data from server
	public String getProfileInfo() throws IOException {
		// Send message to server
		output.println("profileTab" + "$$" + username);
		output.flush();
		String serverMessage = null;
		while (true) {
			if (input.ready()) {
				// Get a response from the server
				serverMessage = input.readLine();
				break;
			}
		}
		return (serverMessage);
	}
	
	// Get profile data from server
	public String getProfileCalHistory() throws IOException {
		// Send message to server
		output.println("profileCalHistory" + "$$" + username);
		output.flush();
		String serverMessage = null;
		while (true) {
			if (input.ready()) {
				// Get a response from the server
				serverMessage = input.readLine();
				break;
			}
		}
		return (serverMessage);
	}
	
	// Get profile data from server
	public String getProfileWorkoutNumHistory() throws IOException {
		// Send message to server
		output.println("profileWorkoutNum" + "$$" + username);
		output.flush();
		String serverMessage = null;
		while (true) {
			if (input.ready()) {
				// Get a response from the server
				serverMessage = input.readLine();
				break;
			}
		}
		return (serverMessage);
	}
	
	// Get history data from server
	public String getHistory() throws IOException {
		// Send message to server
		output.println("historyTab" + "$$" + username);
		output.flush();
		String serverMessage = null;
		while (true) {
			if (input.ready()) {
				// Get a response from the server
				serverMessage = input.readLine();
				break;
			}
		}
		return (serverMessage);
	}
	
	// Get workout data from server
	public String getWorkouts() throws IOException {
		// Send message to server
		output.println("workoutTab" + "$$" + username);
		output.flush();
		String serverMessage = null;
		while (true) {
			if (input.ready()) {
				// Get a response from the server
				serverMessage = input.readLine();
				break;
			}
		}
		return (serverMessage);
	}
	
	// Get food data from server
	public String getFood() throws IOException {
		// Send message to server
		output.println("foodTab" + "$$" + username);
		output.flush();
		String serverMessage = null;
		while (true) {
			if (input.ready()) {
				// Get a response from the server
				serverMessage = input.readLine();
				break;
			}
		}
		return (serverMessage);
	}
	
	// Get social data from server
	public String getSocial() throws IOException {
		// Send message to server
		output.println("socialTab" + "$$" + username);
		output.flush();
		String serverMessage = null;
		while (true) {
			if (input.ready()) {
				// Get a response from the server
				serverMessage = input.readLine();
				break;
			}
		}
		return (serverMessage);
	}

	public void stop() throws Exception {
		input.close();
		output.close();
		clientSocket.close();
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void run() {
	}
}
