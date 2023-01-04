<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 15/05/2021
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <li>CODICE FISCALE:${dataclient.cf}</li>
    <li>ID_CLIENTE:${dataclient.account.id}</li>
    <li>NOME:${dataclient.firstName} </li>
    <li>COGNOME:${dataclient.lastName}</li>
    <li>TELEFONO:${dataclient.cell}</li>
    <li>CITTA' DI NASCITA:${dataclient.city}</li>
    <li>DATA DI NASCITA:${dataclient.date}</li>

</ul>

</body>
</html>
