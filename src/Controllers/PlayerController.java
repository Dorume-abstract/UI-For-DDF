package Controllers;

import GachiCore.Components.Items.Base.Item;
import GachiCore.Components.Items.Consumables.Base.Consumable;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Equipment.Base.EquipmentType;
import GachiCore.Entities.Base.Enemy;
import GachiCore.Entities.Base.GachiPowerUser;
import GachiCore.GameHandlers.FloorHandler;
import GachiCore.GameHandlers.GachiHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class PlayerController{
    private final GachiHandler gachiHandler = GachiHandler.getInstance();
    private final FloorHandler floorHandler = FloorHandler.getInstance();
    private final GachiPowerUser player = gachiHandler.getHero();
    private static final PlayerController instance = new PlayerController();
    private PlayerController(){}

    public static PlayerController getInstance() {
        return instance;
    }

    public int getFloorNumber(){
        return floorHandler.getNumberOfFloor();
    }

    public void attack(){
        player.attack();
    }

    public void nextTurn(){
        gachiHandler.nextTurn();
    }

    public boolean isClear(){
        return floorHandler.getCurrentFloor().isClear();
    }

    public ItemInformation[] getItems(){
        ArrayList<ItemInformation> itemInformationList = new ArrayList<>();
        Arrays.stream(getEquipment()).forEach((ItemInformation itemInfo) -> itemInformationList.add(itemInfo));
        Arrays.stream(getConsumables()).forEach((ItemInformation itemInfo) -> itemInformationList.add(itemInfo));
        return itemInformationList.toArray(new ItemInformation[itemInformationList.size()]);
    }

    public ItemInformation[] getEquipment(){
        ArrayList<ItemInformation> itemInformationList = new ArrayList<>();
        player.getInventory().getEquipments().forEach((Equipment equipment) -> itemInformationList.add(
                new ItemInformation(equipment.getName(), equipment.getDescription(), equipment.getPurchasePrice(),
                        equipment.getSellingPrice())));
        return itemInformationList.toArray(new ItemInformation[itemInformationList.size()]);
    }

    public ItemInformation[] getConsumables(){
        ArrayList<ItemInformation> itemInformationList = new ArrayList<>();
        player.getInventory().getConsumables().forEach((Consumable consumables) -> itemInformationList.add(
                (new ItemInformation(consumables.getName(), consumables.getDescription(), consumables.getPurchasePrice(),
                        consumables.getSellingPrice()))));
        return itemInformationList.toArray(new ItemInformation[itemInformationList.size()]);

    }

    public void useItem(ItemInformation itemInformation){
        Optional optional = player.getInventory().getConsumables().stream().
                filter((Consumable cons) -> compare(itemInformation, cons)).
                findFirst();

        if(optional.isPresent()){
            useConsumable((Consumable) optional.get());
            return;
        }

        optional = player.getInventory().getEquipments().stream().
                filter((Equipment equip) -> compare(itemInformation, equip)).
                findFirst();
        if(optional.isPresent()){
            changeEquip((Equipment) optional.get());
            return;
        }
    }

    public String[] getEnemies(){
        ArrayList<String> enemies = new ArrayList<>();
        Arrays.stream(floorHandler.getCurrentFloor().getEnemies()).forEach((Enemy enemy) -> enemies.add(enemy.getName()));
        return enemies.toArray(new String[enemies.size()]);
    }

    private void useConsumable(Consumable consumable){
        player.getInventory().use(consumable);
    }

    private void changeEquip(Equipment equipment){
        if(equipment.getEquipmentType() == EquipmentType.ARMOR){
            if(player.getInventory().getArmor() == equipment){
                player.getInventory().takeOff(equipment);
            }else {
                player.getInventory().takeOn(equipment);
            }
        }else {
            if(player.getInventory().getWeapon() == equipment){
                player.getInventory().takeOff(equipment);
            }else {
                player.getInventory().takeOn(equipment);
            }
        }
    }

    public boolean isAlive(){
        return player.isAlive();
    }

    private boolean compare(ItemInformation itemInfo, Item item) {
        return itemInfo.getName() == item.getName() &&
                itemInfo.getDescription() == item.getDescription() &&
                Integer.parseInt(itemInfo.getPurchasingPrice()) == item.getPurchasePrice() &&
                Integer.parseInt(itemInfo.getSellingPrice()) == item.getSellingPrice();
    }

    public void nextFloor(){
        gachiHandler.nextFloor();
    }

    public boolean isLastFloor(){
        return floorHandler.isLastFloor();
    }
}
