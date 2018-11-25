package org.ak.billing.constants;

public enum ApplicationConstants {
    CART_QUANTITY(2),
    SHOW_LOGS(true),
    INVENTORY_SHORTAGE_EX_MSG("**Cannot add from inventory, product is in shortage! **Please restock inventory**");

    private final Object appCons;

    ApplicationConstants(Object appCons){
        this.appCons = appCons;
    }

    public final Object getApplicationConstant(){
        return appCons;
    }
}
