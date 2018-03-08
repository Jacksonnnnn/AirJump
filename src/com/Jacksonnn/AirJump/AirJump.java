package com.Jacksonnn.AirJump;

import com.projectkorra.projectkorra.ability.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.ProjectKorra;

public class AirJump extends AirAbility implements AddonAbility {

	Vector vec;
	
	public AirJump(Player player) {
		super(player);
		
		if (!bPlayer.canBend(this)) {
			return;
		}
		setFields();
		start();
	}
	
	public void setFields() {
		vec = player.getLocation().getDirection().normalize().multiply(5);
		vec.setY(1.5);
	}

	@Override
	public void progress() {
		if (player == null || !player.isOnline() || player.isDead()) {
			remove();
			return;
		}
		player.setVelocity(vec);
		bPlayer.addCooldown(this);
		remove();
		return;
	}

	@Override
	public long getCooldown() {
		return 1000;
	}

	@Override
	public Location getLocation() {
		return null;
	}

	@Override
	public boolean isHarmlessAbility()	{
		return true;
	}

	@Override
	public boolean isSneakAbility() {
		return true;
	}

	@Override
	public boolean isHiddenAbility() {
		return false;
	}

	@Override
	public void load() {
		ProjectKorra.plugin.getServer().getPluginManager().registerEvents(new AirJumpListener(), ProjectKorra.plugin);
		
		ProjectKorra.log.info("Successfully enabled " + getName() + " by " + getAuthor());
	}

	@Override
	public void stop() {
		ProjectKorra.log.info("Successfully disabled " + getName() + " by " + getAuthor());
	}

	@Override
	public String getName() {
		return "AirJump";
	}

	@Override
	public String getAuthor() {
		return "Jacksonnn";
	}

	@Override
	public String getVersion() {
		return "BETA 1.0";
	}

	@Override
	public String getDescription() {
		return "Author: " + getAuthor() + "\nAirJump";
	}

}
