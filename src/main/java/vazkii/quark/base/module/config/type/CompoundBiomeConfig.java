package vazkii.quark.base.module.config.type;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import vazkii.quark.base.module.config.Config;
import vazkii.quark.base.module.config.ConfigFlagManager;

public class CompoundBiomeConfig extends AbstractConfigType implements IBiomeConfig {

	@Config(description = "Types of biomes this should spawn in. Must match both this and 'biomes' to spawn.")
	public BiomeTagConfig types;

	@Config(description = "Biome names this should spawn in. Must match both this and 'types' to spawn.")
	public StrictBiomeConfig biomes;

	private CompoundBiomeConfig(BiomeTagConfig types, StrictBiomeConfig biomes) {
		this.types = types;
		this.biomes = biomes;
	}

	@SafeVarargs
	public static CompoundBiomeConfig fromBiomeTags(boolean isBlacklist, TagKey<Biome>... typesIn) {
		return new CompoundBiomeConfig(new BiomeTagConfig(isBlacklist, typesIn), noSBC());
	}

	public static CompoundBiomeConfig fromBiomeTagStrings(boolean isBlacklist, String... typesIn) {
		return new CompoundBiomeConfig(new BiomeTagConfig(isBlacklist, typesIn), noSBC());
	}

	public static CompoundBiomeConfig fromBiomeReslocs(boolean isBlacklist, String... typesIn) {
		return new CompoundBiomeConfig(noBTC(), new StrictBiomeConfig(isBlacklist, typesIn));
	}

	public static CompoundBiomeConfig all() {
		return new CompoundBiomeConfig(noBTC(), noSBC());
	}

	@SuppressWarnings("unchecked")
	private static BiomeTagConfig noBTC() {
		return new BiomeTagConfig(true, new TagKey[0]);
	}


	private static StrictBiomeConfig noSBC() {
		return new StrictBiomeConfig(true, new String[0]);
	}

	@Override
	public void onReload(ConfigFlagManager flagManager) {
		types.onReload(flagManager);
		biomes.onReload(flagManager);
	}

	@Override
	public boolean canSpawn(ResourceLocation b) {
		return b != null && types.canSpawn(b) && biomes.canSpawn(b);
	}

}
