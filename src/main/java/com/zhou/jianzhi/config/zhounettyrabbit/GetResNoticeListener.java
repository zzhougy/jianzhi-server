package com.zhou.jianzhi.config.zhounettyrabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

import java.util.HashMap;

public class GetResNoticeListener implements ChannelAwareMessageListener {

    private static ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        //获取用户id，可以通过队列名称获取
        String queueName = message.getMessageProperties().getConsumerQueue();
        String userId = queueName.substring(queueName.lastIndexOf("_") + 1);

        io.netty.channel.Channel wsChannel = WebSocketHandler.userChannelMap.get(userId);

        //判断用户是否在线
        if (wsChannel != null) {
            //如果连接不为空，表示用户在线
            //封装返回数据
            HashMap countMap = new HashMap();
            countMap.put("getResNoticeCount", 1);
            Result result = new Result(true, StatusCode.OK, "查询成功", countMap);

//            AjaxResult ajaxResult = new AjaxResult();
//            ajaxResult.setCode(200);
//            ajaxResult.setMsg("查询成功");
//            ajaxResult.setData(countMap);

            // 把数据通过WebSocket连接主动推送用户
            wsChannel.writeAndFlush(new TextWebSocketFrame(MAPPER.writeValueAsString(result)));
        }
    }
}