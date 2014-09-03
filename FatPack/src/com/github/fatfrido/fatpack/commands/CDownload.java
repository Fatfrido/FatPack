package com.github.fatfrido.fatpack.commands;

import java.io.FileNotFoundException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.github.fatfrido.fatpack.Downloader;
import com.github.fatfrido.fatpack.LangManager;

public class CDownload implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		
		LangManager lm = new LangManager();
		FileConfiguration lang = lm.langFileApp();
		String path1 = "messages.commands.download.";
		
		Player player = null;
		if (cs instanceof Player) {
			player = (Player) cs;
		}
		
		if(cmd.getName().equalsIgnoreCase("download")){
			if(cs instanceof Player){
				player.sendMessage(lm.stringGetter(lang, path1 + "notConsole"));
				return false;
			}else {
				if(args.length < 1){
					cs.sendMessage(lm.stringGetter(lang, path1 + "tooFewArgs"));
					return false;
				}else {
					if(args[0].equalsIgnoreCase("vault")){
						try {
							Downloader.getVault();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						return true;
					}else if(args[0].equalsIgnoreCase("pex")){
						try {
							Downloader.getPEX();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						return true;
					}else {
						if(!(args[0].equalsIgnoreCase("vault") && args[0].equalsIgnoreCase("pex"))){
							cs.sendMessage(lm.stringGetter(lang, path1 + "wrongArg"));
							return false;
						}else {
							return false;
						}
					}
				}
			}
		}
		
		return false;
	}
}
