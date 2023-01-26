<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <style>

        .containerCenter1{
            display: flex;
            width: 75vw;
            background-color: transparent;
            margin-left:12.5vw;
            margin-right:12.5vw;
            min-height: 100%;
            position:relative;
            justify-content: center;
        }

    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="Modifica Username"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>

    <script src="${pageContext.request.contextPath}/js/validate.js"></script>

</head>


<body>

<jsp:include page="/WEB-INF/views/partials/headerAdmin.jsp"></jsp:include>


<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarAdmin.jsp"></jsp:include>



<aside class="containerBackground">

    <section class="containerCenter1" id="ajax">

        <jsp:include page="../partials/updateUsername.jsp"></jsp:include>


    </section>
</aside>

<!-- footer-->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>