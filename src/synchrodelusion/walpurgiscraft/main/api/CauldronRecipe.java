package synchrodelusion.walpurgiscraft.main.api;

import java.util.Arrays;

import org.bukkit.Material;

public class CauldronRecipe {
	private final Material[] list;
	private final CauldronRecipeResult result;
	public CauldronRecipe(CauldronRecipeResult result, Material[] mats) {
		this.result=result;
		list=mats;
		Arrays.sort(list);
	}
	public Material[] getList(){
		return list;
	}
	public CauldronRecipeResult getResult() {
		return result;
	}
}
