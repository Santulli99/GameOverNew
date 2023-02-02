function  myFunction(){

    location.replace("http://localhost:8080/GameOverNew_war_exploded/HomePageController/homePage");

}


function  myFunction2(){

    location.replace("http://localhost:8080/GameOver_war_exploded/AccountServlet/registrazione");

}

function  myFunction11(){

    window.open("https://www.facebook.com/campaign/landing.php?campaign_id=1556287114&extra_1=s%7Cc%7C467452162730%7Ce%7Cfacebook%7C&placement=&creative=467452162730&keyword=facebook&partner_id=googlesem&extra_2=campaignid%3D1556287114%26adgroupid%3D106538428541%26matchtype%3De%26network%3Dg%26source%3Dnotmobile%26search_or_content%3Ds%26device%3Dc%26devicemodel%3D%26adposition%3D%26target%3D%26targetid%3Dkwd-541132862%26loc_physical_ms%3D20599%26loc_interest_ms%3D%26feeditemid%3D%26param1%3D%26param2%3D&gclid=EAIaIQobChMIufyylaOi8QIVDQGLCh32DQCYEAAYASAAEgKJ7PD_BwE");

}

function  myFunction22(){

    window.open("https://www.instagram.com/");

}

function  myFunction3(){

    window.open("https://www.twitch.tv/");

}

function  myFunction4(){

    window.open("https://twitter.com/?lang=it");

}

function  myFunction5(){

    window.open("https://www.youtube.com/");

}


function  bottone1(){

    document.getElementById("a1").src="/GameOverNew_war_exploded/images/MicrosoftTeams-image.png";

}

function  bottone2(){

    document.getElementById("a1").src="/GameOverNew_war_exploded/images/MicrosoftTeams-image1.png";

}

function  bottone3(){

    document.getElementById("a1").src="/GameOverNew_war_exploded/images/MicrosoftTeams-image3.png";

}

function  bottone4(){

    document.getElementById("a1").src="/GameOverNew_war_exploded/images/MicrosoftTeams-image4.png";

}
function  bottone5(){

    document.getElementById("a1").src="/GameOverNew_war_exploded/images/MicrosoftTeams-image5.png";

}

function  bottone6(){

    document.getElementById("a1").src="/GameOverNew_war_exploded/images/MicrosoftTeams-image6.png";

}
function  bottone7(){

    document.getElementById("a1").src="/GameOverNew_war_exploded/images/MicrosoftTeams-image7.png";

}
function  bottone8(){

    document.getElementById("a1").src="/GameOverNew_war_exploded/images/MicrosoftTeams-image8.png";

}

function JSalertSuccess(message){
    swal("Congratulazioni!", message, "success");

}


function JSalertError(message){
    swal("Errore!", message, "error");

}
function JSalertWarning(message){
    swal("Attenzione!",message, "warning");

}

/******************ricerca ajax per utente************************/
function  ricercaAjax(){


    var pattern=document.getElementById("ricerca1").value;
    var xhr= new XMLHttpRequest();

    xhr.onreadystatechange= function () {

        if (this.readyState == 4 && this.status == 200) {

            myFunctionXhr(this);

        }
    };
        xhr.open("GET","/GameOverNew_war_exploded/GestioneProdottoController/searchProductWithAjax?stringa="+pattern,true);
        xhr.send();
}
function  myFunctionXhr(xhr){
    var i;
    var data = JSON.parse(xhr.responseText);
    var string="";

    if(data.length==0){
        string+="<div class=\"vetrina\" id=\"ajax\">\n" +
            "\n" +
            "    <div class=\"vetrinaAjax\" >\n" +
            "\n" +
            "        <img src=\"/GameOverNew_war_exploded/icons/icons8-ricerca-50.png\">\n" +
            "\n" +
            "        <p> Siamo spiacenti, nessun risultato trovato! <br>\n" +
            "            Sembra che non ci siano giochi corrispondenti alla tua richiesta\n" +
            "        </p>\n" +
            "\n" +
            "        <a href=\"/GameOverNew_war_exploded/HomePageController/homePageUtent\"><button class=\"button\">Torna all'HomePage</button></a>\n" +
            "\n" +
            "\n" +
            "    </div>\n" +
            "\n" +
            "</div>\n";
    }else {

        for (i = 0; i < data.length; i++) {
            string += "   <div class=\"divVetrina\" >\n" +
                "    <p>" + data[i].productName + "</p>\n" +
                "\n" +
                "       <a href=\"http://localhost:8080/GameOverNew_war_exploded/GestioneProdottoController/showProductUtente?id=" + data[i].id + "\"> <img  src=\"/GameOverNew_war_exploded/cover/" + data[i].cover + "\">\n" +
                "       </a>\n" +
                "\n" +
                "        <p class=\"divPrezzo\">" + data[i].price + "&euro;</p>\n" +
                "    </div>"
        }
    }
    document.getElementById("ajax").className="containerCenter";
    document.getElementById("ajax").innerHTML=string;
}


