package learning.com.TCP;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

//传输图片
public class SocketTCP02Client {
    public static void main(String[] args) throws Exception {
//        连接本机9999端口
        Socket socket = new Socket(InetAddress.getLocalHost(),8888);

        System.out.println("socket 连接成功...");
        System.out.println("客户端 socket 返回 = "+socket.getClass());
//        得到与socket有关的输出流
        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        String filepath = "Client.png";
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filepath));
        byte[] picture = StreamUtils.streamToButeArray(bufferedInputStream);
        bufferedOutputStream.write(picture);
        bufferedInputStream.close();
        socket.shutdownOutput();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String accept = reader.readLine();
        System.out.println(accept);
        socket.shutdownInput();
        reader.close();


        System.out.println("客户端退出...");
    }
}
