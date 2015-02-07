package co.q64.materialbird.game.screens;

import android.graphics.Color;
import co.q64.materialbird.framework.Game;
import co.q64.materialbird.framework.Screen;
import co.q64.materialbird.game.material.elements.MaterialLoader;

public class SplashScreen extends Screen {

	MaterialLoader loader;
	
	public SplashScreen(Game game) {
		super(game);
		loader = new MaterialLoader(game.getContext());
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(float deltaTime) {
		game.getGraphics().drawRect(0, 0, 1281, 1281, Color.WHITE);
		loader.render(game.getGraphics().getCanvas());
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub
		
	}

}
