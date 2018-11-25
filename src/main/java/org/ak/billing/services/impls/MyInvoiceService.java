package org.ak.billing.services.impls;

import org.ak.billing.beans.Shopper;
import org.ak.billing.services.InvoiceService;
import org.ak.billing.strategies.InvoicingStrategy;

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

    }
}
