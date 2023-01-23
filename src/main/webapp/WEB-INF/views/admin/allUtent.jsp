<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'  %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="Utenti"/>
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
            font-size: 20px;

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
            box-shadow:0 0 8px 0 rgba(0,0,0,0.4);

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

        }
        .orders div {
            width: 180px;
            margin: 0 10px 0 10px;
        }
        .orders #button {
            width: 180px;
            margin: 0 ;
            display: flex;
            justify-content: flex-end;
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
                <h3> Utenti registrati </h3>
                <li>
                    <div><p><b>EMAIL</b></p></div>
                    <div><p><b>USERNAME</b></p></div>
                    <div></div>
                </li>
                <c:forEach items="${accounts}" var="account">
                        <li>
                            <div><p> ${account.email} </p></div>
                            <div><p> ${account.username}</p></div>
                            <div id="button">
                                <a href="${context}/AccountServlet/showAccount?id=${model.dao.account.id}"><button >VISUALIZZA</button></a>
                            </div>


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
