package model.dao.product;

import model.entity.Prodotto;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ProductDao <E extends  Exception>{

    //cercare in base alle proprietà

    ArrayList<Prodotto> searchProductsByName(String nome) throws E;
    ArrayList<Prodotto> searchProductsByPrice(double price_MAX) throws E;
    ArrayList<Prodotto> searchProductsByCategory(String category) throws E;
    ArrayList<Prodotto> searchProductsByCategoryAndPlatform(String category, int idPlatform) throws E;
    ArrayList<Prodotto> searchProductsByDate(LocalDate date) throws E;

    

    Prodotto searchProduct(int id) throws E;

    ArrayList<Prodotto> searchAllProducts() throws E;

    ArrayList<Prodotto> searchProductsvetrina(int idpiattaforma) throws E;


    boolean createProduct(Prodotto prodotto) throws E;
    boolean updateProduct(Prodotto prodotto) throws E;
    boolean deleteProduct(int id) throws E;


    Prodotto searchProductWithCategory(int id) throws E;
    Prodotto searchProductWithPlatforms(int id) throws E;
    Prodotto searchProductWithPlatformsAndCategory(int id) throws E;
    Prodotto searchProductWithOrders(int id) throws E;
    ArrayList<Prodotto> searchProductsByCategoryAndPlatform1() throws E;

    //hash MAP
    ArrayList<Prodotto> searchProductWithCategory() throws E;
    ArrayList<Prodotto> searchProductWithPlatform(int idpiattaforma) throws E;
    ArrayList<Prodotto> searchProductWithOrders() throws E;

}
