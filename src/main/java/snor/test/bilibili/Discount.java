package snor.test.bilibili;

import com.csvreader.CsvReader;
import snor.test.Utils.CSVUtils;
import snor.test.Utils.CommonUtils;
import snor.test.bilibili.model.ValuationInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 计算价格为0.0286的cdn流量原价与现价的折扣
 */
public class Discount {

    /*
     每月           美国和加拿大	欧洲 	    南非和中东 	日本	        澳大利亚	    新加坡、韩   印度	        南美洲
                                                                                国、中国
                                                                                台湾、中国
                                                                                香港和菲
                                                                                律宾

     前 10TB 	    0.085 USD	0.085 USD	0.110 USD	0.114 USD	0.114 USD	0.140 USD	0.170 USD	0.250 USD
     接下来的 40TB 	0.080 USD	0.080 USD	0.105 USD	0.089 USD	0.098 USD	0.135 USD	0.130 USD	0.200 USD
     接下来的 100TB 	0.060 USD	0.060 USD	0.090 USD	0.086 USD	0.094 USD	0.120 USD	0.110 USD	0.180 USD
     接下来的 350TB 	0.040 USD	0.040 USD	0.080 USD	0.084 USD	0.092 USD	0.100 USD	0.100 USD	0.160 USD
     接下来的 524TB 	0.030 USD	0.030 USD	0.060 USD	0.080 USD	0.090 USD	0.080 USD	0.100 USD	0.140 USD
     接下来 4PB  	0.025 USD	0.025 USD	0.050 USD	0.070 USD	0.085 USD	0.070 USD	0.100 USD	0.130 USD
     超过 5PB 	    0.020 USD	0.020 USD	0.040 USD	0.060 USD	0.080 USD	0.060 USD	0.100 USD	0.125 USD
85270.58535
51200 = 34070.58535
     $0.0286 per GB data transfer out (Asia)
     $0.0286 per GB data transfer out (South Africa)
     $0.0286 per GB data transfer out (Australia)
     $0.0286 per GB data transfer out (Canada)
     $0.0286 per GB data transfer out (Europe)
     $0.0286 per GB data transfer out (India)
     $0.0286 per GB data transfer out (Japan)
     $0.0286 per GB data transfer out (United States)
     $0.0286 per GB data transfer out (South America)

     */
    public String price286Path = "D://JavaResult/2019-02-bilibi-price286.csv";
    CsvReader price286Reader;
    List<String> descriptionList;
    List<String> usageQuantityList;
    List<String> unBlendedCostList;
    List<String> resultsList;
//  final double[] totalUsage = {0, 10 * 1024, 40 * 1024, 100 * 1024, 350 * 1024};

    final double[] totalUsage = {0, 10 * 1024, 50 * 1024, 150 * 1024, 500 * 1024};

    final double[] priceAsia = {0.140,0.135,0.120,0.100,0.080};
    final double[] priceSouthAfrica = {0.110,0.105,0.090,0.080,0.060};
    final double[] priceAustralia = {0.114,0.098,0.094,0.092,0.090};
    final double[] priceCanada = {0.085,0.080,0.060,0.040,0.030};
    final double[] priceEurope = {0.085,0.080,0.060,0.040,0.030};
    final double[] priceIndia = {0.170,0.130,0.110,0.100,0.100};
    final double[] priceJapan = {0.114,0.089,0.086,0.084,0.080};
    final double[] priceUnitedStates = {0.085,0.080,0.060,0.040,0.030};
    final double[] priceSouthAmerica = {0.250,0.200,0.180,0.160,0.140};
    final String[] regionLists = {"(Asia)","(South Africa)","(Australia)","(Canada)","(Europe)","(India)","(Japan)","(United States)","(South America)"};
    /*
    暂时没用
     */
    private void getBillingCsv() throws IOException {
        price286Reader = CSVUtils.Read(price286Path);
    }

