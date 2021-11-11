package com.moumangtai.demo.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Message implements Serializable {

    private int messageType;

    private int sequenceId;

    public abstract int getMessageType();
}
