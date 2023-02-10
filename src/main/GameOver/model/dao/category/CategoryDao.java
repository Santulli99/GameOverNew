package model.dao.category;

import model.entity.Category;

import java.util.ArrayList;

public interface CategoryDao <E extends  Exception> {

    Category searchCategory(int id) throws E;
    ArrayList<Category> searchAllCategory() throws E;
    boolean  createCategory(Category category)throws E;
    boolean  updateCategory(Category category) throws E;

    //attenzione i giochi già presenti con quella categoria devono essere eliminati?
    boolean  deleteCategory(Category category) throws E;

    Category searchCategoryWithProducts(int id) throws E;

    //query con hashMap
    ArrayList<Category> searchAllCategoryWithProducts() throws E;


}
