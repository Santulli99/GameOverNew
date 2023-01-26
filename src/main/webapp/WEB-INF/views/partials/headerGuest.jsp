<div class="header">

    <header class="topbar">

        <div class="logo">
            <img onclick="myFunction()"  src="${pageContext.request.contextPath}/images/logo_nuovo.png">
        </div>


        <form class="ig-ricerca" action="${pageContext.request.contextPath}/ProductServlet/searchProductGuest" method="get" >
            <input  id="ricerca1" name="stringa" onkeyup="ricercaAjaxGuest()" class="ricerca" type="text" placeholder="ricerca" >
            <button class="ig-ricerca-submit" type="submit" value="" title="Ricerca avanzata"></button>
        </form>

        <div class="utente-carello" >

            <div class="utente" id="menu2">

                <img src="${pageContext.request.contextPath}/icons/icons8-utente-48.png">

                <ul id="menu1" class="menu-utente">
                    <li>
                        <a href="${pageContext.request.contextPath}/AutenticazioneController/login">login</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/RegistrazioneController/registrazione">registrazione</a>
                    </li>
                </ul>

            </div>

            <div class="carrello">
                 <img id="img1"   src="${pageContext.request.contextPath}/icons/icons8-carrello-della-spesa-48.png">

                <script>
                    document.getElementById("img1").onclick = function()  {JSalertWarning("Effettuare login o registrazione per visualizzare il carrello")};
                </script>
            </div>

        </div>
    </header>
</div>
