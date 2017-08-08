package lteii.binarooms;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import lteii.binarooms.state.State;
import lteii.binarooms.state.StateInfos;
import lteii.binarooms.state.StateRoom;
import lteii.binarooms.state.StateSavedRooms;
import lteii.binarooms.state.SubState;

import static lteii.binarooms.ActMain.DATABASE;
import static lteii.binarooms.ActMain.STATES_MANAGER;

public class MenuDrawerLayout extends DrawerLayout {

    private static final String[] menuStrings = new String[] {"Navigate", "Saved rooms", "Infos"};

    public static final int MIN_MENU_IDX = -1, MAX_MENU_IDX = 2;
    public static final int MENU_IDX_NONE = -1;

    public static final int MENU_IDX_NAVIGATE = 0;
    public static final int MENU_IDX_SAVED_ROOMS = 1;
    public static final int MENU_IDX_INFOS = 2;


    private int menuIndex = MENU_IDX_NONE;
    private ListView menuListView = null;

    public MenuDrawerLayout(Context context) {
        super(context);
    }
    public MenuDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setup(final AppCompatActivity activity) {
        menuListView = findViewById(R.id.left_drawer);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, R.layout.layout_menu_drawer_list, menuStrings);
        menuListView.setAdapter(adapter);

        menuListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != menuIndex) {
                    if (position == MENU_IDX_NAVIGATE) {
                        STATES_MANAGER.setState(new StateRoom().setup(DATABASE.sourceRoom));
                    } else if (position == MENU_IDX_SAVED_ROOMS) {
                        STATES_MANAGER.setState(new StateSavedRooms());
                    } else if (position == MENU_IDX_INFOS) {
                        STATES_MANAGER.setState(new StateInfos());
                    } else {
                        throw new IllegalStateException();
                    }
                    setMenuIndex(position);
                    closeDrawer(menuListView);
                }
            }
        });
    }

    public void setupMenuIndex(State currentState) {
        if (currentState instanceof StateRoom) {
            setMenuIndex(MENU_IDX_NAVIGATE);
        } else if (currentState instanceof StateSavedRooms) {
            setMenuIndex(MENU_IDX_SAVED_ROOMS);
        } else if (currentState instanceof StateInfos) {
            setMenuIndex(MENU_IDX_INFOS);
        } else if (!(currentState instanceof SubState)) {
            setMenuIndex(MENU_IDX_NONE);
        }
    }

    private void setMenuIndex(int index) {
        if (index < MIN_MENU_IDX || index > MAX_MENU_IDX) throw new IllegalArgumentException();
        menuIndex = index;
        if (index != MENU_IDX_NONE) {
            menuListView.setItemChecked(index, true);
        }
    }


}
