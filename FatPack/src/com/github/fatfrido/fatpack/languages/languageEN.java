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
		
		String pathgeneral = "messages.general.logger.";
		encfg.addDefault(pathgeneral + "pluginEnabled", "successfully enabled!");
		encfg.addDefault(pathgeneral + "pluginVersion", "version");
		encfg.addDefault(pathgeneral + "noTranslation", "The selected language file is missing at least one String!");
		encfg.addDefault(pathgeneral + "invalidURL", "The URL is not valid! Correct it in config.yml");
		encfg.addDefault(pathgeneral + "noConsole", "&4This command is only available for the players!");
		
		String pathcday = "messages.commands.day.";
		encfg.addDefault(pathcday + "console", "&7Praise the &4Servergod&7 - a new day has just begun!");
		encfg.addDefault(pathcday + "player", "&7A new day has just begun.");
		encfg.addDefault(pathcday + "tooFewArgs", "&4Argument is missing: <worldname>");
		encfg.addDefault(pathcday + "tooManyArgs", "&4Too many arguments!");
		encfg.addDefault(pathcday + "permission", "&4You do not have the power to influence star system!");
		encfg.addDefault(pathcday + "worldNotFound", "&4World does not exist!");
		
		String pathperms = "permissions.groups.";
		encfg.addDefault(pathperms + "admins", "Nobiles");
		encfg.addDefault(pathperms + "general", "Citizens");
		encfg.addDefault(pathperms + "newbies", "Immigrants");
		
		String pathcerts = "certificates.";
		encfg.addDefault(pathcerts + "messages.missingRequired", "&4You do not have the required certificate to buy this one! Missing certificate: &7");
		encfg.addDefault(pathcerts + "messages.invalidCertificate", "&4This certificate does not exist!");
		encfg.addDefault(pathcerts + "messages.hasAlready", "&7You already have this certificate!");
		encfg.addDefault(pathcerts + "messages.gotCertificate", "&7Congratulations, You have just got a new certificate: &6");
		encfg.addDefault(pathcerts + "tnt", "This certificate allows the owner to use TNT for mining operations. It is not allowed "
				+ "to blow up buildings. A valid mining license is required.");
		encfg.addDefault(pathcerts + "nether", "With this license you are allowed to enter the nether and do whatever you want in it, except it is against the law.");
		encfg.addDefault(pathcerts + "mining", "As the owner of this certificate you are allowed to create mines and gather ressources there. "
				+ "Deep-mining is not included, it is still forbidden for you!");
		encfg.addDefault(pathcerts + "deepmining", "With this certificate you are allowed to mine in the deepest layers of the world. This is the best way to find diamonds!");
		
		String pathcbuy = "messages.commands.buy.";
		encfg.addDefault(pathcbuy + "notPlayer", "&4Only a player can use this command!");
		encfg.addDefault(pathcbuy + "tooFewArgs", "&4At least one Argument is missing.");
		encfg.addDefault(pathcbuy + "notEnoughMoney", "&4You do not have enough money to buy this!");
		
		String pathccertificates = "messages.commands.certificates.";
		encfg.addDefault(pathccertificates + "certificates", "&6Your certificates are: &7");
		encfg.addDefault(pathccertificates + "nocertificates", "&7You do not have any certificates currently! Buy one using: /buy certificates <certificate> ");
		encfg.addDefault(pathccertificates + "removed", "&7successfully removed.");
		encfg.addDefault(pathccertificates + "playernotfound", "&4The player does not exist or cannot be found!");
		encfg.addDefault(pathccertificates + "createname", "&7You are going to create a new certificate. First enter its name using: &6/certificate create name <name>");
		encfg.addDefault(pathccertificates + "createprice", "&7Now enter the price a player has to pay for this certificate (e.g. 5600). Use: &6/certificate create price <certificate-name> <price>");
		encfg.addDefault(pathccertificates + "createrequirements", "&7Type in the reqired certificate to buy the new one. If you dont want any requirements, then type in &4null&7. Usage: &6/certificate create requirements <certificate-name> <certificate|null>");
		encfg.addDefault(pathccertificates + "createperms", "&7Now you can add some permissions to your certificate: &6/certificate create perms <certificate-name> <block.mine, use.lever|null>");
		encfg.addDefault(pathccertificates + "createdesc", "&7You can add a short description for this certificate: &6/certificate create desc <certificate-name> <your short description>");
		encfg.addDefault(pathccertificates + "createexe", "&7To finish and actually create the new certificate, type following: &6/certificate create name <certificate-name>");
		encfg.addDefault(pathccertificates + "createend", "&7Your certificate has been created!");
		encfg.addDefault(pathccertificates + "enternumber", "&4Please enter a number!");
		encfg.addDefault(pathccertificates + "existsalready", "&4The certificate exists already!");
		
		encfg.options().copyDefaults(true);
		try {
			encfg.save(enfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

