package tfar.curiosities.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
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

public class BlackFootsPawItem extends Item {
	public BlackFootsPawItem(Properties properties) {
		super(properties);
	}

	private static final UUID uuid = UUID.fromString("171e9a9b-cffa-4d09-8826-35a81a76e291");

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		return CurioCaps.createProvider(new ICurio() {

			public void onCurioTick(String identifier, int index, LivingEntity livingEntity) {
				if (!livingEntity.getEntityWorld().isRemote && livingEntity.ticksExisted % 100 == 0) {
					livingEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 200, 4, true, true));
				}
			}

			@Override
			public Multimap<String, AttributeModifier> getAttributeModifiers(String identifier) {
				Multimap<String, AttributeModifier> atts = HashMultimap.create();
				if (CuriosAPI.getCurioTags(stack.getItem()).contains(identifier)) {
					atts.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(uuid, "Armor bonus", 4.0D, AttributeModifier.Operation.ADDITION));
					atts.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(uuid, "Speed Boost", 1.25D, AttributeModifier.Operation.MULTIPLY_TOTAL));
				}
				return atts;
			}
		});
		}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("Came from a lost warrior").applyTextStyle(TextFormatting.GRAY));
	}
}
