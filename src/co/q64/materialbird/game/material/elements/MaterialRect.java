package co.q64.materialbird.game.material.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import co.q64.materialbird.game.material.MaterialElement;
import co.q64.materialbird.game.material.interfaces.Colorable;

public class MaterialRect extends MaterialElement implements Colorable {

	private int color;
	private Rect draw;
	private Paint paint;
	
	public MaterialRect(Context context, int x, int y, int width, int height) {
		super(context, x, y, width, height);
		draw = new Rect(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void render(Canvas canvas) {
		draw.set(getX(), getY(), getWidth(), getHeight());
		paint.setColor(color);
		canvas.drawRect(draw, paint);
	}
	
	@Override
	public void renderNoOffset(Canvas canvas) {
		draw.set(getRealX(), getRealY(), getWidth(), getHeight());
		paint.setColor(color);
		canvas.drawRect(draw, paint);
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
