<%--
  Created by IntelliJ IDEA.
  User: Gerry
  Date: 14/05/2021
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html>
<head>
    <title>Title</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
            text-align: left;
        }
    </style>
</head>
<body>

<h1>DatABASE</h1>


    <table>
        <tr>
            <th>id</th> <th>email</th> <th>password</th> <th>username</th><th>admin</th>
            <th>CODICE FISCALE</th><th>NOME</th> <th>COGNOME</th><th>TELEFONO</th> <th>CITTA NASCITA</th><th>DATA DI NASCITA</th>
            <th>id_indirizzo</th><th>via</th> <th>indirizzo</th> <th> CAP</th> <th>città</th> <th>provincia</th>
        </tr>


<c:forEach items="${accounts}" var="account">

    <tr>

     <td>    id: ${account.id}         </td>
     <td>     email: ${account.email} </td>
    <td>  password:${account.password} </td>
    <td>  username:${account.username} </td>
    <td>  amministratore: ${account.admin} </td>
     <td>CODICE FISCALE:${account.dataClient.cf}</td>

     <td>NOME:${account.dataClient.firstName} </td>
     <td>COGNOME:${account.dataClient.lastName}</td>
     <td>TELEFONO:${account.dataClient.cell}</td>
     <td>CITTA' DI NASCITA:${account.dataClient.city}</td>
     <td>DATA DI NASCITA:${account.dataClient.date}</td>
     <td> id_indirizzo:${account.address.id} </td>
     <td> via: ${account.address.street}</td>
     <td> indirizzo:${account.address.streetNumber}</td>
     <td>   CAP:${account.address.postalCode}</td>
     <td>   città:${account.address.city}</td>
     <td>   provincia:${account.address.province}</td>
 </tr>

</c:forEach>
    </table>

<a  href="AccountServlet"> next page </a>


</body>
</html>
