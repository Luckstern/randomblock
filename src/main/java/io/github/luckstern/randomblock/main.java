package io.github.luckstern.randomblock;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class main extends JavaPlugin implements Listener {
    List<Material> blocks = new ArrayList<Material>();
    boolean enabled = true;
    String version = "v1.0";
    @Override
    public void onEnable() {
        getLogger().info("Randomblock "+version+" is ready!");
        this.getServer().getPluginManager().registerEvents(this, this);
        Plugin plugin = Bukkit.getPluginManager().getPlugin("randomblock");
        for (Material block : Material.values()) {
            if (block.isBlock()) {
                blocks.add(block);
            }
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Randomblock "+version+" has officially been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("randomblock")) {
            if (enabled) {
                if (args[0].equals("disable")) {
                    enabled = false;
                    sender.sendMessage("Randomblock has been disabled!");
                    return true;
                } else if (args[0].equalsIgnoreCase("enable")) {
                    sender.sendMessage("Randomblock is already enabled!");
                    return true;
                }
            }

            if (!enabled) {
                if (args[0].equals("disable")) {
                    sender.sendMessage("Randomblock is already disabled!");
                    return true;
                } else if (args[0].equalsIgnoreCase("enable")) {
                    enabled = true;
                    sender.sendMessage("Randomblock has been enabled!");
                    return true;
                }
            }
        }
        return false;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (enabled) {
            Material randomBlock = blocks.get(new Random().nextInt(blocks.size()));
            event.getBlock().setType(randomBlock);
        }
    }
}
