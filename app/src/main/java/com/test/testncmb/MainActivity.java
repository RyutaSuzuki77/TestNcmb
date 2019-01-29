package com.test.testncmb;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nifcloud.mbaas.core.DoneCallback;
import com.nifcloud.mbaas.core.FindCallback;
import com.nifcloud.mbaas.core.NCMB;
import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBObject;
import com.nifcloud.mbaas.core.NCMBQuery;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //NCMBにて取得したAPIKEYを記載
    private final String app_key = "b6f94875e0ce8f917a141c54c15406d6c69a97565ec6e42be481812048e63d13";
    private final String client_key = "265913ff778ea9822819457addb28f18bdd19b1cf2b7e290981c766779136dcf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SDKの初期化
        NCMB.initialize(this.getApplicationContext(),app_key,client_key);
        //
        final NCMBQuery<NCMBObject> query = new NCMBQuery<>("Testclass");
        query.whereEqualTo("message", "ドラゴン");
        //query.setLimit(1);
        final Context context = getApplicationContext();
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                query.findInBackground(new FindCallback<NCMBObject>() {
                    @Override
                    public void done(List<NCMBObject> list, NCMBException e) {
                        if(e != null){
                            Toast.makeText(context, "データストアに存在しません。", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "データストアに存在します。", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

}