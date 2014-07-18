package com.github.fatfrido.fatpack.languages;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.fatfrido.fatpack.FatPack;

public class languageDE {
	
	static JavaPlugin plugin = FatPack.getPlugin(FatPack.class);
	
	public static void de() {
		
		File defile = new File("plugins/FatPack/lang", "de.yml");
		FileConfiguration decfg = YamlConfiguration.loadConfiguration(defile);
		
		String pathgeneral = "messages.general.logger";
		decfg.addDefault(pathgeneral + ".pluginactivated", "wurde erfolgreich aktiviert!");
		decfg.addDefault(pathgeneral + ".plugindeactivated", "wurde erfolgreich deaktiviert!");
		decfg.addDefault(pathgeneral + ".pluginversion", "Version");
		
		String pathcday = "messages.commands.day";
		decfg.addDefault(pathcday + ".console", "&6Dank dem &4Servergott &6beginnt ein neuer Tag.");
		
		decfg.options().copyDefaults(true);
		try {
			decfg.save(defile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
