

<div class="header">

    <header class="topbar">

        <div class="logo">
           <a href="/GameOver_war_exploded/AccountServlet/utente"> <img src="/GameOver_war_exploded/images/logo_nuovo.png"></a>
        </div>


        <form class="ig-ricerca" action="/GameOver_war_exploded/ProductServlet/searchProductUtent" method="get" >
            <input  id="ricerca1" name="stringa" onkeyup="ricercaAjax()" class="ricerca" type="text" placeholder="ricerca" >
            <button class="ig-ricerca-submit" type="submit" value="" title="Ricerca avanzata"></button>

        </form>

        <div class="utente-carello" >

            <div class="utente" id="menu2">

                <img src="/GameOver_war_exploded/icons/icons8-utente-48.png">

                <ul id="menu1" class="menu-utente">
                    <li>

                        <a href="/GameOver_war_exploded/AccountServlet/logout">Disconnetti</a>
                    </li>
                    <li>
                        <a href="/GameOver_war_exploded/AccountServlet/showAccountUtent">Il mio profilo</a>
                    </li>
                    <li>
                        <a href="/GameOver_war_exploded/OrderServlet/showOrdersUtent">I miei ordini</a>
                    </li>
                </ul>

            </div>

            <div class="carrello">
                <a href="/GameOver_war_exploded/CartServlet/showCart">  <img src="/GameOver_war_exploded/icons/icons8-carrello-della-spesa-48.png">
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