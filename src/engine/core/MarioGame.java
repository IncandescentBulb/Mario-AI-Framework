package engine.core;

import java.awt.event.KeyEvent;
import java.awt.image.VolatileImage;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.*;

import javax.swing.JFrame;

import agents.human.Agent;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class MarioGame {
    /**
     * the maximum time that agent takes for each step
     */
    public static final long maxTime = 40;
    /**
     * extra time before reporting that the agent is taking more time that it should
     */
    public static final long graceTime = 10;
    /**
     * Screen width
     */
    public static final int width = 256;
    /**
     * Screen height
     */
    public static final int height = 256;
    /**
     * Screen width in tiles
     */
    public static final int tileWidth = width / 16;
    /**
     * Screen height in tiles
     */
    public static final int tileHeight = height / 16;
    /**
     * print debug details
     */
    public static final boolean verbose = false;

    /**
     * pauses the whole game at any moment
     */
    public boolean pause = false;

    /**
     * events that kills the player when it happens only care about type and param
     */
    private MarioEvent[] killEvents;

    //visualization
    public boolean first = true;
    private JFrame window = null;
    private MarioRender render = null;
    private MarioAgent agent = null;
    private MarioWorld world = null;
    private boolean ended = false;
    private boolean exited = false;
    private boolean restarting = false;
    public KeyAdapter waitFor = null;

    /**
     * Create a mario game to be played
     */
    public MarioGame() {

    }

    /**
     * Create a mario game with a different forward model where the player on certain event
     *
     * @param killEvents events that will kill the player
     */
    public MarioGame(MarioEvent[] killEvents) {
        this.killEvents = killEvents;
    }

    private int getDelay(int fps) {
        if (fps <= 0) {
            return 0;
        }
        return 1000 / fps;
    }
    public void lose(){
        this.world.lose();
    }

    private void setAgent(MarioAgent agent) {
        this.agent = agent;
        if (agent instanceof KeyAdapter) {
            this.render.addKeyListener((KeyAdapter) this.agent);
        }
        if(this.waitFor != null){
            this.render.addKeyListener(this.waitFor);
        }
    }
    private class WaitForKey extends KeyAdapter{
        private MarioGame game;
        public WaitForKey(MarioGame g){
            game = g;
        }
        @Override
        public void keyPressed(KeyEvent e) {
            toggleKey(e.getKeyCode(), true);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            toggleKey(e.getKeyCode(), false);
        }

        private void toggleKey(int keyCode, boolean isPressed) {
            switch (keyCode) {
                case KeyEvent.VK_ENTER: // generate new level
                    game.lose();
                    System.out.println("enter");
                    game.ended = true;
                    game.closeWindow();
                    break;
                case KeyEvent.VK_ESCAPE: // go back to input parameters
                    game.lose();
                    exited = true;
                    game.closeWindow();
                    break;
                case KeyEvent.VK_BACK_SPACE: // restart with same level
                    game.lose();
                    restarting = true;
                    game.closeWindow();
                    break;
                case KeyEvent.VK_DEAD_TILDE:
                    System.out.println("tilde");
                    game.lose();

                    //restarting = true;
                    //game.closeWindow();
                    break;
            }
        }
    }
    public void closeWindow(){
        this.window.dispose();
    }
    public void addKey(){
        this.waitFor = new WaitForKey(this);
    }
    public boolean getEnded(){
        return ended;
    }
    public boolean getExited(){
        return exited;
    }
    public boolean getRestarting() { return restarting;}

    /**
     * Play a certain mario level
     *
     * @param level a string that constitutes the mario level, it uses the same representation as the VGLC but with more details. for more details about each symbol check the json file in the levels folder.
     * @param timer number of ticks for that level to be played. Setting timer to anything &lt;=0 will make the time infinite
     * @return statistics about the current game
     */
    public MarioResult playGame(String level, int timer) {
        return this.runGame(new Agent(), level, timer, 0, true, 30, 2);
    }

    /**
     * Play a certain mario level
     *
     * @param level      a string that constitutes the mario level, it uses the same representation as the VGLC but with more details. for more details about each symbol check the json file in the levels folder.
     * @param timer      number of ticks for that level to be played. Setting timer to anything &lt;=0 will make the time infinite
     * @param marioState the initial state that mario appears in. 0 small mario, 1 large mario, and 2 fire mario.
     * @return statistics about the current game
     */
    public MarioResult playGame(String level, int timer, int marioState) {
        return this.runGame(new Agent(), level, timer, marioState, true, 30, 2);
    }

    /**
     * Play a certain mario level
     *
     * @param level      a string that constitutes the mario level, it uses the same representation as the VGLC but with more details. for more details about each symbol check the json file in the levels folder.
     * @param timer      number of ticks for that level to be played. Setting timer to anything &lt;=0 will make the time infinite
     * @param marioState the initial state that mario appears in. 0 small mario, 1 large mario, and 2 fire mario.
     * @param fps        the number of frames per second that the update function is following
     * @return statistics about the current game
     */
    public MarioResult playGame(String level, int timer, int marioState, int fps) {
        return this.runGame(new Agent(), level, timer, marioState, true, fps, 2);
    }

    /**
     * Play a certain mario level
     *
     * @param level      a string that constitutes the mario level, it uses the same representation as the VGLC but with more details. for more details about each symbol check the json file in the levels folder.
     * @param timer      number of ticks for that level to be played. Setting timer to anything &lt;=0 will make the time infinite
     * @param marioState the initial state that mario appears in. 0 small mario, 1 large mario, and 2 fire mario.
     * @param fps        the number of frames per second that the update function is following
     * @param scale      the screen scale, that scale value is multiplied by the actual width and height
     * @return statistics about the current game
     */
    public MarioResult playGame(String level, int timer, int marioState, int fps, float scale) {
        return this.runGame(new Agent(), level, timer, marioState, true, fps, scale);
    }

    /**
     * Run a certain mario level with a certain agent
     *
     * @param agent the current AI agent used to play the game
     * @param level a string that constitutes the mario level, it uses the same representation as the VGLC but with more details. for more details about each symbol check the json file in the levels folder.
     * @param timer number of ticks for that level to be played. Setting timer to anything &lt;=0 will make the time infinite
     * @return statistics about the current game
     */
    public MarioResult runGame(MarioAgent agent, String level, int timer) {
        return this.runGame(agent, level, timer, 0, false, 0, 2);
    }

    /**
     * Run a certain mario level with a certain agent
     *
     * @param agent      the current AI agent used to play the game
     * @param level      a string that constitutes the mario level, it uses the same representation as the VGLC but with more details. for more details about each symbol check the json file in the levels folder.
     * @param timer      number of ticks for that level to be played. Setting timer to anything &lt;=0 will make the time infinite
     * @param marioState the initial state that mario appears in. 0 small mario, 1 large mario, and 2 fire mario.
     * @return statistics about the current game
     */
    public MarioResult runGame(MarioAgent agent, String level, int timer, int marioState) {
        return this.runGame(agent, level, timer, marioState, false, 0, 2);
    }

    /**
     * Run a certain mario level with a certain agent
     *
     * @param agent      the current AI agent used to play the game
     * @param level      a string that constitutes the mario level, it uses the same representation as the VGLC but with more details. for more details about each symbol check the json file in the levels folder.
     * @param timer      number of ticks for that level to be played. Setting timer to anything &lt;=0 will make the time infinite
     * @param marioState the initial state that mario appears in. 0 small mario, 1 large mario, and 2 fire mario.
     * @param visuals    show the game visuals if it is true and false otherwise
     * @return statistics about the current game
     */
    public MarioResult runGame(MarioAgent agent, String level, int timer, int marioState, boolean visuals) {
        return this.runGame(agent, level, timer, marioState, visuals, visuals ? 30 : 0, 2);
    }

    /**
     * Run a certain mario level with a certain agent
     *
     * @param agent      the current AI agent used to play the game
     * @param level      a string that constitutes the mario level, it uses the same representation as the VGLC but with more details. for more details about each symbol check the json file in the levels folder.
     * @param timer      number of ticks for that level to be played. Setting timer to anything &lt;=0 will make the time infinite
     * @param marioState the initial state that mario appears in. 0 small mario, 1 large mario, and 2 fire mario.
     * @param visuals    show the game visuals if it is true and false otherwise
     * @param fps        the number of frames per second that the update function is following
     * @return statistics about the current game
     */
    public MarioResult runGame(MarioAgent agent, String level, int timer, int marioState, boolean visuals, int fps) {
        return this.runGame(agent, level, timer, marioState, visuals, fps, 2);
    }

    /**
     * Run a certain mario level with a certain agent
     *
     * @param agent      the current AI agent used to play the game
     * @param level      a string that constitutes the mario level, it uses the same representation as the VGLC but with more details. for more details about each symbol check the json file in the levels folder.
     * @param timer      number of ticks for that level to be played. Setting timer to anything &lt;=0 will make the time infinite
     * @param marioState the initial state that mario appears in. 0 small mario, 1 large mario, and 2 fire mario.
     * @param visuals    show the game visuals if it is true and false otherwise
     * @param fps        the number of frames per second that the update function is following
     * @param scale      the screen scale, that scale value is multiplied by the actual width and height
     * @return statistics about the current game
     */
    public MarioResult runGame(MarioAgent agent, String level, int timer, int marioState, boolean visuals, int fps, float scale) {
        if (visuals) {
            this.ended = false;
            this.exited = false;
            this.restarting = false;
            this.window = new JFrame("Mario AI Framework");
            this.render = new MarioRender(scale);
            this.window.setContentPane(this.render);
            this.window.pack();
            this.window.setResizable(false);
            this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.render.init();
            this.window.setVisible(true);
            this.window.setAlwaysOnTop(true);
            this.window.setAlwaysOnTop(false);
            this.pause = false;
            //pause = true;
            //this.window.requestFocus();
            //this.render.requestFocusInWindow();
        }
        this.setAgent(agent);

        return this.gameLoop(level, timer, marioState, visuals, fps);
    }

    private MarioResult gameLoop(String level, int timer, int marioState, boolean visual, int fps) {
        this.window.addWindowFocusListener(new WindowAdapter() {

            //To check window gained focus
            public void windowGainedFocus(WindowEvent e) {
                //set flag

                pause = false;
            }
            //To check window lost focus
            public void windowLostFocus(WindowEvent e) {
                //set flag
                //System.out.println("window lost focus");
                pause = true;
            }
        });


        this.render.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                pause = false;
            }
        });
        this.world = new MarioWorld(this.killEvents);
        this.world.visuals = visual;
        this.world.initializeLevel(level, 1000 * timer);
        if (visual) {
            this.world.initializeVisuals(this.render.getGraphicsConfiguration());
        }
        this.world.mario.isLarge = marioState > 0;
        this.world.mario.isFire = marioState > 1;
        this.world.update(new boolean[MarioActions.numberOfActions()]);
        long currentTime = System.currentTimeMillis();

        //initialize graphics
        VolatileImage renderTarget = null;
        Graphics backBuffer = null;
        Graphics currentBuffer = null;
        if (visual) {
            renderTarget = this.render.createVolatileImage(MarioGame.width, MarioGame.height);
            backBuffer = this.render.getGraphics();
            currentBuffer = renderTarget.getGraphics();
            this.render.addFocusListener(this.render);
            this.window.addWindowFocusListener(new WindowAdapter() {
                public void windowGainedFocus(WindowEvent e) {
                    render.requestFocusInWindow();
                }
            });
        }

        MarioTimer agentTimer = new MarioTimer(MarioGame.maxTime);
        this.agent.initialize(new MarioForwardModel(this.world.clone()), agentTimer);

        ArrayList<MarioEvent> gameEvents = new ArrayList<>();
        ArrayList<MarioAgentEvent> agentEvents = new ArrayList<>();
        if(first){
            pause = true;
            first = false;
        }
        while (this.world.gameStatus == GameStatus.RUNNING) {

            if (!this.pause/* && this.render.focused*/) {
                //get actions
                agentTimer = new MarioTimer(MarioGame.maxTime);
                boolean[] actions = this.agent.getActions(new MarioForwardModel(this.world.clone()), agentTimer);
                if (MarioGame.verbose) {
                    if (agentTimer.getRemainingTime() < 0 && Math.abs(agentTimer.getRemainingTime()) > MarioGame.graceTime) {
                        System.out.println("The Agent is slowing down the game by: "
                                + Math.abs(agentTimer.getRemainingTime()) + " msec.");
                    }
                }
                // update world
                this.world.update(actions);
                gameEvents.addAll(this.world.lastFrameEvents);
                agentEvents.add(new MarioAgentEvent(actions, this.world.mario.x,
                        this.world.mario.y, (this.world.mario.isLarge ? 1 : 0) + (this.world.mario.isFire ? 1 : 0),
                        this.world.mario.onGround, this.world.currentTick));
            }

            //render world
            if (visual) {
                this.render.renderWorld(this.world, renderTarget, backBuffer, currentBuffer);
            }
            //check if delay needed
            if (this.getDelay(fps) > 0) {
                try {
                    currentTime += this.getDelay(fps);
                    Thread.sleep(Math.max(0, currentTime - System.currentTimeMillis()));
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        return new MarioResult(this.world, gameEvents, agentEvents);
    }
}
