package org.ph.ssm.ZJJGWeb.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: Wxz
 * @Date: 2020/9/1 10:30
 */

@ServerEndpoint("/websocket")
@Component
public class WebSocketServlet {
   // MyThread thread1=new MyThread();
    //开启线程
    //Thread thread=new Thread(thread1);
    //用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServlet> webSocketSet = new CopyOnWriteArraySet<WebSocketServlet>();
    private  Session session=null;

    /**
     * @ClassName: onOpen
     * @Description: 开启连接的操作
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException{
        //获取WebsocketConfig.java中配置的“sessionId”信息值
        String httpSessionId = (String) config.getUserProperties().get("sessionId");
        this.session=session;
        webSocketSet.add(this);
        //开启一个线程对数据库中的数据进行轮询
        //thread.start();

    }

    /**
     * @ClassName: onClose
     * @Description: 连接关闭的操作
     */
    @OnClose
    public void onClose(){
        //thread1.stopMe();
        webSocketSet.remove(this);
    }

    /**
     * 告知前端数据库发生变化，调用sendMessage()方法
     * @param count
     */
    @OnMessage
    public void onMessage(int count) {
        try {
            sendMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 出错操作
     * @param error
     */
    @OnError
    public void onError(Throwable error){
        error.printStackTrace();
    }

    /**
     * 此方法是被调用的方法，所以没注解，这里才是真正处理逻辑的地方，可以给前台传输你想要传输的内容，我只是用1进行代替了。
     * @throws IOException
     * 发送自定义信号，“1”表示告诉前台，数据库发生改变了，需要刷新
     */
    public void sendMessage() throws IOException{
        //群发消息
        for(WebSocketServlet item: webSocketSet){
            item.session.getBasicRemote().sendText("1");
        }
    }
}
