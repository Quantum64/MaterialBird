package co.q64.materialbird.engine.interfaces;

import co.q64.materialbird.engine.interfaces.IGraphics.ImageFormat;

public interface IImage {
    public int getWidth();
    public int getHeight();
    public ImageFormat getFormat();
    public void dispose();
}
