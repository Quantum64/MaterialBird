package co.q64.materialbird.game.material;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import co.q64.materialbird.game.base.ContentPane2D;
import co.q64.materialbird.game.material.util.MaterialShadow;
import co.q64.materialbird.game.objects.DynamicElement;

public class MaterialPane2D extends ContentPane2D {

	Bitmap layerBuffer = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
	Canvas layerCanvas = new Canvas(layerBuffer);
	Paint layerClear = new Paint();
	PorterDuffXfermode trans = new PorterDuffXfermode(Mode.CLEAR);
	MaterialShadow shadow = new MaterialShadow();

	public MaterialPane2D(int x, int y, int width, int height) {
		super(x, y, width, height);
		layerClear.setXfermode(trans);
	}

	public void render(Canvas canvas) {
		Map<Integer, List<MaterialElement>> layers = splitRenderLayers(getElements());
		for (List<MaterialElement> layer : layers.values()) {
			//layerCanvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);
			layerCanvas.drawPaint(layerClear);
			for (MaterialElement e : layer) {
				e.setOffset(getX(), getY());
				e.render(layerCanvas);
			}
			shadow.render(canvas, layerBuffer);
			canvas.drawBitmap(layerBuffer, getXOffset(), getYOffset(), null);
		}
	}

	public void addElement(final DynamicElement element) {
		super.addElement(element);/*
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
		thread.start();*/
	}

	private Map<Integer, List<MaterialElement>> splitRenderLayers(List<DynamicElement> in) {
		Map<Integer, List<MaterialElement>> layers = new TreeMap<Integer, List<MaterialElement>>();
		for (DynamicElement e : in) {
			if (layers.get(e.getZ()) == null) {
				layers.put(e.getZ(), new ArrayList<MaterialElement>());
			}
			layers.get(e.getZ()).add(e.getObject());
		}
		return layers;
	}
}
