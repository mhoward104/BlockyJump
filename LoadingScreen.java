package edu.angelo.FinalProjectBlockyJumpHoward;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
        //Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
        Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
        Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
        Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
        Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
        Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
        Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
        Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
        Assets.pause = g.newPixmap("pausemenu.png", PixmapFormat.ARGB4444);
        Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
        Assets.headUp = g.newPixmap("headup.png", PixmapFormat.ARGB4444);
        Assets.headLeft = g.newPixmap("headleft.png", PixmapFormat.ARGB4444);
        Assets.headDown = g.newPixmap("headdown.png", PixmapFormat.ARGB4444);
        Assets.headRight = g.newPixmap("headright.png", PixmapFormat.ARGB4444);
        Assets.tail = g.newPixmap("tail.png", PixmapFormat.ARGB4444);
        Assets.stain1 = g.newPixmap("stain1.png", PixmapFormat.ARGB4444);
        Assets.stain2 = g.newPixmap("stain2.png", PixmapFormat.ARGB4444);
        Assets.stain3 = g.newPixmap("stain3.png", PixmapFormat.ARGB4444);
        Assets.target = g.newPixmap("target.png", PixmapFormat.ARGB4444);
        Assets.running = g.newPixmap("running.png", PixmapFormat.ARGB4444);
        Assets.slide = g.newPixmap("slide.png", PixmapFormat.ARGB4444);
        Assets.backgroundmenu = g.newPixmap("background Menu.png", PixmapFormat.ARGB4444);
        Assets.buttons2 = g.newPixmap("buttons2.png", PixmapFormat.ARGB4444);
        Assets.blocky = g.newPixmap("blocky.png", PixmapFormat.ARGB4444);
        Assets.blockyslide = g.newPixmap("blockyslide.png", PixmapFormat.ARGB4444);
        Assets.numbers2 = g.newPixmap("numbers2.png", PixmapFormat.ARGB4444);
        Assets.blacknumbers = g.newPixmap("blacknumbers.png", PixmapFormat.ARGB4444);
        Assets.spike = g.newPixmap("spike.png", PixmapFormat.ARGB4444);
        Assets.spikedown = g.newPixmap("spikedown.png", PixmapFormat.ARGB4444);
        Assets.helpscreen1 = g.newPixmap("helpscreen1.png", PixmapFormat.ARGB4444);
        Assets.helpscreen2 = g.newPixmap("helpscreen2.png", PixmapFormat.ARGB4444);
        Assets.helpscreen3 = g.newPixmap("helpscreen3.png", PixmapFormat.ARGB4444);
        Assets.click = game.getAudio().newSound("click.ogg");
        Assets.eat = game.getAudio().newSound("eat.ogg");
        Assets.bitten = game.getAudio().newSound("gameover.ogg");
        Assets.paused = game.getAudio().newSound("paused.ogg");
        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }

    public void present(float deltaTime) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }
}
