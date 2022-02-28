<%@ page import="mylist.model.User" %>
<%@ page import="mylist.model.Category" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.02.2022
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyList</title>
</head>
<body>

<%
    User user = (User) session.getAttribute("user");
    List<mylist.model.List> items = (List<mylist.model.List>) request.getAttribute("items");
%>

<div style="margin:0 auto;width: 1000px;height: 1000px;border: 1px solid">
    <a href="/home" style="padding: 10px">Նախորդ</a>
    <div>Բարի Գալուստ <%=user.getName()%>, | <a href="/myItems"> Իմ հայտարարությունները</a>| <a href="/addItem">
        Ավելացնել հայտարարություն </a> | <a href="/logout">Դուրս գալ</a></div>


    <div>
        <%if (items != null) {%>
        <%
            for (mylist.model.List item : items) {%>
        <a href="/singleItem?id=<%=item.getId()%>">
            <div style="width: 160px;float: left">
                <div>
                    <%if (item.getPicUrl() != null && !item.getPicUrl().equals("")) {%>
                    <img src="/getImage?pic_url=<%=item.getPicUrl()%>" width="150px">
                    <%} else {%>
                    <img src="/img/image-not-found.jpg" width="150px">
                    <%}%>
                </div>
                <div>
                    <span><%=item.getTitle()%> | <%=item.getPrice()%></span>
                </div>
            </div>
        </a>
        <%
                }
            }
        %>
    </div>
</div>

</body>
</html>
