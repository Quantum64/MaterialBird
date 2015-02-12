package co.q64.materialbird.game;

import java.util.ArrayList;
import java.util.List;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import android.graphics.Point;
import android.view.Display;
import co.q64.materialbird.engine.ModifiedGameActivity;

public class MaterialBird extends ModifiedGameActivity {

	private static int CAMERA_WIDTH;
	private static int CAMERA_HEIGHT;

	private Scene scene;

	private TiledTextureRegion playerTextureRegion;

	private List<Sprite> bg;

	@Override
	public EngineOptions onCreateEngineOptions() {
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		CAMERA_WIDTH = size.x;
		CAMERA_HEIGHT = size.y;
		bg = new ArrayList<Sprite>();
		Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		EngineOptions options = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);

		return options;
	}

	@Override
	public void onCreateResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		BitmapTextureAtlas bitmapBuffer;
		bitmapBuffer = new BitmapTextureAtlas(this.getTextureManager(), 200, 200);
		playerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapBuffer, this, "bird.png", 0, 0, 1, 1);
		bitmapBuffer.load();
	}

	@Override
	protected Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		scene = new Scene();
		scene.setBackground(new Background(0.129f, 0.588f, 0.953f, 1f));

		float centerX = (CAMERA_WIDTH - this.playerTextureRegion.getWidth()) / 2;
		float centerY = (CAMERA_HEIGHT - this.playerTextureRegion.getHeight()) / 2;

		AnimatedSprite player = new AnimatedSprite(centerX, centerY, 200, 200, playerTextureRegion, getVertexBufferObjectManager());

		scene.attachChild(player);

		scene.registerUpdateHandler(new Tick(this));

		return scene;
	}

	public List<Sprite> getBgComponents() {
		return bg;
	}

	public Scene getScene() {
		return scene;
	}
}
