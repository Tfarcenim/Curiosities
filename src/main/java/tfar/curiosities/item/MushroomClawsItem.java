package tfar.curiosities.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;

import java.util.Set;

public class MushroomClawsItem extends ToolItem {

	public MushroomClawsItem(float attackDamageIn, float attackSpeedIn, IItemTier tier, Set<Block> effectiveBlocksIn, Properties builder) {
		super(attackDamageIn, attackSpeedIn, tier, effectiveBlocksIn, builder);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment.type == EnchantmentType.DIGGER || enchantment.type == EnchantmentType.WEAPON;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return state.getBlockHardness(null,null) <= 1.5 ? 20 : 1 * super.getDestroySpeed(stack, state);
	}
}
