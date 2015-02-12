package co.q64.materialbird.game.sprite;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class SpriteXMove extends AnimatedSprite {

	public SpriteXMove(float pX, float pY, float pWidth, float pHeight, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pWidth, pHeight, pTiledTextureRegion, pVertexBufferObjectManager);
	}

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		this.setPosition(this.getX() - 2f, this.getY());
		super.onManagedUpdate(pSecondsElapsed);
	}
}
