package me.catsflex.nosuffocationoverlay.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class YACLIntegration {
	public static Screen createScreen(Screen parent) {
		var config = NoSuffocationOverlayConfig.getInstance();
		
		return YetAnotherConfigLib.createBuilder()
			.title(Component.translatable("config.nosuffocationoverlay.title"))
			.category(ConfigCategory.createBuilder()
				
				.name(Component.translatable("config.nosuffocationoverlay.section.general"))
				
				// 'Enabled' option
				.option(Option.<Boolean>createBuilder()
					.name(Component.translatable("config.nosuffocationoverlay.option.enabled.name"))
					.description(OptionDescription.of(Component.translatable("config.nosuffocationoverlay.option.enabled.desc")))
					.binding(NoSuffocationOverlayConfig.DEF_ENABLED, () -> config.enabled, v -> config.enabled = v)
					.controller(TickBoxControllerBuilder::create)
					.build())
				
				// 'Overlay Opacity' option
				.option(Option.<Integer>createBuilder()
					.name(Component.translatable("config.nosuffocationoverlay.option.opacity.name"))
					.description(OptionDescription.of(Component.translatable("config.nosuffocationoverlay.option.opacity.desc")))
					.binding(NoSuffocationOverlayConfig.DEF_OPACITY_PERCENT, () -> config.opacityPercent, v -> config.opacityPercent = v)
					.controller(opt -> IntegerSliderControllerBuilder.create(opt).range(NoSuffocationOverlayConfig.LIM_PERCENT_MIN, NoSuffocationOverlayConfig.LIM_PERCENT_MAX).step(1))
					.build())
				
				// 'Overlay Brightness' option
				.option(Option.<Integer>createBuilder()
					.name(Component.translatable("config.nosuffocationoverlay.option.brightness.name"))
					.description(OptionDescription.of(Component.translatable("config.nosuffocationoverlay.option.brightness.desc")))
					.binding(NoSuffocationOverlayConfig.DEF_BRIGHTNESS_PERCENT, () -> config.brightnessPercent, v -> config.brightnessPercent = v)
					.controller(opt -> IntegerSliderControllerBuilder.create(opt).range(NoSuffocationOverlayConfig.LIM_PERCENT_MIN, NoSuffocationOverlayConfig.LIM_PERCENT_MAX).step(1))
					.build())
				
				.build())
			.save(config::save)
			.build()
			.generateScreen(parent);
	}
}
