package me.Geekenex.RRClasses.Abilities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.Geekenex.RRClasses.Main;

public class AbilityList implements Listener {
	
	@SuppressWarnings("unused")
	private Main plugin;
	public AbilityList(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	
	
    public static Map<String, Ability> abilities = new HashMap<>();

    //Alchemist
    private Map<Player, Integer> slimyPlayers = new HashMap<>();
    
    
    public AbilityList() {
        initializeAbilities();
    }

    public void initializeAbilities() {
    	
    	/*
    	 * SHOP ABILITIES
    	 */
    	//FIREBALL
        Ability fireball = new Ability(3, 12);
        fireball.setItem(Material.BLAZE_ROD, "Fireball", ChatColor.GOLD, "Right-Click to launch a fireball at your foes.", ChatColor.YELLOW);       
        fireball.shopItem().addLore("Costs $" + fireball.getShopCost(), ChatColor.GREEN);        
        abilities.put("fireball", fireball);
        
        //HEAL POOL
        Ability healpool = new Ability(8, 30);
        healpool.setItem(Material.RED_DYE, "Heal Pool", ChatColor.RED, "Right-Click to create a small area of healing.", ChatColor.GOLD);
        healpool.shopItem().addLore("Costs $" + healpool.getShopCost(), ChatColor.GREEN);
        abilities.put("healpool", healpool);
        
        //REPULSION
        Ability repulsion = new Ability(10, 10);
        repulsion.setItem(Material.BLUE_DYE, "Repulsion", ChatColor.DARK_AQUA, "Right-Click to unleash a burst of repulsion.", ChatColor.AQUA);
        repulsion.shopItem().addLore("Costs $" + repulsion.getShopCost(), ChatColor.GREEN);
        abilities.put("repulsion", repulsion);
        
        /*
         * CLASS ABILITIES
         */
        //QUESTIONABLE POTION
        Ability questionablepotion = new Ability(0, 35);
        questionablepotion.setItem(Material.POTION, "Questionable Potion", ChatColor.DARK_PURPLE, "Surely drinking this is a good idea.", ChatColor.LIGHT_PURPLE);
        abilities.put("questionablepotion", questionablepotion);
        
        //FLAME CONCOCTION
        Ability flameconcoction = new Ability(0, 40);
        flameconcoction.setItem(Material.BLAZE_POWDER, "Flame Concoction", ChatColor.GOLD, "This flame experiment causes devastating damage.", ChatColor.YELLOW);
        abilities.put("flameconcoction", flameconcoction);
        
        //SLIMY COAT
        Ability slimycoat = new Ability(0, 30);
        slimycoat.setItem(Material.SLIME_BALL, "Slimy Coat", ChatColor.DARK_GREEN, "Everything in contact with you bounces away", ChatColor.GREEN);
        abilities.put("slimycoat", slimycoat);
    }
    
    public Ability getAbility(String identifier) {
        return abilities.get(identifier);
    }
    
    @EventHandler
    public void playerInteract(PlayerInteractEvent e) {
    	Player p = e.getPlayer();
        Location playerLocation = p.getLocation();
		ItemStack item = e.getItem();
		if(e.getAction() == null) return;
		
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			
			//Fireball
			Ability fb = abilities.get("fireball");
			if(item != null && item.isSimilar(fb.getItem())) {
				if(fb.checkCooldown(p)) {
				// Spawn a fireball with no block damage
                Fireball fireball = p.launchProjectile(Fireball.class);
                fireball.setIsIncendiary(false);
                fireball.setYield(0);

                // Set the fireball velocity in the direction the player is looking
                Vector direction = p.getLocation().getDirection().multiply(2);
                fireball.setVelocity(direction);
                //Sound
	            playerLocation.getWorld().playSound(playerLocation, Sound.ENTITY_BLAZE_SHOOT, 1.0f, 1.0f);
	            
	            fb.setCooldown(p, fb.getCooldownValue());
				} else {
					p.sendMessage(fb.cooldownMessage(p));
				}
			}
			
			//Heal pool
			Ability hp = abilities.get("healpool");
			if(item != null && item.isSimilar(hp.getItem())) {
				if(hp.checkCooldown(p)) {
	            // Create an AreaEffectCloud entity
	            AreaEffectCloud cloud = p.getWorld().spawn(p.getLocation(), AreaEffectCloud.class);
	            cloud.setRadius(3.0f); // Set the radius of the area
	            cloud.setDuration(4 * 20); // Set the duration (5 seconds, in ticks)
	            cloud.setParticle(Particle.HEART); // Set the particle effect
	            cloud.setColor(Color.GREEN); // Set the color of the cloud
	            cloud.setReapplicationDelay(40); // Set the reapplication delay in ticks (10 ticks = 0.5 seconds)

	            // Create a potion effect for healing
	            PotionEffect healEffect = new PotionEffect(PotionEffectType.HEAL, 0, 1, true, false, true);
	            cloud.addCustomEffect(healEffect, true); // Add the potion effect to the cloud
	            //Sound
	            playerLocation.getWorld().playSound(playerLocation, Sound.ENTITY_PLAYER_BREATH, 1.0f, 1.0f);
	            
	            hp.setCooldown(p, hp.getCooldownValue());
	        } else {
	        	p.sendMessage(hp.cooldownMessage(p));
	        }
			}
			
			Ability repulsion = abilities.get("repulsion");
			//Repulsion
			if (item != null && item.isSimilar(repulsion.getItem())) {
				if(repulsion.checkCooldown(p)) {
	            spawnRepulsionParticles(playerLocation);

	            double knockbackStrength = 2.0;
	            applyKnockbackToNearbyMobs(playerLocation, knockbackStrength, plugin);
	            //Sound
	            playerLocation.getWorld().playSound(playerLocation, Sound.ENTITY_CAMEL_DASH, 1.0f, 1.0f);
	            
	            repulsion.setCooldown(p, repulsion.getCooldownValue());
	        } else {
	        	p.sendMessage(repulsion.cooldownMessage(p));
	        }
			}
			
			//Flame Concoction
			Ability fc = abilities.get("flameconcoction");
			if (item != null && item.isSimilar(fc.getItem())) {
				if(fc.checkCooldown(p)) {
			// Launch the blaze powder
            Location location = p.getEyeLocation();
            Vector direction = location.getDirection().normalize().multiply(1.5);
            Item droppedItem = p.getWorld().dropItem(location, new ItemStack(Material.BLAZE_POWDER));
            droppedItem.setVelocity(direction);
            droppedItem.setPickupDelay(Integer.MAX_VALUE);
            final double RANGE = 5.0; // The range of the area of effect
            final int FIRE_TICKS = 100; // The number of ticks the target should be on fire
            // Schedule the blaze powder to create an area of effect and then remove itself
            AtomicInteger taskId = new AtomicInteger(); // Use AtomicInteger to store the task ID
            int delay = 20;
            int interval = 20;

            taskId.set(Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                int count = 0; // Keep track of the number of times the task has run

                @Override
                public void run() {
                    Location groundLocation = droppedItem.getLocation();

                    // Create flame particles around the blaze powder
                    p.getWorld().spawnParticle(Particle.FLAME, groundLocation, 100, 1, 1, 1);

                    // Play a cool sound
                    p.getWorld().playSound(groundLocation, Sound.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 1.0f, 1.0f);

                    // Damage and ignite targets in the area of effect
                    for (Entity entity : droppedItem.getNearbyEntities(RANGE, RANGE, RANGE)) {
                        if (entity instanceof LivingEntity) {
                            LivingEntity livingEntity = (LivingEntity) entity;
                            livingEntity.damage(4, p); // Deal 4 damage
                            livingEntity.setFireTicks(FIRE_TICKS); // Set the target on fire
                        }
                    }

                    // Increment the count and check if the task should stop
                    count++;
                    if (count >= 4) { // 4 times longer
                        Bukkit.getScheduler().cancelTask(taskId.get());
                        // Remove the blaze powder item
                        droppedItem.remove();
                    }
                }
            }, delay, interval));
            
            fc.setCooldown(p, fc.getCooldownValue());
	    } else {
	    	p.sendMessage(fc.cooldownMessage(p));
	    }
			}
			
