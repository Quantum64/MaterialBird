package co.q64.materialbird.engine.shader;

import android.opengl.GLES20;
import co.q64.materialbird.engine.vecmath.Vector3f;

public class Shader {

	private int id;

	protected Shader(String name) {
		this.id = ShaderLoader.load(name);
	}

	public int getId() {
		return id;
	}

	public void enable() {
		GLES20.glUseProgram(id);
	}

	public void disable() {
		GLES20.glUseProgram(0);
	}

	public int getUniform(String name) {
		return GLES20.glGetUniformLocation(id, name);
	}

	public void setUniform3f(String name, Vector3f vector) {
		GLES20.glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
	}
}
