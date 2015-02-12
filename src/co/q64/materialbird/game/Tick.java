package co.q64.materialbird.game;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.sprite.Sprite;

public class Tick implements IUpdateHandler {

	private MaterialBird game;
	private float time;
	private float cleanup = 0;
	private float spawnCloud = 5f;
	private Random r;

	public Tick(MaterialBird game) {
		this.game = game;
		this.r = new Random();
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		time = time + pSecondsElapsed;
		if (time > cleanup) {
			cleanup = cleanup + 2;
			Set<Sprite> toRemove = new HashSet<Sprite>();
			for (Sprite sprite : game.getBgComponents()) {
				if ((sprite.getX() + sprite.getWidth()) < -10) {
					toRemove.add(sprite);
					game.getBg().detachChild(sprite);
				}
			}
			game.getBgComponents().removeAll(toRemove);
			toRemove.clear();
		}
		if (time > spawnCloud) {
			game.newBgComponent("cloud");
			spawnCloud = spawnCloud + 5f;
		}
	}

	@Override
	public void reset() {

	}

}
