package synchrodelusion.walpurgiscraft.main.cauldron;

import org.bukkit.Location;

public class Cauldron {
	private final Location loc;
	private final CauldronInside inside;
	public Cauldron(Location loc) {
		this.loc=loc;
		this.inside=new CauldronInside(this);
	}
	public Location getLocation() {
		return loc;
	}
	public CauldronInside getInside() {
		return inside;
	}
}
