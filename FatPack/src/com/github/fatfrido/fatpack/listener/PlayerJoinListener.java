package com.github.fatfrido.fatpack.listener;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.github.fatfrido.fatpack.FatPack;

public class PlayerJoinListener implements Listener{
	
	private Economy econ;
	
	public PlayerJoinListener(FatPack plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	
	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent event){
		this.econ = FatPack.econ;
		Player player = event.getPlayer();
		PermissionUser user = PermissionsEx.getUser(player);
		if(player.isOp()){
			if(player.hasPlayedBefore()){
				
				return;
			}else {
				user.addGroup("Adel");
				return;
			}
		}else if(player.hasPlayedBefore()){
			
			return;
		}else {
			user.addGroup("Buerger");
			return;
		}
	}
}
