package synchrodelusion.walpurgiscraft.main.api;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.itemnbtapi.NBTItem;
import synchrodelusion.walpurgiscraft.main.cauldron.Cauldron;

public interface CauldronRecipeResult {
	public void effect(Cauldron caul, Material[] usedMaterials);
	default ItemStack setNBT(ItemStack item) {
		NBTItem nbt=new NBTItem(item);
		nbt.setBoolean("walpurgis_drop", true);
		return nbt.getItem();
	}
}
