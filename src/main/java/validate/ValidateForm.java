package validate;

import model.dao.account.SqlAccountDao;
import model.dao.dataClient.SqlDataClientDao;
import model.entity.Account;
import model.entity.DataClient;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateForm {



    public static boolean confermaPassword(String pasword, String password2){

        if(pasword.equals(password2)){
            return true;
        }
        return false;

    }

    public static boolean confermaEmail(String email, String email2){

        if(email.equals(email2)){
            return true;
        }
        return false;

    }




    public static  boolean validateDateNascita(LocalDate date) {

        LocalDate dataOggi = LocalDate.now();

        LocalDate dataOrdine = date;
        LocalDate controllo = date.plusDays(6570);

        if(controllo.isBefore(dataOggi)){
            return true;
        }
        return false;
    }



    public static boolean validateEmail(String email){

        String regex="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches())
            return true;

        return false;
    }

    public static boolean searchEmail(String email){
        SqlAccountDao sqlAccountDao= new SqlAccountDao();
        Account account=new Account();
        try {
            account=sqlAccountDao.searchAccountEmail(email);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (account==null)
            return true;

        return false;
    }

    public static boolean validatePassword(String password){

        String regex="^[A-Za-z0-9]{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        if (matcher.matches())
            return true;

        return false;
    }



    public static boolean validateNome(String nome){

        String regex="^[A-Za-z ]{3,30}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nome);

        if (matcher.matches())
            return true;

        return false;
    }


    public static boolean validateCognome(String cognome){

        String regex="^[A-Za-zàèùòì' ]{3,30}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cognome);

        if (matcher.matches())
            return true;

        return false;
    }


    public static boolean validateResidenza(String residenza){

        String regex="^[A-Za-z ]{3,30}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(residenza);

        if (matcher.matches())
            return true;

        return false;
    }


    public static boolean validateVia(String via){

        String regex="^[A-Za-z ]{3,30}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(via);

        if (matcher.matches())
            return true;

        return false;
    }


    public static boolean validateluogo_nascita(String luogo_nascita){

        String regex="^[A-Za-z ]{3,30}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(luogo_nascita);

        if (matcher.matches())
            return true;

        return false;
    }


    public static boolean validateUsername(String username){

        String regex="^[A-Za-z0-9_-]{3,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);

        if (matcher.matches())
            return true;

        return false;
    }


    public static boolean validateTelefono(String telefono){

        String regex="^((00|\\+)[\\. ]??)??3\\d{2}[\\. ]??\\d{6,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telefono);
        if (matcher.matches())
            return true;

        return false;
    }

    public static boolean searchTelefono(String telefono){
        SqlDataClientDao sqlDataClientDao= new SqlDataClientDao();
        DataClient dataClient= new DataClient();
        try {
            dataClient=sqlDataClientDao.searchDataClientWithTel(telefono);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (dataClient==null)
            return true;

        return false;
    }


    public static boolean validateCf(String cf){

        String regex="^[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cf);
        if (matcher.matches())
            return true;

        return false;
    }


    public static boolean searchCf(String cf){
        SqlDataClientDao sqlDataClientDao= new SqlDataClientDao();
        DataClient dataClient= new DataClient();
        try {
            dataClient=sqlDataClientDao.searchDataClientWithCf(cf);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (dataClient==null)
            return true;

        return false;
    }

    public static boolean validateProvincia(String provincia){

        String regex="^[A-Za-z ]{3,30}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(provincia);

        if (matcher.matches())
            return true;

        return false;
    }



    public static boolean validateCivico(int civico){

        if (civico<=0 || civico>=500)
            return false;

        return true;
    }



    public static boolean validateCap(String cap){

        String regex="^\\d{5}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cap);

        if (matcher.matches())
            return true;

        return false;
    }





}
