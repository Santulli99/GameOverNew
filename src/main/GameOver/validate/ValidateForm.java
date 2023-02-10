package validate;

import model.dao.account.SqlAccountDao;
import model.entity.Account;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateForm {


    public static boolean confermaPassword(String pasword, String password2) {

        if (pasword.equals(password2)) {
            return true;
        }
        return false;

    }

    public static boolean confermaEmail(String email, String email2) {

        if (email.equals(email2)) {
            return true;
        }
        return false;

    }


    public static boolean validateDateNascita(LocalDate date) {

        LocalDate dataOggi = LocalDate.now();

        LocalDate dataOrdine = date;
        LocalDate controllo = date.plusDays(6570);

        if (controllo.isBefore(dataOggi)) {
            return true;
        }
        return false;
    }


    public static boolean validateEmail(String email) {

        String regex = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches())
            return true;

        return false;
    }

    public static boolean searchEmail(String email) {
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

    public static boolean validatePassword(String password) {

        String regex = "^[A-Za-z0-9]{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        if (matcher.matches())
            return true;

        return false;
    }


    public static boolean validateNome(String nome) {

        String regex = "^[A-Za-z ]{3,30}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nome);

        if (matcher.matches())
            return true;

        return false;
    }


    public static boolean validateCognome(String cognome) {

        String regex = "^[A-Za-zàèùòì' ]{3,30}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cognome);

        if (matcher.matches())
            return true;

        return false;
    }


    public static boolean validateUsername(String username) {

        String regex = "^[A-Za-z0-9_-]{3,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);

        if (matcher.matches())
            return true;

        return false;
    }















}
