package co.q64.materialbird.game.objects;

import co.q64.materialbird.game.material.MaterialElement;

public class DynamicElement {
	private String name;
	private MaterialElement object;
	private int z;

	public DynamicElement(String name, MaterialElement object, int z) {
		this.name = name;
		this.object = object;
	}

	public String getName() {
		return name;
	}

	public MaterialElement getObject() {
		return object;
	}

	public int getZ() {
		return z;
	}
}
