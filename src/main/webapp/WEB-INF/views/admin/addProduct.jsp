<%@ page contentType="text/html;charset=iso-8859-1" language="java" %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Aggiungi Prodotto"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>

    <style>

        .f_product label {

            color: #1a1a1a;
            margin-bottom: 10px;
            font-weight: 500;
            font-style: italic;


        }


        #number1 {
            padding: 20px;
            display: flex;
            flex-flow: row;
            justify-content: space-between;

        }

        #number1 div {

            display: flex;
            flex-flow: column;


        }

        #number2 {
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
            flex-flow: column;
        }


        #number3 {
            padding: 20px;
            display: flex;
            flex-flow: row nowrap;
            justify-content: space-between;

        }

        #number3 div {

            display: flex;
            flex-flow: column;

        }


        #number4 {
            padding: 20px;
            display: flex;
            flex-flow: row nowrap;
            justify-content: space-between;
            align-items: center;


        }

        #number4 div {

            display: flex;
            flex-flow: column;
        }

        #number5 {
            display: flex;
            justify-content: center;
            align-items: center;
        }


    </style>


</head>
<body>


<!-- header-->

<jsp:include page="/WEB-INF/views/partials/headerAdmin.jsp"></jsp:include>

<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarAdmin.jsp"></jsp:include>


<aside class="containerBackground">

    <section class="containerCenter" id="ajax">

        <form class="f_product" action="${pageContext.request.contextPath}/GestioneProdottoController/createProduct"
              method="post" enctype="multipart/form-data">

            <div id="number1">
                <div>
                    <label for="nome">Nome del prodotto</label>
                    <input type="text" minlength="5" maxlength="50" id="nome" name="nome" required>
                </div>
                <div>
                    <label for="prezzo">Prezzo di base</label>
                    <input type="text" id="prezzo" name="prezzo" required>
                </div>
            </div>

            <div id="number2">

                <label for="content">Contenuto (max 5000 caratteri)</label>
                <textarea id="content" rows="20" maxlength="5000" cols="70" name="description" required></textarea>

            </div>

            <div id="number3">

                <div>
                    <label for="cover">Immagine di copertina</label>
                    <input type="file"
                           accept=".apng, .avif, .gif, .jpg, .jpeg, .jfif, .pjpeg, .pjp, .png, .svg, .webp"
                           id="cover" name="cover" required>
                </div>

                <div>
                    <label for="datau">data uscita</label>
                    <input type="date" name="data" id="datau" required>
                </div>
            </div>

            <div id="number4">


                <div>
                    <select name="categoria" required>
                        <option value="CASUAL-GAME">CASUAL-GAME</option>
                        <option value="AZIONE-AVVENTURA">AZIONE-AVVENTURA</option>
                        <option value="SIMULAZIONE">SIMULAZIONE</option>
                        <option value="SPORT">SPORT</option>
                        <option value="RPG">RPG</option>
                        <option value="GUIDA">GUIDA</option>
                    </select>
                </div>

                <div>
                    <select name="piattaforma" required>
                        <option value="PC">PC</option>
                        <option value="PS4">PS4</option>
                        <option value="XBOX">XBOX</option>
                    </select>
                </div>
            </div>

            <div id="number5">
                <button class="button" type="submit">inserisci prodotto</button>
            </div>
        </form>

    </section>
</aside>

<!--footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>


</body>
</html>
