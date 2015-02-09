package co.q64.materialbird.engine.shader;

import co.q64.materialbird.engine.io.IOManager;
import android.opengl.GLES20;
import android.util.Log;

public class ShaderLoader {

	public int load(String shader) {
		String vert = IOManager.getTextIO().readText(shader + ".vert");
		String frag = IOManager.getTextIO().readText(shader + ".frag");
		return create(vert, frag);
	}

	protected int create(String vertex, String fragment) {
		int prog = GLES20.glCreateProgram();
		int vert = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
		int frag = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
		GLES20.glShaderSource(vert, vertex);
		GLES20.glShaderSource(frag, fragment);
		GLES20.glCompileShader(vert);
		GLES20.glCompileShader(frag);
		validateShader(vert);
		validateShader(frag);
		GLES20.glAttachShader(prog, vert);
		GLES20.glAttachShader(prog, frag);
		GLES20.glLinkProgram(prog);
		GLES20.glValidateProgram(prog);
		return prog;
	}

	private void validateShader(int id) {
		int[] compiled = new int[1];
		GLES20.glGetShaderiv(id, GLES20.GL_COMPILE_STATUS, compiled, 0);
		if (compiled[0] == 0) {
			Log.e("MaterialBird", "Could not compile shader " + id + ":");
			Log.e("MaterialBird", GLES20.glGetShaderInfoLog(id));
			GLES20.glDeleteShader(id);
			id = 0;
		}
	}
}
