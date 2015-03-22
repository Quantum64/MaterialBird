package co.q64.materialbird.game.objects;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.sprite.Sprite;

import co.q64.materialbird.game.MaterialBird;

public class Bird implements IUpdateHandler {

	private MaterialBird app;
	private Sprite player;
	private float rotationSpeed = 0;
	private float rotation = 0;
	private float rotationTime = 0;
	private float velocity = 0;
	private float gravity = 1;

	public Bird(MaterialBird app, Sprite player) {
		this.app = app;
		this.player = player;
	}

	public void tap() {
		rotationSpeed = -10;
		velocity = -30;
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		if (!app.isPaused()) {
			if (!(rotation >= 85) && rotationSpeed >= 0 && velocity > 0) {
				rotationTime++;
				if (rotationTime > 25) {
					rotationSpeed += (rotationTime - 5) / 4.0;
					rotation += rotationSpeed;
				} else {
					rotationSpeed += (rotationTime) / 12.0;
					rotation += rotationSpeed;
				}
			} else if (rotationSpeed < 0) {
				rotation += rotationSpeed;
				if (rotation <= -15) {
					rotation = -15;
					rotationSpeed = 0;
				}
			} else {
				rotationTime = 0;
				rotationSpeed = 0;
			}
			player.setRotation(rotation);
			if (player.getY() + velocity < -player.getHeight()) {
				player.setPosition(player.getX(), -player.getHeight() - 1);
			}
			if (player.getY() >= MaterialBird.CAMERA_HEIGHT - player.getHeight() - 1 && velocity > 0) {
				player.setPosition(player.getX(), MaterialBird.CAMERA_HEIGHT - player.getHeight());
				app.endGame();
			} else {
				player.setPosition(player.getX(), player.getY() + velocity);
				velocity = velocity + gravity;
			}
		}
	}

	@Override
	public void reset() {
		rotationSpeed = 0;
		rotation = 0;
		rotationTime = 0;
		velocity = 0;
	}
}
