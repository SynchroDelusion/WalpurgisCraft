package synchrodelusion.walpurgiscraft.main.api;

import java.util.Arrays;

import org.bukkit.Material;

public class CauldronRecipe {
	private final Material first;
	private final Material second;
	private final Material third;
	private final Material[] list;
	private final CauldronRecipeResult result;
	public CauldronRecipe(CauldronRecipeResult result, Material f, Material s, Material t) {
		first=f;
		second=s;
		third=t;
		this.result=result;
		Material[] l=new Material[3];
		l[0]=f;
		l[1]=s;
		l[2]=t;
		Arrays.sort(l);
		list=l;
	}
	public Material[] getList(){
		return list;
	}
	public Material getFirst() {
		return first;
	}
	public Material getSecond() {
		return second;
	}
	public Material getThird() {
		return third;
	}
	public CauldronRecipeResult getResult() {
		return result;
	}
}
