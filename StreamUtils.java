package learning.com.TCP;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamUtils {
    public static byte[]  streamToButeArray(InputStream is)throws Exception{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[]  b = new byte[1024];
        int len;
        while((len=is.read(b))!=-1){
            bos.write(b,0,len);
        }
        byte[] arry = bos.toByteArray();
        bos.close();
        return arry;
    }
}
