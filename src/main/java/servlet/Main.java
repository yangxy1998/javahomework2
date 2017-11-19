//package servlet;
//
///**
// * Created by Administrator on 2017/11/5.
// */
//import recommend.RecommenderImpl;
//import util.FileHandlerImpl;
//import vo.StockInfo;
//import vo.UserInterest;
//
//public class Main{
//
//    public static void main(String[] args){
//        FileHandlerImpl fileHandler=new FileHandlerImpl();
//        RecommenderImpl recommender=new RecommenderImpl();
//
//        StockInfo[] stockInfos = fileHandler.getStockInfoFromFile("E:\\GitHub\\homework_2\\src\\main\\resources\\data.txt");
//        //矩阵
//        double[][] matrix, recommend;
//        //获得用户兴趣数据
//        UserInterest[] userInterests = fileHandler.getUserInterestFromFile("E:\\GitHub\\homework_2\\src\\main\\resources\\interest.txt");
//        //分析股票相似度
//        matrix = recommender.calculateMatrix(stockInfos);
//        //根据相似度矩阵推荐用户
//        recommend = recommender.recommend(matrix,userInterests);
//        //写入矩阵文件
//        fileHandler.setCloseMatrix2File(matrix);
//        //写入推荐文件
//        fileHandler.setRecommend2File(recommend);
//    }
//
//}
