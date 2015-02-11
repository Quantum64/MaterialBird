package co.q64.materialbird.engine.component;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.GLES20;
import co.q64.materialbird.engine.component.interfaces.Renderable;
import co.q64.materialbird.engine.shader.Shaders;
import co.q64.materialbird.engine.vecmath.Vector2f;

public class Triangle implements Renderable {

	private float vertices[] = new float[6];
	static final int COORDS_PER_VERTEX = 3;
	float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };
	private FloatBuffer vertexBuffer;

	public Triangle(Vector2f p1, Vector2f p2, Vector2f p3) {

		vertices[0] = p1.x;
		vertices[1] = p1.y;
		vertices[2] = p2.x;
		vertices[3] = p2.y;
		vertices[4] = p3.x;
		vertices[5] = p3.y;

		ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
		bb.order(ByteOrder.nativeOrder());

		vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
	}

	@Override
	public void render() {
		GLES20.glUseProgram(Shaders.TEST_SHADER.getId());
		int mPositionHandle = GLES20.glGetAttribLocation(Shaders.TEST_SHADER.getId(), "vPosition");
		GLES20.glEnableVertexAttribArray(mPositionHandle);
		GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, 3, vertexBuffer);
		int mColorHandle = GLES20.glGetUniformLocation(Shaders.TEST_SHADER.getId(), "vColor");
		GLES20.glUniform4fv(mColorHandle, 1, color, 0);
		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
		GLES20.glDisableVertexAttribArray(mPositionHandle);
	}
}
