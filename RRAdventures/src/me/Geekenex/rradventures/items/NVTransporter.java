package me.Geekenex.rradventures.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Geekenex.rradventures.Main;

public class NVTransporter implements Listener {

	@SuppressWarnings("unused")
	private Main plugin;
	public NVTransporter(Main plugin) {
		this.plugin = plugin;
		
	}
	
	public void giveNVTrans(Player player) {
		ItemStack NVTrans = new ItemStack(Material.PRISMARINE_SHARD, 1);
	    ItemMeta itemMeta = NVTrans.getItemMeta();
	 
	    String ikeyLore = ChatColor.AQUA + "Brings you back home.";
	    List<String> loreList = new ArrayList<String>();
	    loreList.add(ikeyLore);
	    itemMeta.setLore(loreList);
	 
	    itemMeta.setDisplayName(ChatColor.DARK_AQUA + "Noobville Transporter");
	    NVTrans.setItemMeta(itemMeta);
		
	    player.getInventory().addItem(NVTrans);
	}
	
	public boolean checkNVTrans(Player player) {
		ItemStack NVTrans = new ItemStack(Material.PRISMARINE_SHARD, 1);
	    ItemMeta itemMeta = NVTrans.getItemMeta();
	 
	    String ikeyLore = ChatColor.AQUA + "Brings you back home.";
	    List<String> loreList = new ArrayList<String>();
	    loreList.add(ikeyLore);
	    itemMeta.setLore(loreList);
	 
	    itemMeta.setDisplayName(ChatColor.DARK_AQUA + "Noobville Transporter");
	    NVTrans.setItemMeta(itemMeta);
	    
	    if(player.getInventory().contains(NVTrans)) {
	    	return true;
	    }
	    return false;

	}
	
}
