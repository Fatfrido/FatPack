package com.github.fatfrido.fatpack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class CertificateManager {
	
	static JavaPlugin plugin = FatPack.getPlugin(FatPack.class);
	private static Map<String, String> certName = new HashMap<String, String>();
	private static Map<String, Double> certPrice = new HashMap<String, Double>();
	private static Map<String, List<String>> certReq = new HashMap<String, List<String>>();
	private static Map<String, String> certDesc = new HashMap<String, String>();
	private static Map<String, List<String>> certPerms = new HashMap<String, List<String>>();
	
	public void certificateManager(){
		List<String> tnt = Arrays.asList("tnt.place");
		List<String> tntReq = Arrays.asList("mining");
		createCertificate("tnt", 25000, tntReq, null, tnt);
		
		List<String> nether = Arrays.asList("nether.enter");
		createCertificate("nether", 2100, null, null, nether);
		
		List<String> mining = Arrays.asList("");
		createCertificate("mining", 500, null, null, mining);
		
		List<String> deepMining = Arrays.asList("");
		List<String> deepMiningReq = Arrays.asList("mining");
		createCertificate("deepmining", 3500, deepMiningReq, null, deepMining);
	}
	
	public static ArrayList<File> CheckCertificate(File dir, String certificate){
    	File[] files = dir.listFiles();
    	ArrayList<File> matches = new ArrayList<File> ();
    	if(files != null){
    		for(int i = 0; i < files.length; i++){
    			if(files[i].getName().equalsIgnoreCase(certificate + ".yml")){
    				matches.add(files[i]);
    			}
    			if(files[i].isDirectory()){
    				matches.addAll(CheckCertificate(files[i], certificate));
    			}
    		}
    	}else{
    		plugin.getLogger().info("§4Certificate does not exist!");
    		return null;
    	}
		return matches;
    }
	
	public List<String> getAllCerts(){
		File[] folder = plugin.getDataFolder().listFiles();
    	ArrayList<File> matches = new ArrayList<File> ();
    	if(folder != null){
    		for(int i = 0; i < folder.length; i++){
    			if(folder[i].getName().equals("certificates")){
    				matches.add(folder[i]);
    			}
    		}
    	}
		File[] files = matches.get(0).listFiles();
		ArrayList<String> certs = new ArrayList<String>();
		for(int j = 0; j < files.length; j++){
			String str = files[j].getName().replace(".yml", "");
			certs.add(str);
		}
		certs.toString();
		return certs;
	}
    
    public File GetRightMatch(ArrayList<File> matches, String name){
    	if(matches.size() > 1){
    		plugin.getLogger().info("§4There are more than one Files with name: " + name + ".yml!");
    		return null;
    	}else if(matches.size() == 0){
    		return null;
    	}else {
			File file = matches.get(0);
    		return file;
		}
    }

    
    public FileConfiguration getCertificate(String certificate){
    	ArrayList<File> matches = CheckCertificate(plugin.getDataFolder(), certificate);
    	File file = GetRightMatch(matches, certificate);
    	if(file == null){
    		return null;
    	}else {
    		FileConfiguration cert = YamlConfiguration.loadConfiguration(file);
    		return cert;
    	}
    }
    
    public File getCertFile(String certificate){
    	ArrayList<File> matches = CheckCertificate(plugin.getDataFolder(), certificate);
    	File file = GetRightMatch(matches, certificate);
    	if(file == null){
    		return null;
    	}else {
    		return file;
    	}
    }
	
	public void createCertificate(String certificate, double price, List<String> req, String desc, List<String> perms){
		LangManager lm = new LangManager();
		FileConfiguration lang = lm.langFileApp();
		String path1 = "certificates.";
		String desc1;
		if(desc == null){
			desc1 = lm.stringGetter(lang, path1 + certificate);
		}else {
			desc1 = desc.toString();
		}
		if(req == null){
			req = Arrays.asList("");
		}
		File file = new File("plugins/FatPack/certificates", certificate + ".yml");
		FileConfiguration cert = YamlConfiguration.loadConfiguration(file);
		
		cert.addDefault("price", price);
		cert.addDefault("requirements", req);
		cert.addDefault("description", desc1);
		cert.addDefault("permissions", perms);
		
		cert.options().copyDefaults(true);
		try {
			cert.save(file);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean addCertificate(String certificate, Player player, List<String> required){
		LangManager lm = new LangManager();
		FileConfiguration lang = lm.langFileApp();
		FileConfiguration pd = PlayerManager.getPlayerData(player);
		String path1 = "certificates";
		String path2 = "certificates.messages.";
		List<String> certs = pd.getStringList(path1);
		if(required == null){
			if(!certs.contains(certificate.toLowerCase())){
				certs.add(certificate.toLowerCase());
				pd.set(path1, certs);
				PlayerManager.savePlayerData(pd, player);
				return true;
			}else {
				player.sendMessage(lm.stringGetter(lang, path2 + "hasAlready"));
				return false;
			}
		}else {
			for(int i = 0; i < required.size(); i++){
				if(!certs.contains(required.get(i).toLowerCase())){
					player.sendMessage(lm.stringGetter(lang, path2 + "missingRequired") + required);
					return true;
				}
			}
			certs.add(certificate.toLowerCase());
			pd.set(path1, certs);
			PlayerManager.savePlayerData(pd, player);
			return true;
		}
	}
	
	public boolean hasCertificate(String certificate, Player player){
		FileConfiguration pd = PlayerManager.getPlayerData(player);
		String path1 = "certificates";
		List<String> certs = pd.getStringList(path1);
		if(certs.contains(certificate.toLowerCase())){
			return true;
		}else {
			return false;
		}
	}
	
	public List<String> getRequirement(String certificate){
		FileConfiguration cert = getCertificate(certificate);
		List<String> required = cert.getStringList("required");
		if(!(required == null)){
			return required;
		}else {
			return null;
		}
	}
	
	public boolean removeCertificate(String certificate, String p){
		Player player = Bukkit.getServer().getPlayer(p);
		String path1 = "certificates";
		if(player != null){
			FileConfiguration pd = PlayerManager.getPlayerData(player);
			PermissionUser user = PermissionsEx.getUser(player);
			List<String> perms = getCertPerms(certificate);
			
			List<String> certs = pd.getStringList(path1);
			if(certs.contains(certificate)){
				certs.remove(certificate);
				pd.set(path1, certs);
				PlayerManager.savePlayerData(pd, player);
				for(int i = 0; i < perms.size(); i++){
					user.removePermission(perms.get(i));
				}
				SetupPermissions.reloadPEX();
				return true;
			}else{
				plugin.getLogger().info("player doesnt have this certificate!");
				return false;
			}
		}else {
			plugin.getLogger().info("player = null");
			return false;
		}
	}
	
	public void addCertPerms(String certificate, Player player){
		PermissionUser user = PermissionsEx.getUser(player);
		List<String> perms = getCertPerms(certificate);
		for(int i = 0; i < perms.size(); i++){
			user.addPermission(perms.get(i));
		}
	}
	
	public List<String> getCertPerms(String certificate){
		FileConfiguration cert = getCertificate(certificate);
		List<String> perms = cert.getStringList("permissions");
		return perms;
	}
	
	public double getCertPrice(String certificate){
		double price = getCertificate(certificate).getDouble("price");
		price = Math.round(price * 100)/100;
		return price;
	}
	
	public List<String> getCertificates(Player player){
		FileConfiguration pd = PlayerManager.getPlayerData(player);
		String path1 = "certificates";
		List<String> certs = pd.getStringList(path1);
		return certs;
	}
	
	public int newCertificate(String name){
		if(!(certName.containsKey(name))){
			certName.put(name, name);
			return 0;
		}else if(certName.containsKey(name) && certPrice.containsKey(name) && certReq.containsKey(name) && certDesc.containsKey(name) && certPerms.containsKey(name)){
			createCertificate(name, certPrice.get(name), certReq.get(name), certDesc.get(name).toString(), certPerms.get(name));
			return 1;
		}else {
			return 2;
		}
		
	}
	
	public void editPrice(String name, double price){
		if(getCertificate(name) == null){
			certPrice.put(name, price);
		}else {
			FileConfiguration cert = getCertificate(name);
			File file = getCertFile(name);
			cert.set("price", price);
			try {
				cert.save(file);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void editReqirements(String name, List<String> req){
		if(getCertificate(name) == null){
			certReq.put(name, req);
		}else {
			FileConfiguration cert = getCertificate(name);
			File file = getCertFile(name);
			cert.set("requirements", req);
			try {
				cert.save(file);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void editPerms(String name, List<String> perms){
		if(getCertificate(name) == null){
			certPerms.put(name, perms);
		}else {
			FileConfiguration cert = getCertificate(name);
			File file = getCertFile(name);
			cert.set("permissions", perms);
			try {
				cert.save(file);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void editDesc(String name, ArrayList<String> desc){
		StringBuilder sb = new StringBuilder();
		for(String str : desc){
			sb.append(str);
			sb.append(" ");
		}
		String description = sb.toString();
		if(getCertificate(name) == null){
			certDesc.put(name, description);
		}else {
			FileConfiguration cert = getCertificate(name);
			File file = getCertFile(name);
			cert.set("description", description);
			try {
				cert.save(file);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void printCertificate(String certificate, Player player){
		LangManager lm = new LangManager();
		FileConfiguration lang = lm.langFileApp();
		String path1 = "certificates.";
		String path2 = "certificates.printed.";
		if(hasCertificate(certificate, player) == true){
			if(player.getInventory().firstEmpty() == -1){
				player.sendMessage(lm.stringGetter(lang, path1 + "fullInv"));
			}else {
				FileConfiguration certData = getCertificate(certificate);
				PlayerInventory inv = player.getInventory();
				ItemStack cert = new ItemStack(Material.WRITTEN_BOOK);
				BookMeta meta = (BookMeta) cert.getItemMeta();
				String price = String.valueOf(certData.getInt("price"));
				meta.setAuthor(player.getName());
				meta.setTitle(certificate.toUpperCase());
				String divider = lm.stringGetter(lang, path2 + "divider");
				String pr = lm.stringGetter(lang, path2 + "price");
				String req = lm.stringGetter(lang, path2 + "requirements");
				String desc = lm.stringGetter(lang, path2 + "description");
				String perms = lm.stringGetter(lang, path2 + "permissions");
				meta.setPages("", "", "");
				meta.setPage(1, pr + "\n"+ divider + price + "\n\n" + req + "\n" + divider + reqToString(certificate));
				meta.setPage(2, desc + "\n" + divider + certData.getString("description"));
				meta.setPage(3, perms + "\n" + divider + permsToString(certificate));
				meta.setDisplayName(lm.stringGetter(lang, path2 + "title") + certificate.toUpperCase());
				meta.setLore(certData.getStringList("permissions"));
				cert.setItemMeta(meta);
				inv.addItem(cert);
			}
		}else {
			player.sendMessage(lm.stringGetter(lang, path1 + "messages.notHas"));
		}
	}
	
	public String reqToString(String certificate){
		FileConfiguration cert = getCertificate(certificate);
		List<String> data = cert.getStringList("requirements");
		StringBuilder sb = new StringBuilder();
		for(String str : data){
			sb.append(str);
			sb.append("\n");
		}
		String req = sb.toString();
		return req;
	}
	
	public String permsToString(String certificate){
		FileConfiguration cert = getCertificate(certificate);
		List<String> data = cert.getStringList("permissions");
		StringBuilder sb = new StringBuilder();
		for(String str : data){
			sb.append(str);
			sb.append("\n");
		}
		String perms = sb.toString();
		return perms;
	}
}
