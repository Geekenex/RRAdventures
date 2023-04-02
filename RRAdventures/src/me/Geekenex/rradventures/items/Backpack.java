package me.Geekenex.rradventures.items;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.Geekenex.rradventures.Main;

public class Backpack implements Listener {
	
	@SuppressWarnings("unused")
	private Main plugin;
	public Backpack(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void bpOpen(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		p.openInventory(p.getEnderChest());
		p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
	}

}
