package io.github.trytonvanmeer.libretrivia.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;

import io.github.trytonvanmeer.libretrivia.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings:
                return true;
            case R.id.about:
                onAbout();
                return true;
            case  android.R.id.home:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onAbout() {
        String appName = getResources().getString(R.string.app_name);
        String appDescription = getResources().getString(R.string.app_description);

        new LibsBuilder()
                .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                .withAboutIconShown(true)
                .withAboutAppName(appName)
                .withAboutVersionShownName(true)
                .withAboutDescription(appDescription)
                .start(this);
    }
}
