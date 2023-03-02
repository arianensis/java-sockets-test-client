import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner readConsole = new Scanner(System.in);
        Socket socket;
        DataOutputStream out;
        DataInputStream in;
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
            in = new DataInputStream(socket.getInputStream())
            out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                System.out.print("Enter number in range [0-255] to send, or a negative to end: ");
                String response = readConsole.nextLine();
                int message = 256;
                try {
                    message = Integer.parseInt(response);
                    if (message > 255) {
                        System.out.println("ERROR: Input must be an integer lower than 256");   
                        continue;                 
                    } else if (message < 0) {
                        break;
                    } else {
                        System.out.println("SENDING: "+message);
                        out.writeByte(message);
                        System.out.println("RECEIVED: "+in.readByte());
                    }
                } catch (Exception e) {
                    System.out.println("ERROR: Input must be an integer lower than 256");
                    continue;
                }
            }
    
            try {
                socket.close();
                System.out.print("CONNECTION CLOSED");
            } catch (IOException e) {
            }
            readConsole.close();
        } catch (IOException e) {
        }        
    }
}