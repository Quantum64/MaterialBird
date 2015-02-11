package co.q64.materialbird.engine.shader;

import android.opengl.GLES20;
import co.q64.materialbird.engine.vecmath.Vector3f;
import co.q64.materialbird.engine.vecmath.Vector4f;

public class Shader {

	private int id;

	private final static String vertexShaderCode = "attribute vec4 vPosition;" + "void main() {" + "  gl_Position = vPosition;" + "}";

	private final static String fragmentShaderCode = "precision mediump float;" + "uniform vec4 vColor;" + "void main() {" + "  gl_FragColor = vColor;" + "}";

	protected Shader() {
		this(vertexShaderCode, fragmentShaderCode);
	}

	protected Shader(String name) {
		this.id = ShaderLoader.load(name);
	}

	protected Shader(String vert, String frag) {
		this.id = ShaderLoader.create(vert, frag);
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

	public int getAttrib(String name) {
		GLES20.glBindAttribLocation(id, 0, "vPosition");
		return GLES20.glGetAttribLocation(id, "vPosition");
	}

	public void setUniform3f(String name, Vector3f vector) {
		GLES20.glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
	}

	public void setUniform4f(String name, Vector4f vector) {
		GLES20.glUniform4f(getUniform(name), vector.x, vector.y, vector.z, vector.y);
	}
}
