package vo;

public class UserInterest {
    private byte[] IsLike;
    public UserInterest(byte[] IsLike)
    {
        this.IsLike=IsLike;
    }
    public byte[] getIsLike(){return IsLike;}
}
