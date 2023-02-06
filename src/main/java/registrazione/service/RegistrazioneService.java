package registrazione.service;

import model.entity.Account;
import model.entity.Address;
import model.entity.DataClient;

import javax.servlet.http.HttpServletRequest;
/**
 * interfaccia per i metodi del sottosistema Registrazione
 *
 * @author Gerado esposito
 */
public interface RegistrazioneService {

     /**
      * firma del metodo che implementa la registrazione di un account
      *
      * @param request HttpServletRequest che estrae i parametri dalla richiesta
      * @return un Account
      */
     Account registrazioneAccount(HttpServletRequest request);

     /**
      * firma del metodo che implementa la registrazione dei dati anagrafici relativi all'account
      *
      * @param request HttpServletRequest che estrae i parametri dalla richiesta
      * @param account Account utilizzato per settare i dati anagrafici
      * @return un DataClient
      */
     DataClient registrazioneDataClient(HttpServletRequest request,Account account);

     /**
      * firma del metodo che implementa la registrazione dei dati anagrafici
      *
      * @param request HttpServletRequest che estrae i parametri dalla richiesta
      * @param account Account utilizzato per settare i dati relativi all'indirizzo
      * @return un Address
      */
     Address registrazioneAddressClient(HttpServletRequest request,Account account);

}
