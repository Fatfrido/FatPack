package com.github.fatfrido.fatpack;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.github.fatfrido.fatpack.commands.CBuy;
import com.github.fatfrido.fatpack.commands.CCertificate;
import com.github.fatfrido.fatpack.commands.CDay;
import com.github.fatfrido.fatpack.languages.languageDE;
import com.github.fatfrido.fatpack.languages.languageEN;
import com.github.fatfrido.fatpack.listener.PlayerJoinListener;

public class FatPack extends JavaPlugin {
	
	public static Economy economy = null;
    public static Permission permission = null;
    public static Chat chat = null;
    public static FatPack plugin = null;
    static PermissionsEx pex;
    
	@Override
	public void onDisable(){
		this.getLogger().info("Plugin disabled!");
	}
	
	@Override
	public void onEnable(){
		
		//general config
		loadConfig();
		
		//lang
		setupLang();
		
		LangManager lm = new LangManager();
		FileConfiguration lang = lm.langFileApp();
		
		//vault
		if(!this.setupEconomy()){
			this.getLogger().info("No Connection with Vault!");
		}
		
		String pluginVerison = lm.stringGetter(lang, "messages.general.logger.pluginVersion");
		this.getLogger().info(lm.stringGetter(lang, "messages.general.logger.pluginEnabled"));
		this.getLogger().info(pluginVerison + ": " + getDescription().getVersion());
		
		//bank
		defaultEcon();
		
		//permissionsEx
		SetupPermissions.setupDefaultPermissions();
		
		//listener
		new PlayerJoinListener(this);
		
		//Commands
		this.getCommand("day").setExecutor(new CDay());
		this.getCommand("buy").setExecutor(new CBuy());
		this.getCommand("certificate").setExecutor(new CCertificate());
		
		//Recipes
		
		//certificates
		CertificateManager cm = new CertificateManager();
		cm.certificateManager();
		
		
	}
	
	private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }
        return (economy != null);
    }
    
  	private void loadConfig() {
  		String header = this.getDescription().getName() + " " +this.getDescription().getVersion() + "\n" 
  				+ "Please take a look on http://dev.bukkit.org/bukkit-plugins/fatpack/ to learn how to\n"
  				+ "use the config.yml . Feel free to send me a PM or leave a ticket so I can improve The FatPack!\n"
  				+ "Thanks for supporting me and this plugin!";
  		this.getConfig().options().header(header);
  		
  		String path1 = "config.messages.";
  		this.getConfig().addDefault(path1 + "language", "en");
  		this.getConfig().addDefault(path1 + "modt", "This server runs &6The FatPack!");
  		
  		String path2 = "config.options.";
  		this.getConfig().addDefault(path2 + "createDefaultPerms", true);
  		String path21 = "config.options.MySQL.";
  		this.getConfig().addDefault(path21 + "enableMySQL", true);
  		this.getConfig().addDefault(path21 + "host", "localhost");
  		this.getConfig().addDefault(path21 + "port", "3306");
  		this.getConfig().addDefault(path21 + "database", "fatpack");
  		this.getConfig().addDefault(path21 + "username", "user");
  		this.getConfig().addDefault(path21 + "password", "password");
  		String path22 = "config.options.economy.";
  		this.getConfig().addDefault(path22 + "startBalance", "150");
  		this.getConfig().addDefault(path22 + "createStatebank", true);
  		this.getConfig().addDefault(path22 + "statebankOwner", "Servergod");
  		
  		String path3 = "config.join.once.tools.";
  		this.getConfig().addDefault(path3 + "sword", 272);
  		this.getConfig().addDefault(path3 + "shovel", 273);
  		this.getConfig().addDefault(path3 + "pickaxe", 274);
  		this.getConfig().addDefault(path3 + "axe", 275);
  		String path31 = "config.join.once.armor.";
  		this.getConfig().addDefault(path31 + "helmet", 298);
  		this.getConfig().addDefault(path31 + "chest", 299);
  		this.getConfig().addDefault(path31 + "pants", 300);
  		this.getConfig().addDefault(path31 + "boots", 301);
  		String path32 = "config.join.everyday.";
  		this.getConfig().addDefault(path32 + "EXP-Bottle", true);
  		this.getConfig().addDefault(path32 + "food.enabled", true);
  		this.getConfig().addDefault(path32 + "food", 297);
  		this.getConfig().addDefault(path32 + "food.amount", 3);
  		
  		this.getConfig().options().copyDefaults(true);
  		this.saveConfig();
  	}
    
    private void defaultEcon(){
    }
    
    private void setupLang(){
    	new languageDE();
    	new languageEN();
    	languageDE.de();
    	languageEN.en();
    }
}
