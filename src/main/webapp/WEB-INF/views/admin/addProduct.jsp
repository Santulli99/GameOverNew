<%@ page contentType="text/html;charset=iso-8859-1" language="java" %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Aggiungi Prodotto"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>
    <script src="/GameOverNew_war_exploded/js/validate.js"></script>

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

        p {

            margin: 5px 0 0 0;
            display: none;
            font-size: 15px;
            color: #ee3124;
        }


    </style>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
<script>

    var a=${success};

    if(a==false){
        JSalertWarning("Prodotto non aggiunto.\nAttenzione alcuni campi potrebbero essere non validi.");
    }

</script>

<!-- header-->

<jsp:include page="/WEB-INF/views/partials/headerAdmin.jsp"></jsp:include>

<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarAdmin.jsp"></jsp:include>


<aside class="containerBackground">

    <section class="containerCenter" id="ajax">

        <form class="f_product" action="${pageContext.request.contextPath}/GestioneProdottoController/createProduct"
              method="post" onsubmit="return validateAddProdotto()" enctype="multipart/form-data">

            <div id="number1">
                <div>
                    <label for="nome">Nome del prodotto</label>
                    <input type="text" onblur="validateNomeProdotto()" minlength="3" maxlength="50" id="nome" name="nome" required>
                    <p id="pr1"></p>
                </div>
                <div>
                    <label for="prezzo">Prezzo di base</label>
                    <input type="text" onblur="validatePrezzoProdotto()" id="prezzo" name="prezzo" required>
                    <p id="pr2"></p>
                </div>
            </div>

            <div id="number2">

                <label for="content">Contenuto (max 5000 caratteri)</label>
                <textarea id="content" onblur="validateDescrizioneProdotto()" rows="20" maxlength="5000" cols="70" name="description" required></textarea>
                <p id="pr3"></p>
            </div>

            <div id="number3">

                <div>
                    <label for="cover">Immagine di copertina</label>
                    <input type="file" onblur="validateCoverProdotto()"
                           accept=".apng, .avif, .gif, .jpg, .jpeg, .jfif, .pjpeg, .pjp, .png, .svg, .webp"
                           id="cover" name="cover" required>
                    <p id="pr4"></p>
                </div>

                <div>
                    <label for="datau">Data Uscita</label>
                    <input type="date" name="data" id="datau" onblur="validateDataUscitaProdotto()" required>
                    <p id="pr5"></p>
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
