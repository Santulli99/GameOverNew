<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'  %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="Modifica profilo"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>


    <style>
        fieldset{
            border: none;
        }

        form input{
            outline: none;
            border: #ccc solid 1px
        }

        table th{
            color: #323233;
        }

        .set{
            margin-right: 2rem;
        }
    </style>




</head>
<body>



<!-- header-->

<jsp:include page="/WEB-INF/views/partials/headerAdmin.jsp"></jsp:include>

<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/views/partials/navBarAdmin.jsp"></jsp:include>



<aside class="containerBackground">

    <section class="containerCenter" id="ajax">
        <div class="conteinerRegistrazione">

            <form   action="GestioneUtenteController/updateAdmin" method="post">

                <b class="titoli " >Il tuo profilo SIG.${account.dataClient.firstName}</b>


                <div style="text-align: left">
                    <p>Dati Account</p>

                    <table >
                        <tr>
                            <th>email </th>
                            <th>conferma email </th>
                        </tr>
                        <tr>
                            <td >
                                <label for="email">
                                    <input class="set" type="email" name="email" id="email" placeholder="${account.email}">
                                </label>
                            </td>
                            <td >
                                <label  for="email1">
                                    <input class="set" type="email" name="email1" id="email1"  placeholder="${account.email}" >
                                </label>
                            </td>
                        </tr>

                        <tr>
                            <th >password </th>
                            <th > conferma password </th>
                        </tr>
                        <tr>
                            <td>
                                <label for="password">
                                    <input class="set" type="password" name="password" id="password"  placeholder="${account.password}">
                                </label>
                            </td>
                            <td>
                                <label for="password1"><input class="set" type="password" name="password1" id="password1"  placeholder="${account.password}" >
                                </label>
                            </td>

                        </tr>
                        <tr>
                            <th>username </th>
                        </tr>
                        <tr>
                            <td>
                                <label for="username"><input class="set" type="text" name="username" id="username"  placeholder="${account.username}" >
                                </label>
                            </td>
                        </tr>

                    </table>
                </div>




                <div  style="text-align: left">
                    <p>Dati anagrafici</p>

                    <table>

                        <tr>
                            <th> nome</th>
                            <th> cognome</th>
                        </tr>

                        <tr>
                            <td> <label for="nome"> <input class="set" type="text" name="nome" id="nome" placeholder="${account.dataClient.firstName}" >  </label></td>
                            <td> <label for="cognome"> <input class="set" type="text" name="cognome" id="cognome" placeholder="${account.dataClient.lastName}" > </label> </td>
                        </tr>

                        <tr>
                            <th> telefono</th>
                            <th> luogo di nascita</th>
                        </tr>

                        <tr>
                            <td> <label for="tel"> <input class="set" type="tel" name="telefono" id="tel" placeholder="${account.dataClient.cell}">  </label></td>
                            <td><label for="cittN"> <input class="set" type="text" name="cittàNascita" id="cittN" placeholder="${account.dataClient.city}" ></label></td>
                        </tr>

                        <tr>
                            <th> data nascita </th>
                            <th> codice fiscale </th>
                        </tr>

                        <tr>
                            <td> <label for="dataN"> <input class="set" type="text" name="dataNascita" id="dataN" placeholder="${account.dataClient.date}" >  </label></td>
                            <td><label for="cf"> <input class="set" type="text" name="codiceFiscale" id="cf" placeholder="${account.dataClient.cf}" ></label></td>
                        </tr>



                    </table>


                </div>

                <div style="text-align: left">
                    <p>Dati azienda</p>

                    <table>
                        <tr>
                            <th>citta' </th>
                            <th>provincia </th>
                        </tr>
                        <tr>
                            <td> <label for="città"><input class="set" type="text" name="città" id="città"  placeholder="${account.address.city}"  ></label></td>
                            <td>  <label for="provincia"><input class="set" type="text" name="provincia" id="provincia"  placeholder="${account.address.province}"  ></label></td>
                        </tr>

                        <tr>
                            <th >via </th>
                            <th >n°civico </th>
                        </tr>
                        <tr>
                            <td> <label for="via"><input class="set" type="text" name="via" id="via"  placeholder="${account.address.street}" disabled="disabled" ></label> </td>
                            <td>  <label for="civico"><input class="set" type="number" name="civico" id="civico"  placeholder="${account.address.streetNumber}" disabled="disabled" ></label></td>

                        </tr>
                        <tr>
                            <th>cap </th>
                        </tr>
                        <tr>
                            <td>  <label for="cap"><input class="set" type="number" name="cap" id="cap"  placeholder="${account.address.postalCode}" disabled="disabled"></label></td>
                        </tr>

                    </table>
                </div>
                <div style="text-align: center">
                    <button class="button" type="submit" >MODIFICA</button>
                </div>
            </form>
        </div>
    </section>
</aside>

<!--footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>


</body>
</html>

