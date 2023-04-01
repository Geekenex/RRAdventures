package me.Geekenex.rradventures.events.villageexit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import me.Geekenex.rradventures.Main;
import me.Geekenex.rradventures.items.NoobVilleKey;

public class NoobVille implements Listener {

	@SuppressWarnings("unused")
	private Main plugin;
	public NoobVille(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	World world = Bukkit.getServer().getWorld("smt_world");
	private NoobVilleKey nvkey = new NoobVilleKey(plugin);
	
	//Exiting Noobville	
	@EventHandler
	public void ironExit(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		
	Location forest = new Location(world, 61, 4, -17);

		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(e.getClickedBlock().getType().equals(Material.IRON_BLOCK)) {
				if(nvkey.checkNVkey(player)) {
					player.teleport(forest);
					player.sendMessage(ChatColor.GREEN + "You enter the forest...");
					player.playSound(player.getLocation(), Sound.BLOCK_GRASS_PLACE, 1, 1);
				}
				if(nvkey.checkNVkey(player) == false) {
					if (e.getHand() == EquipmentSlot.HAND) {
						player.sendMessage(ChatColor.RED + "You need the iron key!");
						player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
					}
				}
			}
		}
	}
	
}
