package co.q64.materialbird.game.material.elements;

import android.content.Context;
import android.graphics.Canvas;
import co.q64.materialbird.game.material.MaterialBase;
import co.q64.materialbird.game.material.interfaces.Colorable;
import co.q64.materialbird.game.material.interfaces.Renderable;

public class MaterialRect extends MaterialBase implements Renderable, Colorable {

	private int color;
	
	public MaterialRect(Context context, int x, int y, int width, int height) {
		super(context, x, y, width, height);
	}

	@Override
	public void render(Canvas canvas) {
		
	}

	@Override
	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public int getColor() {
		return color;
	}

}
