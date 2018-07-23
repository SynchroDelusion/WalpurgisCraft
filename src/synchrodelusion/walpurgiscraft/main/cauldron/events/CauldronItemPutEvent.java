package synchrodelusion.walpurgiscraft.main.cauldron.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import synchrodelusion.walpurgiscraft.main.cauldron.Cauldron;

public class CauldronItemPutEvent extends Event implements Cancellable{
	private static final HandlerList handlers = new HandlerList();
	private final Cauldron cauldron;
	private boolean cancelled=false;
	public CauldronItemPutEvent(Cauldron cauldron) {
		this.cauldron=cauldron;
	}
	
	public HandlerList getHandlers() {
	    return handlers;
	}

	public static HandlerList getHandlerList() {
	    return handlers;
	}
	public Cauldron getCauldron() {
		return cauldron;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean newValue) {
		cancelled=newValue;
	}
}
