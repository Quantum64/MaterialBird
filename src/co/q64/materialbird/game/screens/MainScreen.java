package co.q64.materialbird.game.screens;

import co.q64.materialbird.engine.component.Triangle;
import co.q64.materialbird.engine.interfaces.IGame;
import co.q64.materialbird.engine.interfaces.IScreen;
import co.q64.materialbird.engine.vecmath.Vector2f;

public class MainScreen extends IScreen {

	Triangle t;

	public MainScreen(IGame game) {
		super(game);
	}

	@Override
	public void init() {
		t = new Triangle(Vector2f.factory(0.0f, 0.5f), Vector2f.factory(0.5f, -0.5f), Vector2f.factory(-0.5f, 0.5f));
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
