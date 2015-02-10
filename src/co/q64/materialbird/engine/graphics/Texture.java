package co.q64.materialbird.engine.graphics;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.opengl.GLES20;
import android.util.Log;
import co.q64.materialbird.engine.Game;
import co.q64.materialbird.engine.io.IOManager;

public class Texture {
	private int width;
	private int height;
	private int id;

	public Texture(String name) {

	}

	private int load(String name) {
		final int[] textureHandle = new int[1];
		int id = -1;
		int pixels[];
		 if (textureHandle[0] != 0)
		    {
		try {
			InputStream in = IOManager.getFileIO().readAsset(name);
			Bitmap bitmap = BitmapFactory.decodeStream(in);
			width = bitmap.getWidth();
			height = bitmap.getHeight();
			in.close();
			pixels = new int[width * height];
			bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

			// Damnit opengl, why do you make me convert this
			int[] data = new int[width * height];
			for (int i = 0; i < width * height; i++) {
				int a = (pixels[i] & 0xff000000) >> 24;
				int r = (pixels[i] & 0xff0000) >> 16;
				int g = (pixels[i] & 0xff00) >> 8;
				int b = (pixels[i] & 0xff);
				data[i] = a << 24 | b << 16 | g << 8 | r;
			}
			
			
			
		} catch (IOException e) {
			Log.w(Game.APPLICATION_NAME, e);
		}}
	}
}
