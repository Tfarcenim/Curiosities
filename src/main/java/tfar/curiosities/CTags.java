package tfar.curiosities;

import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class CTags {

	public static final Tag<Item> CURIO = tag("curio");
	public static final Tag<Item> BACK = tag("back");
	public static final Tag<Item> BELT = tag("belt");
	public static final Tag<Item> BODY = tag("body");
	public static final Tag<Item> CHARM = tag("charm");
	public static final Tag<Item> HEAD = tag("head");
	public static final Tag<Item> HANDS = tag("hands");
	public static final Tag<Item> NECKLACE = tag("necklace");
	public static final Tag<Item> RING = tag("ring");

	private static Tag<Item> tag(String name) {
		return new ItemTags.Wrapper(new ResourceLocation("curios", name));
	}

}
