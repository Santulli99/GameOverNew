<%--
  Created by IntelliJ IDEA.
  User: Gerry
  Date: 21/05/2021
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="andrea"/>

    </jsp:include>
    <style>

        .label{
            padding-bottom:10px;
            font-weight: bold;

        }

        p{
            margin: 5px 0 0 0;
            display: none;
            font-size: 15px;
            color: #ee3124;
        }
    </style>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</head>

<body>
<script>
    var a=${modificaPassword};
    if(a==true){
        JSalertSuccess("Password modificata con successo");
    }
</script>

<div class="conteinerLogin">
    <form class="form"  name="modulo"  onsubmit="return (validatePassword()&& confermaPassword())" action="${context}/AccountServlet/updatePassword"  method="post">


        <label class="label" for="password">Nuova password </label>
        <input onblur="validatePassword()" type="password" name="password" id="password"  placeholder="password" required>
        <p id="pr3"></p>
        <br>
        <label class="label" for="password1">Conferma password </label>
        <input onblur="confermaPassword()" type="password" name="password2" id="password1"  placeholder="conferma password" required>
        <p id="pr4"></p>
        <br>


        <button class="button"  type="submit">modifica</button>

    </form>

</div>
</body>
</html>
