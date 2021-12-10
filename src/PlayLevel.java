import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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
        //System.out.print(args.length);
        Scanner sc = new Scanner(System.in);
        boolean fullExit = false;
        MarioGame game = new MarioGame();

        while(!fullExit) {
            game.first = true;
            boolean hasLine = false;
            System.out.print("Enter a string: ");
            do {


                if(sc.hasNextLine()) {
                    //System.out.println("l;kjasdf");
                    hasLine = true;
                    String str = sc.nextLine();              //reads string

                    //sc.close();
                    if (str == "") {
                        System.out.print("Exiting");
                        fullExit = true;
                        break;
                    }else{
                        System.out.print("You have entered: " + str);
                    }
                }else{

                }

            }while(!hasLine);
            if(fullExit){
                break;
            }

            boolean exited = false;
            ///*

            //game.pause = true;
            game.addKey();
            MarioLevelGenerator generator = new levelGenerators.FernandesMahanyMatava.LevelGenerator();
            //*/
            while (!exited) {

                //MarioGame game = new MarioGame();
                //game.addKey();
                //Milestone 2
                //MarioLevelGenerator generator = new levelGenerators.FernandesMahanyMatava.LevelGenerator();
                //Milestone 1
                //MarioLevelGenerator generator = new levelGenerators.FernandesMahanyMatava1.LevelGenerator();


                String level = generator.getGeneratedLevel(new MarioLevelModel(150, 16), new MarioTimer(5 * 60 * 60 * 1000));


                //System.out.println(game.pause);
                //String level = getLevel("./levels/original/lvl-1.txt");//, 200, 0));
                do {
                    printResults(game.playGame(level, 200, 0));
                    //printResults(game.runGame(new agents.robinBaumgarten.Agent(), getLevel("./levels/original/lvl-1.txt"), 20, 0, true));
                    // try {
                    while (!game.getEnded() && !exited) {
                        //System.out.println(";flag");
                        if (game.getExited()) {
                            exited = true;
                            //System.out.println(";lkjasdf");
                            break;
                        }
                        if (game.getEnded()) {
                            //System.out.println("flaggg");
                        }
                        if(game.getRestarting()){
                            break;
                        }
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            break;
                        }
                        //System.out.println("lkj;adsklj");
                        //System.out.println(game.getEnded());
                    }
                    if (!exited) {
                        System.out.println("new level");
                    }
                    System.out.println("exited: " + exited);
                    System.out.println(game.getRestarting());
                }while(game.getRestarting());

               /* } catch () {
                    e.printStackTrace();
                    System.out.println("KLjlkj");
                }
                System.out.println("flag2");*/
            }
        }
    }
}
