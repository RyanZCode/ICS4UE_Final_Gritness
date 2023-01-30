package gritnessApp;

//imports for network communication
import java.io.*;
import java.net.*;
import java.time.Period;
import java.util.*;

/**
 * CentralDatabase is the server of the app
 * 
 * @author Ryan Zhou
 * @version Jan 2023
 */
class CentralDatabase {
	final int PORT = 10111;
	ServerSocket serverSocket;
	Socket clientSocket;
	PrintWriter output;
	BufferedReader input;
	int clientCounter = 0;
	HashMap<String, User> loginInfo = new HashMap<String, User>();

	/**
	 * main
	 * Creates a server and starts it
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		CentralDatabase server = new CentralDatabase();
		server.go();
	}

	public void go() throws Exception {
		// Create a socket with the local IP address and wait for connection request
		System.out.println("Waiting for a connection request from a client ...");
		serverSocket = new ServerSocket(PORT); // Create and bind a socket
		while (true) {
			clientSocket = serverSocket.accept(); // Cait for connection request
			clientSocket.setTcpNoDelay(true);
			clientCounter = clientCounter + 1;
			System.out.println("Client " + clientCounter + " connected");
			Thread connectionThread = new Thread(new ConnectionHandler(clientSocket));
			connectionThread.start(); // Start a new thread to handle the connection
		}
	}

	public void createNewUser(String username, String displayName, String password) {
		loginInfo.put(username, new User(username, displayName, password));
	}

	class ConnectionHandler extends Thread {
		Socket socket;
		PrintWriter output;
		BufferedReader input;
		String data;
		Queue<String> dataQ = new LinkedList<String>();

		public ConnectionHandler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				output = new PrintWriter(socket.getOutputStream());
				// receive a message from the client
			} catch (IOException e) {
				e.printStackTrace();
			}
			while (true) {
				try {
					if (input.ready()) {
						data = input.readLine();
						dataQ.add(data);
					}
					if (!dataQ.isEmpty()) {
						data = dataQ.poll();
						String[] inputData = data.split("\\$+");
						System.out.println(Arrays.toString(inputData));
						String inputType = inputData[0];
						System.out.println("type: " + inputType);
						switch (inputType) {
						case "signup": {
							//String displayName = inputData[1];
							String displayName = "Tester";
							// need to make displayname field
							String username = inputData[1];
							String password = inputData[2];
							if (loginInfo.containsKey(username)) {
								output.println("username taken");
								output.flush();
							} else {
								createNewUser(username, displayName, password);
								output.println("success");
								output.flush();
							}
							break;
						}
						case "login": {
							String username = inputData[1];
							String password = inputData[2];
							User user = loginInfo.get(username);
							if (user == null) {
								output.println("username not found");
								output.flush();
							} else if (loginInfo.get(username).getPassword().equals(password)) {
								output.println("success");
								output.flush();
							} else {
								output.println("wrong password");
								output.flush();
							}
							break;
						}
						case "profileTab": {
							String username = inputData[1];
							User user = loginInfo.get(username);
							output.println(user.getDisplayName() + "$$" + user.getAge()  + "$$" +  user.getWeight()  + "$$" +  user.getHeight());
							output.flush();
							break;
						}
						case "profileCalHistory": {
							String username = inputData[1];
							User user = loginInfo.get(username);
							output.println(user.getCalorieHistory());
							System.out.println(user.getCalorieHistory());
							output.flush();
							break;
						}
						case "profileWorkoutNum": {
							String username = inputData[1];
							User user = loginInfo.get(username);
							output.println(user.getWorkoutNumberHistory());
							output.flush();
							break;
						}
						case "historyTab": {
							String username = inputData[1];
							User user = loginInfo.get(username);
							break;
							
						}
						case "workoutTab": {
							String username = inputData[1];
							User user = loginInfo.get(username);
							break;
						}
						case "foodTab": {
							String username = inputData[1];
							User user = loginInfo.get(username);
							break;
						}
						case "socialTab": {
							String username = inputData[1];
							User user = loginInfo.get(username);
							output.println(user.getFriendsString());
							output.flush();
							break;
						}
						case "sendAge": {
							String username = inputData[1];
							User user = loginInfo.get(username);
							user.setAge(Integer.parseInt(inputData[2]));
							System.out.println("age is " + user.getAge());
							break;
						}
						case "sendWeight": {
							String username = inputData[1];
							User user = loginInfo.get(username);
							user.setWeight(Double.parseDouble(inputData[2]));
							System.out.println("weight is " + user.getWeight());
							break;
						}
						case "sendHeight": {
							String username = inputData[1];
							User user = loginInfo.get(username);
							user.setHeight(Integer.parseInt(inputData[2]));
							System.out.println("height is " + user.getHeight());
							break;
						}
						case "sendName": {
							String username = inputData[1];
							User user = loginInfo.get(username);
							user.setDisplayName(inputData[2]);
							System.out.println("name is " + user.getDisplayName());
							break;
						}
						case "test": {
							String username = inputData[1];
							User user = loginInfo.get(username);
							Day day = new Day();
							day.addWorkout("testing");
							day.setTotalCalories(2000);
							user.addDay(day);
							Day day2 = new Day();
							day2.addWorkout("testing");
							day2.setTotalCalories(2000);
							day2.setDate(day2.getDate().minus(Period.ofDays(-1)));
							user.addDay(day2);
							Day day3 = new Day();
							day3.addWorkout("testing");
							day3.setTotalCalories(1000);
							day3.setDate(day3.getDate().minus(Period.ofDays(-2)));
							user.addDay(day3);
							output.println("testing");
							output.flush();
							System.out.println("tested");
							break;
						}
						default:
							output.println("null input");
							output.flush();
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}