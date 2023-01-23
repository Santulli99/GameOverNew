<div class="header">

  <header class="topbar">

    <div class="logo">
      <a href="${pageContext.request.contextPath}/AccountServlet/showAdmin"><img src="${pageContext.request.contextPath}/images/logo_nuovo.png"></a>
    </div>


      <form class="ig-ricerca" action="${pageContext.request.contextPath}/ProductServlet/searchProductAdmin" method="get">
        <input id="ricerca1" name="stringa" onkeyup="ricercaAjaxAdmin()" class="ricerca" type="text" placeholder="ricerca" >
        <button class="ig-ricerca-submit" type="submit" value="" title="Ricerca avanzata"></button>
      </form>


    <div class="utente-carello" >

      <div class="utente" id="menu2">

        <a href="${pageContext.request.contextPath}/AccountServlet/showAccountAdmin"><img src="${pageContext.request.contextPath}/icons/icons8-amministratore-uomo-96.png" title="Profilo"></a>

      </div>

      <div onclick="myFunction()" class="carrello">
        <img src="${pageContext.request.contextPath}/icons/icons8-uscita-52.png" title="Logout">
      </div>

    </div>
  </header>
</div>