package me.Geekenex.rradventures;


import org.bukkit.plugin.java.JavaPlugin;

import me.Geekenex.rradventures.bosses.CrazyMike;
import me.Geekenex.rradventures.bosses.Geronimo;
import me.Geekenex.rradventures.bosses.MotherCuckoo;
import me.Geekenex.rradventures.commands.Currencies;
import me.Geekenex.rradventures.commands.IronKey;
import me.Geekenex.rradventures.commands.NoobVilleTransporter;
import me.Geekenex.rradventures.events.death.Respawn;
import me.Geekenex.rradventures.events.messages.Chat;
import me.Geekenex.rradventures.events.messages.Join;
import me.Geekenex.rradventures.events.messages.Leave;
import me.Geekenex.rradventures.events.teleportation.Cave;
import me.Geekenex.rradventures.events.teleportation.Well;
import me.Geekenex.rradventures.events.villageexit.NoobVille;
import me.Geekenex.rradventures.events.worldinteraction.BlockBreaks;
import me.Geekenex.rradventures.events.worldinteraction.BlockPlace;
import me.Geekenex.rradventures.events.worldinteraction.BucketUsing;
import me.Geekenex.rradventures.events.worldinteraction.Farming;
import me.Geekenex.rradventures.items.AllCurrencies;
import me.Geekenex.rradventures.items.CrazyPowder;
import me.Geekenex.rradventures.items.NVShard;
import me.Geekenex.rradventures.items.NVTransporter;
import me.Geekenex.rradventures.items.NoobVilleKey;
import me.Geekenex.rradventures.items.SpiderWand;
import me.Geekenex.rradventures.items.WaterPill;
import me.Geekenex.rradventures.mobdrops.CrazyMikeDrops;
import me.Geekenex.rradventures.mobdrops.Mobs;
import me.Geekenex.rradventures.utils.CooldownManager;
import me.Geekenex.rradventures.utils.MCcooldown;

public class Main extends JavaPlugin {
	
@Override
public void onEnable() {
	saveDefaultConfig();
	new Join(this);
	new Leave(this);
	new Chat(this);
	new BlockBreaks(this);
	new BlockPlace(this);
	new Farming(this);
	new CrazyMike(this);
	new CrazyMikeDrops(this);
	new CrazyPowder(this);
	new Mobs(this);
	new NoobVille(this);
	new NoobVilleKey(this);
	new SpiderWand(this);
	new NVShard(this);
	new Cave(this);
	new Well(this);
	new BucketUsing(this);
	new MotherCuckoo(this);
	new WaterPill(this);
	new Geronimo(this);
	new NVTransporter(this);
	new AllCurrencies(this);
	new Respawn(this);
	MCcooldown.setupCooldown();
	CooldownManager.setupCooldown();
	this.getCommand("nvkey").setExecutor(new IronKey(this));
	this.getCommand("nvtrans").setExecutor(new NoobVilleTransporter(this));
	this.getCommand("currencies").setExecutor(new Currencies(this));
	
	System.out.print("RR Adventures is locked and loaded!");
	
}

@Override
public void onDisable() {

}


}
