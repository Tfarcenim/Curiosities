package tfar.curiosities.item;

import net.minecraft.item.FishingRodItem;

public class GoldenFishingRodItem extends FishingRodItem {
	public GoldenFishingRodItem(Properties builder) {
		super(builder);
	}

	@Override
	public int getItemEnchantability() {
		return 22;
	}
}
