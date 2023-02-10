<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Carrello"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>

        .checked {
            color: orange;
        }

        #titolo {

            width: 100%;
        }

        #prodottoDesiderato {
            display: flex;
            flex-flow: row wrap;
            width: 100%;
            padding-left: 30px;
            padding-right: 30px;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        #cover {
            width: 120px;
            border-radius: 10px;
            box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.7);
        }

        #descrizione {
            width: 40%;
            display: flex;
            flex-flow: column wrap;
            justify-content: space-between;

        }

        #bottoni {
            width: 30%;
        }

        #titologioco {
            width: 100%;
        }

        #valutazione {
            width: 100%;
            display: flex;
            flex-flow: row nowrap;
            justify-content: space-between;
            align-items: center;
        }

        #prezzo {
            width: 100%;
            display: flex;
            flex-flow: row nowrap;
            justify-content: space-between;
            align-items: center;
        }

        .divprezzo {
            width: 30%;
        }


        .button {

            background-color: #ee3124;
            color: #EEEEEE;
            margin: 25px 100px 0px 100px;
            border-radius: 5px;
            border: 2px solid #ee3124;
            padding: 5px;
            width: 198px;
            height: 36px;
            border-radius: 10px;
            box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.7);

        }

        .button:hover {
            background-color: #ad0e06;
            color: #EEEEEE;
        }

        a {
            font-size: 20px;

        }


    </style>


</head>
<body>
<!-- header -->


<jsp:include page="/WEB-INF/views/partials/headerUtent.jsp"></jsp:include>


<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarUtent.jsp"></jsp:include>


<aside class="containerBackground">

    <section class="containerCenter" id="ajax">

        <div class="generalCart">
            <div class="CrtTitolo">

                <p>Lista Desideri</p>
            </div>

            <c:forEach items="${lista.prodotti}" var="prodotto">
            <div id="prodottoDesiderato">

                <div id="cover">
                    <img style="border-radius: 10px;" src="${pageContext.request.contextPath}/cover/${prodotto.cover}">
                </div>
                <div id="descrizione">

                    <div id="titologioco">
                        <h1><p>${prodotto.productName}</p></h1>
                    </div>
                    <div id="valutazione">
                        <div style="width: 50%" id="${prodotto.id}">
                            <span id="1" class="fa fa-star "></span>
                            <span id="2" class="fa fa-star "></span>
                            <span id="3" class="fa fa-star "></span>
                            <span id="4" class="fa fa-star "></span>
                            <span id="5" class="fa fa-star "></span>
                            <script>
                                valutazioneChecked(${prodotto.valutazioneMedia}, ${prodotto.id});
                            </script>
                        </div>
                    </div>
                    <div id="prezzo">

                        <div class="divprezzo"><p>${prodotto.price}&euro;</p></div>
                        <div class="divprezzo"><p>${prodotto.platformName}</p></div>
                        <div class="divprezzo"><p>${prodotto.categoryName}</p></div>

                    </div>


                </div>


                <div id="bottoni">


                    <a id="a${prodotto.id}"
                       href="${pageContext.request.contextPath}/GestioneAcquistiController/addCart?id=${prodotto.id}">
                        <button id="button${prodotto.id}" class="button">aggiungi al carrello</button>
                    </a>
                    <c:forEach items="${prodottiNelCarrello}" var="prodotto1">
                        <c:set var="valore" value="${prodotto.id}"></c:set>
                        <c:if test="${prodotto1.id ==valore}">

                            <script>
                                document.getElementById("a${prodotto.id}").setAttribute("href", "");
                                document.getElementById("button${prodotto.id}").innerHTML="Prodotto nel carrello";
                                document.getElementById("a${prodotto.id}").style.pointerEvents="none";
                                document.getElementById("button${prodotto.id}").style.backgroundColor="#FFFFFF";
                                document.getElementById("button${prodotto.id}").style.color="#000000";
                                document.getElementById("button${prodotto.id}").style.border="none";
                                document.getElementById("button${prodotto.id}").style.boxShadow="none";
                            </script>

                        </c:if>

                    </c:forEach>
                    <a href="${pageContext.request.contextPath}/ListaDesideriController/rimuoviListaDesideri?id=${prodotto.id}">
                        <button class="button">rimuovi</button>
                    </a>

                </div>


            </div>

            </c:forEach>


    </section>
</aside>


<!-- footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>