package moze_intel.projecte.events;

import moze_intel.projecte.PECore;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.FOVModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PECore.MODID, value = Dist.CLIENT)
public class PlayerRender {

	@SubscribeEvent
	public static void onFOVUpdateEvent(FOVModifierEvent evt) {
	}
}