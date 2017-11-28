package baseballsim;

import java.util.*;
/**
 *
 * @author Quinn
 */
public class Lineup {
    private Batter[] batters = new Batter[9];
    private boolean underConstruction;
    private int currentBatter;
    
    
    public Lineup Lineup() {
        underConstruction = true;
        currentBatter = 0;
        return this;
    }
    
    public Lineup first(Batter b) {
        batters[0] = b;
        return this;
    }
    
    public Lineup second(Batter b) {
        batters[1] = b;
        return this;
    }
    
    public Lineup third(Batter b) {
        batters[2] = b;
        return this;
    }
    
    public Lineup fourth(Batter b) {
        batters[3] = b;
        return this;
    }
    
    public Lineup fifth(Batter b) {
        batters[4] = b;
        return this;
    }
    
    public Lineup sixth(Batter b) {
        batters[5] = b;
        return this;
    }
    
    public Lineup seventh(Batter b) {
        batters[6] = b;
        return this;
    }
    
    public Lineup eigth(Batter b) {
        batters[7] = b;
        return this;
    }
    
    public Lineup ninth(Batter b) {
        batters[8] = b;
        return this;
    }
    
    public void incrimentCurrentBatter() {
        if (currentBatter == 8) {
            currentBatter = 0;
        } else {
            currentBatter++;
        }
    }
    
    public Batter getCurrentBatter() {
        return batters[currentBatter];
    }
    
    public Lineup build() {
        for (int i = 0; i < 9; i++) {
            if (batters[i] == null) {
                throw new IllegalArgumentException("One of the slots is not filled");
            }
        }
        return this;
    }
    
    @Override
    public String toString() {
        return "Current Batter: " + getCurrentBatter().getName();
    }
    
    public String getBatterLines() {
        String s = "";
        for (Batter b : batters) {
            s += b + "\n";
        }
        return s;
    }
    
}
