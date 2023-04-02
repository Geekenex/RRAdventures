package me.Geekenex.RRClasses.Abilities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class AbilityList {
	
    private Map<String, Ability> abilities = new HashMap<>();

    public AbilityList() {
        initializeAbilities();
    }

    public void initializeAbilities() {
        Ability fireball = new Ability(false, 1, 15);
        fireball.setItem(Material.BLAZE_ROD, "Fireball", ChatColor.GOLD, "Right-Click to launch a fireball at your foes.", ChatColor.YELLOW);

        abilities.put("fireball", fireball);
        
        
    }
    
    
    public Ability getAbility(String identifier) {
        return abilities.get(identifier);
    }
}