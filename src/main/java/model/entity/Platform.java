package model.entity;

import java.util.ArrayList;

public class Platform {

    private String platformName;
    private int id;
    private ArrayList<Prodotto> prodottos =new ArrayList<>();



    public Platform() {
        super();
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Prodotto> getProducts() {
        return prodottos;
    }

    public void setProducts(ArrayList<Prodotto> prodottos) {
        this.prodottos = prodottos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Platform platform = (Platform) o;
        return getId() == platform.getId();
    }

}
