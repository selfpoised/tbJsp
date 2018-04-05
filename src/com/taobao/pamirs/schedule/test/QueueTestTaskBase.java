package com.taobao.pamirs.schedule.test;

import com.taobao.pamirs.schedule.IScheduleTaskDeal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 测试基类，实现查询接口，比较器接口
 * @author xuannan
 *
 */
public class QueueTestTaskBase implements IScheduleTaskDeal<Long> {

  protected static transient Log log = LogFactory.getLog(QueueTestTaskBase.class);

  protected DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Comparator<Long> getComparator(){
	  return new Comparator<Long>(){
		  public int compare(Long o1, Long o2){
			  return o1.compareTo(o2);
		  }
		  public boolean equals(Object obj){
			  return this == obj;
		  }
	  };
  }

   public List<Long> selectTasks(String ownSign,int queueNum,List<String> queryCondition,int fetchNum) throws Exception{
     List<Long> result = new ArrayList<Long>();
     if(queryCondition.size() ==0){
       return result;
     }

     StringBuffer condition = new StringBuffer();
     for(int i=0;i<queryCondition.size();i++){
        if(i >0){
         condition.append(",");
        }
        condition.append( queryCondition.get(i));
     }

     Connection conn = null;
     try{
    	 conn =dataSource.getConnection();
    	 String dbType = this.getDataBaseType(conn);
    	 String sql = null;
    	 if("oracle".equalsIgnoreCase(dbType)){
    		 sql = "select ID from SCHEDULE_TEST where OWN_SIGN = '" + ownSign + "' and mod(id," + queueNum+ ") in (" + condition.toString() +") and sts ='N' and rownum <= " + fetchNum;
    	 }else if("mysql".equalsIgnoreCase(dbType)){
    		 sql = "select ID from SCHEDULE_TEST where OWN_SIGN = '" + ownSign + "'  and mod(id," + queueNum+ ") in (" + condition.toString() +") and sts ='N' LIMIT " + fetchNum;
    	 }else{
    		 throw new Exception("不支持的数据库类型：" + dbType);
    	 }
    	 PreparedStatement statement = conn.prepareStatement(sql);
    	 ResultSet set = statement.executeQuery();
    	 while(set.next()){
    		 result.add(set.getLong("ID"));
    	 }
    	 set.close();
    	 statement.close();
    	 return result;
     }finally{
    	if(conn != null)
    		conn.close();
     }
   }
	public String getDataBaseType(Connection conn) throws SQLException{
		return conn.getMetaData().getDatabaseProductName();
	}
}