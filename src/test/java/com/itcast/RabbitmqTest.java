package com.itcast;

import com.itcast.springboot_rabbitmq_producer.ProducerApplication;
import com.itcast.springboot_rabbitmq_producer.config.RabbitmqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProducerApplication.class)
public class RabbitmqTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
@Test
    public void test01() {
        rabbitTemplate.convertAndSend(RabbitmqConfig.ITEM_TOPIC_EXCHANGE,"item.insert", "商品新增，routing key 为item.insert");
        rabbitTemplate.convertAndSend(RabbitmqConfig.ITEM_TOPIC_EXCHANGE,"item.update", "商品修改，routing key 为item.update");
        rabbitTemplate.convertAndSend(RabbitmqConfig.ITEM_TOPIC_EXCHANGE,"item.delete", "商品删除，routing key 为item.delete");
    }
}
