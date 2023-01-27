<%--
  Created by IntelliJ IDEA.
  User: Gerry
  Date: 29/06/2021
  Time: 09:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Prodotto"/>
        <jsp:param name="script" value="jqueryfunction.js"/>

    </jsp:include>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>


<style>
    .fa-heart {
        font-size: 35px;
        color: #1a1a1a;
    }

    .checked {
        color: #ee3124;
    }

    /*prodotto*/


    .prodotto {
        display: flex;
        flex-flow: column wrap;
        width: 100%;
        margin: 25px;


    }

    .prodottoTitolo {
        position: relative;
        display: flex;
        align-items: center;
        width: 100%;
        border-radius: 10px;
        box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.4);
        background-color: white;
        margin-bottom: 20px;
        color: #8b8a8a;
    }

    .prodottoTitolo p {
        margin-left: 10px;
        font-style: italic;
        font-weight: bold;
        font-size: 20px;


    }

    .prodotto img {

        width: 270px;
        border-radius: 10px;
        box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.7);
    }

    .prodottoimg {

        display: flex;
        flex-flow: row wrap;
        justify-content: space-between;
    }

    .latodetroCopertina {
        display: flex;
        flex-flow: column wrap;
        align-items: center;
        justify-content: center;
        justify-items: center;
        width: 650px;
        background-color: white;
        border-radius: 10px;
        box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.4);


    }

    .latodetroCopertina #p1 {
        font-style: italic;
        font-weight: bold;
        font-size: 30px;
        color: #8b8a8a;
        margin-bottom: 5px;
    }

    .latodetroCopertina #p2 {
        font-style: italic;
        font-weight: bold;
        font-size: 30px;
        color: #ee3124;
        margin-top: 5px;
        margin-bottom: 10px;
    }

    .latodetroCopertina #p2:hover {

        transform: scale(1.1);
    }


    .latodetroCopertina #p3 {
        margin-top: 30px;
        font-size: 18px;
        color: #8b8a8a;
        font-style: italic;
    }

    .descrizione {
        background-color: white;
        width: 100%;
        border-radius: 10px;
        box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.4);
        margin-top: 20px;

    }

    .descrizione h3 {
        font-size: 20px;
        text-align: center;
        font-weight: bold;
        font-style: italic;
        color: #8b8a8a;

    }

    .descrizione p {
        margin: 10px;
        font-size: 18px;
        color: #8b8a8a;
        font-style: italic;


    }

    #p_error {
        font-style: italic;
        font-weight: bold;
        font-size: 20px;
        color: #ee3124;
        margin-top: 5px;
        margin-bottom: 10px;
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

        <!--div prodotto  composto da 3 sotto div in colonna  -->

        <div class="prodotto">

            <!-- div titolo -->

            <div class="prodottoTitolo">

                <p>${prodotto.productName}</p>

            </div>

            <!-- div con 2 sotto div uno copertina e l'altro prezzo e carrello -->

            <div class="prodottoimg">

                <img src="${pageContext.request.contextPath}/cover/${prodotto.cover}">


                <div class="latodetroCopertina">

                    <p id="p1">DIGITALE</p>
                    <p id="p2">${prodotto.price}&euro;</p>


                    <!-- ciclo che verifica se il prodotto è già nel carrello-->

                    <c:set var="verifica" scope="page" value="${0}"></c:set>
                    <!-- variabile di controllo per lo script-->

                    <c:forEach items="${carrello.cartItems}" var="pro">

                        <c:set var="valore" value="${prodotto.id}"></c:set>

                        <c:if test="${pro.item.id ==valore}">

                            <p id="p_error"> Attenzione!!!! prodotto già nel carrello </p>

                            <c:set var="verifica" scope="page" value="${1}"></c:set>
                        </c:if>

                    </c:forEach>


                    <a id="aa3" href="${pageContext.request.contextPath}/CartServlet/addCart?id=${prodotto.id}">
                        <button id="aa4" class="button">aggiungi al carrello</button>
                    </a>
                    <a id="aa3"
                       href="${pageContext.request.contextPath}/RecensioneController/scriviRecensione?id=${prodotto.id}">
                        <button id="aa5" class="button">Aggiungi recensione</button>
                    </a>
                    <a id="aa7"
                       href="${pageContext.request.contextPath}/ListaDesideriController/aggiungiListaDesideri?id=${prodotto.id}"
                       class="fa fa-heart" style="text-decoration: none"></a>
                    <script>
                        var agg =${aggiunto};
                        if (agg == true) {
                            document.getElementById("aa7").className = "fa fa-heart checked";
                            document.getElementById("aa7").removeAttribute("href");
                        }
                    </script>
                    <script defer>
                        var veri =${verifica};

                        if (veri == 1) {
                            document.getElementById("aa3").style.display = "none";
                            document.getElementById("aa4").style.display = "none";
                        }


                    </script>

                    <p id="p3">
                        Codice articolo
                        ${prodotto.id}
                        <br>
                        Genere
                        ${prodotto.category.categoryName}
                        <br>
                        Rilascio
                        ${prodotto.dataString}
                    </p>

                </div>
            </div>

            <!-- div per la descrizione  -->

            <div class="descrizione">
                <h3>descrizione</h3>
                <p>${prodotto.description}</p>
            </div>

            <div class="descrizione">
                <h3>Recensioni</h3>
                <c:forEach items="${recensioni}" var="recensione">
                    <div style="border:solid 2px black">
                        <p>${recensione.descrizione}</p>
                        <p>
                        <div style="width: 50%" id="${recensione.account.id}">
                            <span id="1" class="fa fa-star "></span>
                            <span id="2" class="fa fa-star "></span>
                            <span id="3" class="fa fa-star "></span>
                            <span id="4" class="fa fa-star "></span>
                            <span id="5" class="fa fa-star "></span>
                            <script>
                                valutazioneChecked(${recensione.valutazione}, ${recensione.account.id});
                            </script>
                        </div>
                        </p>
                    </div>
                </c:forEach>
            </div>

        </div>


    </section>
</aside>

<!-- footer -->

<div>
    <jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>
</div>


</body>


</html>
