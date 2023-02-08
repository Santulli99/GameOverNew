<%--
  Created by IntelliJ IDEA.
  User: Gerry
  Date: 22/06/2021
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Utente"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>


    <style>
        .generalProfilo {
            display: flex;
            flex-flow: column;
            width: 100%;

        }

        .generalProfilo h3 {
            margin: 10px;
            font-size: 35px;
            color: #ee3124;
            font-weight: bold;
            font-style: italic;
        }

        .generalProfilo img {
            width: 50px;
            margin: 10px;
            box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.4);
        }

        .sottodivPro {

            display: flex;
            flex-flow: column wrap;
            box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.2);
            margin: 10px;
            height: 200px;

        }

        .sottodivPro p {
            margin: 20px;
            font-style: italic;
            font-weight: normal;


        }

        .sottodivPro b {
            color: #ee3124;
        }


        .sottodivPro1 {

            display: flex;
            flex-flow: column wrap;
            box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.2);
            margin: 10px;
            height: 100px;


        }

        .sottodivPro1 p {
            margin: 20px;
            font-style: italic;
            font-weight: normal;
        }

        .sottodivPro1 b {
            color: #ee3124;
        }

        #cliente {
            display: flex;
            flex-flow: row nowrap;
            align-items: center;
        }

        #p1 {
            text-align: left;
            font-weight: bold;
            font-style: italic;
            font-size: 30px;
            color: #ee3124;
            margin: 10px;
        }

        #p2 {
            text-align: left;
            font-weight: normal;
            font-style: italic;
            font-size: 30px;
            color: #000000;
            margin: 10px;
        }

    </style>


</head>
<body>

<!-- header -->


<jsp:include page="/WEB-INF/views/partials/headerAdmin.jsp"></jsp:include>


<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarAdmin.jsp"></jsp:include>


<aside class="containerBackground">

    <section class="containerCenter" id="ajax">

        <div class="generalProfilo">
            <div id="cliente"><p id="p1">Dati cliente:</p>
                <p id="p2">${accountUtent.firstName} ${accountUtent.lastName}</p></div>

            <div><img src="${pageContext.request.contextPath}/icons/icons8-utente-60.png">
            </div>
            <div class="sottodivPro">

                <p><b>nome</b> <br>
                    ${accountUtent.firstName}
                </p>

                <p><b> cognome</b><br>
                    ${accountUtent.lastName}
                </p>

                <p><b>data di nascita</b><br>

                    ${accountUtent.dataString}
                </p>

            </div>

            <div><img src="${pageContext.request.contextPath}/icons/icons8-lucchetto.gif">
            </div>
            <div class="sottodivPro1">

                <p><b>la tua passsword</b> <br>
                    ***************
                </p>
            </div>

            <div><img src="${pageContext.request.contextPath}/icons/icons8-nuovo-messaggio-50.png">
            </div>

            <div class="sottodivPro1">

                <p><b> la tua email</b> <br>
                    ${accountUtent.email}
                </p>

            </div>


            <div><img src="${pageContext.request.contextPath}/icons/icons8-nome-utente-100.png">
            </div>
            <div class="sottodivPro1">

                <p><b> la tua username</b><br>
                    ${accountUtent.username}
                </p>

            </div>
        </div>

    </section>
</aside>

<!-- footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>
