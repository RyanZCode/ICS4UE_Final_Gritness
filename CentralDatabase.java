package gritnessApp;

//imports for network communication
import java.io.*;
import java.net.*;
import java.util.*;

// Jason
class CentralDatabase {
	final int PORT = 10111;

	ServerSocket serverSocket;
	Socket clientSocket;
	PrintWriter output;
	BufferedReader input;
	int clientCounter = 0;
	HashMap<String, User> loginInfo = new HashMap<String, User>();

	public static void main(String[] args) throws Exception {
		CentralDatabase server = new CentralDatabase();
		server.go();
	}

	public void go() throws Exception {
		// create a socket with the local IP address and wait for connection request
		System.out.println("Waiting for a connection request from a client ...");
		serverSocket = new ServerSocket(PORT); // create and bind a socket
		while (true) {
			clientSocket = serverSocket.accept(); // wait for connection request
			clientSocket.setTcpNoDelay(true);
			clientCounter = clientCounter + 1;
			System.out.println("Client " + clientCounter + " connected");
			Thread connectionThread = new Thread(new ConnectionHandler(clientSocket));
			connectionThread.start(); // start a new thread to handle the connection
		}
	}

	public void createNewUser(String username, String displayName, String password) {
		loginInfo.put(username, new User(username, displayName, password));
	}

//------------------------------------------------------------------------------
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
						String[] inputData = data.split("\\$\\$");
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
							output.println(user.getCalorieHistory());
							output.println(user.getWorkoutNumberHistory());
						}
						case "profileCalHistory": {
							String username = inputData[1];
							User user = loginInfo.get(username);
							output.println(user.getCalorieHistory());
						}
						case "profileWorkoutNum": {
							String username = inputData[1];
							User user = loginInfo.get(username);
							output.println(user.getWorkoutNumberHistory());
						}
						case "historyTab": {
							String username = inputData[1];
							User user = loginInfo.get(username);
							
						}
						case "workoutTab": {
							String username = inputData[1];
							User user = loginInfo.get(username);
						}
						case "foodTab": {
							String username = inputData[1];
							User user = loginInfo.get(username);
						}
						case "socialTab": {
							String username = inputData[1];
							User user = loginInfo.get(username);
							output.println(user.getFriendsString());
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