<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Recensione"/>
        <jsp:param name="script" value="jqueryfunction.js"/>
    </jsp:include>

    <meta charset="UTF-8">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>


        .checked {
            color: orange;
        }

        #cover {
            width: 120px;
            border-radius: 10px;
            box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.7);
        }

        a {
            font-size: 20px;
        }

        .rate {
            float: left;
            height: 46px;
            padding: 0 10px;
        }

        .rate:not(:checked) > input {
            position: fixed;
            top: -9999px;
        }

        .rate:not(:checked) > label {
            float: right;
            width: 1em;
            overflow: hidden;
            white-space: nowrap;
            cursor: pointer;
            font-size: 45px;
            color: #ccc;
        }

        .rate:not(:checked) > label:before {
            content: '★ ';
        }

        .rate > input:checked ~ label {
            color: #ffc700;
        }

        .rate:not(:checked) > label:hover,
        .rate:not(:checked) > label:hover ~ label {
            color: #deb217;
        }

        .rate > input:checked + label:hover,
        .rate > input:checked + label:hover ~ label,
        .rate > input:checked ~ label:hover,
        .rate > input:checked ~ label:hover ~ label,
        .rate > label:hover ~ input:checked ~ label {
            color: #c59b08;
        }


        #boxrecensione {
            width: 100%;
            color: #8b8a8a;
            margin-top: 20px;
            font-style: italic;
            font-weight: bold;
            font-size: 25px;
            padding-left: 5px;
            box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.7);
            border-radius: 10px;

        }

        #cover {
            width: 120px;
            border-radius: 10px;
            box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.7);
        }

        #principale {
            display: flex;
            flex-flow: column nowrap;
        }

        #box1 {
            display: flex;
            flex-flow: column;
            justify-content: flex-start;
            margin-bottom: 20px;

        }

        #box1 label {
            width: 100%;
            font-style: italic;
            font-weight: bold;
            font-size: 25px;
            margin-bottom: 10px;
        }

        #box2 {
            display: flex;
            flex-flow: column;
            justify-content: flex-start;
            margin-bottom: 20px;

        }

        #box3 {
            display: flex;
            flex-flow: column nowrap;
            justify-content: flex-start;
            margin-bottom: 20px;

        }


        #box4 {
            display: flex;
            flex-flow: column nowrap;
            justify-content: flex-start;
            margin-bottom: 20px;

        }

        #box5 {
            display: flex;
            flex-flow: row;
            justify-content: flex-end;
            margin-bottom: 20px;
        }

        #boxrecensione {
            margin-bottom: 30px;
        }

        #box4 label {
            width: 100%;
            font-style: italic;
            font-size: 25px;
            margin-bottom: 10px;
        }


        .valc {
            width: 100%;
            font-style: italic;
            font-size: 25px;
            margin-bottom: 10px;
        }

        .button1 {
            background-color: #ee3124;
            color: white;
            border-radius: 5px;
            border: 2px solid #ee3124;
            padding: 5px;
        }

        .button1:hover {
            background-color: #8b8a8a;
            border: 2px solid #8b8a8a;

        }
    </style>
</head>
<body>

<script>

    var suc1=${successo};
    if(suc1==false) {
        JSalertSuccess("Recensione fallita! Immettere parametri validi");
    }

</script>

<!-- header -->
<jsp:include page="/WEB-INF/views/partials/headerUtent.jsp"></jsp:include>
<!-- barra di navigazione  -->
<jsp:include page="/WEB-INF/views/partials/navBarUtent.jsp"></jsp:include>

<aside class="containerBackground">
    <section class="containerCenter" id="ajax">

        <div id="principale">
            <form action="${pageContext.request.contextPath}/RecensioneController/creaRecensione"
                  method="post" >

                <div id="boxrecensione">
                    <label>Crea recensione</label>
                </div>


                <div id="box1">
                    <label>${prodotto.productName}</label>
                    <div id="cover">
                        <img style="border-radius: 10px;"
                             src="${pageContext.request.contextPath}/cover/${prodotto.cover}">
                    </div>
                </div>


                <div id="box2">
                    <label class="valc">Valutazione complessiva:</label>
                    <div>
                        <div class="rate">
                            <input type="radio" id="star1" name="valutazione" value="5"/>
                            <label for="star1" title="text">1 star</label>
                            <input type="radio" id="star2" name="valutazione" value="4"/>
                            <label for="star2" title="text">2 stars</label>
                            <input type="radio" id="star3" name="valutazione" value="3"/>
                            <label for="star3" title="text">3 stars</label>
                            <input type="radio" id="star4" name="valutazione" value="2"/>
                            <label for="star4" title="text">4 stars</label>
                            <input type="radio" id="star5" name="valutazione" value="1"/>
                            <label for="star5" title="text">5 stars</label>
                        </div>
                    </div>
                </div>

                <div id="box3">
                    <label class="valc" for="sottobox3_2">Aggiungi un titolo</label>
                    <input style="border-radius: 5px" id="sottobox3_2" type="text" minlength="5" maxlength="50"
                           name="titoloRecensione"
                           required>
                </div>
                <div id="box4">
                    <label id="sottobox4_1" for="sottobox4_2">Aggiungi una recensione scritta</label>
                    <textarea  id="sottobox4_2" rows="5" maxlength="500" cols="90"
                              name="descrizione"
                              required>
                    </textarea>
                </div>
                <div id="box5">
                    <input type="hidden" name="id" value="${prodotto.id}">
                    <button class="button1" type="submit">invia</button>
                </div>
            </form>
        </div>
    </section>
</aside>


<!-- footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"></jsp:include>

</body>
</html>