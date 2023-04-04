package me.Geekenex.RRClasses.Abilities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
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

    public AbilityList() {
        initializeAbilities();
    }

    public void initializeAbilities() {
    	
    	/*
    	 * SHOP ABILITIES
    	 */
    	//FIREBALL
        Ability fireball = new Ability(3, 15);
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
        Ability questionablepotion = new Ability(10, 10);
        questionablepotion.setItem(Material.POTION, "Questionable Potion", ChatColor.DARK_PURPLE, "Surely drinking this is a good idea.", ChatColor.LIGHT_PURPLE);
        abilities.put("questionablepotion", questionablepotion);
        
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
			if(item != null && item.isSimilar(abilities.get("fireball").getItem())) {
				// Spawn a fireball with no block damage
                Fireball fireball = p.launchProjectile(Fireball.class);
                fireball.setIsIncendiary(false);
                fireball.setYield(0);

                // Set the fireball velocity in the direction the player is looking
                Vector direction = p.getLocation().getDirection().multiply(2);
                fireball.setVelocity(direction);
                //Sound
	            playerLocation.getWorld().playSound(playerLocation, Sound.ENTITY_BLAZE_SHOOT, 1.0f, 1.0f);
			}
			
			//Heal pool
			if(item != null && item.isSimilar(abilities.get("healpool").getItem())) {
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
	        }
			
			//Repulsion
			if (item != null && item.isSimilar(abilities.get("repulsion").getItem())) {
	            spawnRepulsionParticles(playerLocation);

	            double knockbackStrength = 2.0;
	            applyKnockbackToNearbyMobs(playerLocation, knockbackStrength, plugin);
	            //Sound
	            playerLocation.getWorld().playSound(playerLocation, Sound.ENTITY_CAMEL_DASH, 1.0f, 1.0f);
	        }
	    }
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
    	if(e.getItem().isSimilar(abilities.get("questionablepotion").getItem())) {
    		e.setCancelled(true);
    		p.setFoodLevel(0);
    		p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 16 * 20, 1));
    		p.getLocation().getWorld().spawnParticle(Particle.CRIT_MAGIC, p.getLocation(), 200, 1, 1, 1, 5.0);
    		//Sound
            p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1.0f, 1.0f);
    	}
    }
    
}