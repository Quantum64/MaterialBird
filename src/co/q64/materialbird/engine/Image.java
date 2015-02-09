package co.q64.materialbird.engine;

import android.graphics.Bitmap;
import co.q64.materialbird.engine.interfaces.IImage;
import co.q64.materialbird.engine.interfaces.IGraphics.ImageFormat;

public class Image implements IImage {
	Bitmap bitmap;
	ImageFormat format;

	public Image(Bitmap bitmap, ImageFormat format) {
		this.bitmap = bitmap;
		this.format = format;
	}

	@Override
	public int getWidth() {
		return bitmap.getWidth();
	}

	@Override
	public int getHeight() {
		return bitmap.getHeight();
	}

	@Override
	public ImageFormat getFormat() {
		return format;
	}

	@Override
	public void dispose() {
		bitmap.recycle();
	}
}
