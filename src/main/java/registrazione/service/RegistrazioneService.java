package registrazione.service;

import model.entity.Account;
import model.entity.DataClient;

import javax.servlet.http.HttpServletRequest;

/**
 * interfaccia per i metodi del sottosistema Registrazione
 *
 * @author Gerardo Esposito
 */

public interface RegistrazioneService {

     /**
      * firma del metodo che implementa la registrazione di un account
      *
      * @param request oggetto della servlet, che contiene i parametri inviati e la sessione corrente
      * @return oggetto della classe Account su cui sono stati settati i parametri estratti dalla request
      */
     Account registrazioneAccount(HttpServletRequest request);

}
