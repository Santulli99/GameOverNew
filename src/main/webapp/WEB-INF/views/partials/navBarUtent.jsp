<div class="topNavBar">
    <nav class="navBar">

        <ul class="primoul">

            <li id="li1"> <img src="/GameOverNew_war_exploded/icons/icons8-monitor-52.png"><a>pc</a>
                <!--menu tendina con categorie -->
                <ul id="sotto_ul1" class="sotto_ul">
                    <li>
                        <h4 href="#">PC Giochi</h4>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=CASUAL-GAME&pla=1">CASUAL-GAME</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=AZIONE-AVVENTURA&pla=1">AZIONE-AVVENTURA</a>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=SIMULAZIONE&pla=1">SIMULAZIONE  </a>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=SPORT&pla=1">SPORT</a>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=RPG&pla=1">RPG</a>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=GUIDA&pla=1">GUIDA</a>
                    </li>

                </ul>

                <!--utilizzo querystring? per passare le categorie alla servlet -->


            </li>
            <li id="li2"> <img src="/GameOverNew_war_exploded/icons/icons8-xbox-52.png" ><a>xbox</a>
                <!--menu tendina con categorie -->
                <ul id="sotto_ul2" class="sotto_ul">
                    <li>
                        <h4 href="#">XBOX Giochi</h4>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=CASUAL-GAME&pla=3">CASUAL-GAME</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=AZIONE-AVVENTURA&pla=3">AZIONE-AVVENTURA</a>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=SIMULAZIONE&pla=3">SIMULAZIONE  </a>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=SPORT&pla=3">SPORT</a>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=RPG&pla=3">RPG</a>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=GUIDA&pla=3">GUIDA</a>
                    </li>

                </ul>
            </li>
            <li id="li3"> <img src="${pageContext.request.contextPath}/icons/icons8-play-station-50.png "><a>playstation</a>
                <!--menu tendina con categorie -->
                <ul id="sotto_ul3" class="sotto_ul">
                    <li>
                        <h4 href="#">Playstation Giochi</h4>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=CASUAL-GAME&pla=2">CASUAL-GAME</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=AZIONE-AVVENTURA&pla=2">AZIONE-AVVENTURA</a>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=SIMULAZIONE&pla=2">SIMULAZIONE  </a>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=SPORT&pla=2">SPORT</a>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=RPG&pla=2">RPG</a>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/ProductServlet/showProductsWithCatAndPlaUtent?model.dao.category=GUIDA&pla=2">GUIDA</a>
                    </li>

                </ul>
            </li>

        </ul>

        <div id="para1"> Benvenuto Sig. ${account.username} </div>

    </nav>


</div>