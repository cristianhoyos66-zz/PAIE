package co.com.poli.implementation;

import co.com.poli.contract.ProductServices;
import co.com.poli.entities.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductServices {

    private static List<Product> productList;

    static {
        productList = new ArrayList<Product>() {
            {
                add(new Product("10", "Gasiosa", 1500d));
                add(new Product("20", "Malta", 1500d));
                add(new Product("30", "mr. té", 100d));
                add(new Product("40", "la water", 2500d));
            }
        };
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Product getSearchProduct(String code) {
        Product product = null;
        for (Product pr : productList) {
            if (pr.getCode().equals(code)) {
                //product = pr;
                return pr;
            }
        }
        return product;
    }
}
