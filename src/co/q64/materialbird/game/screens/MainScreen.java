package co.q64.materialbird.game.screens;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import co.q64.materialbird.framework.Game;
import co.q64.materialbird.framework.Screen;
import co.q64.materialbird.game.material.elements.MaterialLoader;
import co.q64.materialbird.game.material.util.MaterialShadow;

public class MainScreen extends Screen {

	MaterialLoader loader;

	public MainScreen(Game game) {
		super(game);
		loader = new MaterialLoader(game.getContext());
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(float deltaTime) {
		game.getGraphics().drawRect(0, 0, 1281, 1281, Color.WHITE);
		// loader.render(game.getGraphics().getCanvas());
		Bitmap bitmap = Bitmap.createBitmap(game.getGraphics().getCanvas().getWidth(), game.getGraphics().getCanvas().getHeight(), Bitmap.Config.ARGB_8888);
		Canvas temp = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		temp.drawCircle(100, 100, 100, paint);
		MaterialShadow ms = new MaterialShadow(6, Color.BLACK, 0.5f, 5, -Math.PI / 4);
		game.getGraphics().getCanvas().drawBitmap(ms.createDropShadow(bitmap), 100, 100, null);
		game.getGraphics().getCanvas().drawBitmap(bitmap, 100, 100, null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub

	}

}
