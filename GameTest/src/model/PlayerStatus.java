package model;

public class PlayerStatus {

	private GameFrame frame;
	private GameMap map;
	private Player player;
	
	
	public PlayerStatus(GameFrame frame, GameMap map, Player player) {
		super();
		this.frame = frame;
		this.map = map;
		this.player = player;
	}
	public GameFrame getFrame() {
		return frame;
	}
	public void setFrame(GameFrame frame) {
		this.frame = frame;
	}
	public GameMap getMap() {
		return map;
	}
	public void setMap(GameMap map) {
		this.map = map;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
}
