package me.Geekenex.rradventures.events.worldinteraction;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.Geekenex.rradventures.Main;

public class Farming implements Listener {

	@SuppressWarnings("unused")
	private Main plugin;
	public Farming(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	//Soil Protection
	@EventHandler
	public void noUproot(PlayerInteractEvent e) {
	    if(e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.FARMLAND)
	        e.setCancelled(true);
	}

	//StopHoeing
	@EventHandler
	public void stopHoeing(PlayerInteractEvent e) {
		Block land = e.getClickedBlock();
		if(e.getItem() == null) return;
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		if(e.getItem().getType() ==  Material.IRON_HOE) {
			if(land.getType() == Material.GRASS_BLOCK || land.getType() == Material.DIRT || land.getType() == Material.DIRT_PATH || land.getType() == Material.COARSE_DIRT) {
				e.setCancelled(true);
				}
			}
		}
	}
	
	//Farming
	@EventHandler
	public void farmCrops(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if(e.getItem() == null) return;
			if(e.getClickedBlock() == null) return;
				if(e.getItem().getType() ==  Material.IRON_HOE) {
						if (e.getClickedBlock().getType() == Material.WHEAT) {
				            final Ageable ageable = (Ageable) e.getClickedBlock().getState().getBlockData();
				            if (ageable.getAge() != 7) return;
				            player.getInventory().addItem(new ItemStack(Material.WHEAT, 1));
				            e.getClickedBlock().setType(Material.WHEAT);
					}
				}
			}
		
	
	
}