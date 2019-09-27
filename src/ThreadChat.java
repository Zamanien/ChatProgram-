
import java.io.*;
import java.net.*;
import java.util.logging.*;

/**
 * @author Tariq
 *
 */

public class ThreadChat implements Runnable{
    String name;
    int portNumber;

    public ThreadChat(String name, int port) {
        name = "";
        portNumber = port;


    }

    /**
     * @Override
     * used to override the "run" method in the interface: "Runnable".
     * Method also creates a log file to keep track of transactions between clients and server.
     */

    //Implements and Executes the "run" method from the Runnable Interface.
    @Override
    public void run(){
        ServerSocket serverSocket;
        try{
            serverSocket = new ServerSocket(portNumber);
            //Accepts the client
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            //Initiates and retrieves the output- and inputstream of the socket
            DataInputStream dis =   new DataInputStream(socket.getInputStream());
            DataOutputStream dos =  new DataOutputStream(socket.getOutputStream());
            //Method used to log transactions
            Logger log = Logger.getLogger("LogTranscription");
            //Saves the log to path given
            FileHandler fileHandler = new FileHandler("C:\\Users\\Tariq\\Google Drev\\KEA - Datamatiker - 3. Semester\\Projects\\Chat-Message Project\\Code\\ChatProgram\\Log", true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            //Formattering af logfilen.
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            //Following method exports the log data into a logfile. Takes fileHandler as parameter.
            log.addHandler(fileHandler);
            String messages = "";
            String client;
            client = dis.readUTF();

            System.out.println("Username: " + client);


            String msg = "";

            //loop. Kører så længe msg =/= "quit".
            while(!msg.equalsIgnoreCase("quit")){
                msg = dis.readUTF();
                System.out.println(client + "says: " + msg);
                messages = msg;
                dos.writeUTF(messages);
                log.info(client + ": " + msg);
                dos.flush();

            }

            dis.close();
            socket.close();
            serverSocket.close();

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
