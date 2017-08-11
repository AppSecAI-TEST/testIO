package com.greenline.buffered;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

/**
 * @Type BufferedInputStreamTest
 * @Desc 缓冲流测试类
 * @author yangyi
 * @date 2017年8月8日
 * @Version V1.0
 */
public class BufferedInputStreamTest {
    
    /**
     * 字节流读取数据
     * 
     * @throws IOException
     */
    @Test
    public void testFileInputStream() throws IOException {
        FileInputStream fis = new FileInputStream("C:/Users/yangy/Desktop/IO流测试/1.png");
        FileOutputStream fos = new FileOutputStream("C:/Users/yangy/Desktop/IO流测试/2.png");
        
        Long start = System.currentTimeMillis();
        
        int a;
        // 一次读取一个字节
        /*while ((a = fis.read()) != -1 ) {
            fos.write(a);
        }*/
        
        // 可以定义字节数组进行数据传输
        byte[] arr = new byte[1024 * 8];
        while((a = fis.read(arr)) != -1) {
            fos.write(arr, 0, a);
        }
        
        // 关流
        fis.close();
        fos.close();
        
        Long end = System.currentTimeMillis();
        System.out.println("数据传输使用时间为: " + (end - start) + "毫秒");
    } 
    
    @Test
    public void testBufferedInputStream() throws IOException {
        
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:/Users/yangy/Desktop/IO流测试/1.png"));
        BufferedOutputStream bos = new BufferedOutputStream (new FileOutputStream("C:/Users/yangy/Desktop/IO流测试/2.png"));
        
        Long start = System.currentTimeMillis();
        
        int a;
        while ((a = bis.read()) != -1 ) {
            bos.write(a);
        }
        
        // 关流
        bis.close();
        bos.close();
        
        Long end = System.currentTimeMillis();
        System.out.println("数据传输使用时间为: " + (end - start) + "毫秒");
    } 
}
