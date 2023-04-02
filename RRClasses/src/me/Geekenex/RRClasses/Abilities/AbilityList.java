package me.Geekenex.RRClasses.Abilities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.Geekenex.RRClasses.Main;

public class AbilityList implements Listener {
	
	@SuppressWarnings("unused")
	private Main plugin;
	public AbilityList(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	
	
    private Map<String, Ability> abilities = new HashMap<>();

    public AbilityList() {
        initializeAbilities();
    }

    public void initializeAbilities() {
        Ability fireball = new Ability(false, 1, 15, 3);
        fireball.setItem(Material.BLAZE_ROD, "Fireball", ChatColor.GOLD, "Right-Click to launch a fireball at your foes.", ChatColor.YELLOW);

        abilities.put("fireball", fireball);
        
        
    }
    
    public Ability getAbility(String identifier) {
        return abilities.get(identifier);
    }
    
    @EventHandler
    public void playerInteract(PlayerInteractEvent e) {
    	Player p = e.getPlayer();
		ItemStack item = e.getItem();
		if(e.getAction() == null) return;
		
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			
			//Fireball
			if(item != null && item.getType().equals(Material.BLAZE_ROD)) {
				// Spawn a fireball with no block damage
                Fireball fireball = p.launchProjectile(Fireball.class);
                fireball.setIsIncendiary(false);
                fireball.setYield(0);

                // Set the fireball velocity in the direction the player is looking
                Vector direction = p.getLocation().getDirection().multiply(2);
                fireball.setVelocity(direction);
			}
			
			}
    }
    
}