package synchrodelusion.walpurgiscraft.main.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

public class WalpurgisCraftAPI {
	private final List<CauldronRecipe> recipes=new ArrayList<CauldronRecipe>();
	public boolean isUsed(Material m) {
		for(CauldronRecipe recipe:recipes) {
			if(recipe.getFirst().equals(m)) return true;
			if(recipe.getSecond().equals(m)) return true;
			if(recipe.getThird().equals(m)) return true;
		}
		return false;
	}
	public void register(CauldronRecipe recipe) {
		if(!recipes.contains(recipe)) {
			recipes.add(recipe);
		}
	}
	public CauldronRecipeResult getResult(Material f, Material s, Material t) {
		Material[] list=new Material[3];
		list[0]=f;
		list[1]=s;
		list[2]=t;
		Arrays.sort(list);
		for(CauldronRecipe recipe: recipes) {
			Material[] comp=recipe.getList();
			if(list[0]==comp[0]) {
				if(list[1]==comp[1]) {
					if(list[2]==comp[2]) {
						return recipe.getResult();
					}
				}
			}
		}
		return null;
	}
	/*@SuppressWarnings("deprecation")
	protected static Material[] sort(Material[] toSort) {
		int[] a=new int[toSort.length];
		for(int i=0; i<=toSort.length-1; i++) {
			a[i]=toSort[i].getId();
		}
		Arrays.sort(a);
		Material[] b=new Material[a.length];
		for(int i=0; i<=a.length-1; i++) {
			b[i]=Material.getMaterial(a[i]);
		}
		return b;
	}*/
}
