<!--importo la libreria jstl core con prefisso c -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<c:set var="context" value="${pageContext.request.contextPath}"/>

<title> ${param.title}</title>

<!-- 4 tag importanti obbligatori -->
<meta charset="UTF-8">
<meta  name = "viewport"  content = "width = device-width, initial-scale = 1,viewport-fit=cover" >
<meta name="description" content="E-commerce videogame digitali">

<!-- icona della  pagina  -->
<link rel="icon"  type="image/png"  href="${context}/images/logo.png">

<!-- meta tag per  apple-->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title" content="GameOver">
<link rel="apple-touch-icon" href="${context}/icons/logo.png">
<link rel="apple-touch-startup-image" href="${context}/icons/logo.png">

<!-- tag andoid -->
<!-- tag colore intestazione pagina -->
<meta name="theme-color" content="colore scelto da noi">


<!-- importare il css per unificare il codice su tutti i browser -->
<link href="${context}/css/reset.css" rel="stylesheet">
<!-- importare css e js condivisi da ogni pagina (codice css e js comuni)-->
 <link href="${context}/css/library.css" rel="stylesheet">

<!--importare i specifici css e js -->
<c:if test="${not empty param.style}" >
    <link rel="stylesheet" href="${context}/css/${param.style}">
</c:if>

<c:if test="${not empty param.script}" >

    <script src="${context}/js/${param.script}" defer></script>

</c:if>
<script src="${context}/js/library.js"></script>


