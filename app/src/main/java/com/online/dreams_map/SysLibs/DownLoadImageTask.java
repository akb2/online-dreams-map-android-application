package com.online.dreams_map.SysLibs;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;





public class DownLoadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    String filename;

    private Context context;
    private Activity activity;


    public DownLoadImageTask(ImageView bmImage, String filename, Context context){
        this.bmImage = bmImage;
        this.filename = filename;
        this.context = context;
        this.activity = (Activity) context;
    }

    protected Bitmap doInBackground(String... urls) {
        Bitmap mIcon11 = null;
        String urldisplay = urls[0];
        int start_str = urldisplay.lastIndexOf("?t=");


        // Локальная картинка
        if(urldisplay.equals("local")){
            Resources res = context.getResources();
            int resID = res.getIdentifier(filename, "drawable", this.context.getPackageName());

            if(resID > 0)
                {mIcon11 = BitmapFactory.decodeResource(res, resID);}
        }

        // Загрузка из интернета
        else if(start_str > 0){
            String last_date = urldisplay.substring(start_str + 3);
            last_date = last_date == null? "0": last_date;
            filename = filename + last_date + ".png";

            File file = context.getFileStreamPath(filename);


            if(file.exists()){
                mIcon11 = BitmapFactory.decodeFile(file.getAbsolutePath());
            }

            else{
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    mIcon11 = BitmapFactory.decodeStream(in);


                    try{
                        FileOutputStream out = context.openFileOutput(filename, context.MODE_PRIVATE);
                        mIcon11.compress(Bitmap.CompressFormat.PNG, 100, out);
                        out.close();
                    }

                    catch(Exception ignored)
                        {}

                    mIcon11.recycle();

                    file = context.getFileStreamPath(filename);
                    mIcon11 = BitmapFactory.decodeFile(file.getAbsolutePath());
                }

                catch (Exception e)
                    {e.printStackTrace();}

            }
        }



        return mIcon11;
    }


    protected void onPostExecute(Bitmap result){
        bmImage.post(new setImageView(bmImage, result));
    }





    private class setImageView extends Thread{
        private ImageView bmImage;
        private Bitmap result;

        public setImageView(ImageView bmImage, Bitmap result){
            this.bmImage = bmImage;
            this.result = result;
        }

        @Override
        public void run(){
            bmImage.setImageBitmap(result);
        }
    }


}