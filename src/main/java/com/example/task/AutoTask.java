package com.example.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** 
 * @author  lilufeng 
 * @date 创建时间：2016年4月15日 上午9:17:35 
 */
@Component
public class AutoTask {
	
	private static Logger logger = LoggerFactory.getLogger(AutoTask.class);
	
	 private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	 /**
	  * 定时任务
	  */
	 //@Scheduled(cron="0 * * * * ?")
	 public void reportCurrentTime() {
		 logger.info("The time is now " + dateFormat.format(new Date()));
	 }
	
}
