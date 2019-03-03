package snor.test.Utils;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**   
  * CSV操作(读取和写入)
  * @author lq
  * @version 2018-04-23 
  */
public class CSVUtils {

    /**
      * 导出到文件 
      * @param file csv文件(路径+文件名)，csv文件不存在会自动创建
      * @param dataList 数据
      * @return
      */
    public static boolean exportCsv(File file, List<String> dataList){
        boolean isSucess=false;
        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
             out = new FileOutputStream(file);
             osw = new OutputStreamWriter(out);
             bw =new BufferedWriter(osw);
             if(dataList!=null && !dataList.isEmpty()){
                 for(String data : dataList){
                     bw.append(data).append("\r");
                 }
             }
             isSucess=true;
        } catch (Exception e) {
             isSucess=false;
        }finally{
            if(bw!=null){
                 try {

                     bw.close();
                     bw=null;
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
            }
            if(osw!=null){
                 try {
                     osw.close();
                     osw=null;
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
            }
            if(out!=null){
                 try {
                     out.close();
                     out=null;
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
            }
        }

        return isSucess;
    }

    /**
      * 导入数据到代码处理
      * @param file csv文件(路径+文件)
      * @return
      */
    public static List<String> importCsv(File file){
        System.out.println("aaa:" + file.getName());
        List<String> dataList=new ArrayList<String>();

        BufferedReader br=null;
        try {
             br = new BufferedReader(new FileReader(file));
             String line = "";
             while ((line = br.readLine()) != null) {
                 dataList.add(line);
             }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
             if(br!=null){
                 try {
                     br.close();
                     br=null;
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
        }
        return dataList;
     }

     /*
     * importCsv 导入CSV到代码中;
     * string ;
     * */
     public static List<String> importCsv(String path){
        return importCsv(new File(path));
     }

    /*
     * exportCsv 导出数据到CSV文件中;
     * string ;
     * */
    public static Boolean exportCsv(String path, List<String> dataList){
        return exportCsv(new File(path), dataList);
    }


    public static void Writer(String path, List<String[]> dataList) throws IOException {

        // 第一参数：新生成文件的路径 第二个参数：分隔符（不懂仔细查看引用百度百科的那段话） 第三个参数：字符集
        CsvWriter csvWriter = new CsvWriter(path, ',', Charset.forName("UTF-8"));

        // 写表头和内容，因为csv文件中区分没有那么明确，所以都使用同一函数，写成功就行
        for (String[] item : dataList) {
            csvWriter.writeRecord(item);
        }
        // 关闭csvWriter
        csvWriter.close();
    }

    public static CsvReader Read(String path) throws IOException {

        // 第一参数：读取文件的路径 第二个参数：分隔符（不懂仔细查看引用百度百科的那段话） 第三个参数：字符集
        return new CsvReader(path, ',', Charset.forName("UTF-8"));
    }


/*    
            
            *//**
      * 测试
      * @param args
      *//*
            public static void main(String[] args){  
        //exportCsv();
        //importCsv();
     } 
                
                
                
                
                *//**
          * CSV读取测试
          * @throws Exception
          *//*
                public static void importCsv()  {
        List<String> dataList=CSVUtils.importCsv(new File("D:/test/ljq.csv"));
        if(dataList!=null && !dataList.isEmpty()){
            for(int i=0; i<dataList.size();i++ ){
                if(i!=0){//不读取第一行
                    String s=dataList.get(i); 
                    System.out.println("s  "+s);
                      String[] as = s.split(",");
                      System.out.println(as[0]);
                      System.out.println(as[1]);
                      System.out.println(as[2]);
                }
            }
        }
    }
    
            *//**
      * CSV写入测试
      * @throws Exception
      *//*
            public static void exportCsv() {
        List<String> dataList=new ArrayList<String>();
        dataList.add("number,name,sex");
        dataList.add("1,张三,男");
        dataList.add("2,李四,男");
        dataList.add("3,小红,女");
        boolean isSuccess=CSVUtils.exportCsv(new File("D:/test/ljq.csv"), dataList);
        System.out.println(isSuccess);
    }
       
            */
}
