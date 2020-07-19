package tfar.curiosities;

import net.minecraft.item.ItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.Arrays;

public class Materials {

	public static final ToolMaterial holy = new ToolMaterial(0,300,1,0,20,
					() -> Ingredient.merge(Arrays.asList(Ingredient.fromTag(Tags.Items.INGOTS_GOLD),Ingredient.fromTag(Tags.Items.DUSTS_GLOWSTONE))));

	public static final ToolMaterial blaze = new ToolMaterial(0,800,1,7,10,
					() -> Ingredient.fromItems(Items.BLAZE_ROD));

	public static final ToolMaterial orohime = new ToolMaterial(0,1200,1,9,8,
					() -> Ingredient.fromTag(Tags.Items.GEMS_EMERALD));

	public static final ToolMaterial crimson = new ToolMaterial(0,500,1,2,14,
					() -> Ingredient.fromTag(Tags.Items.INGOTS_GOLD));

	public static final ToolMaterial defuser = new ToolMaterial(0, ItemTier.DIAMOND.getMaxUses(),1,3,14,
					() -> Ingredient.fromTag(Tags.Items.GEMS_DIAMOND));

	public static final ToolMaterial mushroom_claws = new ToolMaterial(1,2000,1,1,7,
					() -> Ingredient.fromItems(Curiosities.mushroom_powder_vial));



}
