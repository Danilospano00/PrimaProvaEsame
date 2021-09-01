package org.esame.utils;

import org.esame.models.Message;
import org.esame.models.dtos.MessageDTO;

public class MessageTransformer{

    public static Message fromDTO(MessageDTO dto) {
        Message result = new Message(dto.getTesto(), UserTransformer.fromDTO(dto.getAutore()));
        return result;
    }

    public static MessageDTO toDTO(Message model) {
        MessageDTO result = new MessageDTO(model.getTesto(), UserTransformer.toDTO(model.getAutore(), false));
        return result;
    }
}