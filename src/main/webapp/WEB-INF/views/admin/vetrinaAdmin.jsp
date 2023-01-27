<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'  %>
<div class="vetrina" >
    <c:forEach items="${vetrina}" var="prodotto">
        <div class="divVetrina">
            <p>${prodotto.productName}</p>

            <a href="${pageContext.request.contextPath}/GestioneProdottoController/updateProduct?id=${prodotto.id}"> <img  src="${pageContext.request.contextPath}/cover/${prodotto.cover}">
            </a>

            <p class="divPrezzo">${prodotto.price}&euro;</p>
        </div>
    </c:forEach>

</div>
