package ru.vovai.game.characters;

import ru.vovai.game.exceptions.InvalidHealthException;

public class Player extends Creature {
    private final int maxHealth;
    private int healCount;

    public Player(int attack, int defense, int health, int minDamage, int maxDamage) throws Exception {
        super(attack, defense, health, minDamage, maxDamage);
        this.maxHealth = health;
        this.healCount = 4;
    }

    public int heal() throws InvalidHealthException {
        if (healCount > 0) {
            int healAmount = (int) (maxHealth * 0.3); // Исцеление на 30% от максимального здоровья
            setHealth(getHealth() + healAmount);
            if (getHealth() > maxHealth) {
                setHealth(maxHealth);
            }
            healCount--;
            return healAmount;
        }
        return -1;
    }
}
