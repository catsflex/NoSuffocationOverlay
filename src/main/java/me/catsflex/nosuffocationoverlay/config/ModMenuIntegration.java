package me.catsflex.nosuffocationoverlay.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenuIntegration implements ModMenuApi {
	
	private final String YACL_ID = "yet_another_config_lib_v3";
	
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return FabricLoader.getInstance().isModLoaded(YACL_ID) ? YACLIntegration::createScreen : parent -> null;
	}
}
