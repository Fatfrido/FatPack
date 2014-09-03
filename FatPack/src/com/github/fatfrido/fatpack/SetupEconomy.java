package com.github.fatfrido.fatpack;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class SetupEconomy {
	
	LangManager lm = new LangManager();
	FileConfiguration lang = lm.langFileApp();
	private static JavaPlugin plugin = FatPack.getPlugin(FatPack.class);
	private Economy econ = FatPack.economy;
	
	public void createAcc(OfflinePlayer player){
		econ.createPlayerAccount(player);
		econ.depositPlayer(player, plugin.getConfig().getDouble("config.options.economy.startBalance"));
	}
}
