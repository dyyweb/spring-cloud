package com.dy.lisener;


import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 消费消息
 *
 * @author DengYang
 * @date 2018/6/5 15:59
 * @since JDK1.8.0_161
 */
@Slf4j
public class MessageLisener implements MessageListenerConcurrently {
    String pay_topic = "YBL_GATEWAY_PAY_TOPIC";
    String refund_topic = "YBL_GATEWAY_REFUND_TOPIC";

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        // 如果没有return success ，consumer会重新消费该消息，直到return success
        String body = "";
        try {
            Message msg = msgs.get(0);
            body = new String(msg.getBody(), "utf-8");
            log.info("接收到消息 --> Topic = {},Tags={},Key={},data={}", msg.getTopic(), msg.getKeys(), msg.getTags(), body);
            if (pay_topic.equals(msg.getTopic())) {

            }else if (refund_topic.equals(msg.getTopic())) {

            }
            log.info("接收到消息处理成功!");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        } catch (Exception e) {
            log.error("MessageLisener消息消费失败{},异常{}", body, e.getMessage());
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
    }
}
