
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


</head>

<body>

<div class="conteinerLogin">
<form class="form" name="modulo"  action="${context}/AccountServlet/login"  method="post">

        <label class="label" for="email">email</label>
        <input   type="email" name="email" id="email"  placeholder="email" required>
        <br>

        <label class="label" for="password">password </label>
        <input type="password" name="password" id="password"  placeholder="password" required>
        <br>
        <p></p>
        <button class="button"  type="submit" >login</button>


</form>
   <div class="sottobloccoLogin">
    <p>Non hai un model.dao.account?</p> <a href="${context}/AccountServlet/registrazione">Registrati ora!</a>
   </div>
    </div>
</body>
</html>
