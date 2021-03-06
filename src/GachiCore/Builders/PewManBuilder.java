package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.PewMan;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Items.Consumables.Base.Consumable;
import GachiCore.Components.Items.Consumables.MediumHealPotion;
import GachiCore.Components.Items.Equipment.WaterPistol;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

import java.util.Random;

public class PewManBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(4, 8, 2, 22, 2, 80, 2);
        Inventory inventory = new Inventory();
        PewMan pewMan = new PewMan(stats, inventory, true);
        WaterPistol waterPistol = new WaterPistol();
        Consumable mediumHealPotion = new MediumHealPotion();
        mediumHealPotion = getPotion(50, mediumHealPotion);
        if (mediumHealPotion != null){
            inventory.addItem(mediumHealPotion);
        }
        setName("PewMan");
        pewMan.setName("PewMan");
        waterPistol.setOwner(pewMan);
        inventory.takeOn(waterPistol);
        inventory.addMoney(randmoney());
        pewMan.setMessenger(messenger);
        pewMan.setEnemy(enemy);
        return pewMan;
    }
    private int randmoney(){
        int lowest = 20;
        int highest = 40;
        Random random = new Random();
        int money = random.nextInt(highest - lowest) + lowest;
        return money;
    }
}
