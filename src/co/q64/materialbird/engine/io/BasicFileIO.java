package co.q64.materialbird.engine.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.preference.PreferenceManager;
import co.q64.materialbird.engine.interfaces.IFileIO;

public abstract class BasicFileIO implements IFileIO {
	private static Context context;
	private AssetManager assets;
	private String externalStoragePath;

	protected BasicFileIO() {
		context = IOManager.getContext();
		this.assets = context.getAssets();
		this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
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
