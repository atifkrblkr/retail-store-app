package org.ak.billing.services.impls;

import org.ak.billing.beans.Product;
import org.ak.billing.beans.Shopper;
import org.ak.billing.constants.ApplicationConstants;
import org.ak.billing.helpers.Utility;
import org.ak.billing.services.InvoiceService;
import org.ak.billing.strategies.InvoicingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class MyInvoiceService implements InvoiceService {
    private final InvoicingStrategy invoicingStrategy;

    public MyInvoiceService(InvoicingStrategy invoicingStrategy) {
        this.invoicingStrategy = invoicingStrategy;
    }

    @Override
    public void generate(Shopper shopper) {
        invoicingStrategy.generate(shopper);
    }

    @Override
    public void print(Shopper shopper) {
        //Bill Header
        printBuffer();
        printCenter(ApplicationConstants.BILL_HEADER.getApplicationConstant().toString(),
                ApplicationConstants.BILL_PADDING.getApplicationConstant().toString());
        printBuffer();
        Utility.println(" ");

        //Bill & User Info
        printColumn("Date",Utility.getFormattedDate(shopper.getInvoice().getDate()),
                ApplicationConstants.BILL_SPACE.getApplicationConstant().toString());
        printColumn("Bill ID",shopper.getInvoice().getUid().toString(),
                ApplicationConstants.BILL_SPACE.getApplicationConstant().toString());
        printColumn("Customer Name",shopper.getUserDetails().getName(),
                ApplicationConstants.BILL_SPACE.getApplicationConstant().toString());
        printColumn("Customer ID",shopper.getUserDetails().getUid(),
                ApplicationConstants.BILL_SPACE.getApplicationConstant().toString());
        printColumn("Customer Contacts", Utility.getCSVFromList(shopper.getUserDetails().getContacts()),
                ApplicationConstants.BILL_SPACE.getApplicationConstant().toString());
        printColumn("Customer Since",Utility.getFormattedDate(shopper.getUserDetails().getUserSince()),
                ApplicationConstants.BILL_SPACE.getApplicationConstant().toString());
        printColumn("Customer Type",shopper.getUserDetails().getUserType().toString(),
                ApplicationConstants.BILL_SPACE.getApplicationConstant().toString());
        Utility.println(" ");

        //Bill Products Info
        printBuffer();
        printCenter(ApplicationConstants.BILL_PRODUCT_HEADER.getApplicationConstant().toString(),
                ApplicationConstants.BILL_PADDING.getApplicationConstant().toString());
        printProducts(shopper.getShoppingCart().getProductsInCart().getProducts().values());
        Utility.println("\n");
        printBuffer();
        printBuffer();

    }

    private void printBuffer(){
        Utility.println(StringUtils.repeat(ApplicationConstants.BILL_PADDING.getApplicationConstant().toString(),
                (int)ApplicationConstants.BILL_LENGTH.getApplicationConstant()));
    }

    private void printCenter(String s, String padding){
        Utility.println(StringUtils.rightPad(StringUtils.leftPad(s,
                ((int)ApplicationConstants.BILL_LENGTH.getApplicationConstant()+s.length())/2,
                padding),(int)ApplicationConstants.BILL_LENGTH.getApplicationConstant(),padding));
    }

    private void printColumn(String s, String t, String padding){
        Utility.println(
                StringUtils.repeat(ApplicationConstants.BILL_SPACE.getApplicationConstant().toString(),
                (int)ApplicationConstants.BILL_LENGTH.getApplicationConstant()/4) +
                StringUtils.rightPad(s,20, padding) + t
        );
    }

    private void printProducts(Collection<Product> products){
        String productHeader =  StringUtils.rightPad(" PRODUCT NAME ",30," ") +
                StringUtils.rightPad("PRODUCT ID",40," ") +
                StringUtils.rightPad("PRODUCT TYPE",18," ") +
                StringUtils.rightPad("UNIT PRICE",12," ") +
                StringUtils.rightPad("QUANTITY",10," ")+
                StringUtils.rightPad("TOTAL PRICE",12," ");
        printBuffer();
        Utility.println(productHeader);
        printBuffer();
        for (Product p : products){
            String product =  StringUtils.rightPad("* "+p.getName(),30," ") +
                    StringUtils.rightPad(p.getId().toString(),40," ") +
                    StringUtils.rightPad(p.getType().toString(),18," ") +
                    StringUtils.rightPad("$"+p.getUnitPrice(),12," ") +
                    StringUtils.rightPad(String.valueOf(p.getQuantity()),10," ")+
                    StringUtils.rightPad("$"+p.getQuantity()*p.getUnitPrice(),12," ");
            Utility.println(product);
        }
    }
}
