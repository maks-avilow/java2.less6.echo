package lesson6Java2;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocketsocket = new ServerSocket(8189)) {
            System.out.println("Ждем подключения");
            Socket socket = serverSocketsocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            String s;
            while (true){
                s = inputStream.readUTF();
                if ("/end".equals(s)){
                    break;
                }

                    outputStream.writeUTF("Echo:  " + s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}