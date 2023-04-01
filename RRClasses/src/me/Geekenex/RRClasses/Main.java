package me.Geekenex.RRClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {

	public static HashMap<UUID, String> classtype;
	public static HashMap<UUID, Integer> classlevel;
	
	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		saveDefaultConfig();
		new ClassCommands(this);
		new ClassEvents(this);
		new classesGUI(this);
		new GuiUse(this);
		this.getCommand("alchemist").setExecutor(new ClassCommands(this));
		this.getCommand("clearclass").setExecutor(new ClassCommands(this));
		this.getCommand("tank").setExecutor(new ClassCommands(this));
		this.getCommand("rogue").setExecutor(new ClassCommands(this));
		this.getCommand("brawler").setExecutor(new ClassCommands(this));
		this.getCommand("disciple").setExecutor(new ClassCommands(this));
		this.getCommand("levelget").setExecutor(new ClassCommands(this));
		this.getCommand("clearlevels").setExecutor(new ClassCommands(this));
		this.getCommand("class").setExecutor(new classesGUI(this));
		this.getCommand("skilltokens").setExecutor(new ClassCommands(this));
		DiscipleCD.setupCooldown();
		
		System.out.print("RR Classes is enabled!");
		
		File dir = getDataFolder();
		if(!dir.exists())
		 if(!dir.mkdir())
			 System.out.println("Couldn't create a directory for RR Adventures!");
		
		classtype = (HashMap<UUID, String>) load(new File(getDataFolder(), "classes.dat"));
		
		if(classtype == null) {
			classtype = new HashMap<UUID, String>();
		}
		
		
		classlevel = (HashMap<UUID, Integer>) load(new File(getDataFolder(), "classlevel.dat"));
		
			if(classlevel == null) {
				classlevel = new HashMap<UUID, Integer>();
		}
			
		
	}

	@Override
	public void onDisable() {
		save(classtype, new File(getDataFolder(), "classes.dat"));
		save(classlevel, new File(getDataFolder(), "classlevel.dat"));
	}

	public void save(Object o, File f) {
		try {
			if(!f.exists())
				f.createNewFile();
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(o);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object load(File f) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			Object result = ois.readObject();
			ois.close();
			return result;
		} catch(Exception e) {
			return null;
		}
	}



}
