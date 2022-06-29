package escapefromuniversity.inGame;

import escapefromuniversity.model.GameState;
import escapefromuniversity.model.basics.Point2D;
import escapefromuniversity.model.gameObject.GameObjectType;
import escapefromuniversity.model.gameObject.State;

/**
 * 
 * interface of controller of game view.
 *
 */
public interface GameView {

    /**
     * 
     */
    void updateView();

    /**
     * 
     * @param id : id of object.
     */
    void removeSpriteAnimation(int id);

    /**
     * 
     * @param id : id of object.
     * @param position : new position.
     * @param state
     */
    void updateSpriteAnimation(int id, Point2D position, State state);

    /**
     * 
     * @param id : id of object.
     * @param state : state of object.
     * @param type : type of object.
     * @param height : height of object.
     * @param width : width of object.
     * @param position : position of object.
     */
    void addSpriteAnimation(int id, State state, GameObjectType type, double height, double width, Point2D position);

    /**
     * 
     * @param id : id of object.
     * @return if the id is present.
     */
    boolean containThisID(int id);

    /**
     * 
     * @param gameState : win or lost.
     */
    void end(GameState gameState);

//    /**
//     * 
//     * @param gameController
//     * @param player
//     */
//    void loaderComponent(GameController gameController, Player player);

}
