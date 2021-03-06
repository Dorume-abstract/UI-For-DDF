package GachiCore.GameHandlers.Floors;

import GachiCore.AI.Slime;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.SlimeBuilder;
import GachiCore.Components.Items.Consumables.SmallHealPotion;
import GachiCore.Components.Items.Equipment.*;
import GachiCore.GameHandlers.Floor;
import GachiCore.GameHandlers.FloorEnemies;
import GachiCore.GameHandlers.Floors.Base.FloorBuilder;
import GachiCore.GameHandlers.Floors.Base.Shop;

public class FirstFloor implements FloorBuilder {

    @Override
    public Floor getFloor() {
        AIBuilder builder = new SlimeBuilder();
        FloorEnemies floorEnemies = new FloorEnemies(builder.build(getEnemy()),
                builder.build(getEnemy()), builder.build(getEnemy()));
        Shop shop = new Shop(new Stick(), new LeatherArmor(), new SmallHealPotion());
        return new Floor(floorEnemies, shop);
    }
}
