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
public class MenuState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;


    Texture ground;
    Texture background;
    Texture playBtn;
    private Vector2 groundPos1, groundPos2;
    private String tap = "TAP";

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
        cam.setToOrtho(false, FlappyBird.WIDTH / 2 , FlappyBird.HEIGHT /2 );
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        ground = new Texture("ground.png");
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(),GROUND_Y_OFFSET);
        font = new BitmapFont(Gdx.files.internal("text.fnt"));
        font.getData().setScale(.5f,.5f);


    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
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
        sb.draw(playBtn, groundPos1.x +65, groundPos1.y +230);
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);

        sb.end();

        sb.begin();
        font.setColor(Color.WHITE);
        font.draw(sb, "TAP TO PLAY!", 10, 325);
        font.draw(sb, "TAP TO PLAY!", 10, 125);

        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
