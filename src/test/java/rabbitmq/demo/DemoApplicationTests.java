package rabbitmq.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    //发送数据
    @Test
    public void contextLoads() {
        //需要自己构造message
        //rabbitTemplate.send(exchange,routeKey,message);


        //object作为默认的消息体，只需要传入发送的对象，自动序列化给rabbitmq
        //rabbitTemplate.convertAndSend(exchange,routeKey,object);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","This is my first msg");
        map.put("data","yes");
        rabbitTemplate.convertAndSend("exchange.topic","bu.hai",map);
    }


    //接收数据
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("bu.hao");
        System.out.println(o.getClass());
        System.out.println(o);
    }

}
