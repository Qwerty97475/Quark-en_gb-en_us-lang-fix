package vazkii.quark.content.client.resources;

import net.minecraft.world.entity.EquipmentSlot;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum AttributeSlot {
	MAINHAND(EquipmentSlot.MAINHAND),
	OFFHAND(EquipmentSlot.OFFHAND),
	FEET(EquipmentSlot.FEET),
	LEGS(EquipmentSlot.LEGS),
	CHEST(EquipmentSlot.CHEST),
	HEAD(EquipmentSlot.HEAD),
	POTION("potion.whenDrank");

	@Nullable
	private final EquipmentSlot canonicalSlot;
	private final String locKey;

	AttributeSlot(@Nullable EquipmentSlot canonicalSlot, String locKey) {
		this.canonicalSlot = canonicalSlot;
		this.locKey = locKey;
	}

	AttributeSlot(String locKey) {
		this(null, locKey);
	}

	AttributeSlot(@Nonnull EquipmentSlot canonicalSlot) {
		this(canonicalSlot, "item.modifiers." + canonicalSlot.getName());
	}

	public boolean hasCanonicalSlot() {
		return canonicalSlot != null;
	}

	@Nonnull
	public EquipmentSlot getCanonicalSlot() {
		if (canonicalSlot == null)
			throw new IllegalStateException("Potions have no canonical slot");
		return canonicalSlot;
	}

	public String getTranslationKey() {
		return locKey;
	}

	public static AttributeSlot fromCanonicalSlot(@Nonnull EquipmentSlot slot) {
		switch (slot) {
			case OFFHAND -> {
				return OFFHAND;
			}
			case FEET -> {
				return FEET;
			}
			case LEGS -> {
				return LEGS;
			}
			case CHEST -> {
				return CHEST;
			}
			case HEAD -> {
				return HEAD;
			}
			default -> {
				return MAINHAND;
			}
		}
	}
}
