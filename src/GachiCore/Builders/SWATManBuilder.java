package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.SWATMan;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Items.Consumables.Base.Consumable;
import GachiCore.Components.Items.Consumables.LargeHealPotion;
import GachiCore.Components.Items.Equipment.M4A4;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

import java.util.Random;

public class SWATManBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(11, 15, 4, 60, 2, 80, 2);
        Inventory inventory = new Inventory();
        SWATMan swatMan = new SWATMan(stats, inventory, true);
        M4A4 m4A4 = new M4A4();
        Consumable largeHealPotion  = new LargeHealPotion();
        largeHealPotion = getPotion(50, largeHealPotion);
        if (largeHealPotion != null){
            inventory.addItem(largeHealPotion);
        }
        m4A4.setOwner(swatMan);
        inventory.takeOn(m4A4);
        inventory.addMoney(randmoney());
        setName("SWATMan");
        swatMan.setName("SWATMan");
        swatMan.setMessenger(messenger);
        swatMan.setEnemy(enemy);
        return swatMan;
    }
    private int randmoney(){
        int lowest = 40;
        int highest = 80;
        Random random = new Random();
        int money = random.nextInt(highest - lowest) + lowest;
        return money;
    }
}
