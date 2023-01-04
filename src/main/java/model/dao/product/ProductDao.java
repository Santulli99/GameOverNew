package model.dao.product;

import model.entity.Product;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ProductDao <E extends  Exception>{

    //cercare in base alle proprietà

    ArrayList<Product> searchProductsByName(String nome) throws E;
    ArrayList<Product> searchProductsByPrice(double price_MAX) throws E;
    ArrayList<Product> searchProductsByCategory(String category) throws E;
    ArrayList<Product> searchProductsByCategoryAndPlatform(String category,int idPlatform) throws E;
    ArrayList<Product> searchProductsByDate(LocalDate date) throws E;

    

    Product searchProduct(int id) throws E;

    ArrayList<Product> searchAllProducts() throws E;

    ArrayList<Product> searchProductsvetrina(int idpiattaforma) throws E;


    boolean createProduct(Product product) throws E;
    boolean updateProduct(Product product) throws E;
    boolean deleteProduct(int id) throws E;


    Product searchProductWithCategory(int id) throws E;
    Product searchProductWithPlatforms(int id) throws E;
    Product searchProductWithPlatformsAndCategory(int id) throws E;
    Product searchProductWithOrders(int id) throws E;
    ArrayList<Product> searchProductsByCategoryAndPlatform1() throws E;

    //hash MAP
    ArrayList<Product> searchProductWithCategory() throws E;
    ArrayList<Product> searchProductWithPlatform(int idpiattaforma) throws E;
    ArrayList<Product> searchProductWithOrders() throws E;

}
