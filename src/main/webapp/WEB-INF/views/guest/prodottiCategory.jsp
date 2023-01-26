
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'  %>

<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="Prodotti"/>
        <jsp:param name="script" value="jqueryfunction.js"/>

    </jsp:include>
</head>
<body>
<!-- header -->

<jsp:include page="/WEB-INF/views/partials/headerGuest.jsp"></jsp:include>

<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarGuest.jsp"></jsp:include>

<!--conteiner Center -->

<aside class="containerBackground">

    <section class="containerCenter" id="ajax">

        <div class="vetrina">
            <c:forEach items="${prodotti}" var="prodotto">

                <div class="divVetrina">
                <p>${prodotto.productName}</p>

                <a href="${pageContext.request.contextPath}/ProductServlet/showProduct?id=${prodotto.id}"> <img  src="${pageContext.request.contextPath}/cover/${prodotto.cover}">
                </a>

                <p class="divPrezzo">${prodotto.price}&euro;</p>
                </div>
            </c:forEach>

        </div>
    </section>
</aside>

<!-- footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>


</body>
</html>
