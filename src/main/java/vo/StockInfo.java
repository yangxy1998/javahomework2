package vo;

public class StockInfo{
    private String Id;
    private String Title;
    private String Author;
    private String Date;
    private String Lastupdate;
    private String Content;
    private String Answerauthor;
    private String Answer;
    private int AnswerLength;

    public String getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getAnswer() {
        return Answer;
    }

    public String getAnswerauthor() {
        return Answerauthor;
    }

    public String getContent() {
        return Content;
    }

    public String getDate() {
        return Date;
    }

    public String getLastupdate() {
        return Lastupdate;
    }

    public int getAnswerLength(){
        return AnswerLength;
    }

    public void setAnswer(String answer) {
        Answer=answer;
    }

    public void setAnswerauthor(String answerauthor) {
        Answerauthor = answerauthor;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setLastupdate(String lastupdate) {
        Lastupdate = lastupdate;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public StockInfo(String id,String title,String author,String date,String lastupdate,String content,String answerauthor,String answer){
        Id=id;
        Title=title;
        Author=author;
        Date=date;
        Lastupdate=lastupdate;
        Content=content;
        Answerauthor=answerauthor;
        Answer=answer;
        AnswerLength=answer.length();
    }
    public StockInfo(String id,String title,String author,String date,String lastupdate,String answerauthor,String answer){
        Id=id;
        Title=title;
        Author=author;
        Date=date;
        Lastupdate=lastupdate;
        Content=null;
        Answerauthor=answerauthor;
        Answer=answer;
        AnswerLength=answer.length();
    }
    public StockInfo(StockInfo temp){
        Id=temp.getId();
        Title=temp.getTitle();
        Author=temp.getAuthor();
        Date=temp.getDate();
        Lastupdate=temp.getLastupdate();
        Content=temp.getContent();
        Answerauthor=temp.getAnswerauthor();
        Answer=temp.getAnswer();
        AnswerLength=Answer.length();
    }
}