		//Slimy Coat
			Ability sc = abilities.get("slimycoat");
			if (item != null && item.isSimilar(sc.getItem())) {
				if(sc.checkCooldown(p)) {
			    // Activate the Slimy Coat ability
				p.sendMessage(ChatColor.GREEN + "You're now coated in slime! The effect will wear off in 30 seconds.");

	            double bounceStrength = 1.0;
	            slimyPlayers.put(p, (int)bounceStrength);
	            bounceStrengths.put(p, bounceStrength);

	            // Schedule task to remove slime coat after 30 seconds
	            Bukkit.getScheduler().runTaskLater(plugin, () -> {
	                slimyPlayers.remove(p);
	                bounceStrengths.remove(p);
	                p.sendMessage(ChatColor.YELLOW + "The slime effect has worn off.");
	            }, 50 * 20L);
	            
	            sc.setCooldown(p, sc.getCooldownValue());
			} else {
				p.sendMessage(sc.cooldownMessage(p));
			}	
			}
			
			
		}
    }
    

    @EventHandler
    public void onEntityCollision(EntityDamageByEntityEvent e) {
        Entity damaged = e.getEntity();
        Entity damager = e.getDamager();

        if (damager instanceof Player) {
            Player player = (Player) damager;

            // Check if the player has Slimy Coat active
            if (slimyPlayers.containsKey(player)) {
                Vector direction = damaged.getLocation().subtract(player.getLocation()).toVector().normalize().multiply(2.5);
                direction.setY(Math.max(direction.getY(), 0.3));

                // Apply the velocity change to the damaged entity
                damaged.setVelocity(direction);
            }
            
        }
        if (damaged instanceof Player) {
            Player player = (Player) damaged;

            // Check if the player has Slimy Coat active
            if (slimyPlayers.containsKey(player)) {
                Vector direction = damager.getLocation().subtract(player.getLocation()).toVector().normalize().multiply(2.5);
                direction.setY(Math.max(direction.getY(), 0.3));

                // Apply the velocity change to the damager entity
                damager.setVelocity(direction);
            }
        }
    }
    
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        // Check if the player has Slimy Coat active and is on the ground
        if (slimyPlayers.containsKey(player) && player.isOnGround()) {
            bouncePlayer(player);
        }
    }
    
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();

            // Check if the player has Slimy Coat active and the damage is from falling
            if (slimyPlayers.containsKey(player) && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                e.setCancelled(true); // Cancel the fall damage
            }
        }
    }
    
    private Map<Player, Double> bounceStrengths = new HashMap<>();
    private void bouncePlayer(Player player) {
        Vector velocity = player.getVelocity();
        double bounceStrength = bounceStrengths.getOrDefault(player, 0.6);
        velocity.setY(bounceStrength);

        Vector direction = player.getLocation().getDirection().normalize().multiply(0.3); // You can adjust the multiplier for more or less acceleration
        velocity.add(direction);

        player.setVelocity(velocity);
    }
    
    

    private void spawnRepulsionParticles(Location location) {
        int particleCount = 300;
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.AQUA, 1);
        location.getWorld().spawnParticle(Particle.REDSTONE, location, particleCount, 1, 1, 1, 5.0, dustOptions);
    }

    private void applyKnockbackToNearbyMobs(Location location, double knockbackStrength, Plugin plugin) {
        double radius = 5;
        for (Entity entity : location.getWorld().getNearbyEntities(location, radius, radius, radius)) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                Vector direction = livingEntity.getLocation().subtract(location).toVector().normalize().multiply(knockbackStrength);
                direction.setY(Math.max(direction.getY(), 0.2));

                // Apply the velocity change after a short delay
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                    livingEntity.setVelocity(direction);
                    if (entity instanceof Player) {
                        Player player = (Player) entity;
                        int originalNoDamageTicks = player.getNoDamageTicks();
                        player.setNoDamageTicks(0);
                        player.setVelocity(direction);
                        player.setNoDamageTicks(originalNoDamageTicks);
                    }
                }, 1L);
            }
        }
    }
    
    @EventHandler
    public void onPotionDrink(PlayerItemConsumeEvent e) {
    	Player p = e.getPlayer();
    	Ability qp = abilities.get("questionablepotion");
    	if(e.getItem().isSimilar(qp.getItem())) {
    		e.setCancelled(true);
    		if(qp.checkCooldown(p)) {
    		p.setFoodLevel(0);
    		p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 16 * 20, 1));
    		p.getLocation().getWorld().spawnParticle(Particle.CRIT_MAGIC, p.getLocation(), 200, 1, 1, 1, 5.0);
    		//Sound
            p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1.0f, 1.0f);
            
            qp.setCooldown(p, qp.getCooldownValue());
    	} else {
    		p.sendMessage(qp.cooldownMessage(p));
    	}
    	}
    }
    
}