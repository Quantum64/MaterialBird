package co.q64.materialbird.game;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.sprite.Sprite;

import co.q64.materialbird.game.sprite.SpriteXMove;

public class Tick implements IUpdateHandler {

	private static final int HILL_GFX_NUM = 1;

	private MaterialBird game;
	private float time;
	private float cleanup = 0;
	private float spawnCloud = 5f;
	private float checkHill = 0.5f;
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
			int x = MaterialBird.CAMERA_WIDTH;
			int y = r.nextInt((int) Math.round(MaterialBird.CAMERA_HEIGHT - (MaterialBird.CAMERA_HEIGHT / 2.0)));
			game.newBgComponent(x, y, "cloud", 2.5f);
			spawnCloud = spawnCloud + 5f;
		}
		if (time > checkHill) {
			checkHill = checkHill + 0.5f;
			SpriteXMove sprite = game.getLastHill();
			if (sprite != null) {
				if (sprite.getPxLeft() < (sprite.getWidth() / 8.0)) {
					if (r.nextInt(3) == 1) {
						game.addHill("hill" + (r.nextInt(HILL_GFX_NUM) + 1), 2f);
					}
				} else {
					if (sprite.getPxLeft() < (sprite.getWidth() / 16.0)) {
						game.addHill("hill" + (r.nextInt(HILL_GFX_NUM) + 1), 2f);
					}
				}
			}
		}
	}

	@Override
	public void reset() {

	}

}
