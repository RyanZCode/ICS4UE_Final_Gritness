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
	final String LOCAL_HOST = "localhost";
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
	
	// Sends user age to server
	public void sendAge(int age) {
		// Send message to server
		output.println("sendAge" + "$$" + username + "$$" + age);
		output.flush();
	}
	
	// Sends user weight to server
	public void sendWeight(double weight) {
		// Send message to server
		output.println("sendWeight" + "$$" + username + "$$" + weight);
		output.flush();
	}
	
	// Sends user height to server
	public void sendHeight(int height) {
		// Send message to server
		output.println("sendHeight" + "$$" + username + "$$" + height);
		output.flush();
	}
	
	// Sends user display name to server
	public void sendName(String name) {
		// Send message to server
		output.println("sendName" + "$$" + username + "$$" + name);
		output.flush();
	}
	
	// Sends user calorie goal to server
	public void sendCalorieGoal(int calorieGoal) {
		// Send message to server
		output.println("sendCalorieGoal" + "$$" + username + "$$" + calorieGoal);
		output.flush();
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
	public String getProfileCalHistory(String username) throws IOException {
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
	
	// Get username
	public String getUsername() {
		return this.username;
	}
	
	// Get profile data from server
	public String getProfileWorkoutNumHistory(String username) throws IOException {
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
	
	// Get display name from server
	public String getDisplayName() throws IOException {
		// Send message to server
		output.println("getDisplayName" + "$$" + username);
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
	
	// Get friend's display name from server
	public String getFriendData(String friendUsername) throws IOException {
		// Send message to server
		output.println("getFriendData" + "$$" + friendUsername);
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
	
	// Get friend usernames from server
	public String getFriendUsernames() throws IOException {
		// Send message to server
		output.println("getFriendUsernames" + "$$" + username);
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
	
	// Send a friend request to server
	public String sendFriendRequest(String friendUsername) throws IOException {
		// Send message to server
		output.println("sendFriendRequest" + "$$" + username + "$$" + friendUsername);
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
	
	public String sendTest() throws IOException {
		output.println("test" + "$$" + username);
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
	
	// Tells server to close I/O
	public void sendStop() {
		output.println("stop");
		output.flush();
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
