package snor.test.Utils;

public class LogUtils {

    public static void consolePrint(String log){
        printLog(log);
    }

    public static void consolePrint(int log){
        printLog(log);
    }

    public static void consolePrint(double log){
        printLog(log);
    }

    private static void printLog(Object log){
        System.out.println(log);
    }


}
