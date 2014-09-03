package com.github.fatfrido.fatpack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Downloader {
	
	static JavaPlugin plugin = FatPack.getPlugin(FatPack.class);
	
	public static void downloadFile(String filename, String website, OutputStream os) throws IOException{
		LangManager lm = new LangManager();
		FileConfiguration lang = lm.langFileApp();
		String path1 = "messages.general.logger.";
		
        try {
			URL url = new URL(website);
			if(filename.equals("vault")){
				Date d = new Date();
				DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
				String date = df.format(d);
				plugin.getConfig().set("config.options.downloadVault.lastUpdated", date);
				plugin.saveConfig();
			}
			if(filename.equals("pex")){
				Date d = new Date();
				DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
				String date = df.format(d);
				plugin.getConfig().set("config.options.downloadPEX.lastUpdated", date);
				plugin.saveConfig();
			}
			URLConnection conn = url.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			byte buffer[] = new byte[1024];
			int i;
			while((i = is.read(buffer)) != -1){
				os.write(buffer, 0, i);
				os.flush();
			}
			plugin.getLogger().info("download finished!");
		} catch (MalformedURLException e) {
			plugin.getLogger().info(lm.stringGetter(lang, path1 + "invalidURL"));
		}
    }
	
	public static void downloadFile(String filename, String website) throws IOException{
		LangManager lm = new LangManager();
		FileConfiguration lang = lm.langFileApp();
		String path1 = "messages.general.logger.";
		OutputStream os = null;
		
        try {
			URL url = new URL(website);
			
			if(filename.equals("vault")){
				
				Date d = new Date();
				DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
				String date = df.format(d);
				plugin.getConfig().set("config.options.downloadVault.lastUpdated", date);
				plugin.saveConfig();
			}
			if(filename.equals("pex")){
				
				Date d = new Date();
				DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
				String date = df.format(d);
				plugin.getConfig().set("config.options.downloadPEX.lastUpdated", date);
				plugin.saveConfig();
			}
			
			int lastBackslash = website.lastIndexOf("\\");
			int beginIndex = lastBackslash - website.length();
			String str = url.getFile().substring(beginIndex);
			plugin.getLogger().info(str);
			filename = str;
			os = new FileOutputStream(plugin.getDataFolder().getAbsolutePath().replace("FatPack", "") + File.separator + filename);
			URLConnection conn = url.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			byte buffer[] = new byte[1024];
			int i;
			while((i = is.read(buffer)) != -1){
				os.write(buffer, 0, i);
				os.flush();
			}
			plugin.getLogger().info("download finished!");
		} catch (MalformedURLException e) {
			plugin.getLogger().info(lm.stringGetter(lang, path1 + "invalidURL"));
		}
    }
	  
	  public static void getVault() throws FileNotFoundException{
		  
		  String path = "config.options.downloadVault.";
		  String website = plugin.getConfig().getString("config.options.downloadVault.url");
		  String filename = null;
		  if(plugin.getConfig().getString(path + "customName") != null){
			  filename = plugin.getConfig().getString(path + "customName");
			  OutputStream os = new FileOutputStream(plugin.getDataFolder().getAbsolutePath().replace("FatPack", "") + File.separator + filename + ".jar");
			  try {
				plugin.getLogger().info("downloading vault...");
				downloadFile("vault", website, os);
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }else {
			 try {
				plugin.getLogger().info("downloading vault...");
				downloadFile("vault", website);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		  }  
	  }
	  
	  public static void getPEX() throws FileNotFoundException{  
		  
		  String path = "config.options.downloadPEX.";
		  String website = plugin.getConfig().getString("config.options.downloadPEX.url");
		  String filename = null;
		  if(plugin.getConfig().getString(path + "customName") != null){
			  filename = plugin.getConfig().getString(path + "customName");
			  OutputStream os = new FileOutputStream(plugin.getDataFolder().getAbsolutePath().replace("FatPack", "") + File.separator + filename + ".jar");
			  try {
				plugin.getLogger().info("downloading PEX...");
				downloadFile("vault", website, os);
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }else {
			  try {
				plugin.getLogger().info("downloading PEX...");
				downloadFile("vault", website);
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
	  }
}
