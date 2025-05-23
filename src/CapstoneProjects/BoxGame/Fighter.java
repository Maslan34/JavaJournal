package CapstoneProjects.BoxGame;

public class Fighter {
    String name;
    int damage;
    int health;
    int weight;
    double dodge;

    public Fighter(String name, int damage, int health, int weight, double dodge) {
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.weight = weight;
        this.dodge = dodge;
    }

    public int hit(Fighter opponent) {
        System.out.println("------------");
        System.out.println(this.name + " => " + opponent.name + " dealt " + this.damage + " damage.");

        if (opponent.dodge()) {
            System.out.println(opponent.name + " dodged the incoming damage.");
            return opponent.health;
        }

        if (opponent.health - this.damage < 0)
            return 0;

        return opponent.health - this.damage;
    }

    public boolean dodge() {
        double randomValue = Math.random() * 100;
        return randomValue <= this.dodge;
    }
}
