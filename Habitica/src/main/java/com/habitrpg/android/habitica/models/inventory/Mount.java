package com.habitrpg.android.habitica.models.inventory;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Mount extends RealmObject implements Animal {

    Boolean owned;
    @PrimaryKey
    String key;
    String animal, color, animalGroup, animalText, colorText;
    Boolean premium, limited;
    Integer numberOwned;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAnimalGroup() {
        return animalGroup;
    }

    public void setAnimalGroup(String group) {
        this.animalGroup = group;
    }

    public String getAnimalText() {
        if (animalText == null) {
            return animal;
        }
        return animalText;
    }

    public void setAnimalText(String animalText) {
        this.animalText = animalText;
    }

    public String getColorText() {
        if (colorText == null) {
            return color;
        }
        return colorText;
    }

    public void setColorText(String colorText) {
        this.colorText = colorText;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public Boolean getLimited() {
        return limited;
    }

    public void setLimited(Boolean limited) {
        this.limited = limited;
    }

    public Integer getNumberOwned() {
        if (numberOwned == null) {
            return 0;
        }
        return numberOwned;
    }

    public void setNumberOwned(Integer numberOwned) {
        this.numberOwned = numberOwned;
    }

    public Boolean getOwned() {
        return owned;
    }

    public void setOwned(Boolean owned) {
        this.owned = owned;
    }
}
