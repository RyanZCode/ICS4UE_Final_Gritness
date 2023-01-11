package gritnessApp;

//imports for network communication
import java.io.*;
import java.net.*;
import java.util.*;


class CentralDatabase {
    final int PORT = 5000;       
    
    ServerSocket serverSocket;
    Socket clientSocket;
    PrintWriter output;
    BufferedReader input;
    int clientCounter = 0;
    HashMap<User, String> loginInfo = new HashMap<User, String>();
    public static void main(String[] args) throws Exception{ 
    	CentralDatabase server = new CentralDatabase();
        server.go();
    }
    
    public void go() throws Exception{ 
        //create a socket with the local IP address and wait for connection request       
        System.out.println("Waiting for a connection request from a client ...");
        serverSocket = new ServerSocket(PORT);                //create and bind a socket
        while(true) {
            clientSocket = serverSocket.accept();             //wait for connection request
            clientSocket.setTcpNoDelay(true);
            clientCounter = clientCounter +1;
            System.out.println("Client "+clientCounter+" connected");
            Thread connectionThread = new Thread(new ConnectionHandler(clientSocket));
            connectionThread.start();                         //start a new thread to handle the connection
        }
    }
    
    public void createNewUser(String username, String displayName, String password) {
    	User newUser = new User(username, displayName);
    	loginInfo.put(newUser, password);
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
                //receive a message from the client
//                data = input.readLine();
               
                
//                System.out.println("Message from the client: " + msg);
//                //send a response to the client
//                output.println("Client "+clientCounter+", you are connected!");
//                output.flush();         
//                //after completing the communication close the streams but do not close the socket!
//                input.close();
//                output.close();
            }catch (IOException e) {e.printStackTrace();}
            while(true) {
            	try {
            		if(input.ready()) {
            			data = input.readLine();
            			dataQ.add(data);
            		}
            		if(!dataQ.isEmpty()) {
            			data = dataQ.poll();
            			String[] inputData = data.split(" ");
                        String inputType = inputData[0];
                        if(inputType.equals("signup")) {
                        	String displayName = inputData[1];
                            String username = inputData[2];
                            String password = inputData[3];
                            createNewUser(username, displayName, password);
                            
                        }
                        
            		}
            		
            	}catch(IOException e){
            		e.printStackTrace();
            	}
            }
            
        }
    }    
}
