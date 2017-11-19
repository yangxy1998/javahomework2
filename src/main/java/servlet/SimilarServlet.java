package servlet;

import recommend.Recommender;
import recommend.RecommenderImpl;
import util.FileHandler;
import util.FileHandlerImpl;
import vo.StockInfo;
import vo.UserInterest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/similarity")
public class SimilarServlet  extends HttpServlet{
    private FileHandler fileHandler;

    private Recommender recommender;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 数据处理
        StockInfo[] stockInfos = fileHandler.getStockInfoFromFile("E:\\GitHub\\homework_2\\src\\main\\resources\\data.txt");
        //矩阵
        double[][] matrix, recommend;
        //获得用户兴趣数据
        UserInterest[] userInterests = fileHandler.getUserInterestFromFile("E:\\GitHub\\homework_2\\src\\main\\resources\\interest.txt");
        //分析股票相似度
        matrix = recommender.calculateMatrix(stockInfos);
        //根据相似度矩阵推荐用户
        recommend = recommender.recommend(matrix,userInterests);
        //写入矩阵文件
        fileHandler.setCloseMatrix2File(matrix);
        //写入推荐文件
        fileHandler.setRecommend2File(recommend);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        fileHandler = new FileHandlerImpl();
        recommender = new RecommenderImpl();
    }

}
