package main;
// Edge.java
public class Edge {
    private boolean claimed = false;
    private Player owner = null;

    public boolean isClaimed() {
        return claimed;
    }

    public void claim(Player player) {
        if (!claimed) {
            claimed = true;
            owner = player;
        }
    }

    public Player getOwner() {
        return owner;
    }

    public String getOwnerName() {
        return owner == null ? null : owner.getName();
    }

    @Override
    public String toString() {
        return claimed ? (owner == null ? "X" : owner.getName().substring(0, 1)) : " ";
    }
}