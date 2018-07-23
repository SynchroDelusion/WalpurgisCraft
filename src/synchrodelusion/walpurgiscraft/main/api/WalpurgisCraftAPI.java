package synchrodelusion.walpurgiscraft.main.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

public class WalpurgisCraftAPI {
	private final List<CauldronRecipe> recipes=new ArrayList<CauldronRecipe>();
	public boolean isUsed(Material m) {
		for(CauldronRecipe recipe:recipes) {
			Material[] l=recipe.getList();
			for(int i=0; i<=2; i++) {
				if(l[i]==m) return true;
			}
		}
		return false;
	}
	public void register(CauldronRecipe recipe) {
		if(!recipes.contains(recipe)) {
			recipes.add(recipe);
		}
	}
	public CauldronRecipeResult getResult(Material[] mats) {
		Arrays.sort(mats);
		for(CauldronRecipe recipe: recipes) {
			Material[] comp=recipe.getList();
			if((mats[0]==comp[0])&&(mats[1]==comp[1])&&(mats[2]==comp[2])) {
				return recipe.getResult();
			}
		}
		return null;
	}
}
