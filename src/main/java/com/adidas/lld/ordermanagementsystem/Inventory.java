package com.adidas.lld.ordermanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inventory {
  List<ProductCategory> productCategoryList;

  Inventory() {
    productCategoryList = new ArrayList<>();
  }

  public void addCategory(int categoryId, String name, int price) {
    ProductCategory productCategory = new ProductCategory();
    productCategory.price = price;
    productCategory.categoryName = name;
    productCategory.productCategoryId = categoryId;
    productCategoryList.add(productCategory);

  }

  //add product to the particular category
  public void addProduct(Product product, int productCategoryId) {

    //take out the respective productCategory Object
    ProductCategory categoryObject = null;
    for (ProductCategory category : productCategoryList) {
      if (category.productCategoryId == productCategoryId) {
        categoryObject = category;
      }
    }

    if (categoryObject != null) {
      categoryObject.addProduct(product);
    }
  }

  //remove product from the category
  public void removeItems(Map<Integer, Integer> productCategoryAndCountMap) {

    for (Map.Entry<Integer, Integer> entry : productCategoryAndCountMap.entrySet()) {
      ProductCategory category = getProductCategoryFromId(entry.getKey());
      category.removeProduct(entry.getValue());
    }

  }

  private ProductCategory getProductCategoryFromId(int productCategoryId){

    for(ProductCategory productCategory : productCategoryList){

      if(productCategory.productCategoryId == productCategoryId){
        return productCategory;
      }
    }

    return null;
  }


}



