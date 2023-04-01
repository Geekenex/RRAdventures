package me.Geekenex.rradventures.events.teleportation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import me.Geekenex.rradventures.Main;

public class Cave implements Listener {

	@SuppressWarnings("unused")
	private Main plugin;
	public Cave(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	World world = Bukkit.getServer().getWorld("smt_world");
	
	//Entering Cave
	@EventHandler
	public void enterCave(PlayerMoveEvent e) {
		Location cave = new Location(world, -875, 66, 758, 180, 0);
		ItemStack ironpick = new ItemStack(Material.IRON_PICKAXE, 1);
		Player player = e.getPlayer();
		if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.BLACK_CONCRETE)) {
			if(player.getInventory().contains(ironpick)) {
				player.teleport(cave);
				player.playSound(player.getLocation(), Sound.AMBIENT_CAVE, 1, 1);
				player.sendMessage(ChatColor.DARK_GRAY + "You enter the depths of the cave...");
			}
		}
	}
	
	//Exit Cave
	@EventHandler
	public void exitCave(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		Location village = new Location(world, -206, 26, 130);
		if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.GRAY_CONCRETE)) {
		player.teleport(village);	
		}
	}
	
}
