package lteii._10rooms;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import lteii._10rooms.state.StateAllRooms;
import lteii._10rooms.state.StateHelloWorld;
import lteii._10rooms.state.StateRoom;

import static lteii._10rooms.ActMain.STATE_MANAGER;

public class MenuDrawerLayout extends DrawerLayout {

    private static final String[] menuStrings = new String[] {"Hello world", "All rooms", "Source room", "Your rooms", "Saved rooms", "Tendencies"};

    public static final int MIN_MENU_IDX = -1, MAX_MENU_IDX = 5;
    public static final int MENU_IDX_NONE = -1;

    public static final int MENU_IDX_HELLO_WORLD = 0;
    public static final int MENU_IDX_ALL_ROOMS = 1;
    public static final int MENU_IDX_ROOM = 2;
    public static final int MENU_IDX_YOUR_ROOMS = 3;
    public static final int MENU_IDX_SAVED_ROOMS = 4;
    public static final int MENU_IDX_TENDENCIES = 5;


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
                    if (position == MENU_IDX_HELLO_WORLD) {
                        STATE_MANAGER.setState(new StateHelloWorld());
                    } else if (position == MENU_IDX_ALL_ROOMS) {
                        STATE_MANAGER.setState(new StateAllRooms());
                    } else if (position == MENU_IDX_ROOM) {
                        STATE_MANAGER.setState(new StateRoom().setup(Database.SOURCE_ROOM));
                    } else if (position == MENU_IDX_YOUR_ROOMS) {

                    } else if (position == MENU_IDX_SAVED_ROOMS) {

                    } else if (position == MENU_IDX_TENDENCIES) {

                    } else {
                        throw new IllegalStateException();
                    }
                    setMenuIndex(position);
                    closeDrawer(menuListView);
                }
            }
        });
    }

    public void setMenuIndex(int index) {
        if (index < MIN_MENU_IDX || index > MAX_MENU_IDX) throw new IllegalArgumentException();
        menuIndex = index;
        if (index != MENU_IDX_NONE) {
            menuListView.setItemChecked(index, true);
        }
    }


}
