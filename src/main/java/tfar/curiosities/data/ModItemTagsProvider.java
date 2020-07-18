package tfar.curiosities.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import tfar.curiosities.Curiosities;
import tfar.curiosities.CTags;

public class ModItemTagsProvider extends ItemTagsProvider {
	public ModItemTagsProvider(DataGenerator p_i48255_1_) {
		super(p_i48255_1_);
	}

	@Override
	protected void registerTags() {
		getBuilder(CTags.CHARM).add(Curiosities.blackfoot_paw,Curiosities.bezoar,Curiosities.vitamins,
						Curiosities.hyper_pill,Curiosities.eye_patch,Curiosities.looter_badge,Curiosities.void_charm);

		getBuilder(CTags.NECKLACE).add(Curiosities.creeper_amulet);

		getBuilder(CTags.HEAD).add(Curiosities.eldritch_mask);

		getBuilder(Tags.Items.MUSHROOMS).add(Curiosities.mushroom_powder_vial);
	}
}