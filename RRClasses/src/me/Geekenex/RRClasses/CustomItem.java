package me.Geekenex.RRClasses;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItem {
	
	ItemStack item;
	
	public CustomItem(Material material, String name, ChatColor nameColor, String lore , ChatColor loreColor) {
		item = new ItemStack(material, 1);
	    ItemMeta abilityMeta = item.getItemMeta();
	    String abilityLore = loreColor + lore;
	    List<String> loreList = new ArrayList<String>();
	    loreList.add(abilityLore);
	    abilityMeta.setLore(loreList);
	    abilityMeta.setDisplayName(nameColor + name);
	    item.setItemMeta(abilityMeta);
	}
	
	public ItemStack getItem() {
		return item;
	}
	
}
