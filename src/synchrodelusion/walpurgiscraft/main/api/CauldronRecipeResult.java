package synchrodelusion.walpurgiscraft.main.api;

import org.bukkit.Material;

import synchrodelusion.walpurgiscraft.main.cauldron.Cauldron;

public interface CauldronRecipeResult {
	public void effect(Cauldron caul, Material[] usedMaterials);
}
