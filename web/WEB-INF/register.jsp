<%--
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
<a href="/home">Նախորդ</a>
<form action="/register" method="post">
    <input type="text" name="name" placeholder="Անուն"><br>
    <input type="text" name="surname" placeholder="Ազգանուն"><br>
    <input type="text" name="phone" placeholder="Հեռախոսահամար"><br>
    <input type="email" name="email" placeholder="էմեյլ"><br>
    <input type="password" name="password" placeholder="Գաղտնաբառ"><br>
    <input type="submit" value="Գրանցում">
</form>


</body>
</html>
