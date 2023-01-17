package gritnessApp;

//imports for network communication
import java.io.*;
import java.net.*;
import java.util.*;

// Jason
class CentralDatabase {
	final int PORT = 5000;

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
						String[] inputData = data.split(" ");
						String inputType = inputData[0];
						switch (inputType) {
						case "signup": {
							String displayName = inputData[1];
							String username = inputData[2];
							String password = inputData[3];
							if (loginInfo.containsKey(username)) {
								output.println("username taken");
							} else {
								createNewUser(username, displayName, password);
								output.println("success");
							}
							break;
						} case "login": {
							String username = inputData[0];
							String password = inputData[1];
							User user = loginInfo.get(username);
							if (user == null) {
								output.println("username not found");
							} else if (loginInfo.get(username).getPassword().equals(password)) {
								output.println("success");
							} else {
								output.println("wrong password");
							}
							break;
						} 
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}