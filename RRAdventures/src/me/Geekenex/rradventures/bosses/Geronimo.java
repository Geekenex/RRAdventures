package me.Geekenex.rradventures.bosses;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Geekenex.rradventures.Main;
import me.Geekenex.rradventures.utils.Utils;

public class Geronimo implements Listener {

	private Main plugin;
	public Geronimo(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	World world = Bukkit.getServer().getWorld("smt_world");
	
	
	//His spawn
	@EventHandler
	public void hiveBreak(BlockBreakEvent e) {
		if(e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		if(e.getBlock().getType().equals(Material.BEE_NEST)) {
			Location loc = e.getBlock().getLocation();
			int regentime = 400;
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	            public void run() {
	        			loc.getBlock().setType(Material.BEE_NEST);		
	          }
			}, regentime); 
			e.setCancelled(true);
			loc.getBlock().setType(Material.COBWEB);
		Location geronimoLoc = new Location(world, 71, 4, -203);
	    Spider geronimo = (Spider) world.spawnEntity(geronimoLoc, EntityType.SPIDER);
	   
	    geronimo.setCustomName(ChatColor.RED + "Geronimo");
	    geronimo.setCustomNameVisible(true);
	    geronimo.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 4000, 4));
	    geronimo.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 4000, 3));
	    geronimo.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
	    geronimo.setHealth(100);
	    geronimo.setMetadata("Geronimo",new FixedMetadataValue(plugin, "geronimo"));
	    Bukkit.getServer().broadcastMessage(Utils.chat("&cGeronimo&0 > &7How dare you disturb my sleep!"));
	}
	}
	
	//Effects in battle
	@EventHandler
	public void hurtbyGeronimo(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Spider) {
		if(e.getDamager().hasMetadata("Geronimo")) {
			Player p = (Player) e.getEntity();
			p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 300, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 0));
			int random = ThreadLocalRandom.current().nextInt(10);
			if(random<3) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 120, 1));
			}
		}
	}		
	}
	
	//Drops
	@EventHandler
	public void geronimoDeath(EntityDeathEvent e) {
		if(e.getEntity() instanceof Spider) {
		if(e.getEntity().hasMetadata("Geronimo")) {
			//HisDrops
			ItemStack spiderwand = new ItemStack(Material.NETHER_BRICK, 1);
		    ItemMeta itemMeta = spiderwand.getItemMeta();
		    String ikeyLore = ChatColor.RED + "Inflicts the rath of the arachnids.";
		    List<String> loreList = new ArrayList<String>();
		    loreList.add(ikeyLore);
		    itemMeta.setLore(loreList);
		    itemMeta.setDisplayName(ChatColor.WHITE + "Spider Staff");
		    spiderwand.setItemMeta(itemMeta);
		    e.getEntity().getLocation().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(spiderwand));
		}
	}
		}
	
}
