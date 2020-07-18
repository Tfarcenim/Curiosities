package tfar.curiosities;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Curiosities.MODID)
public class LootInjector {

	@SubscribeEvent
	public static void lootLoad(LootTableLoadEvent evt) {
		String prefix = "minecraft:chests/";
		String name = evt.getName().toString();

		if (name.startsWith(prefix)) {
			String file = name.substring(name.indexOf(prefix) + prefix.length());
			switch (file) {
				case "abandoned_mineshaft":
				case "stronghold_corridor":
				case "end_city_treasure":
				case "nether_bridge":

				 evt.getTable().addPool(getInjectPool(file)); break;
				default: break;
			}
		}
	}

	public static LootPool getInjectPool(String entryName) {
		return LootPool.builder()
						.addEntry(getInjectEntry(entryName, 1))
						.bonusRolls(0, 1)
						.name(Curiosities.MODID+"_inject")
						.build();
	}

	private static LootEntry.Builder getInjectEntry(String name, int weight) {
		ResourceLocation table = new ResourceLocation(Curiosities.MODID, "inject/" + name);
		return TableLootEntry.builder(table)
						.weight(weight);
	}

}