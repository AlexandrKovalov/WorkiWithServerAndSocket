import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private static ServerSocket server;
    private static Socket socket;
    private static final int PORT = 7777;

    public static void main(String[] args) {


        try {
            server = new ServerSocket(PORT);
            System.out.println("Сервер успешно запущен");
            socket = server.accept();
            System.out.println("Клиент успешно соединился");


            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

            while (true) {
                String message = in.nextLine();


                if ("/exit".equals(message)) {
                    System.out.println("Клиент отключился");
                    break;
                }
                out.println("Server: " + message);
                System.out.println("Client: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           try {
               socket.close();
               server.close();
           } catch (IOException e) {
               //игнорируем ошибку, потому что если сокет не удалось закрыть значит он уже закрыт
           }
        }

    }

}
