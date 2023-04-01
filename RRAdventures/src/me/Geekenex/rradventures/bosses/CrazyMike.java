package me.Geekenex.rradventures.bosses;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Geekenex.rradventures.Main;
import me.Geekenex.rradventures.utils.Utils;

public class CrazyMike implements Listener {

	private Main plugin;
	public CrazyMike(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	World world = Bukkit.getServer().getWorld("smt_world");
	
	//Summoning Crazy Mike
	@EventHandler
	public void cmSpawner(BlockBreakEvent e) {
		Location loc = e.getBlock().getLocation();
		if(e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		if(e.getBlock().getType().equals(Material.GOLD_BLOCK)) {
			int regentime = 4000;
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	            public void run() {
	        			loc.getBlock().setType(Material.GOLD_BLOCK);		
	          }
			}, regentime); 
			e.setCancelled(true);
			Location ironspawn = new Location(world, -867, 13, 651);
		    Zombie cm = (Zombie) world.spawnEntity(ironspawn, EntityType.ZOMBIE);
		   
		    cm.setCustomName(ChatColor.GRAY + "Crazy Mike");
		    cm.setCustomNameVisible(true);
		    cm.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
		    cm.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000, 3));
		    cm.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000, 2));
		    cm.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		    cm.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(160);
		    cm.setHealth(160);
		    cm.setMetadata("CrazyMike",new FixedMetadataValue(plugin, "crazymike"));
				loc.getBlock().setType(Material.COAL_BLOCK);
				Bukkit.broadcastMessage(Utils.chat("&8Crazy Mike&7: Don't touch my treasure! You hooligans will pay!"));	
		}
	}
	
}
