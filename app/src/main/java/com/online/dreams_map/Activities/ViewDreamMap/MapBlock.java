package com.online.dreams_map.Activities.ViewDreamMap;





public class MapBlock {

    private String back;
    private String object;
    private int size;
    private int rotate;
    private int orig_rotate;
    private int height3d;
    private String border_up;
    private String border_right;
    private String border_down;
    private String border_left;





    public MapBlock(
            String back,
            String object,
            int size,
            int rotate,
            int orig_rotate,
            int height3d,
            String[] border_up,
            String[] border_right,
            String[] border_down,
            String[] border_left
    ){
        this.back = back;
        this.object = object;

        this.border_up = border_up[0]+"_"+border_up[1];
        this.border_right = border_right[0]+"_"+border_right[1];
        this.border_down = border_down[0]+"_"+border_down[1];
        this.border_left = border_left[0]+"_"+border_left[1];

        this.height3d = height3d;
        this.size = size;
        this.rotate = rotate;
        this.orig_rotate = orig_rotate;
    }





    // Получить тип местности блока
    public String getBack(){
        return back;
    }


    // Получить объект блока
    public String getObject(){
        return object;
    }


    // Получить размер ячейки
    public int getSize(){
        return size;
    }


    // Получить размер ячейки
    public int getRotate(){
        return rotate;
    }


    // Получить размер ячейки
    public int getOrigRotate(){
        return orig_rotate;
    }


    // Получить высоту блока по Z
    public int get3DHeight(){
        return height3d;
    }


    // Есть ли границы
    public boolean getBorder(){
        if(!getBorderUp().equals("empty") || !getBorderRight().equals("empty") || !getBorderDown().equals("empty") || !getBorderLeft().equals("empty"))
            {return true;}

        return false;
    }


    // Получить верхнюю границу
    public String getBorderUp(){
        return border_up;
    }


    // Получить правую границу
    public String getBorderRight(){
        return border_right;
    }


    // Получить нижнюю границу
    public String getBorderDown(){
        return border_down;
    }


    // Получить левую границу
    public String getBorderLeft(){
        return border_left;
    }


}
