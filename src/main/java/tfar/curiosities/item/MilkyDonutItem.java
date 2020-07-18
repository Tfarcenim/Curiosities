package tfar.curiosities.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MilkyDonutItem extends Item {
	public MilkyDonutItem(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		if (!worldIn.isRemote) entityLiving.curePotionEffects(stack);
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
}
