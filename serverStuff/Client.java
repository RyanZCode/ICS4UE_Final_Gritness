package gritnessApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [Client.java]
 * This class obtains information and data from server
 * @author Ryan Zhou
 * @author Jason Wu
 * @version 1.0 Jan 24, 2023
 */
public class Client implements Runnable {
	final String LOCAL_HOST = "10.0.0.7";
	final int PORT = 10111;
	Socket clientSocket;
	PrintWriter output;
	BufferedReader input;
	String serverData;
	Queue<String> dataQ = new LinkedList<>();
	private String username;

	/**
	 * Client
	 * Client constructor
	 */
	Client() {
	}

	/**
	 * start
	 * Connects to the server
	 * @throws Exception
	 */
	public void start() throws Exception {
		// create a socket with the local IP address and attempt a connection
		System.out.println("Connecting to server...");

		// create and bind a socket, and request connection
		clientSocket = new Socket(LOCAL_HOST, PORT);
		output = new PrintWriter(clientSocket.getOutputStream());
		input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		System.out.println("Connected successfully!");

		try {Thread.sleep(5);} catch (InterruptedException e) {throw new RuntimeException(e);}
	}

	/**
	 * sendLogin
	 * Sends the login data to the server and receives whether successful or not
	 * @param username User's name
	 * @param password User's password
	 * @return successful or unsuccessful login
	 * @throws IOException
	 */
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

	/**
	 * sendSignUp
	 * Sends signup data to server and receives whether signup was successful or not
	 * @param username User's username
	 * @param password User's password
	 * @return successful or unsuccessful signup
	 * @throws IOException
	 */
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
	
	/**
	 * sendAge
	 * Sends user's age to server
	 * @param age User's age
	 */
	public void sendAge(int age) {
		// Send message to server
		output.println("sendAge" + "$$" + username + "$$" + age);
		output.flush();
	}
	

	/**
	 * sendWeight
	 * Sends user's weight to server
	 * @param weight User's weight
	 */
	public void sendWeight(double weight) {
		// Send message to server
		output.println("sendWeight" + "$$" + username + "$$" + weight);
		output.flush();
	}
	
	/**
	 * sendHeight
	 * Sends user's height to server
	 * @param height User's Height
	 */
	public void sendHeight(int height) {
		// Send message to server
		output.println("sendHeight" + "$$" + username + "$$" + height);
		output.flush();
	}
	
	/**
	 * sendName
	 * Sends user's display name to server
	 * @param name User's display name
	 */
	public void sendName(String name) {
		// Send message to server
		output.println("sendName" + "$$" + username + "$$" + name);
		output.flush();
	}
	
	/**
	 * sendCalorieGoal
	 * Sends user's calorie goal to server
	 * @param calorieGoal User's calorie goal
	 */
	public void sendCalorieGoal(int calorieGoal) {
		// Send message to server
		output.println("sendCalorieGoal" + "$$" + username + "$$" + calorieGoal);
		output.flush();
	}
	
