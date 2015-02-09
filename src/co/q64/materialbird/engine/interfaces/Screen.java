package co.q64.materialbird.engine.interfaces;

import android.content.Context;

public abstract class Screen {
	protected final Game game;

	public Screen(Game game) {
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
