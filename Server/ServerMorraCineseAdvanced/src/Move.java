import java.io.Serializable;

public class Move implements Serializable {
    private static final long serialVersionUID = 1L;

    private int playerId;
    private int positionX;
    private int positionY;
    // Altri campi per descrivere la mossa

    public Move(int playerId, int positionX, int positionY) {
        this.playerId = playerId;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    // Metodi getter e setter per accedere ai campi della mossa
}