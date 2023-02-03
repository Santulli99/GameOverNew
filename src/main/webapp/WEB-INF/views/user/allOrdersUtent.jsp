<%--
  Created by IntelliJ IDEA.
  User: Gerry
  Date: 22/06/2021
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'  %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="Ordini"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>


    <style>

        .orders{

            width:100%;
        }

        .orders ul{
            display: flex;
            flex-flow: column wrap;
            margin: 20px 10px 20px 10px;
            padding:0px;
        }

        .orders ul h3{
            text-align:left;
            font-weight: bold;
            font-style: italic;
            font-size: 30px;
            color: #ee3124;
            margin:10px;

        }

        .orders ul li{
            display: flex;
            flex-flow: row;
            box-shadow:0 0 8px 0 rgba(0,0,0,0.2);
            padding:2px;
            justify-content:space-between;
            align-items:center;
            margin:10px;
            list-style-type: none;


        }

        .orders  button{
            font-size: 16px;
            background-color: #ee3124;
            color: white;
            margin:10px;
            border-radius:5px;
            border:2px solid #ee3124;
            padding:5px;
            cursor: pointer;
            box-shadow:0 0 8px 0 rgba(0,0,0,0.4)

        }


        .orders button:hover{

            background-color: #00000066;
            border:2px solid #8b8a8a;

        }


        .orders p{

            margin:10px;
            font-family:OpenSans;
            font-style:italic;
            font-weight:normal;
            font-size: 20px;

        }
        .orders a{
            margin-right: 10px;
        }

        @media screen and (max-width: 1024px)  {


            .orders ul{
                display: flex;
                flex-flow: column;
                margin: 20px 10px 20px 10px;
                padding:0px;
            }

            .orders ul li{
                display: flex;
                flex-flow: column;
                box-shadow:0 0 8px 0 rgba(0,0,0,0.2);
                padding:2px;
                justify-content:space-between;
                align-items:center;
                margin:10px;
                list-style-type: none;


            }
        }



    </style>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>




</head>
<body>
<script>

    var er1=${elimina};
    if(er1==true) {
        JSalertSuccess("Il reso è stato effettuato!");
    }
    else{
        JSalertError("E' possibile effettuare il reso solo entro 15 giorni dall'acquisto");
    }
</script>

<!-- header -->


<jsp:include page="/WEB-INF/views/partials/headerUtent.jsp"></jsp:include>


<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarUtent.jsp"></jsp:include>



<aside class="containerBackground">

    <section class="containerCenter" id="ajax">

        <div class="orders">

            <ul>
                <h3> I miei ordini </h3>

                <c:forEach items="${orders}" var="order">

                    <li>

                        <p> <b>ID_ORDINE: </b> <br>${order.id} </p>
                        <p> <b>DATA: </b> <br>${order.dataString}</p>
                        <p> <b>NUM_PRODOTTI:</b> <br> ${order.num_product} </p>

                      <div>  <a href="${pageContext.request.contextPath}/GestioneAcquistiController/showOrderUtent?id=${order.id}"><button >VISUALIZZA</button></a>
                        <a href="${pageContext.request.contextPath}/GestioneAcquistiController/removeOrderUtent?id=${order.id}"><button >ELIMINA</button></a></div>

                    </li>

                </c:forEach>
            </ul>
        </div>
    </section>
</aside>

<!-- footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>

