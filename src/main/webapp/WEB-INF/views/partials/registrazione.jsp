
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="registrazione"/>

    </jsp:include>


    <style>

        p{

            margin: 5px 0 0 0;
            display: none;
            font-size: 15px;
            color: #ee3124;
        }

    </style>


</head>
<body>

<div class="conteinerRegistrazione">

    <form id="form3"  name="modulo" onsubmit="return validateForm()" action="${pageContext.request.contextPath}/RegistrazioneController/registrazione" method="post">

        <h5> <b>Compila i campi seguenti per registrarti <br>
            NB: I campi contrassegnati da * sono obbligatori</b></h5>


        <div id="num1">

            <div class="label1">
                <div><label>email *</label></div>
                <div> <input onblur="validateEmail();checkEmailSignAjax()" type="email" name="email" id="email"  placeholder="email" value="${email}" required>
                    <p id="pr1" ></p>
                    <p id="pr100"></p>
                </div>
            </div>

            <div class="label1">
                <div> <label>conferma email *</label></div>
                <div> <input onblur="confermaEmail()" type="email" name="email1" id="email1"  placeholder="email" value="${email1}" required >
                    <p id="pr2" ></p>
                </div>
            </div>

            <div class="label1">
                <div>  <label >password *</label> </div>
                <div>   <input onblur="validatePassword()" type="password" name="password" id="password"  placeholder="password" value="${password}"required>
                    <p id="pr3"></p>
                </div>

            </div>

            <div class="label1">
                <div>   <label >conferma pass.*</label></div>
                <div>   <input onblur="confermaPassword()"  type="password" name="password1" id="password1"  placeholder="password" value="${password1}" required>
                    <p id="pr4"></p>
                </div>
            </div>

            <div class="label1">
                <div>   <label>username *</label></div>
                <div>    <input onblur="validateUsername()" type="text" name="username" id="username"  placeholder="username" value="${username}" required >
                    <p id="pr5"></p>
                </div>
            </div>


        </div>




        <div id="num2">


            <div class="label1">
            <div>  <label> nome *</label></div>
          <div> <input onblur="validateNome()" type="text" name="nome" id="nome" placeholder="nome" value="${nome}" required >
              <p id="pr6"></p>
          </div>


            </div>


            <div class="label1">
            <div>   <label> cognome *</label></div>
            <div>   <input onblur="validateCognome()" type="text" name="cognome" id="cognome" placeholder="cognome" value="${cognome}" required>
                <p id="pr7"></p>
            </div>
            </div>


            <div class="label1">
            <div>  <label>tel *</label></div>
                  <div>  <input onblur="validateTel();checkTelSignAjax()" type="tel" name="telefono" id="telefono" placeholder="3123456789" value="${telefono}" required >
                            <p id="pr8"></p>
                            <p id="pr800"></p>
                  </div>
            </div>

            <div class="label1">
            <div>   <label> luogo di nascita *</label></div>
            <div>   <input onblur="validatecittaNascita()" type="text" name="cittaNascita" id="cittN" placeholder="luogo di nascita" value="${cittaNascita}" required>
                <p id="pr9"></p>
            </div>
            </div>

          </div>


            <div id="num3">

            <div class="label1">
                <div><label> data nascita *</label></div>
                <div> <input onblur="validateDate()"  type="date" name="dataNascita" id="dataN"  value="${dataNascita}" required>
                        <p id="pdata"></p>
                </div>


            </div>

            <div class="label1">
                <div>  <label> codice fiscale *</label></div>
                <div>   <input onblur="validateCf();checkCfSignAjax()" type="text" name="codiceFiscale" id="codiceFiscale" placeholder="codice fiscale"  value="${codiceFiscale}"required>
                    <p id="pr10"></p>
                    <p id="pr1000"></p>
                </div>
            </div>

                  <div class="label1">
                      <div>  <label>citta' *</label></div>
                      <div>   <input onblur="validateCitta()"  type="text" name="citta" id="città"  placeholder="citta'" value="${citta}" required>
                          <p id="pr11"></p>
                      </div>

                  </div>

            <div class="label1">
            <div>    <label>provincia *</label></div>
            <div>    <input onblur="validateProvincia()" type="text" name="provincia" id="provincia"  placeholder="provincia" value="${provincia}"  required>
                <p id="pr12"></p>
            </div>
            </div>

              </div>

           <div id="num4">
            <div class="label1">
            <div> <label>via *</label></div>
            <div> <input onblur="validateVia()" type="text" name="via" id="via"  placeholder="via" value="${via}" required >
                <p id="pr13"></p>
            </div>

            </div>

            <div  class="label1">
            <div>   <label>n°civico *</label></div>
        <div>     <input onblur="validateCivico()" type="number" name="civico" id="civico"  placeholder="N°civico" value="${civico}" required>
            <p id="pr14"></p>
        </div>

            </div>

            <div  class="label1">
            <div>   <label>cap *</label></div>
            <div> <input onblur="validateCap()" class="set" type="number" name="cap" id="cap"  placeholder="cap" value="${cap}" required>
                <p id="pr15"></p>
            </div>
            </div>

           </div>

        <div id="num5">
            <button class="button" type="submit" >registrati ora!</button>
        </div>
    </form>
</div>

<script>
    var er=${errore_dati};

    if(er==true){

        JSalertWarning( "Hey, sembra che alcuni dati  corrispondano a un model.dao.account già esistente.");
    }

</script>


</body>
</html>
