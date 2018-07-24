package synchrodelusion.walpurgiscraft.main.cauldron;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import de.tr7zw.itemnbtapi.NBTItem;

public class CauldronShapedRecipe {
	private final ItemStack cauldron;
	private final ShapedRecipe cauldronRecipe;
	public CauldronShapedRecipe(JavaPlugin plugin) {
		cauldron=createCauldron();
		cauldronRecipe=createRecipe(plugin,plugin.getName());
		plugin.getServer().addRecipe(cauldronRecipe);
	}
	protected ItemStack createCauldron() {
		ItemStack item=new ItemStack(Material.CAULDRON_ITEM);
		final ItemMeta meta=item.getItemMeta();
		meta.setDisplayName("§bMagiczny Kocio³ek");
		item.setItemMeta(meta);
		NBTItem nbt=new NBTItem(item);
		nbt.setBoolean("walpurgis_cauldron", true);
		item=nbt.getItem();
		return item;
	}
	private ShapedRecipe createRecipe(JavaPlugin plugin, String pluginName) {
		NamespacedKey key=new NamespacedKey(plugin,pluginName);
		ShapedRecipe recipe=new ShapedRecipe(key, cauldron);
		recipe.shape("AAA","AEA","ACA");
		recipe.setIngredient('A', Material.AIR);
		recipe.setIngredient('E', Material.ENCHANTMENT_TABLE);
		recipe.setIngredient('C', Material.CAULDRON_ITEM);
		return recipe;
	}
}
