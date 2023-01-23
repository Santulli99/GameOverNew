<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'  %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="Profilo "/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>


    <style>
        .generalProfilo{
            display:flex;
            flex-flow:column;
            width:100%;

        }
        .generalProfilo h3{
            margin:10px;
            font-size:35px;
            color:#ee3124;
            font-weight:bold;
            font-style:italic;
            box-shadow:0 0 8px 0 rgba(0,0,0,0.4);
            border-radius: 10px;
            padding: 5px;
        }

        .generalProfilo img{
            width:50px;
            margin:10px;
            box-shadow:0 0 8px 0 rgba(0,0,0,0.4);
            border-radius: 10px;
        }
        .sottodivPro{

            display:flex;
            flex-flow:column wrap;
            box-shadow:0 0 8px 0 rgba(0,0,0,0.2);
            margin:10px;
            height:200px;
            border-radius: 10px;
        }
        .sottodivPro p{
            margin:20px;
            font-style:italic;
            font-weight:normal;


        }
        .sottodivPro b{
            color:#ee3124;
        }



        .sottodivPro1{

            display:flex;
            flex-flow:column wrap;
            box-shadow:0 0 8px 0 rgba(0,0,0,0.2);
            margin:10px;
            height:100px;

            border-radius: 10px;
        }

        .sottodivPro1 p{
            margin:20px;
            font-style:italic;
            font-weight:normal;
        }

        .sottodivPro1 b{
            color:#ee3124;
        }


        button{

            color: #323233;
            margin:10px;
            border-radius:5px;
            padding:5px;
            cursor: pointer;
            box-shadow:0 0 8px 0 rgba(0,0,0,0.3)

        }

        button:hover{
            transform: scale(1.1);
        }

        .modifica{

            display: flex;
            flex-flow: row;
            align-items: center;

        }


    </style>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>




</head>


<body>

<script>

    var er1=${modifica};
    if(er1==true) {
        JSalertSuccess("La password è stata cambiata!");
    }
</script>



<script>

    var er1=${modificaEmail};
    if(er1==true) {
        JSalertSuccess("L'email  è stata cambiata !");
    }
</script>


<script>

    var er1=${modificaDati};
    if(er1==true) {
        JSalertSuccess("Hai aggiornato i tuoi dati!");
    }


</script>


<script>
    var er1=${modificaUsername};

    if(er1==true) {
        JSalertSuccess("L'username  è stata cambiata!");
    }
</script>


<script>
    var er1=${modificaAddress};

    if(er1==true) {
        JSalertSuccess("Hai aggiornato i tuoi dati!");
    }
</script>









<!-- header-->

<jsp:include page="/WEB-INF/views/partials/headerAdmin.jsp"></jsp:include>

<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarAdmin.jsp"></jsp:include>



<aside class="containerBackground" id="ajax">

    <section class="containerCenter">
        <div class="generalProfilo">
            <h3>I miei dati</h3>

            <div class="modifica">  <img src="${context}/icons/icons8-utente-60.png"> <a href="${context}/DataClientServlet/showDataClient"><button>modifica</button></a>
            </div>
            <div class="sottodivPro">

                <p><b>nome</b> <br>
                    ${account.dataClient.firstName}
                </p>

                <p><b> cognome</b><br>
                    ${account.dataClient.lastName}
                </p>

                <p><b>telefono</b> <br>
                    ${account.dataClient.cell}
                </p>

                <p><b>citta' di  nascita</b><br>
                    ${account.dataClient.city}
                </p>

                <p><b>data di nascita</b><br>

                    ${account.dataClient.dataString}
                </p>

                <p><b>codice fiscale</b><br>
                    ${account.dataClient.cf}
                </p>


            </div >

            <div class="modifica">

                <img src="${context}/icons/icons8-lucchetto.gif">

                <a href="${context}/AccountServlet/showUpdatePassword"><button>modifica</button></a>
            </div>
            <div class="sottodivPro1">

                <p><b>la tua passsword</b> <br>
                    ***************
                </p>
            </div>

            <div class="modifica">

                <img src="${context}/icons/icons8-nuovo-messaggio-50.png">

                <a href="${context}/AccountServlet/showUpdateEmail"><button>modifica</button></a>
            </div>

            <div  class="sottodivPro1">

                <p><b> la tua email</b> <br>
                    ${account.email}
                </p>

            </div>


            <div class="modifica">
                <img src="${context}/icons/icons8-nome-utente-100.png">

                <a href="${context}/AccountServlet/showUpdateUsername"><button>modifica</button></a>
            </div>
            <div class="sottodivPro1">

                <p><b> la tua username</b><br>
                    ${account.username}
                </p>

            </div>

            <div class="modifica">

                <img src="${context}/icons/icons8-home-page-50.png">

                <a href="${context}/AddressServlet/showUpdateAddress"><button>modifica</button></a>
            </div>

            <div class="sottodivPro">

                <p><b> residenza</b><br>
                    ${account.address.city}
                </p>


                <p><b>provincia</b> <br>
                    ${account.address.province}
                </p>


                <p><b>via</b><br>
                    ${account.address.street}
                </p>

                <p><b>civico</b><br>
                    ${account.address.streetNumber}
                </p>

                <p><b>CAP</b><br>
                    ${account.address.postalCode}
                </p>

            </div>
        </div>
    </section>
</aside>

<!--footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>


</body>
</html>
