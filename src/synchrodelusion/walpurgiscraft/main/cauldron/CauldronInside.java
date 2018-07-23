package synchrodelusion.walpurgiscraft.main.cauldron;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import synchrodelusion.walpurgiscraft.main.WalpurgisCraft;
import synchrodelusion.walpurgiscraft.main.api.CauldronRecipeResult;
import synchrodelusion.walpurgiscraft.main.cauldron.events.CauldronItemPutEvent;

public class CauldronInside {
	private final Cauldron caul;
	private final List<Material> contents=new ArrayList<Material>();
	public CauldronInside(Cauldron caul) {
		this.caul=caul;
	}
	public List<Material> getContents(){
		return contents;
	}
	public int getFreeSpace() {
		return 3-contents.size();
	}
	public int getFilledSpace() {
		return contents.size();
	}
	public boolean canPut() {
		if(contents.size()<3) return true;
		else return false;
	}
	public void clear() {
		contents.clear();
	}
	public void put(Material m) {
		if(canPut()) {
			CauldronItemPutEvent event=new CauldronItemPutEvent(caul);
			Bukkit.getPluginManager().callEvent(event);
			if(event.isCancelled()==false) {
				Location loc=caul.getLocation().clone();
				if(getFreeSpace()!=1) loc.getWorld().playSound(loc, Sound.BLOCK_LAVA_EXTINGUISH, 0.5F, 2F);
				contents.add(m);
				if(contents.size()==3) {
					checkCombination();
				}
			}
		}
	}
	private void checkCombination() {
		Location sl=caul.getLocation().clone();
		sl.getWorld().playSound(sl, Sound.ITEM_BUCKET_EMPTY_LAVA, 1F, 2F);
		Material[] c=new Material[3];
		c=contents.toArray(new Material[3]);
		CauldronRecipeResult result=WalpurgisCraft.getInst().getAPI().getResult(c);
		if(result==null) {
			for(int i=0; i<=2; i++) {
				ItemStack item=new ItemStack(contents.get(i));
				Location loc=caul.getLocation().getBlock().getLocation().clone();
				loc.add(0.5, 0, 0.5);
				Item ent=caul.getLocation().getWorld().dropItem(loc.clone().add(0, 1, 0), item);
				ent.setGravity(false);
				ent.setVelocity(new Vector(0,0,0));
				ent.setGlowing(true);
				ent.teleport(loc.clone().add(0, 1, 0));
			}
			clear();
			return;
		}
		result.effect(caul,c);
		clear();
	}
}
