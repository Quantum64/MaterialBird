package co.q64.materialbird.engine;

import android.media.SoundPool;
import co.q64.materialbird.engine.interfaces.ISound;

public class Sound implements ISound {
	int soundId;
	SoundPool soundPool;

	public Sound(SoundPool soundPool, int soundId) {
		this.soundId = soundId;
		this.soundPool = soundPool;
	}

	@Override
	public void play(float volume) {
		soundPool.play(soundId, volume, volume, 0, 0, 1);
	}

	@Override
	public void dispose() {
		soundPool.unload(soundId);
	}

}
