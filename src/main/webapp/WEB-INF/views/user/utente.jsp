<%--
  Created by IntelliJ IDEA.
  User: Gerry
  Date: 22/06/2021
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'  %>
<html>
<head>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

  <jsp:include page="/WEB-INF/views/partials/head.jsp" >
    <jsp:param name="title" value="Homepage"/>
    <jsp:param name="script" value="jqueryfunction.js"/>
  </jsp:include>


  <style>

    .swal-text {
      font-size: 30px;
      position: relative;
      float: none;
      line-height: normal;
      vertical-align: top;
      text-align: center;
      display: inline-block;
      margin: 0;
      padding: 0 10px;
      font-weight: 400;
      color: rgba(0,0,0,.64);
      max-width: calc(100% - 20px);
      overflow-wrap: break-word;
      box-sizing: border-box;
      font-weight: bold;
    }

    #para1 {

      font-size: 18px;
      color: white;
      font-style: italic;
      font-weight:bold;


    }

  </style>

  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>



</head>
<body>
<script>
  var suc=${ordini};
  if(suc==false) {
    JSalertWarning("Non hai ordini da visualizzare!");
  }
</script>
<script>

  var codiciSeriali="${codiciSeriali}";
  codiciSeriali = codiciSeriali.replace(/[\[\],]/g, "");
  codiciSeriali=codiciSeriali.replace(" ","\n");
  var suc1=${successo};

  if(suc1==true) {
    JScodiciSeriali("Acquisto effettuato.","Codici Seriali:\n"+codiciSeriali);
  }

</script>

<script>
  i=0;

  bannerDinamic();

</script>




<jsp:include page="/WEB-INF/views/partials/headerUtent.jsp"></jsp:include>


<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarUtent.jsp"></jsp:include>



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

     <jsp:include page="/WEB-INF/views/user/vetrinaUtent.jsp"></jsp:include>

    </div>


  </section>
</aside>

<!-- footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>




