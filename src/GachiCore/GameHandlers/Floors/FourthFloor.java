package GachiCore.GameHandlers.Floors;

import GachiCore.Builders.*;
import GachiCore.GameHandlers.Floor;
import GachiCore.GameHandlers.FloorEnemies;
import GachiCore.GameHandlers.Floors.Base.FloorBuilder;

import java.util.Random;

public class FourthFloor implements FloorBuilder {
    @Override
    public Floor getFloor() {
        FloorEnemies floorEnemies = new FloorEnemies();
        for (int index = 0; index < 4; index++){
            floorEnemies.addBot(getRandomBuilder( new Random(),
                    new PewManBuilder(), new MinotourBuilder(), new SpearManBuilder())
                    .build(getEnemy()));
        }
        return new Floor(floorEnemies, getRandomShop(new Random(2)));
    }
}
