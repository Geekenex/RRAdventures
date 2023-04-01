package me.Geekenex.rradventures.events.teleportation;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.Geekenex.rradventures.Main;

public class Well implements Listener {

	@SuppressWarnings("unused")
	private Main plugin;
	public Well(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	World world = Bukkit.getServer().getWorld("smt_world");
	
	//Entering Water Well
	@EventHandler
	public void enterWell(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		Location wellunder = new Location(world, 91, 16, 333);
		if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.BLUE_CONCRETE)) {
		player.playSound(player.getLocation(), Sound.ENTITY_DROWNED_AMBIENT_WATER, 1, 1);
		player.teleport(wellunder);	
		}
	}
	
}
