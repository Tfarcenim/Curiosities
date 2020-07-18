package tfar.curiosities.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import org.codehaus.plexus.util.StringUtils;
import tfar.curiosities.Curiosities;

public class ModLanguageProvider extends LanguageProvider {
	public ModLanguageProvider(DataGenerator gen) {
		super(gen, Curiosities.MODID, "en_us");
	}

	@Override
	protected void addTranslations() {
		for (Item item : Curiosities.items) {
			add(item.getTranslationKey(), getNameFromItem(item));
		}
	}

	public static String getNameFromItem(Item item){
		return StringUtils.capitaliseAllWords(item.getTranslationKey().split("\\.")[2].replace("_", " "));
	}
}