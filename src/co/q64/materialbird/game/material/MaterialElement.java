package co.q64.materialbird.game.material;

import android.content.Context;
import android.content.res.Resources;
import co.q64.materialbird.game.base.Element;
import co.q64.materialbird.game.material.interfaces.Material;

public abstract class MaterialElement extends Element implements Material {

	private Context context;

	private int x;
	private int y;
	private int offsetX;
	private int offsetY;
	private int width;
	private int height;

	public MaterialElement(Context context, int x, int y, int width, int height) {
		this.context = context;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.offsetX = 0;
		this.offsetY = 0;
	}

	public Resources getResources() {
		return context.getResources();
	}

	public Context getContext() {
		return context;
	}

	public int getRealX() {
		return x;
	}

	public int getRealY() {
		return y;
	}
	
	public int getX() {
		return x + offsetX;
	}

	public int getY() {
		return y + offsetY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffset(int x, int y) {
		this.offsetX = x;
		this.offsetY = y;
	}
}
