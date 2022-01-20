package lesson6Java2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    private  DataOutputStream outputStream;
    private  Socket socket;
    private  DataInputStream inputStream;
    private Scanner scanner;

    public static void main(String[] args) {
          new EchoClient();
    }

    public EchoClient() {
        scanner = new Scanner(System.in);
        openConnectoin();
        while(true){
            sendMessage();
        }
    }

    private void openConnectoin() {
        try {
            socket = new Socket("localhost", 8189);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            final String msgFromServer = inputStream.readUTF();
                            System.out.println(msgFromServer);
                        }
                    } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            closeConnection();
                        }
                    }
                }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendMessage(){
     final String s = scanner.nextLine();
        try {
            outputStream.writeUTF(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
