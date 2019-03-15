package snor.test.bilibili;

import com.csvreader.CsvReader;
import snor.test.Utils.CSVUtils;
import snor.test.Utils.CommonUtils;
import snor.test.Utils.LogUtils;
import snor.test.bilibili.model.BillingItems;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CdnProcess {

    public String BillPath = "C:/Users/hui57/Desktop/工作/bilibili/201901-billing.csv";
    public String Path1 = "C:/Users/hui57/Desktop/工作/bilibili/2018-11-bilibili.csv";
    public String Path2 = "C:/Users/hui57/Desktop/工作/bilibili/2019-02-bilibi.csv";
    public final static String resultBasePath = "D:/JavaResult/";
    List<String> requestsList;
    List<String> price286List;
    List<String> othersList;
    CsvReader billingReader;

    int itemDescriptionIndex = 13;          // csv中详细描述index
    int usageQuantityIndex = 16;            // csv中用量index
    int unBlendedRateIndex = 17;            // csv中单价index
    int unBlendedCostIndex = 18;            // csv中单价index
    String ItemDescription = "ItemDescription";
    String UsageQuantity = "UsageQuantity";
    String UnBlendedRate = "UnBlendedRate";
    String UnBlendedCost = "UnBlendedCost";

    private void getBillingCsv() throws IOException {
        billingReader = CSVUtils.Read(BillPath);
    }

    /*
        获取包含请求数的csv
     */
    private void getRequestsList(){

        requestsList = new ArrayList<>();
        BillingItems billingItems = new BillingItems();
        /*for(int i=0; i<billingList.size();i++ ){
            if(i!=0){
                String s = billingList.get(i);
                String[] items = s.split(",");
                if (items[itemDescriptionIndex].contains("Requests")) {
                    //System.out.println(items[itemDescriptionIndex]);
                    billingItems.add(items[itemDescriptionIndex],
                            Double.parseDouble(items[usageQuantityIndex]),
                            Double.parseDouble(items[unBlendedRateIndex]),
                            Double.parseDouble(items[unBlendedCostIndex]));
                }
            }
        }*/

    }

    /*private void getPrice286List(){

    }

    private void getOthersList(){

    }*/

    /*
    * 处理CSV账单
    * */
    /*private void processBilling() throws IOException {
        requestsList = new ArrayList<>();
        price286List = new ArrayList<>();;
        othersList = new ArrayList<>();;
        BillingItems requestsItems = new BillingItems();
        BillingItems price286Items = new BillingItems();
        BillingItems othersItems = new BillingItems();
        *//*for(int i=0; i<billingList.size();i++ ){
            if(i!=0){
                String s = billingList.get(i);
                String[] items = s.split("\",\"");
                if (items[itemDescriptionIndex].contains("Requests")) {
                    //System.out.println(items[itemDescriptionIndex]);
                    requestsItems.add(items[itemDescriptionIndex],
                            CommonUtils.StringToDouble(items[usageQuantityIndex]),
                            CommonUtils.StringToDouble(items[unBlendedRateIndex]),
                            CommonUtils.StringToDouble(items[unBlendedCostIndex]));
                }else if (items[unBlendedRateIndex].equals("0.0286")) {
                    price286Items.add(items[itemDescriptionIndex],
                            CommonUtils.StringToDouble(items[usageQuantityIndex]),
                            CommonUtils.StringToDouble(items[unBlendedRateIndex]),
                            CommonUtils.StringToDouble(items[unBlendedCostIndex]));
                }else {
                    System.out.println(s);
                    othersItems.add(items[itemDescriptionIndex],
                            CommonUtils.StringToDouble(items[usageQuantityIndex]),
                            CommonUtils.StringToDouble(items[unBlendedRateIndex]),
                            CommonUtils.StringToDouble(items[unBlendedCostIndex]));
                }
            }
        }*//*
        //过滤表头
        billingReader.readHeaders();
        while (billingReader.readRecord()) {
            // 获取内容的两种方式
            // 1. 通过下标获取
            //System.out.print(billingReader.get(0));
            // 2. 通过表头的文字获取
            //System.out.println(" " + billingReader.get("年龄"));
            if (billingReader.get(ItemDescription).contains("Requests")) {
                //System.out.println(items[itemDescriptionIndex]);
                requestsItems.add(billingReader.get(ItemDescription),
                        CommonUtils.StringToDouble(billingReader.get(UsageQuantity)),
                        CommonUtils.StringToDouble(billingReader.get(UnBlendedRate)),
                        CommonUtils.StringToDouble(billingReader.get(UnBlendedCost)));
            }else if (billingReader.get(UnBlendedRate).equals("0.0286")) {
                price286Items.add(billingReader.get(ItemDescription),
                        CommonUtils.StringToDouble(billingReader.get(UsageQuantity)),
                        CommonUtils.StringToDouble(billingReader.get(UnBlendedRate)),
                        CommonUtils.StringToDouble(billingReader.get(UnBlendedCost)));
            }else {
                //System.out.println(s);
                othersItems.add(billingReader.get(ItemDescription),
                        CommonUtils.StringToDouble(billingReader.get(UsageQuantity)),
                        CommonUtils.StringToDouble(billingReader.get(UnBlendedRate)),
                        CommonUtils.StringToDouble(billingReader.get(UnBlendedCost)));
            }
        }
        requestsList.addAll(requestsItems.getBillingItems());
        price286List.addAll(price286Items.getBillingItems());
        othersList.addAll(othersItems.getBillingItems());
    }*/

    private void processBillingB() {
        requestsList = new ArrayList<>();
        price286List = new ArrayList<>();;
        othersList = new ArrayList<>();;
        BillingItems requestsItems = new BillingItems();
        BillingItems price286Items = new BillingItems();
        BillingItems othersItems = new BillingItems();
        List<String> billingList = CSVUtils.importCsv(BillPath);
        double price = 0;
        int count = 0;
        for(int i=0; i<billingList.size();i++ ){
            if(i!=0){
                String s = billingList.get(i);
                String[] items = s.split("\",\"");
                //String[] items = s.trim().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)",-1); //双引号内的逗号不分割  双引号外的逗号进行分割
                if (items[itemDescriptionIndex].indexOf("requests") != -1 ||
                        items[itemDescriptionIndex].indexOf("Requests") != -1) {
                    //System.out.println(items[itemDescriptionIndex]);
                    //System.out.println(items[itemDescriptionIndex]);
                    price = price + CommonUtils.StringToDouble(items[unBlendedCostIndex]);
                    count++;
                    requestsItems.add(items[itemDescriptionIndex],
                            CommonUtils.StringToDouble(items[usageQuantityIndex]),
                            CommonUtils.StringToDouble(items[unBlendedRateIndex]),
                            CommonUtils.StringToDouble(items[unBlendedCostIndex]));
                }else if (CommonUtils.StringToDouble(items[unBlendedRateIndex]) == 0.0286) {
                    price286Items.add(items[itemDescriptionIndex],
                            CommonUtils.StringToDouble(items[usageQuantityIndex]),
                            CommonUtils.StringToDouble(items[unBlendedRateIndex]),
                            CommonUtils.StringToDouble(items[unBlendedCostIndex]));
                }else {
                    //System.out.println(s);
                    othersItems.add(items[itemDescriptionIndex],
                            CommonUtils.StringToDouble(items[usageQuantityIndex]),
                            CommonUtils.StringToDouble(items[unBlendedRateIndex]),
                            CommonUtils.StringToDouble(items[unBlendedCostIndex]));
                }
            }
        }
        LogUtils.consolePrint(price);
        LogUtils.consolePrint(count);
        //过滤表头
        requestsList.addAll(requestsItems.getBillingItems());
        price286List.addAll(price286Items.getBillingItems());
        othersList.addAll(othersItems.getBillingItems());
    }

    /*
        导出账单到CSV
     */
    private void exportBilling() {
        String fileBaseName = getFileBaseName();
        CSVUtils.exportCsv(resultBasePath + fileBaseName + "-requests.csv", requestsList);
        CSVUtils.exportCsv(resultBasePath + fileBaseName + "-price286.csv", price286List);
        CSVUtils.exportCsv(resultBasePath + fileBaseName + "-others.csv", othersList);
    }

    public void mainThread(){
        try {
            /*if (billingReader.getColumnCount() == 0) {
                System.out.println("billing is empty");
                return;
            }*/
            BillPath = Path2;
            getBillingCsv();
            processBillingB();
            exportBilling();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileBaseName() {
        String[] pathItems = BillPath.split("/");
        return pathItems[pathItems.length-1].split("\\.")[0];
    }

}
