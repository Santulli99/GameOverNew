package Application_Logic.gestioneAcquisti;

import Application_Logic.entity.Order;

import java.util.Comparator;

public class OrdinareCliente implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {

        if(o1.getAccount().getUsername().compareTo(o2.getAccount().getUsername())>0){
            return 1;
        }
        if(o1.getAccount().getUsername().compareTo(o2.getAccount().getUsername())<0){
            return -1;
        }

        return 0;
    }
}
