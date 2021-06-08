package Entities;

public class Game implements Entity{

    private String firstPlayerWord, secondPlayerWord, thirdPlayerWord;
    private Integer gameid;

    public Game(){}

    public Game(String firstPlayerWord, String secondPlayerWord, String thirdPlayerWord, Integer gameid) {
        this.firstPlayerWord = firstPlayerWord;
        this.secondPlayerWord = secondPlayerWord;
        this.thirdPlayerWord = thirdPlayerWord;
        this.gameid = gameid;
    }

    public String getFirstPlayerWord() {
        return firstPlayerWord;
    }

    public void setFirstPlayerWord(String firstPlayerWord) {
        this.firstPlayerWord = firstPlayerWord;
    }

    public String getSecondPlayerWord() {
        return secondPlayerWord;
    }

    public void setSecondPlayerWord(String secondPlayerWord) {
        this.secondPlayerWord = secondPlayerWord;
    }

    public String getThirdPlayerWord() {
        return thirdPlayerWord;
    }

    public void setThirdPlayerWord(String thirdPlayerWord) {
        this.thirdPlayerWord = thirdPlayerWord;
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }
}
