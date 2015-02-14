package co.q64.materialbird.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import android.graphics.Point;
import android.view.Display;
import co.q64.materialbird.engine.ModifiedGameActivity;
import co.q64.materialbird.game.sprite.SpriteXMove;

public class MaterialBird extends ModifiedGameActivity {

	public static int CAMERA_WIDTH;
	public static int CAMERA_HEIGHT;

	private Scene scene;
	private Random random;

	private TiledTextureRegion playerTextureRegion;

	private Entity bgLayer;
	private Map<String, TiledTextureRegion> bgTextures;
	private List<SpriteXMove> bg;
	private SpriteXMove lastHill;

	public MaterialBird() {
		random = new Random();
	}

	@Override
	public EngineOptions onCreateEngineOptions() {
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		CAMERA_WIDTH = size.x;
		CAMERA_HEIGHT = size.y;
		bg = new ArrayList<SpriteXMove>();
		bgTextures = new HashMap<String, TiledTextureRegion>();
		Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		EngineOptions options = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);

		return options;
	}

	@Override
	public void onCreateResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		BitmapTextureAtlas bitmapBuffer;
		bitmapBuffer = new BitmapTextureAtlas(this.getTextureManager(), 250, 114);
		playerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapBuffer, this, "bird.png", 0, 0, 1, 1);
		bitmapBuffer.load();
		bitmapBuffer = new BitmapTextureAtlas(this.getTextureManager(), 500, 300);
		bgTextures.put("cloud", BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapBuffer, this, "cloud.png", 0, 0, 1, 1));
		bitmapBuffer.load();
		bitmapBuffer = new BitmapTextureAtlas(this.getTextureManager(), 700, 350);
		bgTextures.put("hill1", BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapBuffer, this, "hill1.png", 0, 0, 1, 1));
		bitmapBuffer.load();
	}

	@Override
	protected Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		scene = new Scene();
		scene.setBackground(new Background(0.129f, 0.588f, 0.953f, 1f));

		bgLayer = new Entity();
		bgLayer.setX(0);
		bgLayer.setY(0);

		float centerX = (CAMERA_WIDTH - this.playerTextureRegion.getWidth()) / 2;
		float centerY = (CAMERA_HEIGHT - this.playerTextureRegion.getHeight()) / 2;

		AnimatedSprite player = new AnimatedSprite(centerX, centerY, 250, 114, playerTextureRegion, getVertexBufferObjectManager());

		scene.attachChild(bgLayer);

		addHill("hill1", 2f);

		scene.attachChild(player);

		scene.registerUpdateHandler(new Tick(this));

		return scene;
	}

	public void newBgComponent(float x, float y, String texture, float speed) {
		TiledTextureRegion tex = bgTextures.get(texture);
		SpriteXMove sprite = new SpriteXMove(x, y, tex.getWidth(), tex.getHeight(), tex, getVertexBufferObjectManager(), speed, texture);
		bgLayer.attachChild(sprite);
		bg.add(sprite);
	}

	public void addHill(String texture, float speed) {
		TiledTextureRegion tex = bgTextures.get(texture);
		SpriteXMove sprite = new SpriteXMove(MaterialBird.CAMERA_WIDTH, MaterialBird.CAMERA_HEIGHT - tex.getHeight(), tex.getWidth(), tex.getHeight(), tex, getVertexBufferObjectManager(), speed, texture);
		bgLayer.attachChild(sprite);
		bg.add(sprite);
		lastHill = sprite;
	}

	public List<SpriteXMove> getBgComponents() {
		return bg;
	}

	public Scene getScene() {
		return scene;
	}

	public Entity getBg() {
		return bgLayer;
	}

	public SpriteXMove getLastHill() {
		return lastHill;
	}
}
