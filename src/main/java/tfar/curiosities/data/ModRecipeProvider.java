package tfar.curiosities.data;


import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
import tfar.curiosities.Curiosities;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
	public ModRecipeProvider(DataGenerator p_i48262_1_) {
		super(p_i48262_1_);
	}

	@Override
	protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
		ShapedRecipeBuilderNoCriteria.shapedRecipe(Curiosities.eye_patch)
						.key('a', Tags.Items.STRING)
						.key('b', Curiosities.purified_soul)
						.key('c', Tags.Items.LEATHER)
						.patternLine("a  ")
						.patternLine("bcb")
						.patternLine("bbb")
						.build(consumer);

		ShapelessRecipeBuilderNoCriteria.shapelessRecipe(Curiosities.void_charm,4)
						.addIngredient(Curiosities.dark_obelisk)
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(Curiosities.eldritch_mask)
						.key('a', Curiosities.mysterious_battery)
						.key('b', Curiosities.dark_obelisk)
						.key('c', Curiosities.voltic_thread)
						.key('d', Curiosities.soul_vial)
						.patternLine("aba")
						.patternLine("bcb")
						.patternLine("ddd")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(Curiosities.holy_knife)
						.key('a', Tags.Items.INGOTS_GOLD)
						.key('b', Tags.Items.DUSTS_GLOWSTONE)
						.key('c', Tags.Items.INGOTS_IRON)
						.patternLine("  a")
						.patternLine("ba ")
						.patternLine("cb ")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(Curiosities.blazing_blade)
						.key('a', Tags.Items.RODS_BLAZE)
						.key('b', Curiosities.voltic_thread)
						.key('c', Curiosities.soul_vial)
						.patternLine("  a")
						.patternLine("ba ")
						.patternLine("c b")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(Curiosities.defuser_sword)
						.key('a', Curiosities.creeper_leather)
						.key('b', Items.DIAMOND_SWORD)
						.patternLine("aab")
						.patternLine("aba")
						.patternLine("baa")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(Curiosities.vivre_card)
						.key('a', Items.ENDER_EYE)
						.key('b', Items.PAPER)
						.key('c', Curiosities.purified_soul)
						.patternLine("aba")
						.patternLine("bcb")
						.patternLine("aba")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(Curiosities.milky_donut)
						.key('a', Items.WHEAT)
						.key('b', Items.SWEET_BERRIES)
						.key('c', Items.EGG)
						.key('d', Items.MILK_BUCKET)
						.patternLine("abc")
						.patternLine("ddd")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(Curiosities.glowstone_vial)
						.key('a', Items.GLOWSTONE)
						.key('b', Items.GLASS_BOTTLE)
						.patternLine("aaa")
						.patternLine("aba")
						.patternLine("aaa")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(Curiosities.soul_vial)
						.key('a', Curiosities.purified_soul)
						.key('b', Items.GLASS_BOTTLE)
						.patternLine(" a ")
						.patternLine("aba")
						.patternLine(" a ")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(Curiosities.creeper_amulet)
						.key('a', Curiosities.creeper_leather)
						.key('b', Items.STRING)
						.key('c', Tags.Items.GEMS_EMERALD)
						.patternLine("abb")
						.patternLine("acb")
						.patternLine("caa")
						.build(consumer);
	}
}