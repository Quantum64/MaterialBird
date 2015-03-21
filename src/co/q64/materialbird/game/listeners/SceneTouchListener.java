package co.q64.materialbird.game.listeners;

import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import co.q64.materialbird.game.MaterialBird;

public class SceneTouchListener implements IOnSceneTouchListener {

	MaterialBird app;

	public SceneTouchListener(MaterialBird app) {
		this.app = app;
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		if (app.isPaused()) {
			return false;
		}
		if (pSceneTouchEvent.isActionDown()) {
			app.getBird().tap();
		}
		return true;
	}
}
