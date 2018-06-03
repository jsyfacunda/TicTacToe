import org.jetbrains.annotations.NotNull;

public class Player implements Comparable{
    //variables
    private String name;
    private int wins = 0;
    private int losses = 0;

    //constructor
    public Player(String name) {
        this.name = name;
    }

    //add wins or losses
    public void addWin() {wins++;}
    public void addLoss() {losses++;}

    //getters
    public String getName() {return name;}
    public int getWins() {return wins;}
    public int getLosses() {return losses;}

    //setters
    public void setName(String name) {this.name = name;}

    //resets scores and name
    public void reset() {
        setName("");
        wins = 0;
        losses = 0;
    }

    @Override
    public boolean equals(Object o) {
        if(this.getClass() == o.getClass() && o != null){
            Player other = (Player) o;
            return this.name.equals(other.name) && this.wins == other.wins && this.losses == other.losses;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashcode = 33;
        hashcode *= name.hashCode();
        hashcode *= wins;
        hashcode *= losses;
        return hashcode;
    }

    @Override
    public Player clone() throws CloneNotSupportedException {
        return (Player) super.clone();
    }

    @Override
    public int compareTo(Object o) {
        if(this.getClass() == o.getClass() && o != null){
            Player other = (Player) o;
            if(other.wins != wins)
                return (other.wins - wins);
            else
                return other.name.length() - name.length();
        }
        return -1;
    }
}