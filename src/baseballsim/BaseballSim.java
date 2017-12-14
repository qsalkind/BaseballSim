package baseballsim;

import java.util.*;
/**
 *
 * @author Quinn
 */
public class BaseballSim {

    //(name, paCount, kCount, bbCount, groundBallCount, lineDriveCount, flyBallCount, hrFb, hrLd,groundBallHits,lineDriveHits,flyBallHits,ld2B, gb2B, fb2B
    private static Lineup lineup = new Lineup()
            .first(new Batter("Jean Segura", 566, 83, 34, 240, 110, 89, 6, 5, 75, 71, 10, 27, 3, 0))
            .second(new Batter("Mitch Haniger", 410, 93, 31, 119, 74, 82, 13, 3, 30, 55, 18, 19, 4, 2))
            .third(new Batter("Robinson Cano", 648, 85, 49, 256, 145, 119, 20, 3, 60, 76, 30, 19, 6, 8))
            .fourth(new Batter("Nelson Cruz", 645, 140, 70, 174, 143, 106, 32, 7, 51, 72, 37, 19, 6, 3))
            .fifth(new Batter("Kyle Seager", 650, 110, 58, 151, 104, 214, 25, 2,36, 63, 42, 23, 4, 6))
            .sixth(new Batter("Metch Haniger", 410, 93, 31, 119, 74, 82, 13, 3, 30, 55, 18, 19, 4 , 2))
            .seventh(new Batter("Mitch Hanigoat", 410, 93, 31, 119, 74, 82, 13, 3, 30, 55, 18, 19, 4 , 2))
            .eigth(new Batter("Goat Haniger", 410, 93, 31, 119, 74, 82, 13, 3, 30, 55, 18, 19, 4 , 2))
            .ninth(new Batter("Mitch Hooger", 410, 93, 31, 119, 74, 82, 13, 3, 30, 55, 18, 19, 4 , 2))
            .build();
    
    private static ScoreCard scoreCard = new ScoreCard();
    private static BasePath basePath = new BasePath(scoreCard);
    private static int numGames = 162;
                
    public static void main(String args[]) {
        
            int totalRuns = 0;
            //simulate some amount of games
            for (int i = 0; i < numGames; i++)    { 
                //simulate a game
                while (scoreCard.getInning() < 10) {
                    if (scoreCard.getCurrentOuts() < 3) {
                        System.out.println("============");
                        System.out.println(lineup);
                        System.out.println(scoreCard);
                        System.out.println("Runners: " +  basePath);
                        System.out.println("============");
                        Batter b = lineup.getCurrentBatter();
                        basePath.handlePAResult(b, b.takeAtBat());
                        lineup.incrimentCurrentBatter();
                        System.out.println("\n\n");
                    } else {
                        System.out.println("End of inning: " + scoreCard.getInning());
                        scoreCard.incrimentInning();
                        scoreCard.clearOuts();
                        basePath.clearBasePath();
                    }
                    
                }   
                System.out.println("Game " + (i+1) + " has finsihed with " + scoreCard.getRuns() + " runs");
                //System.out.println("+++++++++++");
                //System.out.println("Game Total: " + scoreCard.getRuns());
                totalRuns += scoreCard.getRuns();
                //System.out.println("+++++++++++");
                //System.out.println(lineup.getBatterLines());
                scoreCard.reset();
            }
            System.out.println("+++++++++++");
            System.out.println("Number of Games: " + numGames);
            System.out.println("TotalRuns: " + totalRuns);
            System.out.println("+++++++++++");
            System.out.println(lineup.getBatterLines());
        }
    
}
