package com.greenline.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import com.greenline.bio.Calculator;

/**
 * @Type ReadHandler
 * @Desc 服务端Handler异步回调
 * @author yangyi
 * @date 2017年8月9日
 * @Version V1.0
 */
public class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {
    // 用于读取半包消息和发送应答
    private AsynchronousSocketChannel channel;

    public ReadHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    /**
     * 服务端异步操作成功回调
     */
    public void completed(Integer result, ByteBuffer attachment) {
        System.out.println("服务端接收异步请求成功回调!");
        // flip操作
        attachment.flip();
        // 根据
        byte[] message = new byte[attachment.remaining()];
        attachment.get(message);
        try {
            String expression = new String(message, "UTF-8");
            System.out.println("服务器收到消息: " + expression);
            String calrResult = null;
            try {
                calrResult = Calculator.cal(expression).toString();
            } catch (Exception e) {
                calrResult = "计算错误：" + e.getMessage();
            }
            // 向客户端发送消息
            doWrite(calrResult);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务端响应
     * 
     * @param result
     */
    private void doWrite(String result) {
        System.out.println("服务端开始向客户端发送消息");
        byte[] bytes = result.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        // 异步写数据 参数与前面的read一样
        channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            public void completed(Integer result, ByteBuffer buffer) {
                // 如果没有发送完，就继续发送直到完成
                if (buffer.hasRemaining())
                    channel.write(buffer, buffer, this);
                else {
                    // 创建新的Buffer
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    // 异步读 第三个参数为接收消息回调的业务Handler
                    channel.read(readBuffer, readBuffer, new ReadHandler(channel));
                }
            }

            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    channel.close();
                } catch (IOException e) {
                }
            }
        });
    }
    
    /**
     * 服务端异步操作失败回调
     */
    public void failed(Throwable exc, ByteBuffer attachment) {
        System.out.println("服务端解说异步请求失败回调!");
        try {
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}