package com.github.fatfrido.fatpack;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class SetupPermissions {
	
	static JavaPlugin plugin = FatPack.getPlugin(FatPack.class);
	
	static PermissionManager pm = PermissionsEx.getPermissionManager();
	
	static LangManager lm = new LangManager();
	static FileConfiguration lang = lm.langFileApp();
	static String path1 = "permissions.groups.";
	
	public static void addGroupPEX(String groupname) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex group " + groupname + " create");
	}
	
	public static void addGroupPerms(List<String> perms, String groupname, String worldname){
		pm.getBackend().getGroupData(groupname).setPermissions(perms, worldname);
	}
	
	public static void addGroupPerms(List<String> perms, String groupname){
		for(int i = 0; i < Bukkit.getWorlds().size(); i++){
			String name = Bukkit.getWorlds().get(i).getName();
			pm.getBackend().getGroupData(groupname).setPermissions(perms, name);
		}
	}
	
	public static void removeDefaultGroup(){
		pm.getBackend().getGroupData("default").remove();
	}
	
	public static void addRank(String groupname, int rank){
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex group " + groupname + " rank " + rank);
	}
	
	public static void setupDefaultPermissions(){
    	LangManager lm = new LangManager();
		FileConfiguration lang = lm.langFileApp();
		String path1 = "permissions.groups.";
		String path2 = "config.options.createDefaultPerms";
		
		if(plugin.getConfig().getBoolean(path2) == true){
			removeDefaultGroup();
			
			addGroupPEX(lang.getString(path1 + "admins"));
			addGroupPEX(lang.getString(path1 + "general"));
			addGroupPEX(lang.getString(path1 + "newbies"));
			plugin.getLogger().info("Gruppen geadded");
			//admins
			List<String> adminPerms = Arrays.asList("*");
			addGroupPerms(adminPerms, lang.getString(path1 + "admins"));
			
			addRank(lang.getString(path1 + "admins"), 100);
			
			//general
			List<String> generalPerms = Arrays.asList("");
			addGroupPerms(generalPerms, lang.getString(path1 + "general"));
			
			addRank(lang.getString(path1 + "general"), 300);
			
			//newbies
			List<String> newbiesPerms = Arrays.asList("");
			addGroupPerms(newbiesPerms, lang.getString(path1 + "newbies"));
			
			addRank(lang.getString(path1 + "newbies"), 500);
			
			plugin.getConfig().set(path2, false);
			plugin.saveConfig();
		}
    }
	
	public static void reloadPEX(){
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex reload");
	}
}
