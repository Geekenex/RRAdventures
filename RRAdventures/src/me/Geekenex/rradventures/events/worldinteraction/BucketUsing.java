package me.Geekenex.rradventures.events.worldinteraction;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

import me.Geekenex.rradventures.Main;

public class BucketUsing implements Listener {
	
	@SuppressWarnings("unused")
	private Main plugin;
	public BucketUsing(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	//Fill
	@EventHandler
	public void bucketFill(PlayerBucketFillEvent e) {
		if(e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		e.setCancelled(true);
	}
	
	//Empty
	@EventHandler
	public void bucketFill(PlayerBucketEmptyEvent e) {
		if(e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		e.setCancelled(true);
	}
}
