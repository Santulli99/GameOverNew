function validateCognome() {
    var cognome = document.forms["modulo"]["cognome"].value;
    var cognomeRegex = /^[A-Za-zàèùòì' ]{3,30}$/;
    if ((cognome == "") || (cognome == "undefined")) {
        document.forms["modulo"]["cognome"].style.border = "2px solid #ee3124";
        document.forms["modulo"]["cognome"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["cognome"].style.outline = "1px solid";
        document.forms["modulo"]["cognome"].style.outlineColor = "#ee3124";
        document.getElementById("pr7").innerHTML = "Il cognome è obbligatorio";
        document.getElementById("pr7").style.display = "block";
        return false;
    } else if (!cognomeRegex.test(cognome)) {

        document.getElementById("pr7").innerHTML = "Il cognome non è valido.<br>Il cognome deve contenere solo lettere maiuscole e minuscole.";
        document.forms["modulo"]["cognome"].style.border = "2px solid #ee3124";
        document.forms["modulo"]["cognome"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["cognome"].style.outline = "1px solid";
        document.forms["modulo"]["cognome"].style.outlineColor = "#ee3124";
        document.getElementById("pr7").style.display = "block";
        return false;

    } else {

        document.forms["modulo"]["cognome"].style.outline = "initial";
        document.forms["modulo"]["cognome"].style.border = "";
        document.forms["modulo"]["cognome"].style.boxShadow = "none";
        document.getElementById("pr7").style.display = "none";
        return true;
    }

}


function validateNome() {
    var nome = document.forms["modulo"]["nome"].value;
    var nomeRegex = /^[A-Za-z ]{3,30}$/;
    if ((nome == "") || (nome == "undefined")) {
        document.forms["modulo"]["nome"].style.border = "2px solid #ee3124";
        document.forms["modulo"]["nome"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["nome"].style.outline = "1px solid";
        document.forms["modulo"]["nome"].style.outlineColor = "#ee3124";
        document.getElementById("pr6").innerHTML = "Il nome è obbligatorio";
        document.getElementById("pr6").style.display = "block";
        return false;
    } else if (!nomeRegex.test(nome)) {
        document.getElementById("pr6").innerHTML = "Il nome non è valido.<br>Il nome deve contenere solo lettere maiuscole e minuscole.";
        document.forms["modulo"]["nome"].style.border = "2px solid #ee3124";
        document.forms["modulo"]["nome"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["nome"].style.outline = "1px solid";
        document.forms["modulo"]["nome"].style.outlineColor = "#ee3124";
        document.getElementById("pr6").style.display = "block";
        return false;

    } else {

        document.forms["modulo"]["nome"].style.outline = "initial";
        document.forms["modulo"]["nome"].style.border = "";
        document.forms["modulo"]["nome"].style.boxShadow = "none";
        document.getElementById("pr6").style.display = "none";
        return true;
    }

}

function confermaUsername() {
    var userConferma = document.forms["modulo"]["username2"].value;
    var user = document.forms["modulo"]["username"].value;

    if ((userConferma == "") || (userConferma == "undefined")) {
        document.forms["modulo"]["username2"].style.border = "2px solid #ee3124";
        document.forms["modulo"]["username2"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["username2"].style.outline = "1px solid";
        document.forms["modulo"]["username2"].style.outlineColor = "#ee3124";
        document.getElementById("pr16").innerHTML = "L'username di conferma è obbligatorio";
        document.getElementById("pr16").style.display = "block";
        return false;
    } else if (userConferma != user) {
        document.getElementById("pr16").innerHTML = "I due username non coincidono";
        document.forms["modulo"]["username2"].style.border = "2px solid #ee3124";
        document.forms["modulo"]["username2"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["username2"].style.outline = "1px solid";
        document.forms["modulo"]["username2"].style.outlineColor = "#ee3124";
        document.getElementById("pr16").style.display = "block";
        return false;

    } else {

        document.forms["modulo"]["username2"].style.outline = "initial";
        document.forms["modulo"]["username2"].style.border = "";
        document.forms["modulo"]["username2"].style.boxShadow = "none";
        document.getElementById("pr16").style.display = "none";
        return true;
    }

}


function validateUsername() {
    var user = document.forms["modulo"]["username"].value;
    var userRegex = /^[A-Za-z0-9_-]{3,15}$/;
    if ((user == "") || (user == "undefined")) {
        document.forms["modulo"]["username"].style.border = "2px solid #ee3124";
        document.forms["modulo"]["username"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["username"].style.outline = "1px solid";
        document.forms["modulo"]["username"].style.outlineColor = "#ee3124";
        document.getElementById("pr5").innerHTML = "L'username è obbligatorio";
        document.getElementById("pr5").style.display = "block";
        return false;
    } else if (!userRegex.test(user)) {
        document.getElementById("pr5").innerHTML = "L'username non è valido.<br>L'username deve contenere solo caratteri alfanumerici più  <b>_</b> e <b>-</b> di lunghezza min 3 e max 15.";
        document.forms["modulo"]["username"].style.border = "2px solid #ee3124";
        document.forms["modulo"]["username"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["username"].style.outline = "1px solid";
        document.forms["modulo"]["username"].style.outlineColor = "#ee3124";
        document.getElementById("pr5").style.display = "block";
        return false;

    } else {

        document.forms["modulo"]["username"].style.outline = "initial";
        document.forms["modulo"]["username"].style.border = "";
        document.forms["modulo"]["username"].style.boxShadow = "none";
        document.getElementById("pr5").style.display = "none";
        return true;
    }

}

function validateDate() {
    var data = document.forms["modulo"]["dataNascita"].value;
    var dataNascita = new Date(data);
    var dataOggi = new Date();
    var year = dataOggi.getFullYear() - 18;
    var dataNuova = new Date();
    dataNuova.setDate(dataOggi.getDate());
    dataNuova.setFullYear(year);
    dataNuova.setMonth(dataOggi.getMonth());
    console.log(dataNuova);
    if (dataNascita <= dataNuova) {
        document.forms["modulo"]["dataNascita"].style.outline = "initial";
        document.forms["modulo"]["dataNascita"].style.border = "";
        document.forms["modulo"]["dataNascita"].style.boxShadow = "none";
        document.getElementById("pdata").style.display = "none";
        return true;
    } else {
        document.forms["modulo"]["dataNascita"].style.border = "2px solid #ee3124";
        document.forms["modulo"]["dataNascita"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["dataNascita"].style.outline = "1px solid";
        document.forms["modulo"]["dataNascita"].style.outlineColor = "#ee3124";
        document.getElementById("pdata").innerHTML = "La data di nascita è non è valida.<br>L'utente deve avere almeno 18 anni.";
        document.getElementById("pdata").style.display = "block";
        return false;
    }


}


function confermaPassword() {
    var pass1 = document.forms["modulo"]["password1"].value;
    var pass = document.forms["modulo"]["password"].value;

    if ((pass1 == "") || (pass1 == "undefined")) {
        document.forms["modulo"]["password1"].style.border = "2px solid #ee3124";
        document.forms["modulo"]["password1"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["password1"].style.outline = "1px solid";
        document.forms["modulo"]["password1"].style.outlineColor = "#ee3124";
        document.getElementById("pr4").innerHTML = "La password di conferma è obbligatoria";
        document.getElementById("pr4").style.display = "block";
        return false;
    } else if (pass1 != pass) {
        document.getElementById("pr4").innerHTML = "Le due password non coincidono";
        document.forms["modulo"]["password1"].style.border = "2px solid #ee3124";
        document.forms["modulo"]["password1"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["password1"].style.outline = "1px solid";
        document.forms["modulo"]["password1"].style.outlineColor = "#ee3124";
        document.getElementById("pr4").style.display = "block";
        return false;

    } else {

        document.forms["modulo"]["password1"].style.outline = "initial";
        document.forms["modulo"]["password1"].style.border = "";
        document.forms["modulo"]["password1"].style.boxShadow = "none";
        document.getElementById("pr4").style.display = "none";
        return true;
    }

}


function validatePassword() {
    var pass = document.forms["modulo"]["password"].value;
    var passRegex = /^[A-Za-z0-9]{8,20}$/;
    if ((pass == "") || (pass == "undefined")) {
        document.forms["modulo"]["password"].style.border = "2px solid #ee3124";
        document.forms["modulo"]["password"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["password"].style.outline = "1px solid";
        document.forms["modulo"]["password"].style.outlineColor = "#ee3124";
        document.getElementById("pr3").innerHTML = "La password è obbligatoria";
        document.getElementById("pr3").style.display = "block";
        return false;
    } else if (!passRegex.test(pass)) {
        document.getElementById("pr3").innerHTML = "La password non è valida.<br>La password deve contenere lettere minuscole,maiuscole,numeri e deve essere compresa tra 8 e 20 caratteri";
        document.forms["modulo"]["password"].style.border = "2px solid #ee3124";
        document.forms["modulo"]["password"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["password"].style.outline = "1px solid";
        document.forms["modulo"]["password"].style.outlineColor = "#ee3124";
        document.getElementById("pr3").style.display = "block";
        return false;

    } else {

        document.forms["modulo"]["password"].style.outline = "initial";
        document.forms["modulo"]["password"].style.border = "";
        document.forms["modulo"]["password"].style.boxShadow = "none";
        document.getElementById("pr3").style.display = "none";
        return true;
    }

}

/*validate pagamento*/
function checkIntestatario() {
    var inteCarta = document.getElementById("inteCarta").value;
    var inteCartaRegex = /^[A-Z][a-z]+\s[A-Za-zàèùòì' ]+$/;
    if ((inteCarta == "") || (inteCarta == "undefined")) {
        document.getElementById("inteCarta").style.border = "2px solid #ee3124";
        document.getElementById("inteCarta").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("inteCarta").style.outline = "1px solid";
        document.getElementById("inteCarta").style.outlineColor = "#ee3124";
        document.getElementById("pr1").innerHTML = "Inserire Nome e Cognome";
        document.getElementById("pr1").style.display = "block";
        return false;
    } else if (!inteCartaRegex.test(inteCarta)) {
        document.getElementById("pr1").innerHTML = "Nome e/o Cognome  non  valido.<br>Il Nome e Cognome  devono iniziare con lettera maiuscola e contenere solo lettere minuscole.";
        document.getElementById("inteCarta").style.border = "2px solid #ee3124";
        document.getElementById("inteCarta").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("inteCarta").style.outline = "1px solid";
        document.getElementById("inteCarta").style.outlineColor = "#ee3124";
        document.getElementById("pr1").style.display = "block";
        return false;

    } else {

        document.getElementById("inteCarta").style.outline = "initial";
        document.getElementById("inteCarta").style.border = "";
        document.getElementById("inteCarta").style.boxShadow = "none";
        document.getElementById("inteCarta").style.outline = "1px solid";
        document.getElementById("inteCarta").style.outlineColor = "#0a9a0d";
        document.getElementById("pr1").style.display = "none";
        return true;
    }
}

function checkNumeroCarta() {
    var numCarta = document.getElementById("NumCarta").value;
    var numCartaRegex = /^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14})$/;
    if ((numCarta == "") || (numCarta == "undefined")) {
        document.getElementById("NumCarta").style.border = "2px solid #ee3124";
        document.getElementById("NumCarta").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("NumCarta").style.outline = "1px solid";
        document.getElementById("NumCarta").style.outlineColor = "#ee3124";
        document.getElementById("pr2").innerHTML = "Inserire numero Carta di Credito";
        document.getElementById("pr2").style.display = "block";
        return false;
    } else if (!numCartaRegex.test(numCarta)) {
        document.getElementById("pr2").innerHTML = "Numero Carta di Credito non valido.<br>Il numero della Carta di Credito deve essere di 16 cifre.";
        document.getElementById("NumCarta").style.border = "2px solid #ee3124";
        document.getElementById("NumCarta").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("NumCarta").style.outline = "1px solid";
        document.getElementById("NumCarta").style.outlineColor = "#ee3124";
        document.getElementById("pr2").style.display = "block";
        return false;

    } else {

        document.getElementById("NumCarta").style.outline = "initial";
        document.getElementById("NumCarta").style.border = "";
        document.getElementById("NumCarta").style.boxShadow = "none";
        document.getElementById("NumCarta").style.outline = "1px solid";
        document.getElementById("NumCarta").style.outlineColor = "#0a9a0d";
        document.getElementById("pr2").style.display = "none";
        return true;
    }

}


function meseCheck0() {
    var mese = document.getElementById("meseCarta").value;
    if (mese.length == 1) {
        document.getElementById("meseCarta").value = "0" + mese;
    }
}

function meseCheck() {
    var mese = document.getElementById("meseCarta").value;
    if (mese != "" && mese <= 12) {
        document.getElementById("meseCarta").style.outline = "initial";
        document.getElementById("meseCarta").style.border = "";
        document.getElementById("meseCarta").style.boxShadow = "none";
        document.getElementById("meseCarta").style.outline = "1px solid";
        document.getElementById("meseCarta").style.outlineColor = "#0a9a0d";
        document.getElementById("pr3").style.display = "none";
        return true;
    } else {
        document.getElementById("pr3").innerHTML = "Mese non valido.";
        document.getElementById("meseCarta").style.border = "2px solid #ee3124";
        document.getElementById("meseCarta").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("meseCarta").style.outline = "1px solid";
        document.getElementById("meseCarta").style.outlineColor = "#ee3124";
        document.getElementById("pr3").style.display = "block";
        return false;
    }
}

function annoCheck() {
    var anno = document.getElementById("annoCarta").value;
    var data = new Date();
    var annoAttuale = data.getFullYear();
    if (anno >= annoAttuale) {
        document.getElementById("annoCarta").style.outline = "initial";
        document.getElementById("annoCarta").style.border = "";
        document.getElementById("annoCarta").style.boxShadow = "none";
        document.getElementById("annoCarta").style.outline = "1px solid";
        document.getElementById("annoCarta").style.outlineColor = "#0a9a0d";
        document.getElementById("pr5").style.display = "none";
        return true;
    } else {
        document.getElementById("pr5").innerHTML = "L'anno di scadenza della carta è nel passato.";
        document.getElementById("annoCarta").style.border = "2px solid #ee3124";
        document.getElementById("annoCarta").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("annoCarta").style.outline = "1px solid";
        document.getElementById("annoCarta").style.outlineColor = "#ee3124";
        document.getElementById("pr5").style.display = "block";
        return false;
    }
}


function checkCvC() {
    var cvcCarta = document.getElementById("CvcCarta").value;
    var cvcCartaRegex = /^\d{3,4}$/;
    if ((cvcCarta == "") || (cvcCarta == "undefined")) {
        document.getElementById("CvcCarta").style.border = "2px solid #ee3124";
        document.getElementById("CvcCarta").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("CvcCarta").style.outline = "1px solid";
        document.getElementById("CvcCarta").style.outlineColor = "#ee3124";
        document.getElementById("pr4").innerHTML = "Inserire CVC/CVV";
        document.getElementById("pr4").style.display = "block";
        return false;
    } else if (!cvcCartaRegex.test(cvcCarta)) {
        document.getElementById("pr4").innerHTML = "CVC/CVV non valido.<br>Il CVC/CVV deve essere di 3/4 cifre.";
        document.getElementById("CvcCarta").style.border = "2px solid #ee3124";
        document.getElementById("CvcCarta").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("CvcCarta").style.outline = "1px solid";
        document.getElementById("CvcCarta").style.outlineColor = "#ee3124";
        document.getElementById("pr4").style.display = "block";
        return false;

    } else {
        document.getElementById("CvcCarta").style.outline = "initial";
        document.getElementById("CvcCarta").style.border = "";
        document.getElementById("CvcCarta").style.boxShadow = "none";
        document.getElementById("CvcCarta").style.outline = "1px solid";
        document.getElementById("CvcCarta").style.outlineColor = "#0a9a0d";
        document.getElementById("pr4").style.display = "none";
        return true;
    }
}


function validateAcquisto() {
    if (checkIntestatario() && checkNumeroCarta() && meseCheck() && annoCheck() && checkCvC()) {
        return true;
    }
    return false;
}


/******************************************************************/


function confermaEmail() {
    var email1 = document.forms["modulo"]["email1"].value;
    var email = document.forms["modulo"]["email"].value;
    if ((email1 == "") || (email1 == "undefined")) {
        document.forms["modulo"]["email1"].style.border = "2px solid #ee3124";
        document.forms["modulo"]["email1"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["email1"].style.outline = "1px solid";
        document.forms["modulo"]["email1"].style.outlineColor = "#ee3124";
        document.getElementById("pr2").innerHTML = "L'indirizzo e-mail di conferma è obbligatorio";
        document.getElementById("pr2").style.display = "block";
        return false;
    } else if (email1 != email) {

        document.getElementById("pr2").innerHTML = "I due indirizzi email non coincidono";
        document.forms["modulo"]["email1"].style.border = "2px solid #ee3124";
        document.forms["modulo"]["email1"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["email1"].style.outline = "1px solid";
        document.forms["modulo"]["email1"].style.outlineColor = "#ee3124";
        document.getElementById("pr2").style.display = "block";
        return false;

    } else {

        document.forms["modulo"]["email1"].style.outline = "initial";
        document.forms["modulo"]["email1"].style.border = "";
        document.forms["modulo"]["email1"].style.boxShadow = "none";
        document.getElementById("pr2").style.display = "none";
        return true;
    }


}


function validateEmail() {
    var email = document.forms["modulo"]["email"].value;
    var emailRegex = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;


    if ((email == "") || (email == "undefined")) {
        document.forms["modulo"]["email"].style.border = "2px solid #ee3124";
        document.forms["modulo"]["email"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["email"].style.outline = "1px solid";
        document.forms["modulo"]["email"].style.outlineColor = "#ee3124";
        document.getElementById("pr1").innerHTML = "L'indirizzo e-mail è obbligatorio";
        document.getElementById("pr1").style.display = "block";
        return false;
    } else if (!emailRegex.test(email)) {
        document.getElementById("pr1").innerHTML = "L'indirizzo email non è valido.<br>Immetti l'indirizzo e-mail nel formato nomeutente@dominio.it";
        document.forms["modulo"]["email"].style.border = "2px solid #ee3124";
        document.forms["modulo"]["email"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["email"].style.outline = "1px solid";
        document.forms["modulo"]["email"].style.outlineColor = "#ee3124";
        document.getElementById("pr1").style.display = "block";
        return false;

    } else {

        document.forms["modulo"]["email"].style.outline = "initial";
        document.forms["modulo"]["email"].style.border = "";
        document.forms["modulo"]["email"].style.boxShadow = "none";
        document.getElementById("pr1").style.display = "none";
        return true;
    }

}

function validateNomeProdotto(){
    var nome = document.getElementById("nome").value;
    var nomeRegex = /^.{3,50}$/;


    if ((nome == "") || (nome == "undefined")) {
        document.getElementById("nome").style.border = "2px solid #ee3124";
        document.getElementById("nome").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("nome").style.outline = "1px solid";
        document.getElementById("nome").style.outlineColor = "#ee3124";
        document.getElementById("pr1").innerHTML = "Il nome del prodotto è obbligatorio";
        document.getElementById("pr1").style.display = "block";
        return false;
    } else if (!nomeRegex.test(nome)) {
        document.getElementById("pr1").innerHTML = "Il nome del prodotto non è valido.<br>Il nome del prodotto deve contenere solo<br>caratteri alfanumerici di lunghezza min 3.";
        document.getElementById("nome").style.border = "2px solid #ee3124";
        document.getElementById("nome").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("nome").style.outline = "1px solid";
        document.getElementById("nome").style.outlineColor = "#ee3124";
        document.getElementById("pr1").style.display = "block";
        return false;

    } else {
        document.getElementById("nome").style.outline = "initial";
        document.getElementById("nome").style.border = "";
        document.getElementById("nome").style.boxShadow = "none";
        document.getElementById("nome").style.outline = "1px solid";
        document.getElementById("nome").style.outlineColor = "#0a9a0d";
        document.getElementById("pr1").style.display = "none";
        return true;
    }
}

function validatePrezzoProdotto(){
    var prezzo = document.getElementById("prezzo").value;
    var prezzoRegex = /^\d+(\.\d{2})?$/;


    if ((prezzo == "") || (prezzo == "undefined")) {
        document.getElementById("prezzo").style.border = "2px solid #ee3124";
        document.getElementById("prezzo").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("prezzo").style.outline = "1px solid";
        document.getElementById("prezzo").style.outlineColor = "#ee3124";
        document.getElementById("pr2").innerHTML = "Il prezzo del prodotto è obbligatorio";
        document.getElementById("pr2").style.display = "block";
        return false;
    } else if (!prezzoRegex.test(prezzo)) {
        document.getElementById("pr2").innerHTML = "Il prezzo del prodotto non è valido.<br>Il prezzo del prodotto deve contenere<br>solo cifre e il <b>.</b>";
        document.getElementById("prezzo").style.border = "2px solid #ee3124";
        document.getElementById("prezzo").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("prezzo").style.outline = "1px solid";
        document.getElementById("prezzo").style.outlineColor = "#ee3124";
        document.getElementById("pr2").style.display = "block";
        return false;

    } else {
        document.getElementById("prezzo").style.outline = "initial";
        document.getElementById("prezzo").style.border = "";
        document.getElementById("prezzo").style.boxShadow = "none";
        document.getElementById("prezzo").style.outline = "1px solid";
        document.getElementById("prezzo").style.outlineColor = "#0a9a0d";
        document.getElementById("pr2").style.display = "none";
        return true;
    }
}

function validateDescrizioneProdotto(){
    var testo = document.getElementById("content");
    var descrizione=testo.value;
    var descrizioneRegex = /^.{10,5000}$/;

    if ((descrizione == "") || (descrizione == "undefined")) {
        document.getElementById("content").style.border = "2px solid #ee3124";
        document.getElementById("content").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("content").style.outline = "1px solid";
        document.getElementById("content").style.outlineColor = "#ee3124";
        document.getElementById("pr3").innerHTML = "La descrizione è obbligatoria";
        document.getElementById("pr3").style.display = "block";
        return false;
    } else if (!descrizioneRegex.test(descrizione)) {
        document.getElementById("pr3").innerHTML = "Descrizione non valida.La descrizione deve contenere minimo 10 caratteri e massimo 5000 caratteri.";
        document.getElementById("content").style.border = "2px solid #ee3124";
        document.getElementById("content").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("content").style.outline = "1px solid";
        document.getElementById("content").style.outlineColor = "#ee3124";
        document.getElementById("pr3").style.display = "block";
        return false;

    } else {
        document.getElementById("content").style.outline = "initial";
        document.getElementById("content").style.border = "";
        document.getElementById("content").style.boxShadow = "none";
        document.getElementById("content").style.outline = "1px solid";
        document.getElementById("content").style.outlineColor = "#0a9a0d";
        document.getElementById("pr3").style.display = "none";
        return true;
    }
}

function validateCoverProdotto(){
    const file = document.getElementById("cover");
    const fileTrovato=file.files[0];

    if (fileTrovato.size > 1024 * 1024) {
        document.getElementById("pr4").innerHTML = "Cover non valida.<br>La cover deve avere dimensione massima 1MB";
        document.getElementById("cover").style.border = "2px solid #ee3124";
        document.getElementById("cover").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("cover").style.outline = "1px solid";
        document.getElementById("cover").style.outlineColor = "#ee3124";
        document.getElementById("pr4").style.display = "block";
        return false;
    }else{
        document.getElementById("cover").style.outline = "initial";
        document.getElementById("cover").style.border = "";
        document.getElementById("cover").style.boxShadow ="none";
        document.getElementById("pr4").style.display = "none";
        return true;
    }
}

function validateDataUscitaProdotto(){
    var data = document.getElementById("datau").value;
    var dataUscita = new Date(data);
    var dataOggi = new Date();

    if ((data == "") || (data == "undefined")) {
        document.getElementById("datau").style.border = "2px solid #ee3124";
        document.getElementById("datau").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("datau").style.outline = "1px solid";
        document.getElementById("datau").style.outlineColor = "#ee3124";
        document.getElementById("pr5").innerHTML = "La data di uscita è obbligatoria";
        document.getElementById("pr5").style.display = "block";
        return false;
    } else if (dataUscita>dataOggi) {
        document.getElementById("pr5").innerHTML = "Data di uscita non valida.<br>La data di uscita deve precedere<br>la data odierna";
        document.getElementById("datau").style.border = "2px solid #ee3124";
        document.getElementById("datau").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("datau").style.outline = "1px solid";
        document.getElementById("datau").style.outlineColor = "#ee3124";
        document.getElementById("pr5").style.display = "block";
        return false;

    } else {
        document.getElementById("datau").style.outline = "initial";
        document.getElementById("datau").style.border = "";
        document.getElementById("datau").style.boxShadow = "none";
        document.getElementById("datau").style.outline = "1px solid";
        document.getElementById("datau").style.outlineColor = "#0a9a0d";
        document.getElementById("pr5").style.display = "none";
        return true;
    }
}

function validateTitoloRecensione(){
    var titolo = document.getElementById("sottobox3_2").value;
    console.log(titolo);

    if ((titolo == "") || (titolo == "undefined")) {
        document.getElementById("sottobox3_2").style.border = "2px solid #ee3124";
        document.getElementById("sottobox3_2").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("sottobox3_2").style.outline = "1px solid";
        document.getElementById("sottobox3_2").style.outlineColor = "#ee3124";
        document.getElementById("pr1").innerHTML = "Il titolo è obbligatorio";
        document.getElementById("pr1").style.display = "block";
        return false;
    } else if (titolo.length>100) {
        document.getElementById("pr1").innerHTML = "Titolo non valido.Inserire un titolo la cui lunghezza non sia > di 100 caratteri";
        document.getElementById("sottobox3_2").style.border = "2px solid #ee3124";
        document.getElementById("sottobox3_2").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("sottobox3_2").style.outline = "1px solid";
        document.getElementById("sottobox3_2").style.outlineColor = "#ee3124";
        document.getElementById("pr1").style.display = "block";
        return false;

    } else {
        document.getElementById("sottobox3_2").style.outline = "initial";
        document.getElementById("sottobox3_2").style.border = "";
        document.getElementById("sottobox3_2").style.boxShadow = "none";
        document.getElementById("sottobox3_2").style.outline = "1px solid";
        document.getElementById("sottobox3_2").style.outlineColor = "#0a9a0d";
        document.getElementById("pr1").style.display = "none";
        return true;
    }
}

function validateDescrizioneRecensione(){
    var descrizione = document.getElementById("sottobox4_2").value;
    console.log(descrizione);
    if ((descrizione == "") || (descrizione == "undefined")) {
        document.getElementById("sottobox4_2").style.border = "2px solid #ee3124";
        document.getElementById("sottobox4_2").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("sottobox4_2").style.outline = "1px solid";
        document.getElementById("sottobox4_2").style.outlineColor = "#ee3124";
        document.getElementById("pr2").innerHTML = "La descrizione è obbligatoria";
        document.getElementById("pr2").style.display = "block";
        return false;
    } else if (descrizione.length>500) {
        document.getElementById("pr2").innerHTML = "Descrizione non valida.Inserire una descrizione la cui lunghezza non sia > di 500 caratteri";
        document.getElementById("sottobox4_2").style.border = "2px solid #ee3124";
        document.getElementById("sottobox4_2").style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("sottobox4_2").style.outline = "1px solid";
        document.getElementById("sottobox4_2").style.outlineColor = "#ee3124";
        document.getElementById("pr2").style.display = "block";
        return false;

    } else {
        document.getElementById("sottobox4_2").style.outline = "initial";
        document.getElementById("sottobox4_2").style.border = "";
        document.getElementById("sottobox4_2").style.boxShadow = "none";
        document.getElementById("sottobox4_2").style.outline = "1px solid";
        document.getElementById("sottobox4_2").style.outlineColor = "#0a9a0d";
        document.getElementById("pr2").style.display = "none";
        return true;
    }
}


function validateAddProdotto(){
    if (validateNomeProdotto() && validatePrezzoProdotto() && validateDescrizioneProdotto() &&
        validateCoverProdotto() && validateDataUscitaProdotto()) {
        return true;
    }
    return false;
}
function validateUpdateProdotto(){
    if (validateNomeProdotto() && validatePrezzoProdotto() && validateDescrizioneProdotto()) {
        return true;
    }
    return false;
}


/*******************Ajax per verificare se email già presente nel database************************/

function checkEmailSignAjax() {
    var email = document.forms["modulo"]["email"].value;
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200) {
            if (this.responseText == "" || this.responseText == null) {
                document.getElementById("pr100").style.display = "none";
                return true;
            } else {
                document.forms["modulo"]["email"].style.border = "2px solid #ee3124";
                document.forms["modulo"]["email"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
                document.forms["modulo"]["email"].style.outline = "1px solid";
                document.forms["modulo"]["email"].style.outlineColor = "#ee3124";
                document.getElementById("pr100").style.display = "block";
                document.getElementById("pr100").innerHTML = this.responseText;
                return false;
            }
        }
    };
    xhr.open("GET", "/GameOverNew_war_exploded/RegistrazioneController/checkEmailSign?email=" + email, true);
    xhr.send();

}

function checkEmailAjax() {
    var email = document.forms["modulo"]["email"].value;
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200) {
            if (this.responseText == "" || this.responseText == null) {
                document.getElementById("pr100").style.display = "none";
                return true;
            } else {
                document.forms["modulo"]["email"].style.border = "2px solid #ee3124";
                document.forms["modulo"]["email"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
                document.forms["modulo"]["email"].style.outline = "1px solid";
                document.forms["modulo"]["email"].style.outlineColor = "#ee3124";
                document.getElementById("pr100").style.display = "block";
                document.getElementById("pr100").innerHTML = this.responseText;
                return false;
            }
        }
    };
    xhr.open("GET", "/GameOverNew_war_exploded/GestioneUtenteController/checkEmail?email=" + email, true);
    xhr.send();

}


/****************************w*********************************************************/
function validateForm() {
    if (validateCognome() && validateEmail() && validateNome() && validatePassword() && validateUsername()
        && validateDate() && confermaEmail() && confermaPassword()) {
        return true;
    }
    return false;
}



