package co.q64.materialbird.game.material.elements;

import android.graphics.Canvas;
import android.graphics.Paint;
import co.q64.materialbird.game.material.interfaces.MaterialPlane;
import co.q64.materialbird.game.material.interfaces.Renderable;

public class MaterialCircle implements Renderable, MaterialPlane {

	private int color = 0;
	private int radius = 50;

	@Override
	public void render(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(color);
		canvas.drawCircle(radius, radius, radius, paint);
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
