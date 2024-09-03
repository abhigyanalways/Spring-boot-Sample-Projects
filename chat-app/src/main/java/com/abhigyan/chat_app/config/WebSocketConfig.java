package com.abhigyan.chat_app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//REMEMBER : This time, the dispatcher servlet will be bypassed because we are not dealing with an Http request.
//Since we require something to direct the message to appropriate controller method , we configure that here.
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    //STOMP is the protocol that suggests the message formats.
    //It handles messages,topics,actions(send/connect/etc) and such things
    //by working on top of WS.
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();// see: the end-point to establish the persistent connection of websocket
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); //this replaces the dispatcher-servlet and Directs the request(msg actually) to appropriate controller method.
        registry.enableSimpleBroker("/topic"); //This endpoint is later on hit by the controller's method , specified in @SendTo.
    }
}

//example of stomp adhering message:
/*
SEND  --command
destination:/topic/chat
content-type:text/plain

Hello, World!  --body
        ^@

 */

//Remember websocket connections can be stateful because there are several messages sent over the same connection.
//The simp-message-header-accessor class/obj is an example of shared state.
