package com.Jacksonnn.AirJump;

import com.projectkorra.projectkorra.ability.*;
import org.bukkit.event.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.permissions.*;
import org.bukkit.event.player.*;
import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.ProjectKorra;
import java.util.ArrayList;
import com.projectkorra.projectkorra.ability.util.ComboManager.AbilityInformation;
import com.projectkorra.projectkorra.ability.util.MultiAbilityManager.MultiAbilityInfoSub;

public class AirJump extends AirAbility implements AddonAbility, Listener {

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

	private static Permission perm;

	@Override
	public void load() {
		perm = new Permission("bending.ability.AirJump");
		perm.setDefault(PermissionDefault.TRUE);
		ProjectKorra.plugin.getServer().getPluginManager().addPermission(perm);
	}

	@Override
	public void stop() {
		ProjectKorra.plugin.getServer().getPluginManager().removePermission(perm);
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

	@EventHandler
	public void activate(PlayerToggleSneakEvent event) {
		Player player = event.getPlayer();
		if (player.isSneaking()) return;
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		if (bPlayer == null) return;
		if (!bPlayer.canBend(CoreAbility.getAbility(AirJump.class))) return;
		new AirJump(player);
	}

}
