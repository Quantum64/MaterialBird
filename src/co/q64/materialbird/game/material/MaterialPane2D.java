package co.q64.materialbird.game.material;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import co.q64.materialbird.game.base.ContentPane2D;
import co.q64.materialbird.game.material.util.MaterialShadow;
import co.q64.materialbird.game.objects.DynamicElement;

public class MaterialPane2D extends ContentPane2D {

	public void render(Canvas canvas) {
		prepareRenderOrder();
		for (DynamicElement element : getElements()) {
			int x = element.getX() + getX();
			int y = element.getY() + getY();
			Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
			Canvas rendered = new Canvas(bitmap);
			element.getObject().render(rendered);
			MaterialShadow shadow = new MaterialShadow();
			Bitmap bakedShadow = shadow.createDropShadow(bitmap);
			canvas.drawBitmap(bakedShadow, x, y, null);
			canvas.drawBitmap(bitmap, x, y, null);
		}
	}
}
