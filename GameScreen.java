package edu.angelo.FinalProjectBlockyJumpHoward;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class GameScreen extends Screen {
    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }

    GameState state = GameState.Ready;
    World world;
    int oldScore = 0;
    String score = "0";
    TargetSet target = new TargetSet();
    public GameScreen(Game game) {
        super(game);
        world = new World();
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        if(state == GameState.Ready)
            updateReady(touchEvents);
        if(state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if(state == GameState.Paused)
            updatePaused(touchEvents);
        if(state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        if(touchEvents.size() > 0)
            state = GameState.Running;
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_DOWN) {
               // world.character.slide = true;
                if(event.x <= 200 && event.y <= 250)
                {
                    if(!world.character.jump && !world.character.fall && !world.character.slide) //doesnt work if already jumping
                    {
                    world.character.jump = true;
                    world.character.fall = false;
                    world.character.gravityX = 20;
                    }
                }
                else if(event.x <= 200 && event.y > 250)
                {
                    if(!world.character.jump && !world.character.fall)//if charcter is already in an action can not do another action
                    world.character.slide = true;
                }

                //world.character.slide = true;

            }
            if(event.type == TouchEvent.TOUCH_UP) {

                if (event.x > 250 && event.x <= 315) {
                    if (event.y > 200 && event.y <= 280) {

                        if (Settings.soundEnabled)
                            Assets.paused.play(1);
                        state = GameState.Paused;
                        return;
                    }
                }
            }
        }

        world.update(deltaTime);
        if(world.gameOver) {
            if(Settings.soundEnabled)
                Assets.bitten.play(1);
            state = GameState.GameOver;
        }
        if(oldScore < world.score) {
            oldScore = world.score;
            score = "" + oldScore;
            /*if(Settings.soundEnabled)
                Assets.eat.play(1);*/
        }
        else
        {
            oldScore = world.score;
            score = "" + oldScore;
        }
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > 120 && event.x <=190) {
                    if (event.y > 100 && event.y <= 250) {
                        if (Settings.soundEnabled)
                            Assets.click.play(1);
                        state = GameState.Running;
                        return;
                    }
                }
                    if(event.x < 120 && event.x > 50) {
                        if (event.y > 100 && event.y <= 250) {
                            if (Settings.soundEnabled)
                                Assets.click.play(1);
                            Settings.addScore(world.score);
                            Settings.save(game.getFileIO());
                            game.setScreen(new MainMenuScreen(game));
                            return;
                        }
                    }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {

                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }


    @Override
    public void present(float deltaTime) {

        Graphics g = game.getGraphics();
        int width = g.getWidth();
        int height = g.getHeight();
        g.drawPixmap(Assets.background, 0, 0);

        drawWorld(world);
        if(state == GameState.Ready)
            drawReadyUI();
        if(state == GameState.Running)
            drawRunningUI();
        if(state == GameState.Paused)
            drawPausedUI();
        if(state == GameState.GameOver)
            drawGameOverUI();

        drawText(g, score, 290, 0); //g.getWidth() - score.length()*20, 0)
    }


    private void drawWorld(World world) {
        Graphics g = game.getGraphics();


        int width = g.getWidth();
        int height = g.getHeight();
        //sets the background color to a sky blue




        //draws the character either in a slide position or a standing position
        if(!world.character.slide)
        {
            g.drawPixmap(Assets.blocky,world.character.locationX, world.character.locationY);
        }
        else
        {
            g.drawPixmap(Assets.blockyslide,world.character.locationX, world.character.locationY);
        }

        //draws the different types of objects either one that is on the floor or floating in the air
        for(int i = 0; i < world.obstacleSet.obstacles.size(); i++)
        {
            if(world.obstacleSet.obstacles.get(i).type == 1)
            {
                g.drawPixmap(Assets.spike, world.obstacleSet.obstacles.get(i).locationX,  world.obstacleSet.obstacles.get(i).locationY  );
            }
            else
            {
                g.drawPixmap(Assets.spikedown, world.obstacleSet.obstacles.get(i).locationX ,  world.obstacleSet.obstacles.get(i).locationY -16 );
            }

        }




    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.ready, 60, 150);

    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.buttons2, 260, 210, 64, 128, 64, 64);

    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.pause, 80, 100);

    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.gameOver, 100, 120);
       // g.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);

    }

    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 420;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 40;
                y += 40;
                x -= 30;
                srcWidth = 30; //draws the score on the screen vertically rather than horizontally to match up with the screen better
            }

            g.drawPixmap(Assets.blacknumbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }

    @Override
    public void pause() {
        if(state == GameState.Running)
            state = GameState.Paused;

        if(world.gameOver) {
            Settings.addScore(world.score);
            Settings.save(game.getFileIO());
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
