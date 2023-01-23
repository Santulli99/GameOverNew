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
    var a=${modificaUsername};
    if(a==true){
        JSalertSuccess("Username modificato con successo");
    }
</script>

<div class="conteinerLogin">
    <form class="form" name="modulo" onsubmit="return (validateUsername()&&confermaUsername())" action="${context}/AccountServlet/updateUsername"   method="post">


        <label class="label" for="username">Nuova Username </label>
        <input onblur="validateUsername()" type="text" name="username" id="username"  value="${account.username}"  required>
        <p id="pr5"></p>
        <br>
        <label class="label" for="username2">Conferma Username </label>
        <input onblur="confermaUsername()" type="text" name="username2" id="username2"   required>
        <p id="pr16"></p>
        <br>

        <button class="button"  type="submit"   >modifica</button>

    </form>

</div>



</body>
</html>
