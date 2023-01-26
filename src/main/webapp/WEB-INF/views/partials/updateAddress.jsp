<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="andrea"/>

    </jsp:include>
    <style>

        .label{
            padding-bottom:10px;
            font-weight: bold;

        }

        p{

            margin: 5px 0 0 0;
            display: none;
            font-size: 15px;
            color: #ee3124;
        }
    </style>


    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>



</head>

<body>

<script>
    var a=${modificaAddress};
    if(a==true){
        JSalertSuccess("Indirizzo modificato con successo");
    }
</script>


<div class="conteinerLogin">
    <form class="form" name="modulo"  onsubmit="return validateFormAddress()" action="${pageContext.request.contextPath}/GestioneUtenteController/updateAddress"   method="post">


        <label class="label" for="città"> Citta'  </label>
        <input onblur="validateCitta()" type="text" name="citta" id="città"  value="${account.address.city}" required>
        <p id="pr11"></p>
        <br>
        <label class="label" for="provincia"> Provincia </label>
        <input onblur="validateProvincia()" type="text" name="provincia" id="provincia"  value="${account.address.province}" required>
        <p id="pr12"></p>
        <br>
        <label class="label" for="via">Via  </label>
        <input onblur="validateVia()" type="text" name="via" id="via" value="${account.address.street}" required>
        <p id="pr13"></p>
        <br>
        <label class="label" for="civico"> Civico </label>
        <input onblur="validateCivico()" type="text" name="civico" id="civico"  value="${account.address.streetNumber}" required>
        <p id="pr14"></p>
        <br>
        <label class="label" for="cap">Cap </label>
        <input onblur="validateCap()" type="text" name="cap" id="cap"  value="${account.address.postalCode}"required>
        <p id="pr15"></p>

        <button class="button"  type="submit"   >modifica</button>
    </form>

</div>



</body>
</html>
