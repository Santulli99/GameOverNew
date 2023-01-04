
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="Login"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>

    <script src="/GameOver_war_exploded/js/validate.js"></script>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>


<body>
<script>
    var acquisto=${acquistoFialed};
    if(acquisto==true) {
        JSalertWarning("Effettuare login o registrazione per continuare l'acquisto");
    }

</script>
<script>
    var reg=${registrazione};
    if(reg==true){
        JSalertSuccess("Registrazione effettuata!!");
    }
</script>
<script>
    var login=${login};
    if(login==false){
        JSalertError("Credenziali non valide");
    }
</script>

<jsp:include page="/WEB-INF/views/partials/headerGuest.jsp"></jsp:include>


    <!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarGuest.jsp"></jsp:include>



<aside class="containerBackground">

    <section class="containerCenter1" id="ajax">

        <jsp:include page="../partials/login.jsp"></jsp:include>


    </section>
</aside>

<!-- footer-->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>
