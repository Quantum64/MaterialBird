package co.q64.materialbird.game.material;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import co.q64.materialbird.game.base.ContentPane2D;
import co.q64.materialbird.game.material.util.MaterialShadow;
import co.q64.materialbird.game.objects.DynamicElement;

public class MaterialPane2D extends ContentPane2D {

	public MaterialPane2D(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	private Map<WeakReference<DynamicElement>, Bitmap> bakedShadows = new ConcurrentHashMap<WeakReference<DynamicElement>, Bitmap>();

	public void render(Canvas canvas) {
		prepareRenderOrder();
		for (DynamicElement element : getElements()) {
			int x = element.getX() + getX();
			int y = element.getY() + getY();
			element.getObject().render(canvas);
			for (WeakReference<DynamicElement> ref : bakedShadows.keySet()) {
				Log.w("MaterialBird", "Ref found " + ref); //TODO Testing Only
				if (ref.get() == element) {
					Log.w("MaterialBird", "Drawing Shadow"); //TODO Testing Only
					canvas.drawBitmap(bakedShadows.get(ref), x, y, null);
				}
			}
		}
	}

	public void addElement(final DynamicElement element) {
		super.addElement(element);
		final Bitmap temp = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				Canvas template = new Canvas(temp);
				element.getObject().render(template);
				MaterialShadow shadow = new MaterialShadow();
				Log.w("MaterialBird", "Drawing shadow"); //TODO Testing Only
				Bitmap bakedShadow = shadow.createDropShadow(temp);
				WeakReference<DynamicElement> linkback = new WeakReference<DynamicElement>(element);
				//bakedShadows.put(linkback, bakedShadow);
				Log.w("MaterialBird", "Added weak ref"); //TODO Testing Only
				List<WeakReference<DynamicElement>> nullRefs = new ArrayList<WeakReference<DynamicElement>>();
				for (WeakReference<DynamicElement> ref : bakedShadows.keySet()) {
					if (ref.get() == null) {
						nullRefs.add(ref);
						Log.w("MaterialBird", "Removing null ref " + ref.toString()); //TODO Testing Only
					}
				}
				bakedShadows.keySet().removeAll(nullRefs);
			}
		});
		thread.setName("shadowgenerator-" + element.getName());
		thread.start();
	}
}
