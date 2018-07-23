package synchrodelusion.walpurgiscraft.main.api;

import java.util.Arrays;

import org.bukkit.Material;

public class CauldronRecipe {
	private final Material[] list;
	private final CauldronRecipeResult result;
	public CauldronRecipe(CauldronRecipeResult result, Material f, Material s, Material t) {
		this.result=result;
		list=new Material[3];
		list[0]=f;
		list[1]=s;
		list[2]=t;
		Arrays.sort(list);
	}
	public Material[] getList(){
		return list;
	}
	public CauldronRecipeResult getResult() {
		return result;
	}
}
