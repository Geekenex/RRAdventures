package me.Geekenex.rradventures.events.worldinteraction;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.Geekenex.rradventures.Main;

public class BlockPlace implements Listener {

	@SuppressWarnings("unused")
	private Main plugin;
	public BlockPlace(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	//BlockPlace
	@EventHandler
	public void placeProtect(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		if(player.getGameMode() == GameMode.CREATIVE) return;
		e.setCancelled(true);
	}	
	
}
