package learning.com.TCP;

import javafx.concurrent.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 服务端
 */
public class SocketTCP01Server {
    public static void main(String[] args) throws IOException {
//        在本机999端口监听，此时其他服务不能监听99端口，否则监听失败
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端在9999端口监听，等待连接....");
//        如有客户端连接，则返回socket对象，可以返回多个socket对象
        Socket accept = serverSocket.accept();

        System.out.println("服务端 socket = "+ accept.getClass());
//        通过socket，获取输入流
        InputStream inputStream = accept.getInputStream();
        byte[] buff = new byte[1024];
        int readlen=0;
        while((readlen=inputStream.read(buff))!=-1){
            System.out.println(new String(buff,0,readlen));
        }

        accept.shutdownInput();

        OutputStream outputStream = accept.getOutputStream();
        outputStream.write("hello,Client!".getBytes(StandardCharsets.UTF_8));
        accept.shutdownOutput();

        System.out.println("服务端退出...");
        outputStream.close();
        inputStream.close();
        accept.close();


    }
}
