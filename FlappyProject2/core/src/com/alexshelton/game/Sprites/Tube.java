package com.alexshelton.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Alex on 11/14/2015.
 */
public class Tube {

    public  static final int TUBE_WIDTH = 52;
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int TUBE_GAP_HARD = 75;
    private static final int LOWEST_OPENING = 120;

    private Texture topTube, bottomTube, topTube2, bottomTube2, topTube3, bottomTube3, topTube4, bottomTube4;
    private Vector2 posTopTube, posBotTube;
    private Rectangle boundsTop, boundsBot;
    private Random rand;







    public Tube(float x ) {

        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        topTube2 = new Texture("toptube2.png");
        bottomTube2 = new Texture("bottomtube2.png");
        topTube3 = new Texture("toptube3.png");
        bottomTube3 = new Texture("bottomtube3.png");
        topTube4 = new Texture("toptube_2.png");
        bottomTube4 = new Texture("bottomtube_2.png");



        rand = new Random();





            posTopTube = new Vector2(x, rand.nextInt(130) + TUBE_GAP + LOWEST_OPENING);
            posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());





            boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
            boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());



    }



    public Texture getTopTube(float score) {
        if(score <= 3)
           return topTube2;
        else if(score <= 6)
            return topTube3;

        else if(score <=9)
            return topTube4;
        else
            return topTube;
    }

    public Texture getBottomTube(float score)
    {
        if(score <= 3)
        return bottomTube2;
        else if (score <= 6)
            return bottomTube3;
        else if (score <=9)
            return bottomTube4;
        else
            return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public void reposition( float x, float score) {

        if(score <= 5){
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());}
        else if (score >5)
            posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP_HARD + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP_HARD - bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y);

        boundsBot.setPosition(posBotTube.x, posBotTube.y);

    }

    public boolean collides(Rectangle player){

       return player.overlaps(boundsTop) || player.overlaps(boundsBot);



    }



    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}
