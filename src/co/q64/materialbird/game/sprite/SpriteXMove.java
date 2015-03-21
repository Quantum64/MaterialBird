package co.q64.materialbird.game.sprite;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import co.q64.materialbird.game.MaterialBird;

public class SpriteXMove extends AnimatedSprite {

	private float speed;
	private String name;

	public SpriteXMove(float pX, float pY, float pWidth, float pHeight, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, float speed, String name) {
		super(pX, pY, pWidth, pHeight, pTiledTextureRegion, pVertexBufferObjectManager);
		this.speed = speed;
		this.name = name;
	}

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		this.setPosition(this.getX() - speed, this.getY());
		super.onManagedUpdate(pSecondsElapsed);
	}

	public float pxToGone() {
		return getX() + getWidth();
	}

	public float getPxLeft() {
		return getX() + getWidth() - MaterialBird.CAMERA_WIDTH - /*Shadow padding*/ 70;
	}

	public String getName() {
		return name;
	}
}
