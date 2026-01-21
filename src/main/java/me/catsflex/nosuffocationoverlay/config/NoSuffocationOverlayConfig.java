package me.catsflex.nosuffocationoverlay.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class NoSuffocationOverlayConfig {
	
	// Default values
	public static final boolean DEF_ENABLED = true;
	public static final int DEF_OPACITY_PERCENT = 50;
	public static final int DEF_BRIGHTNESS_PERCENT = 10;
	
	// Limits
	public static final int LIM_PERCENT_MIN = 0;
	public static final int LIM_PERCENT_MAX = 100;
	
	// Current values
	public boolean enabled = DEF_ENABLED;
	public int opacityPercent = DEF_OPACITY_PERCENT;
	public int brightnessPercent = DEF_BRIGHTNESS_PERCENT;
	
	// Config saving stuff
	private static final Gson _GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final String _FILE_PATH = "config/nosuffocationoverlay.json";
	private static final File _FILE = new File(Minecraft.getInstance().gameDirectory, _FILE_PATH);
	
	private static NoSuffocationOverlayConfig _instance;
	
	public static NoSuffocationOverlayConfig getInstance() {
		if (_instance == null) {
			_instance = load();
		}
		return _instance;
	}
	
	public static NoSuffocationOverlayConfig load() {
		if (_FILE.exists()) {
			try (var reader = new FileReader(_FILE)) {
				var loaded = _GSON.fromJson(reader, NoSuffocationOverlayConfig.class);
				loaded.opacityPercent = Math.clamp(loaded.opacityPercent, LIM_PERCENT_MIN, LIM_PERCENT_MAX);
				loaded.brightnessPercent = Math.clamp(loaded.brightnessPercent, LIM_PERCENT_MIN, LIM_PERCENT_MAX);
				return loaded;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		var config = new NoSuffocationOverlayConfig();
		config.save();
		return config;
	}
	
	public void save() {
		try (var writer = new FileWriter(_FILE)) {
			_GSON.toJson(this, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
