package co.q64.materialbird.game;

import co.q64.materialbird.framework.Screen;
import co.q64.materialbird.framework.implementation.AndroidGame;
import co.q64.materialbird.game.screens.MainScreen;

public class MaterialBird extends AndroidGame {

	@Override
	public Screen getInitScreen() {
		return new MainScreen(this);
	}
	
}
