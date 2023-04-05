package me.Geekenex.RRClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItem implements Serializable {
	

	private static final long serialVersionUID = 1L;
	ItemStack item;
	ItemMeta abilityMeta;
	List<String> loreList = new ArrayList<String>();
	
	public CustomItem(Material material, String name, ChatColor nameColor, String lore , ChatColor loreColor) {
		item = new ItemStack(material, 1);
	    abilityMeta = item.getItemMeta();
	    String abilityLore = loreColor + lore;
	    loreList.add(abilityLore);
	    abilityMeta.setLore(loreList);
	    abilityMeta.setDisplayName(nameColor + name);
	    item.setItemMeta(abilityMeta);
	}
	
	public ItemStack getItem() {
		return item;
	}
	
	public void addLore(String lore, ChatColor loreColor) {
		String loreToAdd = loreColor + lore;
	    loreList.add(loreToAdd);
	    abilityMeta.setLore(loreList);
	    item.setItemMeta(abilityMeta);
	}
	
	public void removeLore(String lore, ChatColor loreColor) {
		String loreToAdd = loreColor + lore;
	    loreList.remove(loreToAdd);
	    abilityMeta.setLore(loreList);
	    item.setItemMeta(abilityMeta);
	}
	
	public void clearLore() {
		loreList.clear();
	}
}
