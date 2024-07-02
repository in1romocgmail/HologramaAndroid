package com.cromero.hiTFG;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.WindowManager;

public class CustomActivity extends Activity {

    public String uno[] = {"sun", "Sun", "san", "San", "Sam", "sand", "Sand", "SAN", "Sant","zoom",
                            "sum", "sur","sup","Tang","sang","pan","Salou","Shaun","Shanw","sound"};

    public String dos[] = {"car", "Car", "call", "Call", "card", "Card","cara","ko","Cao","cars",
                            "cal","call","karts","caos","chaos","kart"};

    public String tres[] = {"fish", "Fish", "fi", "Fi", "fis", "Fis","face","six","Fizz","CIS","piece",};

    public String cuatro[] = {"bird", "Bird", "beer", "Beer", "bir", "Bir","ver","Berg","bear","beg",
                            "bead"};

    public String cinco[] = {"shoe", "Shoe", "choose", "Choose", "chus", "Chus","surf","sur","you","sup",
                            "su","Chu","tú"};

    public String seis[] = {"shark", "Shark", "sar", "Sar","chat","chats","chart","SARC","Sark","tshark","chao"};

    public String siete[] = {"tree", "Tree", "trip", "Trip", "tri", "Tri","tweet","Tui","twitch","Twi","tuit",
                                "three","trip","trip"};

    public String ocho[] = {"flower", "Flower", "Flowers", "flaguer", "Flaguer","showers","flowers","flowerz",
                            "tower","flow","fluor","software"};

    public String nueve[] = {"butterfly", "Butterfly", "butterflies","baterflai","baterflay","Buterfly",
                            "butter fly","butter-fly"};

    public String diez[] = {"jellyfish", "JellyFish", "jelyi fish", "Lleli Fish", "yeli fish", "Yeli Fish","Jerry fish",
                            "jellyfished","Jenny fish","Jenny Fish","Jenny face","Jenni fish","Geli fish"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        // to increase the volume
        AudioManager audioManager = (AudioManager) this
                .getSystemService(Context.AUDIO_SERVICE);
        // Set the volume of played media to maximum.
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

        // to increase brihtness
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.screenBrightness = 1.0f;
        getWindow().setAttributes(layoutParams);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	/*
     * @Override public boolean onOptionsItemSelected(MenuItem item) { switch
	 * (item.getItemId()) { case R.id.menu_about: Intent activity = new
	 * Intent(this, AboutActivity.class); this.startActivity(activity); return
	 * true; default: return super.onOptionsItemSelected(item); } }
	 */

    @Override
    public void onBackPressed() {
        // Display alert message when back button has been pressed
        backButtonHandler();
        return;
    }

    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                CustomActivity.this);
        // Setting Dialog Title
        alertDialog.setTitle("Salir");
        // Setting Dialog Message
        alertDialog
                .setMessage("¿Estás seguro que quieres salir de este apartado?");
        // Setting Icon to Dialog
        // alertDialog.setIcon(R.drawable.dialog_icon);
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("Si",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Intent i = new
                        // Intent(CustomActivity.this,MainActivity.class);
                        CustomActivity.this.finish();
                        // startActivity(i);
                    }
                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });
        // Showing Alert Message
        alertDialog.show();
    }

}
