package com.alexshelton.game.States;

import com.alexshelton.game.FlappyBird;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Alex on 11/14/2015.
 */
public class GameOver extends State {

    //variables for ground and score on Game Over screen
    private static final int GROUND_Y_OFFSET = -50;
    private float finalScore;


    Texture ground;
    Texture background;
    Texture gameover;
    private Vector2 groundPos1;
    ParseUtil parseUtil = new ParseUtil();


    //Game Over constructor
    public GameOver(GameStateManager gsm, float FS) {
        super(gsm);
        background = new Texture("bg.png");
        gameover = new Texture("gameover.png");
        cam.setToOrtho(false, FlappyBird.WIDTH / 2 , FlappyBird.HEIGHT /2 );
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        ground = new Texture("ground.png");

        font = new BitmapFont(Gdx.files.internal("text.fnt"));
        font.getData().setScale(1f,1f);
        font2 = new BitmapFont(Gdx.files.internal("text.fnt"));
        font2.getData().setScale(0.5f,0.5f);
        finalScore = FS;

    }

    //on gameover screen, tapping starts a new game state
    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            //parseUtil.add_net_score(String.valueOf(finalScore));
            parseUtil.add_score(String.valueOf(finalScore));
            gsm.set(new PlayState(gsm));

        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);

        sb.begin();

        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(gameover, groundPos1.x + 27, groundPos1.y + 300);
        sb.draw(ground, groundPos1.x, groundPos1.y);

        sb.end();

        sb.begin();

        font.setColor(Color.WHITE);
        font.draw(sb, String.format("%.0f", finalScore), 90f, 200);

        //display a different taunt based on final score
        if(finalScore <=3){
            font2.draw(sb, "UR GARBAGE", 15, 350);
        }
        else if(finalScore <= 6){
            font2.draw(sb, "OPEN UR EYES", 5, 360);
        }
        else if(finalScore <= 9){
            font2.draw(sb, "SUCKER", 50, 375);
        }
        else{
            font2.draw(sb, "GOOGLE IT!", 30, 350);
        }

        sb.end();
<<<<<<< HEAD
=======

>>>>>>> refs/remotes/was2792/master
    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();
    }
}
