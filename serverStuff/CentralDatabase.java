package gritnessApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [CentralDatabase.java]
 * Creates the server, holds user data
 * Receives and sends data
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
    
    /**
     * CentralDatabase
     * Constructor
     */
    CentralDatabase(){}
    
    /**
     * go
     * Runs the server
     * @throws Exception
     */
    public void go() throws Exception {
        // Create a socket with the local IP address and wait for connection request
        System.out.println("Waiting for a connection request from a client ...");
        serverSocket = new ServerSocket(PORT); // Create and bind a socket
        while (true) {
            clientSocket = serverSocket.accept(); // Wait for connection request
            clientSocket.setTcpNoDelay(true);
            clientCounter = clientCounter + 1;
            System.out.println("Client " + clientCounter + " connected");
            Thread connectionThread = new Thread(new ConnectionHandler(clientSocket));
            connectionThread.start(); // Start a new thread to handle the connection
        }
    }
    
    /**
     * createNewUser
     * Creates the user's account
     * @param username Username
     * @param displayName User's display name
     * @param password User's password
     */
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
        /**
         * Run
         * Takes input data from the client and outputs data to the client
         */
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
                        String inputType = inputData[0];
                        
                        if (inputData[0].equals("stop")) {
                            input.close();
                            output.close();
                            break;
                        } else if (inputData[0].equals("signup")) {
                            String username = inputData[1];
                            String password = inputData[2];
                            if (loginInfo.containsKey(username)) {
                                output.println("Username Taken");
                                output.flush();
                            } else {
                                createNewUser(username, "", password);
                                output.println("success");
                                output.flush();
                            }
                        } else if (inputData[0].equals("login")) {
                            String username = inputData[1];
                            String password = inputData[2];
                            User user = loginInfo.get(username);
                            if (user == null) {
                                output.println("Username Not Found");
                                output.flush();
                            } else if (loginInfo.get(username).getPassword().equals(password)) {
                                output.println("success");
                                output.flush();
                            } else {
                                output.println("Wrong Password");
                                output.flush();
                            }
                        } else {      
                            String username = inputData[1];
                            User user = loginInfo.get(username);
                            
                            if (user.getHistory().size() != 0) {
                                if (!user.getToday().getDate().isEqual(LocalDate.now())) {
                                    user.addDay(new Day());
                                }
                            } else {
                                user.addDay(new Day());
                            }
                            
                            switch (inputType) {
                                case "getProfileInfo": {
                                    output.println(user.getDisplayName() + "$$" + user.getAge()  + "$$" +  user.getWeight()  + "$$" +  user.getHeight());
                                    output.flush();
                                    break;
                                }
                                case "getProfileCalHistory": {
                                    output.println(user.getCalorieHistory());
                                    output.flush();
                                    break;
                                }
                                case "getProfileWorkoutNumHistory": {
                                    output.println(user.getWorkoutNumberHistory());
                                    output.flush();
                                    break;
                                }
                                case "getNutritionTab": {
                                    output.println(user.getCalories() + "$$" + user.getProtein() + "$$" + user.getCarbs() + "$$" + user.getSugar() + "$$" + user.getFiber() + "$$" + user.getFats() + "$$" + user.getSodium());
                                    output.flush();
                                    break;
                                }
                                case "socialTab": {
                                    output.println(user.getFriendsString());
                                    output.flush();
                                    break;
                                }
                                case "sendAge": {
                                    user.setAge(Integer.parseInt(inputData[2]));
                                    break;
                                }
                                case "sendWeight": {
                                    user.setWeight(Double.parseDouble(inputData[2]));
                                    break;
                                }
                                case "sendHeight": {
                                    user.setHeight(Integer.parseInt(inputData[2]));
                                    break;
                                }
                                case "sendName": {
                                    user.setDisplayName(inputData[2]);
                                    break;
                                } 
                                case "sendCalorieGoal": {
                                    user.setCalorieGoal(Integer.parseInt(inputData[2]));
                                    break;
                                }
                                case "getDisplayName":{
                                    output.println(user.getDisplayName());
                                    output.flush();
                                    break;
                                }
                                case "getFriendUsernames" :{
                                    output.println(user.getFriendsString());
                                    output.flush();
                                    break;
                                }
                                case "getFriendData":{
                                    output.println(user.getDisplayName() + "$$" + user.getAge() + "$$" + user.getWeight() + "$$" + user.getHeight() + "$$" + user.getBMI() + "$$" + user.getBMR());
                                    output.flush();
                                    break;
                                }
                                case "sendFriendRequest":{
                                    User friend = null;
                                    friend = loginInfo.get(inputData[2]);
                                    if (friend == null) {
                                        output.println("Username not found");
                                        output.flush();
                                    } else if (user.getFriendsString().contains(inputData[2])) {
                                        output.println("Friend already added");
                                        output.flush();
                                    } else {
                                        user.addFriend(inputData[2]);
                                        output.println("success");
                                        output.flush();
                                    }
                                    break;
                                }
                                case "getCalorieGoal": {
                                    output.println(user.getCalorieGoal());
                                    output.flush();
                                    break;
                                }
                                case "sendMealInfo": {
                                    user.addCalories(Integer.parseInt(inputData[2]));
                                    user.addProtein(Integer.parseInt(inputData[3]));
                                    user.addCarbs(Integer.parseInt(inputData[4]));
                                    user.addSugar(Integer.parseInt(inputData[5]));
                                    user.addFiber(Integer.parseInt(inputData[6]));
                                    user.addFats(Integer.parseInt(inputData[7]));
                                    user.addSodium(Integer.parseInt(inputData[8]));
                                    output.println("Meal added successfully");
                                    output.flush();
                                    break;
                                }
                                case "sendWorkoutData": {
                                    user.getToday().addWorkout(inputData[2], Integer.parseInt(inputData[3]));
                                    output.println("Workout complete!");
                                    output.flush();
                                    break;
                                }
                                case "getHistoryTab": {
                                    output.println(user.getHistoryString());
                                    output.flush();
                                    break;
                                }
                                case "getDayInfo": {
                                    String dayString = inputData[2];
                                    Day day = user.getDay(dayString);
                                    output.println(day.getTotalCalories() + "$$" + day.getTotalProtein() + "$$" + day.getTotalCarbs() + "$$" + day.getTotalFats() + "$$" + day.getTotalSodium());
                                    output.flush();
                                    break;
                                }
                                case "getWorkoutDayInfo": {
                                    String dayString = inputData[2];
                                    Day day = user.getDay(dayString);
                                    String out = "";
                                    for (int i = 0; i < day.getNumberWorkouts(); i++) {
                                        out += day.getWorkouts().get(i);
                                    }
                                    output.println(out);
                                    output.flush();
                                    break;
                                }
                                default:
                                    output.println("Null Input");
                                    output.flush();
                            }
                        }
                    }
                    
                } catch (IOException e) {
                    break;
                }
            }
            
        }
    }
}