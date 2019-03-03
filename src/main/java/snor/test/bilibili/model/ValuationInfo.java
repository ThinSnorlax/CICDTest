package snor.test.bilibili.model;

import java.util.HashMap;

public class ValuationInfo {

    public Double usageQuantity = 0.00;
    public Double unBlendedRate = 0.00;
    public Double unBlendedCost = 0.00;

    public ValuationInfo() {}

    public ValuationInfo (Double usageQuantity,
                          Double unBlendedRate, Double unBlendedCost){
        this.usageQuantity = usageQuantity;
        this.unBlendedRate = unBlendedRate;
        this.unBlendedCost = unBlendedCost;
    }

    @Override
    public String toString() {
        return usageQuantity.toString() + "," + unBlendedRate.toString() + "," + unBlendedCost.toString();
    }


}