    /*
    获取折扣折扣
     */
    public void getDiscount() {
        resultsList = new ArrayList<>();
        descriptionList = new ArrayList<>();
        usageQuantityList = new ArrayList<>();
        unBlendedCostList = new ArrayList<>();
        List<String> price286List = CSVUtils.importCsv(price286Path);
        resultsList.add("Description,usageQuantity,unBlendedRate,unBlendedCost,原价,折扣");
        //List<ValuationInfo> valuationInfos = new ArrayList<>();
        for(int i=0; i<price286List.size();i++ ){
            if(i!=0){
                String s = price286List.get(i);
                String[] items = s.split(",");
                ValuationInfo valuationInfo = new ValuationInfo(CommonUtils.StringToDouble(items[1]),
                        CommonUtils.StringToDouble(items[2]), CommonUtils.StringToDouble(items[3]), 0.00, 0.00, items[0]);
                if (items[0].contains(regionLists[0])) {
                    getCostOfProduction(valuationInfo, priceAsia, CommonUtils.StringToDouble(items[1]));
                }else if (items[0].contains(regionLists[1])) {
                    getCostOfProduction(valuationInfo, priceSouthAfrica, CommonUtils.StringToDouble(items[1]));
                }else if (items[0].contains(regionLists[2])) {
                    getCostOfProduction(valuationInfo, priceAustralia, CommonUtils.StringToDouble(items[1]));
                }else if (items[0].contains(regionLists[3])) {
                    getCostOfProduction(valuationInfo, priceCanada, CommonUtils.StringToDouble(items[1]));
                }else if (items[0].contains(regionLists[4])) {
                    getCostOfProduction(valuationInfo, priceEurope, CommonUtils.StringToDouble(items[1]));
                }else if (items[0].contains(regionLists[5])) {
                    getCostOfProduction(valuationInfo, priceIndia, CommonUtils.StringToDouble(items[1]));
                }else if (items[0].contains(regionLists[6])) {
                    getCostOfProduction(valuationInfo, priceJapan, CommonUtils.StringToDouble(items[1]));
                }else if (items[0].contains(regionLists[7])) {
                    getCostOfProduction(valuationInfo, priceUnitedStates, CommonUtils.StringToDouble(items[1]));
                }else if (items[0].contains(regionLists[8])) {
                    getCostOfProduction(valuationInfo, priceSouthAmerica, CommonUtils.StringToDouble(items[1]));
                }
                resultsList.add(valuationInfo.toStringAll());
            }
        }

    }

    /*
     获取原价
     {0.140,0.135,0.120,0.100,0.080}
     {0, 10 * 1024, 50 * 1024, 150 * 1024, 500 * 1024}
     85270.58535
     */
    public void getCostOfProduction(ValuationInfo valuationInfo, double[] priceList, double usage) {
        double costOfProduction = 0;
        if (usage > 5 * 1024 * 1024 &&
            totalUsage[totalUsage.length - 1] != 5 * 1024 * 1024) {
            throw new RuntimeException("价格不够，请添加");
        }
        for (int i=1; i<totalUsage.length; i++) {
            if (usage > totalUsage[i]) {
                costOfProduction += (totalUsage[i] - totalUsage[i-1]) * priceList[i-1];
            }else {
                costOfProduction += (usage - totalUsage[i-1]) * priceList[i-1];
                break;
            }
        }
        valuationInfo.costOfProduction = costOfProduction;
        valuationInfo.discount = valuationInfo.unBlendedCost / costOfProduction;
    }

    /*
        导出账单到CSV
     */
    private void exportDiscount() {
        String fileBaseName = getFileBaseName();
        CSVUtils.exportCsv(CdnProcess.resultBasePath + fileBaseName + "-discount.csv", resultsList);
    }

    private String getFileBaseName() {
        String[] pathItems = price286Path.split("/");
        return pathItems[pathItems.length-1].split("\\.")[0];
    }

    public void mainThread() {
        getDiscount();
        exportDiscount();
    }

}
