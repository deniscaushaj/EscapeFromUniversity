package escapefromuniversity.model.map;

import escapefromuniversity.model.GameModel;
import escapefromuniversity.model.gameObject.enemy.Boss;
import escapefromuniversity.model.gameObject.player.Player;

import java.util.List;
import java.util.Map;

public class MapManagerImpl implements MapManager {
    private final Map<Door,Door> doors;
    private final GameModel gameModel;
    private Room currentRoom;

    public MapManagerImpl(GameModel gameModel) {
        this.gameModel = gameModel;
        List<Room> roomList = this.createRooms();
        this.doors = this.createDoors();

    }

    @Override
    public void update(double deltaTime) {
        this.currentRoom.update(deltaTime);
    }

    @Override
    public Room getRoom() {
        return this.currentRoom;
    }

    @Override
    public Player getPlayer() {
        return this.currentRoom.getPlayer();
    }

    private List<Room> createRooms() {
        return null;

    }

    private Map<Door, Door> createDoors(){
        return null;
    }

    @Override
    public void goQuiz(Boss boss) {
        this.gameModel.goQuiz(boss);
    }

    @Override
    public void setCurrentRoom(Door door) {
        this.currentRoom.getPlayer().setRoom(door.getRoom());
        this.currentRoom = doors.get(door).getRoom();
        this.currentRoom.getPlayer().setPosition(doors.get(door).getPos());
    }

    @Override
    public void goShop() {
        this.gameModel.goShop();
    }

}
