package gui;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Trevor/extremesnow
 * @since 7/24/2020 at 11:29 PM
 */
public class GUI {


    private InventoryHolder owner;
    private String invName;
    private ItemStack air;
    private ItemStack fillItem;
    private int current_page;
    @Getter
    private List<GUIPage> pages = new ArrayList<>();
    private ItemStack nextPageItem;
    private ItemStack prevPageItem;
    private SlotCorner nextPageItemSlot = SlotCorner.BOTTOM_RIGHT;
    private SlotCorner prevPageItemSlot = SlotCorner.BOTTOM_LEFT;


    private GUI() {}

    public GUI(InventoryHolder owner, String invName) {
        this.owner = owner;
        this.invName = invName;
        air = new ItemStack(Material.AIR, 1);
    }

    public GUIPage createPage(String title, int rows) {
        GUIPage page = new GUIPage(owner, title, rows * 9);
        pages.add(page);
        return page;
    }


    public void createPages(String title, int rows, List<ItemStack> items) {
        if (rows == 1) rows++;
        int page_slots = (rows - 1) * 9;

        int total_needed_pages =  (items.size() / page_slots) + 1;
        //System.out.println("items: " + items.size() + " total needed: " + total_needed_pages);



        for (int i = 0; i < total_needed_pages; i++) {
            createPage(title, rows);
        }
        List<ItemStack> list = new ArrayList<>(items);

        int current_item = 0;
        for (GUIPage page : pages) {
            while (current_item < list.size()) {
                boolean b = page.addItemFromTo(list.get(current_item), 0, page.getInv().getSize() - 9, true);
                if (!b) break;
                current_item++;
            }
        }
    }

    public Inventory getPageInventory(GUIPage guiPage) {
        if (pages.size() == 0) {
            return guiPage.getInv();
        }

        if (nextPageItem != null) {
            guiPage.setItem(nextPageItem, getCornerSlotNumber(nextPageItemSlot, guiPage.getInv()));
        }
        if (prevPageItem != null) {
            guiPage.setItem(prevPageItem, getCornerSlotNumber(prevPageItemSlot, guiPage.getInv()));
        }

        return guiPage.getInv();
    }

    public GUIPage getPage(int page) {
        if (pages.size() == 0) {
            return getPage0();
        }
        GUIPage guiPage = pages.get(page);

        if (guiPage == null) {
            return getPage0();
        }

        if (nextPageItem != null) {
            guiPage.setItem(nextPageItem, getCornerSlotNumber(nextPageItemSlot, guiPage.getInv()));
        }
        if (prevPageItem != null) {
            guiPage.setItem(prevPageItem, getCornerSlotNumber(prevPageItemSlot, guiPage.getInv()));
        }

        return guiPage;
    }

    private GUIPage getPage0() {
        return pages.get(0);
    }

    public GUIPage getFirstPage() {
        return getPage(0);
    }

    public Inventory getNextPage() {
        int nextPage = current_page + 1;
        if (pages.size() > nextPage) {
            current_page++;
            return getPageInventory(pages.get(nextPage));
        } else return getPageInventory(pages.get(current_page));
    }

    public Inventory getPreviousPage() {
        int previousPage = current_page - 1;

        if (current_page > 0) {
            current_page--;
            return getPageInventory(pages.get(previousPage));
        } else return getPageInventory(pages.get(current_page));
    }


    public ItemStack getAir() {
        return air;
    }

    public ItemStack getFillItem() {
        return fillItem;
    }

    public void setNextPageItem(ItemStack nextPageItem) {
        this.nextPageItem = nextPageItem;
    }

    public ItemStack getNextPageItem() {
        return nextPageItem;
    }

    public void setPrevPageItem(ItemStack prevPageItem) {
        this.prevPageItem = prevPageItem;
    }

    public ItemStack getPrevPageItem() {
        return prevPageItem;
    }

    public void setNextPageItemSlot(SlotCorner nextPageItemSlot) {
        this.nextPageItemSlot = nextPageItemSlot;
    }

    public SlotCorner getNextPageItemSlot() {
        return nextPageItemSlot;
    }

    public void setPrevPageItemSlot(SlotCorner prevPageItemSlot) {
        this.prevPageItemSlot = prevPageItemSlot;
    }

    public SlotCorner getPrevPageItemSlot() {
        return prevPageItemSlot;
    }

    public int getCornerSlotNumber(SlotCorner slotCorner, Inventory inventory) {
        int size = inventory.getSize();
        if (slotCorner == SlotCorner.TOP_LEFT) return 0;
        if (slotCorner == SlotCorner.TOP_RIGHT) return 8;
        if (slotCorner == SlotCorner.BOTTOM_LEFT) return size - 9;
        if (slotCorner == SlotCorner.BOTTOM_RIGHT) return size - 1;
        return 0;
    }


    public enum SlotCorner {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT

    }

    public enum ClickedInvType {
        CHEST,
        PLAYER,
        OTHER
    }
}
