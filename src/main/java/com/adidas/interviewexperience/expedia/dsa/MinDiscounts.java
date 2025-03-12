package com.adidas.interviewexperience.expedia.dsa;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * last Question Asked in Expedia contest ->
 *
 * given productPrices with discount tags applicable
 *
 * prices -> 100, d1, d2, empty <- note this discount and the other product occuring later is a different product with same prize
 * prices -> 200, d1, d2, d3
 * prices -> 100, d1, d2, d4    <- this discount price is not the same product
 *
 * for each discountTags there is a type of discount ->
 *      if discountType = 0 -> price change> to the value given
 *      if discountType = 1 -> prices gets discounted to the value
 *      if discountType = 2 -> prices get deducted to the value
 *
 *      we have to take the minimum of these about
 *
 *      for a price = 100
 *      (dis1, 0, 25), (dis2, 1, 10), (dis3, 1, 60)
 *
 *      price = 25 (since case 0, 100 -> 25)
 *
 *
 */
@Slf4j
public class MinDiscounts {
  public int minDiscounts(List<List<String>> prices, List<List<String>> discounts){
    int totalValue = 0;

    Map<String, List<String>> discountsMap = new HashMap<>();



    for(List<String> dis : discounts){
      String key = String.format("%s_%s", dis.get(0), dis.get(1));
      discountsMap.computeIfAbsent(key, k -> new ArrayList<>()).add(dis.get(2));
    }

    for(int cI = 0; cI< prices.size(); cI++){

      int currentPrice = Integer.parseInt(prices.get(cI).get(0)), minPrice = currentPrice;


      for(int col = 1; col < prices.get(cI).size(); col++){

        String currentDis = prices.get(cI).get(col);

        if("Empty".equalsIgnoreCase(currentDis)) continue;

        for(int type : new int[]{0, 1, 2}){

          String discountKey = String.format("%s_%s", currentDis, type);

          if(type == 0){
            for(String dis : discountsMap.getOrDefault(discountKey, new ArrayList<>())){
              minPrice = Math.min(minPrice, Integer.parseInt(dis));

            }

          }else if(type == 1){
            for(String dis : discountsMap.getOrDefault(discountKey, new ArrayList<>())){
              int adjustedPrice = (int) (currentPrice * (1 - Double.parseDouble(dis)/100.00));
              minPrice = Math.min(minPrice, adjustedPrice);

            }
          }else {
            for(String dis : discountsMap.getOrDefault(discountKey, new ArrayList<>())){
              int adjustedPrice = (currentPrice - Integer.parseInt(dis));
              minPrice = Math.min(minPrice, adjustedPrice);

            }
          }
        }


      }

      totalValue += minPrice;


    }

    return totalValue;
  }

  public int minDiscounts2(List<List<String>> prices, List<List<String>> discounts) {
    int totalValue = 0;

    Map<String, List<String>> discountsMap = new HashMap<>();

    // Map to store discount tags for each product
    List<Product> productList = new ArrayList<>();

    // Populate the product list with price and associated discount tags
    for (List<String> product : prices) {
      int price = Integer.parseInt(product.get(0));
      Set<String> tags = new HashSet<>();

      for (int i = 1; i < product.size(); i++) {
        if (!"EMPTY".equalsIgnoreCase(product.get(i))) {
          tags.add(product.get(i));
        }
      }
      productList.add(new Product(price, tags));
    }

    // Populate the discount map
    for (List<String> dis : discounts) {
      String key = String.format("%s_%s", dis.get(0), dis.get(1));
      discountsMap.computeIfAbsent(key, k -> new ArrayList<>()).add(dis.get(2));
    }

    // Process each product and find the minimum possible price
    for (Product product : productList) {
      int currentPrice = product.price;
      int minPrice = currentPrice;

      for (String tag : product.tags) {
        for (int type : new int[]{0, 1, 2}) {
          String discountKey = String.format("%s_%s", tag, type);

          if (discountsMap.containsKey(discountKey)) {
            for (String discountValue : discountsMap.get(discountKey)) {
              int discountedPrice = currentPrice;

              if (type == 0) {
                discountedPrice = Integer.parseInt(discountValue);
              } else if (type == 1) {
                discountedPrice = (int) (currentPrice * (1 - Double.parseDouble(discountValue) / 100.0));
              } else if (type == 2) {
                discountedPrice = currentPrice - Integer.parseInt(discountValue);
              }

              minPrice = Math.min(minPrice, discountedPrice);
            }
          }
        }
      }

      totalValue += minPrice;
    }

    return totalValue;
  }

  // Helper class to store product price and tags
  static class Product {
    int price;
    Set<String> tags;

    Product(int price, Set<String> tags) {
      this.price = price;
      this.tags = tags;
    }
  }




    public static void main(String[] args){
    List<List<String>> products = Arrays.asList(
        Arrays.asList("10", "sale", "jan-sale"),
        Arrays.asList("200", "sale", "EMPTY")
    );

    List<List<String>> discounts = Arrays.asList(
        Arrays.asList("sale", "0", "10"),
        Arrays.asList("jan-sale", "1", "10")
    );

    log.info("TotalDiscount : {}", new MinDiscounts().minDiscounts(products, discounts));

    List<List<String>> products2 = Arrays.asList(
        Arrays.asList("100", "dcoupon1"),
        Arrays.asList("50", "dcoupon1"),
        Arrays.asList("30", "dcoupon1"),
        Arrays.asList("100", "dcoupon2"),
        Arrays.asList("50", "dcoupon2"),
        Arrays.asList("30", "dcoupon2")
    );

    List<List<String>> discounts2 = Arrays.asList(
        Arrays.asList("dcoupon1", "0", "60"),
        Arrays.asList("dcoupon1", "1", "30"),
        Arrays.asList("dcoupon1", "1", "20"),
        Arrays.asList("dcoupon2", "2", "20"),
        Arrays.asList("dcoupon2", "1", "85"),
        Arrays.asList("dcoupon2", "0", "15")
    );

    log.info("TotalDiscount : {}", new MinDiscounts().minDiscounts(products2, discounts2));

  }
}
