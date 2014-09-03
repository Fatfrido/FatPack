package com.github.fatfrido.fatpack.commands;

import java.util.List;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.github.fatfrido.fatpack.CertificateManager;
import com.github.fatfrido.fatpack.LangManager;

public class CBuy implements CommandExecutor{
	
	static LangManager lm = new LangManager();
	static FileConfiguration lang = lm.langFileApp();
	private Economy econ;
	static String path1 = "permissions.groups.";
	static String path2 = "messages.commands.buy.";
	static String path3 = "certificates.messages.";

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		Player player = null;
		if (cs instanceof Player) {
			player = (Player) cs;
		}
		if(cmd.getName().equalsIgnoreCase("buy")){
			if(player == null){
				cs.sendMessage(lm.stringGetter(lang, path2 + "notPlayer"));
			}else {
				if(args.length >= 2){
					if(args[0].equalsIgnoreCase("certificate")){
						CertificateManager cm = new CertificateManager();
						if(cm.getCertificate(args[1]) == null){
							player.sendMessage(lm.stringGetter(lang, path3 + "invalidCertificate"));
							return false;
						}else {
							List<String> required = cm.getRequirement(args[1]);
							if(cm.hasCertificate(args[1], player) == true){
								player.sendMessage(lm.stringGetter(lang, path3 + "hasAlready"));
								return true;
							}else {
								double price = cm.getCertPrice(args[1]);
								if(this.econ.has(player, price)){
									cm.addCertificate(args[1], player, required);
									cm.addCertPerms(args[1], player);
									player.sendMessage(lm.stringGetter(lang, path3 + "gotCertificate") + args[1]);
									econ.withdrawPlayer(player, price);
									return true;
								}else {
									player.sendMessage(lm.stringGetter(lang, path2 + "notEnoughMoney"));
									return true;
								}
							}
						}
					}else if(args[0].equalsIgnoreCase("command")){
						
						return true;
					}else {
						return false;
					}
				}else {
					player.sendMessage(lm.stringGetter(lang, path2 + "tooFewArgs"));
					return false;
				}
			}
		}
		return false;
	}
	
}
