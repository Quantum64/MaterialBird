package co.q64.materialbird.game.material.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import co.q64.materialbird.game.material.MaterialBase;
import co.q64.materialbird.game.material.interfaces.Colorable;
import co.q64.materialbird.game.material.interfaces.Renderable;

public class MaterialCircle extends MaterialBase implements Renderable, Colorable {

	private int color = 0;
	private int radius;

	public MaterialCircle(Context context, int x, int y, int radius) {
		super(context, x, y, radius * 2, radius * 2);
		this.radius = radius;
	}

	@Override
	public void render(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(color);
		canvas.drawCircle(getX(), getY(), radius, paint);
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getColor() {
		return color;
	}
}