package co.q64.materialbird.engine.component;

import java.nio.IntBuffer;

import android.opengl.GLES30;
import co.q64.materialbird.engine.component.interfaces.Renderable;

public class Rect implements Renderable {

	int vao;

	public Rect(int x, int y, int width, int height) {
		IntBuffer gen = IntBuffer.wrap(new int[1]);
		GLES30.glGenVertexArrays(1, gen);
		vao = gen.array()[0];
	}

	@Override
	public void render() {
		GLES30.glBindVertexArray(vao);
		GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 4);

	}
}
