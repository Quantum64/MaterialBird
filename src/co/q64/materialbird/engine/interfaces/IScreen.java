package co.q64.materialbird.engine.interfaces;

import android.content.Context;

public abstract class IScreen {
	protected final IGame game;

	public IScreen(IGame game) {
		this.game = game;
	}

	public Context getContext() {
		return game.getContext();
	}

	public abstract void update(float deltaTime);

	public abstract void paint(float deltaTime);

	public abstract void pause();

	public abstract void resume();

	public abstract void dispose();

	public abstract void backButton();
}
