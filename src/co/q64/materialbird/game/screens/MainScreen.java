package co.q64.materialbird.game.screens;

import co.q64.materialbird.engine.component.Triangle;
import co.q64.materialbird.engine.interfaces.IGame;
import co.q64.materialbird.engine.interfaces.IScreen;

public class MainScreen extends IScreen {

	Triangle t = new Triangle();
	
	public MainScreen(IGame game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {

	}

	@Override
	public void paint(float deltaTime) {
		t.render();
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
