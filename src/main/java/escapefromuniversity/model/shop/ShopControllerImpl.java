package escapefromuniversity.model.shop;

import escapefromuniversity.model.gameObject.player.Player;
import escapefromuniversity.model.quiz.Competition;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShopControllerImpl implements ShopController {
import static escapefromuniversity.model.shop.ShopViewImpl.*;

public class ShopControllerImpl implements ShopController, MouseListener {

    private final ShopView shopView;
    private Items itemType;
    private Player player; // TODO initialize
    private Competition competition; // TODO same

    public ShopControllerImpl() {
        this.shopView = new ShopViewImpl(this);
        this.initializeButtons();
    }

    private void initializeButtons() {
        this.shopView.getExit().addMouseListener(this);
        this.shopView.getBuyLife().addMouseListener(this);
        this.shopView.getBuyArmor().addMouseListener(this);
        this.shopView.getBuyDamage().addMouseListener(this);
        this.shopView.getBuyDamage().addMouseListener(this);
    }

    @Override
    public void closeShop() {

    }

    @Override
    public void buyItem() {
        switch(this.getItemType()) {
            case RESET_HEALTH:
                this.itemType.resetHealth(this.player);
                break;
            case INCREASE_ARMOR:
                this.itemType.increaseArmor(this.player);
                break;
            case INCREASE_DAMAGE:
                this.itemType.increaseDamage(this.player);
                break;
            case DOUBLE_CHANCE:
                this.itemType.doubleChance(this.competition);
                break;
            default: {
            }
        }
    }

    @Override
    public void setItemType(Items itemType) {
        this.itemType = itemType;
    }

    @Override
    public Items getItemType() {
        return this.itemType;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (this.shopView.getExit().equals(source)) {
            this.closeShop();
        } else if (this.shopView.getBuyLife().equals(source)) {
            this.setItemType(Items.RESET_HEALTH);
        } else if (this.shopView.getBuyArmor().equals(source)) {
            this.setItemType(Items.INCREASE_ARMOR);
        } else if (this.shopView.getBuyDamage().equals(source)) {
            this.setItemType(Items.INCREASE_DAMAGE);
        } else if (this.shopView.getBuyChance().equals(source)) {
            this.setItemType(Items.DOUBLE_CHANCE);
        }
        this.buyItem();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getSource();
        if (this.shopView.getBuyLife().equals(source)) {
            this.shopView.setItemInfo(buyLifeInfo);
        } else if (this.shopView.getBuyArmor().equals(source)) {
            this.shopView.setItemInfo(buyArmorInfo);
        } else if (this.shopView.getBuyDamage().equals(source)) {
            this.shopView.setItemInfo(buyDamageInfo);
        } else if (this.shopView.getBuyChance().equals(source)) {
            this.shopView.setItemInfo(buyChanceInfo);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.shopView.setItemInfo("");
    }
}
