<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="andrea"/>

    </jsp:include>
    <style>


        p {


            margin: 5px 0 0 0;
            display: none;
            font-size: 15px;
            color: #ee3124;
        }


    </style>


    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>


</head>

<body>


<div class="conteinerRegistrazione">
    <form class="form1" name="modulo" onsubmit="return (validateNome() && validateCognome())"
          action="${pageContext.request.contextPath}/GestioneUtenteController/updateDati" method="post">

        <div id="num1">
            <div class="label1">
                <div><label> nome *</label></div>
                <div><input onblur="validateNome()" type="text" name="nome" id="nome" placeholder="nome"
                            value="${account.firstName}" required>
                    <p id="pr6"></p>
                </div>


            </div>

            <div class="label1">
                <div><label class="label" for="cognome"> Cognome </label></div>
                <div><input type="text" name="cognome" id="cognome" value="${account.lastName}">
                    <p id="pr7"></p>
                </div>

            </div>

            <div class="label1">
                <div><label> data nascita *</label></div>
                <div><input type="date" name="dataNascita" id="dataN" value="${account.date}" disabled>
                    <p id="pdata"></p>
                </div>
            </div>

        </div>
        <div id="num5">
            <button class="button" type="submit">modifica</button>
        </div>
    </form>
</div>

</body>
</html>
