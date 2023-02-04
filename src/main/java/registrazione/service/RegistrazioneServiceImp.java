package registrazione.service;

import model.dao.account.SqlAccountDao;
import model.dao.address.SqlAddressDao;
import model.dao.dataClient.SqlDataClientDao;
import model.entity.Account;
import model.entity.Address;
import model.entity.DataClient;
import validate.ValidateForm;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.LocalDate;

public class RegistrazioneServiceImp implements RegistrazioneService {

    private SqlAccountDao accountDao=new SqlAccountDao();
    private Account account;
    private SqlDataClientDao sqlDataClientDao=new SqlDataClientDao();
    private DataClient dataClient;
    private SqlAddressDao sqlAddressDao=new SqlAddressDao();
    private Address address;


    @Override
    public Account registrazioneAccount(HttpServletRequest request) {
        account=new Account();

        String email1=request.getParameter("email");
        String emailConferma=request.getParameter("email1");
        boolean validato1= ValidateForm.validateEmail(email1);
        boolean validato1_1=ValidateForm.searchEmail(email1);
        if(validato1 && validato1_1)
            request.setAttribute("email",email1);
            boolean validato2=ValidateForm.confermaEmail(email1,emailConferma);
        if(validato2)
            request.setAttribute("email1",emailConferma);
            String password1=request.getParameter("password");
            String passwordConferma=request.getParameter("password1");
            boolean validato3=ValidateForm.validatePassword(password1);
        if(validato3)
            request.setAttribute("password",password1);
            boolean validato4=ValidateForm.confermaPassword(passwordConferma,password1);
        if(validato4)
            request.setAttribute("password1",passwordConferma);

        String usernam=request.getParameter("username");
        boolean validato5=ValidateForm.validateUsername(usernam);
        if(validato5)
            request.setAttribute("username",usernam);

         if(validato1 && validato1_1 && validato2 && validato3 && validato4 && validato5){
             account.setEmail(email1);
             account.setPassword(password1);
             account.setUsername(usernam);
             account.setAdmin(false);
             System.out.println(account.getEmail()+account.getPassword()+account.getUsername());
             try {
                 if(accountDao.createAccount(account)){
                     return account;
                 }

             } catch (SQLException throwables) {
                 throwables.printStackTrace();
             }
         }

         return null;

    }


    @Override
    public DataClient registrazioneDataClient(HttpServletRequest request,Account account) {
        if(account==null)
            return null;
        String nome=request.getParameter("nome");
        boolean validato6=ValidateForm.validateNome(nome);
        if(validato6)
            request.setAttribute("nome",nome);

        String cognome=request.getParameter("cognome");
        boolean validato7=ValidateForm.validateCognome(cognome);
        if(validato7)
            request.setAttribute("cognome",cognome);

        String telefono=request.getParameter("telefono");
        boolean validato8=ValidateForm.validateTelefono(telefono);
        boolean validato8_1=ValidateForm.searchTelefono(telefono);
        if(validato8 && validato8_1)
            request.setAttribute("telefono",telefono);

        String cittaNascita=request.getParameter("cittaNascita");
        boolean validato9=ValidateForm.validateluogo_nascita(cittaNascita);
        if(validato9)
            request.setAttribute("cittaNascita",cittaNascita);

        LocalDate dataNascita= LocalDate.parse(request.getParameter("dataNascita"));
        boolean validato10=ValidateForm.validateDateNascita(dataNascita);
        if(validato10)
            request.setAttribute("dataNascita",dataNascita);

        String cf=request.getParameter("codiceFiscale");
        boolean validato11=ValidateForm.validateCf(cf);
        boolean validato11_1=ValidateForm.searchCf(cf);
        if(validato11 && validato11_1)
            request.setAttribute("codiceFiscale",cf);

        if(validato6 && validato7 && validato7 && validato8 && validato8_1 &&
                validato9 && validato10 && validato11 &&validato11_1) {
            dataClient=new DataClient();
            dataClient.setAccount(account);
            dataClient.setFirstName(nome);
            dataClient.setLastName(cognome);
            dataClient.setCell(telefono);
            dataClient.setCity(cittaNascita);
            dataClient.setDate(dataNascita.plusDays(1));
            dataClient.setCf(cf);
            try {
                sqlDataClientDao.createDataClient(dataClient);
                return  dataClient;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Address registrazioneAddressClient(HttpServletRequest request,Account account) {
        if(account==null)
            return null;
        String citta=request.getParameter("citta");
        boolean validato12=ValidateForm.validateResidenza(citta);
        if(validato12)
            request.setAttribute("citta",citta);

        String provincia=request.getParameter("provincia");
        boolean validato13=ValidateForm.validateProvincia(provincia);
        if(validato13)
            request.setAttribute("provincia",provincia);

        String via=request.getParameter("via");
        boolean validato14=ValidateForm.validateVia(via);
        if(validato14)
            request.setAttribute("via",via);

        int civico= Integer.parseInt(request.getParameter("civico"));
        boolean validato15=ValidateForm.validateCivico(civico);
        if(validato15)
            request.setAttribute("civico",civico);

        int cap= Integer.parseInt(request.getParameter("cap"));
        boolean validato16=ValidateForm.validateCap(String.valueOf(cap));
        if(validato16)
            request.setAttribute("cap",cap);

        if(validato12 && validato13 && validato14 && validato15 && validato16) {
            address=new Address();
            address.setAccount(account);
            address.setCity(citta);
            address.setProvince(provincia);
            address.setStreet(via);
            address.setStreetNumber(civico);
            address.setPostalCode(cap);
            try {
                sqlAddressDao.createAddress(address);
                return address;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
