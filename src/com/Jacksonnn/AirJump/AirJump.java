package com.Jacksonnn.AirJump;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.AirAbility;
import com.projectkorra.projectkorra.configuration.ConfigManager;
import com.projectkorra.projectkorra.util.ParticleEffect;

public class AirJump extends AirAbility implements AddonAbility {
	Vector vec;
	Location location;
	private int distance = ConfigManager.getConfig().getInt("ExtraAbilities.Jacksonnn.AirJump.Distance");
	private int height = ConfigManager.getConfig().getInt("ExtraAbilities.Jacksonnn.AirJump.Height");
	private long cooldown = ConfigManager.getConfig().getLong("ExtraAbilities.Jacksonnn.AirJump.Cooldown");
	
	public AirJump(Player player) {
		super(player);
		
		if(!bPlayer.canBend(this)) {
			return;
		}
		
		setFields();
		start();
	}
	public void setFields() {
		vec = player.getLocation().getDirection().normalize().multiply(distance);
		vec.setY(height);
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
		return cooldown;
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
		
		ConfigManager.languageConfig.get().addDefault("ExtraAbilities.Jacksonnn.AirJump.Description", "Often times in an airbender's nomadic life they'll need to propell themselves up and out to get away from fighting. This is what the ability does.");
		ConfigManager.languageConfig.get().addDefault("ExtraAbilities.Jacksonnn.AirJump.Instructions", "Shift: Jump Up and Out.");
		ConfigManager.getConfig().addDefault("ExtraAbilities.Jacksonnn.AirJump.Distance", 5);
		ConfigManager.getConfig().addDefault("ExtraAbilities.Jacksonnn.AirJump.Height", 1.5);
		ConfigManager.getConfig().addDefault("ExtraAbilities.Jacksonnn.AirJump.Cooldown", 10000);
		ConfigManager.defaultConfig.save();
		ConfigManager.languageConfig.save();
		
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
		return "BETA 1.1.3";
	}

	@Override
	public String getDescription() {
		return "Author: " + getAuthor() + "\nAirJump\n" + ConfigManager.languageConfig.get().getString("ExtraAbilities.Jacksonnn.AirJump.Description");
	}
	
	public String getInstructions() {
		return ConfigManager.languageConfig.get().getString("ExtraAbilities.Jacksonnn.AirJump.Instructions");
	}

}
