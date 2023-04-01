package me.Geekenex.rradventures.mobdrops;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import me.Geekenex.rradventures.Main;

public class Mobs implements Listener {

	@SuppressWarnings("unused")
	private Main plugin;
	public Mobs(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	//Spiders
	//Mob Drops	
	@EventHandler
	public void spiderLoot(EntityDeathEvent e) {
		LivingEntity mob = e.getEntity();
		if(mob.getKiller() instanceof Player) {
		if(mob.getType().equals(EntityType.SPIDER)) {
			e.getDrops().add(new ItemStack(Material.BRICK, 1));
	}
		}
	}
	
	
	
}
