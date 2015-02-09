package co.q64.materialbird.engine.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import co.q64.materialbird.engine.interfaces.IFileIO;

public class TextIO implements IFileIO {

	private Context context;
	private AssetManager assets;
	private String externalStoragePath;

	protected TextIO(Context context) {
		this.context = context;
		this.assets = context.getAssets();
		this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
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

	@Override
	public InputStream readAsset(String file) throws IOException {
		return assets.open(file);
	}

	@Override
	public InputStream readFile(String file) throws IOException {
		return new FileInputStream(externalStoragePath + file);
	}

	@Override
	public OutputStream writeFile(String file) throws IOException {
		return new FileOutputStream(externalStoragePath + file);
	}

	public SharedPreferences getSharedPref() {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
}
