package com.example.sharjeel.filedataget;

import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    int x1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Button b=(Button)findViewById(R.id.button);
    }

    public void play(View v) {

    }

    public void pause(View v) {
        if (player != null) {
            player.pause();
        }
    }

    public void stop(View v) {
        stopPlayer();
    }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
            Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }

    public void order(View v) {
        try {

            AssetManager am=getAssets();// If this line gives you ERROR then try AssetManager am=getActivity().getAssets();
            InputStream is=am.open("museMonitor_2018-06-24--19-00-12_1367485629.xls");
            Workbook wb =Workbook.getWorkbook(is);
            Sheet s=wb.getSheet(0);
            int row =s.getRows();
            int col=s.getColumns();
            String xx="";

            for(int i=0;i<row;i++)
            {
                for (int c=0;c<col;c++)
                {
                    Cell z=s.getCell(c,i);
                    xx=xx+z.getContents();

                }
                xx=xx+"\n";
            }
          display(xx);
            if (player == null) {
                player = MediaPlayer.create(this, R.raw.song);
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        stopPlayer();
                    }
                });
            }

            player.start();
 x1=Integer.parseInt(xx);
//if (x1>=0.282844 ){
   // player.start();
//}
        }

        catch (Exception e)
        {

        }

    }
    public void display(String value)
    {
        TextView x=(TextView) findViewById(R.id.textView);
        x.setText(value);
    }
}
