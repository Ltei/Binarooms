package lteii._10rooms;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;

import lteii._10rooms.state.SubStateInfos;
import lteii._10rooms.state.StatesManager;
import lteii._10rooms.state.StateRoom;


public class ActMain extends AppCompatActivity {

    public static final int RC_CHECK_PERMISSIONS = 0;


    public static StatesManager STATES_MANAGER = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        if (Permissions.checkup(this)) onPermissionCheckValidated();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_CHECK_PERMISSIONS) {
            if (resultCode == RESULT_OK) {
                onPermissionCheckValidated();
            } else {
                Permissions.checkup(this);
            }
        }
    }


    @Override
    public void onBackPressed() {
        if (!STATES_MANAGER.onBackPressed())
            super.onBackPressed();
    }

    private void onPermissionCheckValidated() {
        Database.setup(getApplicationContext());

        final MenuDrawerLayout menuDrawerLayout = (MenuDrawerLayout)findViewById(R.id.MenuDrawerLayout);
        menuDrawerLayout.setup(this);

        final ImageButton toolbarMenuButton = (ImageButton) findViewById(R.id.toolbar_menu_button);
        toolbarMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuDrawerLayout.isDrawerOpen(Gravity.START)) {
                    menuDrawerLayout.closeDrawer(Gravity.START);
                } else {
                    menuDrawerLayout.openDrawer(Gravity.START);
                }
            }
        });

        final ImageButton toolbarInfosButton = (ImageButton) findViewById(R.id.toolbar_infos_button);
        toolbarInfosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                STATES_MANAGER.setSubState(new SubStateInfos());
            }
        });

        STATES_MANAGER = new StatesManager(getFragmentManager(), menuDrawerLayout, new StateRoom().setup(Database.SOURCE_ROOM));
    }



}
