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
        <jsp:param name="title" value="Profilo"/>
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
            box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.4);
            border-radius: 10px;
            padding: 5px;
        }

        .generalProfilo img {
            width: 50px;
            margin: 10px;
            box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.4);
            border-radius: 10px;
        }

        .sottodivPro {

            display: flex;
            flex-flow: column wrap;
            box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.2);
            margin: 10px;
            height: 200px;
            border-radius: 10px;
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
            border-radius: 10px;
        }

        .sottodivPro1 p {
            margin: 20px;
            font-style: italic;
            font-weight: normal;
        }

        .sottodivPro1 b {
            color: #ee3124;
        }


        button {

            color: #323233;
            margin: 10px;
            border-radius: 5px;
            padding: 5px;
            cursor: pointer;
            box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.3)

        }

        button:hover {
            transform: scale(1.1);
        }

        .modifica {

            display: flex;
            flex-flow: row;
            align-items: center;

        }

        @media screen and (max-width: 1024px) {
            .sottodivPro {

                display: flex;
                flex-flow: column wrap;
                box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.2);
                margin: 10px;
                height: auto;
                border-radius: 10px;
            }
        }

    </style>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>


</head>
<body>

<script>

    var er1 =${modificaPassword};
    if (er1 == true) {
        JSalertSuccess("La password ?? stata cambiata!");
    }
</script>

<script>

    var er1 =${modificaEmail};
    if (er1 == true) {
        JSalertSuccess("L'email  ?? stata cambiata !");
    }
</script>


<script>

    var er1 =${modificaDati};
    if (er1 == true) {
        JSalertSuccess("Hai aggiornato i tuoi dati!");
    }


</script>


<script>
    var er1 =${modificaUsername};

    if (er1 == true) {
        JSalertSuccess("L'username  ?? stata cambiata!");
    }
</script>



<!-- header -->


<jsp:include page="/WEB-INF/views/partials/headerUtent.jsp"></jsp:include>


<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarUtent.jsp"></jsp:include>


<aside class="containerBackground">

    <section class="containerCenter" id="ajax">


        <div class="generalProfilo">
            <h3>I miei dati</h3>

            <div class="modifica"><img src="${pageContext.request.contextPath}/icons/icons8-utente-60.png"> <a
                    href="${pageContext.request.contextPath}/GestioneUtenteController/showDataClient">
                <button>modifica</button>
            </a>
            </div>
            <div class="sottodivPro">

                <p><b>nome</b> <br>
                    ${account.firstName}
                </p>

                <p><b> cognome</b><br>
                    ${account.lastName}
                </p>


                <p><b>data di nascita</b><br>

                    ${account.dataString}
                </p>


            </div>

            <div class="modifica">

                <img src="${pageContext.request.contextPath}/icons/icons8-lucchetto.gif">

                <a href="${pageContext.request.contextPath}/GestioneUtenteController/showUpdatePassword">
                    <button>modifica</button>
                </a>
            </div>
            <div class="sottodivPro1">

                <p><b>la tua passsword</b> <br>
                    ***************
                </p>
            </div>

            <div class="modifica">

                <img src="${pageContext.request.contextPath}/icons/icons8-nuovo-messaggio-50.png">

                <a href="${pageContext.request.contextPath}/GestioneUtenteController/showUpdateEmail">
                    <button>modifica</button>
                </a>
            </div>

            <div class="sottodivPro1">

                <p><b> la tua email</b> <br>
                    ${account.email}
                </p>

            </div>


            <div class="modifica">
                <img src="${pageContext.request.contextPath}/icons/icons8-nome-utente-100.png">

                <a href="${pageContext.request.contextPath}/GestioneUtenteController/showUpdateUsername">
                    <button>modifica</button>
                </a>
            </div>
            <div class="sottodivPro1">

                <p><b> la tua username</b><br>
                    ${account.username}
                </p>

            </div>

        </div>

    </section>
</aside>

<!-- footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>




