package tfar.curiosities.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import tfar.curiosities.CurioCaps;
import top.theillusivec4.curios.api.capability.ICurio;

import javax.annotation.Nullable;

public class VoidCharmItem extends EldritchItem {
	public VoidCharmItem(Properties properties) {
		super(properties);
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		return CurioCaps.createProvider(new ICurio() {
			@Override
			public void onCurioTick(String identifier, int index, LivingEntity livingEntity) {
				World world = livingEntity.world;
				if (!world.isRemote) {
					if (!livingEntity.onGround && !livingEntity.isInWater()) {
						//probably falling, time to save them
						if (livingEntity.getPositionVec().y < -10) {
							BlockPos pos = getBlockPos(stack);
							livingEntity.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
							((ServerWorld)world).spawnParticle(ParticleTypes.PORTAL, pos.getX(), pos.getY(), pos.getZ(),
							8, 0, 0, 0,1);
							SoundEvent soundEvent = SoundEvents.ENTITY_ENDERMAN_TELEPORT;
							livingEntity.playSound(soundEvent, 1.0F, 1.0F);
							stack.shrink(1);
						}
					} else {
						//not falling, save current position
						if (!world.getBlockState(livingEntity.getPosition().down()).isAir()) {
							setBlockPos(stack, livingEntity.getPosition());
						}
					}
				}
			}
		});
	}

	public static void setBlockPos(ItemStack stack, BlockPos pos) {
		stack.getOrCreateTag().putIntArray("pos",new int[]{pos.getX(),pos.getY(),pos.getZ()});
	}

	@Nullable
	public static BlockPos getBlockPos(ItemStack stack) {
		if (!stack.hasTag())return null;
		int[] arr = stack.getTag().getIntArray("pos");
		return new BlockPos(arr[0],arr[1],arr[2]);
	}
}
