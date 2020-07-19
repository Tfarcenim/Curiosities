package tfar.curiosities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.EndDimension;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import tfar.curiosities.item.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Curiosities.MODID)
public class Curiosities {
	// Directly reference a log4j logger.

	public static final String MODID = "curiosities";

	public static Item blackfoot_paw;
	public static Item bezoar;
	public static Item vitamins;
	public static Item eye_patch;
	public static Item hyper_pill;
	public static Item miner_gloves;
	public static Item builder_gloves;
	public static Item looter_badge;
	public static Item void_charm;
	public static Item creeper_amulet;
	public static Item eldritch_mask;
	public static Item holy_knife;
	public static Item blazing_blade;
	public static Item orhime;
	public static Item crimson_blade;
	public static Item defuser_sword;
	public static Item mushroom_claws;
	public static Item vivre_card;
	public static Item golden_fishing_rod;
	public static Item milky_donut;
	public static Item purified_soul;
	public static Item mysterious_battery;
	public static Item dark_obelisk;
	public static Item voltic_thread;
	public static Item mushroom_powder_vial;
	public static Item soul_vial;
	public static Item creeper_leather;
	public static Item glowstone_vial;

	List<Item> immunity_items;


	public Curiosities() {
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		// Register the doClientStuff method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Item.class, this::items);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.addListener(this::onPotionAdd);
		MinecraftForge.EVENT_BUS.addListener(this::miningSpeed);
		MinecraftForge.EVENT_BUS.addListener(this::looting);
		MinecraftForge.EVENT_BUS.addListener(this::xp);
		MinecraftForge.EVENT_BUS.addListener(this::blockplace);
		MinecraftForge.EVENT_BUS.addListener(this::onLoot);
		MinecraftForge.EVENT_BUS.addListener(this::onAnvilUpdate);
		MinecraftForge.EVENT_BUS.addListener(this::hurt);
		MinecraftForge.EVENT_BUS.addListener(this::damage);

	}

