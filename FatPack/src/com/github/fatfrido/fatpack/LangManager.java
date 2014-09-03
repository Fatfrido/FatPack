package com.github.fatfrido.fatpack;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class LangManager {
	
	static JavaPlugin plugin = FatPack.getPlugin(FatPack.class);
	
	//gets the lang from the general config file
	public String getLanguage(){
    	String lang = null;
    	lang = plugin.getConfig().getString("config.messages.language");
    	if(lang == null){
    		plugin.getLogger().info("§4No valid value in config: config.messages.language ! standard: en");
    		ServerStop();
    	}
    	return lang;
    }
    
	//checks if the wanted file exists and returns it; otherwise the server will be shut down.
    public ArrayList<File> CheckLang(File dir, String lang){
    	File[] files = dir.listFiles();
    	ArrayList<File> matches = new ArrayList<File> ();
    	if(files != null){
    		for(int i = 0; i < files.length; i++){
    			if(files[i].getName().equalsIgnoreCase(lang + ".yml")){
    				matches.add(files[i]);
    			}
    			if(files[i].isDirectory()){
    				matches.addAll(CheckLang(files[i], lang));
    			}
    		}
    	}else{
    		plugin.getLogger().info("§4Cannot find language files! example: en.yml");
    		ServerStop();
    		return null;
    	}
		return matches;
    }
    
    public File GetRightMatch(ArrayList<File> matches, String name){
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
    
    public void ServerStop(){
    	Bukkit.getServer().shutdown();
    }
    
    //makes the file (langData) usable for the plugin
    public FileConfiguration langFileApp(){
    	String lang = getLanguage();
    	ArrayList<File> matches = CheckLang(plugin.getDataFolder(), lang);
    	File langFile = GetRightMatch(matches, lang);
		FileConfiguration langcfg = YamlConfiguration.loadConfiguration(langFile);
		return langcfg;
    }
    
    public String stringGetter(FileConfiguration lang, String path){
    	String value = lang.getString(path);
    	value = value.replaceAll("&", "§");
    	value = value.replaceAll("%u", "ü");
    	value = value.replaceAll("%o", "ö");
    	value = value.replaceAll("%a", "ä");
    	value = value.replaceAll("%sz", "&");
    	if(value != null){
    		return value;
    	}else {
    		String err = lang.getString("messages.general.logger.noTranslation");
    		err = "§4" + err;
    		return err;
    	}
    }
}
