<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'  %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="Homepage"/>
        <jsp:param name="script" value="jqueryfunction.js"/>

    </jsp:include>

</head>
<body>

<script>
    i=0;
    bannerDinamic();

</script>

<script>
    var reg="${registrazione}";
    if(reg==true){
        JSalertSuccess("Registrazione effettuata!!");
    }
</script>

<!-- header -->

<jsp:include page="/WEB-INF/views/partials/headerGuest.jsp"></jsp:include>

<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarGuest.jsp"></jsp:include>

<!--conteiner Center -->

<aside class="containerBackground">

    <section class="containerCenter" id="ajax">

        <!-- banner -->

        <div class="slider-container">
            <div class="image-container">
                <img id="a1" src="${pageContext.request.contextPath}/images/MicrosoftTeams-image.png" class="slider-image" />

            </div>
            <div class="button-container">
                <button onclick="bottone1()" ></button>
                <button onclick="bottone2()" ></button>
                <button onclick="bottone3()" ></button>
                <button onclick="bottone4()" ></button>
                <button onclick="bottone5()" ></button>
                <button onclick="bottone6()" ></button>
                <button onclick="bottone7()" ></button>
                <button onclick="bottone8()" ></button>
            </div>
        </div>


        <div>
            <jsp:include page="/WEB-INF/views/guest/vetrinaGuest.jsp"></jsp:include>
        </div>


    </section>
</aside>

<!-- footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>
