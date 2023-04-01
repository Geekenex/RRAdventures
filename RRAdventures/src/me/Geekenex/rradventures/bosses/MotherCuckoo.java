package me.Geekenex.rradventures.bosses;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Geekenex.rradventures.Main;
import me.Geekenex.rradventures.utils.MCcooldown;
import me.Geekenex.rradventures.utils.Utils;

public class MotherCuckoo implements Listener {

	private Main plugin;
	public MotherCuckoo(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	World world = Bukkit.getServer().getWorld("smt_world");
	
	@EventHandler
	public void brickWalk(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if(e.getItem() == null) return;
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(e.getItem().getType().equals(Material.HEART_OF_THE_SEA)) {
				if(MCcooldown.checkCooldown(player)) {
					MCcooldown.setCooldown(player,180);
			int amount = e.getPlayer().getInventory().getItemInMainHand().getAmount();
			if(amount > 1) {
				player.getInventory().getItemInMainHand().setAmount(amount-1);
			} else {
				player.getInventory().getItemInMainHand().setAmount(0);
			}
				Bukkit.broadcastMessage(Utils.chat("&bThe Gods answer your summon."));
				Location mcloc = new Location(world, 209, 14, 187);
			    Guardian mckid = (Guardian) world.spawnEntity(mcloc, EntityType.GUARDIAN);
			    mckid.setMetadata("mckid", new FixedMetadataValue(plugin, "mckid"));
			} else {
				e.getPlayer().sendMessage(ChatColor.DARK_GRAY + "The Gods ignore your summon.");
			}
		}
	}
	}

	
	@EventHandler
	public void kidDeath(EntityDeathEvent e) {
		if(e.getEntity() instanceof LivingEntity) {
		if(e.getEntityType() == EntityType.GUARDIAN) {
			if(e.getEntity().hasMetadata("mckid")) {
				Location mcloc = new Location(world, 209, 14, 187);
			    ElderGuardian mc = (ElderGuardian) world.spawnEntity(mcloc, EntityType.ELDER_GUARDIAN);
			   Bukkit.getServer().broadcastMessage(Utils.chat("&3Mother Cuckoo &1> &7How dare you hurt my child!"));
			    mc.setCustomName(ChatColor.AQUA + "Mother Cuckoo");
			    mc.setCustomNameVisible(true);
			    mc.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 3000, 1));
			    mc.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(160);
			    mc.setHealth(100);
			    mc.setMetadata("MotherCuckoo", new FixedMetadataValue(plugin, "mothercuckoo"));
			    
			}
			
		}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof ElderGuardian && e.getDamager() instanceof Player) {
			if(e.getEntity().hasMetadata("MotherCuckoo")) {
				int random = ThreadLocalRandom.current().nextInt(10);
				if(random<3) {
					Player p = (Player) e.getDamager();
					p.playSound(p.getLocation(), Sound.AMBIENT_UNDERWATER_EXIT, 2F, 1F);
					Location loc = e.getEntity().getLocation();
					world.spawnEntity(loc, EntityType.GUARDIAN);
					
				}
			}
		}
	}
	
	
	
	
}
