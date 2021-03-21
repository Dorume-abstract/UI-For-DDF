package GachiCore.Components.Items.Equipment;

import GachiCore.Components.Buffs.SpearBuff;
import GachiCore.Components.Buffs.StickBuff;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Equipment.Base.EquipmentType;
import GachiCore.Components.Items.Equipment.Base.Quality;

public class Spear extends Equipment {
    public Spear(){
        super("Spear", "Spear of justice", 80, 40, EquipmentType.WEAPON);
        setQuality(Quality.TWO);
        buffs.add(new SpearBuff());
    }
}
