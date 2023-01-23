

<div class="header">

    <header class="topbar">

        <div class="logo">
           <a href="${pageContext.request.contextPath}/AccountServlet/utente"> <img src="${pageContext.request.contextPath}/images/logo_nuovo.png"></a>
        </div>


        <form class="ig-ricerca" action="${pageContext.request.contextPath}/ProductServlet/searchProductUtent" method="get" >
            <input  id="ricerca1" name="stringa" onkeyup="ricercaAjax()" class="ricerca" type="text" placeholder="ricerca" >
            <button class="ig-ricerca-submit" type="submit" value="" title="Ricerca avanzata"></button>

        </form>

        <div class="utente-carello" >

            <div class="utente" id="menu2">

                <img src="${pageContext.request.contextPath}/icons/icons8-utente-48.png">

                <ul id="menu1" class="menu-utente">
                    <li>

                        <a href="${pageContext.request.contextPath}/AccountServlet/logout">Disconnetti</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/AccountServlet/showAccountUtent">Il mio profilo</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/OrderServlet/showOrdersUtent">I miei ordini</a>
                    </li>
                </ul>

            </div>

            <div class="carrello">
                <a href="${pageContext.request.contextPath}/CartServlet/showCart">  <img src="${pageContext.request.contextPath}/icons/icons8-carrello-della-spesa-48.png">
                </a>

            </div>

            <div id="num_product"> ${quantity}</div>
            <script>

                var  q=${quantity};

                if(q>0){
                    document.getElementById("num_product").style.display="flex";
                }
            </script>


        </div>
    </header>
</div>