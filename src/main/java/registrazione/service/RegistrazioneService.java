package registrazione.service;

import model.entity.Account;
import model.entity.Address;
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

     /**
      * firma del metodo che implementa la registrazione dei dati anagrafici relativi all'account
      *
      * @param request oggetto della servlet, che contiene i parametri inviati e la sessione corrente
      * @param account oggetto della classe Account utilizzato per settare i dati anagrafici
      * @return un oggetto della classe  DataClient contenente le info sui dati anagrafici dell'account
      */
     DataClient registrazioneDataClient(HttpServletRequest request,Account account);

     /**
      * firma del metodo che implementa la registrazione dei dati relativi all'indirizzo
      *
      * @param request oggetto della servlet, che contiene i parametri inviati e la sessione corrente
      * @param account Account utilizzato per settare i dati relativi all'indirizzo
      * @return un oggetto della classe Address contenente le info sui dati dell'indirizzo dell'account
      */
     Address registrazioneAddressClient(HttpServletRequest request,Account account);

}
