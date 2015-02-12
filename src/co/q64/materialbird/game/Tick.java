package co.q64.materialbird.game;

import java.util.HashSet;
import java.util.Set;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.sprite.Sprite;

public class Tick implements IUpdateHandler {

	private MaterialBird game;
	private float cleanup = 0;

	public Tick(MaterialBird game) {
		this.game = game;
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		if (pSecondsElapsed > cleanup) {
			cleanup = cleanup + 2;
			Set<Sprite> toRemove = new HashSet<Sprite>();
			for (Sprite sprite : game.getBgComponents()) {
				if ((sprite.getX() - sprite.getWidth()) < -10) {
					toRemove.add(sprite);
					game.getScene().detachChild(sprite);
				}
			}
			game.getBgComponents().removeAll(toRemove);
			toRemove.clear();
		}
	}

	@Override
	public void reset() {

	}

}
