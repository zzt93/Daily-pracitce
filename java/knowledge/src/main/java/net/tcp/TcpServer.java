package net.tcp;

import com.google.common.primitives.Bytes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 17/3/24.
 */
public class TcpServer {

    private static final int MAX = 1000;
    private ServerSocket server = new ServerSocket(9000);

    public TcpServer() throws IOException {
    }

    private void simpleReply() throws IOException {
        Socket socket = server.accept();
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        byte[] bytes = new byte[MAX];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            byte[] request = Arrays.copyOf(bytes, len);
            System.out.println(Arrays.toString(request));
            outputStream.write(Bytes.concat("server received: ".getBytes(), request));
        }
    }

    void reply() {
        try {
            simpleReply();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TcpServer tcpServer = new TcpServer();
        TcpClient tcpClient = new TcpClient();
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(tcpClient::send);
        service.execute(tcpServer::reply);
        service.shutdown();
    }
}
