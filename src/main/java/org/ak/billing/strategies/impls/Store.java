package org.ak.billing.strategies.impls;

import org.ak.billing.beans.Product;
import org.ak.billing.beans.Products;
import org.ak.billing.constants.ProductTypes;
import org.ak.billing.strategies.StoreDBStrategy;

import java.util.UUID;

//Singleton & Immutable
public final class Store implements StoreDBStrategy {

    private final ThreadLocal<Products> productInventory = new ThreadLocal<>();

    private Store() {
        productInventory.set(initialize());
    }

    private static final class StoreLoader {
        private static final Store singleton = new Store();
    }

    public static final Store getStore() {
        return StoreLoader.singleton;
    }

    @Override
    public final ThreadLocal<Products> getProductInventory() {
        return productInventory;
    }

    private final Products initialize() {
        Products inventory = new Products();

        //Add Product 1
        UUID pid = UUID.randomUUID();
        Product p = new Product(pid, "Vanheusen LM31 M", 20, ProductTypes.CLOTHING, 19.99d);
        inventory.getProducts().put(pid, p);

        //Add Product 2
        pid = UUID.randomUUID();
        p = new Product(pid, "Gillete Vector Blue", 62, ProductTypes.COSMETICS, 4.99d);
        inventory.getProducts().put(pid, p);

        //Add Product 3
        pid = UUID.randomUUID();
        p = new Product(pid, "Transcend 64GB P320", 120, ProductTypes.ELECTRONICS, 14.99d);
        inventory.getProducts().put(pid, p);

        //Add Product 4
        pid = UUID.randomUUID();
        p = new Product(pid, "Tomato", 20, ProductTypes.GROCERY, 0.99d);
        inventory.getProducts().put(pid, p);

        //Add Product 5
        pid = UUID.randomUUID();
        p = new Product(pid, "Luxor Black Royal Smooth", 45, ProductTypes.STATIONERY, 1.99d);
        inventory.getProducts().put(pid, p);

        return inventory;
    }
}