/******************ricerca ajax per guest************************/



function  ricercaAjaxGuest(){


    var pattern=document.getElementById("ricerca1").value;
    var xhr= new XMLHttpRequest();

    xhr.onreadystatechange= function () {

        if (this.readyState == 4 && this.status == 200) {

            myFunctionXhrGuest(this);

        }
    };
    xhr.open("GET","/GameOverNew_war_exploded/GestioneProdottoController/searchProductWithAjax?stringa="+pattern,true);
    xhr.send();
}
function  myFunctionXhrGuest(xhr){
    var i;
    var data = JSON.parse(xhr.responseText);
    var string="";
    if(data.length==0){
        string+="<div class=\"vetrina\" id=\"ajax\">\n" +
            "\n" +
            "    <div class=\"vetrinaAjax\" >\n" +
            "\n" +
            "        <img src=\"/GameOverNew_war_exploded/icons/icons8-ricerca-50.png\">\n" +
            "\n" +
            "        <p> Siamo spiacenti, nessun risultato trovato! <br>\n" +
            "            Sembra che non ci siano giochi corrispondenti alla tua richiesta\n" +
            "        </p>\n" +
            "\n" +
            "        <a href=\"/GameOverNew_war_exploded/HomePageController/homePage\"><button class=\"button\">Torna all'HomePage</button></a>\n" +
            "\n" +
            "\n" +
            "    </div>\n" +
            "\n" +
            "</div>\n";
    }else {

        for (i = 0; i < data.length; i++) {
            string += "   <div class=\"divVetrina\" >\n" +
                "    <p>" + data[i].productName + "</p>\n" +
                "\n" +
                "       <a href=\"http://localhost:8080/GameOverNew_war_exploded/GestioneProdottoController/showProduct?id=" + data[i].id + "\"> <img  src=\"/GameOverNew_war_exploded/cover/" + data[i].cover + "\">\n" +
                "       </a>\n" +
                "\n" +
                "        <p class=\"divPrezzo\">" + data[i].price + "&euro;</p>\n" +
                "    </div>"
        }
    }
    document.getElementById("ajax").className="containerCenter";
    document.getElementById("ajax").innerHTML=string;
}

/******************ricerca ajax per Admin************************/
function  ricercaAjaxAdmin(){


    var pattern=document.getElementById("ricerca1").value;
    var xhr= new XMLHttpRequest();

    xhr.onreadystatechange= function () {

        if (this.readyState == 4 && this.status == 200) {

            myFunctionXhrAdmin(this);

        }
    };
    xhr.open("GET","/GameOverNew_war_exploded/GestioneProdottoController/searchProductWithAjax?stringa="+pattern,true);
    xhr.send();
}

function  myFunctionXhrAdmin(xhr){
    var i;
    var data = JSON.parse(xhr.responseText);
    var string="";

    if(data.length==0){
        string+="<div class=\"vetrina\" id=\"ajax\">\n" +
            "\n" +
            "    <div class=\"vetrinaAjax\" >\n" +
            "\n" +
            "        <img src=\"/GameOverNew_war_exploded/icons/icons8-ricerca-50.png\">\n" +
            "\n" +
            "        <p> Siamo spiacenti, nessun risultato trovato! <br>\n" +
            "            Sembra che non ci siano giochi corrispondenti alla tua richiesta\n" +
            "        </p>\n" +
            "\n" +
            "        <a href=\"/GameOverNew_war_exploded/HomePageController/homePageAdmin\"><button class=\"button\">Torna all'HomePage</button></a>\n" +
            "\n" +
            "\n" +
            "    </div>\n" +
            "\n" +
            "</div>\n";
    }else {

        for (i = 0; i < data.length; i++) {
            string += "   <div class=\"divVetrina\" >\n" +
                "    <p>" + data[i].productName + "</p>\n" +
                "\n" +
                "       <a href=\"http://localhost:8080/GameOverNew_war_exploded/GestioneProdottoController/updateProduct?id=" + data[i].id + "\"> <img  src=\"/GameOverNew_war_exploded/cover/" + data[i].cover + "\">\n" +
                "       </a>\n" +
                "\n" +
                "        <p class=\"divPrezzo\">" + data[i].price + "&euro;</p>\n" +
                "    </div>"
        }
    }
    document.getElementById("ajax").className="containerCenter";
    document.getElementById("ajax").innerHTML=string;
}



