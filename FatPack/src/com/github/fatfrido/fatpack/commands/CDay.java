package com.github.fatfrido.fatpack.commands;

import org.bukkit.Bukkit;
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
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args){
		
		LangManager lm = new LangManager();
		FileConfiguration lang = lm.langFileApp();
		String path1 = "messages.commands.day.";
		
		Player player = null;
		if (cs instanceof Player) {
			player = (Player) cs;
		}
		if(cmd.getName().equalsIgnoreCase("day") || cmd.getName().equalsIgnoreCase("tag")){
			if(player == null){
				if(args.length == 1){
					String argworld = args[0];
					World world = Bukkit.getWorld(argworld);
					if(world != null){
						world.setTime(24000);
						plugin.getServer().broadcastMessage(lm.stringGetter(lang, path1 + "console"));
						return true;
					}else {
						cs.sendMessage(lm.stringGetter(lang, path1 + "worldNotFound"));
						return false;
					}
				}else if(args.length < 1){
					cs.sendMessage(lm.stringGetter(lang, path1 + "tooFewArgs"));
					return false;
				}else {
					cs.sendMessage(lm.stringGetter(lang, path1 + "tooManyArgs"));
					return false;
				}
			}else {
				if(player.hasPermission("day.use")){
					if(args.length == 1){
						String argworld = args[0];
						World world = Bukkit.getWorld(argworld);
						if(world != null){
							world.setTime(24000);
							plugin.getServer().broadcastMessage(lm.stringGetter(lang, path1 + "player"));
							return true;
						}else {
							cs.sendMessage(lm.stringGetter(lang, path1 + "worldNotFound"));
							return false;
						}
					}else if(args.length < 1){
						player.getWorld().setTime(24000);
						Bukkit.getServer().broadcastMessage(lm.stringGetter(lang, path1 + "player"));
						return true;
					}else {
						player.sendMessage(lm.stringGetter(lang, path1 + "tooManyArgs"));
						return false;
					}
				}else {
					player.sendMessage(lm.stringGetter(lang, path1 + "permission"));
					return false;
				}
			}
		}
		return false;
	}
}
