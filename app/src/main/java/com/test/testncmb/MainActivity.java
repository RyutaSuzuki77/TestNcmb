package com.test.testncmb;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nifcloud.mbaas.core.DoneCallback;
import com.nifcloud.mbaas.core.NCMB;
import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBObject;

public class MainActivity extends AppCompatActivity {
    //NCMBにて取得したAPIKEYを記載
    private final String app_key = "b6f94875e0ce8f917a141c54c15406d6c69a97565ec6e42be481812048e63d13";
    private final String client_key = "265913ff778ea9822819457addb28f18bdd19b1cf2b7e290981c766779136dcf";
    public String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SDKの初期化
        NCMB.initialize(this.getApplicationContext(),app_key,client_key);
        //NCMBObjectの作成(クラス名は何でもOK)
        final NCMBObject obj = new NCMBObject("Testclass");
        final Context context = getApplicationContext();
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.edittext);
                //入力したテキスト取得
                message = editText.getText().toString();
                try{
                    //データストアに取得したテキストを登録
                    obj.put("message",message);
                }catch (NCMBException e){
                    e.printStackTrace();
                }
                obj.saveInBackground(new DoneCallback() {
                    @Override
                    public void done(NCMBException e) {
                        if(e != null){
                            //登録失敗
                            Toast.makeText(context, "ERROR", Toast.LENGTH_LONG).show();
                        } else {
                            //登録成功
                            Toast.makeText(context, "SUCCESS", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

}
