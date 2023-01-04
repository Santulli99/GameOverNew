package model.dao.dataClient;

import model.entity.Account;
import model.entity.DataClient;

import java.util.ArrayList;

public interface DataClientDao <E extends  Exception> {

    //non ha senso perchè come facciamo a prenderci l'id ? se avessimo messo il cf allora lo prendevamo da form
    //il lato server non  conosce gli  id incrementati
    DataClient searchDataClient(int id) throws E;
    DataClient searchDataClientWithTel(String num) throws E;
    DataClient searchDataClientWithCf(String cf) throws E;


    ArrayList<DataClient> searchAllDataClient()throws E;
    boolean createDataClient(DataClient dataClient) throws E;
    boolean updateDataClient(DataClient dataClient,Account account) throws E;
    boolean deleteDataClient(DataClient dataClient) throws E;



    //dammi i dati del cliente tramite il suo codice id


    DataClient searchDataClientWithClient(int id_Account) throws E;






}
