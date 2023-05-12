<%@ page contentType="text/html; charset=gb2312"%>
<html>
<body>
<h2>Hello World!</h2>
<%
    if (application.getAttribute("count") == null) {
        application.setAttribute("count", new Integer(0));
    }
    Integer count = (Integer) application.getAttribute("count");
    application
            .setAttribute("count", new Integer(count.intValue() + 1));
    count = (Integer) application.getAttribute("count");
%>
<center>
    这是第<%=100+count.intValue()%>个访客!

    一共有<%=1000000%>个访客!
</center>
</body>
</html>
