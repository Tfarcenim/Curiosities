package tfar.curiosities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import top.theillusivec4.curios.api.CuriosAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class A {
	public static Optional<ItemStack> getCurio(Item item, PlayerEntity player) {
		return getAllSlots(player).stream().filter(stack -> stack.getItem() == item).findFirst();
	}

	public static List<ItemStack> getAllSlots(PlayerEntity player) {

		List<ItemStack> list = new ArrayList<>();

		CuriosAPI.getCuriosHandler(player)
						.map(iCurioItemHandler -> iCurioItemHandler.getCurioMap().values())
						.ifPresent(curioStackHandlers -> curioStackHandlers
										.forEach(curioStackHandler -> IntStream.range(0, curioStackHandler.getSlots())
														.filter(i -> !curioStackHandler.getStackInSlot(i).isEmpty())
														.forEach(i -> list.add(curioStackHandler.getStackInSlot(i)))));
		return list;
	}
}
