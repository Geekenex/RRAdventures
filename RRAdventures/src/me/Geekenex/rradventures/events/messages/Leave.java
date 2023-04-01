package me.Geekenex.rradventures.events.messages;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Geekenex.rradventures.Main;
import me.Geekenex.rradventures.utils.Utils;

public class Leave implements Listener {
	
	private Main plugin;
	public Leave(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		e.setQuitMessage(null);
		Bukkit.broadcastMessage(Utils.chat(plugin.getConfig().getString("plugin_prefix")) + Utils.chat(plugin.getConfig().getString("leave_message").replace("<player>", player.getName())));
	}

}
