package co.q64.materialbird.game.objects;

import co.q64.materialbird.game.material.interfaces.Renderable;

public class DynamicElement {
	private String name;
	private Renderable object;
	private int x;
	private int y;
	private int z;

	public DynamicElement(String name, Renderable object, int x, int y, int z) {
		this.name = name;
		this.object = object;
		this.x = x;
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public Renderable getObject() {
		return object;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
}
