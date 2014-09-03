package com.github.fatfrido.fatpack;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Recipes {
	
	public void addRecipes(){
		addShapedRecipes();
		addShapelessRecipes();
		addSpecialRecipes();
	ItemStack item0 = new ItemStack(Material.DIAMOND_BLOCK);
	ItemMeta meta0 = item0.getItemMeta();
	meta0.setDisplayName("§b§lDER §bDimantblock");
	item0.setItemMeta(meta0);
	ShapelessRecipe recipe0 = new ShapelessRecipe(item0);
	recipe0.addIngredient(9, Material.DIAMOND_BLOCK);
	Bukkit.addRecipe(recipe0);
	
	
	}
	
	public void addFurnanceRecipes(){
		
	}
	
	public void addShapedRecipes(){
		
	}
	
	public void addShapelessRecipes(){
		
	}
	
	public void addSpecialRecipes(){
		
		//legendary Sword
		ItemStack is = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta m = is.getItemMeta();
		m.setDisplayName("§6Feuerklinge");
		m.addEnchant(Enchantment.FIRE_ASPECT, 6, true); //boolean: respect level limit
		m.addEnchant(Enchantment.DAMAGE_ALL, 6, true);
		m.addEnchant(Enchantment.DURABILITY, 6, true);
		m.addEnchant(Enchantment.LOOT_BONUS_MOBS, 6, true);
		List<String> lore = Arrays.asList("This is the legendary sword. It is full of power, it is only waiting for a brave warrior to release all its force!");
		is.setItemMeta(m);
		Material mat = Material.ENDER_PEARL;
		ItemStack mis = new ItemStack(Material.ENDER_PEARL);
		ItemMeta mmat = mis.getItemMeta();
		List<String> lore1 = Arrays.asList("This can be used to craft a legendary weapon!");
		mmat.setLore(lore1);
		m.setDisplayName("&5Mysterious Component");
		mis.setItemMeta(mmat);
		ShapedRecipe r = new ShapedRecipe(is);
		r.shape("XSX", "XNX", "XPX");
		r.setIngredient('S', Material.DIAMOND_SWORD);
		r.setIngredient('N', Material.NETHER_STAR);
		r.setIngredient('P', mat);
		Bukkit.addRecipe(r);
		//legendary dependencies
		ItemStack is1 = new ItemStack(Material.ENDER_PEARL);
		ItemMeta m1 = is.getItemMeta();
		List<String> lore2 = Arrays.asList("This can be used to craft a legendary weapon!");
		m1.setLore(lore2);
		m.setDisplayName("&5Mysterious Component");
		ShapelessRecipe r1 = new ShapelessRecipe(is1);
		r1.addIngredient(Material.ENDER_PEARL);
		r1.addIngredient(Material.EMERALD);
		Bukkit.addRecipe(r1);
	}
}
