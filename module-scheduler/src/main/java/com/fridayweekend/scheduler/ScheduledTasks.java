package com.fridayweekend.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduledTasks {
	
	final static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
	private String fixedDelay = null;

	public String getFixedDelay() {
		return fixedDelay;
	}

	public void setFixedDelay(String fixedDelay) {
		this.fixedDelay = fixedDelay;
	}

	public void run() {
		logger.info("Scheduler task: " + dateFormat.format(new Date()));
    }
}