package me.Geekenex.rradventures.mobdrops;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Geekenex.rradventures.Main;

public class CrazyMikeDrops implements Listener {
	
	@SuppressWarnings("unused")
	private Main plugin;
	public CrazyMikeDrops(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void mikeDeath(EntityDeathEvent e) {
		LivingEntity mob = e.getEntity();
		
		//HisDrops
		ItemStack crazypowder = new ItemStack(Material.SUGAR, 10);
	    ItemMeta itemMeta = crazypowder.getItemMeta();
	    String ikeyLore = ChatColor.DARK_GRAY + "1000% Energy";
	    List<String> loreList = new ArrayList<String>();
	    loreList.add(ikeyLore);
	    itemMeta.setLore(loreList);
	    itemMeta.setDisplayName(ChatColor.YELLOW + "Crazy Powder");
	    crazypowder.setItemMeta(itemMeta);
	    
		if(mob instanceof Zombie) {
			if(mob.hasMetadata("CrazyMike")) {
			mob.getLocation().getWorld().dropItem(mob.getLocation(), new ItemStack(crazypowder));
			}
	}
	}
	
	
	
}
