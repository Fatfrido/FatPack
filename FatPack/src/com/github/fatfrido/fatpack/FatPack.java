package com.github.fatfrido.fatpack;

import java.io.File;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.fatfrido.fatpack.commands.CDay;
import com.github.fatfrido.fatpack.languages.languageDE;
import com.github.fatfrido.fatpack.languages.languageEN;
import com.github.fatfrido.fatpack.listener.PlayerJoinListener;

public class FatPack extends JavaPlugin {
	
	public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;
    public static FatPack plugin;
    
    public static FatPack getFatPack(){
		return FatPack.plugin;
	}
    
	@Override
	public void onDisable(){
		this.getLogger().info("deactivated!");
	}
	
	@Override
	public void onEnable(){
		this.getLogger().info("version " + getDescription().getVersion());
		this.getLogger().info("");
		
		//general config
		loadConfig();
		
		//lang
		setupLang();
		
		//permissionsEx
		defaultBank();
		
		//vault setup
		setupEconomy();
		setupChat();
		setupPermissions();
		
		//listener
		new PlayerJoinListener(this);
		
		//Commands
		this.getCommand("day").setExecutor(new CDay());
		
		//Recipes
		
		
		
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
    
  	private void loadConfig() {
  		String path1 = "config.messages";
  		this.getConfig().addDefault(path1 + ".language", "en");
  		this.getConfig().addDefault(path1 + ".modt", "This server runs §6The FatPack!");
  		
  		String path2 = "config.join.once.tools";
  		this.getConfig().addDefault(path2 + ".sword", 272);
  		this.getConfig().addDefault(path2 + ".shovel", 273);
  		this.getConfig().addDefault(path2 + ".pickaxe", 274);
  		this.getConfig().addDefault(path2 + ".axe", 275);
  		String path21 = "config.join.once.armor";
  		this.getConfig().addDefault(path21 + ".helmet", 298);
  		this.getConfig().addDefault(path21 + ".chest", 299);
  		this.getConfig().addDefault(path21 + ".pants", 300);
  		this.getConfig().addDefault(path21 + ".boots", 301);
  		String path22 = "config.join.everyday";
  		this.getConfig().addDefault(path22 + ".EXP-Bottle", true);
  		this.getConfig().addDefault(path22 + ".food.enabled", true);
  		this.getConfig().addDefault(path22 + ".food", 297);
  		this.getConfig().addDefault(path22 + ".food.amount", 3);
  		
  		this.getConfig().options().copyDefaults(true);
  		this.saveConfig();
  	}
    
    private void defaultBank(){
    	//econ.createBank(statebank, owner);
    }
    
    private void setupLang(){
    	new languageDE();
    	new languageEN();
    	languageDE.de();
    	languageEN.en();
    }
    
}
