package net.baydan.whsmgc.api;

public class ManaSystemAttributes {
    public static int calculateMaxMana(int manaLevel){
        int maxMana = 100;
        // Increases mana by 50% each time
        for(int i = 0; i < manaLevel - 1; i++) {
            maxMana = maxMana + (int)(maxMana * 0.5);
        }
        return maxMana;

    }

}
