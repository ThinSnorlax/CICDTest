package snor.test.bilibili.model;

import snor.test.Utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillingItems {

    public Map<String,ValuationInfo> billingItems;


    public BillingItems() {
        billingItems = new HashMap<>();
    }
    /*public void addUsageQuantity(String item, double usage){
        add(usageQuantity, item, usage);
    }

    public void addUnBlendedRate(String item, double rate){
        add(unBlendedRate, item, rate);
    }

    public void addUnBlendedCost(String item, double cost){
        add(unBlendedCost, item, cost);
    }*/


    public void add(String item,
                    double usage, double rate, double cost){
        if (billingItems.get(item) == null) {
            billingItems.put(item,new ValuationInfo(usage, rate, cost));
        }else if (billingItems.get(item).unBlendedRate == rate) {
            ValuationInfo valuationInfo = billingItems.get(item);
            valuationInfo.usageQuantity += usage;
            valuationInfo.unBlendedCost += cost;
            billingItems.put(item, valuationInfo);
        }else {
            //LogUtils.consolePrint(item);
            ValuationInfo valuationInfo = billingItems.get(item);
            if (valuationInfo == null) {
                billingItems.put(item + "-" + rate,new ValuationInfo(usage, rate, cost));
            }else {
                valuationInfo.usageQuantity += usage;
                valuationInfo.unBlendedCost += cost;
                billingItems.put(item + "-" + rate,new ValuationInfo(usage, rate, cost));
            }
            //LogUtils.consolePrint(a);
        }
    }


    public List<String> getBillingItems(){
        List<String> billLists = new ArrayList<>();
        billLists.add("Description,usageQuantity,unBlendedRate,unBlendedCost");
        for(Map.Entry<String, ValuationInfo> entry : billingItems.entrySet()){
            //LogUtils.consolePrint(entry.getKey().replace(",",""));
            billLists.add(entry.getKey().replace(",","") + "," +
                    entry.getValue().toString());
        }
        return billLists;
    }

}
