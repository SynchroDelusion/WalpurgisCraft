package synchrodelusion.walpurgiscraft.main.cauldron;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import synchrodelusion.walpurgiscraft.main.WalpurgisCraft;

public class CauldronConfiguration {
	private final File cauldronFile;
	public CauldronConfiguration() {
		if(!WalpurgisCraft.getInst().getDataFolder().exists()) {
			WalpurgisCraft.getInst().getDataFolder().mkdir();
		}
		cauldronFile=new File(WalpurgisCraft.getInst().getDataFolder(),"cauldrons.yml");
		if(!cauldronFile.exists()) {
			try {
				cauldronFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public YamlConfiguration getYaml() {
		return YamlConfiguration.loadConfiguration(cauldronFile);
	}
	public File getFile() {
		return cauldronFile;
	}
	public void save(YamlConfiguration source) {
		try {
			source.save(cauldronFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
