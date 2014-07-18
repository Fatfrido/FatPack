package com.github.fatfrido.fatpack.languages;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.fatfrido.fatpack.FatPack;

public class languageEN {
	
	static JavaPlugin plugin = FatPack.getPlugin(FatPack.class);
	
	public static void en() {
		
		File enfile = new File("plugins/FatPack/lang", "en.yml");
		FileConfiguration encfg = YamlConfiguration.loadConfiguration(enfile);
		
		String pathgeneral = "messages.general.logger";
		encfg.addDefault(pathgeneral + ".pluginenabled", "successfully enabled!");
		encfg.addDefault(pathgeneral + ".plugindisabled", "disabled!");
		encfg.addDefault(pathgeneral + ".pluginversion", "version");
		
		String pathcday = "messages.commands.day";
		encfg.addDefault(pathcday + ".console", "&6Praise the &4Servergod&6 - a new day has just begun!");
		
		encfg.options().copyDefaults(true);
		try {
			encfg.save(enfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

