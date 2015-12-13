package com.alexshelton.game;

import com.alexshelton.game.States.GameStateManager;
import com.alexshelton.game.States.MenuState;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends Game {

	//Set Width and Height of the Game Screen
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;


	public static final String TITLE = "Flappy";

	public GameStateManager gsm;
	public SpriteBatch batch;
	private Music music;
	//Font to store things I write to the screen
	public BitmapFont font;


	//Create Method for the Game Sets Music, GameState, and initial Sprite Batch
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
		music = Gdx.audio.newMusic((Gdx.files.internal("lost-woods.mp3")));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		Gdx.gl.glClearColor(1, 0, 0, 1);



	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);



	}

	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
		font.dispose();
	}
}
