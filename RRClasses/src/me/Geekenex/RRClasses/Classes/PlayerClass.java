package me.Geekenex.RRClasses.Classes;

import java.io.Serializable;

public class PlayerClass implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	private String className;

    public PlayerClass(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
    
   public Boolean equals(PlayerClass pc) {
	   if(className == pc.className) return true;
	   return false;
   }
}