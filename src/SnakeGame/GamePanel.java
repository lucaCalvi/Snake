package SnakeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener, Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 750;
	public static final int HEIGHT = 750;
	
	public static final int LOOP = 40;
	
	private Thread thread;
	
	private boolean running;
	
	private boolean right;
	private boolean left;
	private boolean up;
	private boolean down;
	
	private Snake snake;
	
	private Food food;
	
	public GamePanel() {
		setFocusable(true);
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.BLACK);
	}
	
	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public Snake getSnake() {
		return snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	public void start() {
		running = true;
		down = true;
		snake = new Snake();
		food = new Food(snake);
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void move() {
		isBorderCollision();
		
		if(up) {
			Iterator<BodyPart> iterator = snake.getSnakeBody().iterator();
			BodyPart head = iterator.next();
			int oldXCoord = head.getxCoord();
			int oldYCoord = head.getyCoord();
			head.setyCoord(head.getyCoord() - 1 * Snake.SNAKE_SIZE);
			while(iterator.hasNext()) {
				BodyPart bodyPart = iterator.next();
				int newXCoord = oldXCoord;
				int newYCoord = oldYCoord;
				oldXCoord = bodyPart.getxCoord();
				oldYCoord = bodyPart.getyCoord();
				bodyPart.setxCoord(newXCoord);
				bodyPart.setyCoord(newYCoord);
			}
		}
		if(right) {
			Iterator<BodyPart> iterator = snake.getSnakeBody().iterator();
			BodyPart head = iterator.next();
			int oldXCoord = head.getxCoord();
			int oldYCoord = head.getyCoord();
			head.setxCoord(head.getxCoord() + 1 * Snake.SNAKE_SIZE);
			while(iterator.hasNext()) {
				BodyPart bodyPart = iterator.next();
				int newXCoord = oldXCoord;
				int newYCoord = oldYCoord;
				oldXCoord = bodyPart.getxCoord();
				oldYCoord = bodyPart.getyCoord();
				bodyPart.setxCoord(newXCoord);
				bodyPart.setyCoord(newYCoord);
			}
		}
		if(down) {
			Iterator<BodyPart> iterator = snake.getSnakeBody().iterator();
			BodyPart head = iterator.next();
			int oldXCoord = head.getxCoord();
			int oldYCoord = head.getyCoord();
			head.setyCoord(head.getyCoord() + 1 * Snake.SNAKE_SIZE);
			while(iterator.hasNext()) {
				BodyPart bodyPart = iterator.next();
				int newXCoord = oldXCoord;
				int newYCoord = oldYCoord;
				oldXCoord = bodyPart.getxCoord();
				oldYCoord = bodyPart.getyCoord();
				bodyPart.setxCoord(newXCoord);
				bodyPart.setyCoord(newYCoord);
			}
		}
		if(left) {
			Iterator<BodyPart> iterator = snake.getSnakeBody().iterator();
			BodyPart head = iterator.next();
			int oldXCoord = head.getxCoord();
			int oldYCoord = head.getyCoord();
			head.setxCoord(head.getxCoord() - 1 * Snake.SNAKE_SIZE);
			while(iterator.hasNext()) {
				BodyPart bodyPart = iterator.next();
				int newXCoord = oldXCoord;
				int newYCoord = oldYCoord;
				oldXCoord = bodyPart.getxCoord();
				oldYCoord = bodyPart.getyCoord();
				bodyPart.setxCoord(newXCoord);
				bodyPart.setyCoord(newYCoord);
			}
		}
		
		isSnakeCollision();
		isFoodEaten();
	}
	
	public boolean isFoodEaten() {
		Iterator<BodyPart> iterator = snake.getSnakeBody().iterator();
		BodyPart head = iterator.next();
		if(head.getxCoord() > (food.getxCoord() - Food.FOOD_SIZE) && head.getxCoord() < (food.getxCoord() + Food.FOOD_SIZE) && 
		   head.getyCoord() > (food.getyCoord() - Food.FOOD_SIZE) && head.getyCoord() < (food.getyCoord() + Food.FOOD_SIZE)) {
			food = new Food(snake);
			BodyPart bodyPart = new BodyPart();
			snake.addBodyPart(bodyPart);
			return true;
		}
		
		return false;
	}
	
	public boolean isSnakeCollision() {
		Iterator<BodyPart> iterator = snake.getSnakeBody().iterator();
		BodyPart head = iterator.next();
		while(iterator.hasNext()) {
			BodyPart bodyPart = iterator.next();
			if(head.getxCoord() == bodyPart.getxCoord() && head.getyCoord() == bodyPart.getyCoord()) {
				System.out.print("Game Over");
				stop();
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isBorderCollision() {
		if(snake.getHead().getxCoord() - Snake.SNAKE_SIZE / 2 < 0 || snake.getHead().getxCoord() + Snake.SNAKE_SIZE / 2 >= WIDTH ||
		   snake.getHead().getyCoord() - Snake.SNAKE_SIZE / 2 < 0 || snake.getHead().getyCoord() + Snake.SNAKE_SIZE / 2 >= HEIGHT) {
			System.out.print("Game Over");
			stop();
			return true;
		}
		
		return false;
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(snake != null) {
			for(BodyPart bodyPart: snake.getSnakeBody()) {
				g.setColor(Color.GREEN);
				g.fillRect(bodyPart.getxCoord(), bodyPart.getyCoord(), Snake.SNAKE_SIZE, Snake.SNAKE_SIZE);
			}
		}
		
		if(food != null) {
			g.setColor(Color.RED);
			g.fillRect(food.getxCoord(), food.getyCoord(), Food.FOOD_SIZE, Food.FOOD_SIZE);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && !down) {
			up = true;
			left = false;
			right = false;
		}
		if((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && !left) {
			right = true;
			up = false;
			down = false;
		}
		if((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && !up) {
			down = true;
			right = false;
			left = false;
		}
		if((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && !right) {
			left = true;
			up = false;
			down = false;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		while(running) {
			try {
				thread.sleep(LOOP);
				move();
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
