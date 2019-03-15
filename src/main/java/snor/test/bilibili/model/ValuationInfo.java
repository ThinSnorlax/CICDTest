package snor.test.bilibili.model;

import java.util.HashMap;

public class ValuationInfo {

    public String description = "";
    public Double usageQuantity = 0.00;
    public Double unBlendedRate = 0.00;
    public Double unBlendedCost = 0.00;
    public Double discount = 0.00;
    public Double costOfProduction = 0.00;

    public ValuationInfo() {}

    public ValuationInfo (Double usageQuantity,
                          Double unBlendedRate, Double unBlendedCost){
        this.usageQuantity = usageQuantity;
        this.unBlendedRate = unBlendedRate;
        this.unBlendedCost = unBlendedCost;
    }

    public ValuationInfo (Double usageQuantity,
                          Double unBlendedRate, Double unBlendedCost, Double discount, Double costOfProduction, String description){
        this.usageQuantity = usageQuantity;
        this.unBlendedRate = unBlendedRate;
        this.unBlendedCost = unBlendedCost;
        this.discount = discount;
        this.costOfProduction = costOfProduction;
        this.description = description;
    }

    @Override
    public String toString() {
        return usageQuantity.toString() + "," + unBlendedRate.toString() + "," + unBlendedCost.toString();
    }

    public String toStringAll() {
        return description + "," + usageQuantity.toString() + "," + unBlendedRate.toString() + "," + unBlendedCost.toString() + "," +
                costOfProduction.toString() + "," + discount.toString();
    }

}
