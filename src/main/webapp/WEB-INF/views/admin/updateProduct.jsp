<%@ page contentType="text/html;charset=iso-8859-1" language="java" %>

<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="Modifica Prodotto"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>


    <style>

        .f_product label{

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

        #number1 div{

            display: flex;
            flex-flow: column;


        }

        #number2{
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
            flex-flow: column;
        }

        #number5{
            display: flex;
            justify-content: flex-start;
            align-items: center;
        }

        #number6{
            position: absolute;
            bottom: 14px;
            left: 587px;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 10px;
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

        <form class="f_product" action="${pageContext.request.contextPath}/GestioneProdottoController/updateProduct"
              method="post"  enctype="multipart/form-data" >

            <div id="number1">
                <div>
                    <label for="nome" >Nome del prodotto</label>
                    <input value="${prodotto.productName}" type="text" minlength="5" maxlength="50" id="nome" name="nome"  required >
                </div>
                <div>
                    <label for="prezzo" >Prezzo di base</label>
                    <input value="${prodotto.price}" type="text"   id="prezzo" name="prezzo" required >
                </div>
            </div>

            <div id="number2">

                <label for="content" >Contenuto (max 5000 caratteri)</label>
                <textarea   id="content" rows="20" maxlength="5000" cols="70" name="description"  required >${prodotto.description}</textarea>

            </div>

            <div id="number5">
                <div><input type="hidden"  name="id"  value="${prodotto.id}">
                <button class="button" type="submit" >modifica prodotto</button>
                </div>
            </div>
        </form>


        <div id="number6">
            <a href="${pageContext.request.contextPath}/ProductServlet/deleteProduct?id=${prodotto.id}"> <button class="button" >elimina prodotto</button></a>

        </div>

    </section>
</aside>

<!--footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>


</body>
</html>
