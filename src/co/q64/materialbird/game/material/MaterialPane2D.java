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
		for (DynamicElement wrapped : getElements()) {
			MaterialElement element = wrapped.getObject();
			element.setOffset(getX(), getY());
			element.render(canvas);
			for (WeakReference<DynamicElement> ref : bakedShadows.keySet()) {
				if (ref.get() == wrapped) {
					canvas.drawBitmap(bakedShadows.get(ref), element.getX(), element.getY(), null);
					break;
				}
			}
		}
	}

	public void addElement(final DynamicElement element) {
		super.addElement(element);
		final Bitmap temp = Bitmap.createBitmap(element.getObject().getWidth() * 2, element.getObject().getHeight() * 2, Bitmap.Config.ARGB_8888);
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				Canvas template = new Canvas(temp);
				element.getObject().renderNoOffset(template);
				MaterialShadow shadow = new MaterialShadow();
				Bitmap bakedShadow = shadow.createDropShadow(temp);
				WeakReference<DynamicElement> linkback = new WeakReference<DynamicElement>(element);
				bakedShadows.put(linkback, bakedShadow);
				List<WeakReference<DynamicElement>> nullRefs = new ArrayList<WeakReference<DynamicElement>>();
				for (WeakReference<DynamicElement> ref : bakedShadows.keySet()) {
					if (ref.get() == null) {
						nullRefs.add(ref);
					}
				}
				bakedShadows.keySet().removeAll(nullRefs);
			}
		});
		thread.setName("shadowgenerator-" + element.getName());
		thread.start();
	}
}
