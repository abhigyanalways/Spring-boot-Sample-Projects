package com.abhigyan.chat_app.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class chatController {

    private static final Logger log = LoggerFactory.getLogger(chatController.class);

    //a method to send the message(payload)
    @MessageMapping("/chat.sendMessage")//this is used by the broker to decide which controller method to invoke.
    @SendTo("/topic/public")//after that , whatever the controller returns is sent back to the broker's specified topic
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage // the websocket alternative to @RequestBody is @Payload
    ){
        return chatMessage; //no processing in msg for simplicity
        //this will go to the broker now (destination prefix)
    }

    //we use this method to add user' name ,i.e.,preparing the session attribute for future use (which wont be necessary)
    @MessageMapping("chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
            headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());//The session attributes are mapped internally to
        // the session id by spring
            log.info("new client connected: " +chatMessage.getSender());
            return chatMessage;
    }

    //The SimpMessageHeaderAccessor(a utility class) object is used as a state
    // That is commonly shared by all the messages in one particular persistent session .
    //We have not used a header annotation (@Header in ws/stomp) like we had an http because
    // this time a single connection would have multiple messages. (Unlike one request per connection in HTTP.
    //One of the best examples is the username which would remain the same for one session, no matter how many messages.

    //Session attributes is a type of map that is instantiated by default and remains empty unless the programmer manually populates it with some data
    //he might use later , like the tag or username for a client .
    //It has to be understood that session attributes play a very powerful role by establishing and maintaining
    //a persistent state throughout the session of this. websocket communication.
    // This information then needs not to be written in every message.
}
