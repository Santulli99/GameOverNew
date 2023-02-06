<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Pagamento"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>

    <script src="${pageContext.request.contextPath}/js/validate.js"></script>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

    <style>
        .label {
            padding-bottom: 10px;
            font-weight: bold;
        }

        p {
            margin: 5px 0 0 0;
            display: none;
            font-size: 15px;
            color: #ee3124;
        }
    </style>

</head>


<body>


<jsp:include page="/WEB-INF/views/partials/headerUtent.jsp"></jsp:include>


<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarUtent.jsp"></jsp:include>


<aside class="containerBackground">

    <section class="containerCenter1" id="ajax">

        <div class="conteinerLogin">

            <label class="label" for="inteCarta">Intestatario carta</label>
            <input type="text" onblur="checkIntestatario()" name="intestatario" id="inteCarta" placeholder="Nome e Cognome" required>
            <p id="pr1"></p>
            <br>

            <label class="label" for="NumCarta">Numero Carta </label>
            <input type="text" onblur="checkNumeroCarta()" name="numeroCarta" id="NumCarta" onkeyup="getCardType()" placeholder="Numero Carta"
                   required>
            <p id="pr2"></p>
            <div >
                 <img  id="tipoCarta">

            </div>
            <br>
            <label class="label" for="dateCarta">Data scadenza carta </label>
            <input type="text" maxlength="7" size="7" onblur="checkDataScadenza()" name="dataCarta" id="dateCarta" placeholder="MM/AAAA" pattern="(0[1-9]|1[0-2])[/](20\d{2})" required>
            <p id="pr3"></p>
            <br>
            <label class="label" for="CvcCarta">Cvc </label>
            <input type="text" onblur="checkCvC()" name="cvc" id="CvcCarta" placeholder="CVC" required>
            <p id="pr4"></p>
            <br>
            <p></p>
            <a href="${pageContext.request.contextPath}/GestioneAcquistiController/createOrder">
                <button class="button" type="submit">conferma pagamento</button>
            </a>
        </div>


    </section>
</aside>

<!-- footer-->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>