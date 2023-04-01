package me.Geekenex.rradventures.events.messages;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Geekenex.rradventures.Main;
import me.Geekenex.rradventures.utils.Utils;

public class Join implements Listener {
	
	private Main plugin;
	public Join(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
		
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		e.setJoinMessage(null);
		Bukkit.broadcastMessage(Utils.chat(plugin.getConfig().getString("plugin_prefix")) + Utils.chat(plugin.getConfig().getString("join_message").replace("<player>", player.getName())));
		
	}

}
