package me.Geekenex.rradventures.events.worldinteraction;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Geekenex.rradventures.Main;

public class BlockBreaks implements Listener {
	
	private Main plugin;
	public BlockBreaks(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	//Break Blocks
	@EventHandler
	public void blockProtection(BlockBreakEvent e) {
		Player player = e.getPlayer();
		if(player.getGameMode() == GameMode.CREATIVE) return;
		if(e.getBlock().getType() == Material.GOLD_BLOCK) return;
		if(e.getBlock().getType() == Material.BEE_NEST) return;
		e.setCancelled(true);
		
	}
	
	//OreRespawns
	@EventHandler
	public void mineOres(BlockBreakEvent e) {
		Block ore = e.getBlock();
		Location blockposition = e.getBlock().getLocation();
		Player player = e.getPlayer();
		int regentime = 1000;
		if(player.getGameMode() == GameMode.CREATIVE) return; 
		if(ore.getType().equals(Material.COAL_ORE) || e.getBlock().getType().equals(Material.IRON_ORE)) {
			
			//Coal Drops
			if(ore.getType().equals(Material.COAL_ORE)) {
			player.getInventory().addItem(new ItemStack(Material.COAL));
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				
	            public void run() {
	        			blockposition.getBlock().setType(Material.COAL_ORE);		
	          }
			}, regentime); 
			}
			
			//Iron Drops
	        if(ore.getType().equals(Material.IRON_ORE)) {
	    		ItemStack ironChunk = new ItemStack(Material.LIGHT_GRAY_DYE, 1);
	    	    ItemMeta itemMeta = ironChunk.getItemMeta();
	    	    itemMeta.setDisplayName(ChatColor.GRAY + "Iron Chunk");
	    	    ironChunk.setItemMeta(itemMeta);
	        player.getInventory().addItem(ironChunk);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				
	            public void run() {
	        			blockposition.getBlock().setType(Material.IRON_ORE);	
	          }
			}, regentime); 
			}
			blockposition.getBlock().setType(Material.STONE);		
		}
		
		}
	
	//StoneMining
	@EventHandler
	public void mineStone(BlockBreakEvent e) {
		Block ore = e.getBlock();
		Player player = e.getPlayer();
		if(player.getGameMode() == GameMode.CREATIVE) return; 
		if(ore.getType().equals(Material.STONE)) {
			player.getInventory().addItem(new ItemStack(Material.COBBLESTONE));	
	  }
		
	}
		
	
	
}
