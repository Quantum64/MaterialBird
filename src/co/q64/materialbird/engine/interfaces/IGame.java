package co.q64.materialbird.engine.interfaces;

import android.content.Context;

public interface IGame {

    public IAudio getAudio();

    public IInput getInput();

    public IFileIO getFileIO();

    public IGraphics getGraphics();

    public void setScreen(IScreen screen);

    public IScreen getCurrentScreen();

    public IScreen getInitScreen();
    
    public Context getContext();
}
