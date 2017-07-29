package lteii.binarooms.state;

import android.app.FragmentManager;

import java.util.Stack;

import lteii.binarooms.MenuDrawerLayout;
import lteii.binarooms.R;


public class StatesManager {

    private final FragmentManager fragmentManager;
    private final MenuDrawerLayout menuDrawerLayout;

    private final Stack<State> states;

    public StatesManager(FragmentManager fragmentManager, MenuDrawerLayout menuDrawerLayout, State firstState) {
        this.fragmentManager = fragmentManager;
        this.menuDrawerLayout = menuDrawerLayout;
        this.states = new Stack<>();
        setState(firstState);
    }


    public void setState(State state) {
        for (int i=0; i<states.size(); i++) {
            final State saved = states.get(i);
            if (saved instanceof SubState || saved.getClass().equals(state.getClass())) {
                states.remove(i);
                i--;
            }
        }

        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, state)
                .commit();
        states.push(state);

        if (states.size() >= 10) states.remove(0);

        menuDrawerLayout.setupMenuIndex(state);
    }

    public boolean onBackPressed() {
        if (states.size() == 1) {
            return false;
        } else {
            states.pop();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, states.firstElement())
                    .commit();
            menuDrawerLayout.setupMenuIndex(states.firstElement());
            return true;
        }
    }

    public State getCurrentState() {
        return states.firstElement();
    }


}
