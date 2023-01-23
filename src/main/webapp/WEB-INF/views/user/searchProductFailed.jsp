<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'  %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="Ricerca Prodotti"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>


    <style>

        #para1 {

            font-size: 18px;
            color: white;
            font-style: italic;
            font-weight:bold;


        }

    </style>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>



</head>
<body>
<script>

    var suc1=${successo};
    if(suc1==true) {
        JSalertSuccess("Il tuo acquisto è effettuato!");
    }

</script>

<script>
    i=0;
    bannerDinamic();

</script>




<!-- header -->


<jsp:include page="/WEB-INF/views/partials/headerUtent.jsp"></jsp:include>


<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarUtent.jsp"></jsp:include>



<aside class="containerBackground">

    <section class="containerCenter" id="ajax">

        <div class="vetrina">

            <div class="vetrinaAjax" >

                <img src="${context}/icons/icons8-ricerca-50.png">

                <p> Siamo spiacenti, nessun risultato trovato! <br>
                    Sembra che non ci siano giochi corrispondenti alla tua richiesta
                </p>

                <a href="${context}/AccountServlet/utente"><button class="button">Torna all'HomePage</button></a>


            </div>

        </div>

    </section>
</aside>

<!-- footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>
