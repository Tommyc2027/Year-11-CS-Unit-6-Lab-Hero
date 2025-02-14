import java.util.Random;

class Hero {
    private String name;
    private int hitPoints;
    private static final int HP = 100;
    private static final int DAMAGE = 10;
    private static final Random rand = new Random();

    public Hero(String name) {
        this.name = name;
        this.hitPoints = HP;
    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public String toString() {
        return "Hero{name='" + name + "', hitPoints=" + hitPoints + "}";
    }

    public void attack(Hero opponent) {
        if (rand.nextDouble() < 0.5) {
            opponent.hitPoints -= DAMAGE;
        } else {
            this.hitPoints -= DAMAGE;
        }
    }

    public void senzuBean() {
        this.hitPoints = HP;
    }

    private void fightUntilTheDeathHelper(Hero opponent) {
        while (this.hitPoints > 0 && opponent.hitPoints > 0) {
            this.attack(opponent);
        }
    }

    public String fightUntilTheDeath(Hero opponent) {
        this.senzuBean();
        opponent.senzuBean();
        fightUntilTheDeathHelper(opponent);
        return this.name + ": " + this.hitPoints + "\t" + opponent.name + ": " + opponent.hitPoints;
    }

    private int[] nFightsToTheDeathHelper(Hero opponent, int n) {
        int[] wins = new int[2];
        for (int i = 0; i < n; i++) {
            fightUntilTheDeathHelper(opponent);
            if (this.hitPoints > 0) {
                wins[0]++;
            } else {
                wins[1]++;
            }
            this.senzuBean();
            opponent.senzuBean();
        }
        return wins;
    }

    public String nFightsToTheDeath(Hero opponent, int n) {
        int[] results = nFightsToTheDeathHelper(opponent, n);
        String result = this.name + ": " + results[0] + " wins\n" + opponent.name + ": " + results[1] + " wins\n";
        if (results[0] > results[1]) {
            result += this.name + " wins";
        } else if (results[1] > results[0]) {
            result += opponent.name + " wins";
        } else {
            result += "draw";
        }
        return result;
    }

    public void dramaticFightToTheDeath(Hero opponent) throws InterruptedException {
        this.senzuBean();
        opponent.senzuBean();
        while (this.hitPoints > 0 && opponent.hitPoints > 0) {
            this.attack(opponent);
            System.out.println(this.name + ": " + this.hitPoints + "\t" + opponent.name + ": " + opponent.hitPoints);
            Thread.sleep(1000);
        }
    }
}

public class HeroBattle {
    public static void main(String[] args) throws InterruptedException {
        Hero hero1 = new Hero("Andrew Tate");
        Hero hero2 = new Hero("Taylor Swift");

        System.out.println(hero1.fightUntilTheDeath(hero2));
        System.out.println(hero1.nFightsToTheDeath(hero2, 5));
        hero1.dramaticFightToTheDeath(hero2);
    }
}
