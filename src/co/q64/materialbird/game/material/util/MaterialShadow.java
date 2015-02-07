package co.q64.materialbird.game.material.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

public class MaterialShadow {

	/** The shadow size. */
	private int shadowSize;

	/** The shadow color. */
	private int shadowColor;

	/** The shadow opacity. */
	private float shadowOpacity;

	/** The shadow offset angle (in radians). */
	private double angle;

	/** The shadow offset distance (in Java2D units). */
	private int distance;

	/**
	 * Creates a new instance with default attributes.
	 */
	public MaterialShadow() {
		this(5, Color.BLACK, 0.5f, 5, -Math.PI / 4);
	}

	/**
	 * Creates a new instance with the specified attributes.
	 *
	 * @param size
	 *            the shadow size.
	 * @param color
	 *            the shadow color.
	 * @param opacity
	 *            the shadow opacity.
	 * @param distance
	 *            the shadow offset distance.
	 * @param angle
	 *            the shadow offset angle (in radians).
	 */

	public MaterialShadow(int size, int color, float opacity, int distance, double angle) {
		this.shadowSize = size;
		this.shadowColor = color;
		this.shadowOpacity = opacity;
		this.distance = distance;
		this.angle = angle;
	}

	/**
	 * Returns the shadow size.
	 *
	 * @return The shadow size.
	 */
	public int getShadowSize() {
		return this.shadowSize;
	}

	/**
	 * Returns the shadow color.
	 *
	 * @return The shadow color (never <code>null</code>).
	 */
	public int getShadowColor() {
		return this.shadowColor;
	}

	/**
	 * Returns the shadow opacity.
	 *
	 * @return The shadow opacity.
	 */
	public float getShadowOpacity() {
		return this.shadowOpacity;
	}

	/**
	 * Returns the shadow offset distance.
	 *
	 * @return The shadow offset distance (in Java2D units).
	 */
	public int getDistance() {
		return this.distance;
	}

	/**
	 * Returns the shadow offset angle (in radians).
	 *
	 * @return The angle (in radians).
	 */
	public double getAngle() {
		return this.angle;
	}

	/**
	 * Calculates the x-offset for drawing the shadow image relative to the
	 * source.
	 *
	 * @return The x-offset.
	 */
	public int calculateOffsetX() {
		return (int) (Math.cos(this.angle) * this.distance) - this.shadowSize;
	}

	/**
	 * Calculates the y-offset for drawing the shadow image relative to the
	 * source.
	 *
	 * @return The y-offset.
	 */
	public int calculateOffsetY() {
		return -(int) (Math.sin(this.angle) * this.distance) - this.shadowSize;
	}

	/**
	 * Creates and returns an image containing the drop shadow for the specified
	 * source image.
	 *
	 * @param source
	 *            the source image.
	 *
	 * @return A new image containing the shadow.
	 */
	public Bitmap createDropShadow(Bitmap source) {
		Bitmap subject = Bitmap.createBitmap(source.getWidth() + this.shadowSize * 2, source.getHeight() + this.shadowSize * 2, Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(subject);
		canvas.drawBitmap(source, this.shadowSize, this.shadowSize, null);
		applyShadow(subject);
		return subject;
	}

	/**
	 * Applies a shadow to the image.
	 *
	 * @param image
	 *            the image.
	 */
	protected void applyShadow(Bitmap image) {
		int dstWidth = image.getWidth();
		int dstHeight = image.getHeight();

		int left = (this.shadowSize - 1) >> 1;
		int right = this.shadowSize - left;
		int xStart = left;
		int xStop = dstWidth - right;
		int yStart = left;
		int yStop = dstHeight - right;

		int shadowRgb = Color.red(this.shadowColor) & 0x00FFFFFF;

		int[] aHistory = new int[this.shadowSize];
		int historyIdx;

		int aSum;

		int[] dataBuffer = new int[image.getWidth() * image.getHeight()];
		image.getPixels(dataBuffer, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
		int lastPixelOffset = right * dstWidth;
		float sumDivider = this.shadowOpacity / this.shadowSize;

		// horizontal pass

		for (int y = 0, bufferOffset = 0; y < dstHeight; y++, bufferOffset = y * dstWidth) {
			aSum = 0;
			historyIdx = 0;
			for (int x = 0; x < this.shadowSize; x++, bufferOffset++) {
				int a = dataBuffer[bufferOffset] >>> 24;
				aHistory[x] = a;
				aSum += a;
			}

			bufferOffset -= right;

			for (int x = xStart; x < xStop; x++, bufferOffset++) {
				int a = (int) (aSum * sumDivider);
				dataBuffer[bufferOffset] = a << 24 | shadowRgb;

				// substract the oldest pixel from the sum
				aSum -= aHistory[historyIdx];

				// get the lastest pixel
				a = dataBuffer[bufferOffset + right] >>> 24;
				aHistory[historyIdx] = a;
				aSum += a;

				if (++historyIdx >= this.shadowSize) {
					historyIdx -= this.shadowSize;
				}
			}
		}

		// vertical pass
		for (int x = 0, bufferOffset = 0; x < dstWidth; x++, bufferOffset = x) {
			aSum = 0;
			historyIdx = 0;
			for (int y = 0; y < this.shadowSize; y++, bufferOffset += dstWidth) {
				int a = dataBuffer[bufferOffset] >>> 24;
				aHistory[y] = a;
				aSum += a;
			}

			bufferOffset -= lastPixelOffset;

			for (int y = yStart; y < yStop; y++, bufferOffset += dstWidth) {
				int a = (int) (aSum * sumDivider);
				dataBuffer[bufferOffset] = a << 24 | shadowRgb;

				// substract the oldest pixel from the sum
				aSum -= aHistory[historyIdx];

				// get the lastest pixel
				a = dataBuffer[bufferOffset + lastPixelOffset] >>> 24;
				aHistory[historyIdx] = a;
				aSum += a;

				if (++historyIdx >= this.shadowSize) {
					historyIdx -= this.shadowSize;
				}
			}
		}
		image.setPixels(dataBuffer, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
	}
}