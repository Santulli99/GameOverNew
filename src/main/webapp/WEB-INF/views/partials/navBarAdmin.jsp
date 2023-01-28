<div class="topNavBar">
    <nav class="navBar">

        <ul class="primoul">

            <li id="li1"> <img src="${pageContext.request.contextPath}/icons/icons8-ingranaggio-48.png"><a>Gestione Prodotti </a>
                <!--menu tendina con categorie -->
                <ul id="sotto_ul1" class="sotto_ul">
                    <li>
                        <h4 href="#">Prodotti </h4>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/GestioneProdottoController/showCreatProduct">Aggiungi prodotto </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/GestioneProdottoController/showAllProduct">Visualizza prodotti</a>
                    </li>


                </ul>




            </li>
            <li id="li2"> <img src="${pageContext.request.contextPath}/icons/icons8-gestione-sviluppo-commerciale-48 (1).png" ><a>Gestione Cliente</a>
                <!--menu tendina con categorie -->
                <ul id="sotto_ul2" class="sotto_ul">
                    <li>
                        <h4>Clienti</h4>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/GestioneUtenteController/showAllUtent">Visualizza Clienti </a>
                    </li>

                </ul>
            </li>
            <li id="li3"> <img src="${pageContext.request.contextPath}/icons/icons8-gestione-48.png"><a>Gestione Ordini</a>

                <ul id="sotto_ul3" class="sotto_ul">
                    <li>
                        <h4 >Ordini</h4>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/OrderServlet/showOrders">Visualizza Ordini </a>
                    </li>


                </ul>
            </li>


        </ul>

        <div id="para1"> Benvenuto Admin: ${account.dataClient.firstName} </div>
    </nav>


</div>
