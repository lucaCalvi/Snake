package SnakeGame;

import java.util.Random;

public class Food {

	private int xCoord;
	private int yCoord;
	
	public static final int FOOD_SIZE = 10;
	
	public Food(Snake snake) {
		boolean isSnakePart = false;
		do {
			Random rand = new Random();
			
			xCoord = rand.nextInt(GamePanel.WIDTH - Food.FOOD_SIZE * 5) + Food.FOOD_SIZE * 5;
			yCoord = rand.nextInt(GamePanel.HEIGHT - GamePanel.HEIGHT_INFO_PANEL - Food.FOOD_SIZE * 5) + Food.FOOD_SIZE * 5;
			
			for(BodyPart bodyPart : snake.getSnakeBody()) {
				if(bodyPart.getxCoord() > (xCoord - Food.FOOD_SIZE) && bodyPart.getxCoord() < (xCoord + Food.FOOD_SIZE) && 
				   bodyPart.getyCoord() > (yCoord - Food.FOOD_SIZE) && bodyPart.getyCoord() < (yCoord + Food.FOOD_SIZE)) {
					isSnakePart = true;
				}
			}
		} while(isSnakePart);
	}
	
	public int getxCoord() {
		return xCoord;
	}
	
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	
	public int getyCoord() {
		return yCoord;
	}
	
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
}
