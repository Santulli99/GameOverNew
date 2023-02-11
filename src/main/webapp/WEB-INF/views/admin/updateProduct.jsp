<%@ page contentType="text/html;charset=iso-8859-1" language="java" %>

<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Modifica Prodotto"/>
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

        #number5 {
            display: flex;
            justify-content: flex-start;
            align-items: center;
        }

        #number6 {
            position: absolute;
            bottom: 14px;
            left: 587px;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 10px;
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

    var a=${update};

    if(a==false){
        JSalertWarning("Prodotto non modificato.\nAttenzione alcuni campi potrebbero essre non validi.");
    }

</script>
<!-- header-->

<jsp:include page="/WEB-INF/views/partials/headerAdmin.jsp"></jsp:include>

<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarAdmin.jsp"></jsp:include>


<aside class="containerBackground">

    <section class="containerCenter" id="ajax">

        <form class="f_product" action="${pageContext.request.contextPath}/GestioneProdottoController/updateProduct"
              method="post" onsubmit="return validateUpdateProdotto()" enctype="multipart/form-data">

            <div id="number1">
                <div>
                    <label for="nome">Nome del prodotto</label>
                    <input value="${prodotto.productName}" onblur="validateNomeProdotto()" type="text" minlength="5" maxlength="50" id="nome"
                           name="nome" required>
                    <p id="pr1"></p>
                </div>
                <div>
                    <label for="prezzo">Prezzo di base</label>
                    <input value="${prodotto.price}" onblur="validatePrezzoProdotto()" type="text" id="prezzo" name="prezzo" required>
                    <p id="pr2"></p>
                </div>
            </div>

            <div id="number2">

                <label for="content">Contenuto (max 5000 caratteri)</label>
                <textarea id="content" onblur="validateDescrizioneProdotto()" rows="20"  maxlength="5000" cols="70" name="description"
                          required>${prodotto.description}</textarea>
                <p id="pr3"></p>
            </div>

            <div id="number5">
                <div><input type="hidden" name="id" value="${prodotto.id}">
                    <button class="button" type="submit">modifica prodotto</button>
                </div>
            </div>
        </form>


        <div id="number6">
            <a href="${pageContext.request.contextPath}/GestioneProdottoController/deleteProduct?id=${prodotto.id}">
                <button class="button">elimina prodotto</button>
            </a>

        </div>

    </section>
</aside>

<!--footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>


</body>
</html>
