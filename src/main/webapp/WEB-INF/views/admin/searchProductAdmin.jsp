<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'  %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="Ricerca Prodotti"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>


    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>



</head>
<body>

<!-- header -->


<jsp:include page="/WEB-INF/views/partials/headerAdmin.jsp"></jsp:include>


<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarAdmin.jsp"></jsp:include>



<aside class="containerBackground">

    <section class="containerCenter" id="ajax">

        <div>

            <jsp:include page="/WEB-INF/views/admin/vetrinaAdmin.jsp"></jsp:include>

        </div>

    </section>
</aside>

<!-- footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>


