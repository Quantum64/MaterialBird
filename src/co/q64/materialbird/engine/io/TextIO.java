package co.q64.materialbird.engine.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.util.Log;
import co.q64.materialbird.engine.Game;

public class TextIO extends BasicFileIO {

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
			Log.w(Game.APPLICATION_NAME, e);
		}
		return result.toString();
	}
}
