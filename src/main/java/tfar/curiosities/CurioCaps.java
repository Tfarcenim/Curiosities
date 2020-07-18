package tfar.curiosities;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.capability.CuriosCapability;
import top.theillusivec4.curios.api.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CurioCaps {

	public static ICapabilityProvider createProvider(ICurio curio) {
		return new Provider(curio);
	}

	public static class Provider implements ICapabilityProvider {
		final LazyOptional<ICurio> capability;

		Provider(ICurio curio) {
			this.capability = LazyOptional.of(() -> curio);
		}

		@Nonnull
		public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
			return CuriosCapability.ITEM.orEmpty(cap, this.capability);
		}
	}
}
