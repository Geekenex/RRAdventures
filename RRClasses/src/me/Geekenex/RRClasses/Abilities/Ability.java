package me.Geekenex.RRClasses.Abilities;

import java.io.Serializable;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.Geekenex.RRClasses.CustomItem;

public class Ability implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean classAbility;
	private int tier;
	private int cooldown;
	private int xpCost;
	private int shopCost;
	private CustomItem customItem;
	private CustomItem shopItem;
	private String description;
	
	public Ability(boolean classAbility, int tier, int cooldown, int shopCost) {
		this.classAbility = classAbility;
		this.tier = tier;
		this.cooldown = cooldown;
		this.shopCost = shopCost;
		
		//Sets the xpCost to purchase this ability based on what tier it is set to
		if(classAbility) {
		if(tier == 0) {
			xpCost = 0; //XP level 0
		}
		if(tier == 1) {
			xpCost = 160; //XP level 10
		}
		if(tier == 2) {
			xpCost = 550; //XP level 20
		}
		if(tier == 3) {
			xpCost = 1395; //XP level 30
		}
		if(tier == 4) {
			xpCost = 2920; //XP level 40
		}
		}
	}
	
	public void setItem(Material material, String name, ChatColor nameColor, String lore , ChatColor loreColor) {
		customItem = new CustomItem(material, name, nameColor, lore , loreColor);
		this.shopItem = new CustomItem(material, name, nameColor, lore , loreColor);
	}

	public boolean isClassAbility() {
		return classAbility;
	}

	public int getTier() {
		return tier;
	}

	public int getCooldown() {
		return cooldown;
	}

	public int getXpCost() {
		return xpCost;
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
