package com.alexshelton.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Alex on 11/14/2015.
 */
public abstract class State {

    //Set Camera, Vector, and GSM
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;


    //Variables to write to screen
    public BitmapFont font;
    public BitmapFont font2;




    //State Constructor
    protected State (GameStateManager gsm) {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();


    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();




}
