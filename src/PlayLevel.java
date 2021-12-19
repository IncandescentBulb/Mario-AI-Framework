import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import static java.lang.Math.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
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
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("generated_levels.txt");
            //myWriter.write("Files in Java might be tricky, but it is fun enough! badfasfsdf");

            System.out.println();//"Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        while(!fullExit) {
            Hashtable<String, Integer> parameters
                    = new Hashtable<String, Integer>();
            parameters.put("min-width", 85);
            parameters.put("max-width", 150);
            parameters.put("difficulty", 1);
            parameters.put("theme", 0);
            //numbers.put("two", 2);
            //numbers.put("three", 3);
            //int width = 150;
            //int paramLength = 1;
            game.first = true;
            boolean hasLine = false;
            boolean skip = false;
            System.out.println("Enter a string. Enter \"exit\" to exit. Press Enter without input to use default values for the remaining parameters: ");
            Set<String> parametersKeys = parameters.keySet();
            Iterator<String> itr = parametersKeys.iterator();
            String param = "";

           /* if(itr.hasNext()){
                param = itr.next();
            }*/
            boolean invalidInput = false;
            while(itr.hasNext()) {
                if(fullExit || skip){
                    break;
                }else if(!invalidInput){
                    param = itr.next();
                    invalidInput = false;
                }

                //prompt user for each parameter, give ranges/options for values
                switch(param){
                    case "min-width":

                        System.out.print("Min level length (between 60 and 340, default 85): ");
                        break;
                    case "max-width":
                        int max = (int) Math.floor(parameters.get("min-width")*1.8);
                        if (max>400){
                            max = 400;
                        }
                        System.out.print("Max level length (between " + parameters.get("min-width") + " and 400, default " + max + "): ");
                        break;
                    case "difficulty":
                        System.out.print("Difficulty (between 0 and 3, default 1): ");
                        break;
                    case "theme":
                        System.out.print("Level theme (0: default, 1: mushroom, 2: pipes): ");
                        break;
                    default:
                        System.out.println("uh oh");
                }

                do {
                    if (sc.hasNextLine()) {
                        //System.out.println("l;kjasdf");
                        hasLine = true;
                        String str = sc.nextLine();              //reads string

                        //sc.close();
                        if (str == "exit") {
                            System.out.print("Exiting");
                            //i = paramLength;
                            fullExit = true;
                            break;
                        } else if (str == "") {
                            System.out.println("using defaults for remaining parameters");
                            skip = true;
                            if(parameters.get("min-width")>= parameters.get("max-width")){
                                int max = (int) Math.floor(parameters.get("min-width")*1.8);
                                if (max>400){
                                    max = 400;
                                }
                                parameters.replace("max-width", max);
                            }
                            //i = paramLength;
                            break;
                        }else {
                            int temp = 0;
                            //System.out.print("You have entered: " + str);
                            try{
                                temp = Integer.parseInt(str);
                            }catch(NumberFormatException e){
                                System.out.println("invalid input");
                                invalidInput = true;
                                //i--;
                                break;
                            }

                            switch(param){
                                case "min-width":
                                    if(temp >= 60 && temp <=340){
                                        parameters.replace("min-width", temp);
                                    }else{
                                        System.out.println("invalid number");
                                        //i--;
                                        invalidInput = true;
                                        break;
                                    }
                                    break;
                                case "max-width":
                                    if(temp >= parameters.get("min-width") && temp <=400){
                                        parameters.replace("max-width", temp);
                                    }else{
                                        System.out.println("invalid number");
                                        //i--;
                                        invalidInput = true;
                                        break;
                                    }
                                    break;
                                case "difficulty":
                                    if(temp >= 0 && temp <=3){
                                        parameters.replace(param, temp);
                                    }else{
                                        System.out.println("invalid number");
                                        //i--;
                                        invalidInput = true;
                                        break;
                                    }
                                    //System.out.print("Difficulty (between 0 and 5, default 2): ");
                                    break;
                                case "theme":
                                    //System.out.print("Level theme (0 or 1, default 0): ");
                                    if(temp >= 0 && temp <=2){
                                        parameters.replace(param, temp);
                                    }else{
                                        System.out.println("invalid number");
                                        //i--;
                                        invalidInput = true;
                                        break;
                                    }
                                    break;
                                default:
                                    System.out.println("uh oh");
                            }
                            if(invalidInput){
                                break;
                            }
                        }
                        //check values of str, parameters. need to check against possible values.
                    } else {
                        System.out.println("abababababa;lkjbkl");
                    }

                } while (!hasLine);

            }
            if(fullExit){
                break;
            }
            //System.out.println("Max level width: " + width);

            boolean exited = false;
            ///*

            //game.pause = true;
            game.addKey();
            MarioLevelGenerator generator = new levelGenerators.FernandesMahanyMatava.LevelGenerator();
            levelGenerators.FernandesMahanyMatava.LevelGenerator gen = (levelGenerators.FernandesMahanyMatava.LevelGenerator)generator;
            gen.setParameters(parameters);
            //*/
            while (!exited) {

                //MarioGame game = new MarioGame();
                //game.addKey();
                //Milestone 2
                //MarioLevelGenerator generator = new levelGenerators.FernandesMahanyMatava.LevelGenerator();
                //Milestone 1
                //MarioLevelGenerator generator = new levelGenerators.FernandesMahanyMatava1.LevelGenerator();


                String level = generator.getGeneratedLevel(new MarioLevelModel(parameters.get("max-width")+20, 16), new MarioTimer(5 * 60 * 60 * 1000));
                if(myWriter != null) {
                    try {
                        myWriter.write(level + "\n\n\n\n");
                    } catch (IOException e) {
                        System.out.println("An error occurred trying to write level to file.");
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("myWriter is null");
                }

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
        sc.close();
        if(myWriter != null) {
            try {
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred trying to close writer.");
                e.printStackTrace();
            }
        }
    }
}
