package model.entity;

import java.util.ArrayList;

public class Category {

    private String categoryName,description;
    private int id;
    private ArrayList<Prodotto> prodottos =new ArrayList<>();

    public Category() {
        super();
    }


    public ArrayList<Prodotto> getProducts() {
        return prodottos;
    }

    public void setProducts(ArrayList<Prodotto> prodottos) {
        this.prodottos = prodottos;
    }



    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id;
    }


}
