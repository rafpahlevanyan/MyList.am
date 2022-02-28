<%@ page import="java.util.List" %>
<%@ page import="mylist.model.Category" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.02.2022
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Լոգին</title>
</head>
<body>

<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
%>
<a href="/home" style="padding: 10px">Նախորդ</a>
<form action="/addItem" method="post" enctype="multipart/form-data">
    <input type="text" name="title" placeholder="Անուն"><br>
    <input type="number" name="price" placeholder="Գին"><br>
    <select name="cat_id">
        <%
            for (Category category : categories) {%>
        <option value="<%=category.getId()%>"><%=category.getName()%>
        </option>
        <% }%>
    </select>
    <textarea name="description" placeholder="Նկարագրություն"></textarea><br>
    <input type="file" name="picture"><br>

    <input type="submit" value="Գրանցում">
</form>


</body>
</html>
