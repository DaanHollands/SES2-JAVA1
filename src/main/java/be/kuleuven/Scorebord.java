package be.kuleuven;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Deze Scorebord klasse houdt een lijst van spelers bij met een desbetreffende score
 */
public class Scorebord{
    private Type spelersArray = new TypeToken<ArrayList<Speler>>(){}.getType();
    private ArrayList<Speler> Spelers = new ArrayList<Speler>();
    private String opslagBestand;

    /**
     * @param opslagBestand locatie voor het opslaan van de scorebord-data
     */
    public Scorebord(String opslagBestand){
        this.opslagBestand = opslagBestand;

        try{
            Spelers = readFrom();
        } catch (IOException i){
            throw new RuntimeException(i);
        }
    }

    private void saveTo() throws IOException{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String data = "";
        FileWriter fileWriter = new FileWriter(this.opslagBestand);
        fileWriter.write(gson.toJson(Spelers, spelersArray));
        fileWriter.close();
    }

    private ArrayList<Speler> readFrom() throws IOException{
        Gson gson = new Gson();
        return gson.fromJson(new FileReader(this.opslagBestand), spelersArray);
    }

    /**
     * @param naam Naam van de toe te voegen speler
     * @param huidigeScore Huidige score van de toe te voegen Speler
     */
    public void voegToe(String naam, int huidigeScore){
        Spelers.add(new Speler(naam, huidigeScore));
        try {
            saveTo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param naam naam van de opgevraagde speler
     * @return Score van de speler; Geeft -1 wanneer de speler niet is gevonden
     */
    public int getTotaleScore(String naam){
        for (Speler speler : Spelers) {
            if(speler.getNaam().equals(naam)){
                return speler.getScore();
            }
        }
        return -1;
    }

    /**
     * @return De naam van de gewonnen speler
     */
    public String getWinnaar(){
        int hoogsteScore = 0;
        String hoogsteScoreNaam = "";
        for (Speler speler : Spelers){
            if(speler.getScore() > hoogsteScore){
                hoogsteScoreNaam = speler.getNaam();
            }
        }
        return hoogsteScoreNaam;
    }
}
