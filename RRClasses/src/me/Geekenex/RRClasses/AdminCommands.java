package me.Geekenex.RRClasses;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommands implements CommandExecutor {
	
	@SuppressWarnings("unused")
	private Main plugin;
	public AdminCommands(Main plugin) {
		this.plugin = plugin;
	}
	
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }
        
        if (label.equalsIgnoreCase("resetattributes")) {
            Player player = (Player) sender;
            
            double defaultSpeed = plugin.getConfig().getDouble("default_speed");
        	double defaultHealth = plugin.getConfig().getDouble("default_health");
        	double defaultKnockbackResistance = plugin.getConfig().getDouble("default_knockbackresistance");
        	double defaultDamage = plugin.getConfig().getDouble("default_damage");
        	double defaultAttackSpeed = plugin.getConfig().getDouble("default_attackspeed");
        	double defaultArmor = plugin.getConfig().getDouble("default_armor");
        	double defaultArmorToughness = plugin.getConfig().getDouble("default_armortoughness");
        	
        	AttributeInstance speed = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        	AttributeInstance health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        	AttributeInstance knockbackresistance = player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
        	AttributeInstance damage = player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        	AttributeInstance attackspeed = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        	AttributeInstance armor = player.getAttribute(Attribute.GENERIC_ARMOR);
        	AttributeInstance armortoughness = player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS);
        	
        	speed.setBaseValue(defaultSpeed);
        	health.setBaseValue(defaultHealth);
        	knockbackresistance.setBaseValue(defaultKnockbackResistance);
        	damage.setBaseValue(defaultDamage);
        	attackspeed.setBaseValue(defaultAttackSpeed);
        	armor.setBaseValue(defaultArmor);
        	armortoughness.setBaseValue(defaultArmorToughness);
        	
        	
            player.sendMessage("All attributes have been reset to their default values.");
            return true;
        }
        
        return true;
    }
}
