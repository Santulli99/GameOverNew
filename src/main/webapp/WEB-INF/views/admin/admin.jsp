
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="Homepage"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>

    <style>

        /*contenuto centrale vetrina*/
        .divsCenter{
            position: relative;
            width: 50%;
            padding: 20px;
            max-height: 50%;
            font-family: OpenSans;
            font-style: italic;
            font-weight: bold;
        }

        .divsCenter h3{
            font-weight: bold;

        }

        .divsCenter p {
            display: flex;
            justify-content: center;
            justify-items: center;
            align-content: center;
            border-radius: 10px;
            background: #8b8a8a;
            color:#efe9ea;
            padding: 10px 10px 10px 10px;

        }

        .divsCenter p:hover{
            position: relative;
            top:5px;
        }

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

    var a=${success};

    if(a==true){
        JSalertSuccess("Prodotto aggiunto con successo");
    }

</script>


<script>

    var b=${update};

    if(b==true){
        JSalertSuccess("Prodotto modificato con successo");
    }

    if(b==false){
        JSalertError("Prodotto non modificato");
    }
</script>


<script>

    var c=${delete};

    if(c==true){
        JSalertSuccess("Prodotto eliminato con successo");
    }

    if(c==false){
        JSalertError("Prodotto non eliminato");
    }
</script>


<!-- header-->

<jsp:include page="/WEB-INF/views/partials/headerAdmin.jsp"></jsp:include>

    <!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarAdmin.jsp"></jsp:include>



<aside class="containerBackground">

    <section class="containerCenter" id="ajax">

        <div class="divsCenter">
            <h3>Totale incasso mensile</h3>
            <p>${totale_incasso}&euro;</p>
        </div>

        <div class="divsCenter">
            <h3>Numero prodotti</h3>
            <p>${n_products}</p>
        </div>

        <div class="divsCenter">
            <h3>Numero clienti registrati</h3>
            <p>${n_client}</p>
        </div>

        <div class="divsCenter">
            <h3>Numero ordini mensili</h3>
            <p>${n_ordini}</p>
        </div>


    </section>
</aside>

<!--footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>


</body>
</html>
