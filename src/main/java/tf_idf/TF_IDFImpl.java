package tf_idf;


import javafx.util.Pair;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import util.StockSorter;
import vo.StockInfo;

import java.util.List;

public class TF_IDFImpl implements TF_IDF {
    /**
     * this func you need to calculate words frequency , and sort by frequency.
     * you maybe need to use the sorter written by yourself in example 1
     *
     * @param words the word after segment
     * @return a sorted words
     * @see StockSorter
     */
    @Override
    public Pair<String, Integer>[] getResult(List<String> words, StockInfo[] stockInfos) {
        //TODO: write your code here
        MyWord[] myword=new MyWord[words.size()];
        for(int i=0;i<words.size();i++){
            myword[i]=new MyWord(words.get(i));
        }
        int sum=0;
        for(int i=1;i<stockInfos.length;i++){//对每一行数据进行分词
            Result result= ToAnalysis.parse(stockInfos[i].getAnswer());
            List<Term> terms=result.getTerms();
            sum=sum+terms.size();
            for(int j=0;j<words.size();j++){
                for(int k=0;k<terms.size();k++){
                    if(terms.get(k).getName().equals(myword[j].getWord())){
                        myword[j].setSumOfArticle();
                        break;
                    }
                }
                for(int k=0;k<terms.size();k++){
                    if(terms.get(k).getName().equals(myword[j].getWord())){
                        myword[j].setSumOfWord();
                    }
                }
            }
        }
        BubbleDescendingsort(myword);//按照TF-IDF值进行快速排序。
        Pair<String, Integer>[] pairs=new Pair[words.size()];
        for(int i=0;i<myword.length;i++){
            pairs[i]=new Pair<>(myword[i].getWord(),i+1);//排序结果以词、TF-IDF值的排名为标准存入pairs数组当中。
        }
        return pairs;
    }

    public class MyWord{
        private int SumOfWord;
        private int SumOfArticle;
        private String myword;
        public void setSumOfWord(){SumOfWord++;}
        public void setSumOfArticle(){SumOfArticle++;}
        public String getWord(){return myword;}
        public void setWord(String word){myword=word;}
        private double TfIdf;
        public void setTfIdf(int totalWords,int totalLines){
            TfIdf=(SumOfWord/totalWords)*(Math.log(totalLines)/Math.log(SumOfArticle+1));
        }
        public MyWord(){
            this.SumOfArticle=0;
            this.SumOfWord=0;
            this.myword=null;
        }
        public MyWord(MyWord mw){
            if(mw==null)return;
            this.SumOfArticle=mw.SumOfArticle;
            this.myword=mw.myword;
            this.SumOfWord=mw.SumOfWord;
            this.TfIdf=mw.TfIdf;
        }
        public MyWord(String word){
            this.myword=word;
            this.SumOfArticle=0;
            this.SumOfWord=0;
        }
    }
    void BubbleDescendingsort(MyWord[] a) {
        for(int i=0;i<a.length-1;i++){
            for(int j=0;j<a.length-i-1;j++){
                if(a[j].TfIdf>a[j+1].TfIdf){
                    MyWord temp=new MyWord(a[j]);
                    a[j]=new MyWord(a[j+1]);
                    a[j+1]=new MyWord(temp);
                }
            }
        }
    }
}
