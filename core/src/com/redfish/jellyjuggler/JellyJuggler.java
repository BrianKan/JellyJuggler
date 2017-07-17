package com.redfish.jellyjuggler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redfish.jellyjuggler.Screens.MenuScreen;

public class JellyJuggler extends Game {
	//A List of all the Screens
	private MenuScreen menuScreen;

	//Get Screen Size
	public static float SCREEN_WIDTH=Gdx.graphics.getWidth();
	public static float SCREEN_HEIGHT=Gdx.graphics.getHeight();



	// Menu Swapping Vars
	public final static int MENU_NUMBER=0;
	public final static int GAME_NUMBER=1;

	@Override
	public void create () {
		menuScreen=new MenuScreen(this);
		setScreen(menuScreen);

	}


	//Method to handle screen switching
	public void switchScreen(int screen){
		switch(screen){
			case MENU_NUMBER:
				this.setScreen(new MenuScreen(this));
				break;

		}
	}

	@Override
	public void render () {
		super.render();

	}

	@Override
	public void dispose () {


	}

}