/**   funzione ajax ricercare ordini in base alla data e ai clienti      **/


function  ricercaOrdiniAjax(valore){

    var xhr= new XMLHttpRequest();
    xhr.onreadystatechange= function () {

        if (this.readyState == 4 && this.status == 200) {

            ricercaOrdiniXhrAjax(this,valore);

        }
    };
    xhr.open("GET","/GameOverNew_war_exploded/OrderServlet/showOrdersWithAjax?valore="+valore,true);
    xhr.send();

}


function  ricercaOrdiniXhrAjax(xhr,valore) {

    var i;
    var data = JSON.parse(xhr.responseText);
    var string = "";
    string += " <div class=\"orders\">\n" +
        "\n" +
        "            <ul>\n" +
        "             <div id=\"ordineMenu\">\n" +
        "                 <div>\n" +
        "                 <h3> Ordini effettuati </h3>\n" +
        "             </div>\n" +
        "                  <div id=\"select\">\n" +
        "                 <select id=\"op\"  name=\"ordine\"  onchange=\"ricercaOrdiniAjax(this.value)\">\n" +
        "                     <option id=\"op0\" hidden>"+valore+"</option>\n" +
        "                     <option id=\"op1\"  value=\"data crescente\"> data crescente</option>\n" +
        "                     <option id=\"op2\"  value=\"data decrescente\">data decrescente </option>\n" +
        "                     <option id=\"op3\"  value=\"cliente\">cliente</option>\n" +
        "                 </select>\n" +
        "                  </div>\n" +
        "\n" +
        "             </div>\n" +
        "\n" +
        "                <li>\n" +
        "                    <div><p><b>ID_ORDINE</b></p></div>\n" +
        "                    <div><p><b>DATA</b></p></div>\n" +
        "                    <div><p><b>NUM_PRODOTTI</b></p></div>\n" +
        "                    <div><p><b>CLIENTE</b></p></div>\n" +
        "                    <div></div>\n" +
        "                </li>"



    for (i = 0; i < data.length; i++) {

        console.log(data[i].date.year);
        var date1= new Date(data[i].date);

        string += "  <li>\n" +
            "                            <div><p>" + data[i].id + "</p></div>\n" +
            "                            <div><p> " + data[i].date.day+"/"+data[i].date.month+"/"+data[i].date.year+ " </p></div>\n" +
            "                            <div><p> " + data[i].num_product + " </p></div>\n" +
            "                             <div><p>"+ data[i].account.username+" </p></div>  \n"    +
            "                            <div><a href=\"/GameOverNewNew_war_exploded/OrderServlet/showOrderAdmin?id=" + data[i].id + "\"><button>DETTAGLIO ORDINE</button></a></div>\n" +
            "                        </li>"


    }

    string += "   </ul>\n" +
        "        </div>";


    document.getElementById("ajax").className="containerCenter";
    document.getElementById("ajax").innerHTML=string;

console.log(data)


}




function  bannerDinamic(){

    setInterval(banner,3000);



}



function  banner(){

    const a=["MicrosoftTeams-image.png","MicrosoftTeams-image1.png","MicrosoftTeams-image3.png","MicrosoftTeams-image4.png","MicrosoftTeams-image5.png","MicrosoftTeams-image6.png","MicrosoftTeams-image7.png","MicrosoftTeams-image8.png"];

    document.getElementById("a1").src="/GameOverNew_war_exploded/images/"+a[i];

    i++;
    if(i==a.length){
        i=0;
    }

}

function valutazioneChecked(valutazione,id){
    var i;
    const collection =document.getElementById("" + id).getElementsByTagName("span");
        for (i=0; i < valutazione; i++) {
            collection[i].className=  "fa fa-star checked";
        }
}

function controlloProdotti(prodotti,id){
    var i;
    for (i = 0; i < prodotti.length; i++) {
        if (prodotti[i].id ==id) {
            document.getElementById("a40").setAttribute("href", "");
            document.getElementById("button40").innerHTML="prodotto gia nel carrello";
        }
    }
}


/** validare form **/


























