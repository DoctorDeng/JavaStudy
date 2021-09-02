package concurrent;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 使用 Thread-Per-Message 模式实现的简单 echo 服务, echo 服务会将请求的数据原样返回给请求端.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/8/25 15:16
 */
public class EchoServerThreadImpl {
    public static void main(String[] args) throws IOException {
    final ServerSocketChannel ssc = ServerSocketChannel.open().bind(new InetSocketAddress(8080));
        //处理请求
        try {
            while (true) {
                // 接收请求
                SocketChannel sc = ssc.accept();
                // 每个请求都创建一个线程
                new Thread(()->{
                    try {
                        // 读Socket
                        ByteBuffer rb = ByteBuffer.allocateDirect(1024);
                        sc.read(rb);
                        //模拟处理请求
                        //Thread.sleep(2000);
                        // 写Socket
                        ByteBuffer wb = (ByteBuffer) rb.flip();
                        sc.write(wb);
                        // 关闭Socket
                        sc.close();
                    }catch(IOException e){
                        throw new UncheckedIOException(e);
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ssc.close();
            } catch (Exception ignored) {
            }
        }
    }
}
