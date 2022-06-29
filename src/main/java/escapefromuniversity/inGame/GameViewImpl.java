package escapefromuniversity.inGame;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import escapefromuniversity.controller.map.LayersControllerImpl;
import escapefromuniversity.launcher.LauncherView;
import escapefromuniversity.model.GameState;
import escapefromuniversity.model.basics.Point2D;
import escapefromuniversity.model.gameObject.GameObjectType;
import escapefromuniversity.model.gameObject.State;
import escapefromuniversity.model.gameObject.player.Player;
import escapefromuniversity.model.gameObject.player.PlayerImpl;
import escapefromuniversity.model.map.Camera;
import escapefromuniversity.model.map.MapProperties;
import escapefromuniversity.model.map.Rectangle;
import escapefromuniversity.model.map.TMXMapParser;
import escapefromuniversity.model.map.Tile;
import escapefromuniversity.model.map.TileDrawer;
import escapefromuniversity.model.map.TileDrawerImpl;
import escapefromuniversity.view.map.canvas.CanvasDrawer;
import escapefromuniversity.view.map.canvas.CanvasDrawerImpl;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 
 * class  of controller of game view.
 *
 */
public class GameViewImpl implements GameView {

    private static final long TIME_TO_END = 5000;
    private final GameController gameController;
    private final MapProperties map;
    private CanvasDrawer canvasDrawer;
    private TileDrawer tileDrawer;
    private final Camera camera;
    private double x = 30;
    private double y = 30;
    private static final double RADIUS = 10;
    private final Map<Integer, SpriteAnimation> spriteAnimations = new HashMap<>();
    private final LayersControllerImpl layersController;
    private final Player fakePlayer = new PlayerImpl(GameObjectType.PLAYER, new Point2D(x, y), 0, null, 0, null);

    @FXML
    private Canvas gameCanvas;

    /**
     * 
     * @param gameController
     * @param player
     */
    public GameViewImpl(final GameController gameController, final Player player) {
        this.gameController = gameController;
        this.camera = ratio -> {
            var playerHitBox = player.getObjectHitBox();
            var center = playerHitBox.getBottomLeftCorner().sum(playerHitBox.getUpperRightCorner()).multiplication(0.5);
            return new Rectangle(center.sum(new Point2D(-RADIUS, -RADIUS / ratio)), center.sum(new Point2D(RADIUS, RADIUS / ratio)));
        };
        final var parser = new TMXMapParser("final-map.tmx");
        this.map = parser.parse();
        this.layersController =  new LayersControllerImpl(map, player);
    }
    
//    public void loaderComponent(final GameController gameController, final Player player) {
//        this.gameController = gameController;
//        this.camera = ratio -> {
//            var playerHitBox = player.getObjectHitBox();
//            var center = playerHitBox.getBottomLeftCorner().sum(playerHitBox.getUpperRightCorner()).multiplication(0.5);
//            return new Rectangle(center.sum(new Point2D(-RADIUS, -RADIUS / ratio)), center.sum(new Point2D(RADIUS, RADIUS / ratio)));
//        };
//        final var parser = new TMXMapParser("final-map.tmx");
//        this.map = parser.parse();
//        this.layersController =  new LayersControllerImpl(map, player);
//    }

    @FXML
    protected void initialize() {
        this.canvasDrawer = new CanvasDrawerImpl(gameCanvas);
        this.tileDrawer = new TileDrawerImpl(map, this.canvasDrawer);
    }

    private Stream<Tile> getTilesToDraw(final Rectangle proj) throws ParserConfigurationException, IOException, SAXException {
        return this.layersController.getVisibleLayers().flatMap(l -> l.getVisibleTiles().stream())
                .filter(t -> t.getX() - proj.getMinX() > -1 && t.getX() - proj.getMaxX() < 1 &&
                        t.getY() - proj.getMinY() > -1 && t.getY() - proj.getMaxY() < 1);
    }

