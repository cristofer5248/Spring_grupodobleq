package com.grupoq.app.models.service;




public interface IEmailService {
	void sendSimpleMessage(String to, String subject, String text);

	void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);
}
