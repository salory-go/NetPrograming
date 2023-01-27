package learning.com.TCP;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketTCP01Client {
    public static void main(String[] args) throws IOException {
//        连接本机9999端口
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);

        System.out.println("socket 连接成功...");
        System.out.println("客户端 socket 返回 = "+socket.getClass());
//        得到与socket有关的输出流
        OutputStream outputStream = socket.getOutputStream();
//        也可以包装成字符流
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
//        writer.write("hello,server!");
//        writer.newLine();
//        writer.flush();
//        socket.shutdownOutput();
//        writer.close();
//        写入数据
        outputStream.write("hello,server!".getBytes(StandardCharsets.UTF_8));

        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        byte[] buff = new byte[1024];
        int readlen=0;
        while((readlen=inputStream.read(buff))!=-1){
            System.out.println(new String(buff,0,readlen));
        }

        socket.shutdownInput();
        inputStream.close();
        outputStream.close();
        socket.close();
//        writer.close();
        System.out.println("客户端退出...");
    }
}
