package com.taobao.pamirs.schedule.test;

import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 单个任务处理实现
 * @author xuannan
 *
 */
public class QueueTestTaskSingle extends QueueTestTaskBase implements IScheduleTaskDealSingle<Long> {

  public QueueTestTaskSingle() {
  }
 
  public boolean execute(Long task,String ownSign) throws Exception{
	 Connection conn = null;
	 Long id = (Long)task;
    try{
    conn =dataSource.getConnection();
    String sql = "update SCHEDULE_TEST SET STS ='Y' ,DEAL_COUNT = DEAL_COUNT + 1 WHERE ID = ? and STS ='N' ";
    PreparedStatement statement = conn.prepareStatement(sql);
    statement.setLong(1,id);
    statement.executeUpdate();
    statement.close();
    conn.commit();
    log.debug("处理任务：" + id +" 成功！");
    return true;
    }catch(Exception e){
      log.error("执行任务：" + task + "失败：" + e.getMessage(), e);
      if(conn != null){
    	  conn.rollback();
      }
      return false;
    }finally{
    	if(conn != null){
    		conn.close();
    	}
    }
  }
}