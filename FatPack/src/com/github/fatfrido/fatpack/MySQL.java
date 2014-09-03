package com.github.fatfrido.fatpack;

import java.sql.Connection;
import java.sql.DriverManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MySQL {
	
	static JavaPlugin plugin = FatPack.getPlugin(FatPack.class);
	
	String path = "config.options.MySQL.";
	private String host = plugin.getConfig().getString(path + "host");
	private int port = plugin.getConfig().getInt(path + "port");
	private String db = plugin.getConfig().getString(path + "database");
	private String username = plugin.getConfig().getString(path + "username");
	private String pw = plugin.getConfig().getString(path + "password");
	
	private Connection conn = null;
	
	public void startMySQL() throws Exception{
		this.openConnection();
	}
	
	public Connection openConnection() throws Exception{
		this.conn = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.db, this.username, this.pw);
		return conn;
	}
	
}
