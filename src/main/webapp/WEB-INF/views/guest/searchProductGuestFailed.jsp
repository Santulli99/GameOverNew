<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Ricerca Prodotti"/>
        <jsp:param name="script" value="jqueryfunction.js"/>

    </jsp:include>

</head>
<body>

<script>
    i = 0;
    bannerDinamic();

</script>

<!-- header -->

<jsp:include page="/WEB-INF/views/partials/headerGuest.jsp"></jsp:include>

<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarGuest.jsp"></jsp:include>

<!--conteiner Center -->

<aside class="containerBackground">

    <section class="containerCenter" id="ajax">

        <div class="vetrina">

            <div class="vetrinaAjax">

                <img src="${pageContext.request.contextPath}/icons/icons8-ricerca-50.png">

                <p> Siamo spiacenti, nessun risultato trovato! <br>
                    Sembra che non ci siano giochi corrispondenti alla tua richiesta
                </p>

                <a href="${pageContext.request.contextPath}/HomePageController/homePage">
                    <button class="button">Torna all'HomePage</button>
                </a>


            </div>

        </div>


    </section>
</aside>

<!-- footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>

