package util;

import vo.StockInfo;
import vo.UserInterest;

import java.io.*;
import java.util.Scanner;

public class FileHandlerImpl implements FileHandler {

    /**
     * This func gets stock information from the given interfaces path.
     * If interfaces don't exit,or it has a illegal/malformed format, return NULL.
     * The filepath can be a relative path or a absolute path
     *
     * @param filePath
     * @return the Stock information array from the interfaces,or NULL
     */
    @Override
    public StockInfo[] getStockInfoFromFile(String filePath) {
        //TODO: write your code here
        StockInfo[] stockInfos=new StockInfo[61];
        try {
            FileInputStream file=new FileInputStream(filePath);
            BufferedReader br=new BufferedReader(new InputStreamReader(file,"UTF-8"));
            String tempString = " ";
            int line=0;
            while ((tempString=br.readLine())!=null) {
                String items[]=tempString.split("\t");
                if(items[7]!=null) {
                    stockInfos[line] = new StockInfo(items[0], items[1], items[2], items[3], items[4], items[5], items[6], items[7]);
                }
                else{//对于没有content的情况
                    stockInfos[line] = new StockInfo(items[0], items[1], items[2], items[3], items[4], items[5], items[6]);
                }
                line++;
            }
            br.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockInfos;
    }

    /**
     * This func gets user interesting from the given interfaces path.
     * If interfaces don't exit,or it has a illegal/malformed format, return NULL.
     * The filepath can be a relative path or a absolute path
     *
     * @param filePath
     * @return
     */
    @Override
    public UserInterest[] getUserInterestFromFile(String filePath) {
        UserInterest[] userInterest=new UserInterest[500];
        File file=new File(filePath);
        Scanner scanner=null;
        try{
            scanner=new Scanner(file);
            int line=0;
            while(scanner.hasNextLine()){
                String s=scanner.nextLine();
                byte[] bool=new byte[60];
                for(int i=0;i<60;i++){
                    bool=s.getBytes();
                }
                userInterest[line]=new UserInterest(bool);
                line++;
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return userInterest;
    }

    /**
     * This function need write matrix to files
     *
     * @param matrix the matrix you calculate
     */
    @Override
    public void setCloseMatrix2File(double[][] matrix) {
        //TODO: write your code here
        try {
            PrintWriter printWriter=new PrintWriter(new FileWriter("E:\\GitHub\\homework_2\\src\\main\\resources\\CloseMatrix.txt"));
            for(int i=0;i<60;i++){
                for(int j=0;j<60;j++){
                    printWriter.print(matrix[i][j]);
                    printWriter.print('\t');
                }
                printWriter.print('\n');
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function need write recommend to files
     *
     * @param recommend the recommend you calculate
     */
    @Override
    public void setRecommend2File(double[][] recommend) {
        //TODO: write your code here
        try {
            PrintWriter printWriter=new PrintWriter(new FileWriter("E:\\GitHub\\homework_2\\src\\main\\resources\\Recommend.txt"));
            for(int i=0;i<500;i++){
                for(int j=0;j<10;j++){
                    printWriter.print(recommend[i][j]);
                    printWriter.print('\t');
                }
                printWriter.print('\n');
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
