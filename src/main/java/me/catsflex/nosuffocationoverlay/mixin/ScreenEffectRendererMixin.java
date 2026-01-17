package me.catsflex.nosuffocationoverlay.mixin;

import me.catsflex.nosuffocationoverlay.config.NoSuffocationOverlayConfig;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixin {
	
	@Redirect(
		method = "renderTex",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ARGB;colorFromFloat(FFFF)I")
	)
	private static int changeOverlayAlpha(float a, float r, float g, float b) {
		var config = NoSuffocationOverlayConfig.getInstance();
		
		if (!config.enabled) return ARGB.colorFromFloat(a, r, g, b);
		
		float customAlpha = config.opacityPercent / 100.0f;
		return ARGB.colorFromFloat(customAlpha, r, g, b);
	}
}
