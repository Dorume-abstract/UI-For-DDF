package GachiCore.Components.Items;

import GachiCore.Components.Items.Base.Item;
import GachiCore.Components.Items.Consumables.LargeHealPotion;
import GachiCore.Components.Items.Consumables.MediumHealPotion;
import GachiCore.Components.Items.Consumables.SmallHealPotion;
import GachiCore.Components.Items.Equipment.*;


import java.util.HashMap;
import java.util.Map;

public class ItemDictionary {
    private static final ItemDictionary instance = new ItemDictionary();
    private ItemDictionary(){}

    public static ItemDictionary getInstance() {
        return instance;
    }

    private final Map<String, Item> items = new HashMap<>();

    {
        /*
           items.put("NAME_OF_ITEM, new Item());
         */

        //region Equipment
        items.put("Axe", new Axe());
        items.put("BloodyKnife", new BloodyKnife());
        items.put("Bow", new Bow());
        items.put("M4A4", new M4A4());
        items.put("Spear", new Spear());
        items.put("Stick", new Stick());
        items.put("WaterPistol", new WaterPistol());
        items.put("LatexCostume", new LatexCostume());
        items.put("LeatherArmor", new LeatherArmor());

        //endregion
        //region Consumable
        items.put("SmallHealPotion", new SmallHealPotion());
        items.put("MediumHealPotion", new MediumHealPotion());
        items.put("LargeHealPotion", new LargeHealPotion());
        //endregion

    }

    public Item[] getItems(){
        return items.values().toArray(new Item[items.size()]);
    }

    public Item getItem(String name){
        return items.getOrDefault(name, null);
    }

}
