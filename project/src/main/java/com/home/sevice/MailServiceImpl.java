package com.home.sevice;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl extends Authenticator {
	PasswordAuthentication passAuth;
	
	public MailServiceImpl() {
		passAuth = new PasswordAuthentication("zzaaqqqaz", "clvbwgrnmcbgaqun");
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		
		return passAuth;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
