import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner readConsole = new Scanner(System.in);
        Socket socket;
        ObjectOutputStream out;
        ObjectInputStream in;
        while(true) {
            System.out.print("Enter Server Address: ");
            String serverAddress = readConsole.nextLine();
            System.out.print("Enter Port Number: ");
            String serverPort = readConsole.nextLine();
            int portNumber = 0;
            try {
                portNumber = Integer.parseInt(serverPort);
            } catch (Exception e) {
                System.out.println("ERROR: Port must be a positive integer");
                continue;
            }
            if (portNumber < 1) {
                System.out.println("ERROR: Port must be a positive integer");
                continue;
            }            
            try {
                socket = new Socket(serverAddress, portNumber);
                break;
            } catch (Exception e) {
                System.out.println("ERROR: "+e.getMessage()+"");
                continue;
            }
        }
        System.out.println("CONNECTED!");
        try {
            System.out.println(socket);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            while (true) {
                System.out.print("Write a message to send or /END to close connection: ");
                String response = readConsole.nextLine();
                Message message = new Message(response);
                try {
                    if (message.toString().equals("/END")) {
                        break;
                    } else {
                        System.out.println("SENDING: "+message);
                        out.writeObject(message);
                        System.out.println("RECEIVED: "+in.readObject());
                    }
                } catch (Exception e) {
                    System.out.println("ERROR: Server has been disconnected");
                    break;
                }
            }
    
            try {
                socket.close();
                System.out.print("CONNECTION CLOSED");
            } catch (IOException e) {
            }
            readConsole.close();
        } catch (IOException e) {
            System.out.println("a");
        }        
    }
}