package com.alexshelton.game.States;

import com.alexshelton.game.FlappyBird;
import com.alexshelton.game.Sprites.Bird;
import com.alexshelton.game.Sprites.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Alex on 11/14/2015.
 */
public class PlayState extends State {

    //Constant Variables for Tube Count, Spacing, and Ground Offset
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;


    //Constants for displaying Score
    private float scoreX = 120;
    private float scoreY = 375 ;

    //Variables for the score, vector, and all Textures
    private float score;
    private Bird bird;
    private Texture bg;
    private Texture ground, ground2, ground3;
    private Texture bg2;
    private Texture android_bg;
    private Texture cob_bg;
    private Vector2 groundPos1, groundPos2;

    //Variable for Array of Tubes
    private Array<Tube> tubes;


    //PlayState Constructor
    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 100);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        bg = new Texture("cob.png");
        ground = new Texture("ground.png");
        ground2 = new Texture("Ground3.png");
        ground3 = new Texture("Ground2.png");
        bg2 = new Texture("bg.png");
        cob_bg = new Texture("classroom_bg.png");
        android_bg = new Texture("android_bg.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(),GROUND_Y_OFFSET);
        font = new BitmapFont(Gdx.files.internal("text.fnt"));
        font.getData().setScale(0.5f,0.5f);
        tubes = new Array<Tube>();

        //Add tubes
        for(float i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {

        //On Screen touch make bird jump

        if(Gdx.input.justTouched())
            bird.jump();

    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        updateScoreLocation();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        if(bird.getBounds().x >= 156)
            score = (bird.getBounds().x / 176) - 0.6f ;

        for(int i = 0; i < tubes.size; i++){
            Tube tube = tubes.get(i);


            if(cam.position.x - cam.viewportWidth / 2 > tube.getPosTopTube().x + tube.getTopTube(score).getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT), score);
            }

            //Check for tube collision
           if(tube.collides(bird.getBounds()))

               gsm.set(new GameOver(gsm, score));


            }
            //Check for ground collision
             if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET)

               gsm.set(new GameOver(gsm, score));



            //update camera
            cam.update();


    }

    @Override
    public void render(SpriteBatch sb) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        //Determine what to render based on score
        {

            if (score <= 3) {
                sb.draw(bg2, cam.position.x - (cam.viewportWidth / 2), 0);
                sb.draw(bird.getTexture(score), bird.getPosition().x, bird.getPosition().y);

            }
            else if (score <= 6) {
                sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
                sb.draw(bird.getTexture(score), bird.getPosition().x, bird.getPosition().y);


            }
            else if (score <= 9) {
                sb.draw(android_bg, cam.position.x - (cam.viewportWidth / 2), 0);
                sb.draw(bird.getTexture(score), bird.getPosition().x, bird.getPosition().y);

            }
            else if (score > 9) {
                sb.draw(cob_bg, cam.position.x - (cam.viewportWidth / 2), 0);
                sb.draw(bird.getTexture(score), bird.getPosition().x, bird.getPosition().y);

            }

            //pass score to tube to determine what to render
            for (Tube tube : tubes) {

                sb.draw(tube.getTopTube(score), tube.getPosTopTube().x, tube.getPosTopTube().y);
                sb.draw(tube.getBottomTube(score), tube.getPosBotTube().x, tube.getPosBotTube().y);
            }

            //determine what ground to render based on current score
            if(score <= 3) {
                sb.draw(ground, groundPos1.x, groundPos1.y);
                sb.draw(ground, groundPos2.x, groundPos2.y);
            }
            else if(score <= 6){
                sb.draw(ground2, groundPos1.x, groundPos1.y);
                sb.draw(ground2, groundPos2.x, groundPos2.y);
            }
            else if (score <= 9){
                sb.draw(ground3, groundPos1.x, groundPos1.y);
                sb.draw(ground3, groundPos2.x, groundPos2.y);
            }
            else if (score > 9){
                sb.draw(ground, groundPos1.x, groundPos1.y);
                sb.draw(ground, groundPos2.x, groundPos2.y);
            }

        }

        sb.end();


        sb.begin();

        //Write score to screen
        font.setColor(Color.WHITE);
        font.draw(sb, String.format("%.0f", score), scoreX, scoreY);

        sb.end();

    }

    @Override
    public void dispose() {
        //call methods to dispose of bird and tubes as they pass off screen
        bg.dispose();
        bird.dispose();

        ground.dispose();
        for(Tube tube : tubes)
            tube.dispose();

    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth /2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth /2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }

    private  void updateScoreLocation(){
        //continue to update position of score text as screen moves right
       if(scoreX > 1)
          scoreX = bird.getBounds().x;

    }

}
