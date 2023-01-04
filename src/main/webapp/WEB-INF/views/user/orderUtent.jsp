<%--
  Created by IntelliJ IDEA.
  User: Gerry
  Date: 29/06/2021
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'  %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="Ordine"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>

    <style>

        .generalOrder{
            display: flex;
            flex-flow: column nowrap;
            width: 100%;
            background-color: white;
            box-shadow:0 0 8px 0 rgba(0,0,0,0.7);
            border-radius:10px;


        }

        .ordTitolo {
            color:#8b8a8a;
            margin:30px 30px 5px 30px;
            font-style: italic;
            font-weight: bold;
            font-size: 25px;
            padding-left:20px;
            box-shadow:0 0 8px 0 rgba(0,0,0,0.7);
            border-radius:10px;

        }


        .headerOrd{
            border-radius:10px;
            padding:0px;
            margin: 0px 30px 0px 30px;
            display: flex;
            flex-flow: row nowrap;
            justify-items: center;
            justify-content: space-between;
            align-items: center;



        }

        .headerOrd img{
            border-radius:10px;
            margin-right: 20px;
            width:120px;
            box-shadow:0 0 8px 0 rgba(0,0,0,0.7);
        }

        .headerOrd #a2{
            display: flex;
            flex-flow: row;
            margin:10px;
            font-size: 18px;
            color: #8b8a8a;
            font-style: italic;
            font-weight:bold;

        }

        .headerOrd #a3{
            display: flex;
            flex-flow: column wrap;
            justify-items: center;
            justify-content: space-between;
            align-items: center;
        }

        .headerOrd #a3 p{

            font-style: italic;
            font-weight: bold;
            font-size: 20px;
            color: #ee3124;
            margin-top:5px;
            margin-bottom:10px;

        }


        .headerOrd #a3 a{
            text-decoration: none;
            font-size: 18px;
            color: #8b8a8a;
            font-style: italic;
            font-weight:bold;
        }


        .footerOrd{

            display: flex;
            flex-flow: row wrap;
            justify-content: space-between;


        }

        .footerOrd #a4{

            margin:15px 30px 10px 30px;
            padding:20px;
            width:100%;
            display:flex;
            justify-content: flex-end;

            font-size: 18px;
            color: #8b8a8a;
            font-style: italic;
            font-weight:bold;


        }


        .footerOrd #a7{
            width:100%;
            margin:10px 30px 30px 30px;
            padding:10px;
            display:flex;
            flex-flow:row;
            justify-content: center;



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

        <div class="generalOrder">
            <div class="ordTitolo">

                <p>IL MIO ORDINE n.${order.id}</p>

            </div>

            <!--lista prodotti nel carrello -->

            <c:forEach items="${order.products}" var="prodotto">

            <div class="headerOrd">

                <div id="a2">
                    <img src="/GameOver_war_exploded/cover/${prodotto.cover}">
                    <p><b>Nome gioco:</b> ${prodotto.productName} <br>
                        <b>Piattaforma:</b> ${prodotto.platform.platformName} <br>
                        <b>Data uscita:</b> ${prodotto.dataString}<br>
                        <b>Categoria:</b> ${prodotto.category.categoryName}<br>

                    </p>
                </div>

                <div id="a3">
                    <p>prezzo: ${prodotto.price}&euro;</p>
                </div>

            </div>
            </c:forEach>
            <div class="footerOrd">


                <div id="a4">
                    <p>
                        SubTotale : ${totale} &euro; <br>
                        Spedizione: Gratis  <br>
                        Totale    : ${totale} &euro; <br>

                    </p>

                </div>

                <div id="a7">
                    <a href="/GameOver_war_exploded/OrderServlet/showOrdersUtent"><button class="button">continua a navigare</button></a>
                </div>

            </div>


    </section>
</aside>


<!-- footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>

