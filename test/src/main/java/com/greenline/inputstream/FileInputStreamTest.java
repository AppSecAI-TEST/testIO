package com.greenline.inputstream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

/**
 * @Type FileInputStreamTest
 * @Desc FileInputStream测试
 * @author yangyi
 * @date 2017年8月7日
 * @Version V1.0
 */
public class FileInputStreamTest {
    
    /**
     * 流的读取和写入都是以字节的形式进行的
     * 
     * @throws IOException
     */
    @Test
    public void testFileInputStream() throws IOException {
        FileInputStream fis = new FileInputStream("C:/Users/yangy/Desktop/IO流测试/aaa.txt");
        FileOutputStream fos = new FileOutputStream("C:/Users/yangy/Desktop/IO流测试/bbb.txt",true); // true指文件后面可追加
        
        int a;
        while ((a = fis.read()) != -1 ) { // read()方法返回值是int,为了防止读取到11111111时要是返回byte,会出现-1
            System.out.println(a);  // 读取的一个字节输出
            fos.write(a); // write()一次写一个字节
        }
        // 可以定义字节数组进行数据传输
        /*byte[] arr = new byte[1024 * 8];
        while((a = fis.read(arr)) != -1) {
            fos.write(arr, 0, a);
        }*/

        // 关流
        fis.close();
        fos.close();
    }
    
}