    private void drawLayers() throws ParserConfigurationException, IOException, SAXException {
        var proj = this.camera.calcMapProjection(this.canvasDrawer.getScreenRatio());
        this.canvasDrawer.clear();
        getTilesToDraw(proj).forEach(t -> {
            this.tileDrawer.drawTileByID(t.getValue(), this.calcProjectedRectangle(
                    new Rectangle(t.getPosition(), t.getPosition().sum(new Point2D(1, 1))), proj));
        });
        this.spriteAnimations.entrySet().stream().forEach(e -> {
            final SpriteAnimation animation = e.getValue();
            if (animation.isVisible()) {
                this.canvasDrawer.drawImage(animation.getSprite().getFilepath(), animation.getSprite().getRectangle(), animation.getPosition());
            }
        });
    }

    private Rectangle calcProjectedRectangle(final Rectangle rect, final Rectangle proj) {
        return new Rectangle(
                this.calcProjectedPosition(rect.getTopLeft(), proj),
                this.calcProjectedPosition(rect.getBottomRight(), proj));
    }

    private Point2D calcProjectedPosition(final Point2D pos, final Rectangle proj) {
        // Translate top-left point to center the projection on the canvas.
        var point = pos.subtract(proj.getTopLeft());
        // The size of projection rectangle can be different from the canvas size, so calculate the zoom factor.
        var projZoom = this.canvasDrawer.getWidth() / proj.getWidth();
        return point.multiplication(projZoom);
    }

    /**
     * {@inheritDoc}
     */
    public void updateView() {
        try {
            this.drawLayers();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeSpriteAnimation(final int id) {
        this.spriteAnimations.remove(id);
    }

    /**
     * {@inheritDoc}
     */
    public void updateSpriteAnimation(final int id, final Point2D position, final State state) {
        this.spriteAnimations.get(id).setPosition(position);
        this.spriteAnimations.get(id).getSprite();
    }

    /**
     * {@inheritDoc}
     */
    public void addSpriteAnimation(final int id, final State state, final GameObjectType type, final double height, final double width, final Point2D position) {
        final Sprite sprite = new SpriteImpl(state, type);
        final SpriteAnimation animation = new SpriteAnimation(sprite, height, width);
        animation.setPosition(position);
        this.spriteAnimations.put(id, animation);
    }

    /**
     * {@inheritDoc}
     */
    public boolean containThisID(final int id) {
        return this.spriteAnimations.containsKey(id);
    }

    /**
     * {@inheritDoc}
     */
    public void end(final GameState gameState) {
        if (gameState == GameState.WIN){
            //aggiorna con immagine vittoria
        } else if (gameState == GameState.LOST){
            //aggiorna con immagine sconfitta
        }
        try {
            Thread.sleep(TIME_TO_END);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LauncherView.createLauncher();
        //      System.exit(0);
    }

    //    TODO: !!
    //    private GameController gameController;
    //    private GameKeyListener gameKeyListener;
    //    private final JFrame window;
    //    private JPanel pause;
    //    private final GameHUDPanel gameHUD = new GameHUDPanel(WindowSet.getWindowRatio());
    //    private static final long DELAY_CLOSE = 5000;
    //
    //    public GameViewImpl(GameController gameController) {
    //        this.gameController = gameController;
    //        this.gameKeyListener = new GameKeyListener(this.gameController);
    //        this.window = new JFrame();
    //        this.window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    //        this.window.setUndecorated(true);
    //        this.window.setSize(screenWidth, screenHeight);
    //        this.window.setResizable(false);
    //        this.window.setTitle("Escape From University");
    //        String logo = OSFixes.getLocation("images", "logo.png"); // TODO change icon
    //        this.window.setIconImage(Toolkit.getDefaultToolkit().getImage(logo));
    //        this.window.setVisible(true);
    //        //	private final JPanel gamePanel;
    //
    //        this.window.addKeyListener(this.gameKeyListener);
    //
    //        this.pause = new JPanel();
    //
    //    }
    //
    //
    //    @Override
    //    public void addPauseBG() {
    //        Color color = new Color(0,0,0,205);
    //        this.pause.setBackground(color);
    //        this.window.add(this.pause);
    //    }
    //
    //    @Override
    //    public void removePauseBG() {
    //        this.window.remove(this.pause);
    //    }
}
