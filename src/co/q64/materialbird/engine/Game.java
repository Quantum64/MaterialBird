package co.q64.materialbird.engine;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;
import co.q64.materialbird.engine.interfaces.IAudio;
import co.q64.materialbird.engine.interfaces.IFileIO;
import co.q64.materialbird.engine.interfaces.IGame;
import co.q64.materialbird.engine.interfaces.IGraphics;
import co.q64.materialbird.engine.interfaces.IInput;
import co.q64.materialbird.engine.interfaces.IScreen;

public abstract class Game extends Activity implements IGame {
	
	public static final String APPLICATION_NAME = "MaterialBird";
	
	GLRenderView renderView;
	IGraphics graphics;
	IAudio audio;
	IInput input;
	IScreen screen;
	WakeLock wakeLock;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
		int frameBufferWidth = isPortrait ? 800 : 1280;
		int frameBufferHeight = isPortrait ? 1280 : 800;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Config.RGB_565);

		float scaleX = (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();

		renderView = new GLRenderView(this, frameBuffer);
		graphics = new Graphics(getAssets(), frameBuffer);
		audio = new Audio(this);
		input = new Input(this, renderView, scaleX, scaleY);
		screen = getInitScreen();
		setContentView(renderView);
		aquireWakelock();
	}

	private void aquireWakelock() {
		PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "MyGame");
	}

	@Override
	public void onResume() {
		super.onResume();
		wakeLock.acquire();
		screen.resume();
		renderView.resume();
	}

	@Override
	public void onPause() {
		super.onPause();
		wakeLock.release();
		renderView.pause();
		screen.pause();

		if (isFinishing())
			screen.dispose();
	}

	@Override
	public IInput getInput() {
		return input;
	}

	@Override
	public IGraphics getGraphics() {
		return graphics;
	}

	@Override
	public IAudio getAudio() {
		return audio;
	}

	@Override
	public void setScreen(IScreen screen) {
		if (screen == null)
			throw new IllegalArgumentException("Screen must not be null");

		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}

	public IScreen getCurrentScreen() {
		return screen;
	}

	public Context getContext() {
		return this.getApplicationContext();
	}
}