package com.github.fatfrido.fatpack.commands;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.fatfrido.fatpack.FatPack;

public class CDay implements CommandExecutor{
	
	private FatPack plugin;
	 
	public CDay(FatPack plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		Player player = null;
		if (cs instanceof Player) {
			player = (Player) cs;
		}
		if(cmd.getName().equalsIgnoreCase("day") || cmd.getName().equalsIgnoreCase("tag")){
			if(player == null){
				if(args.length == 1){
					World world = this.plugin.getServer().getWorld(args[0]);
					world.setTime(24000);
					this.plugin.getServer().broadcastMessage(ChatColor.GOLD + "Dank dem " + ChatColor.RED + "Servergott" + ChatColor.GOLD + " beginnt ein neuer Tag!");
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
