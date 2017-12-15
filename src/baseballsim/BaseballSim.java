package baseballsim;

import java.util.*;
/**
 *
 * @author Quinn
 */
public class BaseballSim {

    //(name, paCount, kCount, bbCount, groundBallCount, lineDriveCount, flyBallCount, hrFb, hrLd,groundBallHits,lineDriveHits,flyBallHits,ld2B, gb2B, fb2B
    private static Lineup lineup = new Lineup()
            .first(new Batter("Jean Segura", 566, 83, 34, 240, 110, 89, 6, 5, 75, 71, 10, 27, 3, 0, true))
            .second(new Batter("Mitch Haniger", 410, 93, 31, 119, 74, 82, 13, 3, 30, 55, 18, 19, 4, 2, true))
            .third(new Batter("Robinson Cano", 648, 85, 49, 256, 145, 119, 20, 3, 60, 76, 30, 19, 6, 8, false))
            .fourth(new Batter("Nelson Cruz", 645, 140, 70, 174, 143, 106, 32, 7, 51, 72, 37, 19, 6, 3, false))
            .fifth(new Batter("Kyle Seager", 650, 110, 58, 151, 104, 214, 25, 2,36, 63, 42, 23, 4, 6, false))
            .sixth(new Batter("Danny Valencia", 500, 122, 40, 163, 85, 88, 11, 4, 43, 57, 15, 16, 3 , 0, false))
            .seventh(new Batter("Mike Zunino", 435, 160, 39, 75, 58, 95, 23, 2, 27, 41, 29, 19, 5 , 1, false))
            .eigth(new Batter("Ben Gamel", 550, 122, 36, 180, 107, 103, 9, 2, 53, 65, 22, 20, 4 , 3, true))
            .ninth(new Batter("Jarrod Dyson", 390, 55, 28, 152, 72, 73, 4, 1, 39, 41, 7, 9, 3 , 1, true))
            .build();
    
    private static ScoreCard scoreCard = new ScoreCard();
    private static BasePath basePath = new BasePath(scoreCard);
    private static int numGames = 162;
    
                
    public static void main(String args[]) {
        
        ArrayList<Integer> results = new ArrayList<>();
        
        //randomly shuffle the batting order
        //lineup.shuffleOrder();

        int totalRuns = 0;
        //simulate some amount of games
        for (int i = 0; i < numGames; i++)    { 
            //randomly shuffle the batting order (commented out unless desired)
            //lineup.shuffleOrder();           
            
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
            totalRuns += scoreCard.getRuns();
            scoreCard.reset();
        }
        System.out.println("+++++++++++");
        System.out.println("Number of Games: " + numGames);
        System.out.println("TotalRuns: " + totalRuns);
        System.out.println("+++++++++++");
        System.out.println(lineup.getBatterLines());
        results.add(totalRuns);
        totalRuns = 0;
    }
    
    
}
