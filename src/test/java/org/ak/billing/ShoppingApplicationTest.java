package org.ak.billing;

import org.ak.billing.beans.UserDetails;
import org.ak.billing.constants.UserTypes;
import org.ak.billing.daos.impls.MyStoreDao;
import org.ak.billing.exceptions.InventoryShortageException;
import org.ak.billing.services.InvoiceService;
import org.ak.billing.services.StoreDBService;
import org.ak.billing.services.impls.MyCartService;
import org.ak.billing.services.impls.MyInvoiceService;
import org.ak.billing.services.impls.MyStoreDBService;
import org.ak.billing.strategies.impls.MyCartLoadingStrategy;
import org.ak.billing.strategies.impls.MyInvoiceGenerator;
import org.ak.billing.strategies.impls.Store;
import org.junit.Before;
import org.junit.Test;


import java.text.DecimalFormat;
import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;


public class ShoppingApplicationTest {

    private StoreDBService myStoreDBService;
    private MyCartService myCartService;
    private InvoiceService myInvoiceService;
    private DecimalFormat df;

    @Before
    public void setUp() throws Exception {
        //Services
        myStoreDBService = new MyStoreDBService(new MyStoreDao(Store.getStore()));
        myCartService = new MyCartService(myStoreDBService, new MyCartLoadingStrategy());
        myInvoiceService = new MyInvoiceService(new MyInvoiceGenerator());
        df = new DecimalFormat("#.####");
    }

    @Test
    public void UserIsAffiliate() throws InventoryShortageException {
       ShoppingApplication shoppingApplication = new ShoppingApplication(myStoreDBService,
               myCartService, myInvoiceService);
        LocalDateTime localDateTime = LocalDateTime.of(2018, 11, 22, 3, 15);
        UserDetails userDetails = new UserDetails("Atif Karbelkar", UserTypes.AFFILIATE, localDateTime, "+91-9865-000-001", "atif@gmail.com");
        assertEquals("Bill Amount must me 73.5336 for this User",df.format(73.5336d), df.format(shoppingApplication.shop(userDetails)));
    }

    @Test
    public void UserIsEmployee() throws InventoryShortageException {
        ShoppingApplication shoppingApplication = new ShoppingApplication(myStoreDBService,
                myCartService, myInvoiceService);
        LocalDateTime localDateTime = LocalDateTime.of(2018, 11, 22, 3, 15);
        UserDetails userDetails = new UserDetails("Atif Karbelkar", UserTypes.EMPLOYEE, localDateTime, "+91-9865-000-001", "atif@gmail.com");
        assertEquals("Bill Amount must me 57.1928 for this User",df.format(57.1928d), df.format(shoppingApplication.shop(userDetails)));
    }

    @Test
    public void UserIsRecentCustomer() throws InventoryShortageException {
        ShoppingApplication shoppingApplication = new ShoppingApplication(myStoreDBService,
                myCartService, myInvoiceService);
        LocalDateTime localDateTime = LocalDateTime.of(2018, 11, 22, 3, 15);
        UserDetails userDetails = new UserDetails("Atif Karbelkar", UserTypes.CUSTOMER, localDateTime, "+91-9865-000-001", "atif@gmail.com");
        assertEquals("Bill Amount must me 81.7040 for this User",df.format(81.7040d), df.format(shoppingApplication.shop(userDetails)));
    }

    @Test
    public void UserIsOldCustomer() throws InventoryShortageException {
        ShoppingApplication shoppingApplication = new ShoppingApplication(myStoreDBService,
                myCartService, myInvoiceService);
        LocalDateTime localDateTime = LocalDateTime.of(2015, 11, 22, 3, 15);
        UserDetails userDetails = new UserDetails("Atif Karbelkar", UserTypes.CUSTOMER, localDateTime, "+91-9865-000-001", "atif@gmail.com");
        assertEquals("Bill Amount must me 77.6188 for this User",df.format(77.6188d), df.format(shoppingApplication.shop(userDetails)));
    }
}
