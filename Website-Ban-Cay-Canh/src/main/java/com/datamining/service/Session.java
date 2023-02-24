package com.datamining.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Session {

	@Autowired 
	HttpSession session;
	
	public void setAttribute(String name,Object value)
	{
		session.setAttribute(name, value);
	}
	public <T> T getAttribute(String name)
	{
		return (T) session.getAttribute(name);
	}
	public void remoteAttribute(String name)
	{
		session.removeAttribute(name);
	}
}
