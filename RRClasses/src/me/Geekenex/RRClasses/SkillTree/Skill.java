package me.Geekenex.RRClasses.SkillTree;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.Geekenex.RRClasses.CustomItem;
import me.Geekenex.RRClasses.Abilities.Ability;

public class Skill implements Serializable {
	

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
    
    public Skill(String name, String description, int requiredXP, int guiSlot) {
        this.name = name;
        this.description = description;
        this.requiredXPLevel = requiredXP;
        this.guiSlot = guiSlot;
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
}