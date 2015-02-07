package co.q64.materialbird.game.screens;

import android.graphics.Color;
import android.graphics.Rect;
import co.q64.materialbird.framework.Game;
import co.q64.materialbird.framework.Screen;
import co.q64.materialbird.game.material.MaterialPane2D;
import co.q64.materialbird.game.material.elements.MaterialCircle;
import co.q64.materialbird.game.objects.DynamicElement;

public class MainScreen extends Screen {

	MaterialPane2D pane;

	public MainScreen(Game game) {
		super(game);
		pane = new MaterialPane2D();
		MaterialCircle c = new MaterialCircle();
		c.setColor(Color.GREEN);
		c.setRadius(50);
		pane.addElement(new DynamicElement("test_c1", c, 300, 100, 0));
	}

	@Override
	public void update(float deltaTime) {
		pane.move(1, 0);
	}

	@Override
	public void paint(float deltaTime) {
		game.getGraphics().drawRect(0, 0, 2000, 2000, Color.WHITE);
		pane.render(game.getGraphics().getCanvas());
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
