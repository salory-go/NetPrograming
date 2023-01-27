package learning.com.TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 服务端
 */
/**
 * 接收图片
 */
public class SocketTCP02Server {
    public static void main(String[] args) throws Exception {
//        在本机999端口监听，此时其他服务不能监听99端口，否则监听失败
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务端在9999端口监听，等待连接....");
//        如有客户端连接，则返回socket对象，可以返回多个socket对象
        Socket accept = serverSocket.accept();

        System.out.println("服务端 socket = "+ accept.getClass());
        BufferedInputStream bufferedInputStream = new BufferedInputStream(accept.getInputStream());
        byte[] picture = StreamUtils.streamToButeArray(bufferedInputStream);
        String filepath = "Server.png";
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filepath));
        bufferedOutputStream.write(picture);
        accept.shutdownInput();
        bufferedOutputStream.close();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        writer.write("收到");
        writer.close();


        System.out.println("服务端退出...");
        accept.close();


    }
}
