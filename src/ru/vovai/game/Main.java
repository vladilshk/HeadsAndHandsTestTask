package ru.vovai.game;

import ru.vovai.game.characters.Creature;
import ru.vovai.game.characters.Monster;
import ru.vovai.game.characters.Player;
import ru.vovai.game.util.Dice;

public class Main {

    public static void main(String[] args) throws Exception {
        Player player = new Player(10, 6, 50, 1, 6);
        Monster monster = new Monster(8, 8, 40, 1, 10);
        Dice dice = new Dice();


        while (player.getHealth() > 0 && monster.getHealth() > 0) {
            if (Creature.calculateModifier(player.getAttack(), monster.getDefense()) <= 0 ||
                    Creature.calculateModifier(monster.getAttack(), player.getDefense()) <= 0){
                System.out.println("Игрок и монстр не смогут нанести урон друг другу(((");
                break;
            }


            // Игрок атакует монстра
            int playerModifier = Creature.calculateModifier(player.getAttack(), monster.getDefense());
            if (dice.rollAndCheckSuccess(playerModifier)) {
                int playerDamage = player.calculateDamage();
                monster.takeDamage(playerDamage);
                System.out.println("Игрок нанёс монстру " + playerDamage + " очков урона.");
            } else {
                System.out.println("Игрок не смог нанести урон монстру.");
            }


            if (monster.getHealth() > 0) {
                // Монстр атакует игрока
                int monsterModifier = Creature.calculateModifier(monster.getAttack(), player.getDefense());
                if (dice.rollAndCheckSuccess(monsterModifier)) {
                    int monsterDamage = monster.calculateDamage();
                    player.takeDamage(monsterDamage);
                    System.out.println("Монстр нанёс игроку " + monsterDamage + " очков урона.");
                } else {
                    System.out.println("Монстр не смог нанести урон игроку.");
                }
            }

            if (player.getHealth() > 0) {
                // Игрок исцеляется
                int amountOfPlayersHeal = player.heal();
                if (amountOfPlayersHeal != -1) {
                    System.out.println("Игрок исцеляется и восстанавливает " + amountOfPlayersHeal + " здоровья.");
                }
            }


            System.out.println("Здоровье игрока: " + player.getHealth() + " |||| " + "Здоровье монстра: " + monster.getHealth() + "\n");
        }

        if (player.getHealth() > 0 && monster.getHealth() > 0){
            System.out.println("Ничья");
        } else if (player.getHealth() <= 0) {
            System.out.println("Игрок проиграл!");
        } else {
            System.out.println("Игрок победил!");
        }
    }

}
