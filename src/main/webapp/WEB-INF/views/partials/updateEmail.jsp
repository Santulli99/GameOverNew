<%--
  Created by IntelliJ IDEA.
  User: Gerry
  Date: 07/07/2021
  Time: 23:48
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
    var a=${modificaEmail};
    if(a==true){
        JSalertSuccess("Email modificata con successo");
    }
</script>


<div class="conteinerLogin">
    <form class="form" name="modulo"  onsubmit=" return (validateEmail() && confermaEmail())"   action="${context}/AccountServlet/updateEmail"   method="post">


        <label class="label" for="email">Nuova email </label>
        <input onblur="validateEmail();checkEmailAjax();" type="email" name="email" id="email"  value="${account.email}" required>
        <p id="pr1" ></p>
        <p id="pr100"></p>
        <br>
        <label class="label" for="email1">Conferma email </label>
        <input onblur="confermaEmail()" type="email" name="email1" id="email1" required >
        <p id="pr2" ></p>
        <br>

        <button class="button"  type="submit">modifica</button>

    </form>

</div>
<script>

    var esiste=${esiste};

    if(esiste==true){

        JSalertWarning( "Hey, sembra che l'indirizzo email  corrisponda a un model.dao.account già esistente.");
    }

</script>




</body>
</html>
