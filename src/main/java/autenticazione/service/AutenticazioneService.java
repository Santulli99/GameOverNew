package autenticazione.service;

import model.entity.Account;

import javax.servlet.http.HttpSession;

public interface AutenticazioneService {
    Account login(String email , String password);
    boolean verificaAdmin(Account account);
    void logout(HttpSession sessione);

}
