package com.Jacksonnn.AirJump;

import com.projectkorra.projectkorra.ability.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import com.projectkorra.projectkorra.ProjectKorra;

public class AirJump extends AirAbility implements AddonAbility {

	public AirJump(Player player) {
		super(player);
	}

	@Override
	public void progress() {
		if (player == null || !player.isOnline() || player.isDead()) {
			remove();
			return;
		}

	}

	@Override
	public long getCooldown() {
		return 30000;
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
