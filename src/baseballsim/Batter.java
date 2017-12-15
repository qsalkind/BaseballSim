package baseballsim;

public class Batter {
    private String name;
    private int plateAppearances;
    private int walks;
    private int hbp;
    private int strikeOuts;
    private double kPct;
    private double bbPct;
    private double contactRate;
    private double hbpRate;
    private int kCount;
    private int bbCount;
    private int paCount;
    private int groundBallCount;
    private double gbPct;
    private int lineDriveCount;
    private double ldPct;
    private int flyBallCount;
    private double fbPct;
    
    private double hrFbPct;
    private double hrLdPct;
    
    private int hrFb;
    private int hrLd;
    private int groundBallHits;
    private int lineDriveHits;
    private int flyBallHits;
    
    private double ldHitPct;
    private double gbHitPct;
    private double fbHitPct;
    private double ld2BPct;
    private double gb2BPct;
    private double fb2BPct;
    
    private int homeRuns;
    private int hits;
    private int atBats;
    private int timesOnBase;
    private int totalBases;
    
    private boolean isFast;
            

    public Batter(String name, int paCount, int kCount, int bbCount, int groundBallCount, int lineDriveCount, int flyBallCount, int hrFb, int hrLd, int groundBallHits, int lineDriveHits, int flyBallHits, double ld2B, double gb2B, double fb2B, boolean isFast) {
        this.name = name;
        this.isFast = isFast;
        
        this.paCount = paCount;
        this.kCount = kCount;
        this.bbCount = bbCount;
        this.groundBallCount = groundBallCount;
        this.lineDriveCount = lineDriveCount;
        this.flyBallCount = flyBallCount;
        
                
        this.groundBallHits = groundBallHits;
        this.lineDriveHits = lineDriveHits;  //no HRs
        this.flyBallHits = flyBallHits; //no HRs
        this.hrFb = hrFb;
        this.hrLd = hrLd;
        this.hrFbPct = (double)hrFb/(double)flyBallCount;
        this.hrLdPct = (double)hrLd/(double)lineDriveCount;
        
        this.ld2BPct = (double)ld2B/(double)lineDriveHits;
        this.gb2BPct = (double)gb2B/(double)groundBallHits;
        this.fb2BPct = (double)fb2B/(double)flyBallHits;
        
        int contactCount = groundBallCount + lineDriveCount + flyBallCount;
        
        ldPct = (double)lineDriveCount/(double)contactCount;
        ldHitPct = (double)lineDriveHits/(double)lineDriveCount;
        gbPct = (double)groundBallCount/(double)contactCount;
        gbHitPct = (double)groundBallHits/(double)groundBallCount;
        fbPct = (double)flyBallCount/(double)contactCount;
        fbHitPct = (double)flyBallHits/(double)flyBallCount;
        kPct = (double)kCount/(double)paCount;
        bbPct = (double)bbCount/(double)paCount;
        contactRate = 1 - kPct - bbPct;
        hbp = 0;
        hbpRate = 0.008;
        plateAppearances = 0;
        walks = 0;
        strikeOuts = 0;
        homeRuns = 0;
        hits = 0;
        atBats = 0;
        timesOnBase = 0;
        totalBases = 0;
    }
    
    public String getName() {
        return name;
    }
    
    /**
     * Based of off the batters statistics, this returns a PAResult enum indicating
     * the result of the plate appearance using RNG.
     * @return 
     */
    public PAResult takeAtBat() {
        double randomChance = Math.random();
        plateAppearances++;
        log("");
        if (randomChance <= bbPct) {
            walks++;
            timesOnBase++;
            log(name + " walks");
            return PAResult.WALK;
        }
        if (randomChance > bbPct && randomChance < (bbPct+kPct)) {
            strikeOuts++;
            log(name + " strikes out");
            atBats++;
            return PAResult.STRIKEOUT;
        }
        if (randomChance >= (bbPct+kPct) && randomChance < (bbPct+kPct+hbpRate)) {
            hbp++;
            timesOnBase++;
            log( name + " is hit by the pitch!");
            return PAResult.HBP;
        }
        if (randomChance >= (bbPct+kPct+hbpRate)) {
            log(name + " makes contact!");
            atBats++;
            double randomChance2 = Math.random();
            double randomChance3 = Math.random();
            //grounder
            if (randomChance2 <= gbPct) {
                log("> ground ball");
                randomChance = Math.random();
                if (randomChance < gbHitPct) {
                    log( " > its a hit!");
                    if (randomChance3 < gb2BPct) {
                        log("  > DOUBLE!");
                        totalBases++;
                        return PAResult.DOUBLE;
                    }
                    hits++;
                    totalBases++;
                    timesOnBase++;
                }
                else {
                    log(" > out");
                    return PAResult.GROUNDOUT;
                }
            }
            //linedrive
            if (randomChance2 <= gbPct+ldPct && randomChance2 > gbPct) {
                log("> line drive");
                randomChance = Math.random();
                if (randomChance < hrLdPct) {
                    log(" > HOMERUN!!");
                    totalBases += 4;
                    homeRuns++;
                    hits++;
                    timesOnBase++;
                    return PAResult.HOMERUN;
                }
                if (randomChance < (hrLdPct + ldHitPct) && randomChance >= hrLdPct) {
                    log(" > it's a hit!");
                    if (randomChance3 < ld2BPct) {
                        log("  > DOUBLE!");
                        totalBases++;
                        return PAResult.DOUBLE;
                    }
                    hits++;
                    totalBases++;
                    timesOnBase++;
                    return PAResult.SINGLE;
                }
                if (randomChance > (hrLdPct+ldHitPct)) {
                    log(" > out");
                    return PAResult.LINEOUT;
                }
            }
            //flyball
            if (randomChance2 > gbPct+ldPct) {
                log("> fly ball");
                randomChance = Math.random();
                if (randomChance < hrFbPct) {
                    log(" > HOMERUN!!");
                    homeRuns++;
                    hits++;
                    totalBases+=4;
                    timesOnBase++;
                    return PAResult.HOMERUN;
                }
                if (randomChance > hrFbPct && randomChance <= (hrFbPct+fbHitPct)) {
                    log(" > it's a hit!");
                    if (randomChance3 < fb2BPct) {
                        log("  > DOUBLE!");
                        totalBases++;
                        return PAResult.DOUBLE;
                    }
                    hits++;
                    totalBases++;
                    timesOnBase++;
                    return PAResult.SINGLE;
                }
                if (randomChance > (hrFbPct+fbHitPct)) {
                    log(" > out");
                    return PAResult.FLYOUT;
                }
            }
            
        }
        return PAResult.LINEOUT;     
    }
    
    private static void log(String s) {
        System.out.println(s);
    }
    
    public int getPlateAppearances() {
        return plateAppearances;
    }
    
    public int getHomeRuns() {
        return homeRuns;
    }
    
    public int getHits() {
        return hits;
    }
    
    public int getAtBats() {
        return atBats;
    }
    
    public void indicateSacHit() {
        atBats -= 1;
    }
    
    public int getTimesOnBase() {
        return timesOnBase;
    }
    
    public boolean isFast() {
        return isFast;
    }
    
    @Override
    public String toString() {
        String output = "";
        double battingAverage = (double)hits/(double)atBats;
        double onBasePct = (double)timesOnBase/(double)plateAppearances;
        double slugging = (double)totalBases/(double)atBats;
        output += name + ", Hits: " + hits + ", HR: " + homeRuns + " TB: " + totalBases;
        output += " BA: " + battingAverage + ", OBP: " + onBasePct;
        output += " OPS: " + (onBasePct+slugging);
        return output;
    }
}