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
            flex-flow: column;
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
            box-shadow:0 0 8px 0 rgba(0,0,0,0.4);
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

        #ordineMenu{

            display:flex;
            width: 100%;
            flex-flow: row nowrap;
            justify-content: space-between;
            align-items: center;
        }

         #select{
             width: 220px;
            display: flex;
            margin:20px;
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
             <div id="ordineMenu">
                 <div>
                 <h3> Ordini effettuati </h3>
             </div>
                  <div id="select">
                 <select id="op"  name="ordine"  onchange="ricercaOrdiniAjax(this.value)">
                     <option hidden id="op0">Ordina per:</option>
                     <option id="op1"  value="data crescente"> data crescente</option>
                     <option id="op2"  value="data decrescente">data decrescente </option>
                     <option id="op3"  value="cliente">cliente</option>
                 </select>
                  </div>

             </div>

                <li>
                    <div><p><b>ID_ORDINE</b></p></div>
                    <div><p><b>DATA</b></p></div>
                    <div><p><b>NUM_PRODOTTI</b></p></div>
                    <div><p><b>CLIENTE</b></p></div>
                    <div></div>
                </li>
                <c:forEach items="${orders}" var="order">
                        <li>
                            <div><p>${order.id}</p></div>
                            <div><p> ${order.dataString} </p></div>
                            <div><p> ${order.num_product} </p></div>
                            <div><p> ${order.account.username} </p></div>
                            <div><a href="${pageContext.request.contextPath}/GestioneAcquistiController/showOrderAdmin?id=${order.id}"><button>DETTAGLIO ORDINE</button></a></div>
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
