package edu.depaul.csc472.restaurant;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // my_child_toolbar is defined in the layout file
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        final EditText txtUserName = (EditText) findViewById(R.id.lgn_username);
        final EditText txtPassword = (EditText) findViewById(R.id.lgn_password);
        final Button btnSignIn = (Button) findViewById(R.id.btnSignIn);
        final Button btnGotoSignUp = (Button) findViewById(R.id.btnGotoSignUp);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String strUserName = txtUserName.getText().toString();
                    String strPassword = txtPassword.getText().toString();

                    if(strUserName.equals("")||strPassword.equals("")){

                        showMSG("Please input UserName and Password!");
                    }

                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("UserName", strUserName);
                    params.put("Password", strPassword);

                    AsyncSignIn signInTask = new AsyncSignIn();
                    signInTask.params = params;
                    signInTask.execute("http://140.192.34.69/restaurant/api/User/Login");

                } catch (Exception e) {
                    // response body is no valid JSON string
                }
            }
        });

        btnGotoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(SignInActivity.this, SignUpActivity.class);
                //loginIntent.putExtra(RestaurantDetailFragment.ARG_ITEM_ID, id);
                startActivity(loginIntent);
            }
        });

    }

    protected void showMSG(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, RestaurantListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class AsyncSignIn extends AsyncTask<String, Void, JSONObject> {

        private Exception exception;
        public HashMap<String, String> params = new HashMap<String, String>();

        protected JSONObject doInBackground(String... urls) {
            try {
                JSONObject retJson = HttpHelper.Post(urls[0], params);
                return retJson;
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        protected void onPostExecute(JSONObject feed) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {
                if (feed!=null) {
                    showMSG(feed.getString("Message"));
                    if (feed.getString("RetCode").equals("0")){
                        finish();
                    }
                }
            }
            catch (JSONException e) {

            }
        }

        protected void showMSG(String msg){
            Toast.makeText(SignInActivity.this, msg, Toast.LENGTH_LONG).show();
        }
    }
}