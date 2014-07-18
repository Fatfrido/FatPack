package com.github.fatfrido.fatpack.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.fatfrido.fatpack.FatPack;
import com.github.fatfrido.fatpack.LangManager;

public class CDay implements CommandExecutor{
	
	static JavaPlugin plugin = FatPack.getPlugin(FatPack.class);

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		
		LangManager langManager = new LangManager();
		FileConfiguration lang = langManager.langFileApp();
		String path1 = "messages.commands.day";
		
		Player player = null;
		if (cs instanceof Player) {
			player = (Player) cs;
		}
		if(cmd.getName().equalsIgnoreCase("day") || cmd.getName().equalsIgnoreCase("tag")){
			if(player == null){
				if(args.length == 1){
					String argworld = args[0];
					World world = Bukkit.getServer().getWorld(argworld);
					world.setTime(24000);
					plugin.getServer().broadcastMessage(lang.getString(path1 + ".console"));
					return true;
				}else if(args.length < 1){
					cs.sendMessage(ChatColor.RED + "Argument fehlt! " + ChatColor.GRAY +"/" + cmd.getName() + " <Weltname>");
					return false;
				}else {
					cs.sendMessage(ChatColor.RED + "zu viele Argumente! " + ChatColor.GRAY +"/" + cmd.getName() + " <Weltname>");
				}
			}else {
				player.getWorld().setTime(24000);
				this.plugin.getServer().broadcastMessage(ChatColor.GOLD + "Dank " + ChatColor.RED + player.getName() + ChatColor.GOLD + " beginnt ein neuer Tag!");
				return true;
			}
		}
		return false;
	}
}
