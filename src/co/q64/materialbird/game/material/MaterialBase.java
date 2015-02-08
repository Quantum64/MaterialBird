package co.q64.materialbird.game.material;

import android.content.Context;
import android.content.res.Resources;
import co.q64.materialbird.game.material.interfaces.MaterialElement;

public abstract class MaterialBase implements MaterialElement {

	private Context context;
	
	private int x;
	private int y;
	private int width;
	private int height;

	public MaterialBase(Context context, int x, int y, int width, int height) {
		this.context = context;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Resources getResources() {
		return context.getResources();
	}

	public Context getContext() {
		return context;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
