<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Login"/>
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


<jsp:include page="/WEB-INF/views/partials/headerGuest.jsp"></jsp:include>


<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarGuest.jsp"></jsp:include>


<aside class="containerBackground">

    <section class="containerCenter1" id="ajax">

        <div class="conteinerLogin">

            <label class="label" for="inteCarta">Intestatario carta</label>
            <input type="text" name="intestatario" id="inteCarta" placeholder="Nome e Cognome" required>
            <br>

            <label class="label" for="NumCarta">Numero Carta </label>
            <input type="text" name="numeroCarta" id="NumCarta" onkeyup="getCardType()" placeholder="Numero Carta"
                   required>
            <div >
                 <img  id="tipoCarta">

            </div>
            <br>
            <label class="label" for="dateCarta">Data scadenza carta </label>
            <input type="text" name="dataCarta" id="dateCarta" placeholder="MM/AAAA" required>
            <br>
            <label class="label" for="CvcCarta">Cvc </label>
            <input type="text" name="cvc" id="CvcCarta" placeholder="CVC" required>
            <br>
            <p></p>
            <a href="${pageContext.request.contextPath}/GestioneAcquistiController/">
                <button class="button" type="submit">conferma pagamento</button>
            </a>
        </div>


    </section>
</aside>

<!-- footer-->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>