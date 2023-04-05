package me.Geekenex.RRClasses.Abilities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Geekenex.RRClasses.CustomItem;

public class Ability implements Serializable {

	private static final long serialVersionUID = 1L;
	private int cooldown;
	private int shopCost;
	private CustomItem customItem;
	private CustomItem shopItem;
	private String description;
	private String name;
	private HashMap<UUID, Double> cd = new HashMap<>();
	
	public Ability(int shopCost, int cooldown) {
		this.cooldown = cooldown;
		this.shopCost = shopCost;
	}
	
	public void setItem(Material material, String name, ChatColor nameColor, String lore , ChatColor loreColor) {
		customItem = new CustomItem(material, name, nameColor, lore , loreColor);
		this.shopItem = new CustomItem(material, name, nameColor, lore , loreColor);
		this.name = name;
	}

	public int getCooldownValue() {
		return cooldown;
	}


	public ItemStack getItem() {
		return customItem.getItem();
	}
	
	public ItemStack getShopItem() {
		return this.shopItem.getItem();
	} 
	
	public CustomItem shopItem() {
		return this.shopItem;
	}
	
	public CustomItem item() {
		return customItem;
	}
	public void addLore(String lore, ChatColor loreColor) {
		customItem.addLore(lore, loreColor);
	}
	
	public String getDescription() {
		return description;
	}

	public int getShopCost() {
		return shopCost;
	}
	
	//Cooldown
	
	//setCooldown
	public void setCooldown(Player player, int seconds) {
		double delay = System.currentTimeMillis() + (seconds * 1000);
		cd.put(player.getUniqueId(), delay);
		
	}
	
	//getCooldown
	public int getCooldown(Player player) {
		return Math.toIntExact(Math.round(cd.get(player.getUniqueId()) - System.currentTimeMillis())/1000);
	}
	
	
	//checkCooldown
	public boolean checkCooldown(Player player) {
		if(!cd.containsKey(player.getUniqueId()) || cd.get(player.getUniqueId()) <= System.currentTimeMillis()) {
			return true;
		}
		return false;
	}
	
	public String cooldownMessage(Player p) {
			return ChatColor.RED + name + " on cooldown for " + ChatColor.DARK_RED + getCooldown(p) + ChatColor.RED + " more seconds.";
	}
	
	
}
