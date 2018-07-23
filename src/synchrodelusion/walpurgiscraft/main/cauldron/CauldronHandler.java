package synchrodelusion.walpurgiscraft.main.cauldron;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import synchrodelusion.walpurgiscraft.main.WalpurgisCraft;
import synchrodelusion.walpurgiscraft.main.cauldron.events.handlers.CauldronEventHandler;

public class CauldronHandler {
	private final CauldronConfiguration config;
	private final List<Cauldron> cauldronList;
	private final CauldronShapedRecipe recipe;
	private final CauldronEventHandler eventHandler;
	public CauldronHandler() {
		config=new CauldronConfiguration();
		cauldronList=loadCauldrons(config.getYaml());
		recipe=new CauldronShapedRecipe(WalpurgisCraft.getInst());
		eventHandler=new CauldronEventHandler();
		Bukkit.getPluginManager().registerEvents(eventHandler, WalpurgisCraft.getInst());
	}
	public CauldronEventHandler getEventHandler() {
		return eventHandler;
	}
	public Cauldron createCauldron(Location loc) {
		Cauldron caul=new Cauldron(loc);
		cauldronList.add(caul);
		return caul;
	}
	public void removeCauldron(Cauldron caul) {
		if(cauldronList.contains(caul)) {
			cauldronList.remove(caul);
		}
	}
	public Cauldron getCauldron(Location loc) {
		for(Cauldron caul:cauldronList) {
			if(caul.getLocation().equals(loc)) {
				return caul;
			}
		}
		return null;
	}
	public CauldronConfiguration getConfig() {
		return config;
	}
	public List<Cauldron> getCauldrons(){
		return cauldronList;
	}
	public void close() {
		YamlConfiguration conf=saveCauldrons(cauldronList);
		config.save(conf);
	}
	public Cauldron insideCauldron(Location loc) {
		for(Cauldron caul:cauldronList) {
			if(equalsLocation(caul.getLocation(),loc)) {
				return caul;
			}
		}
		return null;
	}
	protected boolean equalsLocation(Location one, Location two) {
		int one_x=one.getBlockX();
		int one_y=one.getBlockY();
		int one_z=one.getBlockZ();
		
		int two_x=two.getBlockX();
		int two_y=two.getBlockY();
		int two_z=two.getBlockZ();
		if((one_x==two_x)&&(one_y==two_y)&&(one_z==two_z)){
			return true;
		}
		return false;
	}
	private List<Cauldron> loadCauldrons(YamlConfiguration config){
		List<Cauldron> cauldrons=new ArrayList<Cauldron>();
		int it=0;
		while(config.contains(String.valueOf(it))) {
			String directory=String.valueOf(it);
			World world=Bukkit.getWorld(config.getString(directory+".world"));
			int x=config.getInt(directory+".x");
			int y=config.getInt(directory+".y");
			int z=config.getInt(directory+".z");
			Location location=new Location(world,x,y,z);
			Cauldron cauldron=new Cauldron(location);
			cauldrons.add(cauldron);
			it++;
		}
		return cauldrons;
	}
	private YamlConfiguration saveCauldrons(List<Cauldron> cauldrons) {
		YamlConfiguration conf=new YamlConfiguration();
		int it=0;
		for(Cauldron caul:cauldrons) {
			String directory=String.valueOf(it);
			String world=caul.getLocation().getWorld().getName();
			int x=caul.getLocation().getBlockX();
			int y=caul.getLocation().getBlockY();
			int z=caul.getLocation().getBlockZ();
			conf.set(directory+".world", world);
			conf.set(directory+".x", x);
			conf.set(directory+".y", y);
			conf.set(directory+".z", z);
			it++;
		}
		return conf;
	}
	public ItemStack createCauldron() {
		return recipe.createCauldron();
	}
	public static Vector vectorBetween(Location from, Location to) {
		Vector f= new Vector(from.getX(), from.getY(), from.getZ());
		Vector t  = new Vector(to.getX(), to.getY(), to.getZ());
		 
		Vector vector = t.subtract(f);
		return vector;
	}
}
