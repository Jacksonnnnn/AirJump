package com.Jacksonnn.AirJump;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;

import com.projectkorra.projectkorra.BendingPlayer;

public class AirJumpListener implements Listener {
	
	@EventHandler
	public void onShift(PlayerAnimationEvent event) {
		Player player = event.getPlayer();
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		
		if (event.isCancelled() || player == null || bPlayer.getBoundAbility().equals(null)) {
			return;
		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("AirJump")) {
			new AirJump(player);
		}
	}

}
