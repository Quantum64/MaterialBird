package co.q64.materialbird.engine.io;

import android.content.Context;

public class IOManager {

	private static Context context;
	private static FileIO fileIO;
	private static TextIO textIO;

	private IOManager() {

	}

	public static void init(Context context) {
		IOManager.context = context;
	}

	public static Context getContext() {
		return context;
	}

	public static FileIO getFileIO() {
		if (fileIO == null) {
			fileIO = new FileIO();
		}
		return fileIO;
	}

	public static TextIO getTextIO() {
		if (textIO == null) {
			textIO = new TextIO();
		}
		return textIO;
	}
}
