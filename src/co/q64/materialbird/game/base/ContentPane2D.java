package co.q64.materialbird.game.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import co.q64.materialbird.game.material.interfaces.Renderable;
import co.q64.materialbird.game.objects.DynamicElement;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class ContentPane2D implements Renderable {

	private int xOffset;
	private int yOffset;
	private int width;
	private int height;

	private int camX;
	private int camY;

	private List<DynamicElement> elements;

	public ContentPane2D(int x, int y, int width, int height) {
		this.xOffset = x;
		this.yOffset = y;
		this.width = width;
		this.height = height;
		this.camX = 0;
		this.camY = 0;
		this.elements = new ArrayList<DynamicElement>();
	}

	@Override
	public void render(Canvas canvas) {
		prepareRenderOrder();
		for (DynamicElement element : elements) {
			int x = camX;
			int y = camY;
			Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
			Canvas rendered = new Canvas(bitmap);
			element.getObject().render(rendered);
			canvas.drawBitmap(bitmap, x, y, null);
		}
	}

	public DynamicElement getElement(String name) {
		for (DynamicElement element : elements) {
			if (element.getName().equalsIgnoreCase(name)) {
				return element;
			}
		}
		return null;
	}

	public void removeElement(String name) {
		DynamicElement elementToRemove = null;
		for (DynamicElement element : elements) {
			if (element.getName().equalsIgnoreCase(name)) {
				elementToRemove = element;
			}
		}
		if (elementToRemove != null) {
			elements.remove(elementToRemove);
		}
	}

	public void addElement(DynamicElement element) {
		for (DynamicElement e : elements) {
			if (e.getName().equalsIgnoreCase(element.getName())) {
				return;
			}
		}
		elements.add(element);
	}

	public List<DynamicElement> getElements() {
		return elements;
	}

	public void prepareRenderOrder() {
		Collections.sort(elements, new Comparator<DynamicElement>() {

			@Override
			public int compare(DynamicElement o1, DynamicElement o2) {
				return (o1.getZ() < o2.getZ() ? -1 : (o1.getZ() == o2.getZ() ? 0 : 1));
			}
		});
	}

	public void moveTo(int x, int y) {
		camX = x;
		camY = y;
	}

	public void move(int x, int y) {
		camX = camX + x;
		camY = camY + y;
	}

	public int getXOffset() {
		return xOffset;
	}

	public int getYOffset() {
		return yOffset;
	}

	public int getX() {
		return camX;
	}

	public int getY() {
		return camY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public void renderNoOffset(Canvas canvas) {
		render(canvas);
	}
}
