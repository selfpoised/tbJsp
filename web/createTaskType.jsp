
<%@page import="com.taobao.pamirs.schedule.MonitorBean"%>
<%@page import="com.taobao.pamirs.schedule.TaskQueueInfo"%>
<%@page import="com.taobao.pamirs.schedule.ScheduleTaskType"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=GB2312" %>
<html>
<head>
<title>
������������
</title>
</head>
<body bgcolor="#ffffff">

<%
	String action = request.getParameter("action");
	String result = "";
	boolean isRefreshParent = false;
	String baseTaskType = request.getParameter("taskType");
	try {
		if (action.equalsIgnoreCase("createTaskType")) {
			ScheduleTaskType taskType = new ScheduleTaskType();
			taskType.setBaseTaskType(baseTaskType);
			taskType.setDealBeanName(request.getParameter("dealBean"));
			MonitorBean
					.getScheduleConfigCenterClient()
					.createBaseTaskType(taskType,
							request.getParameter("queueIds").split(","));
			result = "����" + baseTaskType + "�����ɹ���������";
			isRefreshParent = true;
		} else if (action.equalsIgnoreCase("clearTaskType")) {
			MonitorBean.getScheduleConfigCenterClient().clearTaskType(
					baseTaskType);
			result = "����" + baseTaskType + "��������Ϣ����ɹ���������";
			isRefreshParent = false;
		} else if (action.equalsIgnoreCase("deleteTaskType")) {
			MonitorBean.getScheduleConfigCenterClient().deleteTaskType(baseTaskType);
			result = "����" + baseTaskType + "ɾ���ɹ���������";
			isRefreshParent = true;
		}
	} catch (Throwable e) {
		result ="ERROR:" + e.getMessage(); 
		isRefreshParent = false;
	}
%>
<%=result%>
</body>
</html>
<% if(isRefreshParent == true){ %>
<script>
 parent.location.reload();
</script>
<%}%>