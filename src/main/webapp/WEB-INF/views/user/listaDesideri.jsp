<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 20/01/2023
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp" >
        <jsp:param name="title" value="Homepage"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>

        .checked {
            color: orange;
        }

        #titolo{

            width: 100%;
        }

        #prodottoDesiderato{
            display: flex;
            flex-flow: row wrap;
            width: 100%;
            margin-left: 2%;
            margin-right: 2%;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        #cover{
            width: 100px;
            border-radius: 10px;
            box-shadow:0 0 8px 0 rgba(0,0,0,0.7);
        }
        #descrizione{
            width: 40%;
            display: flex;
            flex-flow: column wrap;
            justify-content: space-between;

        }
        #bottoni{
            width: 30%;
        }
        #titologioco{
            width: 100%;
        }
        #valutazione{
            width: 100%;
            display: flex;
            flex-flow: row nowrap;
            justify-content: space-between;
            align-items: center;
        }
        #prezzo{
            width: 100%;
            display: flex;
            flex-flow: row nowrap;
            justify-content: space-between;
            align-items: center;
        }

        .divprezzo{
            width: 30%;
        }


    </style>

</head>
<body>

<!-- header -->


<jsp:include page="/WEB-INF/views/partials/headerUtent.jsp"></jsp:include>

<jsp:include page="/WEB-INF/views/partials/navBarUtent.jsp"></jsp:include>


<aside class="containerBackground">
    <section class="containerCenter" id="ajax">
    <div id="titolo"> <h1 style="margin-left: 10px; color: #ee3124;" >Lista Desideri</h1>  </div>

        <div id="prodottoDesiderato">

            <div id="cover"> <img style="border-radius: 10px;" src="/GameOverNew_war_exploded/cover/Assassins Creed Valhalla.jpg"></div>
            <div id="descrizione">

                <div id="titologioco"><h1><p>${prodotto.productName}</p></h1></div>
                <div id="valutazione">
                    <div style="width: 50%">
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star"></span>
                    </div> <div><p>totali valutazioni </p></div></div>
                <div id="prezzo">

                    <div class="divprezzo"> <p>${prodotto.price}&euro;</p> </div>
                    <div class="divprezzo"> <p>${prodotto.platform.platformName}</p> </div>
                    <div class="divprezzo"> <p>${prodotto.category.categoryName}</p> </div>

                </div>


            </div>
            <div id="bottoni"><h1>button</h1></div>
        </div>

    </section>

</aside>

<!-- footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>


</body>
</html>
