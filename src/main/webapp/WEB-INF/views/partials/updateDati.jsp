<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="andrea"/>

    </jsp:include>
    <style>


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




<div class="conteinerRegistrazione">
    <form class="form1" name="modulo" onsubmit="return (validateNome() && validateTel())"   action="${context}/DataClientServlet/updateDati"   method="post">

        <div id="num1">
            <div class="label1">
                <div>  <label> nome *</label></div>
                <div> <input onblur="validateNome()" type="text" name="nome" id="nome" placeholder="nome" value="${account.dataClient.firstName}" required >
                    <p id="pr6"></p>
                </div>


            </div>

            <div class="label1">
                <div>     <label class="label" for="cognome"> Cognome </label> </div>
                <div>      <input  type="text" name="cognome" id="cognome"  value="${account.dataClient.lastName}" disabled>
                    <p id="pr7"></p>
                </div>

            </div>

        <div class="label1">
            <div><label> data nascita *</label></div>
            <div> <input   type="date" name="dataNascita" id="dataN"  value="${account.dataClient.date}" disabled>
                <p id="pdata"></p>
            </div>


        </div>

            <div class="label1">
            <div>  <label>tel *</label></div>
            <div>  <input onblur="validateTel();checkTelAjax()" type="tel" name="telefono" id="telefono" placeholder="3123456789" value="${account.dataClient.cell}" required >
                <p id="pr8"></p>
                <p id="pr800"></p>
            </div>
        </div>

        <div class="label1">
                <div>     <label class="label" for="cittN"> Citta' Nascita </label> </div>
                <div>    <input  type="text" name="cittaNascita" id="cittN"  value="${account.dataClient.city}"disabled>
                    <p id="pr9"></p>
                </div>

            </div>
            <div class="label1">
                <div>   <label class="label" for="codiceFiscale"> Codice Fiscale </label> </div>
                <div>   <input  type="text" name="codiceFiscale" id="codiceFiscale"  value="${account.dataClient.cf}" disabled>
                    <p id="pr10"></p>
                    <p id="pr1000"></p>
                </div>

            </div>

        </div>
        <div id="num5">
        <button class="button"  type="submit" >modifica</button>
       </div>
    </form>
</div>

<script>

    var esiste=${esiste};

    if(esiste==true){

        JSalertWarning( "Hey, sembra che il numero di telefono corrisponda a un model.dao.account già esistente.");
    }

</script>



</body>
</html>
