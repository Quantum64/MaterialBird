package co.q64.materialbird.engine;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.opengl.GLES10;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.view.SurfaceHolder;
import co.q64.materialbird.engine.shader.ShaderLoader;
import co.q64.materialbird.engine.shader.Shaders;

public class GLRenderView extends GLSurfaceView implements GLSurfaceView.Renderer {

	private final Paint AA = new Paint();

	Game game;
	Bitmap framebuffer;
	Thread renderThread = null;
	SurfaceHolder holder;
	volatile boolean running = false;

	public GLRenderView(Game game, Bitmap framebuffer) {
		super(game);
		this.game = game;
		this.holder = getHolder();

		setRenderer(this);
	}

	public void resume() {

	}

	public void pause() {

	}

	@Override
	public void onSurfaceCreated(GL10 ignored, EGLConfig config) {
		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		GLES10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	}

	@Override
	public void onSurfaceChanged(GL10 ignored, int width, int height) {
		GLES20.glViewport(0, 0, width, height);
		Shaders.loadAll();
		game.getCurrentScreen().init();
	}

	@Override
	public void onDrawFrame(GL10 ignored) {
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
		game.getCurrentScreen().paint(0);
	}

}