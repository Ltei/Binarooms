package lteii._10rooms.state;


import android.app.FragmentManager;
import android.support.annotation.Nullable;

import lteii._10rooms.MenuDrawerLayout;
import lteii._10rooms.R;


public class StatesManager {


    private final FragmentManager fragmentManager;
    private final MenuDrawerLayout menuDrawerLayout;

    private State currentState = null;
    private @Nullable SubState currentSubState = null;

    public StatesManager(FragmentManager fragmentManager, MenuDrawerLayout menuDrawerLayout, State firstState) {
        this.fragmentManager = fragmentManager;
        this.menuDrawerLayout = menuDrawerLayout;
        setState(firstState);
    }


    public void setState(State state) {
        if (state instanceof StateAllRooms) {
            menuDrawerLayout.setMenuIndex(MenuDrawerLayout.MENU_IDX_ALL_ROOMS);
        } else if (state instanceof StateRoom) {
            menuDrawerLayout.setMenuIndex(MenuDrawerLayout.MENU_IDX_NONE);
        } else {
            throw new IllegalStateException();
        }

        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, state)
                .commit();
        this.currentState = state;
        this.currentSubState = null;
    }

    public void setSubState(SubState subState) {
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, subState)
                .commit();
        this.currentSubState = subState;
    }

    public boolean onBackPressed() {
        if (currentSubState == null) {
            return false;
        } else {
            currentSubState = null;
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, currentState)
                    .commit();
            return true;
        }
    }


}
