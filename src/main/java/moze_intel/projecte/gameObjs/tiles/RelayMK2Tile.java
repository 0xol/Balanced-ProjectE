package moze_intel.projecte.gameObjs.tiles;

import javax.annotation.Nonnull;
import moze_intel.projecte.gameObjs.EnumRelayTier;
import moze_intel.projecte.gameObjs.container.RelayMK2Container;
import moze_intel.projecte.gameObjs.registries.PEBlocks;
import moze_intel.projecte.gameObjs.registries.PETileEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class RelayMK2Tile extends RelayMK1Tile {

	public RelayMK2Tile() {
		super(PETileEntityTypes.RELAY_MK2.get(), 13, EnumRelayTier.MK2);
	}

	@Nonnull
	@Override
	public Container createMenu(int windowId, @Nonnull PlayerInventory playerInventory, @Nonnull PlayerEntity player) {
		return new RelayMK2Container(windowId, playerInventory, this);
	}

	@Nonnull
	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent(PEBlocks.RELAY_MK2.getBlock().getTranslationKey());
	}

	@Override
	protected double getBonusToAdd() {
		return 0.15;
	}
}