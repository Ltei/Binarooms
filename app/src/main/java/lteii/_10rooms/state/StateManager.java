package lteii._10rooms.state;


import android.app.FragmentManager;

import java.util.Stack;

import lteii._10rooms.MenuDrawerLayout;
import lteii._10rooms.R;


public class StateManager {


    private final FragmentManager fragmentManager;
    private final MenuDrawerLayout menuDrawerLayout;

    private final Stack<State> stateStack;

    public StateManager(FragmentManager fragmentManager, MenuDrawerLayout menuDrawerLayout, State firstState) {
        this.fragmentManager = fragmentManager;
        this.menuDrawerLayout = menuDrawerLayout;
        this.stateStack = new Stack<>();
        setState(firstState);
    }


    public void setState(State state) {
        if (state instanceof StateHelloWorld) {
            stateStack.clear();
            menuDrawerLayout.setMenuIndex(MenuDrawerLayout.MENU_IDX_HELLO_WORLD);
        } else if (state instanceof StateAllRooms) {
            stateStack.clear();
            menuDrawerLayout.setMenuIndex(MenuDrawerLayout.MENU_IDX_ALL_ROOMS);
        } else if (state instanceof StateRoom) {
            stateStack.clear();
            menuDrawerLayout.setMenuIndex(MenuDrawerLayout.MENU_IDX_ROOM);
        } else if (state instanceof StateRoomEditor) {

        } else {
            throw new IllegalStateException();
        }

        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, state)
                .commit();
        stateStack.push(state);
    }

    public boolean onBackPressed() {
        if (stateStack.size() <= 1) {
            return false;
        } else {
            stateStack.pop();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, stateStack.firstElement())
                    .commit();
            return true;
        }
    }


}
