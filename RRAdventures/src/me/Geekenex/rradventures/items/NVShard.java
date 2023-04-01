package me.Geekenex.rradventures.items;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.Geekenex.rradventures.Main;

public class NVShard implements Listener {

	@SuppressWarnings("unused")
	private Main plugin;
	public NVShard(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	World world = Bukkit.getServer().getWorld("smt_world");
	
	//Noobville Shard
	@EventHandler
	  public void NVtp(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		ItemStack item = e.getItem();
		Location village = new Location(world, -160, 26, 156, 90, 0);
		if(e.getItem() != null) {
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(item.getType() == Material.PRISMARINE_SHARD) {
				player.teleport(village);
				int amount = e.getItem().getAmount();
				if(amount > 1) {
					player.getInventory().getItemInMainHand().setAmount(amount-1);
				} else {
					player.getInventory().getItemInMainHand().setAmount(0);
				}
			}
		}
		}
	}
	
}
