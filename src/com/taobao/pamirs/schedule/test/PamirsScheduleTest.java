package com.taobao.pamirs.schedule.test;


import com.taobao.pamirs.schedule.TBScheduleManagerFactory;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

/**
 * 调度测试
 * @author xuannan
 *
 */
@SpringApplicationContext( { "schedule.xml" })
public class PamirsScheduleTest  extends UnitilsJUnit4{
	@SpringBeanByName
    TBScheduleManagerFactory tbScheduleManagerFactory;
	
    public void setTbScheduleManagerFactory(
			TBScheduleManagerFactory tbScheduleManagerFactory) {
		this.tbScheduleManagerFactory = tbScheduleManagerFactory;
	}

  @Test
	public void testLoadRunningInfo() throws Exception{
		String baseTaskType ="PamirsScheduleTest";
		String ownSign ="TEST";
		tbScheduleManagerFactory
			.getScheduleConfigCenter().loadTaskTypeRunningInfo(baseTaskType, ownSign, "-100");
		float d = 0.0f;
		tbScheduleManagerFactory
		.getScheduleConfigCenter().clearExpireTaskTypeRunningInfo(baseTaskType,"-1",d);
	}

	@Test    
	public void testRunData() throws Exception {
		int threadNum = 1;
		for (int i = 0; i < threadNum; i++) {
			tbScheduleManagerFactory.createTBScheduleManager("PamirsScheduleTest","BASE");
			tbScheduleManagerFactory.createTBScheduleManager("PamirsScheduleTest","TEST");
			tbScheduleManagerFactory.createTBScheduleManager("PamirsScheduleTest","PRE");
		}
		Thread.sleep(10000000);
	}

	@Test
	public void testRunDataInOneJvmWithMultiScheduleManagerOfTheSameTaskType() throws Exception {
		int threadNum = 1;
		for (int i = 0; i < threadNum; i++) {
			tbScheduleManagerFactory.createTBScheduleManager("PamirsScheduleTest","BASE");
			tbScheduleManagerFactory.createTBScheduleManager("PamirsScheduleTest","BASE");
		}
		Thread.sleep(10000000);
	}

	/**
	 *  need start multiple tests at the same time
	 */
	@Test
	public void testRunDataInDifferentJvmOfTheSameTaskType() throws Exception {
		int threadNum = 1;
		for (int i = 0; i < threadNum; i++) {
			tbScheduleManagerFactory.createTBScheduleManager("PamirsScheduleTest","BASE");
		}
		Thread.sleep(10000000);
	}

	/**
	 *  测试在pamirs_schedule_queue中没有"Base" ownsign的任务。需要将任务Base队列提前删除
	 */
	@Test
	public void testWithoutBaseOwnsignQueue() throws Exception {
		int threadNum = 1;
		tbScheduleManagerFactory.createTBScheduleManager("PamirsScheduleTest","TEST");
	}
}
