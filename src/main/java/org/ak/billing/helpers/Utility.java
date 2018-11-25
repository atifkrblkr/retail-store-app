package org.ak.billing.helpers;

import org.ak.billing.constants.ApplicationConstants;

public class Utility {

    public static void println(Object o) {
        if ((Boolean) ApplicationConstants.SHOW_LOGS.getApplicationConstant()) {
            System.out.println(o);
        }
    }
}
