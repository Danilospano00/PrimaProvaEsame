package org.esame.utils;

import org.esame.models.Message;
import org.esame.models.dtos.MessageDTO;

public class MessageTransformer{

    public static Message fromDTO(MessageDTO dto) {
        Message result = new Message(dto.getTesto(), null);
        return result;
    }

    public static MessageDTO toDTO(Message model) {
        MessageDTO result = new MessageDTO(model.getTesto());
        return result;
    }
}