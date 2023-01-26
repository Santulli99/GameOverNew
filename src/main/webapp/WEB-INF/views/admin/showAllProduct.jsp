<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'  %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="Prodotti"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>


    <style>

        .orders{

            width:100%;
        }

        .orders ul{
            display: flex;
            flex-flow: column;
            margin: 20px 10px 20px 10px;
            padding:0px;
        }

        .orders ul h3{
            text-align:left;
            font-weight: bold;
            font-style: italic;
            font-size: 25px;
            color: #ee3124;
            margin:10px;

        }

        .orders ul li{
            display: flex;
            flex-flow: row;
            box-shadow:0 0 8px 0 rgba(0,0,0,0.4);
            padding:2px;
            justify-content:space-between;
            align-items:center;
            margin:10px;
            list-style-type: none;
             font-size: 20px;

        }

        .orders  button{
            font-size: 16px;
            background-color: #ee3124;
            color: white;
            border-radius:5px;
            border:2px solid #ee3124;
            padding:5px;
            cursor: pointer;
            box-shadow:0 0 8px 0 rgba(0,0,0,0.4);
            margin-right: 15px;

        }


        .orders button:hover{

            background-color: #323233;
            border:2px solid #8b8a8a;

        }


        .orders p{

            margin:10px;
            font-family:OpenSans;
            font-style:italic;
            font-weight:normal;
            text-align: center;

        }
        .orders div {
            width: 180px;
            margin: 0 10px 0 10px;
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

        <div class="orders">

            <ul>
                <h3> Prodotti GameOver </h3>
                <li>
                    <div><p><b>Nome</b></p></div>
                    <div><p><b>Piattaforma</b></p></div>
                    <div><p><b>Categoria</b></p></div>
                    <div></div>

                </li>
                <c:forEach items="${prodottos}" var="prodotto">
                    <li>
                        <div><p>${prodotto.productName}</p></div>
                        <div><p> ${prodotto.platform.platformName} </p></div>
                        <div><p> ${prodotto.category.categoryName} </p></div>
                        <div><a href="${pageContext.request.contextPath}/ProductServlet/updateProduct?id=${model.dao.prodotto.id}"><button>Modifica</button></a>
                        <a href="${pageContext.request.contextPath}/ProductServlet/deleteProduct?id=${model.dao.prodotto.id}"><button>Elimina</button></a></div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </section>
</aside>

<!--footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>


</body>
</html>
