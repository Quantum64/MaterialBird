package co.q64.materialbird.engine.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.util.Log;

public class TextIO extends BasicFileIO {

	protected TextIO(Context context) {
		super(context);
	}

	public String readText(String file) {
		StringBuilder result = new StringBuilder();
		try {
			InputStream stream = readAsset(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			String buffer = new String();
			while ((buffer = reader.readLine()) != null) {
				result.append(buffer);

			}
			reader.close();
		} catch (IOException e) {
			Log.w("MaterialBird", e);
		}
		return result.toString();
	}
}
