package snor.test.Utils;

import java.math.BigDecimal;

public class CommonUtils {

    public static Double StringToDouble(String d){
        //System.out.println("前---" + d);
        if(d.contains("\"")) {
            /*d = d.substring(0, d.length()-1);
            d = d.substring(1, d.length());*/
            d = d.split("\"")[1];
            //System.out.println("后---" + d);
        }
        //throw (new RuntimeException("aaa"));
        /*try {

        }catch (NumberFormatException e) {
            System.out.println(e.toString());
            System.out.println("error double : " + d);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            System.out.println("error double : " + d);
        }
        return 0.00;*/
        BigDecimal bigDecimal = new BigDecimal(d);
        return Double.parseDouble(bigDecimal.toPlainString());
    }

}
