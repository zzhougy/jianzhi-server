package com.zhou.jianzhi.config.zhounettyrabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static ObjectMapper MAPPER = new ObjectMapper();

//    // 送Spring容器中获取消息监听器容器,处理订阅消息sysNotice
//    SimpleMessageListenerContainer sysNoticeContainer = (SimpleMessageListenerContainer) ApplicationContextProvider.getApplicationContext()
//            .getBean("sysNoticeContainer");
//    // 送Spring容器中获取消息监听器容器,处理点赞消息userNotice
//    SimpleMessageListenerContainer userNoticeContainer = (SimpleMessageListenerContainer) ApplicationContextProvider.getApplicationContext()
//            .getBean("userNoticeContainer");

    // 送Spring容器中获取消息监听器容器,处理***消息getResNotice
    SimpleMessageListenerContainer getResNoticeContainer = (SimpleMessageListenerContainer) ApplicationContextProvider.getApplicationContext()
            .getBean("getResNoticeContainer");

    //从Spring容器中获取RabbitTemplate
    RabbitTemplate rabbitTemplate = ApplicationContextProvider.getApplicationContext()
            .getBean(RabbitTemplate.class);

    //存放WebSocket连接Map，根据用户id存放 【!!!!!static!!!!!!】
    public static ConcurrentHashMap<String, Channel> userChannelMap = new ConcurrentHashMap();

    //用户请求WebSocket服务端，执行的方法？？？？
    //zhouis：此方法不负责建立连接，只是获取连接放到Map集合里，已经建立WebSocket连接的才能执行此方法。
    //zhouis：用户请求数据会执行的方法
    //zhouis：WebSocket连接不在这
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //约定用户第一次请求携带的数据：{"userId":"1"}
        //获取用户请求数据并解析
        String json = msg.text();
        //解析json数据，获取用户id
        String userId = MAPPER.readTree(json).get("userId").asText();


        //第一次请求的时候，需要建立WebSocket连接
        Channel channel = userChannelMap.get(userId);
        if (channel == null) {
            //获取WebSocket的连接
            channel = ctx.channel();

            //把连接放到容器中
            userChannelMap.put(userId, channel);



            //------------------------
            //1 创建Rabbit管理器
            RabbitAdmin rabbitAdmin2 = new RabbitAdmin(rabbitTemplate.getConnectionFactory());
            //2 创建队列，每个用户都有自己的队列，通过用户id进行区分
            Queue queue = new Queue("resume_hrget_" + userId, true);
            rabbitAdmin2.declareQueue(queue);
            //------------------------

        }


        //只用完成新消息的提醒即可，只需要获取消息的数量
        //获取RabbitMQ的消息内容，并发送给用户
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        //-----------------------------------------------------
        //拼接获取队列名称
//        String queueName = "article_subscribe_" + userId;
//        //获取Rabbit的Properties容器
//        Properties queueProperties = rabbitAdmin.getQueueProperties(queueName);
//
//        //获取消息数量
//        int noticeCount = 0;
//        //判断Properties是否不为空
//        if (queueProperties != null) {
//            // 如果不为空，获取消息的数量
//            noticeCount = (int) queueProperties.get("QUEUE_MESSAGE_COUNT");
//        }

        //-----------------------------------------------------
        //拼接获取队列名称
//        String userQueueName = "article_thumbup_" + userId;
//        //获取Rabbit的Properties容器
//        Properties userQueueProperties = rabbitAdmin.getQueueProperties(userQueueName);
//
//        //获取消息数量
//        int userNoticeCount = 0;
//        //判断Properties是否不为空
//        if (userQueueProperties != null) {
//            // 如果不为空，获取消息的数量
//            userNoticeCount = (int) userQueueProperties.get("QUEUE_MESSAGE_COUNT");
//        }

        //-----------------------------------------------------
        //拼接获取队列名称
        String getResQueue = "resume_hrget_" + userId;
        //获取Rabbit的Properties容器
        Properties userQueueProperties = rabbitAdmin.getQueueProperties(getResQueue);

        //获取消息数量
        int getResNoticeCount = 0;
        //判断Properties是否不为空
        if (userQueueProperties != null) {
            // 如果不为空，获取消息的数量
            getResNoticeCount = (int) userQueueProperties.get("QUEUE_MESSAGE_COUNT");
        }



        //封装返回的数据
        HashMap countMap = new HashMap();
       // countMap.put("sysNoticeCount", noticeCount);
        // countMap.put("userNoticeCount", userNoticeCount);
        countMap.put("getResNoticeCount", getResNoticeCount);
        Result result = new Result(true, StatusCode.OK, "查询成功", countMap);
//        AjaxResult ajaxResult = new AjaxResult();
//        ajaxResult.setCode(200);
//        ajaxResult.setMsg("查询成功");
//        ajaxResult.setData(countMap);

        //把数据发送给用户
        channel.writeAndFlush(new TextWebSocketFrame(MAPPER.writeValueAsString(result)));

        //把消息从队列里面清空，否则MQ消息监听器会再次消费一次
//        if (noticeCount > 0) {
//            rabbitAdmin.purgeQueue(queueName, true);
//        }
//        if (userNoticeCount > 0) {
//            rabbitAdmin.purgeQueue(userQueueName, true);
//        }
        if (getResNoticeCount > 0) {
            rabbitAdmin.purgeQueue(getResQueue, true);
        }

        //为用户的消息通知队列注册监听器，便于用户在线的时候，
        //一旦有消息，可以主动推送给用户，不需要用户请求服务器获取数据
        //sysNoticeContainer.addQueueNames(queueName);
        //userNoticeContainer.addQueueNames(userQueueName);
        getResNoticeContainer.addQueueNames(getResQueue);
    }

    //断开websocket连接时执行
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        //根据userChannel的Mapvalue获取key：userid
        //获取WebSocket的连接
        Channel channel = ctx.channel();
        ConcurrentHashMap.KeySetView<String, Channel> strings = userChannelMap.keySet(channel);
        String a = String.valueOf(strings);
        String[] split = a.substring(1, a.length() - 1).split(",");
        System.out.println(split[0]);

       System.out.println("channel移除了");
        userChannelMap.remove(split[0]);
        getResNoticeContainer.removeQueueNames("resume_hrget_"+split[0]);
        super.channelUnregistered(ctx);
    }

//
//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("channel 不活跃，断开连接");
//        super.channelInactive(ctx);
//    }
//
//    @Override
//    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("channel 助手类移除");
//        super.handlerRemoved(ctx);
//    }


}