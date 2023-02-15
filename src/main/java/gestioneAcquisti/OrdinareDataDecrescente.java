package gestioneAcquisti;

import model.entity.Order;

import java.util.Comparator;

public class OrdinareDataDecrescente  implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        if(o1.getDate().isBefore(o2.getDate()))
            return 1;

        if(o1.getDate().isAfter(o2.getDate()))
            return -1;

        return 0;
    }
}
