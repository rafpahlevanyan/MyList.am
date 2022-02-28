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
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    mylist.model.List item = (mylist.model.List) request.getAttribute("item");
%>


<div style="margin:0 auto;width: 1000px;height: 1000px;border: 1px solid">

    <a href="/home" style="padding: 10px">Նախորդ</a>
    <% if (user == null) {%>
    <div style="float: right"><a href="/login">Լոգին</a> | <a href="/register"> Գրանցում</a></div>
    <%} else {%>
    <div>Բարի Գալուստ <%=user.getName()%>, | <a href="/myItems"> Իմ հայտարարությունները</a>| <a href="/addItem"> Ավելացնել հայտարարություն </a> | <a href="/logout">Դուրս գալ</a></div>

    <%}%>

    <div>
        <ul style="padding-top: 30px">
            <li style="display: inline;padding-left: 20px"><a
                    href="/home">Գլխավոր</a></li>
            <%
                for (Category category : categories) {%>
            <li style="display: inline;padding-left: 20px"><a
                    href="/home?catId=<%=category.getId()%>"><%=category.getName()%>
            </a>
            </li>
            <%}%>
        </ul>
    </div>

    <div>

<%--        <a href="/singleItem?id=<%=item.getId()%>">--%>
            <div style="width: 500px;float: left">
                <div>
                    <%if (item.getPicUrl() != null && !item.getPicUrl().equals("")) {%>
                    <img src="/getImage?pic_url=<%=item.getPicUrl()%>" width="500px">
                    <%} else {%>
                    <img src="/img/image-not-found.jpg" width="500px">
                    <%}%>
                </div>
                <div>
                    <span><%=item.getTitle()%> | <%=item.getPrice()%></span>
                </div>
                <span><%=item.getDescription()%></span>
                <div>
                    Օգտատիրոջ Տվյալները։ <%=item.getUser().getName()%><br> <%=item.getUser().getEmail()%><br>  <%=item.getUser().getPhone()%>
                </div>
            </div>
<%--        </a>--%>

    </div>
</div>

</body>
</html>
