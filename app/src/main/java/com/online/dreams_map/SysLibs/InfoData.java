package com.online.dreams_map.SysLibs;





public class InfoData {


    // Склонение числительного
    public static String decline_num(int number, String[] words){
        int[] keys = new int[]{ 2, 0, 1, 1, 1, 2 };
        int mod = number % 100;
        int suffix_key = mod > 7 & mod < 20? 2: keys[Math.min(mod % 10, 5)];

        return words[suffix_key];
    }


}