	private void items(RegistryEvent.Register<Item> e) {
		blackfoot_paw = register(new BlackFootsPawItem(new Item.Properties().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON)), "blackfoot_paw", e.getRegistry());
		bezoar = register(new ImmunityItem(new Item.Properties().group(ItemGroup.MISC), Effects.POISON, "Anti Poison"), "bezoar", e.getRegistry());
		vitamins = register(new ImmunityItem(new Item.Properties().group(ItemGroup.MISC), Effects.WEAKNESS, "Anti Weakness"), "vitamins", e.getRegistry());
		eye_patch = register(new ImmunityItem(new Item.Properties().group(ItemGroup.MISC), Effects.BLINDNESS, "Anti Blindness"), "eye_patch", e.getRegistry());
		hyper_pill = register(new ImmunityItem(new Item.Properties().group(ItemGroup.MISC), Effects.SLOWNESS, "Anti Slowness"), "hyper_pill", e.getRegistry());
		miner_gloves = register(new Item(new Item.Properties().group(ItemGroup.MISC)), "miner_gloves", e.getRegistry());
		builder_gloves = register(new BuilderGlovesItem(new Item.Properties().group(ItemGroup.MISC).maxDamage(130)), "builder_gloves", e.getRegistry());
		looter_badge = register(new Item(new Item.Properties().group(ItemGroup.MISC)), "looter_badge", e.getRegistry());
		void_charm = register(new VoidCharmItem(new Item.Properties().group(ItemGroup.MISC)), "void_charm", e.getRegistry());
		creeper_amulet = register(new Item(new Item.Properties().group(ItemGroup.MISC)), "creeper_amulet", e.getRegistry());
		eldritch_mask = register(new EldritchMaskItem(new Item.Properties().group(ItemGroup.MISC)), "eldritch_mask", e.getRegistry());
		holy_knife = register(new SwordItem(Materials.holy, 3, -1.8f, new Item.Properties().group(ItemGroup.MISC)), "holy_knife", e.getRegistry());
		blazing_blade = register(new SwordItem(Materials.blaze, 3, -1.8f, new Item.Properties().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON)), "blazing_blade", e.getRegistry());
		orhime = register(new SwordItem(Materials.orohime, 3, -1.8f, new Item.Properties().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON)), "orhime", e.getRegistry());
		crimson_blade = register(new SwordItem(Materials.crimson, 3, -1.8f, new Item.Properties().group(ItemGroup.MISC)), "crimson_blade", e.getRegistry());
		defuser_sword = register(new SwordItem(Materials.defuser, 3, -1.8f, new Item.Properties().group(ItemGroup.MISC)), "defuser_sword", e.getRegistry());
		mushroom_claws = register(new MushroomClawsItem(3, -1.8f, Materials.mushroom_claws, new HashSet<>(), new Item.Properties().group(ItemGroup.MISC).addToolType(ToolType.PICKAXE, 4)), "mushroom_claws", e.getRegistry());
		vivre_card = register(new VivreCardItem(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)), "vivre_card", e.getRegistry());
		golden_fishing_rod = register(new GoldenFishingRodItem(new Item.Properties().group(ItemGroup.MISC).maxDamage(128)), "golden_fishing_rod", e.getRegistry());
		milky_donut = register(new MilkyDonutItem(new Item.Properties().group(ItemGroup.MISC).food(new Food.Builder().hunger(2).saturation(.8f).build())), "milky_donut", e.getRegistry());
		purified_soul = register(new Item(new Item.Properties().group(ItemGroup.MISC)), "purified_soul", e.getRegistry());
		mysterious_battery = register(new EldritchItem(new Item.Properties().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON)), "mysterious_battery", e.getRegistry());
		dark_obelisk = register(new EldritchItem(new Item.Properties().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON)), "dark_obelisk", e.getRegistry());
		voltic_thread = register(new EldritchItem(new Item.Properties().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON)), "voltic_thread", e.getRegistry());
		mushroom_powder_vial = register(new LoreItem(new Item.Properties().group(ItemGroup.MISC),"Smells weird"), "mushroom_powder_vial", e.getRegistry());
		soul_vial = register(new LoreItem(new Item.Properties().group(ItemGroup.MISC),"Soul Storage"), "soul_vial", e.getRegistry());
		creeper_leather = register(new Item(new Item.Properties().group(ItemGroup.MISC)), "creeper_leather", e.getRegistry());
		glowstone_vial = register(new Item(new Item.Properties().group(ItemGroup.MISC)), "glowstone_vial", e.getRegistry());
	}

	private void looting(LootingLevelEvent e) {
		if (e.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) e.getEntityLiving();
			A.getCurio(looter_badge, player).ifPresent(stack -> e.setLootingLevel(e.getLootingLevel() + 2));
		}
	}

	private void hurt(LivingHurtEvent e) {
		if (e.getSource().getTrueSource() instanceof LivingEntity) {
			LivingEntity livingEntity = (LivingEntity) e.getSource().getTrueSource();
			if (livingEntity.getHeldItemMainhand().getItem() == orhime && livingEntity.isEntityUndead()) {
				e.setAmount(e.getAmount() + 7);
			}

			if (livingEntity.getHeldItemMainhand().getItem() == defuser_sword && e.getEntityLiving() instanceof CreeperEntity) {
				e.setAmount(e.getAmount() * 2);
			}
		}
	}

	private void damage(LivingDamageEvent e) {
		if (e.getSource().getTrueSource() instanceof LivingEntity) {
			LivingEntity attacker = (LivingEntity) e.getSource().getTrueSource();
			if (attacker.getHeldItemMainhand().getItem() == crimson_blade) {
				if (attacker.getRNG().nextDouble() < .05)
				attacker.heal(6);
			} if (attacker.getHeldItemMainhand().getItem() == blazing_blade) {
				List<Entity> entities = attacker.world.getEntitiesInAABBexcluding(attacker,getBox(attacker.getPosition(),5), MobEntity.class::isInstance);
				for (Entity entity : entities) {
					entity.setFire(3);
					entity.attackEntityFrom(DamageSource.causeMobDamage(attacker),6);
				}
			}
		}
	}

	public static AxisAlignedBB getBox(BlockPos pos,int radius) {
		return new AxisAlignedBB(pos.add(-radius,-radius,-radius),pos.add(radius,radius,radius));
	}

		private void xp(LivingExperienceDropEvent e) {
		if (e.getAttackingPlayer() != null) {
			PlayerEntity player = e.getAttackingPlayer();
			A.getCurio(looter_badge, player).ifPresent(stack -> e.setDroppedExperience((int) (e.getDroppedExperience() * 1.5)));
			if (e.getEntityLiving() instanceof CreeperEntity && player.getHeldItemMainhand().getItem() == defuser_sword) {
				e.setDroppedExperience(e.getDroppedExperience() * 2);
			}
		}
	}

	private void onLoot(final LivingDropsEvent e) {
		Entity attacker = e.getSource().getTrueSource();
		if (attacker instanceof LivingEntity) {
			dropPurifiedSouls((LivingEntity) attacker, e);
			if (e.getEntityLiving() instanceof EndermanEntity && e.getEntityLiving().world.dimension instanceof EndDimension) {
				if (attacker instanceof PlayerEntity)
					dropObelisk((PlayerEntity) attacker, e);
			}
			if (e.getEntityLiving() instanceof BlazeEntity && e.getEntityLiving().world.dimension.isNether()) {
				if (attacker instanceof PlayerEntity)
					dropVotaicThread((PlayerEntity) attacker, e);
			}
		}
		//doesn't require mob kills
		if (e.getEntityLiving().world.getBiome(e.getEntityLiving().getPosition()).getCategory() == Biome.Category.FOREST) {
			dropCreeperLeather(e);
		}

		if (e.getEntityLiving() instanceof EnderDragonEntity) {
			dropOrhime(e);
		}
	}

	private void dropOrhime(LivingDropsEvent e) {
		double chance = 1 / 150d;
		LivingEntity victim = e.getEntityLiving();
		if (victim.getRNG().nextDouble() < chance) {
			e.getDrops().add(new ItemEntity(victim.world, victim.getPosX(), victim.getPosY(), victim.getPosZ(), new ItemStack(orhime)));
		}
	}

	private void dropVotaicThread(PlayerEntity attacker, LivingDropsEvent e) {
		double chance = 1 / 150d;
		if (attacker.getRNG().nextDouble() < chance) {
			e.getDrops().add(new ItemEntity(attacker.world, attacker.getPosX(), attacker.getPosY(), attacker.getPosZ(), new ItemStack(voltic_thread)));
		}
	}

	private void dropCreeperLeather(LivingDropsEvent e) {
		LivingEntity victim = e.getEntityLiving();
		double chance = 1 / 1000d;
		if (victim.world.rand.nextDouble() < chance) {
			e.getDrops().add(new ItemEntity(victim.world, victim.getPosX(), victim.getPosY(), victim.getPosZ(), new ItemStack(creeper_leather)));
		}
	}

	private void dropObelisk(PlayerEntity attacker, LivingDropsEvent e) {
		double chance = 1 / 300d;
		if (attacker.getRNG().nextDouble() < chance) {
			e.getDrops().add(new ItemEntity(attacker.world, attacker.getPosX(), attacker.getPosY(), attacker.getPosZ(), new ItemStack(dark_obelisk)));
		}
	}

	private void dropPurifiedSouls(LivingEntity attacker, LivingDropsEvent e) {
		double chance = 0;
		if (attacker.getHeldItemMainhand().getItem() == Curiosities.holy_knife) {
			chance = 1 / 50d;
		}
		if (attacker.getHeldItemMainhand().getItem() == Curiosities.orhime) {
			chance = 1 / 30d;
		}

		if (attacker.getRNG().nextDouble() < chance) {
			e.getDrops().add(new ItemEntity(attacker.world, attacker.getPosX(), attacker.getPosY(), attacker.getPosZ(), new ItemStack(purified_soul)));
		}
	}

	private void onAnvilUpdate(AnvilUpdateEvent e) {

	}

	private void setup(final FMLCommonSetupEvent event) {
		immunity_items = Arrays.asList(bezoar, vitamins, eye_patch, hyper_pill);
	}

	private static final List<Effect> effects = Arrays.asList(Effects.SLOWNESS, Effects.WEAKNESS, Effects.BLINDNESS, Effects.NAUSEA);

	private void onPotionAdd(PotionEvent.PotionAddedEvent e) {
		if (e.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) e.getEntityLiving();
			A.getCurio(eldritch_mask, player).ifPresent(stack -> effects.forEach(player::removeActivePotionEffect));

			immunity_items.forEach(item -> A.getCurio(item, player).ifPresent(stack -> {
				ImmunityItem immune = (ImmunityItem) stack.getItem();
				player.removePotionEffect(immune.immuneTo);
			}));
		}
	}

	private void miningSpeed(PlayerEvent.BreakSpeed e) {
		A.getCurio(miner_gloves, e.getPlayer()).ifPresent(stack -> e.setNewSpeed(e.getOriginalSpeed() * 1.05f));
	}

	private void blockplace(BlockEvent.EntityPlaceEvent e) {
		if (e.getEntity() instanceof PlayerEntity) {
			A.getCurio(builder_gloves, (PlayerEntity) e.getEntity()).ifPresent(stack ->
							stack.damageItem(1, (PlayerEntity) e.getEntity(), livingEntity -> livingEntity.sendBreakAnimation(EquipmentSlotType.FEET)));
		}
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
	}

	public static final List<Item> items = new ArrayList<>();

	private static <T extends IForgeRegistryEntry<T>> T register(T obj, String name, IForgeRegistry<T> registry) {
		registry.register(obj.setRegistryName(new ResourceLocation(MODID, name)));
		if (obj instanceof Item)
			items.add((Item) obj);
		return obj;
	}
}
