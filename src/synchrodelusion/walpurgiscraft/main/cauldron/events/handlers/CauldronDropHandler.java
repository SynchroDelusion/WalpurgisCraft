package synchrodelusion.walpurgiscraft.main.cauldron.events.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitTask;

import synchrodelusion.walpurgiscraft.main.WalpurgisCraft;

public class CauldronDropHandler {
	private final Item item;
	private final BukkitTask task;
	public CauldronDropHandler(Item item, String player) {
		this.item=item;
		task=Bukkit.getScheduler().runTaskTimer(WalpurgisCraft.getInst(), new Runnable() {
			@Override
			public void run() {
				if((item==null)||(item.isValid()==false)||(item.isDead()==true)) {
					stop();
					return;
				}
				if(item.isOnGround()) {
					WalpurgisCraft.getInst().getCauldronHandler().getEventHandler().itemOnGround(item,player);
					stop();
					return;
				}
			}
			
		}, 0, 10);
	}
	public Item getItem() {
		return item;
	}
	private void stop() {
		task.cancel();
	}
	
}
