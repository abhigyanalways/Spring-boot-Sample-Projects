package com.abhigyan.chat_app.chat;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder //builder pattern is seen
public class ChatMessage {
    private String content;
    private String sender;
    private MessageType type;
}
