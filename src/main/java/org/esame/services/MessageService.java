package org.esame.services;

import org.esame.models.Message;
import org.esame.models.User;
import org.esame.services.interfaces.IMessageService;

import java.util.*;


public class MessageService implements IMessageService{
    

    private static MessageService messageService;


    public static MessageService getInstance() {
		if (messageService==null) {
			messageService = new MessageService();
		}
		return messageService;
	}

	private MessageService(){

	}

	public void nuovoMessage(User autore, String testo) {
		Message nuovo = new Message(testo, autore);
		if (getUtentiMessage().get(autore)==null)
			getUtentiMessage().put(autore, new ArrayList<>());
		getUtentiMessage().get(autore).add(nuovo);
	}

	public void eliminaMessage(User proprietario, Message post) {
		List<Message> messages = getUtentiMessage().get(proprietario);
		messages.remove(post);
	}


public List<Message> getAllMessages() {
		List<Message> result = new ArrayList<>();
		for(List<Message> messages: getUtentiMessage().values()) {
			for(Message message: messages) {
					result.add(message);
			}
		}
		return result;
	}

	public Message findPostById(String id) {
		for(List<Message> messages: getUtentiMessage().values()) {
			for(Message message: messages) {
				if (message.getId().equals(id))
					return message;
			}
		}
		return null;
	}


	public List<Message> getAllUserMessages(User owner) {
		List<Message> result = new ArrayList<>();
		for(List<Message> messages: getUtentiMessage().values()) {
			for(Message message: messages) {
				if (owner.equals(message.getAutore()))
					result.add(message);
			}
		}
		return result;
	}
	
	private Map<User, ArrayList<Message>> getUtentiMessage() {
		return PortalService.getInstance().getUtentiMessage();
		}

	




	
}
