package com.github.fatfrido.fatpack.commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.github.fatfrido.fatpack.CertificateManager;
import com.github.fatfrido.fatpack.LangManager;

public class CCertificate implements CommandExecutor{
	
	static LangManager lm = new LangManager();
	static FileConfiguration lang = lm.langFileApp();
	static String path1 = "messages.commands.buy.";
	static String path2 = "messages.commands.certificates.";
	static String path3 = "certificates.printed.";

	@SuppressWarnings("null")
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		Player player = null;
		if (cs instanceof Player) {
			player = (Player) cs;
		}
		CertificateManager cm = new CertificateManager();
		if(cmd.getName().equalsIgnoreCase("certificate")){
			if(args.length >= 1){
				if(args[0].equalsIgnoreCase("list")){
					cs.sendMessage(cm.getAllCerts().toString());
					return true;
				}else if(args[0].equalsIgnoreCase("remove")){
					boolean removed = cm.removeCertificate(args[1], args[2]);
					if(removed == true){
						cs.sendMessage("§6" + args[1] + " " + lm.stringGetter(lang, path2 + "removed"));
						return true;
					}else {
						cs.sendMessage(lm.stringGetter(lang, path2 + "playernotfound"));
						return false;
					}
				}else if(args[0].equalsIgnoreCase("print")){
					if(cs == player){
						cm.printCertificate(args[1], player);
						return true;
					}else {
						cs.sendMessage(lm.stringGetter(lang, path1 + "notPlayer"));
					}
				}else if(args[0].equalsIgnoreCase("edit")){
					if(args.length > 3){
						if(args[1].equalsIgnoreCase("price")){
							cm.editPrice(args[2], Integer.valueOf(args[3]));
							cs.sendMessage( "§7[§6" + args[2].toUpperCase() + "§7]" + lm.stringGetter(lang, path2 + "edited") + lm.stringGetter(lang, path3 + "price"));
							return true;
						}else if(args[1].equalsIgnoreCase("requirements")){
							List<String> req = new ArrayList<String>();
							if(!(args[3].equalsIgnoreCase("null"))){
								for(int i = 3; i < args.length; i++){
									req.add(args[i]);
								}
							}	
							cm.editReqirements(args[2], req);
							cs.sendMessage( "§7[§6" + args[2].toUpperCase() + "§7]" + lm.stringGetter(lang, path2 + "edited") + lm.stringGetter(lang, path3 + "requirements"));
							return true;
						}else if(args[1].equalsIgnoreCase("description")){
							ArrayList<String> desc = new ArrayList<String>();
							for(int i = 3; i < args.length; i++){
								desc.add(args[i]);
							}
							cm.editDesc(args[2], desc);
							cs.sendMessage( "§7[§6" + args[2].toUpperCase() + "§7]" + lm.stringGetter(lang, path2 + "edited") + lm.stringGetter(lang, path3 + "description"));
							return true;
						}else if(args[1].equalsIgnoreCase("permissions")){
							List<String> perms = new ArrayList<String>();
							for(int i = 3; i < args.length; i++){
								perms.add(args[i]);
							}
							cm.editPerms(args[2], perms);
							cs.sendMessage( "§7[§6" + args[2].toUpperCase() + "§7]" + lm.stringGetter(lang, path2 + "edited") + lm.stringGetter(lang, path3 + "permissions"));
							return true;
						}else {
							cs.sendMessage(lm.stringGetter(lang, path2 + "usageEdit"));
							return true;
						}
					}else {
						cs.sendMessage(lm.stringGetter(lang, path1 + "tooFewArgs"));
						cs.sendMessage(lm.stringGetter(lang, path2 + "usageEdit"));
						return false;
					}
					
				}else if(args[0].equalsIgnoreCase("create")){
					if(args.length < 3){
						cs.sendMessage(lm.stringGetter(lang, path2 + "createname"));
						return true;
					}else if(args.length == 3){
						if(args[1].equalsIgnoreCase("name")){
							if(cm.newCertificate(args[2]) == 0){
								cs.sendMessage(lm.stringGetter(lang, path2 + "createprice"));
								return true;
							}else if(cm.newCertificate(args[2]) == 1){
								cs.sendMessage(lm.stringGetter(lang, path2 + "createend"));
								return true;
							}else {
								cs.sendMessage(lm.stringGetter(lang, path2 + "existsalready"));
								return false;
							}
						}else {
							return false;
						}
					}else if(args.length >= 4){
						if(args[1].equalsIgnoreCase("price")){
							try{
								cm.editPrice(args[2], Double.valueOf(args[3]));
								cs.sendMessage(lm.stringGetter(lang, path2 + "createrequirements"));
							}catch(NumberFormatException ex){
								cs.sendMessage(lm.stringGetter(lang, path2 + "enternumber"));
								return true;
							}
						}else if(args[1].equalsIgnoreCase("requirements")){
							List<String> req = new ArrayList<String>();
							if(!(args[3].equalsIgnoreCase("null"))){
								for(int i = 3; i < args.length; i++){
									req.add(args[i]);
								}
							}	
							cm.editReqirements(args[2], req);
							cs.sendMessage(lm.stringGetter(lang, path2 + "createperms"));
							return true;
						}else if(args[1].equalsIgnoreCase("perms")){
							List<String> perms = new ArrayList<String>();
							for(int i = 3; i < args.length; i++){
								perms.add(args[i]);
							}
							cm.editPerms(args[2], perms);
							cs.sendMessage(lm.stringGetter(lang, path2 + "createdesc"));
							return true;	
						}else if(args[1].equalsIgnoreCase("desc")){
							ArrayList<String> desc = new ArrayList<String>();
							for(int i = 3; i < args.length; i++){
								desc.add(args[i]);
							}
							cm.editDesc(args[2], desc);
							cs.sendMessage(lm.stringGetter(lang, path2 + "createexe"));
						}else {
							cs.sendMessage(lm.stringGetter(lang, path1 + "tooFewArgs"));
							return false;
						}
						return true;
					}else {
						return true;
					}
				}
			}else {
				if(player != null){
					List<String> certs = cm.getCertificates(player);
					if(certs != null){
						cs.sendMessage(lm.stringGetter(lang, path2 + "certificates") + certs);
						return true;
					}else {
						cs.sendMessage(lm.stringGetter(lang, path2 + "nocertificates"));
						return true;
					}
				}else {
					cs.sendMessage(lm.stringGetter(lang, path1 + "notPlayer"));
					return true;
				}
			}
		}
		return false;
	}
}
