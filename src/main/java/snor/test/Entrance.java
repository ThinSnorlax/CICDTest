package snor.test;

import snor.test.Utils.LogUtils;
import snor.test.bilibili.CdnProcess;
import snor.test.bilibili.Discount;

public class Entrance {

    public static void main(String[] args) {
        /*for (int i=0; i<100; i++){
            AWSTest.getDynamoDBTables();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        Discount discount = new Discount();
        discount.mainThread();

        /*
        *
        {0.140,0.135,0.120,0.100,0.080}
        {0, 10 * 1024, 50 * 1024, 150 * 1024, 500 * 1024}
        85270.58535
        *
        * */
/*
        double a = 10 * 1024 * 0.14 + 40 * 1024 * 0.135 + 0.12 * (85270.58535 - 50 * 1024);
        LogUtils.consolePrint(a);*/

    }

}
