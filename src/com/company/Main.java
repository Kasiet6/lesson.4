
        package com.company;
import java.util.Random;

public class Main {

    public static int roundNumber = 1;

    //Boss
    public static int bossHealth = 1600;//Здоровье
    public static int bossDamage = 50;//урон
    public static String bossDefence = "";//защита рандомная

    //Heroes
    public static String[] heroesType = {"Weapon", "Magical", "Kinetik", "Medic"};//наши герои

    public static int[] heroesHealth = {270, 280, 240, 550};//270-0(Weapon); 280-1(Magical);240-2(Kinetick)

    public static int[] heroesDamage = {20, 25, 30, 0};//20-0(Weapon); 25-1(Magical);30-2(Kinetick)




    public static void Health () {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i == 3) {
                continue;
            }
            if (heroesHealth[i] > 0  && heroesHealth[i] < 100 && heroesHealth[3] > 0) {
                heroesHealth[i] += 100;
                System.out.println( "Medic Heal" + heroesType[i]);
                break;


            }
        }
    }








    public static void main(String[] args) {
        printStatistic();
        while (isGameFinished()) {
            round();
        }
    }

    public static void round() {
        roundNumber++; //номер раунда увеличиваем на 1
        chooseBossDefence();// для защиты босса
        bossHit();//удар босса
        Health();
        heroesHit();//удар героев
        printStatistic();//напечатать статистику
    }

    public static void printStatistic() { //Распечатывает статистику
        System.out.println("Round: " + roundNumber + " Statistic:");
        System.out.println("---------------------");
        System.out.println("Boss health: " +
                bossHealth + "; Boss damage: " + bossDamage);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesType[i] + " Health: "
                    + heroesHealth[i]);
            System.out.println(heroesType[i] + " Damage: " + heroesDamage[i]);
        }
        System.out.println("---------------------");
    }


    public static void chooseBossDefence() {//Защита для Босса
        Random random = new Random();
        int randomIndex = random.nextInt(heroesType.length);
        bossDefence = heroesType[randomIndex];
        System.out.println("Bosse have defence " + bossDefence);
    }


    public static void bossHit() {//удар Босса
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0)
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
        }
    }

    public static void heroesHit() {//Удар по Боссу героями
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(10);
                    if (bossHealth - heroesDamage[i] * coeff < 0) {

                        bossHealth = 0;

                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println(heroesType[i] + " Critical damage "
                            + heroesDamage[i] * coeff);
                } else {

                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }


    public static boolean isGameFinished() {//Эта логика возвращает нам
        // окончена ли наша игра и кто в нем победил
        if (bossHealth <= 0) {//проверка где выигрывают герои
            System.out.println("Heroes won!!!");
            return false;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {//проверка выйигрывает Босс
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return !allHeroesDead;
    }


}