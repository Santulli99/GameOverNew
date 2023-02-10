package model.dao.product;

import model.entity.Prodotto;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ProductDao <E extends  Exception>{

    //cercare in base alle proprietà

    ArrayList<Prodotto> searchProductsByCategoryAndPlatform(String category, String platform) throws E;


    

    Prodotto searchProduct(int id) throws E;

    ArrayList<Prodotto> searchAllProducts() throws E;

    ArrayList<Prodotto> searchProductsvetrina(String piattaforma) throws E;


    boolean createProduct(Prodotto prodotto) throws E;
    boolean updateProduct(Prodotto prodotto) throws E;
    boolean deleteProduct(int id) throws E;

    boolean updateProductValuation(Prodotto prodotto) throws E;



}
