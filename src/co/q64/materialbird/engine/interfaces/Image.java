package co.q64.materialbird.engine.interfaces;

import co.q64.materialbird.engine.interfaces.Graphics.ImageFormat;

public interface Image {
    public int getWidth();
    public int getHeight();
    public ImageFormat getFormat();
    public void dispose();
}
