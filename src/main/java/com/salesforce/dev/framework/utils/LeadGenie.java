package com.salesforce.dev.framework.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.salesforce.dev.framework.dto.ViewSalesForce;

/**
 * @author Ariel Mattos
 * @since 07/09/2015.
 * @author DanielGonzales
 */
public class LeadGenie {
    public static ViewSalesForce getLeadsView(String jsonFile) {
        Iterator<Object[]> iteratorViewData = DataDrivenManager.getObjects(jsonFile, ViewSalesForce.class);
        List<ViewSalesForce[]> listData = new ArrayList<ViewSalesForce[]>();
        while (iteratorViewData.hasNext()) {
            listData.add((ViewSalesForce[]) iteratorViewData.next());
        }
        ViewSalesForce viewSalesForce = listData.get(0)[0];
        return viewSalesForce;
    }

}
