package synchrodelusion.walpurgiscraft.main;

import org.bukkit.plugin.java.JavaPlugin;

import synchrodelusion.walpurgiscraft.main.api.WalpurgisCraftAPI;
import synchrodelusion.walpurgiscraft.main.cauldron.CauldronHandler;
public class WalpurgisCraft extends JavaPlugin{
	private static WalpurgisCraft inst;
	private CauldronHandler cauldronHandler;
	private WalpurgisCraftAPI api;
	public void onEnable() {
		inst=this;
		cauldronHandler=new CauldronHandler();
		api=new WalpurgisCraftAPI();
	}
	public void onDisable() {
		cauldronHandler.close();
	}
	public static WalpurgisCraft getInst() {
		return inst;
	}
	public CauldronHandler getCauldronHandler() {
		return cauldronHandler;
	}
	public WalpurgisCraftAPI getAPI() {
		return api;
	}
}