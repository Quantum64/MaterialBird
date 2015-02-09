package co.q64.materialbird.engine;

import java.util.List;

import android.view.View.OnTouchListener;
import co.q64.materialbird.engine.interfaces.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener {
	public boolean isTouchDown(int pointer);

	public int getTouchX(int pointer);

	public int getTouchY(int pointer);

	public List<TouchEvent> getTouchEvents();
}
