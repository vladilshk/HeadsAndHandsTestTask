package ru.vovai.game.characters;

import ru.vovai.game.exceptions.InvalidAttackValueException;
import ru.vovai.game.exceptions.InvalidDefenseValueException;
import ru.vovai.game.exceptions.InvalidHealthException;
import ru.vovai.game.exceptions.InvalidMinOrMaxDamageException;

import java.util.Random;

public class Creature {
    private int attack;
    private int defense;
    private int health;
    private int minDamage;
    private int maxDamage;

    public Creature(int attack, int defense, int health, int minDamage, int maxDamage) throws Exception {
        setAttack(attack);
        setDefense(defense);
        setHealth(health);
        setMinAndMaxDamage(minDamage, maxDamage);
    }

    public void takeDamage(int damage) {
        if (damage > 0) {
            health -= damage;
            if (health < 0) {
                health = 0;
            }
        }
    }

    public int calculateDamage() {
        return new Random().nextInt(getMaxDamage() - getMinDamage() + 1) + getMinDamage();
    }

    public static int calculateModifier(int thirstCreatureAttack, int secondCreatureDefense){
        return thirstCreatureAttack - secondCreatureDefense + 1;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }


    private void setMinAndMaxDamage(int minDamage, int maxDamage) throws InvalidMinOrMaxDamageException {
        //TODO: add
        if (minDamage < 1){
            throw new InvalidMinOrMaxDamageException("Min damage should be more then 0!!!");
        } else if (maxDamage < 1){
            throw new InvalidMinOrMaxDamageException("Max damage should be more then 0!!!");
        } else if (maxDamage < minDamage){
            throw new InvalidMinOrMaxDamageException("Min damage shouldn't be more then Max damage!!!");
        }

        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    private void setAttack(int attack) throws InvalidAttackValueException {
        if (attack <= 0 || attack > 30){
            throw new InvalidAttackValueException("Wrong attack value. Attack can't be less then 1 or more then 30!!!");
        }
        this.attack = attack;
    }

    private void setDefense(int defense) throws InvalidDefenseValueException {
        if (defense <= 0 || defense > 30){
            throw new InvalidDefenseValueException("Wrong defense value. Defense can't be less then 1 or more then 30!!!");
        }
        this.defense = defense;
    }

    protected void setHealth(int health) throws InvalidHealthException {
        if (health < 1){
            throw new InvalidHealthException("Wrong health value. Health can't be less then 1!!!");
        }
        this.health = health;
    }
}
