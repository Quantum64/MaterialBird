package co.q64.materialbird.game.material.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import co.q64.materialbird.game.material.MaterialElement;
import co.q64.materialbird.game.material.interfaces.Colorable;

public class MaterialCircle extends MaterialElement implements Colorable {

	private int color = 0;
	private int radius;
	private Paint paint;

	public MaterialCircle(Context context, int x, int y, int radius) {
		super(context, x, y, radius * 2, radius * 2);
		this.radius = radius;
		this.paint = new Paint();
	}

	@Override
	public void render(Canvas canvas) {
		paint.setColor(color);
		canvas.drawCircle(getX(), getY(), radius, paint);
	}
	
	@Override
	public void renderNoOffset(Canvas canvas) {
		paint.setColor(color);
		canvas.drawCircle(getRealX(), getRealX(), radius, paint);
		
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