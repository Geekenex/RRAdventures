package me.Geekenex.RRClasses.SkillTree;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

import me.Geekenex.RRClasses.CustomItem;
import me.Geekenex.RRClasses.Main;
import me.Geekenex.RRClasses.Abilities.Ability;

public class Skill implements Serializable {
	
	private Main plugin;

	private static final long serialVersionUID = 1L;
	private String name;
    private String description;
    private int requiredXPLevel;
    private Skill prerequisite;
    private int guiSlot;
    private CustomItem item;
    private boolean isAbility;
    private Ability ability;
    private HashMap<UUID, Boolean> playerUnlockStatus = new HashMap<>();
    
    public Skill(String name, String description, int requiredXP, int guiSlot, Main plugin) {
        this.name = name;
        this.description = description;
        this.requiredXPLevel = requiredXP;
        this.guiSlot = guiSlot;
        this.plugin = plugin;
        item = new CustomItem(Material.PAPER, name, ChatColor.GREEN, description, ChatColor.GRAY);
    }

    // Add a prerequisite skill by its name
    public void addPrerequisite(Skill prerequisite) {
    	this.prerequisite = prerequisite;     
    	
    }
    
    //Creates the skill item for the GUI
    public void createItem(Player player) {
        boolean isUnlocked = isUnlocked(player.getUniqueId());
        item.clearLore();
        
        item.addLore(description, ChatColor.GRAY);
        if (isUnlocked) {
            item.addLore("Unlocked", ChatColor.LIGHT_PURPLE);
        } else {
            if (prerequisite != null) {
                item.addLore("XP Level Unlock Cost: " + requiredXPLevel, ChatColor.GOLD);
                item.addLore("Prerequisite: " + prerequisite.getName(), ChatColor.RED);
            }
            item.removeLore("Unlocked", ChatColor.LIGHT_PURPLE);
        }
    }
    
    public void setUnlocked(UUID playerId) {
        playerUnlockStatus.put(playerId, true);
    }

    public boolean isUnlocked(UUID playerId) {
        return playerUnlockStatus.getOrDefault(playerId, false);
    }
    
    public CustomItem getCustomItem() {
    	return item;
    }

    // Get the name of the skill
    public String getName() {
        return name;
    }

    // Get the description of the skill
    public String getDescription() {
        return description;
    }

    // Get the required XP to unlock the skill
    public int getRequiredXPLevel() {
        return requiredXPLevel;
    }

    // Get the prerequisites for the skill
    public Skill getPrerequisite() {
        return prerequisite;
    }

	public int getGuiSlot() {
		return guiSlot;
	}
	
	public void setAbility(Ability a) {
		ability = a;
		isAbility = true;
	}
	
	public Boolean isAbility() {
		return isAbility;
	}
	
	public Ability getAbility() {
		if(isAbility)
			return ability;
		else
			return null;
	}
	
    public void applyEffect(Player player) {
    	//Attributes
    	AttributeInstance speed = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
    	AttributeInstance health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
    	AttributeInstance knockbackresistance = player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
    	AttributeInstance damage = player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
    	AttributeInstance attackspeed = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
    	AttributeInstance armor = player.getAttribute(Attribute.GENERIC_ARMOR);
    	AttributeInstance armortoughness = player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS);
        
    	double defaultSpeed = plugin.getConfig().getDouble("default_speed");
    	double defaultHealth = plugin.getConfig().getDouble("default_health");
    	double defaultKnockbackResistance = plugin.getConfig().getDouble("default_knockbackresistance");
    	double defaultDamage = plugin.getConfig().getDouble("default_damage");
    	double defaultAttackSpeed = plugin.getConfig().getDouble("default_attackspeed");
    	double defaultArmor = plugin.getConfig().getDouble("default_armor");
    	double defaultArmorToughness = plugin.getConfig().getDouble("default_armortoughness");
        //Tier 1 buffs
        if (this.name.equals("Quicker Feet")) {
            speed.setBaseValue(defaultSpeed * 1.1);
        }
        if (this.name.equals("Heavier Hits")) {
            damage.setBaseValue(defaultDamage + 2);
        }
        if (this.name.equals("Resilience")) {
            health.setBaseValue(defaultHealth + 2);
        }
        
        // Add other skills' effects here
    }
    
}