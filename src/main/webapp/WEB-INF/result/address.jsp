<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 15/05/2021
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<ul>

    <li> id_indirizzo:${address.id} </li>
    <li> via: ${address.street}</li>
    <li> indirizzo:${address.streetNumber}</li>
    <li>   CAP:${address.postalCode}</li>
    <li>   città:${address.city}</li>
    <li>   provincia:${address.province}</li>
    <li>    id_account: ${address.account.id} </li>
    <li>     email: ${address.account.email} </li>
    <li>  password:${address.account.password} </li>
    <li>  username:${address.account.username} </li>
    <li>  amministratore: ${address.account.admin} </li>
    </tr>
</ul>

</body>
</html>
