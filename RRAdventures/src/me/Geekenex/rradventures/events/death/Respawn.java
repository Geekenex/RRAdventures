package me.Geekenex.rradventures.events.death;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.Geekenex.rradventures.Main;

public class Respawn implements Listener {
	
	@SuppressWarnings("unused")
	private Main plugin;
	public Respawn(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public static HashMap<UUID, Boolean> ir = new HashMap<UUID, Boolean>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDeath(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		UUID puid = p.getUniqueId();
		int timer = 120;
		p.setGameMode(GameMode.SPECTATOR);
		World w = Bukkit.getServer().getWorld("smt_world");
		Location loc = new Location(w, -286, 5, 260);
		ir.put(puid, true);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
        		p.teleport(loc);
          }
		}, 1); 
		p.sendTitle(ChatColor.DARK_RED + "You Died!", "");
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			            public void run() {
			            	p.setGameMode(GameMode.SURVIVAL);
			            	p.teleport(Bukkit.getServer().getWorld("smt_world").getSpawnLocation());
			        		ir.remove(puid);
			          }
					}, timer); 
		    	}
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (ir.containsKey(p.getUniqueId())) {
			e.setCancelled(true);
		}
		else {
			return;
		}
	}
		    
		
	

}
