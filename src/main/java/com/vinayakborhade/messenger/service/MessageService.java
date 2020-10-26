package com.vinayakborhade.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.vinayakborhade.messenger.database.DatabaseClass;
import com.vinayakborhade.messenger.exception.DataNotFoundException;
import com.vinayakborhade.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1, "Hello world", "vinayak"));
		messages.put(2L, new Message(2, "Hello there", "tony"));
	}
	
	public List<Message> getAllMessages() {
//		Message m1 = new Message(1, "hello world", "Vinayak", null);
//		Message m2 = new Message(2, "hello Abi", "Abi", null);
//		List<Message> list = new ArrayList<>();
//		list.add(m1);
//		list.add(m2);
//		return list;
		
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> list = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		for(Message message: messages.values()) {
			calendar.setTime(message.getCreated());
			if(calendar.get(Calendar.YEAR) == year) {
				list.add(message);
			}
		}
		return list;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if(start + size > list.size()) {
			return new ArrayList<Message>();
		}
		return list.subList(start, size + start);
	}
		
	
	public Message getMessage(long id) {
		Message message = messages.get(id);
		if(message == null) {
			throw new DataNotFoundException("Message with id " + id + " not found");
		}
//		System.out.println("getMsg id " + id + " author " + message.getAuthor());
		return message;
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId() <= 0) {
			return null;
		}
		long id = message.getId();
		messages.remove(id);
		messages.put(id, message);
		System.out.println("updated id " + id + " author " + messages.get(id).getAuthor());
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
