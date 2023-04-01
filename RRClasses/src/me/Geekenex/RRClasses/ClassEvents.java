package me.Geekenex.RRClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class ClassEvents implements Listener {

	private Main plugin;
	public ClassEvents(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	int discipleRespawn = 1;
	
	//Function that checks for the player's class and gives them their buffs accordingly
	public void giveClassBuffs(Player player) {
		
		UUID puid = player.getUniqueId();
		
		int effectlvl = Main.classlevel.get(puid);
		
		for(PotionEffect effect:player.getActivePotionEffects()){
			player.removePotionEffect(effect.getType());
		}
		
		//Checks if player is in the class file.
		if(Main.classtype.containsKey(puid)) {
			
			//Checks if player's class is Alchemist
			if(Main.classtype.get(puid).contains("alchemist")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100000, effectlvl, false, false));
			}
			
			//Checks if player's class is Tank
			if(Main.classtype.get(puid).contains("tank")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 100000, effectlvl, false, false));
				if(effectlvl < 4) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000, 0, false, false));
				}
				if(effectlvl == 4) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000, 1, false, false));
				}
			}
			
			//Checks if player's class is Rogue
			if(Main.classtype.get(puid).contains("rogue")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, Math.round(effectlvl / 2), false, false));
				if(effectlvl > 0 && effectlvl < 3) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100000, 0, false, false));
				}
				if(effectlvl > 2) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100000, 1, false, false));
					}
			}
			
			//Checks if player's class is Brawler
			if(Main.classtype.get(puid).contains("brawler")) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000, Math.round(effectlvl / 2), false, false));
			if(effectlvl > 0) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 100000, 0, false, false));
			}
			if(effectlvl > 2) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100000, 0, false, false));
				}
		   }
		}
	}
	
	public void giveClassSkills(Player player) {
		
		UUID puid = player.getUniqueId();
		
		@SuppressWarnings("unused")
		int effectlvl = Main.classlevel.get(puid);
		
		
		//Checks if player is in the class file.
		if(Main.classtype.containsKey(puid)) {
			
			//Checks if player's class is Alchemist
			if(Main.classtype.get(puid).contains("alchemist")) {
				
			}
			
			//Checks if player's class is Tank
			if(Main.classtype.get(puid).contains("tank")) {
				
			}
			
			//Checks if player's class is Rogue
			
			if(Main.classtype.get(puid).contains("rogue")) {
				
				//The Item
				ItemStack rf = new ItemStack(Material.FEATHER);
			    ItemMeta rfmeta = rf.getItemMeta();
			    String rflore = ChatColor.DARK_GREEN + "Right-Click to dash forwards.";
			    List<String> loreList = new ArrayList<String>();
			    loreList.add(rflore);
			    rfmeta.setLore(loreList);
			    rfmeta.setDisplayName(ChatColor.GREEN + "Rogue Dash");
			    rf.setItemMeta(rfmeta);
				if(player.getInventory().contains(rf)) return;
				player.getInventory().setItem(8, rf);
				
				
			}
			
			//Checks if player's class is Brawler
			if(Main.classtype.get(puid).contains("brawler")) {
				
		   }
			//Checks if player's class is Disciple
			if(Main.classtype.get(puid).contains("disciple")) {
				
		   }
		}
	}
	

	
	@EventHandler
	public void joinEffects(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		UUID puid = player.getUniqueId();
		
		if(!Main.classlevel.containsKey(puid)) {
			Main.classlevel.put(puid, 0);
		}
		
		for(PotionEffect effect:player.getActivePotionEffects()){
			player.removePotionEffect(effect.getType());
		}
		
		giveClassBuffs(player);
		giveClassSkills(player);
		
	}
	
	@EventHandler
	public void deathEvent(PlayerRespawnEvent e) {
		Player player = e.getPlayer();	
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
            public void run() {
        				
        giveClassBuffs(player);
        giveClassSkills(player);
		
            }
	  }, 10);
		
  }	

    @SuppressWarnings("deprecation")
	public static boolean isSafeLocation(Location location) {
        try {
            Block feet = location.getBlock();
            if (!feet.getType().isTransparent() && !feet.getLocation().add(0, 1, 0).getBlock().getType().isTransparent()) {
                return false; // not transparent (will suffocate)
            }
            Block head = feet.getRelative(BlockFace.UP);
            if (!head.getType().isTransparent()) {
                return false; // not transparent (will suffocate)
            }
            Block ground = feet.getRelative(BlockFace.DOWN);
            // returns if the ground is solid or not.
            return ground.getType().isSolid();
        } catch (Exception err) {
        }
        return false;
    }
	
	
	@EventHandler
	public void classLevelUp(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		ItemStack item = e.getItem();
		if(e.getItem() == null) return;
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.getHand() == EquipmentSlot.HAND) {
				if(item.getType().equals(Material.MAGMA_CREAM)) {
					if(Main.classlevel.containsKey(player.getUniqueId()) && Main.classlevel.get(player.getUniqueId()) < 4) {
				    int lvl = Main.classlevel.containsKey(player.getUniqueId())?Main.classlevel.get(player.getUniqueId()):0;
				    Main.classlevel.put(player.getUniqueId(), lvl + 1);
					int amount = e.getItem().getAmount();
					if(amount > 1) {
						player.getInventory().getItemInMainHand().setAmount(amount-1);
					} else {
						player.getInventory().getItemInMainHand().setAmount(0);
					}
					player.sendMessage(Utils.chat(plugin.getConfig().getString("plugin_prefix")) + Utils.chat("&7Your skills sharpen!"));
					giveClassBuffs(player);
					giveClassSkills(player);
					
				} else {
					player.sendMessage(Utils.chat(plugin.getConfig().getString("plugin_prefix")) + Utils.chat("&cYou can no longer sharpen your skills!"));
				}
				} else if(item.getType().equals(Material.FEATHER)) {
					if(item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Rogue Dash")) {
						player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1, 1);
						int playerX = player.getLocation().getBlockX();
						int playerY = player.getLocation().getBlockY();
						int playerZ = player.getLocation().getBlockZ();
						player.setVelocity(player.getLocation().getDirection().multiply(1.6).setY(0.8));
						player.getLocation().getWorld().spawnParticle(Particle.CLOUD, playerX, playerY, playerZ, 6);
						}
					}
				}
	}
		}
	
	
	//Disciple Ability
	@EventHandler
	public void discipleDeath(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
		Player p = (Player) e.getEntity();
		//Checks if the entity will die and if entity is player
	    if (((p.getHealth() - e.getFinalDamage()) <= 0)) {
			UUID puid = p.getUniqueId();
			
			int effectlvl = Main.classlevel.get(puid);
			
			for(PotionEffect effect:p.getActivePotionEffects()){
				p.removePotionEffect(effect.getType());
			}
			
			//Checks if player's class is Disciple
	    	
			if(Main.classtype.get(puid).contains("disciple")) {
				if(DiscipleCD.checkCooldown(p)) {
					DiscipleCD.setCooldown(p,60);
				e.setCancelled(true);
				
			p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_DEATH, 2F, 1F);
			p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 2F, 1F);
			p.setHealth(4);
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, effectlvl, false, false));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, effectlvl, false, false));
			p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 400, 0, false, false));
			if(effectlvl == 4) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 400, 0, false, false));
			}
			p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 400, 0, false, false));
			p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 400, 0, false, false));
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 400, effectlvl, false, false));
			p.sendMessage(Utils.chat("&rGod answers your prayers."));
			} else {
				p.sendMessage(Utils.chat("&8God ignores your prayers."));
			}
			}
			}
	    
        if(e.getCause() == DamageCause.FALL){
        	UUID puid = p.getUniqueId();
        	if(Main.classtype.get(puid).contains("rogue")) {
            e.setCancelled(true);
        	}
        }
	    }
	}
	
	//Alchemist Ability
	@EventHandler
	public void onAlchemistHit(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			if(e.getEntity() instanceof LivingEntity) {
				LivingEntity lentity = (LivingEntity) e.getEntity();
			Player p = (Player) e.getDamager();
			UUID puid = p.getUniqueId();
			if(Main.classtype.get(puid).contains("alchemist")) {
				int effectlvl = Main.classlevel.get(puid);
			e.getEntity().setFireTicks(60);
			if(effectlvl > 0) {
				lentity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60 ,Math.round(effectlvl / 2)));
			}
			if(effectlvl > 1) {
				lentity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200 ,Math.round(effectlvl / 2)));
			}
			if(effectlvl > 2) {
				lentity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60 ,Math.round(effectlvl / 2)));
			}
			
		}
	}
		}
	}
	
	//Stops players from removing their skills
	@EventHandler
	public void onSkillClick(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		//Rogue
        if(e.getCurrentItem() == null) return;
        if(e.getCurrentItem().getItemMeta() == null) return;
        if(e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        if(p.getGameMode().equals(GameMode.CREATIVE)) return;
  		if(e.getCurrentItem().getItemMeta().getDisplayName().contentEquals(ChatColor.GREEN + "Rogue Dash")) {
			e.setCancelled(true);
		}
	}
	//Stops players from removing their skills
	@EventHandler
	public void onDropSkill(PlayerDropItemEvent e) {
		
		if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
		//Rogue
  		if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().contentEquals(ChatColor.GREEN + "Rogue Dash")) {
			e.setCancelled(true);
		}
	}
	
	
	
	
}
