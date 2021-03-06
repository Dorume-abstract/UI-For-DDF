package GachiCore.GameHandlers.Floors;

import GachiCore.Builders.SkeletonBuilder;
import GachiCore.Builders.SlaveBuilder;
import GachiCore.Builders.SlimeBuilder;
import GachiCore.Components.Items.ItemDictionary;
import GachiCore.GameHandlers.Floor;
import GachiCore.GameHandlers.FloorEnemies;
import GachiCore.GameHandlers.Floors.Base.FloorBuilder;
import GachiCore.GameHandlers.Floors.Base.Shop;

import java.util.Random;

public class SecondFloor implements FloorBuilder {

    @Override
    public Floor getFloor() {
        FloorEnemies floorEnemies = new FloorEnemies();
        for (int index = 0; index < 4; index++){
            floorEnemies.addBot(getRandomBuilder( new Random(),
                    new SlaveBuilder(), new SkeletonBuilder(), new SlimeBuilder())
                    .build(getEnemy()));
        }
        return new Floor(floorEnemies, getRandomShop(new Random(3)));
    }


}
