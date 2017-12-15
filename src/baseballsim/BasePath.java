/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseballsim;

/**
 *
 * @author Quinn
 */
public class BasePath {
    private Batter first;
    private Batter second;
    private Batter third;
    private ScoreCard scoreCard;
    //ScoreBox
    
    public BasePath(ScoreCard s) {
        first = null;
        second = null;
        third = null;
        scoreCard = s;       
    }
    
    /**
     * Handle the results of a certain batters plate appearance result, and update
     * the base paths and scorecard accordingly.
     * @param batter
     * @param result 
     */
    public void handlePAResult(Batter batter, PAResult result) {
        
        double randomChance = Math.random();
        switch (result) {
            case SINGLE:
                if (third != null) {
                    scoreCard.addRuns(1);
                    third = null;
                }
                if (second != null) {
                    if (second.isFast() && Math.random() < 0.2) {
                        scoreCard.addRuns(1);
                    } else {
                        third = second;
                    second = null;
                    }
                }
                if (first != null) {
                    if (first.isFast() && third == null && Math.random() < 0.2) {
                        third = first;
                    } else {
                        second = first;
                    }
                    first = null;
                }
                first = batter;
                break;
            case DOUBLE:
                if (third != null) {
                    scoreCard.addRuns(1);
                    third = null;
                }
                if (second != null) {
                    scoreCard.addRuns(1);
                    second = null;
                }
                if (first != null) {
                    //runner may score from first in if they are fast
                    if (first.isFast() && Math.random() < 0.2) {
                        scoreCard.addRuns(1);
                    } else {
                        third = first;
                    }
                    first = null;
                }
                second = batter;
                break;
            case TRIPLE:
                if (third != null) {
                    scoreCard.addRuns(1);
                    third = null;
                }
                if (second != null) {
                    scoreCard.addRuns(1);
                    second = null;
                }
                if (first != null) {
                    scoreCard.addRuns(1);
                    first = null;
                }
                third = batter;
                //
                break;
            case HOMERUN:
                scoreCard.addRuns(1 + getNumRunnersOn());
                first = null;
                second = null;
                third = null;
                break;
            case FLYOUT:
                scoreCard.addOut();
                if (third!= null && randomChance < 0.4) {
                    scoreCard.addRuns(1);
                    batter.indicateSacHit();
                }
                if (second != null) {
                    //chance of advancing, negligible for now
                }
                if (first != null) {
                    //chance of advancing, negligible for now
                }
                break;
            case LINEOUT:
                scoreCard.addOut();
                if (third!= null && randomChance < 0.25) {
                    scoreCard.addRuns(1);
                    batter.indicateSacHit();
                }
                if (second != null) {
                    //Small chance of runner advancing, not enough data
                }
                if (first != null) {
                    //VERY small chance of advancing, not enough data
                }
                break;
            case WALK:
                if (first != null) {
                    if (second != null) {
                        if (third != null) {
                            scoreCard.addRuns(1);
                            third = null;
                        }
                        third = second;
                    }
                    second = first;
                }
                first = batter;
                break;
            case GROUNDOUT:
                scoreCard.addOut();
                if (first != null) {
                    scoreCard.addOut();
                }
                break;
            case HBP:
                if (first != null) {
                    if (second != null) {
                        if (third != null) {
                            scoreCard.addRuns(1);
                            third = null;
                        }
                        third = second;
                    }
                    second = first;
                }
                first = batter;
                break;
            case STRIKEOUT:
                scoreCard.addOut();
            default:
                break;
        }
                
        
    }
    
    public int getNumRunnersOn() {
        int count = 0;
        if (first != null) count++;
        if (second != null) count++;
        if (third != null) count++;
        return count;
    }
    
    public void clearBasePath() {
        first = null;
        second = null;
        third = null;
    }
    
    @Override 
    public String toString() {
        return "First: " + first + ", Second: " + second + " , Third: " + third;
    }
}
