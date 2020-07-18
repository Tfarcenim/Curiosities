package tfar.curiosities.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import tfar.curiosities.CurioCaps;
import top.theillusivec4.curios.api.CuriosAPI;
import top.theillusivec4.curios.api.capability.ICurio;

import javax.annotation.Nullable;
import java.util.UUID;

public class EldritchMaskItem extends Item {
	public EldritchMaskItem(Properties properties) {
		super(properties);
	}

	private static final UUID uuid = UUID.fromString("bc3a6ca6-655c-439a-8deb-54fc736bcb28");

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		return CurioCaps.createProvider(new ICurio() {

			@Override
			public Multimap<String, AttributeModifier> getAttributeModifiers(String identifier) {
				Multimap<String, AttributeModifier> atts = HashMultimap.create();
				if (CuriosAPI.getCurioTags(stack.getItem()).contains(identifier)) {
					atts.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(uuid, "Armor bonus", 12.0D, AttributeModifier.Operation.ADDITION));
				}
				return atts;
			}
		});
	}
}
