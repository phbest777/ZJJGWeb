package org.ph.ssm.ZJJGWeb.service;

import org.ph.ssm.ZJJGWeb.tools.SpringTool;
import org.ph.ssm.ZJJGWeb.websocket.WebSocketServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.ph.ssm.ZJJGWeb.service.ZfInfoService;

@Service
public class WebsocketService implements ServletContextListener {
    private ScheduledExecutorService service;

    private ZfInfoService zfInfoService;
    private static int payed_old_sum;
    private static int authed_old_sum;
    private static int orgin_old_sum;


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        zfInfoService= SpringTool.getBean(ZfInfoService.class);
        payed_old_sum=zfInfoService.GetZfTransPayed();
        authed_old_sum=zfInfoService.GetZfTransAuthed();
        orgin_old_sum=zfInfoService.GetZfTransOrigin();
        Runnable runnable=()->{
            int payed_new_sum=zfInfoService.GetZfTransPayed();
            int authed_new_sum=zfInfoService.GetZfTransAuthed();
            int orgin_new_sum=zfInfoService.GetZfTransOrigin();
            //System.out.println("原来数据:"+old_sum+"---新数据:"+new_sum);
            WebSocketServlet wbs=new WebSocketServlet();
            if((payed_old_sum!=payed_new_sum)||(authed_old_sum!=authed_new_sum)||(orgin_old_sum!=orgin_new_sum)) {
                if(payed_old_sum!=payed_new_sum) {
                    System.out.println("Zfinfo Payed Status Updated,All Payed Num is:"+payed_new_sum);
                    System.out.println("Zfinfo Authed Status Updated,All Authed Num is:"+authed_new_sum);
                    payed_old_sum = payed_new_sum;
                    authed_old_sum=authed_new_sum;
                }
                if(orgin_old_sum!=orgin_new_sum){
                    System.out.println("Zfinfo Orgin Status Updated,All Orgin Num is:"+orgin_new_sum);
                    System.out.println("Zfinfo Authed Status Updated,All Authed Num is:"+authed_new_sum);
                    orgin_old_sum = orgin_new_sum;
                    authed_old_sum=authed_new_sum;
                }
                wbs.onMessage(authed_new_sum);
            }
        };
        service= Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable,0,10, TimeUnit.SECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        service.shutdown();
    }
}
