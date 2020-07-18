package tfar.curiosities.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.SwordItem;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import tfar.curiosities.Curiosities;

public class ModItemModelProvider extends ItemModelProvider {
	public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Curiosities.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		Curiosities.items.forEach(item -> {
			String path = item.getRegistryName().getPath();
			try {
				if (item instanceof SwordItem) {
					getBuilder(path).parent(new ModelFile.UncheckedModelFile(mcLoc("item/handheld")))
									.texture("layer0", modLoc("item/" + path));
				} else {
					getBuilder(path).parent(new ModelFile.UncheckedModelFile(mcLoc("item/generated")))
									.texture("layer0", modLoc("item/" + path));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public String getName() {
		return "Item Models";
	}
}