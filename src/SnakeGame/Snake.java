package SnakeGame;

import java.util.ArrayList;
import java.util.List;

public class Snake {
	
	public static final int START_XCOORD = 25;
	public static final int START_YCOORD = 25;
	
	public static final int SNAKE_SIZE = 10;
	
	public static final int INIT_PARTS = 10;
	
	private List<BodyPart> snakeBody;
	
	public Snake() {
		snakeBody = new ArrayList<>();
		BodyPart head = new BodyPart();
		head.setxCoord(START_XCOORD);
		head.setyCoord(START_YCOORD);
		snakeBody.add(head);
		for(int i = 1; i <= INIT_PARTS; i++) {
			BodyPart part = new BodyPart();
			part.setxCoord(START_XCOORD);
			part.setyCoord(START_YCOORD - i  * SNAKE_SIZE);
			snakeBody.add(part);
		}
	}

	public List<BodyPart> getSnakeBody() {
		return snakeBody;
	}

	public void setSnakeBody(List<BodyPart> snakeBody) {
		this.snakeBody = snakeBody;
	}
	
	public BodyPart getHead() {
		return snakeBody.iterator().next();
	}
	
	public void addBodyPart(BodyPart bodyPart) {
		BodyPart lastPart = snakeBody.get(snakeBody.size() - 1);
		bodyPart.setxCoord(lastPart.getxCoord());
		bodyPart.setyCoord(lastPart.getyCoord());
		snakeBody.add(bodyPart);
	}
	
}
