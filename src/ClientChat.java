
import java.util.*;
import java.net.*;
import java.io.*;

/**
 * @author Tariq
 * Client Class. Handles the clients
 */

public class ClientChat {

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);

        //Ellers portNumber = 0;
        int portNumber = 0;

        //Port menu
        System.out.println("Choose a port: \n" +
                "1: Port 9000 \n" +
                "2: Port 9001 \n" +
                "3: Port 9002 \n" +
                "4: Port 9003 \n");

        boolean valid = true;

        //Label, der bruges til at breakes ud af i switch case.
        int options = 0;
        LOOP:
        while (valid) {
            //Sætter options som brugerens næste input iform a int.

            //While loop checks if there's an int. Executes only if there's an int-value.
            //
            while(true) {
                try {
                    //'Clearing' the scanner buffer by creating a new scanner.
                    scan = new Scanner(System.in);
                    //scans 'options' for int
                    options = scan.nextInt();
                    break;
                } catch (NoSuchElementException e) {
                    System.out.println("Invalid input... \n" +
                            "Try again with a number");
                }

            }
                //Switch case. One variable --> Different
                switch (options) {

                    case 1:
                        portNumber = 9000;
                        break LOOP;

                    case 2:
                        portNumber = 9001;
                        break LOOP;

                    case 3:
                        portNumber = 9002;
                        break LOOP;

                    case 4:
                        portNumber = 9003;
                        break LOOP;

                    default:
                        System.out.println("Invalid input... \n" +
                                "Please try again with a number");

                }

            }


            try {
                Socket socket = new Socket("Localhost", portNumber);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                String input = "";
                String messages = "";
                String client;

                boolean run = true;

                    while(run) {
                        System.out.println("Enter Username: ");
                        client = bufferedReader.readLine();

                        //Regex. If statement that checks whether input matches the criteria given
                        //Stops loop if it does.
                        if(client.matches("^[a-zA-Z.\\-_]{1,15}$")) {
                            dataOutputStream.writeUTF(client);
                            run = false;
                        } else {
                            System.out.println("Invalid input. \n" +
                                    "Your input must be a letter from A-B / a-b or digit between 0-9(Max 15 char).");
                        }

                        //While loop --> Hvis input != quit --> kør program
                        //Regex --> input må bestå af lower- og uppercase bogstaver, 0-9 tal, særtegn, max 150.
                    while(!input.equalsIgnoreCase("quit")) {
                        System.out.println("Type 'quit' to exit.");
                        input = bufferedReader.readLine();

                        if(input.matches("^[0-9a-zA-Z.\\\\-_,.!?æøåÆØÅ ]{1,150}$")){
                            dataOutputStream.writeUTF(input);
                            messages = dataInputStream.readUTF();
                            System.out.println("Client says: " + messages);
                            dataOutputStream.flush();
                        } else {
                            System.out.println("Invalid Input...");
                        }
                    }

                    dataOutputStream.close();
                    socket.close();
                }

            }catch (IOException e) {
                e.printStackTrace();
            }








        }





    }

