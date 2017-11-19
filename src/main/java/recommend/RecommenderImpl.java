package recommend;

import javafx.util.Pair;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import segmenter.ChineseSegmenter;
import segmenter.ChineseSegmenterImpl;
import tf_idf.TF_IDF;
import tf_idf.TF_IDFImpl;
import vo.StockInfo;
import vo.UserInterest;

import java.util.List;

public class RecommenderImpl implements Recommender {

    /**
     * this function need to calculate stocks' content similarity,and return the similarity matrix
     *
     * @param stocks stock info
     * @return similarity matrix
     */
    private ChineseSegmenter segmenter=new ChineseSegmenterImpl();
    private TF_IDF tf_idf=new TF_IDFImpl();
    @Override
    public double[][] calculateMatrix(StockInfo[] stocks) {
        //TODO: write your code here
        for(int i=1;i<stocks.length;i++){
            String temp=stocks[i].getAnswer();
            stocks[i].setAnswer(stocks[i].getContent());
            stocks[i].setContent(temp);
        }
        double[][] matrix=new double[stocks.length-1][stocks.length-1];
        List<String> words=segmenter.getWordsFromInput(stocks);
        Pair<String,Integer>[] pairs=tf_idf.getResult(words,stocks);
        for(int i=0;i<stocks.length-1;i++){
            for(int j=i+1;j<stocks.length-1;j++){
                MyWord[] myWord=new MyWord[20];
                for(int k=0;k<20;k++){
                    myWord[k]=new MyWord(pairs[k].getKey());
                    Result resultX= ToAnalysis.parse(stocks[i+1].getAnswer());
                    List<Term> termsX=resultX.getTerms();
                    for(int m=0;m<termsX.size();m++){
                        if(termsX.get(m).getName().equals(myWord[k].myword))myWord[k].setX( myWord[k].getX()+1);
                    }
                    Result resultY= ToAnalysis.parse(stocks[j+1].getAnswer());
                    List<Term> termsY=resultY.getTerms();
                    for(int m=0;m<termsY.size();m++){
                        if(termsY.get(m).getName().equals(myWord[k].myword))myWord[k].setY( myWord[k].getY()+1);
                    }
                }
                double Up=0,Downl=0,Downr=0;
                for(int k=0;k<20;k++){
                    Up=Up+myWord[k].getX()*myWord[k].getY();
                    Downl=Downl+Math.pow(myWord[k].getX(),2.0);
                    Downr=Downr+Math.pow(myWord[k].getY(),2.0);
                }
                matrix[i][j]=Up/(Math.sqrt(Downl)*Math.sqrt(Downr));
                matrix[j][i]=matrix[i][j];
            }
        }
        for(int i=0;i<stocks.length-1;i++){
            matrix[i][i]=0;
        }
        return matrix;
    }

    private class MyWord{
        private String myword;
        private double x;
        private double y;
        public void setWord(String word){myword=word;}
        public void setX(double x){this.x=x;}
        public void setY(double y){this.y=y;}
        public double getX(){return x;}
        public double getY(){return y;}
        public MyWord(){
            this.x=0;
            this.y=0;
            this.myword=null;
        }
        public MyWord(String word){
            this.x=0;
            this.y=0;
            this.myword=word;
        }
    }

    /**
     * this function need to recommend the most possibility stock number
     *
     * @param matrix       similarity matrix
     * @param userInterest user interest
     * @return commend stock number
     */
    @Override
    public double[][] recommend(double[][] matrix, UserInterest[] userInterest) {
        //TODO: write your code here
        double[][] rec=new double[500][10];
        for(int i=0;i<userInterest.length;i++){
            double[] num=new double[userInterest[i].getIsLike().length];
            byte[] boo=userInterest[i].getIsLike();
            for(int j=0;j<userInterest[i].getIsLike().length;j++){//计算每个用户对各个文章的喜好度
                double temp=0;
                for(int k=0;k<boo.length;k++){
                    if(boo[k]==49)temp=temp+matrix[j][k];
                }
                num[j]=temp;
            }
            for(int j=0;j<10;j++){
                double max = 0;
                int queue = 0;
                for(int k=0;k<userInterest[i].getIsLike().length;k++) {//选取其中最大的值，记录其ID后将其归零，以便选取次大的值。
                    if (num[k] > max) {
                        num[queue] = max;
                        max = num[k];
                        queue = k;
                        num[k] = 0;
                    }
                }
                rec[i][j]=queue+1;
            }
        }
        return rec;
    }
}
