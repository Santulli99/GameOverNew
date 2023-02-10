<%--
  Created by IntelliJ IDEA.
  User: Gerry
  Date: 29/06/2021
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'  %>
<html>
<head>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

  <jsp:include page="/WEB-INF/views/partials/head.jsp" >
    <jsp:param name="title" value="Carrello"/>
    <jsp:param name="script" value="jqueryfunction.js"/>
  </jsp:include>
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>

<script>

    var er1=${carrelloVuoto};

    if(er1==true) {
        JSalertWarning("Aggiungere almeno un prodotto per procedere all'acquisto");
    }

</script>

<!-- header -->


<jsp:include page="/WEB-INF/views/partials/headerUtent.jsp"></jsp:include>


<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarUtent.jsp"></jsp:include>



<aside class="containerBackground">

  <section class="containerCenter" id="ajax">

    <div class="generalCart">
      <div class="CrtTitolo">

      <p >Carrello</p>

      </div>

      <!--lista prodotti nel carrello -->

      <c:forEach items="${carrello.cartItems}" var="prodotto">

      <div class="hedercart">

        <img src="${pageContext.request.contextPath}/cover/${prodotto.item.cover}">
        <div id="a2">
          <p>
            <b>Nome gioco:</b> ${prodotto.item.productName} <br>
            <b>Piattaforma:</b> ${prodotto.item.platformName} <br>
            <b>Data uscita:</b> ${prodotto.item.dataString}<br>
            <b>Categoria:</b> ${prodotto.item.categoryName}<br>
          </p>
        </div>

        <div id="a3">
          <a href="${pageContext.request.contextPath}/GestioneAcquistiController/removeCart?id=${prodotto.item.id}">rimuovi</a>
          <p>prezzo: ${prodotto.item.price}&euro;</p>
        </div>

      </div>
      </c:forEach>
 <div class="footerCart">


   <div id="a4">
   <p>
     SubTotale : ${totale} &euro; <br>
     Spedizione: Gratis  <br>
     Totale    : ${totale} &euro; <br>

   </p>

 </div>

   <div id="a7">
     <a href="${pageContext.request.contextPath}/HomePageController/homePageUtent"><button class="button">continua a navigare</button></a>
     <a href="${pageContext.request.contextPath}/GestioneAcquistiController/showAcquisto"><button class="button">Procedi all'acquisto</button></a>
   </div>

</div>


  </section>
</aside>


<!-- footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>