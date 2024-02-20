package be.kuleuven;

public class Speler {
    private String naam;
    private int score;

    public Speler(String naam, int score) {
        this.naam = naam;
        this.score = score;
    }

    public String getNaam(){
        return this.naam;
    }
    public int getScore(){
        return this.score;
    }

}
