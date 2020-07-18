package tfar.curiosities.item;

import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.util.LazyValue;

public class ImmunityItem extends Item {
	public final Effect immuneTo;
	private final String lore;

	public ImmunityItem(Properties properties, Effect immuneTo, String lore) {
		super(properties);
		this.immuneTo = immuneTo;
		this.lore = lore;
	}
}
