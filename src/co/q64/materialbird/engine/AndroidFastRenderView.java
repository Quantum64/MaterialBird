package co.q64.materialbird.engine;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.view.SurfaceHolder;

public class AndroidFastRenderView extends GLSurfaceView implements GLSurfaceView.Renderer {
	
	private final Paint AA = new Paint();
	
	AndroidGame game;
	Bitmap framebuffer;
	Thread renderThread = null;
	SurfaceHolder holder;
	volatile boolean running = false;
	int frames = 0;
	int fps = 0;

	public AndroidFastRenderView(AndroidGame game, Bitmap framebuffer) {
		super(game);
		this.game = game;
		this.framebuffer = framebuffer;
		this.holder = getHolder();

		TimerTask updateFPS = new TimerTask() {
			public void run() {
				fps = frames;
				frames = 0;
			}
		};

		Timer t = new Timer();
		t.scheduleAtFixedRate(updateFPS, 1000, 1000);

	}

	public void resume() {
		running = true;
		renderThread.start();
	}

	public void run() {
		Rect dstRect = new Rect();
		long startTime = System.nanoTime();

		while (running) {

			if (!holder.getSurface().isValid())
				continue;

			float deltaTime = (System.nanoTime() - startTime) / 10000000.000f;
			startTime = System.nanoTime();

			if (deltaTime > 3.15) {
				deltaTime = (float) 3.15; 
			}

			game.getCurrentScreen().update(deltaTime);
			game.getCurrentScreen().paint(deltaTime);

			Canvas canvas = holder.lockCanvas();
			canvas.getClipBounds(dstRect);
			canvas.drawBitmap(framebuffer, null, dstRect, AA);
			Paint paint = new Paint();
			paint.setColor(Color.RED);
			paint.setTextSize(40);
			canvas.drawText("FPS: " + fps, 100, 100, paint);
			holder.unlockCanvasAndPost(canvas);
			frames++;
		}
	}

	public void pause() {
		running = false;
		while (true) {
			try {
				renderThread.join();
				break;
			} catch (InterruptedException e) {
				// retry
			}

		}
	}

}