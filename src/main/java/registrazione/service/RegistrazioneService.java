package registrazione.service;

import model.entity.Account;
import model.entity.Address;
import model.entity.DataClient;

import javax.servlet.http.HttpServletRequest;

public interface RegistrazioneService {


     Account registrazioneAccount(HttpServletRequest request);
     DataClient registrazioneDataClient(HttpServletRequest request,Account account);
     Address registrazioneAddressClient(HttpServletRequest request,Account account);

}
