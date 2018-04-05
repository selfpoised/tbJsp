package com.taobao.pamirs.schedule.test;

import com.taobao.pamirs.schedule.IScheduleTaskDealMulti;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 批处理实现
 * @author xuannan
 *
 */
public class QueueTestTaskMulti extends QueueTestTaskBase implements
        IScheduleTaskDealMulti<Long> {

	private static transient Log log = LogFactory
			.getLog(QueueTestTaskMulti.class);

	public QueueTestTaskMulti() {
	}

	public boolean execute(Object[] tasks,String ownSign) throws Exception {
		Connection conn = null;
		long id = 0;
		try {
			conn = dataSource.getConnection();
			for (int index = 0; index < tasks.length; index++) {
				id = ((Long)tasks[index]).longValue();
				log.debug("处理任务：" + id +" 成功！");
				String sql = "update SCHEDULE_TEST SET STS ='Y' ,DEAL_COUNT = DEAL_COUNT + 1 WHERE ID = ? and STS ='N' ";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setLong(1, id);
				statement.executeUpdate();
				statement.close();
			}
			conn.commit();
		} catch (Exception e) {
			log.error("执行任务：" + id + "失败：" + e.getMessage(), e);
			if (conn != null) {
				conn.rollback();
			}
			return false;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		//System.out.println("处理任务：" + tasks.length);
		return true;
	}
}