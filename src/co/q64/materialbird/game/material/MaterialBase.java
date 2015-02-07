package co.q64.materialbird.game.material;

import android.content.Context;
import android.content.res.Resources;

public abstract class MaterialBase {

	private Context context;

	public MaterialBase(Context context) {
		this.context = context;
	}

	public Resources getResources() {
		return context.getResources();
	}
}
