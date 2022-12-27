package gui;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIPage {

    @Getter
    private Inventory inv;
    private final ItemStack air = new ItemStack(Material.AIR, 1);
    @Getter@Setter
    private ItemStack fillItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    private boolean flexible = false;

    public GUIPage(InventoryHolder owner, String title, int rows) {
        if (rows == 0) flexible = true;
        inv = Bukkit.createInventory(owner, rows, title);
        ItemMeta m = fillItem.getItemMeta();
        m.setDisplayName("");
        fillItem.setItemMeta(m);

    }

    public void setItem(ItemStack item, Integer slot) {
        if (inv.getSize() >= slot) {
            inv.setItem(slot, item);
        }
    }
    public void addItem(ItemStack item) {
        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == air || inv.getItem(i) == null) {
                inv.setItem(i, item);
                break;
            }
        }
    }
    public void addItemFromTo(ItemStack item, int startSlot, int endSlot) {
        if (inv.getSize() < endSlot) return;
        for (int i = startSlot; i < endSlot; i++) {
            if (inv.getItem(i) == air || inv.getItem(i) == null) {
                inv.setItem(i, item);
                break;
            }
        }
    }
    public boolean addItemFromTo(ItemStack item, int startSlot, int endSlot, boolean b) {
        if (inv.getSize() < endSlot) return false;
        for (int i = startSlot; i < endSlot; i++) {

            if (inv.getItem(i) == air || inv.getItem(i) == null) {
                inv.setItem(i, item);
                return true;
            }
        }
        return false;
    }

    public void removeItem(Integer slot) {
        if (inv.getSize() >= slot) {
            inv.setItem(slot, air);
        }
    }
    public void removeAllItems() {
        for (int i = 0 ; i < inv.getSize() ; i++) {
            inv.setItem(i, air);
        }
    }
    public void fillEmpty() {
        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == air || inv.getItem(i) == null) {
                inv.setItem(i, fillItem);
            }
        }
    }

}
