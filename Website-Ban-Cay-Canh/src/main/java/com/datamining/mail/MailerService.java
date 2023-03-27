package com.datamining.mail;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

public interface MailerService {
	List<MailInfo> list = new ArrayList<>();

	void send(MailInfo mail) throws MessagingException;
	
	void send(String to, String subject, String body) throws MessagingException;
	
	void queue(MailInfo mail);
	
	void queue(String to, String subject, String body);
}
