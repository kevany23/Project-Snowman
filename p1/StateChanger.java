package p1;

public class StateChanger {
	public static StateChanger instance = new StateChanger();
	public static StateChanger instance() {
		return instance;
	}
	
	
	private MenuState menuState;
	private PlayState playState;
	private PauseState pauseState;
	
	public void loadStates(MenuState ms, PlayState pls, PauseState pas) {
		menuState = ms;
		playState = pls;
		pauseState = pas;
	}
	
	public State nextState(GState s) {
		State st = null;
		switch(s) {
		case play:
			st = playState;
			break;
		case menu:
			st = menuState;
			break;
		case pause:
			st = pauseState;
			break;
		default: 
			break;
		}
		return st;
	}
	
}
