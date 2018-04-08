package com.taobao.pamirs.schedule.test;

import com.taobao.pamirs.schedule.IScheduleClient;
import com.taobao.pamirs.schedule.ScheduleTaskType;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

@SpringApplicationContext( { "schedule.xml" })
public class CreateTaskTypeTest extends UnitilsJUnit4{
	@SpringBeanByName
    IScheduleClient scheduleConfigCenter;
	
	public void setScheduleConfigCenter(IScheduleClient scheduleConfigCenter) {
		this.scheduleConfigCenter = scheduleConfigCenter;
	}

	@Test    
	public void createTaskType() throws Exception {
		this.scheduleConfigCenter.clearTaskType("PamirsScheduleTest");
		String type ="XNTest";
		this.scheduleConfigCenter.deleteTaskType(type);
		ScheduleTaskType baseTaskType = new ScheduleTaskType();
		baseTaskType.setBaseTaskType(type);
		baseTaskType.setDealBeanName("abc");
		this.scheduleConfigCenter.createBaseTaskType(baseTaskType,new String[]{"A","B","C"});
	}
}
