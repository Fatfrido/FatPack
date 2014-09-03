package com.github.fatfrido.fatpack.listener;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.github.fatfrido.fatpack.FatPack;
import com.github.fatfrido.fatpack.LangManager;
import com.github.fatfrido.fatpack.PlayerManager;
import com.github.fatfrido.fatpack.SetupPermissions;

public class PlayerJoinListener implements Listener{
	
	static JavaPlugin plugin = FatPack.getPlugin(FatPack.class);
	static PermissionManager pm = PermissionsEx.getPermissionManager();
	private Economy econ = FatPack.economy;
	
	public PlayerJoinListener(FatPack plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	
	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent event){
		LangManager lm = new LangManager();
		FileConfiguration lang = lm.langFileApp();
		String path1 = "permissions.groups.";
		
		Player player = event.getPlayer();
		PermissionUser user = PermissionsEx.getUser(player);
		if(player.isOp()){
			if(player.hasPlayedBefore()){
				if(PlayerManager.getPlayerData(player) == null){
					PlayerManager.createPlayerData(player);
					if(!(user.inGroup(lm.stringGetter(lang, path1 + "admins")))){
						user.addGroup(lm.stringGetter(lang, path1 + "admins"));
						return;
					}else {
						
					}
					return;
				}else {
					
				}
				return;
			}else {
				PlayerManager.createPlayerData(player);
				user.addGroup(lm.stringGetter(lang, path1 + "admins"));
				SetupPermissions.reloadPEX();
				
				return;
			}
		}else if(player.hasPlayedBefore()){
			
			return;
		}else {
			user.addGroup(lm.stringGetter(lang, path1 + "newbies"));
			return;
		}
	}
}
