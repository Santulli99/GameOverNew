package Application_Logic;

import Storage.account.SqlAccountDao;
import Application_Logic.entity.Account;

import javax.servlet.http.Part;
import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateForm {

    public ValidateForm() {
    }

    public boolean confermaPassword(String pasword, String password2) {

        if (pasword.equals(password2)) {
            return true;
        }
        return false;

    }

    public boolean confermaEmail(String email, String email2) {

        if (email.equals(email2)) {
            return true;
        }
        return false;

    }


    public  boolean validateDateNascita(LocalDate date) {

        LocalDate dataOggi = LocalDate.now();

        LocalDate dataOrdine = date;
        LocalDate controllo = date.plusDays(6570);

        if (controllo.isBefore(dataOggi)) {
            return true;
        }
        return false;
    }


    public  boolean validateEmail(String email) {

        String regex = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches())
            return true;

        return false;
    }

    public  boolean searchEmail(String email) {
        SqlAccountDao sqlAccountDao = new SqlAccountDao();
        Account account = new Account();
        try {
            account = sqlAccountDao.searchAccountEmail(email);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (account == null)
            return true;

        return false;
    }

    public  boolean validatePassword(String password) {

        String regex = "^[A-Za-z0-9]{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        if (matcher.matches())
            return true;

        return false;
    }


    public  boolean validateNome(String nome) {

        String regex = "^[A-Za-z ]{3,30}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nome);

        if (matcher.matches())
            return true;

        return false;
    }


    public boolean validateCognome(String cognome) {

        String regex = "^[A-Za-z??????????' ]{3,30}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cognome);

        if (matcher.matches())
            return true;

        return false;
    }


    public boolean validateUsername(String username) {

        String regex = "^[A-Za-z0-9_-]{3,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);

        if (matcher.matches())
            return true;

        return false;
    }

    public  boolean validateNomeProdotto(String nome) {

        String regex = "^.{3,50}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nome);

        if (matcher.matches())
            return true;

        return false;
    }

    public boolean validatePrezzoProdotto(String prezzo) {
        String regex = "^\\d+(\\.\\d{2})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(prezzo);

        if (matcher.matches())
            return true;

        return false;
    }

    public boolean validateDescrizioneProdotto(String descrizione) {
        String regex = "^.{10,5000}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(descrizione);

        if (matcher.matches())
            return true;

        return false;
    }
    public boolean validateSizeCoverProdotto(File file){
        long fileSizeInBytes = file.length();
        long fileSizeInMegabytes = fileSizeInBytes / (1024 * 1024);
        return fileSizeInMegabytes > 1;
    }

    public boolean validateCoverProdotto(Part file){
        long fileSize = file.getSize();
        long maxFileSize = 1 * 1024 * 1024; // 1 MB in byte
        if (fileSize > maxFileSize) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validateDataUscitaProdotto(LocalDate date) {

        LocalDate dataOggi = LocalDate.now();
        if (date.isBefore(dataOggi)) {
            return true;
        }
        return false;
    }

    public boolean validateTitoloReview(String titolo){

        return titolo.length()>0 && titolo.length()<101 ;
    }
    public boolean validateCommentoReview(String commento){

        return commento.length()>0 && commento.length()<501 ;
    }

}
