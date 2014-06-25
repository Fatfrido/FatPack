package com.github.fatfrido.fatpack;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.fatfrido.fatpack.commands.CDay;

public class FatPack extends JavaPlugin {
	
	@Override
	public void onDisable(){
		this.getLogger().info(ChatColor.GOLD + "[FatPack]" + ChatColor.RED + " konnte NICHT erfolgreich aktiviert werden!");
	}
	
	@Override
	public void onEnable(){
		this.getLogger().info("Version " + getDescription().getVersion());
		this.getLogger().info("wurde aktiviert!");
		
		//Commands
		this.getCommand("day").setExecutor(new CDay(this));
	}
}
