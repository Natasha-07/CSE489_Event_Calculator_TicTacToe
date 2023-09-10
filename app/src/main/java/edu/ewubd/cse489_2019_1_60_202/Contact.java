package edu.ewubd.cse489_2019_1_60_202;

import android.graphics.Bitmap;

public class Contact {
        String key = "";
        String name = "";
        String email = "";
        String phoneHome = "";
        String phoneOffice = "";
        int width = 40;  // desired width of the bitmap
        int height = 40; // desired height of the bitmap

        Bitmap photo = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        String value = "";


        public Contact(String key, String name ,String email, String phoneHome, String phoneOffice, Bitmap photo, String value){
                this.key = key;
                this.name = name;
                this.email = email;
                this.phoneHome = phoneHome;
                this.phoneOffice = phoneOffice;
                this.photo = photo;
                this.value = value;
        }

}
