package com.svnyoung.pubsub.test;

import com.svnyoung.pubsub.MessageSource;
import com.svnyoung.pubsub.Subject;
import com.svnyoung.pubsub.Terminal;
import com.svnyoung.pubsub.message.MessageModel;
import com.svnyoung.pubsub.spring.listener.Listener;
import com.svnyoung.pubsub.spring.listener.Listeners;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author: sunyang
 * @date: 2019/8/17 14:55
 * @version: 1.0
 * @since: 1.0
 * @see:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class MQTest {

    @Autowired
    private MessageSource messageSource;

    @Listeners(value = {
            @Listener(depend = "GroupDev",topic = "TopicDev",chooser = "*"),
            @Listener(depend = "GroupDev",topic = "TopicDev",chooser = "*")
    })
    public void receive(String ... str){
        System.out.println(str);
    }


    @Test
    public void publish(){
        Subject subject = new Subject();
        subject.setTopic("TopicDev");
        subject.setMessageModel(MessageModel.POINT_TO_POINT);
        subject.setChooser("*");
        Terminal terminal = messageSource.getTerminal(subject);
        terminal.publish("123","dfdfdfdfd");
    }




    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

}
