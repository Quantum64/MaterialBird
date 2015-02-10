package co.q64.materialbird.engine.graphics;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import co.q64.materialbird.engine.Game;
import co.q64.materialbird.engine.io.IOManager;

public class Texture {
	private int width;
	private int height;
	private int id;

	public Texture(String name) {
		id = load(name);
	}

	private int load(String name) {
		final int[] textureHandle = new int[1];

		try {
			InputStream in = IOManager.getFileIO().readAsset(name);
			Bitmap bitmap = BitmapFactory.decodeStream(in);
			width = bitmap.getWidth();
			height = bitmap.getHeight();
			in.close();
			GLES20.glGenTextures(1, textureHandle, 0);
			if (textureHandle[0] != 0) {
				GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);
				GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
				GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
				GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
				GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
				bitmap.recycle();
			}
			if (textureHandle[0] == 0) {
				Log.w(Game.APPLICATION_NAME, "Error loading texture!");
			}

			return textureHandle[0];

		} catch (IOException e) {
			Log.w(Game.APPLICATION_NAME, e);
			return 0;
		}
	}

	public int getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
