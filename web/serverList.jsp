
<%@page import="com.taobao.pamirs.schedule.MonitorBean"%>
<%@page import="com.taobao.pamirs.schedule.TaskQueueInfo"%>
<%@page import="com.taobao.pamirs.schedule.ScheduleServer"%>
<%@page import="com.taobao.pamirs.schedule.ScheduleTaskTypeRunningInfo"%>
<%@page import="com.taobao.pamirs.schedule.ScheduleTaskType"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=GB2312" %>
<html>
<head>
<title>
������������ʷ��Ϣ
</title>
</head>
<body bgcolor="#ffffff">

<%
String baseTaskType =  request.getParameter("baseTaskType");
String ownSign =  request.getParameter("ownSign");
String ip =  request.getParameter("ip");
String orderStr =  request.getParameter("orderStr");
%>

<table >
  <tr>
  	<td>�������ͣ�</td><td><input type="input" id="baseTaskType" value="<%=baseTaskType==null?"":baseTaskType%>"> </td>
  	<td>������</td><td><input type="input" id="ownSign" value="<%=ownSign==null?"":ownSign%>"> </td>
  </tr>
  <tr>
  	<td>IP��</td><td><input type="input" id="ip" value="<%=ip==null?"":ip%>"> </td>
  	<td>����</td><td><input type="input" id="orderStr" value="<%=orderStr==null?"":orderStr%>"> </td>
  </tr>  
  <tr>
  	<td>�����ֶ�˵����TASK_TYPE, IP, HOST_NAME, MANAGER_PORT, THREAD_NUM, REGISTER_TIME, HEARTBEAT_TIME,VERSION</td>
  </tr>  
  <tr>
  	<td>JMX_URL, DEALINFO_DESC, NEXT_RUN_START_TIME, NEXT_RUN_END_TIME, GMT_CREATE, GMT_MODIFIED, OWN_SIGN </td>
  </tr>  
  <tr>
  	<td><input type="button"  onclick="query()" value="��ѯ"></td>
  </tr>  
</table>


   <table border="1" style=";border-COLLAPSE: collapse;display:block;">
   <tr>
   <th nowrap>���</th>
   <th>��������</th>
   <th>��</th>
   <th>IP��ַ</th>
   <th>��������</th>
   <th nowrap>�߳�</th>
   <th>ע��ʱ��</th>
   <th>����ʱ��</th>
   <th nowrap>�汾</th>
   <th nowrap>�´ο�ʼ</th>
   <th nowrap>�´ν���</th>
   <th>JMX</th>
   <th>��������</th>   
   </tr>
   <%
   List<ScheduleServer> serverList = MonitorBean.getScheduleConfigCenterClient()
        .selectScheduleServer(baseTaskType,ownSign,ip,orderStr);
   for(int j =0;j<serverList.size();j++){
   %>
       <tr>
	   <td><%=(j+1) %></td>
	   <td><%=serverList.get(j).getBaseTaskType()%></td>	  
	   <td><%=serverList.get(j).getOwnSign()%></td>	  
	   <td nowrap><%=serverList.get(j).getIp()%></td>	  
	   <td nowrap><%=serverList.get(j).getHostName()%></td>	
	   <td><%=serverList.get(j).getThreadNum()%></td>	
	   <td nowrap><%=serverList.get(j).getRegisterTime()%></td>	
	   <td nowrap><%=serverList.get(j).getHeartBeatTime()%></td>	
	   <td><%=serverList.get(j).getVersion()%></td>	
	   <td nowrap><%=serverList.get(j).getNextRunStartTime() == null?"--":serverList.get(j).getNextRunStartTime()%></td>	
	   <td nowrap><%=serverList.get(j).getNextRunEndTime()==null?"--":serverList.get(j).getNextRunEndTime()%></td>
	   <td nowrap><%=serverList.get(j).getJmxUrl()%></td>	
	   <td nowrap><%=serverList.get(j).getDealInfoDesc()%></td>	
	   </tr>      
   <%
   }
   %>
   </table> 
</body>
</html>

<script>

function query(){
	 var baseTaskType=document.all("baseTaskType").value;
	 var ownSign=document.all("ownSign").value;
	 var ip=document.all("ip").value;
	 var orderStr=document.all("orderStr").value;	 
	 var url =  "serverList.jsp?a=1";
	 if(baseTaskType != null && baseTaskType != ""){
		 url = url + "&baseTaskType=" + baseTaskType;
	 }
	 if(ownSign != null&& ownSign != ""){
		 url = url + "&ownSign=" + ownSign;
	 }
	 if(ip != null&& ip != ""){
		 url = url + "&ip=" + ip;
	 }
	 if(orderStr != null&& orderStr != ""){
		 url = url + "&orderStr=" + orderStr;
	 }
	 window.location.href =  url;
}
</script>
