package net.gamesketch.bukkit.growingore.methods;

import java.util.ArrayList;
import java.util.List;

import net.gamesketch.bukkit.growingore.core;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class PlayerData {
	Player player;
	List<SelectedOre> selectedOres;
	
	public PlayerData(Player p) {
		this.player = p;
		this.selectedOres = new ArrayList<SelectedOre>();
	}
	
	
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean isBlockSelected(Block b) {
		for (SelectedOre sel : selectedOres) {
			if (!sel.getBlock().getWorld().getName().equals(b.getWorld().getName())) { continue; }
			if (sel.getBlock().getX() != b.getX()) { continue; }
			if (sel.getBlock().getY() != b.getY()) { continue; }
			if (sel.getBlock().getZ() != b.getZ()) { continue; }
			return true;
		}
		return false;
	}
	
	public void addSelectedBlock(Block b) {
		selectedOres.add(new SelectedOre(this.player, b));
	}
	public void removeSelectedBlock(SelectedOre s) {
		if (selectedOres.contains(s)) { selectedOres.remove(s); }
	}
	public List<SelectedOre> getSelectedBlocks() {
		return selectedOres;
	}
	
	public void remove() {
		for (SelectedOre ore : selectedOres) {
			ore.restore();
			ore = null;
		}
		selectedOres.clear();
		core.PLAYERDATA.remove(this);
	}
	
}
