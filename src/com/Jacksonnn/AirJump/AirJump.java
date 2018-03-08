package com.Jacksonnn.AirJump;

import com.projectkorra.projectkorra.ability.*;
import com.projectkorra.projectkorra.util.ParticleEffect;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ProjectKorra;

public class AirJump extends AirAbility implements AddonAbility {
	Vector vec;
	Location location;
	
	public AirJump(Player player) {
		super(player);
		
		if(!bPlayer.canBend(this)) {
			return;
		}
		
		setFields();
		start();
	}
	public void setFields() {
		vec = player.getLocation().getDirection().normalize().multiply(5);
		vec.setY(1.5);
		location = player.getLocation();
	}
	@Override
	public void progress() {
		if (player == null || !player.isOnline() || player.isDead()) {
			remove();
			return;
		}
		
		double height = 1;
		for (double radius = 0.75; radius < 1.75; radius += 0.25) {
		  for (double theta = 0; theta < 2*Math.PI; theta+=Math.PI/(4/Math.sqrt(radius))) {
		    double x = Math.cos(theta) * radius;
		    double z = Math.sin(theta) * radius;
		    Location pl = player.getLocation().clone().add(x, height, z);
		    ParticleEffect.CLOUD.display(pl, 0, 0, 0, 0.001f, 1);
		  }
		  height -= 0.25;
		}
		player.setVelocity(vec);
		AirAbility.playAirbendingParticles(location, 2, 0.5F, 0.5F, 0.5F);
		ParticleEffect.CLOUD.display(vec.multiply(-1), (float) 0.1, GeneralMethods.getRightSide(player.getLocation().clone().add(0, 1.48, 0), 0.3), 255);
		ParticleEffect.CLOUD.display(vec.multiply(-1), (float) 0.1, GeneralMethods.getLeftSide(player.getLocation().clone().add(0, 1.48, 0), 0.3), 255);
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
