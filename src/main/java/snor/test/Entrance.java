package snor.test;

import snor.test.bilibili.CdnProcess;

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
        CdnProcess cdnProcess = new CdnProcess();
        cdnProcess.mainThread();
    }

}
