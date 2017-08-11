package com.greenline.reader;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

/**
 * @Type FileReaderTest
 * @Desc 字符流测试
 * @author yangyi
 * @date 2017年8月8日
 * @Version V1.0
 */
public class FileReaderTest {
    
    /**
     * 字节流读取或写入时会出现中文乱码问题
     * 
     * @throws IOException
     */
    @Test
    public void testFileInputStream() throws IOException {
        FileInputStream fis = new FileInputStream("C:/Users/yangy/Desktop/IO流测试/ccc.txt");
        
        int a;
        // 可以定义字节数组进行数据传输
        byte[] arr = new byte[3];
        while((a = fis.read(arr)) != -1) {
            System.out.println(new String(arr, 0, a));
        }

        // 关流
        fis.close();
    }
    
    /**
     * 使用字符流可以解决编码问题
     * 
     * @throws IOException
     */
    @Test
    public void testFileReader() throws IOException {
        FileReader fr = new FileReader("C:/Users/yangy/Desktop/IO流测试/ccc.txt");
        
        int a;
        // 可以定义字节数组进行数据传输
        while((a = fr.read()) != -1) {
            System.out.println((char)a);
        }

        // 关流
        fr.close();
    }
    
    /**
     * 字符流只能用来处理纯文本文件
     * 
     * @throws IOException
     */
    @Test
    public void testFileReader2() throws IOException {
        /*FileReader fr = new FileReader("C:/Users/yangy/Desktop/IO流测试/ccc.txt");
        FileWriter fw = new FileWriter("C:/Users/yangy/Desktop/IO流测试/ddd.txt");*/
        FileReader fr = new FileReader("C:/Users/yangy/Desktop/IO流测试/1.png");
        FileWriter fw = new FileWriter("C:/Users/yangy/Desktop/IO流测试/2.png");
        int a;
        // 可以定义字节数组进行数据传输
        while((a = fr.read()) != -1) {
            fw.write(a);
        }

        // 关流
        fr.close();
        fw.close();
    }
}
