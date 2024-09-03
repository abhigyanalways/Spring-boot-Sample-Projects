package com.abhigyan.chat_app.config;

import com.abhigyan.chat_app.chat.ChatMessage;
import com.abhigyan.chat_app.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;
    //As the name suggests, this would be used to send messages
    // to the broker so that it broadcasts them to clients.
    //This has to be noted that instead of simply creating a chat message and sending it to the broker,
    // we use this method because we cannot send the chatmessage directly. I guess, controller has that power to ask broker for topic publication.
    //and so does the SimpMessageSendingOperation
    @EventListener
    public void handleWebSocketDisconnectListener(
            SessionDisconnectEvent event //This event object contains information like the session id. The id is used to fetch the correct session attributes.
            //The event can also potentially contain the last message that was being sent or had been sent by the client that disconnected.
    ){
        //wrapping event - message to apply operations like header access , also creating an obj of stompHeaderAccesor  thusly.(like destination/subscription/etc)
        StompHeaderAccessor headerAccessor= StompHeaderAccessor.wrap(event.getMessage());
        //We could have used the SimpMessageHeaderAccessor if we intend to work with session attributes only.
        //In order to access the session attributes, we must use a type of header accessor.(simp/stomp)
        //That is the reason why we are Wrapping the event getmessage with a header accessor , So that we might fetch the session attributes.
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username!=null){
            log.info("USER DISCONNECTED :-( username : {}", username);
            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();
            messagingTemplate.convertAndSend("/topic/public",chatMessage);
        }



    }
}
// We will be trying to implement a publisher-subscriber design pattern.
//The basic functionality of websocket protocol allows server to push data to a client without it asking for the data.
//But if we use a broker then we might restrict the clients from seeing the things which they have not subscribed to.

//REMEMBER: event listeners in spring boot are not actively listening , these also follow pub-sub patter.
//On this date I don't exactly understand the mechanics , but this should be analogous:

//We create the event object and encapsulate whatever things we might need.
//We then add (publish) To a broker that goes by application event publisher
//Neglecting topic constraints , all subscribers (@EventListeners) then get the event as parameter
//see : In the snippet above the param is populated by spring framework automatically because session disconnect is not a custom-made event.