package lteii._10rooms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import lteii._10rooms.state.StateHelloWorld;
import lteii._10rooms.state.StateManager;


public class ActMain extends AppCompatActivity {

    public static final int RC_CHECK_PERMISSIONS = 0;


    public static StateManager STATE_MANAGER = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        if (Permissions.checkup(this)) {
            onPermissionCheckValidated();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_CHECK_PERMISSIONS) {
            onCheckPermissionsResult(resultCode);
        }
    }
    private void onCheckPermissionsResult(int resultCode) {
        if (resultCode == RESULT_OK) {
            onPermissionCheckValidated();
        } else {
            Permissions.checkup(this);
        }
    }


    @Override
    public void onBackPressed() {
        if (!STATE_MANAGER.onBackPressed())
            super.onBackPressed();
    }

    private void onPermissionCheckValidated() {
        Database.setup(getApplicationContext());
        final MenuDrawerLayout menuDrawerLayout = (MenuDrawerLayout)findViewById(R.id.MenuDrawerLayout);
        menuDrawerLayout.setup(this);
        STATE_MANAGER = new StateManager(getFragmentManager(), menuDrawerLayout, new StateHelloWorld());
    }



}
