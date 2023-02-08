package registrazione.service;

import model.dao.account.SqlAccountDao;
import model.entity.Account;
import validate.ValidateForm;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * implementa la classe che esplicita i metodi definiti nell'interfaccia di Registrazione
 *
 * @author Gerardo Esposito
 */

public class RegistrazioneServiceImp implements RegistrazioneService {

    private SqlAccountDao accountDao = new SqlAccountDao();
    private Account account;

    /**
     * Implementa la funzionalità che implementa la registrazione di un account
     *
     * @param request oggetto della servlet, che contiene i parametri inviati e la sessione corrente
     * @return oggetto della classe Account su cui sono stati settati i parametri estratti dalla request
     */

    @Override
    public Account registrazioneAccount(HttpServletRequest request) {
        account = new Account();

        String email1 = request.getParameter("email");
        String emailConferma = request.getParameter("email1");
        boolean validato1 = ValidateForm.validateEmail(email1);
        boolean validato1_1 = ValidateForm.searchEmail(email1);
        if (validato1 && validato1_1)
            request.setAttribute("email", email1);
        boolean validato2 = ValidateForm.confermaEmail(email1, emailConferma);
        if (validato2)
            request.setAttribute("email1", emailConferma);
        String password1 = request.getParameter("password");
        String passwordConferma = request.getParameter("password1");
        boolean validato3 = ValidateForm.validatePassword(password1);
        if (validato3)
            request.setAttribute("password", password1);
        boolean validato4 = ValidateForm.confermaPassword(passwordConferma, password1);
        if (validato4)
            request.setAttribute("password1", passwordConferma);

        String usernam = request.getParameter("username");
        boolean validato5 = ValidateForm.validateUsername(usernam);
        if (validato5)
            request.setAttribute("username", usernam);

        String nome = request.getParameter("nome");
        boolean validato6 = ValidateForm.validateNome(nome);
        if (validato6)
            request.setAttribute("nome", nome);

        String cognome = request.getParameter("cognome");
        boolean validato7 = ValidateForm.validateCognome(cognome);
        if (validato7)
            request.setAttribute("cognome", cognome);

        LocalDate dataNascita = LocalDate.parse(request.getParameter("dataNascita"));
        boolean validato8 = ValidateForm.validateDateNascita(dataNascita);
        if (validato8)
            request.setAttribute("dataNascita", dataNascita);


        if (validato1 && validato1_1 && validato2 && validato3 && validato4 && validato5 && validato6 && validato7 && validato8) {
            account.setEmail(email1);
            account.setPassword(password1);
            account.setUsername(usernam);
            account.setVenditore(false);
            account.setFirstName(nome);
            account.setLastName(cognome);
            account.setDate(dataNascita.plusDays(1));
            try {
                if (accountDao.createAccount(account)) {
                    return account;
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;

    }


}
