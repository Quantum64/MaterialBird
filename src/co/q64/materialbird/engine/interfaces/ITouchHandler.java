package co.q64.materialbird.engine.interfaces;

import java.util.List;

import co.q64.materialbird.engine.interfaces.IInput.TouchEvent;
import android.view.View.OnTouchListener;

public interface ITouchHandler extends OnTouchListener {
	public boolean isTouchDown(int pointer);

	public int getTouchX(int pointer);

	public int getTouchY(int pointer);

	public List<TouchEvent> getTouchEvents();
}
