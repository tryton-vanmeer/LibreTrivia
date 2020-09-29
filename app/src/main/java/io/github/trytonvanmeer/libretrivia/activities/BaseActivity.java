package io.github.trytonvanmeer.libretrivia.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;

import io.github.trytonvanmeer.libretrivia.R;
import io.github.trytonvanmeer.libretrivia.settings.SettingsActivity;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                onSettings();
                return true;
            case R.id.about:
                onAbout();
                return true;
            case android.R.id.home:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void onAbout() {
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
