package gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * @author Trevor/extremesnow
 * @since 7/24/2020 at 9:35 PM
 */
public interface IGUI extends InventoryHolder {

    public String key();

    public void onGUIClick(Player whoClicked, int slot, ItemStack clickedItem, io.aquaticlabs.aquaticenchants.gui.GUI.ClickedInvType clickedInvType, ClickType clickType);


}
