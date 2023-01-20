<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 20/05/2021
  Time: 12:34
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
        <th>id_categoria</th> <th>NOME</th> <th>DESCRIZIONE</th>
        <th>NOME PRODOTTO</th><th>DESCRIZIONE PRO</th><th>PREZZO</th> <th>DATA USCITA</th>
    </tr>
    <c:forEach items="${categories}" var="category">

        <tr>
        <c:forEach items="${category.prodottos}" var="prodotto">
          <tr><td>     ${category.id}         </td>
              <td>     ${category.categoryName} </td>
              <td> ${category.description} </td>
            <td>${prodotto.productName} </td>
            <td>${prodotto.description}</td>
            <td>${prodotto.price}</td>
            <td>${prodotto.date}</td> </tr>
        </c:forEach>
        </tr>

    </c:forEach>
</table>


</body>
</html>