	/**
	 * getProfileInfo
	 * Gets profile data from server
	 * @return sent profile data
	 * @throws IOException
	 */
	public String getProfileInfo() throws IOException {
		// Send message to server
		output.println("getProfileInfo" + "$$" + username);
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
	
	/**
	 * getProfileCalHistory
	 * Gets user's calorie history from server
	 * @param username User's display name
	 * @return sent calorie history
	 * @throws IOException
	 */
	public String getProfileCalHistory(String username) throws IOException {
		// Send message to server
		output.println("getProfileCalHistory" + "$$" + username);
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
	
	
	/**
	 * getProfileWorkoutNumHistory
	 * Gets user's workout history from server
	 * @param username User's display name
	 * @return sent workout history
	 * @throws IOException
	 */
	public String getProfileWorkoutNumHistory(String username) throws IOException {
		// Send message to server
		output.println("getProfileWorkoutNumHistory" + "$$" + username);
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
	
	/**
	 * getUsername
	 * Get user's username
	 * @return username
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * getDisplayName
	 * Gets user's display name from server
	 * @return sent display name
	 * @throws IOException
	 */
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
	
	/**
	 * getFriendData
	 * Gets friends data from server
	 * @param friendUsername Friend's display name
	 * @return sent friends data name
	 * @throws IOException
	 */
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
	
	/**
	 * getFriendUsernames
	 * Gets friends display name from server
	 * @return sent friends data name
	 * @throws IOException
	 */
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
	
	/**
	 * sendFriendRequest
	 * Sends friend request to server
	 * @param friendUsername Friend's display name
	 * @return request
	 * @throws IOException
	 */
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
	
	/**
	 * sendWorkoutData
	 * Sends workout data to the server
	 * @param workoutName Name of workout
	 * @param elapsedTime Time taken for workout
	 * @return sent workout data
	 * @throws IOException
	 */
	public String sendWorkoutData(String workoutName, int elapsedTime) throws IOException {
		// Send message to server
		output.println("sendWorkoutData" + "$$" + username + "$$" + workoutName + "$$" + elapsedTime);
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
	
	/**
	 * getHistory
	 * Gets history from server
	 * @return history
	 * @throws IOException
	 */
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
	
	/**
	 * getWorkouts
	 * Gets workout data from server
	 * @return workout data
	 * @throws IOException
	 */
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
	
	/**
	 * getFood
	 * Gets food data from server
	 * @return food data
	 * @throws IOException
	 */
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
	
	/**
	 * getSocial
	 * Gets social data from server
	 * @return social data
	 * @throws IOException
	 */
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
	
	/**
	 * getCalorieGoal
	 * Gets calorie goal from server
	 * @return calorie goal
	 * @throws IOException
	 */
	public String getCalorieGoal() throws IOException {
		// Send message to server
		output.println("getCalorieGoal" + "$$" + username);
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
	
    /**
     * sendMealInfo
     * Sends meal information to server
     * @param calories Calories
     * @param protein Protein
     * @param carbs Carbs
     * @param sugar Sugar
     * @param fiber Fiber
     * @param fats Fats
     * @param sodium Sodium
     * @return Meal information
     * @throws IOException
     */
 	public String sendMealInfo(String calories, String protein, String carbs, String sugar, String fiber, String fats, String sodium) throws IOException {
 		// Send message to server
 		output.println("sendMealInfo" + "$$" + username + "$$" + calories + "$$" + protein + "$$" + carbs + "$$" + sugar + "$$" + fiber + "$$" + fats + "$$" + sodium);
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
 	
	/**
	 * getNutritionTab
	 * Gets nutrition tab information from server
	 * @return nutrition tab information
	 * @throws IOException
	 */
  	public String getNutritionTab() throws IOException {
  		// Send message to server
  		output.println("getNutritionTab" + "$$" + username);
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
  	
	/**
	 * getHistoryTab
	 * Gets history tab information from server
	 * @return history tab information
	 * @throws IOException
	 */
  	public String getHistoryTab() throws IOException {
  		// Send message to server
  		output.println("getHistoryTab" + "$$" + username);
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
  	
  	/**
  	 * getDayInfo
  	 * Gets day information from server
  	 * @param dayString Day
  	 * @return selected day information
  	 * @throws IOException
  	 */
   	public String getDayInfo(String dayString) throws IOException {
   		// Send message to server
   		output.println("getDayInfo" + "$$" + username + "$$" + dayString);
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
   	
   	/**
   	 * getWorkoutDayInfo
   	 * Gets workout information in current day from server
   	 * @param dayString Day
   	 * @return workout information in current day
   	 * @throws IOException
   	 */
   	public String getWorkoutDayInfo(String dayString) throws IOException {
   		// Send message to server
   		output.println("getWorkoutDayInfo" + "$$" + username + "$$" + dayString);
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
	
	/**
	 * sendStop
	 * Tells server to stop IO
	 */
	public void sendStop() {
		output.println("stop");
		output.flush();
	}

	/**
	 * stop
	 * Closes all inputs and outputs and client
	 * @throws Exception
	 */
	public void stop() throws Exception {
		input.close();
		output.close();
		clientSocket.close();
	}
	
	/**
	 * setUsername
	 * Sets username
	 * @param username Username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * run
	 * Run
	 */
	@Override
	public void run() {
	}
}