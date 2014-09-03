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
		
		String pathgeneral = "messages.general.logger.";
		decfg.addDefault(pathgeneral + "pluginEnabled", "wurde erfolgreich aktiviert!");
		decfg.addDefault(pathgeneral + "pluginVersion", "Version");
		decfg.addDefault(pathgeneral + "noTranslation", "Der ausgewaehlten Sprachdatei fehlt mindestens ein String!");
		decfg.addDefault(pathgeneral + "invalidURL", "Diese URL ist nicht gueltig! Aendere sie in der config.yml");
		decfg.addDefault(pathgeneral + "noConsole", "&4Dieser Befehl kann nur von einem Spieler ausgeführt werden!");
		
		String pathcday = "messages.commands.day.";
		decfg.addDefault(pathcday + "console", "&7Dank dem &4Servergott &7beginnt ein neuer Tag.");
		decfg.addDefault(pathcday + "player", "&7Ein neuer Tag beginnt...");
		decfg.addDefault(pathcday + "tooFewArgs", "&4Fehlendes Argument: <Weltname>");
		decfg.addDefault(pathcday + "tooManyArgs", "&4Zu viele Argumente!");
		decfg.addDefault(pathcday + "permission", "&4Du kannst das Sonnensystem nicht beeinflussen!");
		decfg.addDefault(pathcday + "worldNotFound", "&4Die angeforderte Welt existiert nicht!");
		
		String pathperms = "permissions.groups.";
		decfg.addDefault(pathperms + "admins", "Adel");
		decfg.addDefault(pathperms + "general", "B%urger");
		decfg.addDefault(pathperms + "newbies", "Immigranten");
		
		decfg.options().copyDefaults(true);
		try {
			decfg.save(defile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
