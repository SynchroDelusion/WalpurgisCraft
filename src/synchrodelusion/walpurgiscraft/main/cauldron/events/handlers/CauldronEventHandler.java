package synchrodelusion.walpurgiscraft.main.cauldron.events.handlers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import de.tr7zw.itemnbtapi.NBTItem;
import synchrodelusion.walpurgiscraft.main.WalpurgisCraft;
import synchrodelusion.walpurgiscraft.main.cauldron.Cauldron;
import synchrodelusion.walpurgiscraft.main.cauldron.events.CauldronBreakEvent;
import synchrodelusion.walpurgiscraft.main.cauldron.events.CauldronPlaceEvent;

public class CauldronEventHandler implements Listener{
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(e.getItemInHand().getType().equals(Material.CAULDRON_ITEM)) {
			if(e.isCancelled()) return;
			NBTItem nbt=new NBTItem(e.getItemInHand());
			if(nbt.getBoolean("walpurgis_cauldron")) {
				CauldronPlaceEvent event=new CauldronPlaceEvent(e.getBlock(), e);
				Bukkit.getPluginManager().callEvent(event);
				Cauldron caul=WalpurgisCraft.getInst().getCauldronHandler().createCauldron(e.getBlock().getLocation());
				if(event.isCancelled()) {
					e.setCancelled(true);
					WalpurgisCraft.getInst().getCauldronHandler().removeCauldron(caul);
				}
			}
		}
	}
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(e.getBlock().getType().equals(Material.CAULDRON)) {
			if(e.isCancelled()) return;
			Cauldron caul=WalpurgisCraft.getInst().getCauldronHandler().getCauldron(e.getBlock().getLocation());
			if(caul!=null) {
				CauldronBreakEvent event=new CauldronBreakEvent(e.getBlock(),e);
				Bukkit.getPluginManager().callEvent(event);
				if(event.isCancelled()==false) {
					WalpurgisCraft.getInst().getCauldronHandler().removeCauldron(caul);
					e.setDropItems(false);
					ItemStack item=WalpurgisCraft.getInst().getCauldronHandler().createCauldron();
					if(e.getPlayer().getGameMode()!=GameMode.CREATIVE){
						e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), item);
					}
				}else {
					e.setCancelled(true);
				}
			}
		}
	}
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		Item drop=e.getItemDrop();
		if(WalpurgisCraft.getInst().getAPI().isUsed(drop.getItemStack().getType())) {
			NBTItem nbt=new NBTItem(drop.getItemStack());
			if(nbt!=null) {
				if(nbt.getBoolean("walpurgis_drop")) {
					return;
				}
			}
			if(drop.isOnGround()) itemOnGround(drop, e.getPlayer().getName());
			else {
				new CauldronDropHandler(drop, e.getPlayer().getName());
			}
		}
	}
	protected void itemOnGround(Item drop, String player) {
		Cauldron caul=WalpurgisCraft.getInst().getCauldronHandler().insideCauldron(drop.getLocation());
		if(caul!=null) {
			Player p=Bukkit.getPlayer(player);
			if(caul.getInside().getFreeSpace()==0) {
				drop.setGlowing(true);
				if((p!=null)&&(p.isOnline())) {
					drop.teleport(p.getLocation().add(0, 1, 0));
					return;
				}
				drop.setGravity(false);
				drop.setVelocity(new Vector(0,0,0));
				drop.teleport(caul.getLocation().clone().add(0.5, 1, 0.5));
				return;
			}
			ItemStack item=drop.getItemStack();
			drop.remove();
			int amount=item.getAmount();
			if(caul.getInside().getFreeSpace()<amount) {
				ItemStack toDrop=item.clone();
				toDrop.setAmount(amount-caul.getInside().getFreeSpace());
				if((p!=null)&&(p.isOnline())) {
					p.getWorld().dropItem(p.getLocation().add(0, 1, 0), toDrop);
				}else {
					Item dropped=caul.getLocation().getWorld().dropItem(caul.getLocation().clone().add(0, 1, 0), toDrop);
					dropped.setGravity(false);
					dropped.setVelocity(new Vector(0,0,0));
					dropped.teleport(caul.getLocation().clone().add(0.5, 1, 0.5));
				}
				amount=caul.getInside().getFreeSpace();
			}
			for(int i=0; i<=amount-1; i++) {
				caul.getInside().put(item.getType());
			}
			return;
		}
	}
	
}
