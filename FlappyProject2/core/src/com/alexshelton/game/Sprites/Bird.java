package com.alexshelton.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Alex on 11/14/2015.
 */
public class Bird {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;

    private Rectangle bounds;
    private Animation birdAnimation;
    private Animation birdAnimation2;
    private Animation cardinalAnimation;
    private Animation wesAnimation;

    private Texture texture;
    private Texture texture2;
    private Texture texture3;
    private Texture texture4;



    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        texture = new Texture("birdanimation.png");
        texture2 = new Texture("birdanimation2.png");
        texture3 = new Texture("cardinalanimation.png");
        texture4 = new Texture("wesfaceanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        birdAnimation2 = new Animation(new TextureRegion(texture2), 3, 0.5f);
        cardinalAnimation = new Animation(new TextureRegion(texture3), 3, 0.5f);
        wesAnimation = new Animation(new TextureRegion(texture4),3,0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());

    }

    public void update(float dt){
        birdAnimation.update(dt);
        birdAnimation2.update(dt);
        cardinalAnimation.update(dt);
        wesAnimation.update(dt);



            if (position.y > 0)
                velocity.add(0, GRAVITY, 0);
            velocity.scl(dt);
            position.add(MOVEMENT * dt, velocity.y, 0);
            if (position.y < 0)
                position.y = 0;

            velocity.scl(1 / dt);
            bounds.setPosition(position.x, position.y);
        }




    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture(float score) {

        if(score <= 3)
            return birdAnimation2.getFrame();
         else if(score <= 6)
            return cardinalAnimation.getFrame();
        else if (score <= 9)
            return birdAnimation.getFrame();
        else
            return wesAnimation.getFrame();

    }

    public void jump(){

        velocity.y = 250;

    }





    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        texture.dispose();
    }
}
