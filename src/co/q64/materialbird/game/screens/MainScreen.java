package co.q64.materialbird.game.screens;

import java.util.Random;

import android.graphics.Color;
import co.q64.materialbird.engine.interfaces.Game;
import co.q64.materialbird.engine.interfaces.Screen;
import co.q64.materialbird.game.material.MaterialPane2D;
import co.q64.materialbird.game.material.elements.MaterialCircle;
import co.q64.materialbird.game.objects.DynamicElement;

public class MainScreen extends Screen {

	MaterialPane2D pane;

	public MainScreen(Game game) {
		super(game);
		pane = new MaterialPane2D(0, 0, game.getGraphics().getCanvas().getWidth(), game.getGraphics().getCanvas().getHeight());
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			MaterialCircle c = new MaterialCircle(getContext(), r.nextInt(1000), r.nextInt(1000), 100);
			c.setColor(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			c.setRadius(50);
			pane.addElement(new DynamicElement("test_c" + i, c, 0));
		}
	}

	@Override
	public void update(float deltaTime) {
		
	}

	@Override
	public void paint(float deltaTime) {
		pane.move(1, 0);
		game.getGraphics().drawRect(0, 0, 2000, 2000, Color.MAGENTA);
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
