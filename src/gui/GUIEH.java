package gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

/**
 * @author Trevor/extremesnow
 * @since 7/24/2020 at 9:36 PM
 */
public class GUIEH implements Listener {

    private Plugin plugin;

    public GUIEH(Plugin plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof IGUI) {
            e.setCancelled(true);
            IGUI gui = (IGUI) e.getInventory().getHolder();
            if (e.getClickedInventory() == gui.getInventory()) {
                gui.onGUIClick((Player) e.getWhoClicked(), e.getRawSlot(), e.getCurrentItem(), GUI.ClickedInvType.CHEST, e.getClick());
                return;
            }
            if (e.getClickedInventory() == e.getWhoClicked().getInventory()) {
                gui.onGUIClick((Player) e.getWhoClicked(), e.getRawSlot(), e.getCurrentItem(), GUI.ClickedInvType.PLAYER, e.getClick());
                return;
            }
            gui.onGUIClick((Player) e.getWhoClicked(), e.getRawSlot(), e.getCurrentItem(), GUI.ClickedInvType.OTHER, e.getClick());
        }
    }
}