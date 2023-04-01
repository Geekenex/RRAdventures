package me.Geekenex.rradventures.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Geekenex.rradventures.Main;

public class NoobVilleKey {

	@SuppressWarnings("unused")
	private Main plugin;
	public NoobVilleKey(Main plugin) {
		this.plugin = plugin;
		
	}
	
	public void giveNVkey(Player player) {
		ItemStack ironKey = new ItemStack(Material.LEVER, 1);
	    ItemMeta itemMeta = ironKey.getItemMeta();
	 
	    String ikeyLore = ChatColor.GRAY + "This opens the gate to Noobville!";
	    List<String> loreList = new ArrayList<String>();
	    loreList.add(ikeyLore);
	    itemMeta.setLore(loreList);
	 
	    itemMeta.setDisplayName(ChatColor.GRAY + "Noobville Key");
	    ironKey.setItemMeta(itemMeta);
		
	    player.getInventory().addItem(ironKey);
	}
	
	public boolean checkNVkey(Player player) {
		ItemStack ironKey = new ItemStack(Material.LEVER, 1);
	    ItemMeta itemMeta = ironKey.getItemMeta();
	 
	    String ikeyLore = ChatColor.GRAY + "This opens the gate to Noobville!";
	    List<String> loreList = new ArrayList<String>();
	    loreList.add(ikeyLore);
	    itemMeta.setLore(loreList);
	 
	    itemMeta.setDisplayName(ChatColor.GRAY + "Noobville Key");
	    ironKey.setItemMeta(itemMeta);
	    
	    if(player.getInventory().contains(ironKey)) {
	    	return true;
	    }
	    return false;

	}
	
}
