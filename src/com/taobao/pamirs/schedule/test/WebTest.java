package com.taobao.pamirs.schedule.test;

import javax.servlet.http.HttpServlet;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WebTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// static ClassPathXmlApplicationContext appContext;
	static ConfigurableApplicationContext appContext;
	String filename;
	public void init(){
//		appContext =  new ClassPathXmlApplicationContext(
//				new String[] {"schedule.xml"});

		appContext = new ClassPathXmlApplicationContext("schedule.xml");
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
