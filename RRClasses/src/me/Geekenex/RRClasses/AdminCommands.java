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
            
            for (Attribute attribute : Attribute.values()) {
                AttributeInstance attributeInstance = player.getAttribute(attribute);
                
                if (attributeInstance != null) {
                    if (attribute == Attribute.GENERIC_MOVEMENT_SPEED) {
                        attributeInstance.setBaseValue(0.1);
                        sender.sendMessage("Attribute " + attributeInstance.getAttribute().name() + " set to " + attributeInstance.getBaseValue());
                    } else {
                        attributeInstance.setBaseValue(attributeInstance.getDefaultValue());
                        sender.sendMessage("Attribute " + attributeInstance.getAttribute().name() + " set to " + attributeInstance.getDefaultValue());
                    }
                }
            }
            
            player.sendMessage("All attributes have been reset to their default values.");
            return true;
        }
        
        return true;
    }
}
