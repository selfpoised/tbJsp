/**
 *
 *  基于taobao-pamirs-schedule: 2.0.3.7，管理中心使用mysql数据库
 *
 *  目的是为了启动jsp 管理页面，加载调度相关信息
 *
 *  如何配置jsp工程，参考：Intellij idea创建javaWeb以及Servlet简单实现: https://blog.csdn.net/yhao2014/article/details/45740111
 *
 *  解决jsp中文乱码问题，参考: 解决JSP中文乱码问题: http://www.cnblogs.com/chengkai/articles/2171848.html
 *
 *  jsp本地链接调用：
 *      查看调度服务器列表：
 *      	http://localhost:8080/serverList.jsp
 *
 *      特定任务类型信息：
 *          http://localhost:8080/taskTypeInfo.jsp?baseTaskType=PamirsScheduleTest
 *
 *      所以任务类型：
 *          http://localhost:8080/taskTypeList.jsp
 *
 *      调度历史：
 *          http://localhost:8080/serverHistoryList.jsp
 *
 *      增删清理调度任务：
 *          http://localhost:8080/createTaskType.jsp?action=createTaskType&taskType=tasktype1&dealBean=hello&queueIds=1,2,3
 *          http://localhost:8080/createTaskType.jsp?action=clearTaskType&taskType=tasktype1
 *          http://localhost:8080/createTaskType.jsp?action=deleteTaskType&taskType=tasktype1
 *
 *
 */
