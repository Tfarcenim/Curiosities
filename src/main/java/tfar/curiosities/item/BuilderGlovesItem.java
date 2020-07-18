package tfar.curiosities.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import tfar.curiosities.CurioCaps;
import top.theillusivec4.curios.api.CuriosAPI;
import top.theillusivec4.curios.api.capability.ICurio;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class BuilderGlovesItem extends Item {
	public BuilderGlovesItem(Properties properties) {
		super(properties);
	}

	private static final UUID uuid = UUID.fromString("313e451a-bf9f-44c6-8a70-2911bcbdc1f6");

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		return CurioCaps.createProvider(new ICurio() {

			@Override
			public Multimap<String, AttributeModifier> getAttributeModifiers(String identifier) {
				Multimap<String, AttributeModifier> atts = HashMultimap.create();
				if (CuriosAPI.getCurioTags(stack.getItem()).contains(identifier)) {
					atts.put(PlayerEntity.REACH_DISTANCE.getName(), new AttributeModifier(uuid, "Reach bonus", 1.0D, AttributeModifier.Operation.ADDITION));
				}
				return atts;
			}
		});
		}
}
