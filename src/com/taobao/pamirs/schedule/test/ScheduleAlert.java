package com.taobao.pamirs.schedule.test;

import com.taobao.pamirs.schedule.IScheduleAlert;


public class ScheduleAlert implements IScheduleAlert {

	public void noTaskQueue(String taskType, String scheduleServerUUID,
			String message) {
		System.out.println("noTaskQueue.............");
		
	}

	public void noReloadTaskQueue(String taskType, String scheduleServerUUID,
			String message) {
		System.out.println("noReloadTaskQueue.............");
	}

}
