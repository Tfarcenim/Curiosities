package tfar.curiosities.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class VivreCardItem extends Item {
	public VivreCardItem(Properties properties) {
		super(properties);
	}

	public static final String KEY = "entity_holder";

	@Override
	@Nonnull
	public ActionResultType onItemUse(ItemUseContext context) {
		PlayerEntity player = context.getPlayer();
		if (player == null)return ActionResultType.FAIL;
		Hand hand = Hand.MAIN_HAND;
		ItemStack stack = player.getHeldItemMainhand();
		if (player.getEntityWorld().isRemote || !containsEntity(stack)) return ActionResultType.FAIL;
		Entity entity = getEntityFromStack(stack, player.world, true);
		BlockPos blockPos = context.getPos();
		entity.setPositionAndRotation(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, 0, 0);
		stack.shrink(1);
		player.setHeldItem(hand, stack);
		player.world.addEntity(entity);
		//if (entity instanceof LivingEntity) ((LivingEntity) entity).playSound();
		return ActionResultType.CONSUME;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
		if (target.getEntityWorld().isRemote || target instanceof PlayerEntity || !target.isAlive() || containsEntity(stack))
			return false;
		EntityType<?> entityID = target.getType();
		if (isBlacklisted(entityID)) return false;
		ItemStack newStack = stack.copy();
		CompoundNBT nbt = getNBTfromEntity(target);
		ItemStack newerStack = newStack.split(1);
		newerStack.getOrCreateTag().put(KEY,nbt);
		player.swingArm(hand);
		player.setHeldItem(hand, newStack);
		if(!player.addItemStackToInventory(newerStack)){
			ItemEntity itemEntity = new ItemEntity(player.world,player.getPosX(),player.getPosY(),player.getPosZ(),newerStack);
			player.world.addEntity(itemEntity);
		}
		target.remove();
		player.getCooldownTracker().setCooldown(this, 5);
		return true;
	}


	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);

		// tooltip.add(new StringTextComponent(stack.getOrCreateTag().toString()));
		if (containsEntity(stack))
			if (!getID(stack).isEmpty()) {
				String s0 = "entity." + getID(stack);
				String s1 = s0.replace(':','.');
				tooltip.add(new StringTextComponent(I18n.format(s1)));
				tooltip.add(new StringTextComponent("Health: " + stack.getTag().getCompound(KEY).getDouble("Health")));
			}
	}

	@Override
	@Nonnull
	public ITextComponent getDisplayName(@Nonnull ItemStack stack) {
		if (!containsEntity(stack))
			return new TranslationTextComponent(super.getTranslationKey(stack) + ".name");
		String s0 = "entity." + getID(stack);
		String s1 = s0.replace(':','.');
		return new TranslationTextComponent(I18n.format(super.getTranslationKey(stack) + ".name") +": "+ I18n.format(s1));
	}

	//helper methods

	public static boolean containsEntity(@Nonnull ItemStack stack) {
		return stack.hasTag() && stack.getTag().contains(KEY);
	}

	public static String getID(ItemStack stack) {
		return getID(stack.getTag());
	}

	public static String getID(CompoundNBT nbt) {
		return nbt.getCompound(KEY).getString("entity");
	}

	public boolean isBlacklisted(EntityType<?> entity) {
		return false;//MobCatcher.blacklisted.func_199685_a_(entity);
	}

	public static Entity getEntityFromNBT(CompoundNBT nbt, World world, boolean withInfo) {
		Entity entity = Registry.ENTITY_TYPE.getOrDefault(new ResourceLocation(nbt.getString("entity"))).create(world);
		if (withInfo) entity.read(nbt);
		return entity;
	}

	public static Entity getEntityFromStack(ItemStack stack, World world, boolean withInfo) {
		return getEntityFromNBT(stack.getOrCreateTag().getCompound(KEY),world,withInfo);
	}

	public static CompoundNBT getNBTfromEntity(Entity entity){
		CompoundNBT nbt = new CompoundNBT();
		nbt.putString("entity", entity.getType().getRegistryName().toString());
		nbt.putString("id", EntityType.getKey(entity.getType()).toString());
		entity.writeUnlessPassenger(nbt);
		return nbt;
	}
}
