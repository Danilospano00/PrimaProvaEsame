package org.esame.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.esame.models.dtos.UserDTO;
import org.esame.services.PortalService;
import org.esame.services.interfaces.IPortalService;
import org.esame.utils.RequestUtil;
import static org.esame.utils.RequestUtil.*;

import spark.Route;
import spark.Request;
import spark.Response;
import org.esame.models.dtos.*;
import org.esame.utils.*;
import org.esame.exceptions.ErroreUtenteInesistenteException;
import org.esame.models.*;



public class MessageController {
    
    private static final IPortalService portalService = PortalService.getInstance();
    
    UserDTO user1DTO = new UserDTO("Danilo", "Spanò", "danilo", "danilo.spano@gmail.com").setPassword("password");


    public static Route handleGetAllMessage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("messageDto", getAllMessage(request));
        return ViewUtil.render(request, model, Path.Template.MESSAGE_ALL);
    };


    public static Route handlegetAllUserMessage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("messageDto", getAllUserMessage(request));
        return ViewUtil.render(request, model, Path.Template.MESSAGE_ALL);
    };

    public static Route handlegetAllYoursMessage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("messageDtos", getAllMineMessage(request));
        return ViewUtil.render(request, model, Path.Template.MESSAGE_ALL);
    };


    public static Route handleGetPageAddNewMessage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("messageDto", new MessageDTO());
        return ViewUtil.render(request, model, Path.Template.MESSAGE_NEW);
    };


    public static Route handleAddNewMessage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        UserDTO utenteCorrenteDTO = RequestUtil.getSessionCurrentUser(request);
        User utenteProprietario = portalService.getUserService().cercaUtentePerEmail(utenteCorrenteDTO.getEmail());
        MessageDTO messageDto = RequestUtil.getMessageDTOFromRequest(request);
        model.put("messageDto", messageDto);

        portalService.getMessageService().nuovoMessage(utenteProprietario, messageDto.getTesto());

        return ViewUtil.render(request, model, Path.Template.MESSAGE_NEW);
    };


    public static Route handleDeleteRemoveMessage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        UserDTO utenteCorrenteDTO = RequestUtil.getSessionCurrentUser(request);
        User utenteProprietario = portalService.getUserService().cercaUtentePerEmail(utenteCorrenteDTO.getEmail());
        Message message = portalService.getMessageService().findPostById(request.params("id"));

        portalService.getMessageService().eliminaMessage(utenteProprietario, message);
        model.put("messageDtos", getAllUserMessage(request));

        return ViewUtil.render(request, model, Path.Template.MESSAGE_ALL);
    };

 
public static List<MessageDTO> getAllMineMessage(Request request) {
    List<MessageDTO> message = new ArrayList<>();
    UserDTO utenteCorrenteDTO = RequestUtil.getSessionCurrentUser(request);
    try {
        User utenteCorrente = portalService.getUserService().cercaUtentePerEmail(utenteCorrenteDTO.getEmail());
        for(Message item: portalService.getMessageService().getAllUserMessages(utenteCorrente)) {
            message.add(MessageTransformer.toDTO(item));
        }
    } catch (ErroreUtenteInesistenteException e) {
        e.printStackTrace();
    }
    return message;
}

public static List<MessageDTO> getAllMessage(Request request) throws ErroreUtenteInesistenteException {
    List<MessageDTO> messages = new ArrayList<>();
    UserDTO utenteCorrenteDTO = getSessionCurrentUser(request);
        User utenteCorrente = portalService.getUserService().cercaUtentePerEmail(utenteCorrenteDTO.getEmail());
        for(Message item: portalService.getMessageService().getAllMessages()) {
            messages.add(MessageTransformer.toDTO(item));
        }
      return messages;
}

public static List<MessageDTO> getAllUserMessage(Request request) throws ErroreUtenteInesistenteException {
    List<MessageDTO> messages = new ArrayList<>();
    UserDTO utenteCorrenteDTO = RequestUtil.getSessionCurrentUser(request);
        User utenteCorrente = portalService.getUserService().cercaUtentePerEmail(utenteCorrenteDTO.getEmail());
        User utenteDelPost = portalService.getUserService().cercaUtentePerEmail(request.params("author"));
    return messages;
}
}
