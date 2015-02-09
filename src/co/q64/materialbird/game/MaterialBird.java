package co.q64.materialbird.game;

import co.q64.materialbird.engine.Game;
import co.q64.materialbird.engine.interfaces.IScreen;
import co.q64.materialbird.game.screens.MainScreen;

public class MaterialBird extends Game {

	@Override
	public IScreen getInitScreen() {
		return new MainScreen(this);
	}
	
}
