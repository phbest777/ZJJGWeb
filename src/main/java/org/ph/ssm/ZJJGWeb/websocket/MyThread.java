package org.ph.ssm.ZJJGWeb.websocket;


import org.ph.ssm.ZJJGWeb.service.ZfInfoService;
import org.ph.ssm.ZJJGWeb.tools.SpringTool;
import org.ph.ssm.ZJJGWeb.service.AccCollectService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: Wxz
 * @Date: 2020/9/1 10:29
 */
@Component
public class MyThread implements Runnable{
    //普通类获取service对象，否则会报空指针

    private ZfInfoService zfInfoService;
    public static MyThread testUtils;
    //下面的注解一定要有哈
    @PostConstruct
    public void init() {
        testUtils = this;
    }
    //原始数量
    private int sum;
    //数据库变化后的数量
    private int new_sum;
    //设置循环状态
    private boolean stopMe = true;
    public void stopMe() {
        stopMe = false;
    }


    @Override
    public void run() {
        /*ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        deptService=(DeptService)applicationContext.getBean("deptService");*/
        zfInfoService= SpringTool.getBean(ZfInfoService.class);
        sum=zfInfoService.GetZfTransPayed();
        WebSocketServlet wbs=new WebSocketServlet();
        while(stopMe){
            new_sum=zfInfoService.GetZfTransPayed();
            if(sum!=new_sum){
                sum=new_sum;
                wbs.onMessage(sum);
            }
            try {
                //每10秒检查一下数据库
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
