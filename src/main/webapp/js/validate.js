function validateCap() {
    var cap=document.forms["modulo"]["cap"].value;
    var capRegex=/^\d{5}$/;
    if((cap == "") || (cap == "undefined")){
        document.forms["modulo"]["cap"].style.border="2px solid #ee3124";
        document.forms["modulo"]["cap"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["cap"].style.outline="1px solid";
        document.forms["modulo"]["cap"].style.outlineColor="#ee3124";
        document.getElementById("pr15").innerHTML="Il CAP è obbligatorio";
        document.getElementById("pr15").style.display="block";
        return false;
    }

    else if(!capRegex.test(cap)) {
        document.getElementById("pr15").innerHTML="Il CAP non è valido.<br>Il CAP deve avere 5 cifre";
        document.forms["modulo"]["cap"].style.border="2px solid #ee3124"
        document.forms["modulo"]["cap"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["cap"].style.outline="1px solid";
        document.forms["modulo"]["cap"].style.outlineColor="#ee3124";
        document.getElementById("pr15").style.display="block";
        return false;

    }
    else{

        document.forms["modulo"]["cap"].style.outline="initial";
        document.forms["modulo"]["cap"].style.border="";
        document.forms["modulo"]["cap"].style.boxShadow="none";
        document.getElementById("pr15").style.display="none";
        return true;
    }

}




function validateCivico() {
    var civico=document.forms["modulo"]["civico"].value;

    if((civico == "") || (civico == "undefined")){
        document.forms["modulo"]["civico"].style.border="2px solid #ee3124";
        document.forms["modulo"]["civico"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["civico"].style.outline="1px solid";
        document.forms["modulo"]["civico"].style.outlineColor="#ee3124";
        document.getElementById("pr14").innerHTML="Il numero civico è obbligatorio";
        document.getElementById("pr14").style.display="block";
        return false;
    }


    else if(civico<=0 || civico>=500) {
        document.getElementById("pr14").innerHTML="Il numero civico non è valido.<br>Il numero civico deve essere compreso tra 0 e 500";
        document.forms["modulo"]["civico"].style.border="2px solid #ee3124"
        document.forms["modulo"]["civico"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["civico"].style.outline="1px solid";
        document.forms["modulo"]["civico"].style.outlineColor="#ee3124";
        document.getElementById("pr14").style.display="block";
        return false;

    }
    else{

        document.forms["modulo"]["civico"].style.outline="initial";
        document.forms["modulo"]["civico"].style.border="";
        document.forms["modulo"]["civico"].style.boxShadow="none";
        document.getElementById("pr14").style.display="none";
        return true;
    }

}




function validateVia() {
    var via=document.forms["modulo"]["via"].value;
    var viaRegex=/^[A-Za-z ]{2,30}$/;

    if((via == "") || (via == "undefined")){
        document.forms["modulo"]["via"].style.border="2px solid #ee3124";
        document.forms["modulo"]["via"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["via"].style.outline="1px solid";
        document.forms["modulo"]["via"].style.outlineColor="#ee3124";
        document.getElementById("pr13").innerHTML="La via è obbligatoria";
        document.getElementById("pr13").style.display="block";
        return false;
    }


    else if(!viaRegex.test(via)) {
        document.getElementById("pr13").innerHTML="La via non è valida<br>La via deve contenere solo lettere maiuscole e minuscole.";
        document.forms["modulo"]["via"].style.border="2px solid #ee3124";
        document.forms["modulo"]["via"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["via"].style.outline="1px solid";
        document.forms["modulo"]["via"].style.outlineColor="#ee3124";
        document.getElementById("pr13").style.display="block";
        return false;

    }
    else{

        document.forms["modulo"]["via"].style.outline="initial";
        document.forms["modulo"]["via"].style.border="";
        document.forms["modulo"]["via"].style.boxShadow="none";
        document.getElementById("pr13").style.display="none";
        return true;
    }

}


function  validateProvincia(){
    var provincia=document.forms["modulo"]["provincia"].value;
    var proviciaRegex=/^[A-Za-z ]{3,30}$/;

    if((provincia == "") || (provincia == "undefined")){
        document.forms["modulo"]["provincia"].style.border="2px solid #ee3124";
        document.forms["modulo"]["provincia"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["provincia"].style.outline="1px solid";
        document.forms["modulo"]["provincia"].style.outlineColor="#ee3124";
        document.getElementById("pr12").innerHTML="La provincia è obbligatoria";
        document.getElementById("pr12").style.display="block";
        return false;
    }

    else if(!proviciaRegex.test(provincia)) {
        document.getElementById("pr12").innerHTML="La provincia non è valida.<br>La provincia deve contenere solo lettere maiuscole e minuscole.";
        document.forms["modulo"]["provincia"].style.border="2px solid #ee3124";
        document.forms["modulo"]["provincia"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["provincia"].style.outline="1px solid";
        document.forms["modulo"]["provincia"].style.outlineColor="#ee3124";
        document.getElementById("pr12").style.display="block";
        return false;

    }
    else{

        document.forms["modulo"]["provincia"].style.outline="initial";
        document.forms["modulo"]["provincia"].style.border="";
        document.forms["modulo"]["provincia"].style.boxShadow="none";
        document.getElementById("pr12").style.display="none";
        return true;
    }

}


function  validateCitta(){
    var citta=document.forms["modulo"]["citta"].value;
    var cittaRegex=/^[A-Za-z ]{3,30}$/;

    if((citta == "") || (citta == "undefined")){
        document.forms["modulo"]["citta"].style.border="2px solid #ee3124";
        document.forms["modulo"]["citta"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["citta"].style.outline="1px solid";
        document.forms["modulo"]["citta"].style.outlineColor="#ee3124";
        document.getElementById("pr11").innerHTML="La città è obbligatoria";
        document.getElementById("pr11").style.display="block";
        return false;
    }


    else if(!cittaRegex.test(citta)) {
        document.getElementById("pr11").innerHTML="La città non è valida.<br>La città deve contenere solo lettere maiuscole e minuscole.";
        document.forms["modulo"]["citta"].style.border="2px solid #ee3124";
        document.forms["modulo"]["citta"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["citta"].style.outline="1px solid";
        document.forms["modulo"]["citta"].style.outlineColor="#ee3124";
        document.getElementById("pr11").style.display="block";
        return false;

    }
    else{

        document.forms["modulo"]["citta"].style.outline="initial";
        document.forms["modulo"]["citta"].style.border="";
        document.forms["modulo"]["citta"].style.boxShadow="none";
        document.getElementById("pr11").style.display="none";
        return true;
    }

}

function  validateCf(){
    var cf=document.forms["modulo"]["codiceFiscale"].value;
    var cfRegex=/^[a-zA-Z]{6}\d\d[a-zA-Z]\d\d[a-zA-Z]\d\d\d[a-zA-Z]$/;

    if((cf == "") || (cf == "undefined")){
        document.forms["modulo"]["codiceFiscale"].style.border="2px solid #ee3124";
        document.forms["modulo"]["codiceFiscale"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["codiceFiscale"].style.outline="1px solid";
        document.forms["modulo"]["codiceFiscale"].style.outlineColor="#ee3124";
        document.getElementById("pr10").innerHTML="Il codice fiscale è obbligatorio";
        document.getElementById("pr10").style.display="block";
        return false;
    }



    else if(!cfRegex.test(cf)) {
        document.getElementById("pr10").innerHTML="Il codice fiscale non è valido";
        document.forms["modulo"]["codiceFiscale"].style.border="2px solid #ee3124";
        document.forms["modulo"]["codiceFiscale"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["codiceFiscale"].style.outline="1px solid";
        document.forms["modulo"]["codiceFiscale"].style.outlineColor="#ee3124";
        document.getElementById("pr10").style.display="block";
        return false;

    }
    else{

        document.forms["modulo"]["codiceFiscale"].style.outline="initial";
        document.forms["modulo"]["codiceFiscale"].style.border="";
        document.forms["modulo"]["codiceFiscale"].style.boxShadow="none";
        document.getElementById("pr10").style.display="none";
        return true;
    }

}



function  validatecittaNascita(){
    var nascita=document.forms["modulo"]["cittaNascita"].value;
    var nascitaRegex=/^[A-Za-z ]{3,30}$/;
    if((nascita == "") || (nascita == "undefined")){
        document.forms["modulo"]["cittaNascita"].style.border="2px solid #ee3124";
        document.forms["modulo"]["cittaNascita"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["cittaNascita"].style.outline="1px solid";
        document.forms["modulo"]["cittaNascita"].style.outlineColor="#ee3124";
        document.getElementById("pr9").innerHTML="La città di nascita è obbligatoria";
        document.getElementById("pr9").style.display="block";
        return false;
    }

    else if(!nascitaRegex.test(nascita)) {
        document.getElementById("pr9").innerHTML="La città di nascita non è valida.<br>La città di nascita deve contenere solo lettere maiuscole e minuscole.";
        document.forms["modulo"]["cittaNascita"].style.border="2px solid #ee3124";
        document.forms["modulo"]["cittaNascita"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["cittaNascita"].style.outline="1px solid";
        document.forms["modulo"]["cittaNascita"].style.outlineColor="#ee3124";
        document.getElementById("pr9").style.display="block";
        return false;

    }
    else{

        document.forms["modulo"]["cittaNascita"].style.outline="initial";
        document.forms["modulo"]["cittaNascita"].style.border="";
        document.forms["modulo"]["cittaNascita"].style.boxShadow="none";
        document.getElementById("pr9").style.display="none";
        return true;
    }

}



function  validateTel(){
    var tel=document.forms["modulo"]["telefono"].value;
    var telRegex=/^((00|\+)[\. ]??)??3\d{2}[\. ]??\d{6,7}$/;
    if((tel == "") || (tel == "undefined")){
        document.forms["modulo"]["telefono"].style.border="2px solid #ee3124";
        document.forms["modulo"]["telefono"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["telefono"].style.outline="1px solid";
        document.forms["modulo"]["telefono"].style.outlineColor="#ee3124";
        document.getElementById("pr8").innerHTML="Il numero di telefono è obbligatorio";
        document.getElementById("pr8").style.display="block";
        return false;
    }

    else if(!telRegex.test(tel)) {
        document.getElementById("pr8").innerHTML="Il numero di telefono non è valido.<br>Il numero di telefono deve contenere min 9 cifre max 10 cifre.";
        document.forms["modulo"]["telefono"].style.border="2px solid #ee3124";
        document.forms["modulo"]["telefono"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["telefono"].style.outline="1px solid";
        document.forms["modulo"]["telefono"].style.outlineColor="#ee3124";
        document.getElementById("pr8").style.display="block";
        return false;

    }
    else{

        document.forms["modulo"]["telefono"].style.outline="initial";
        document.forms["modulo"]["telefono"].style.border="";
        document.forms["modulo"]["telefono"].style.boxShadow="none";
        document.getElementById("pr8").style.display="none";
        return true;
    }

}




function  validateCognome(){
    var cognome=document.forms["modulo"]["cognome"].value;
    var cognomeRegex=/^[A-Za-zàèùòì' ]{3,30}$/;
    if((cognome == "") || (cognome == "undefined")){
        document.forms["modulo"]["cognome"].style.border="2px solid #ee3124";
        document.forms["modulo"]["cognome"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["cognome"].style.outline="1px solid";
        document.forms["modulo"]["cognome"].style.outlineColor="#ee3124";
        document.getElementById("pr7").innerHTML="Il cognome è obbligatorio";
        document.getElementById("pr7").style.display="block";
        return false;
    }

    else if(!cognomeRegex.test(cognome)) {

        document.getElementById("pr7").innerHTML="Il cognome non è valido.<br>Il cognome deve contenere solo lettere maiuscole e minuscole.";
        document.forms["modulo"]["cognome"].style.border="2px solid #ee3124";
        document.forms["modulo"]["cognome"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["cognome"].style.outline="1px solid";
        document.forms["modulo"]["cognome"].style.outlineColor="#ee3124";
        document.getElementById("pr7").style.display="block";
        return false;

    }
    else{

        document.forms["modulo"]["cognome"].style.outline="initial";
        document.forms["modulo"]["cognome"].style.border="";
        document.forms["modulo"]["cognome"].style.boxShadow="none";
        document.getElementById("pr7").style.display="none";
        return true;
    }

}






function  validateNome(){
    var nome=document.forms["modulo"]["nome"].value;
    var nomeRegex=/^[A-Za-z ]{3,30}$/;
    if((nome == "") || (nome == "undefined")){
        document.forms["modulo"]["nome"].style.border="2px solid #ee3124";
        document.forms["modulo"]["nome"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["nome"].style.outline="1px solid";
        document.forms["modulo"]["nome"].style.outlineColor="#ee3124";
        document.getElementById("pr6").innerHTML="Il nome è obbligatorio";
        document.getElementById("pr6").style.display="block";
        return false;
    }


    else if(!nomeRegex.test(nome)) {
        document.getElementById("pr6").innerHTML="Il nome non è valido.<br>Il nome deve contenere solo lettere maiuscole e minuscole.";
        document.forms["modulo"]["nome"].style.border="2px solid #ee3124";
        document.forms["modulo"]["nome"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["nome"].style.outline="1px solid";
        document.forms["modulo"]["nome"].style.outlineColor="#ee3124";
        document.getElementById("pr6").style.display="block";
        return false;

    }
    else{

        document.forms["modulo"]["nome"].style.outline="initial";
        document.forms["modulo"]["nome"].style.border="";
        document.forms["modulo"]["nome"].style.boxShadow="none";
        document.getElementById("pr6").style.display="none";
        return true;
    }

}

function  confermaUsername(){
    var userConferma=document.forms["modulo"]["username2"].value;
    var user=document.forms["modulo"]["username"].value;

    if((userConferma == "") || (userConferma == "undefined")){
        document.forms["modulo"]["username2"].style.border="2px solid #ee3124";
        document.forms["modulo"]["username2"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["username2"].style.outline="1px solid";
        document.forms["modulo"]["username2"].style.outlineColor="#ee3124";
        document.getElementById("pr16").innerHTML="La password di conferma è obbligatoria";
        document.getElementById("pr16").style.display="block";
        return false;
    }

    else if(userConferma!=user) {
        document.getElementById("pr16").innerHTML="I due username non coincidono";
        document.forms["modulo"]["username2"].style.border="2px solid #ee3124";
        document.forms["modulo"]["username2"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["username2"].style.outline="1px solid";
        document.forms["modulo"]["username2"].style.outlineColor="#ee3124";
        document.getElementById("pr16").style.display="block";
        return false;

    }
    else{

        document.forms["modulo"]["username2"].style.outline="initial";
        document.forms["modulo"]["username2"].style.border="";
        document.forms["modulo"]["username2"].style.boxShadow="none";
        document.getElementById("pr16").style.display="none";
        return true;
    }

}


function  validateUsername(){
    var user=document.forms["modulo"]["username"].value;
    var userRegex=/^[A-Za-z0-9_-]{3,15}$/;
    if((user == "") || (user == "undefined")){
        document.forms["modulo"]["username"].style.border="2px solid #ee3124";
        document.forms["modulo"]["username"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["username"].style.outline="1px solid";
        document.forms["modulo"]["username"].style.outlineColor="#ee3124";
        document.getElementById("pr5").innerHTML="L'username è obbligatorio";
        document.getElementById("pr5").style.display="block";
        return false;
    }

    else if(!userRegex.test(user)) {
        document.getElementById("pr5").innerHTML="L'username non è valido.<br>L'username deve contenere solo caratteri alfanumerici più  <b>_</b> e <b>-</b> di lunghezza min 3 e max 15.";
        document.forms["modulo"]["username"].style.border="2px solid #ee3124";
        document.forms["modulo"]["username"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["username"].style.outline="1px solid";
        document.forms["modulo"]["username"].style.outlineColor="#ee3124";
        document.getElementById("pr5").style.display="block";
        return false;

    }
    else{

        document.forms["modulo"]["username"].style.outline="initial";
        document.forms["modulo"]["username"].style.border="";
        document.forms["modulo"]["username"].style.boxShadow="none";
        document.getElementById("pr5").style.display="none";
        return true;
    }

}

function validateDate(){
    var data=document.forms["modulo"]["dataNascita"].value;
    var dataNascita=new Date(data);
    var dataOggi=new Date();
    var year=dataOggi.getFullYear()-18;
    var dataNuova=new Date();
    dataNuova.setDate(dataOggi.getDate());
    dataNuova.setFullYear(year);
    dataNuova.setMonth(dataOggi.getMonth());
    console.log(dataNuova);
    if(dataNascita<=dataNuova){
        document.forms["modulo"]["dataNascita"].style.outline="initial";
        document.forms["modulo"]["dataNascita"].style.border="";
        document.forms["modulo"]["dataNascita"].style.boxShadow="none";
        document.getElementById("pdata").style.display="none";
        return true;
    }else{
        document.forms["modulo"]["dataNascita"].style.border="2px solid #ee3124";
        document.forms["modulo"]["dataNascita"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["dataNascita"].style.outline="1px solid";
        document.forms["modulo"]["dataNascita"].style.outlineColor="#ee3124";
        document.getElementById("pdata").innerHTML="La data di nascita è non è valida.<br>L'utente deve avere almeno 18 anni.";
        document.getElementById("pdata").style.display="block";
        return false;
    }


}


function  confermaPassword(){
    var pass1=document.forms["modulo"]["password1"].value;
    var pass=document.forms["modulo"]["password"].value;

    if((pass1 == "") || (pass1 == "undefined")){
        document.forms["modulo"]["password1"].style.border="2px solid #ee3124";
        document.forms["modulo"]["password1"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["password1"].style.outline="1px solid";
        document.forms["modulo"]["password1"].style.outlineColor="#ee3124";
        document.getElementById("pr4").innerHTML="La password di conferma è obbligatoria";
        document.getElementById("pr4").style.display="block";
        return false;
    }

    else if(pass1!=pass) {
        document.getElementById("pr4").innerHTML="Le due password non coincidono";
        document.forms["modulo"]["password1"].style.border="2px solid #ee3124";
        document.forms["modulo"]["password1"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["password1"].style.outline="1px solid";
        document.forms["modulo"]["password1"].style.outlineColor="#ee3124";
        document.getElementById("pr4").style.display="block";
        return false;

    }
    else{

        document.forms["modulo"]["password1"].style.outline="initial";
        document.forms["modulo"]["password1"].style.border="";
        document.forms["modulo"]["password1"].style.boxShadow="none";
        document.getElementById("pr4").style.display="none";
        return true;
    }

}




function  validatePassword(){
    var pass=document.forms["modulo"]["password"].value;
    var passRegex=/^[A-Za-z0-9]{8,20}$/;
    if((pass == "") || (pass == "undefined")){
        document.forms["modulo"]["password"].style.border="2px solid #ee3124";
        document.forms["modulo"]["password"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["password"].style.outline="1px solid";
        document.forms["modulo"]["password"].style.outlineColor="#ee3124";
        document.getElementById("pr3").innerHTML="La password è obbligatoria";
        document.getElementById("pr3").style.display="block";
        return false;
    }

    else if(!passRegex.test(pass)) {
        document.getElementById("pr3").innerHTML="La password non è valida.<br>La password deve contenere lettere minuscole,maiuscole,numeri e deve essere compresa tra 8 e 20 caratteri";
        document.forms["modulo"]["password"].style.border="2px solid #ee3124";
        document.forms["modulo"]["password"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["password"].style.outline="1px solid";
        document.forms["modulo"]["password"].style.outlineColor="#ee3124";
        document.getElementById("pr3").style.display="block";
        return false;

    }
    else{

        document.forms["modulo"]["password"].style.outline="initial";
        document.forms["modulo"]["password"].style.border="";
        document.forms["modulo"]["password"].style.boxShadow="none";
        document.getElementById("pr3").style.display="none";
        return true;
    }

}
/*validate pagamento*/
function checkIntestatario(){
    var inteCarta=document.getElementById("inteCarta").value;
    var inteCartaRegex=/^[A-Z][a-z]+\s[A-Za-zàèùòì' ]+$/;
    if((inteCarta == "") || (inteCarta == "undefined")){
        document.getElementById("inteCarta").style.border="2px solid #ee3124";
        document.getElementById("inteCarta").style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("inteCarta").style.outline="1px solid";
        document.getElementById("inteCarta").style.outlineColor="#ee3124";
        document.getElementById("pr1").innerHTML="Inserire Nome e Cognome";
        document.getElementById("pr1").style.display="block";
        return false;
    }

    else if(!inteCartaRegex.test(inteCarta)) {
        document.getElementById("pr1").innerHTML="Nome e/o Cognome  non  valido.<br>Il Nome e Cognome  devono iniziare con lettera maiuscola e contenere solo lettere minuscole.";
        document.getElementById("inteCarta").style.border="2px solid #ee3124";
        document.getElementById("inteCarta").style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.getElementById("inteCarta").style.outline="1px solid";
        document.getElementById("inteCarta").style.outlineColor="#ee3124";
        document.getElementById("pr1").style.display="block";
        return false;

    }
    else{

        document.getElementById("inteCarta").style.outline="initial";
        document.getElementById("inteCarta").style.border="";
        document.getElementById("inteCarta").style.boxShadow="none";
        document.getElementById("inteCarta").style.outline="1px solid";
        document.getElementById("inteCarta").style.outlineColor="#0a9a0d";
        document.getElementById("pr1").style.display="none";
        return true;
    }
}

function checkNumeroCarta(){

}




function checkDataScadenza(){

}




function  checkCvC(){



}





















/******************************************************************/







function  confermaEmail(){
    var email1=document.forms["modulo"]["email1"].value;
    var email=document.forms["modulo"]["email"].value;
    if((email1 == "") || (email1 == "undefined")){
        document.forms["modulo"]["email1"].style.border="2px solid #ee3124";
        document.forms["modulo"]["email1"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["email1"].style.outline="1px solid";
        document.forms["modulo"]["email1"].style.outlineColor="#ee3124";
        document.getElementById("pr2").innerHTML="L'indirizzo e-mail di conferma è obbligatorio";
        document.getElementById("pr2").style.display="block";
        return false;
    }

    else if(email1!=email) {

        document.getElementById("pr2").innerHTML="I due indirizzi email non coincidono";
        document.forms["modulo"]["email1"].style.border="2px solid #ee3124";
        document.forms["modulo"]["email1"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["email1"].style.outline="1px solid";
        document.forms["modulo"]["email1"].style.outlineColor="#ee3124";
        document.getElementById("pr2").style.display="block";
        return false;

    }
    else{

        document.forms["modulo"]["email1"].style.outline="initial";
        document.forms["modulo"]["email1"].style.border="";
        document.forms["modulo"]["email1"].style.boxShadow="none";
        document.getElementById("pr2").style.display="none";
        return true;
    }


}








function  validateEmail(){
    var email=document.forms["modulo"]["email"].value;
    var emailRegex=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;


    if((email == "") || (email == "undefined")){
        document.forms["modulo"]["email"].style.border="2px solid #ee3124";
        document.forms["modulo"]["email"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["email"].style.outline="1px solid";
        document.forms["modulo"]["email"].style.outlineColor="#ee3124";
        document.getElementById("pr1").innerHTML="L'indirizzo e-mail è obbligatorio";
        document.getElementById("pr1").style.display="block";
        return false;
    }
    else if(!emailRegex.test(email)) {
        document.getElementById("pr1").innerHTML="L'indirizzo email non è valido.<br>Immetti l'indirizzo e-mail nel formato nomeutente@dominio.it";
        document.forms["modulo"]["email"].style.border="2px solid #ee3124";
        document.forms["modulo"]["email"].style.boxShadow="0 0 8px 0 rgb(238, 49, 36,0.8)";
        document.forms["modulo"]["email"].style.outline="1px solid";
        document.forms["modulo"]["email"].style.outlineColor="#ee3124";
        document.getElementById("pr1").style.display="block";
        return false;

    }
    else{

        document.forms["modulo"]["email"].style.outline="initial";
        document.forms["modulo"]["email"].style.border="";
        document.forms["modulo"]["email"].style.boxShadow="none";
        document.getElementById("pr1").style.display="none";
        return true;
    }

}
/*******************Ajax per verificare se email già presente nel database************************/

function  checkEmailSignAjax(){
    var email=document.forms["modulo"]["email"].value;
    var xhr= new XMLHttpRequest();
    xhr.onreadystatechange= function () {

        if (this.readyState == 4 && this.status == 200) {
            if(this.responseText=="" || this.responseText==null){
                document.getElementById("pr100").style.display = "none";
                return true;
            }
            else {
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
    xhr.open("GET","/GameOverNew_war_exploded/RegistrazioneController/checkEmailSign?email="+email,true);
    xhr.send();

}

function  checkEmailAjax(){
    var email=document.forms["modulo"]["email"].value;
    var xhr= new XMLHttpRequest();
    xhr.onreadystatechange= function () {

        if (this.readyState == 4 && this.status == 200) {
            if(this.responseText=="" || this.responseText==null){
                document.getElementById("pr100").style.display = "none";
                return true;
            }
            else {
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
    xhr.open("GET","/GameOverNew_war_exploded/GestioneUtenteController/checkEmail?email="+email,true);
    xhr.send();

}
/**************************Ajax per verificare se il numero di telefono è già presente nel database***********************/
function  checkTelSignAjax(){
    var tel=document.forms["modulo"]["telefono"].value;
    var xhr= new XMLHttpRequest();
    xhr.onreadystatechange= function () {

        if (this.readyState == 4 && this.status == 200) {
            if(this.responseText=="" || this.responseText==null){
                document.getElementById("pr800").style.display = "none";
                return true;
            }
            else {
                document.forms["modulo"]["telefono"].style.border = "2px solid #ee3124";
                document.forms["modulo"]["telefono"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
                document.forms["modulo"]["telefono"].style.outline = "1px solid";
                document.forms["modulo"]["telefono"].style.outlineColor = "#ee3124";
                document.getElementById("pr800").style.display = "block";
                document.getElementById("pr800").innerHTML = this.responseText;
                return false;
            }
        }
    };
    xhr.open("GET","/GameOverNew_war_exploded/GestioneUtenteController/checkTelSign?numeroTel="+tel,true);
    xhr.send();

}

function  checkTelAjax(){
    var tel=document.forms["modulo"]["telefono"].value;
    var xhr= new XMLHttpRequest();

    xhr.onreadystatechange= function () {

        if (this.readyState == 4 && this.status == 200) {

            if(this.responseText=="" || this.responseText==null){
                document.getElementById("pr800").style.display = "none";
                return true;

            }
            else {
                document.forms["modulo"]["telefono"].style.border = "2px solid #ee3124";
                document.forms["modulo"]["telefono"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
                document.forms["modulo"]["telefono"].style.outline = "1px solid";
                document.forms["modulo"]["telefono"].style.outlineColor = "#ee3124";
                document.getElementById("pr800").style.display = "block";
                document.getElementById("pr800").innerHTML = this.responseText;
                return false;
            }
        }
    };
    xhr.open("GET","/GameOverNew_war_exploded/GestioneUtenteController/checkTel?numeroTel="+tel,true);
    xhr.send();


}
/**************************Ajax per verificare se il codice fiscale è già presente nel database***********************/
function  checkCfSignAjax(){
    var cf=document.forms["modulo"]["codiceFiscale"].value;
    var xhr= new XMLHttpRequest();
    xhr.onreadystatechange= function () {

        if (this.readyState == 4 && this.status == 200) {
            if(this.responseText=="" || this.responseText==null){
                document.getElementById("pr1000").style.display = "none";
                return true;
            }
            else {
                document.forms["modulo"]["codiceFiscale"].style.border = "2px solid #ee3124";
                document.forms["modulo"]["codiceFiscale"].style.boxShadow = "0 0 8px 0 rgb(238, 49, 36,0.8)";
                document.forms["modulo"]["codiceFiscale"].style.outline = "1px solid";
                document.forms["modulo"]["codiceFiscale"].style.outlineColor = "#ee3124";
                document.getElementById("pr1000").style.display = "block";
                document.getElementById("pr1000").innerHTML = this.responseText;
                return false;
            }
        }
    };
    xhr.open("GET","/GameOverNew_war_exploded/GestioneUtenteController/checkCFSign?cf="+cf,true);
    xhr.send();

}







/****************************w*********************************************************/
function validateForm(){
    if(validateCap() && validateCf() && validateCitta() && validatecittaNascita() && validateCivico()
        && validateCognome() && validateEmail() && validateNome() && validatePassword() && validateProvincia()
        && validateTel() && validateUsername() && validateVia() && validateDate() && confermaEmail() && confermaPassword()
        ){
        return true;
    }
    return false;
}

function validateFormAddress() {

    if( validateCitta() && validateVia()&& validateProvincia()&& validateCivico()&& validateCap()) {
        return true;
    }
    return false;


}


