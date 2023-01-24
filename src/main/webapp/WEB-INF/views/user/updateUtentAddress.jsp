<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>



    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="Modifica Indirizzo"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>

    <script src="${pageContext.request.contextPath}/js/validate.js"></script>

</head>


<body>

<jsp:include page="/WEB-INF/views/partials/headerUtent.jsp"></jsp:include>


<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarUtent.jsp"></jsp:include>



<aside class="containerBackground">

    <section class="containerCenter1" id="ajax">

        <jsp:include page="../partials/updateAddress.jsp"></jsp:include>


    </section>
</aside>

<!-- footer-->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>