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
    ���ǵ�<%=100+count.intValue()%>���ÿ�!

    һ����<%=1000000%>���ÿ�!
</center>
</body>
</html>
