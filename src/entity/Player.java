package src.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import src.main.GamePanel;
import src.main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;

	public final int screenX;
	public final int screenY;

	public Player(GamePanel gp, KeyHandler keyH) {

	this.gp = gp;
	this.keyH = keyH;

	screenX = gp.screenWidth/2 - (gp.tileSize/2);
	screenY = gp.screenHeight/2 - (gp.tileSize/2);

	solidArea = new Rectangle();
	solidArea.x = 8;
	solidArea.y = 16;
	solidArea.width = 32;
	solidArea.height = 32;

	setDefaultValues();
	getPlayerImage();
	}

	//Player Position
	public void setDefaultValues() {

	worldX = gp.tileSize * 26;
	worldY = gp.tileSize * 25;
	speed = 4;
	direction = "down"; //Player Facing

	}

	//Player Images
	public void getPlayerImage() {

	try {

	up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/orangecat_up1.png"));
	up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/orangecat_up2.png"));
	down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/orangecat_down1.png"));
	down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/orangecat_down2.png"));
	left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/orangecat_left1.png"));
	left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/orangecat_left2.png"));
	right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/orangecat_right1.png"));
	right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/orangecat_right2.png"));

	} catch (IOException e) {
	e.printStackTrace();
	}
	}

	//Called 60 times per second
	public void update() {

	if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

	if(keyH.upPressed == true) {
	direction = "up";
	}
	else if(keyH.downPressed == true) {
	direction = "down";
	}
	else if(keyH.leftPressed == true) {
	direction = "left";
	}
	else if(keyH.rightPressed == true) {
	direction = "right";
	}

	//Player Collision
	collisionOn = false;
	gp.cChecker.checkTile(this);

	//If oollision is false, player moves
	if (collisionOn == false) {

	switch (direction) {
	case "up": worldY -= speed;
	break;

	case"down": worldY += speed;
	break;

	case"left": worldX -= speed;
	break;

	case "right": worldX += speed;
	break;

	}
	}

	//Player Sprite Movement
	spriteCounter++;
	if(spriteCounter > 12) {
	if(spriteNumber == 1) {
	spriteNumber = 2;
	}
	else if(spriteNumber == 2) {
	spriteNumber = 1;
	}
	spriteCounter = 0;
	}
	}
	}

	//Player Renderer
	public void draw(Graphics2D g2) {

	BufferedImage image = null;

	switch (direction) {
	case "up" -> {
	if(spriteNumber == 1) {
	image = up1;
	}
	if(spriteNumber == 2) {
	image = up2;
	}
	}
	case "down" -> {
	if(spriteNumber == 1) {
	image = down1;
	}
	if(spriteNumber == 2) {
	image = down2;
	}
	}
	case "left" -> {
	if(spriteNumber == 1) {
	image = left1;
	}
	if(spriteNumber == 2) {
	image = left2;
	}
	}
	case "right" -> {
	if(spriteNumber == 1) {
	image = right1;
	}
	if(spriteNumber == 2) {
	image = right2;
	}
	}
	}
	g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

	}
}
