package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.SpearMan;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Items.Consumables.Base.Consumable;
import GachiCore.Components.Items.Consumables.MediumHealPotion;
import GachiCore.Components.Items.Equipment.Spear;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

import java.util.Random;

public class SpearManBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(8, 10, 2,
                30, 2, 80, 2);
        Inventory inventory = new Inventory();
        SpearMan spearMan = new SpearMan(stats, inventory, false);
        Spear spear = new Spear();
        Consumable mediumHealPotion = new MediumHealPotion();
        mediumHealPotion = getPotion(50, mediumHealPotion);
        if (mediumHealPotion != null){
            inventory.addItem(mediumHealPotion);
        }
        spear.setOwner(spearMan);
        inventory.takeOn(spear);
        inventory.addMoney(randmoney());
        setName("SpearMan");
        spearMan.setName("SpearMan");
        spearMan.setMessenger(messenger);
        spearMan.setEnemy(enemy);
        return spearMan;
    }
    private int randmoney(){
        int lowest = 20;
        int highest = 40;
        Random random = new Random();
        int money = random.nextInt(highest - lowest) + lowest;
        return money;
    }
}
