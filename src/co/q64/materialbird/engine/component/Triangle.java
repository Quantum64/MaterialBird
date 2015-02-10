package co.q64.materialbird.engine.component;

import java.nio.IntBuffer;

import android.opengl.GLES20;
import android.opengl.GLES30;
import co.q64.materialbird.engine.component.interfaces.Renderable;
import co.q64.materialbird.engine.shader.Shaders;

public class Triangle implements Renderable {

	int vao;

	public Triangle() {
		IntBuffer gen = IntBuffer.wrap(new int[1]);
		GLES30.glGenVertexArrays(1, gen);
		vao = gen.array()[0];
	}
	
	public Triangle(int p1, int p2, int p3) {
		IntBuffer gen = IntBuffer.wrap(new int[1]);
		GLES30.glGenVertexArrays(1, gen);
		vao = gen.array()[0];
	}

	@Override
	public void render() {
		GLES20.glUseProgram(Shaders.TEST_SHADER.getId());
		GLES30.glBindVertexArray(vao);
		GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 3);

	}
}
