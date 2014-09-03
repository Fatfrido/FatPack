package com.github.fatfrido.fatpack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerManager {
	
	static JavaPlugin plugin = FatPack.getPlugin(FatPack.class);
	
	public static void createPlayerData(Player player){
		File file = new File("plugins/FatPack/users", player.getName() + ".yml");
		FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);
		
		playerData.addDefault("lastLogin", "");
		playerData.addDefault("group", "");
		playerData.addDefault("certificates", "");
		
		playerData.options().copyDefaults(true);
		try {
			playerData.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<File> CheckPlayerData(File dir, Player player){
    	File[] files = dir.listFiles();
    	ArrayList<File> matches = new ArrayList<File> ();
    	if(files != null){
    		for(int i = 0; i < files.length; i++){
    			if(files[i].getName().equalsIgnoreCase(player.getName() + ".yml")){
    				matches.add(files[i]);
    			}
    			if(files[i].isDirectory()){
    				matches.addAll(CheckPlayerData(files[i], player));
    			}
    		}
    	}else{
    		plugin.getLogger().info("§4Player data is not available. Creating new one.");
    		createPlayerData(player);
    		return null;
    	}
		return matches;
    }
    
    public static File GetRightMatch(ArrayList<File> matches, String name){
    	if(matches.size() > 1){
    		plugin.getLogger().info("§4There are more than one Files with name: " + name + ".yml!");
    		return null;
    	}else if(matches.size() == 0){
    		return null;
    	}else {
			File langFile = matches.get(0);
    		return langFile;
		}
    }

    
    public static FileConfiguration getPlayerData(Player player){
    	ArrayList<File> matches = CheckPlayerData(plugin.getDataFolder(), player);
    	File file = GetRightMatch(matches, player.getName());
    	if(file == null){
    		return null;
    	}else {
    		FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);
    		return playerData;
    	}
    }
    
    public static boolean savePlayerData(FileConfiguration pd, Player player){
    	ArrayList<File> matches = CheckPlayerData(plugin.getDataFolder(), player);
    	File file = GetRightMatch(matches, player.getName());
    	try {
			pd.save(file);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
    }
}
