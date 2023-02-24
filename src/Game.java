import java.util.Random;
import java.util.Scanner;

/**
 * @author Trevor Hartman
 *
 * @author crazyne55
 *
 * 2/24/2023 @ 1204
 */
public class Game {
    Player p1;
    Player p2;
    Dice die;

    public void play() {
        Player current = this.p1;
        takeTurn(current);
        current = nextPlayer(current);
        takeTurn(current);
        System.out.println(announceWinner());
    }

    public Player nextPlayer(Player current) {
        if (current == this.p1) return this.p2;
        else if (current == this.p2) return this.p1;
        return null;
    }

    public void takeTurn(Player player) {
        player.toss(this.die);
    }

    public String announceWinner() {
        String ret = "";
        if(this.p1.getScore() > this.p2.getScore()) {
            ret += "(WINNER) " + this.p1.getName() + " (" + this.p1.getScore() + ")\n";
            ret += "         " + this.p2.getName() + " (" + this.p2.getScore() + ")";
        }else if(this.p2.getScore() > this.p1.getScore()) {
            ret += "(WINNER) " + this.p2.getName() + " (" + this.p2.getScore() + ")\n";
            ret += "         " + this.p1.getName() + " (" + this.p1.getScore() + ")";
        } else {
            ret += "(TIE) " + this.p1.getName() + " (" + this.p1.getScore() + ")\n";
            ret += "(TIE) " + this.p2.getName() + " (" + this.p2.getScore() + ")";
        }
        return ret;
    }

    public Game(Player p1, Player p2, Dice die) {
        this.p1 = p1;
        this.p2 = p2;
        this.die = die;
    }

    public static void main(String[] args) {
        System.out.println(args.length);
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Player 1 Name: ");
        Player p1 = new Player(scan.nextLine());
        System.out.print("Enter Player 2 Name: ");
        Player p2 = new Player(scan.nextLine());
        System.out.print("Enter Sides for die: ");
        Dice die = new Dice(Integer.valueOf(scan.nextLine()));
        Game game = new Game(p1,p2,die);
        game.play();
    }
}

class Dice {
    private int sideFacingUp;
    private int sides;
    private Random randomGenerator;

    public Dice(int sides) {
        this.sideFacingUp = 1;
        this.sides = sides;
        this.randomGenerator = new Random();
    }

    void roll() {
        sideFacingUp = randomGenerator.nextInt(1,sides);
    }
    int view() {
        return sideFacingUp;
    }
}

class Player {
    private String name;
    private int score;

    String getName() {
        return name;
    }
    int getScore() {
        return score;
    }
    void toss(Dice die) {
        die.roll();
        score += die.view();
    }
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }
}