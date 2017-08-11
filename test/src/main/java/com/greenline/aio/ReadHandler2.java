package com.greenline.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @Type ReadHandler2
 * @Desc 客户端异步回调
 * @author yangyi
 * @date 2017年8月10日
 * @Version V1.0
 */
public class ReadHandler2 implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel clientChannel;
    private CountDownLatch latch;

    public ReadHandler2(AsynchronousSocketChannel clientChannel, CountDownLatch latch) {
        this.clientChannel = clientChannel;
        this.latch = latch;
    }
    
    /**
     * 客户端异步成功回调
     */
    public void completed(Integer result, ByteBuffer buffer) {
        System.out.println("客户端接收响应成功回调!");
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        String body;
        try {
            body = new String(bytes, "UTF-8");
            System.out.println("客户端收到结果:" + body);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 客户端异步失败回调
     */
    public void failed(Throwable exc, ByteBuffer attachment) {
        System.err.println("数据读取失败...");
        try {
            clientChannel.close();
            latch.countDown();
        } catch (IOException e) {
        }
    }
}