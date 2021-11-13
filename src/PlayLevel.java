import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import engine.core.*;

public class PlayLevel {
    public static void printResults(MarioResult result) {
        System.out.println("****************************************************************");
        System.out.println("Game Status: " + result.getGameStatus().toString() +
                " Percentage Completion: " + result.getCompletionPercentage());
        System.out.println("Lives: " + result.getCurrentLives() + " Coins: " + result.getCurrentCoins() +
                " Remaining Time: " + (int) Math.ceil(result.getRemainingTime() / 1000f));
        System.out.println("Mario State: " + result.getMarioMode() +
                " (Mushrooms: " + result.getNumCollectedMushrooms() + " Fire Flowers: " + result.getNumCollectedFireflower() + ")");
        System.out.println("Total Kills: " + result.getKillsTotal() + " (Stomps: " + result.getKillsByStomp() +
                " Fireballs: " + result.getKillsByFire() + " Shells: " + result.getKillsByShell() +
                " Falls: " + result.getKillsByFall() + ")");
        System.out.println("Bricks: " + result.getNumDestroyedBricks() + " Jumps: " + result.getNumJumps() +
                " Max X Jump: " + result.getMaxXJump() + " Max Air Time: " + result.getMaxJumpAirTime());
        System.out.println("****************************************************************");
    }

    public static String getLevel(String filepath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (IOException e) {
        }
        return content;
    }

    public static void main(String[] args) {
        //System.out.println("flag!");
        boolean exited = false;
        while(!exited){
            MarioGame game = new MarioGame();
            game.addKey();
            MarioLevelGenerator generator = new levelGenerators.FernandesMahanyMatava.LevelGenerator();
            String level = generator.getGeneratedLevel(new MarioLevelModel(150, 16), new MarioTimer(5 * 60 * 60 * 1000));
            //String level = getLevel("./levels/original/lvl-3.txt");//, 200, 0));
            printResults(game.playGame(level, 200, 0));
            //printResults(game.runGame(new agents.robinBaumgarten.Agent(), getLevel("./levels/original/lvl-1.txt"), 20, 0, true));
           // try {
                while(!game.getEnded()){
                    if(game.getExited()){
                        exited = true;
                        break;
                    }
                }
                if(!exited) {
                    //System.out.println("restarting");
                }

           /* } catch () {
                e.printStackTrace();
                System.out.println("KLjlkj");
            }
            System.out.println("flag2");*/
        }
    }
}
