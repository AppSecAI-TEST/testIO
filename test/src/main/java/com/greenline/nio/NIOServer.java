package com.greenline.nio;

/**
 * @Type NIOServer
 * @Desc NIO服务端
 * @author yangyi
 * @date 2017年8月9日
 * @Version V1.0
 */
public class NIOServer {
    private static int DEFAULT_PORT = 12345;
    private static ServerHandle serverHandle;

    public static void start() {
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int port) {
        if (serverHandle != null)
            serverHandle.stop();
        serverHandle = new ServerHandle(port);
        new Thread(serverHandle, "Server").start();
    }

    public static void main(String[] args) {
        start();
    }
}