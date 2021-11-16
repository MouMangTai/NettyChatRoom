package com.moumangtai.demo.message;

import lombok.Data;

import java.io.Serializable;


@Data
public abstract class Message implements Serializable {

    private int messageType;

    private int sequenceId;

    public Message() {
    }

    public Message(int messageType, int sequenceId) {
        this.messageType = messageType;
        this.sequenceId = sequenceId;
    }

    public Message(int sequenceId) {
        this.sequenceId = sequenceId;
    }

    public abstract int getMessageType();
}
