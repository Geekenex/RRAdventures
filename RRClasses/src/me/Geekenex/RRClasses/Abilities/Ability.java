package me.Geekenex.RRClasses.Abilities;

import java.io.Serializable;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.Geekenex.RRClasses.CustomItem;

public class Ability implements Serializable {

	private static final long serialVersionUID = 1L;
	private int cooldown;
	private int shopCost;
	private CustomItem customItem;
	private CustomItem shopItem;
	private String description;
	
	public Ability(int shopCost, int cooldown) {
		this.cooldown = cooldown;
		this.shopCost = shopCost;
	}
	
	public void setItem(Material material, String name, ChatColor nameColor, String lore , ChatColor loreColor) {
		customItem = new CustomItem(material, name, nameColor, lore , loreColor);
		this.shopItem = new CustomItem(material, name, nameColor, lore , loreColor);
	}

	public int getCooldown() {
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
	
	
}
