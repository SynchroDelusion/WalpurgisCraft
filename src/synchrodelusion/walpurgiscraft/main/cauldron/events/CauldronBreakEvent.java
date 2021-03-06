package synchrodelusion.walpurgiscraft.main.cauldron.events;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;

public class CauldronBreakEvent extends Event implements Cancellable{
	private static final HandlerList handlers = new HandlerList();
	private final Block block;
	private boolean cancelled=false;
	private final BlockBreakEvent event;
	public CauldronBreakEvent(Block block, BlockBreakEvent event) {
		this.block=block;
		this.event=event;
	}
	
	public HandlerList getHandlers() {
	    return handlers;
	}

	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
	public Block getBlock() {
		return block;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean newValue) {
		cancelled=newValue;
	}
	
	public BlockBreakEvent getBlockBreakEvent() {
		return event;
	}
}
