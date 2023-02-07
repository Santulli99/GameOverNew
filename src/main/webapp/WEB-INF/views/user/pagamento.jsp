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

        .dataScadenza {
            display: flex;
            flex-flow: row nowrap;
        }


        .conteinerPagamento {

            display: flex;
            flex-flow: column nowrap;
            justify-items: center;
            justify-content: center;
            background-color: #f2f2f2;
            color: #1a1a1a;
            border-radius: 10px;
            position: relative;
            width: 550px;
            margin: auto;
            padding: 15px;

        }

        #boxPagamento1 {
            display: flex;
            flex-flow: column;
            width: 60%;

        }

        #boxPagamento2 {
            display: flex;
            flex-flow: column;
            width: 60%;
        }

        #boxPagamento3 {
            width: 100%;
            display: flex;
            flex-flow: column;
        }

        .buttonPagamento {

            background-color: #ee3124;
            color: white;
            margin: 25px 100px 0px 100px;
            border-radius: 5px;
            border: 2px solid #ee3124;
            padding: 5px;
        }

        #boxBottone {
            display: flex;
            flex-flow: row;
            justify-content: center;
            justify-items: center;
        }

        #descrizione {
            margin-bottom: 10px;
        }


    </style>

</head>


<body>


<jsp:include page="/WEB-INF/views/partials/headerUtent.jsp"></jsp:include>


<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarUtent.jsp"></jsp:include>


<aside class="containerBackground">

    <section class="containerCenter1" id="ajax">
        <form action="${pageContext.request.contextPath}/GestioneAcquistiController/createOrder"
              onsubmit="return validateAcquisto()" method="post">
            <div class="conteinerPagamento">

                <div id="boxPagamento1">
                    <label class="label" for="inteCarta">Intestatario carta</label>
                    <input type="text" onblur="checkIntestatario()" name="intestatario" id="inteCarta"
                           placeholder="Nome e Cognome" required>
                    <p id="pr1"></p>
                    <br>
                </div>

                <div id="boxPagamento2">
                    <label class="label" for="NumCarta">Numero Carta </label>
                    <input type="text" onblur="checkNumeroCarta()" name="numeroCarta" id="NumCarta"
                           onkeyup="getCardType()"
                           placeholder="Numero Carta" maxlength="16"
                           required>
                    <p id="pr2"></p>
                    <div>
                        <img id="tipoCarta">

                    </div>
                    <br>
                </div>

                <div id="boxPagamento3">
                    <div id="descrizione">
                        <label class="label" for="annoCarta">Data scadenza carta </label>
                    </div>

                    <div class="dataScadenza">
                        <input type="text" maxlength="2" size="2" name="meseCarta" id="meseCarta"
                               onblur="meseCheck0();meseCheck()"
                               placeholder="MM" required>
                        <b>&nbsp/&nbsp</b>
                        <input type="text" maxlength="4" size="3" name="annoCarta" id="annoCarta" placeholder="AAAA"
                               onblur="annoCheck()"
                               required>
                    </div>
                    <p id="pr3"></p>
                    <p id="pr5"></p>

                    <br>
                    <label class="label" for="CvcCarta">CVC/CVV </label>
                    <div>

                        <input type="text" onblur="checkCvC()" maxlength="4" size="3" name="cvc" id="CvcCarta"
                               placeholder="cvc" required>
                        <p id="pr4"></p>
                        <br>
                    </div>

                </div>
                <div id="boxBottone">
                    <button class="buttonPagamento" id="confermaPagamento" type="submit">conferma pagamento</button>
                </div>


            </div>
        </form>
    </section>
</aside>

<!-- footer-->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>