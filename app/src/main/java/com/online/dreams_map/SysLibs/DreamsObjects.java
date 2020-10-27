package com.online.dreams_map.SysLibs;


import java.util.HashMap;





public class DreamsObjects {

    // Список объектов
    public HashMap<String, HashMap<Integer, String[]>> get_objects(){
        HashMap<String, HashMap<Integer, String[]>> ret = new HashMap<>();



        // Башня
        {

            // Вниз
            ret.put("turret_d", new HashMap<Integer, String[]>());
            ret.get("turret_d").put(1, new String[]{"3", "turret_d"});
            ret.get("turret_d").put(2, new String[]{"3", "turret_s"});
            ret.get("turret_d").put(3, new String[]{"3", "turret_u"});
            ret.get("turret_d").put(4, new String[]{"3", "turret_s"});

            // Вверх
            ret.put("turret_u", new HashMap<Integer, String[]>());
            ret.get("turret_u").put(1, new String[]{"3", "turret_u"});
            ret.get("turret_u").put(2, new String[]{"3", "turret_s"});
            ret.get("turret_u").put(3, new String[]{"3", "turret_d"});
            ret.get("turret_u").put(4, new String[]{"3", "turret_s"});

            // Влево
            ret.put("turret_l", new HashMap<Integer, String[]>());
            ret.get("turret_l").put(1, new String[]{"3", "turret_s"});
            ret.get("turret_l").put(2, new String[]{"3", "turret_u"});
            ret.get("turret_l").put(3, new String[]{"3", "turret_s"});
            ret.get("turret_l").put(4, new String[]{"3", "turret_d"});

            // Вправо
            ret.put("turret_r", new HashMap<Integer, String[]>());
            ret.get("turret_r").put(1, new String[]{"3", "turret_s"});
            ret.get("turret_r").put(2, new String[]{"3", "turret_d"});
            ret.get("turret_r").put(3, new String[]{"3", "turret_s"});
            ret.get("turret_r").put(4, new String[]{"3", "turret_u"});

        }

        // Гаражи
        {

            // Гараж 1
            {

                // Вниз
                ret.put("garage1_d", new HashMap<Integer, String[]>());
                ret.get("garage1_d").put(1, new String[]{"2", "garage1_d"});
                ret.get("garage1_d").put(2, new String[]{"2", "garage1_s"});
                ret.get("garage1_d").put(3, new String[]{"2", "garage1_u"});
                ret.get("garage1_d").put(4, new String[]{"2", "garage1_s"});

                // Вверх
                ret.put("garage1_u", new HashMap<Integer, String[]>());
                ret.get("garage1_u").put(1, new String[]{"2", "garage1_u"});
                ret.get("garage1_u").put(2, new String[]{"2", "garage1_s"});
                ret.get("garage1_u").put(3, new String[]{"2", "garage1_d"});
                ret.get("garage1_u").put(4, new String[]{"2", "garage1_s"});

                // Влево
                ret.put("garage1_l", new HashMap<Integer, String[]>());
                ret.get("garage1_l").put(1, new String[]{"2", "garage1_s"});
                ret.get("garage1_l").put(2, new String[]{"2", "garage1_u"});
                ret.get("garage1_l").put(3, new String[]{"2", "garage1_s"});
                ret.get("garage1_l").put(4, new String[]{"2", "garage1_d"});

                // Вправо
                ret.put("garage1_r", new HashMap<Integer, String[]>());
                ret.get("garage1_r").put(1, new String[]{"2", "garage1_s"});
                ret.get("garage1_r").put(2, new String[]{"2", "garage1_d"});
                ret.get("garage1_r").put(3, new String[]{"2", "garage1_s"});
                ret.get("garage1_r").put(4, new String[]{"2", "garage1_u"});

            }

            // Гараж 2
            {

                // Вниз
                ret.put("garage2_d", new HashMap<Integer, String[]>());
                ret.get("garage2_d").put(1, new String[]{"2", "garage2_d"});
                ret.get("garage2_d").put(2, new String[]{"2", "garage2_s"});
                ret.get("garage2_d").put(3, new String[]{"2", "garage2_u"});
                ret.get("garage2_d").put(4, new String[]{"2", "garage2_s"});

                // Вверх
                ret.put("garage2_u", new HashMap<Integer, String[]>());
                ret.get("garage2_u").put(1, new String[]{"2", "garage2_u"});
                ret.get("garage2_u").put(2, new String[]{"2", "garage2_s"});
                ret.get("garage2_u").put(3, new String[]{"2", "garage2_d"});
                ret.get("garage2_u").put(4, new String[]{"2", "garage2_s"});

                // Влево
                ret.put("garage2_l", new HashMap<Integer, String[]>());
                ret.get("garage2_l").put(1, new String[]{"2", "garage2_s"});
                ret.get("garage2_l").put(2, new String[]{"2", "garage2_u"});
                ret.get("garage2_l").put(3, new String[]{"2", "garage2_s"});
                ret.get("garage2_l").put(4, new String[]{"2", "garage2_d"});

                // Вправо
                ret.put("garage2_r", new HashMap<Integer, String[]>());
                ret.get("garage2_r").put(1, new String[]{"2", "garage2_s"});
                ret.get("garage2_r").put(2, new String[]{"2", "garage2_d"});
                ret.get("garage2_r").put(3, new String[]{"2", "garage2_s"});
                ret.get("garage2_r").put(4, new String[]{"2", "garage2_u"});

            }

        }

        // Деревья
        {

            ret.put("wood_1", new HashMap<Integer, String[]>());
            ret.get("wood_1").put(1, new String[]{"2", "wood_1"});
            ret.get("wood_1").put(2, new String[]{"2", "wood_1"});
            ret.get("wood_1").put(3, new String[]{"2", "wood_1"});
            ret.get("wood_1").put(4, new String[]{"2", "wood_1"});

            ret.put("wood_2", new HashMap<Integer, String[]>());
            ret.get("wood_2").put(1, new String[]{"2", "wood_2"});
            ret.get("wood_2").put(2, new String[]{"2", "wood_2"});
            ret.get("wood_2").put(3, new String[]{"2", "wood_2"});
            ret.get("wood_2").put(4, new String[]{"2", "wood_2"});

            ret.put("wood_3", new HashMap<Integer, String[]>());
            ret.get("wood_3").put(1, new String[]{"2", "wood_3"});
            ret.get("wood_3").put(2, new String[]{"2", "wood_3"});
            ret.get("wood_3").put(3, new String[]{"2", "wood_3"});
            ret.get("wood_3").put(4, new String[]{"2", "wood_3"});

            ret.put("wood_4", new HashMap<Integer, String[]>());
            ret.get("wood_4").put(1, new String[]{"2", "wood_4"});
            ret.get("wood_4").put(2, new String[]{"2", "wood_4"});
            ret.get("wood_4").put(3, new String[]{"2", "wood_4"});
            ret.get("wood_4").put(4, new String[]{"2", "wood_4"});

            ret.put("wood_5", new HashMap<Integer, String[]>());
            ret.get("wood_5").put(1, new String[]{"2", "wood_5"});
            ret.get("wood_5").put(2, new String[]{"2", "wood_5"});
            ret.get("wood_5").put(3, new String[]{"2", "wood_5"});
            ret.get("wood_5").put(4, new String[]{"2", "wood_5"});

            ret.put("wood_6", new HashMap<Integer, String[]>());
            ret.get("wood_6").put(1, new String[]{"2", "wood_6"});
            ret.get("wood_6").put(2, new String[]{"2", "wood_6"});
            ret.get("wood_6").put(3, new String[]{"2", "wood_6"});
            ret.get("wood_6").put(4, new String[]{"2", "wood_6"});

            ret.put("wood_7", new HashMap<Integer, String[]>());
            ret.get("wood_7").put(1, new String[]{"2", "wood_7"});
            ret.get("wood_7").put(2, new String[]{"2", "wood_7"});
            ret.get("wood_7").put(3, new String[]{"2", "wood_7"});
            ret.get("wood_7").put(4, new String[]{"2", "wood_7"});

        }

        // Дом 5 этажей
        {

            // Подъезд
            {

                // Вниз
                ret.put("m_house1_d", new HashMap<Integer, String[]>());
                ret.get("m_house1_d").put(1, new String[]{"2", "m_house1_d"});
                ret.get("m_house1_d").put(2, new String[]{"2", "m_house1_l"});
                ret.get("m_house1_d").put(3, new String[]{"2", "m_house1_u"});
                ret.get("m_house1_d").put(4, new String[]{"2", "m_house1_r"});

                // Вверх
                ret.put("m_house1_u", new HashMap<Integer, String[]>());
                ret.get("m_house1_u").put(1, new String[]{"2", "m_house1_u"});
                ret.get("m_house1_u").put(2, new String[]{"2", "m_house1_r"});
                ret.get("m_house1_u").put(3, new String[]{"2", "m_house1_d"});
                ret.get("m_house1_u").put(4, new String[]{"2", "m_house1_l"});

                // Влево
                ret.put("m_house1_l", new HashMap<Integer, String[]>());
                ret.get("m_house1_l").put(1, new String[]{"2", "m_house1_l"});
                ret.get("m_house1_l").put(2, new String[]{"2", "m_house1_u"});
                ret.get("m_house1_l").put(3, new String[]{"2", "m_house1_r"});
                ret.get("m_house1_l").put(4, new String[]{"2", "m_house1_d"});

                // Вправо
                ret.put("m_house1_r", new HashMap<Integer, String[]>());
                ret.get("m_house1_r").put(1, new String[]{"2", "m_house1_r"});
                ret.get("m_house1_r").put(2, new String[]{"2", "m_house1_d"});
                ret.get("m_house1_r").put(3, new String[]{"2", "m_house1_l"});
                ret.get("m_house1_r").put(4, new String[]{"2", "m_house1_u"});

            }

            // Левая часть
            {

                // Вниз
                ret.put("m_house1_d2", new HashMap<Integer, String[]>());
                ret.get("m_house1_d2").put(1, new String[]{"2", "m_house1_d2"});
                ret.get("m_house1_d2").put(2, new String[]{"2", "m_house1_l"});
                ret.get("m_house1_d2").put(3, new String[]{"2", "m_house1_u"});
                ret.get("m_house1_d2").put(4, new String[]{"2", "m_house1_r"});

                // Вверх
                ret.put("m_house1_u2", new HashMap<Integer, String[]>());
                ret.get("m_house1_u2").put(1, new String[]{"2", "m_house1_u"});
                ret.get("m_house1_u2").put(2, new String[]{"2", "m_house1_r"});
                ret.get("m_house1_u2").put(3, new String[]{"2", "m_house1_d2"});
                ret.get("m_house1_u2").put(4, new String[]{"2", "m_house1_l"});

                // Влево
                ret.put("m_house1_l2", new HashMap<Integer, String[]>());
                ret.get("m_house1_l2").put(1, new String[]{"2", "m_house1_l"});
                ret.get("m_house1_l2").put(2, new String[]{"2", "m_house1_u"});
                ret.get("m_house1_l2").put(3, new String[]{"2", "m_house1_r"});
                ret.get("m_house1_l2").put(4, new String[]{"2", "m_house1_d2"});

                // Вправо
                ret.put("m_house1_r2", new HashMap<Integer, String[]>());
                ret.get("m_house1_r2").put(1, new String[]{"2", "m_house1_r"});
                ret.get("m_house1_r2").put(2, new String[]{"2", "m_house1_d2"});
                ret.get("m_house1_r2").put(3, new String[]{"2", "m_house1_l"});
                ret.get("m_house1_r2").put(4, new String[]{"2", "m_house1_u"});

            }

            // Правая часть
            {

                // Вниз
                ret.put("m_house1_d1", new HashMap<Integer, String[]>());
                ret.get("m_house1_d1").put(1, new String[]{"2", "m_house1_d1"});
                ret.get("m_house1_d1").put(2, new String[]{"2", "m_house1_l"});
                ret.get("m_house1_d1").put(3, new String[]{"2", "m_house1_u"});
                ret.get("m_house1_d1").put(4, new String[]{"2", "m_house1_r"});

                // Вверх
                ret.put("m_house1_u1", new HashMap<Integer, String[]>());
                ret.get("m_house1_u1").put(1, new String[]{"2", "m_house1_u"});
                ret.get("m_house1_u1").put(2, new String[]{"2", "m_house1_r"});
                ret.get("m_house1_u1").put(3, new String[]{"2", "m_house1_d1"});
                ret.get("m_house1_u1").put(4, new String[]{"2", "m_house1_l"});

                // Влево
                ret.put("m_house1_l1", new HashMap<Integer, String[]>());
                ret.get("m_house1_l1").put(1, new String[]{"2", "m_house1_l"});
                ret.get("m_house1_l1").put(2, new String[]{"2", "m_house1_u"});
                ret.get("m_house1_l1").put(3, new String[]{"2", "m_house1_r"});
                ret.get("m_house1_l1").put(4, new String[]{"2", "m_house1_d1"});

                // Вправо
                ret.put("m_house1_r1", new HashMap<Integer, String[]>());
                ret.get("m_house1_r1").put(1, new String[]{"2", "m_house1_r"});
                ret.get("m_house1_r1").put(2, new String[]{"2", "m_house1_d1"});
                ret.get("m_house1_r1").put(3, new String[]{"2", "m_house1_l"});
                ret.get("m_house1_r1").put(4, new String[]{"2", "m_house1_u"});

            }

            // Углы
            {

                // Верх-лево
                ret.put("m_house1_angle_u_l", new HashMap<Integer, String[]>());
                ret.get("m_house1_angle_u_l").put(1, new String[]{"2", "m_house1_angle_u_l"});
                ret.get("m_house1_angle_u_l").put(2, new String[]{"2", "m_house1_angle_u_r"});
                ret.get("m_house1_angle_u_l").put(3, new String[]{"2", "m_house1_angle_d_r"});
                ret.get("m_house1_angle_u_l").put(4, new String[]{"2", "m_house1_angle_d_l"});

                // Верх-право
                ret.put("m_house1_angle_u_r", new HashMap<Integer, String[]>());
                ret.get("m_house1_angle_u_r").put(1, new String[]{"2", "m_house1_angle_u_r"});
                ret.get("m_house1_angle_u_r").put(2, new String[]{"2", "m_house1_angle_d_r"});
                ret.get("m_house1_angle_u_r").put(3, new String[]{"2", "m_house1_angle_d_l"});
                ret.get("m_house1_angle_u_r").put(4, new String[]{"2", "m_house1_angle_u_l"});

                // Низ-лево
                ret.put("m_house1_angle_d_l", new HashMap<Integer, String[]>());
                ret.get("m_house1_angle_d_l").put(1, new String[]{"2", "m_house1_angle_d_l"});
                ret.get("m_house1_angle_d_l").put(2, new String[]{"2", "m_house1_angle_u_l"});
                ret.get("m_house1_angle_d_l").put(3, new String[]{"2", "m_house1_angle_u_r"});
                ret.get("m_house1_angle_d_l").put(4, new String[]{"2", "m_house1_angle_d_r"});

                // Низ-право
                ret.put("m_house1_angle_d_r", new HashMap<Integer, String[]>());
                ret.get("m_house1_angle_d_r").put(1, new String[]{"2", "m_house1_angle_d_r"});
                ret.get("m_house1_angle_d_r").put(2, new String[]{"2", "m_house1_angle_d_l"});
                ret.get("m_house1_angle_d_r").put(3, new String[]{"2", "m_house1_angle_u_l"});
                ret.get("m_house1_angle_d_r").put(4, new String[]{"2", "m_house1_angle_u_r"});

            }

        }

        // Дом 10 этажей
        {

            // Подъезд
            {

                // Вниз
                ret.put("b_house1_d", new HashMap<Integer, String[]>());
                ret.get("b_house1_d").put(1, new String[]{"3", "b_house1_d"});
                ret.get("b_house1_d").put(2, new String[]{"3", "b_house1_l"});
                ret.get("b_house1_d").put(3, new String[]{"3", "b_house1_u"});
                ret.get("b_house1_d").put(4, new String[]{"3", "b_house1_r"});

                // Вверх
                ret.put("b_house1_u", new HashMap<Integer, String[]>());
                ret.get("b_house1_u").put(1, new String[]{"3", "b_house1_u"});
                ret.get("b_house1_u").put(2, new String[]{"3", "b_house1_r"});
                ret.get("b_house1_u").put(3, new String[]{"3", "b_house1_d"});
                ret.get("b_house1_u").put(4, new String[]{"3", "b_house1_l"});

                // Влево
                ret.put("b_house1_l", new HashMap<Integer, String[]>());
                ret.get("b_house1_l").put(1, new String[]{"3", "b_house1_l"});
                ret.get("b_house1_l").put(2, new String[]{"3", "b_house1_u"});
                ret.get("b_house1_l").put(3, new String[]{"3", "b_house1_r"});
                ret.get("b_house1_l").put(4, new String[]{"3", "b_house1_d"});

                // Вправо
                ret.put("b_house1_r", new HashMap<Integer, String[]>());
                ret.get("b_house1_r").put(1, new String[]{"3", "b_house1_r"});
                ret.get("b_house1_r").put(2, new String[]{"3", "b_house1_d"});
                ret.get("b_house1_r").put(3, new String[]{"3", "b_house1_l"});
                ret.get("b_house1_r").put(4, new String[]{"3", "b_house1_u"});

            }

            // Левая часть
            {

                // Вниз
                ret.put("b_house1_d2", new HashMap<Integer, String[]>());
                ret.get("b_house1_d2").put(1, new String[]{"3", "b_house1_d2"});
                ret.get("b_house1_d2").put(2, new String[]{"3", "b_house1_l"});
                ret.get("b_house1_d2").put(3, new String[]{"3", "b_house1_u"});
                ret.get("b_house1_d2").put(4, new String[]{"3", "b_house1_r"});

                // Вверх
                ret.put("b_house1_u2", new HashMap<Integer, String[]>());
                ret.get("b_house1_u2").put(1, new String[]{"3", "b_house1_u"});
                ret.get("b_house1_u2").put(2, new String[]{"3", "b_house1_r"});
                ret.get("b_house1_u2").put(3, new String[]{"3", "b_house1_d2"});
                ret.get("b_house1_u2").put(4, new String[]{"3", "b_house1_l"});

                // Влево
                ret.put("b_house1_l2", new HashMap<Integer, String[]>());
                ret.get("b_house1_l2").put(1, new String[]{"3", "b_house1_l"});
                ret.get("b_house1_l2").put(2, new String[]{"3", "b_house1_u"});
                ret.get("b_house1_l2").put(3, new String[]{"3", "b_house1_r"});
                ret.get("b_house1_l2").put(4, new String[]{"3", "b_house1_d2"});

                // Вправо
                ret.put("b_house1_r2", new HashMap<Integer, String[]>());
                ret.get("b_house1_r2").put(1, new String[]{"3", "b_house1_r"});
                ret.get("b_house1_r2").put(2, new String[]{"3", "b_house1_d2"});
                ret.get("b_house1_r2").put(3, new String[]{"3", "b_house1_l"});
                ret.get("b_house1_r2").put(4, new String[]{"3", "b_house1_u"});

            }

            // Правая часть
            {

                // Вниз
                ret.put("b_house1_d1", new HashMap<Integer, String[]>());
                ret.get("b_house1_d1").put(1, new String[]{"3", "b_house1_d1"});
                ret.get("b_house1_d1").put(2, new String[]{"3", "b_house1_l"});
                ret.get("b_house1_d1").put(3, new String[]{"3", "b_house1_u"});
                ret.get("b_house1_d1").put(4, new String[]{"3", "b_house1_r"});

                // Вверх
                ret.put("b_house1_u1", new HashMap<Integer, String[]>());
                ret.get("b_house1_u1").put(1, new String[]{"3", "b_house1_u"});
                ret.get("b_house1_u1").put(2, new String[]{"3", "b_house1_r"});
                ret.get("b_house1_u1").put(3, new String[]{"3", "b_house1_d1"});
                ret.get("b_house1_u1").put(4, new String[]{"3", "b_house1_l"});

                // Влево
                ret.put("b_house1_l1", new HashMap<Integer, String[]>());
                ret.get("b_house1_l1").put(1, new String[]{"3", "b_house1_l"});
                ret.get("b_house1_l1").put(2, new String[]{"3", "b_house1_u"});
                ret.get("b_house1_l1").put(3, new String[]{"3", "b_house1_r"});
                ret.get("b_house1_l1").put(4, new String[]{"3", "b_house1_d1"});

                // Вправо
                ret.put("b_house1_r1", new HashMap<Integer, String[]>());
                ret.get("b_house1_r1").put(1, new String[]{"3", "b_house1_r"});
                ret.get("b_house1_r1").put(2, new String[]{"3", "b_house1_d1"});
                ret.get("b_house1_r1").put(3, new String[]{"3", "b_house1_l"});
                ret.get("b_house1_r1").put(4, new String[]{"3", "b_house1_u"});

            }

            // Арка в доме
            {

                // Вниз
                ret.put("b_house1_d3", new HashMap<Integer, String[]>());
                ret.get("b_house1_d3").put(1, new String[]{"3", "b_house1_d3"});
                ret.get("b_house1_d3").put(2, new String[]{"3", "b_house1_l"});
                ret.get("b_house1_d3").put(3, new String[]{"3", "b_house1_d3"});
                ret.get("b_house1_d3").put(4, new String[]{"3", "b_house1_r"});

                // Вверх
                ret.put("b_house1_u3", new HashMap<Integer, String[]>());
                ret.get("b_house1_u3").put(1, new String[]{"3", "b_house1_d3"});
                ret.get("b_house1_u3").put(2, new String[]{"3", "b_house1_r"});
                ret.get("b_house1_u3").put(3, new String[]{"3", "b_house1_d3"});
                ret.get("b_house1_u3").put(4, new String[]{"3", "b_house1_l"});

                // Влево
                ret.put("b_house1_l3", new HashMap<Integer, String[]>());
                ret.get("b_house1_l3").put(1, new String[]{"3", "b_house1_l"});
                ret.get("b_house1_l3").put(2, new String[]{"3", "b_house1_d3"});
                ret.get("b_house1_l3").put(3, new String[]{"3", "b_house1_r"});
                ret.get("b_house1_l3").put(4, new String[]{"3", "b_house1_d3"});

                // Вправо
                ret.put("b_house1_r3", new HashMap<Integer, String[]>());
                ret.get("b_house1_r3").put(1, new String[]{"3", "b_house1_r"});
                ret.get("b_house1_r3").put(2, new String[]{"3", "b_house1_d3"});
                ret.get("b_house1_r3").put(3, new String[]{"3", "b_house1_l"});
                ret.get("b_house1_r3").put(4, new String[]{"3", "b_house1_d3"});

            }

            // Углы
            {

                // Верх-лево
                ret.put("b_house1_angle_u_l", new HashMap<Integer, String[]>());
                ret.get("b_house1_angle_u_l").put(1, new String[]{"3", "b_house1_angle_u_l"});
                ret.get("b_house1_angle_u_l").put(2, new String[]{"3", "b_house1_angle_u_r"});
                ret.get("b_house1_angle_u_l").put(3, new String[]{"3", "b_house1_angle_d_r"});
                ret.get("b_house1_angle_u_l").put(4, new String[]{"3", "b_house1_angle_d_l"});

                // Верх-право
                ret.put("b_house1_angle_u_r", new HashMap<Integer, String[]>());
                ret.get("b_house1_angle_u_r").put(1, new String[]{"3", "b_house1_angle_u_r"});
                ret.get("b_house1_angle_u_r").put(2, new String[]{"3", "b_house1_angle_d_r"});
                ret.get("b_house1_angle_u_r").put(3, new String[]{"3", "b_house1_angle_d_l"});
                ret.get("b_house1_angle_u_r").put(4, new String[]{"3", "b_house1_angle_u_l"});

                // Низ-лево
                ret.put("b_house1_angle_d_l", new HashMap<Integer, String[]>());
                ret.get("b_house1_angle_d_l").put(1, new String[]{"3", "b_house1_angle_d_l"});
                ret.get("b_house1_angle_d_l").put(2, new String[]{"3", "b_house1_angle_u_l"});
                ret.get("b_house1_angle_d_l").put(3, new String[]{"3", "b_house1_angle_u_r"});
                ret.get("b_house1_angle_d_l").put(4, new String[]{"3", "b_house1_angle_d_r"});

                // Низ-право
                ret.put("b_house1_angle_d_r", new HashMap<Integer, String[]>());
                ret.get("b_house1_angle_d_r").put(1, new String[]{"3", "b_house1_angle_d_r"});
                ret.get("b_house1_angle_d_r").put(2, new String[]{"3", "b_house1_angle_d_l"});
                ret.get("b_house1_angle_d_r").put(3, new String[]{"3", "b_house1_angle_u_l"});
                ret.get("b_house1_angle_d_r").put(4, new String[]{"3", "b_house1_angle_u_r"});

            }

        }

        // Дом из дерева
        {

            // Вниз
            ret.put("s_house1_d", new HashMap<Integer, String[]>());
            ret.get("s_house1_d").put(1, new String[]{"2", "s_house1_d"});
            ret.get("s_house1_d").put(2, new String[]{"2", "s_house1_l"});
            ret.get("s_house1_d").put(3, new String[]{"2", "s_house1_u"});
            ret.get("s_house1_d").put(4, new String[]{"2", "s_house1_r"});

            // Вверх
            ret.put("s_house1_u", new HashMap<Integer, String[]>());
            ret.get("s_house1_u").put(1, new String[]{"2", "s_house1_u"});
            ret.get("s_house1_u").put(2, new String[]{"2", "s_house1_r"});
            ret.get("s_house1_u").put(3, new String[]{"2", "s_house1_d"});
            ret.get("s_house1_u").put(4, new String[]{"2", "s_house1_l"});

            // Влево
            ret.put("s_house1_l", new HashMap<Integer, String[]>());
            ret.get("s_house1_l").put(1, new String[]{"2", "s_house1_l"});
            ret.get("s_house1_l").put(2, new String[]{"2", "s_house1_u"});
            ret.get("s_house1_l").put(3, new String[]{"2", "s_house1_r"});
            ret.get("s_house1_l").put(4, new String[]{"2", "s_house1_d"});

            // Вправо
            ret.put("s_house1_r", new HashMap<Integer, String[]>());
            ret.get("s_house1_r").put(1, new String[]{"2", "s_house1_r"});
            ret.get("s_house1_r").put(2, new String[]{"2", "s_house1_d"});
            ret.get("s_house1_r").put(3, new String[]{"2", "s_house1_l"});
            ret.get("s_house1_r").put(4, new String[]{"2", "s_house1_u"});

        }

        // Дом из сайдинга
        {

            // Вниз
            ret.put("s_house2_d", new HashMap<Integer, String[]>());
            ret.get("s_house2_d").put(1, new String[]{"2", "s_house2_d"});
            ret.get("s_house2_d").put(2, new String[]{"2", "s_house2_l"});
            ret.get("s_house2_d").put(3, new String[]{"2", "s_house2_u"});
            ret.get("s_house2_d").put(4, new String[]{"2", "s_house2_r"});

            // Вверх
            ret.put("s_house2_u", new HashMap<Integer, String[]>());
            ret.get("s_house2_u").put(1, new String[]{"2", "s_house2_u"});
            ret.get("s_house2_u").put(2, new String[]{"2", "s_house2_r"});
            ret.get("s_house2_u").put(3, new String[]{"2", "s_house2_d"});
            ret.get("s_house2_u").put(4, new String[]{"2", "s_house2_l"});

            // Влево
            ret.put("s_house2_l", new HashMap<Integer, String[]>());
            ret.get("s_house2_l").put(1, new String[]{"2", "s_house2_l"});
            ret.get("s_house2_l").put(2, new String[]{"2", "s_house2_u"});
            ret.get("s_house2_l").put(3, new String[]{"2", "s_house2_r"});
            ret.get("s_house2_l").put(4, new String[]{"2", "s_house2_d"});

            // Вправо
            ret.put("s_house2_r", new HashMap<Integer, String[]>());
            ret.get("s_house2_r").put(1, new String[]{"2", "s_house2_r"});
            ret.get("s_house2_r").put(2, new String[]{"2", "s_house2_d"});
            ret.get("s_house2_r").put(3, new String[]{"2", "s_house2_l"});
            ret.get("s_house2_r").put(4, new String[]{"2", "s_house2_u"});

        }

        // Дом из белого кирпича
        {

            // Вниз
            ret.put("s_house3_d", new HashMap<Integer, String[]>());
            ret.get("s_house3_d").put(1, new String[]{"2", "s_house3_d"});
            ret.get("s_house3_d").put(2, new String[]{"2", "s_house3_l"});
            ret.get("s_house3_d").put(3, new String[]{"2", "s_house3_u"});
            ret.get("s_house3_d").put(4, new String[]{"2", "s_house3_r"});

            // Вверх
            ret.put("s_house3_u", new HashMap<Integer, String[]>());
            ret.get("s_house3_u").put(1, new String[]{"2", "s_house3_u"});
            ret.get("s_house3_u").put(2, new String[]{"2", "s_house3_r"});
            ret.get("s_house3_u").put(3, new String[]{"2", "s_house3_d"});
            ret.get("s_house3_u").put(4, new String[]{"2", "s_house3_l"});

            // Влево
            ret.put("s_house3_l", new HashMap<Integer, String[]>());
            ret.get("s_house3_l").put(1, new String[]{"2", "s_house3_l"});
            ret.get("s_house3_l").put(2, new String[]{"2", "s_house3_u"});
            ret.get("s_house3_l").put(3, new String[]{"2", "s_house3_r"});
            ret.get("s_house3_l").put(4, new String[]{"2", "s_house3_d"});

            // Вправо
            ret.put("s_house3_r", new HashMap<Integer, String[]>());
            ret.get("s_house3_r").put(1, new String[]{"2", "s_house3_r"});
            ret.get("s_house3_r").put(2, new String[]{"2", "s_house3_d"});
            ret.get("s_house3_r").put(3, new String[]{"2", "s_house3_l"});
            ret.get("s_house3_r").put(4, new String[]{"2", "s_house3_u"});

        }

        // Дом из красного кирпича
        {

            // Вниз
            ret.put("s_house4_d", new HashMap<Integer, String[]>());
            ret.get("s_house4_d").put(1, new String[]{"2", "s_house4_d"});
            ret.get("s_house4_d").put(2, new String[]{"2", "s_house4_l"});
            ret.get("s_house4_d").put(3, new String[]{"2", "s_house4_u"});
            ret.get("s_house4_d").put(4, new String[]{"2", "s_house4_r"});

            // Вверх
            ret.put("s_house4_u", new HashMap<Integer, String[]>());
            ret.get("s_house4_u").put(1, new String[]{"2", "s_house4_u"});
            ret.get("s_house4_u").put(2, new String[]{"2", "s_house4_r"});
            ret.get("s_house4_u").put(3, new String[]{"2", "s_house4_d"});
            ret.get("s_house4_u").put(4, new String[]{"2", "s_house4_l"});

            // Влево
            ret.put("s_house4_l", new HashMap<Integer, String[]>());
            ret.get("s_house4_l").put(1, new String[]{"2", "s_house4_l"});
            ret.get("s_house4_l").put(2, new String[]{"2", "s_house4_u"});
            ret.get("s_house4_l").put(3, new String[]{"2", "s_house4_r"});
            ret.get("s_house4_l").put(4, new String[]{"2", "s_house4_d"});

            // Вправо
            ret.put("s_house4_r", new HashMap<Integer, String[]>());
            ret.get("s_house4_r").put(1, new String[]{"2", "s_house4_r"});
            ret.get("s_house4_r").put(2, new String[]{"2", "s_house4_d"});
            ret.get("s_house4_r").put(3, new String[]{"2", "s_house4_l"});
            ret.get("s_house4_r").put(4, new String[]{"2", "s_house4_u"});

        }

        // Забор из бетона
        {

            // Прямой фрагмент
            {

                // Горизонтальный
                ret.put("beton_fence_h", new HashMap<Integer, String[]>());
                ret.get("beton_fence_h").put(1, new String[]{"2", "beton_fence_h"});
                ret.get("beton_fence_h").put(2, new String[]{"2", "beton_fence_v"});
                ret.get("beton_fence_h").put(3, new String[]{"2", "beton_fence_h"});
                ret.get("beton_fence_h").put(4, new String[]{"2", "beton_fence_v"});

                // Вертикальный
                ret.put("beton_fence_v", new HashMap<Integer, String[]>());
                ret.get("beton_fence_v").put(1, new String[]{"2", "beton_fence_v"});
                ret.get("beton_fence_v").put(2, new String[]{"2", "beton_fence_h"});
                ret.get("beton_fence_v").put(3, new String[]{"2", "beton_fence_v"});
                ret.get("beton_fence_v").put(4, new String[]{"2", "beton_fence_h"});

            }

            // Одиночный фрагмент
            {

                // Горизонтальный
                ret.put("beton_fence_h_end", new HashMap<Integer, String[]>());
                ret.get("beton_fence_h_end").put(1, new String[]{"2", "beton_fence_h_end"});
                ret.get("beton_fence_h_end").put(2, new String[]{"2", "beton_fence_v_end"});
                ret.get("beton_fence_h_end").put(3, new String[]{"2", "beton_fence_h_end"});
                ret.get("beton_fence_h_end").put(4, new String[]{"2", "beton_fence_v"});

                // Вертикальный
                ret.put("beton_fence_v_end", new HashMap<Integer, String[]>());
                ret.get("beton_fence_v_end").put(1, new String[]{"2", "beton_fence_v_end"});
                ret.get("beton_fence_v_end").put(2, new String[]{"2", "beton_fence_h_end"});
                ret.get("beton_fence_v_end").put(3, new String[]{"2", "beton_fence_v_end"});
                ret.get("beton_fence_v_end").put(4, new String[]{"2", "beton_fence_h_end"});

            }

            // Фрагмент конца забора
            {

                // Слева
                ret.put("beton_fence_h_end_l", new HashMap<Integer, String[]>());
                ret.get("beton_fence_h_end_l").put(1, new String[]{"2", "beton_fence_h_end_l"});
                ret.get("beton_fence_h_end_l").put(2, new String[]{"2", "beton_fence_v_end_u"});
                ret.get("beton_fence_h_end_l").put(3, new String[]{"2", "beton_fence_h_end_r"});
                ret.get("beton_fence_h_end_l").put(4, new String[]{"2", "beton_fence_v_end_d"});

                // Вверху
                ret.put("beton_fence_v_end_u", new HashMap<Integer, String[]>());
                ret.get("beton_fence_v_end_u").put(1, new String[]{"2", "beton_fence_v_end_u"});
                ret.get("beton_fence_v_end_u").put(2, new String[]{"2", "beton_fence_h_end_r"});
                ret.get("beton_fence_v_end_u").put(3, new String[]{"2", "beton_fence_v_end_d"});
                ret.get("beton_fence_v_end_u").put(4, new String[]{"2", "beton_fence_h_end_l"});

                // Справа
                ret.put("beton_fence_h_end_r", new HashMap<Integer, String[]>());
                ret.get("beton_fence_h_end_r").put(1, new String[]{"2", "beton_fence_h_end_r"});
                ret.get("beton_fence_h_end_r").put(2, new String[]{"2", "beton_fence_v_end_d"});
                ret.get("beton_fence_h_end_r").put(3, new String[]{"2", "beton_fence_h_end_l"});
                ret.get("beton_fence_h_end_r").put(4, new String[]{"2", "beton_fence_v_end_u"});

                // Внизу
                ret.put("beton_fence_v_end_d", new HashMap<Integer, String[]>());
                ret.get("beton_fence_v_end_d").put(1, new String[]{"2", "beton_fence_v_end_d"});
                ret.get("beton_fence_v_end_d").put(2, new String[]{"2", "beton_fence_h_end_l"});
                ret.get("beton_fence_v_end_d").put(3, new String[]{"2", "beton_fence_v_end_u"});
                ret.get("beton_fence_v_end_d").put(4, new String[]{"2", "beton_fence_h_end_r"});

            }

            // Углы
            {

                // Верх-лево
                ret.put("beton_fence_angle_u_l", new HashMap<Integer, String[]>());
                ret.get("beton_fence_angle_u_l").put(1, new String[]{"2", "beton_fence_angle_u_l"});
                ret.get("beton_fence_angle_u_l").put(2, new String[]{"2", "beton_fence_angle_u_r"});
                ret.get("beton_fence_angle_u_l").put(3, new String[]{"2", "beton_fence_angle_d_r"});
                ret.get("beton_fence_angle_u_l").put(4, new String[]{"2", "beton_fence_angle_d_l"});

                // Верх-право
                ret.put("beton_fence_angle_u_r", new HashMap<Integer, String[]>());
                ret.get("beton_fence_angle_u_r").put(1, new String[]{"2", "beton_fence_angle_u_r"});
                ret.get("beton_fence_angle_u_r").put(2, new String[]{"2", "beton_fence_angle_d_r"});
                ret.get("beton_fence_angle_u_r").put(3, new String[]{"2", "beton_fence_angle_d_l"});
                ret.get("beton_fence_angle_u_r").put(4, new String[]{"2", "beton_fence_angle_u_l"});

                // Низ-лево
                ret.put("beton_fence_angle_d_l", new HashMap<Integer, String[]>());
                ret.get("beton_fence_angle_d_l").put(1, new String[]{"2", "beton_fence_angle_d_l"});
                ret.get("beton_fence_angle_d_l").put(2, new String[]{"2", "beton_fence_angle_u_l"});
                ret.get("beton_fence_angle_d_l").put(3, new String[]{"2", "beton_fence_angle_u_r"});
                ret.get("beton_fence_angle_d_l").put(4, new String[]{"2", "beton_fence_angle_d_r"});

                // Низ-право
                ret.put("beton_fence_angle_d_r", new HashMap<Integer, String[]>());
                ret.get("beton_fence_angle_d_r").put(1, new String[]{"2", "beton_fence_angle_d_r"});
                ret.get("beton_fence_angle_d_r").put(2, new String[]{"2", "beton_fence_angle_d_l"});
                ret.get("beton_fence_angle_d_r").put(3, new String[]{"2", "beton_fence_angle_u_l"});
                ret.get("beton_fence_angle_d_r").put(4, new String[]{"2", "beton_fence_angle_u_r"});

            }

            // Тройники
            {

                // Нижний
                ret.put("beton_fence_trio_d", new HashMap<Integer, String[]>());
                ret.get("beton_fence_trio_d").put(1, new String[]{"2", "beton_fence_trio_d"});
                ret.get("beton_fence_trio_d").put(2, new String[]{"2", "beton_fence_trio_l"});
                ret.get("beton_fence_trio_d").put(3, new String[]{"2", "beton_fence_trio_u"});
                ret.get("beton_fence_trio_d").put(4, new String[]{"2", "beton_fence_trio_r"});

                // Верхний
                ret.put("beton_fence_trio_u", new HashMap<Integer, String[]>());
                ret.get("beton_fence_trio_u").put(1, new String[]{"2", "beton_fence_trio_u"});
                ret.get("beton_fence_trio_u").put(2, new String[]{"2", "beton_fence_trio_r"});
                ret.get("beton_fence_trio_u").put(3, new String[]{"2", "beton_fence_trio_d"});
                ret.get("beton_fence_trio_u").put(4, new String[]{"2", "beton_fence_trio_l"});

                // Правый
                ret.put("beton_fence_trio_r", new HashMap<Integer, String[]>());
                ret.get("beton_fence_trio_r").put(1, new String[]{"2", "beton_fence_trio_r"});
                ret.get("beton_fence_trio_r").put(2, new String[]{"2", "beton_fence_trio_d"});
                ret.get("beton_fence_trio_r").put(3, new String[]{"2", "beton_fence_trio_l"});
                ret.get("beton_fence_trio_r").put(4, new String[]{"2", "beton_fence_trio_u"});

                // Левый
                ret.put("beton_fence_trio_l", new HashMap<Integer, String[]>());
                ret.get("beton_fence_trio_l").put(1, new String[]{"2", "beton_fence_trio_l"});
                ret.get("beton_fence_trio_l").put(2, new String[]{"2", "beton_fence_trio_u"});
                ret.get("beton_fence_trio_l").put(3, new String[]{"2", "beton_fence_trio_r"});
                ret.get("beton_fence_trio_l").put(4, new String[]{"2", "beton_fence_trio_d"});

            }

            // Перекрестие
            {

                ret.put("beton_fence_quad", new HashMap<Integer, String[]>());
                ret.get("beton_fence_quad").put(1, new String[]{"2", "beton_fence_quad"});
                ret.get("beton_fence_quad").put(2, new String[]{"2", "beton_fence_quad"});
                ret.get("beton_fence_quad").put(3, new String[]{"2", "beton_fence_quad"});
                ret.get("beton_fence_quad").put(4, new String[]{"2", "beton_fence_quad"});

            }

        }

        // Забор из белого кирпича
        {

            // Прямой фрагмент
            {

                // Горизонтальный
                ret.put("white_fence_h", new HashMap<Integer, String[]>());
                ret.get("white_fence_h").put(1, new String[]{"2", "white_fence_h"});
                ret.get("white_fence_h").put(2, new String[]{"2", "white_fence_v"});
                ret.get("white_fence_h").put(3, new String[]{"2", "white_fence_h"});
                ret.get("white_fence_h").put(4, new String[]{"2", "white_fence_v"});

                // Вертикальный
                ret.put("white_fence_v", new HashMap<Integer, String[]>());
                ret.get("white_fence_v").put(1, new String[]{"2", "white_fence_v"});
                ret.get("white_fence_v").put(2, new String[]{"2", "white_fence_h"});
                ret.get("white_fence_v").put(3, new String[]{"2", "white_fence_v"});
                ret.get("white_fence_v").put(4, new String[]{"2", "white_fence_h"});

            }

            // Одиночный фрагмент
            {

                // Горизонтальный
                ret.put("white_fence_h_end", new HashMap<Integer, String[]>());
                ret.get("white_fence_h_end").put(1, new String[]{"2", "white_fence_h_end"});
                ret.get("white_fence_h_end").put(2, new String[]{"2", "white_fence_v_end"});
                ret.get("white_fence_h_end").put(3, new String[]{"2", "white_fence_h_end"});
                ret.get("white_fence_h_end").put(4, new String[]{"2", "white_fence_v"});

                // Вертикальный
                ret.put("white_fence_v_end", new HashMap<Integer, String[]>());
                ret.get("white_fence_v_end").put(1, new String[]{"2", "white_fence_v_end"});
                ret.get("white_fence_v_end").put(2, new String[]{"2", "white_fence_h_end"});
                ret.get("white_fence_v_end").put(3, new String[]{"2", "white_fence_v_end"});
                ret.get("white_fence_v_end").put(4, new String[]{"2", "white_fence_h_end"});

            }

            // Фрагмент конца забора
            {

                // Слева
                ret.put("white_fence_h_end_l", new HashMap<Integer, String[]>());
                ret.get("white_fence_h_end_l").put(1, new String[]{"2", "white_fence_h_end_l"});
                ret.get("white_fence_h_end_l").put(2, new String[]{"2", "white_fence_v_end_u"});
                ret.get("white_fence_h_end_l").put(3, new String[]{"2", "white_fence_h_end_r"});
                ret.get("white_fence_h_end_l").put(4, new String[]{"2", "white_fence_v_end_d"});

                // Вверху
                ret.put("white_fence_v_end_u", new HashMap<Integer, String[]>());
                ret.get("white_fence_v_end_u").put(1, new String[]{"2", "white_fence_v_end_u"});
                ret.get("white_fence_v_end_u").put(2, new String[]{"2", "white_fence_h_end_r"});
                ret.get("white_fence_v_end_u").put(3, new String[]{"2", "white_fence_v_end_d"});
                ret.get("white_fence_v_end_u").put(4, new String[]{"2", "white_fence_h_end_l"});

                // Справа
                ret.put("white_fence_h_end_r", new HashMap<Integer, String[]>());
                ret.get("white_fence_h_end_r").put(1, new String[]{"2", "white_fence_h_end_r"});
                ret.get("white_fence_h_end_r").put(2, new String[]{"2", "white_fence_v_end_d"});
                ret.get("white_fence_h_end_r").put(3, new String[]{"2", "white_fence_h_end_l"});
                ret.get("white_fence_h_end_r").put(4, new String[]{"2", "white_fence_v_end_u"});

                // Внизу
                ret.put("white_fence_v_end_d", new HashMap<Integer, String[]>());
                ret.get("white_fence_v_end_d").put(1, new String[]{"2", "white_fence_v_end_d"});
                ret.get("white_fence_v_end_d").put(2, new String[]{"2", "white_fence_h_end_l"});
                ret.get("white_fence_v_end_d").put(3, new String[]{"2", "white_fence_v_end_u"});
                ret.get("white_fence_v_end_d").put(4, new String[]{"2", "white_fence_h_end_r"});

            }

            // Углы
            {

                // Верх-лево
                ret.put("white_fence_angle_u_l", new HashMap<Integer, String[]>());
                ret.get("white_fence_angle_u_l").put(1, new String[]{"2", "white_fence_angle_u_l"});
                ret.get("white_fence_angle_u_l").put(2, new String[]{"2", "white_fence_angle_u_r"});
                ret.get("white_fence_angle_u_l").put(3, new String[]{"2", "white_fence_angle_d_r"});
                ret.get("white_fence_angle_u_l").put(4, new String[]{"2", "white_fence_angle_d_l"});

                // Верх-право
                ret.put("white_fence_angle_u_r", new HashMap<Integer, String[]>());
                ret.get("white_fence_angle_u_r").put(1, new String[]{"2", "white_fence_angle_u_r"});
                ret.get("white_fence_angle_u_r").put(2, new String[]{"2", "white_fence_angle_d_r"});
                ret.get("white_fence_angle_u_r").put(3, new String[]{"2", "white_fence_angle_d_l"});
                ret.get("white_fence_angle_u_r").put(4, new String[]{"2", "white_fence_angle_u_l"});

                // Низ-лево
                ret.put("white_fence_angle_d_l", new HashMap<Integer, String[]>());
                ret.get("white_fence_angle_d_l").put(1, new String[]{"2", "white_fence_angle_d_l"});
                ret.get("white_fence_angle_d_l").put(2, new String[]{"2", "white_fence_angle_u_l"});
                ret.get("white_fence_angle_d_l").put(3, new String[]{"2", "white_fence_angle_u_r"});
                ret.get("white_fence_angle_d_l").put(4, new String[]{"2", "white_fence_angle_d_r"});

                // Низ-право
                ret.put("white_fence_angle_d_r", new HashMap<Integer, String[]>());
                ret.get("white_fence_angle_d_r").put(1, new String[]{"2", "white_fence_angle_d_r"});
                ret.get("white_fence_angle_d_r").put(2, new String[]{"2", "white_fence_angle_d_l"});
                ret.get("white_fence_angle_d_r").put(3, new String[]{"2", "white_fence_angle_u_l"});
                ret.get("white_fence_angle_d_r").put(4, new String[]{"2", "white_fence_angle_u_r"});

            }

            // Тройники
            {

                // Нижний
                ret.put("white_fence_trio_d", new HashMap<Integer, String[]>());
                ret.get("white_fence_trio_d").put(1, new String[]{"2", "white_fence_trio_d"});
                ret.get("white_fence_trio_d").put(2, new String[]{"2", "white_fence_trio_l"});
                ret.get("white_fence_trio_d").put(3, new String[]{"2", "white_fence_trio_u"});
                ret.get("white_fence_trio_d").put(4, new String[]{"2", "white_fence_trio_r"});

                // Верхний
                ret.put("white_fence_trio_u", new HashMap<Integer, String[]>());
                ret.get("white_fence_trio_u").put(1, new String[]{"2", "white_fence_trio_u"});
                ret.get("white_fence_trio_u").put(2, new String[]{"2", "white_fence_trio_r"});
                ret.get("white_fence_trio_u").put(3, new String[]{"2", "white_fence_trio_d"});
                ret.get("white_fence_trio_u").put(4, new String[]{"2", "white_fence_trio_l"});

                // Правый
                ret.put("white_fence_trio_r", new HashMap<Integer, String[]>());
                ret.get("white_fence_trio_r").put(1, new String[]{"2", "white_fence_trio_r"});
                ret.get("white_fence_trio_r").put(2, new String[]{"2", "white_fence_trio_d"});
                ret.get("white_fence_trio_r").put(3, new String[]{"2", "white_fence_trio_l"});
                ret.get("white_fence_trio_r").put(4, new String[]{"2", "white_fence_trio_u"});

                // Левый
                ret.put("white_fence_trio_l", new HashMap<Integer, String[]>());
                ret.get("white_fence_trio_l").put(1, new String[]{"2", "white_fence_trio_l"});
                ret.get("white_fence_trio_l").put(2, new String[]{"2", "white_fence_trio_u"});
                ret.get("white_fence_trio_l").put(3, new String[]{"2", "white_fence_trio_r"});
                ret.get("white_fence_trio_l").put(4, new String[]{"2", "white_fence_trio_d"});

            }

            // Перекрестие
            {

                ret.put("white_fence_quad", new HashMap<Integer, String[]>());
                ret.get("white_fence_quad").put(1, new String[]{"2", "white_fence_quad"});
                ret.get("white_fence_quad").put(2, new String[]{"2", "white_fence_quad"});
                ret.get("white_fence_quad").put(3, new String[]{"2", "white_fence_quad"});
                ret.get("white_fence_quad").put(4, new String[]{"2", "white_fence_quad"});

            }

        }

        // Забор из красного кирпича
        {

            // Прямой фрагмент
            {

                // Горизонтальный
                ret.put("red_fence_h", new HashMap<Integer, String[]>());
                ret.get("red_fence_h").put(1, new String[]{"2", "red_fence_h"});
                ret.get("red_fence_h").put(2, new String[]{"2", "red_fence_v"});
                ret.get("red_fence_h").put(3, new String[]{"2", "red_fence_h"});
                ret.get("red_fence_h").put(4, new String[]{"2", "red_fence_v"});

                // Вертикальный
                ret.put("red_fence_v", new HashMap<Integer, String[]>());
                ret.get("red_fence_v").put(1, new String[]{"2", "red_fence_v"});
                ret.get("red_fence_v").put(2, new String[]{"2", "red_fence_h"});
                ret.get("red_fence_v").put(3, new String[]{"2", "red_fence_v"});
                ret.get("red_fence_v").put(4, new String[]{"2", "red_fence_h"});

            }

            // Одиночный фрагмент
            {

                // Горизонтальный
                ret.put("red_fence_h_end", new HashMap<Integer, String[]>());
                ret.get("red_fence_h_end").put(1, new String[]{"2", "red_fence_h_end"});
                ret.get("red_fence_h_end").put(2, new String[]{"2", "red_fence_v_end"});
                ret.get("red_fence_h_end").put(3, new String[]{"2", "red_fence_h_end"});
                ret.get("red_fence_h_end").put(4, new String[]{"2", "red_fence_v"});

                // Вертикальный
                ret.put("red_fence_v_end", new HashMap<Integer, String[]>());
                ret.get("red_fence_v_end").put(1, new String[]{"2", "red_fence_v_end"});
                ret.get("red_fence_v_end").put(2, new String[]{"2", "red_fence_h_end"});
                ret.get("red_fence_v_end").put(3, new String[]{"2", "red_fence_v_end"});
                ret.get("red_fence_v_end").put(4, new String[]{"2", "red_fence_h_end"});

            }

            // Фрагмент конца забора
            {

                // Слева
                ret.put("red_fence_h_end_l", new HashMap<Integer, String[]>());
                ret.get("red_fence_h_end_l").put(1, new String[]{"2", "red_fence_h_end_l"});
                ret.get("red_fence_h_end_l").put(2, new String[]{"2", "red_fence_v_end_u"});
                ret.get("red_fence_h_end_l").put(3, new String[]{"2", "red_fence_h_end_r"});
                ret.get("red_fence_h_end_l").put(4, new String[]{"2", "red_fence_v_end_d"});

                // Вверху
                ret.put("red_fence_v_end_u", new HashMap<Integer, String[]>());
                ret.get("red_fence_v_end_u").put(1, new String[]{"2", "red_fence_v_end_u"});
                ret.get("red_fence_v_end_u").put(2, new String[]{"2", "red_fence_h_end_r"});
                ret.get("red_fence_v_end_u").put(3, new String[]{"2", "red_fence_v_end_d"});
                ret.get("red_fence_v_end_u").put(4, new String[]{"2", "red_fence_h_end_l"});

                // Справа
                ret.put("red_fence_h_end_r", new HashMap<Integer, String[]>());
                ret.get("red_fence_h_end_r").put(1, new String[]{"2", "red_fence_h_end_r"});
                ret.get("red_fence_h_end_r").put(2, new String[]{"2", "red_fence_v_end_d"});
                ret.get("red_fence_h_end_r").put(3, new String[]{"2", "red_fence_h_end_l"});
                ret.get("red_fence_h_end_r").put(4, new String[]{"2", "red_fence_v_end_u"});

                // Внизу
                ret.put("red_fence_v_end_d", new HashMap<Integer, String[]>());
                ret.get("red_fence_v_end_d").put(1, new String[]{"2", "red_fence_v_end_d"});
                ret.get("red_fence_v_end_d").put(2, new String[]{"2", "red_fence_h_end_l"});
                ret.get("red_fence_v_end_d").put(3, new String[]{"2", "red_fence_v_end_u"});
                ret.get("red_fence_v_end_d").put(4, new String[]{"2", "red_fence_h_end_r"});

            }

            // Углы
            {

                // Верх-лево
                ret.put("red_fence_angle_u_l", new HashMap<Integer, String[]>());
                ret.get("red_fence_angle_u_l").put(1, new String[]{"2", "red_fence_angle_u_l"});
                ret.get("red_fence_angle_u_l").put(2, new String[]{"2", "red_fence_angle_u_r"});
                ret.get("red_fence_angle_u_l").put(3, new String[]{"2", "red_fence_angle_d_r"});
                ret.get("red_fence_angle_u_l").put(4, new String[]{"2", "red_fence_angle_d_l"});

                // Верх-право
                ret.put("red_fence_angle_u_r", new HashMap<Integer, String[]>());
                ret.get("red_fence_angle_u_r").put(1, new String[]{"2", "red_fence_angle_u_r"});
                ret.get("red_fence_angle_u_r").put(2, new String[]{"2", "red_fence_angle_d_r"});
                ret.get("red_fence_angle_u_r").put(3, new String[]{"2", "red_fence_angle_d_l"});
                ret.get("red_fence_angle_u_r").put(4, new String[]{"2", "red_fence_angle_u_l"});

                // Низ-лево
                ret.put("red_fence_angle_d_l", new HashMap<Integer, String[]>());
                ret.get("red_fence_angle_d_l").put(1, new String[]{"2", "red_fence_angle_d_l"});
                ret.get("red_fence_angle_d_l").put(2, new String[]{"2", "red_fence_angle_u_l"});
                ret.get("red_fence_angle_d_l").put(3, new String[]{"2", "red_fence_angle_u_r"});
                ret.get("red_fence_angle_d_l").put(4, new String[]{"2", "red_fence_angle_d_r"});

                // Низ-право
                ret.put("red_fence_angle_d_r", new HashMap<Integer, String[]>());
                ret.get("red_fence_angle_d_r").put(1, new String[]{"2", "red_fence_angle_d_r"});
                ret.get("red_fence_angle_d_r").put(2, new String[]{"2", "red_fence_angle_d_l"});
                ret.get("red_fence_angle_d_r").put(3, new String[]{"2", "red_fence_angle_u_l"});
                ret.get("red_fence_angle_d_r").put(4, new String[]{"2", "red_fence_angle_u_r"});

            }

            // Тройники
            {

                // Нижний
                ret.put("red_fence_trio_d", new HashMap<Integer, String[]>());
                ret.get("red_fence_trio_d").put(1, new String[]{"2", "red_fence_trio_d"});
                ret.get("red_fence_trio_d").put(2, new String[]{"2", "red_fence_trio_l"});
                ret.get("red_fence_trio_d").put(3, new String[]{"2", "red_fence_trio_u"});
                ret.get("red_fence_trio_d").put(4, new String[]{"2", "red_fence_trio_r"});

                // Верхний
                ret.put("red_fence_trio_u", new HashMap<Integer, String[]>());
                ret.get("red_fence_trio_u").put(1, new String[]{"2", "red_fence_trio_u"});
                ret.get("red_fence_trio_u").put(2, new String[]{"2", "red_fence_trio_r"});
                ret.get("red_fence_trio_u").put(3, new String[]{"2", "red_fence_trio_d"});
                ret.get("red_fence_trio_u").put(4, new String[]{"2", "red_fence_trio_l"});

                // Правый
                ret.put("red_fence_trio_r", new HashMap<Integer, String[]>());
                ret.get("red_fence_trio_r").put(1, new String[]{"2", "red_fence_trio_r"});
                ret.get("red_fence_trio_r").put(2, new String[]{"2", "red_fence_trio_d"});
                ret.get("red_fence_trio_r").put(3, new String[]{"2", "red_fence_trio_l"});
                ret.get("red_fence_trio_r").put(4, new String[]{"2", "red_fence_trio_u"});

                // Левый
                ret.put("red_fence_trio_l", new HashMap<Integer, String[]>());
                ret.get("red_fence_trio_l").put(1, new String[]{"2", "red_fence_trio_l"});
                ret.get("red_fence_trio_l").put(2, new String[]{"2", "red_fence_trio_u"});
                ret.get("red_fence_trio_l").put(3, new String[]{"2", "red_fence_trio_r"});
                ret.get("red_fence_trio_l").put(4, new String[]{"2", "red_fence_trio_d"});

            }

            // Перекрестие
            {

                ret.put("red_fence_quad", new HashMap<Integer, String[]>());
                ret.get("red_fence_quad").put(1, new String[]{"2", "red_fence_quad"});
                ret.get("red_fence_quad").put(2, new String[]{"2", "red_fence_quad"});
                ret.get("red_fence_quad").put(3, new String[]{"2", "red_fence_quad"});
                ret.get("red_fence_quad").put(4, new String[]{"2", "red_fence_quad"});

            }

        }

        // Забор из сетки Рабица
        {

            // Прямой фрагмент
            {

                // Горизонтальный
                ret.put("rabits_fence_h", new HashMap<Integer, String[]>());
                ret.get("rabits_fence_h").put(1, new String[]{"2", "rabits_fence_h"});
                ret.get("rabits_fence_h").put(2, new String[]{"2", "rabits_fence_v"});
                ret.get("rabits_fence_h").put(3, new String[]{"2", "rabits_fence_h"});
                ret.get("rabits_fence_h").put(4, new String[]{"2", "rabits_fence_v"});

                // Вертикальный
                ret.put("rabits_fence_v", new HashMap<Integer, String[]>());
                ret.get("rabits_fence_v").put(1, new String[]{"2", "rabits_fence_v"});
                ret.get("rabits_fence_v").put(2, new String[]{"2", "rabits_fence_h"});
                ret.get("rabits_fence_v").put(3, new String[]{"2", "rabits_fence_v"});
                ret.get("rabits_fence_v").put(4, new String[]{"2", "rabits_fence_h"});

            }

            // Одиночный фрагмент
            {

                // Горизонтальный
                ret.put("rabits_fence_h_end", new HashMap<Integer, String[]>());
                ret.get("rabits_fence_h_end").put(1, new String[]{"2", "rabits_fence_h_end"});
                ret.get("rabits_fence_h_end").put(2, new String[]{"2", "rabits_fence_v_end"});
                ret.get("rabits_fence_h_end").put(3, new String[]{"2", "rabits_fence_h_end"});
                ret.get("rabits_fence_h_end").put(4, new String[]{"2", "rabits_fence_v"});

                // Вертикальный
                ret.put("rabits_fence_v_end", new HashMap<Integer, String[]>());
                ret.get("rabits_fence_v_end").put(1, new String[]{"2", "rabits_fence_v_end"});
                ret.get("rabits_fence_v_end").put(2, new String[]{"2", "rabits_fence_h_end"});
                ret.get("rabits_fence_v_end").put(3, new String[]{"2", "rabits_fence_v_end"});
                ret.get("rabits_fence_v_end").put(4, new String[]{"2", "rabits_fence_h_end"});

            }

            // Фрагмент конца забора
            {

                // Слева
                ret.put("rabits_fence_h_end_l", new HashMap<Integer, String[]>());
                ret.get("rabits_fence_h_end_l").put(1, new String[]{"2", "rabits_fence_h_end_l"});
                ret.get("rabits_fence_h_end_l").put(2, new String[]{"2", "rabits_fence_v_end_u"});
                ret.get("rabits_fence_h_end_l").put(3, new String[]{"2", "rabits_fence_h_end_r"});
                ret.get("rabits_fence_h_end_l").put(4, new String[]{"2", "rabits_fence_v_end_d"});

                // Вверху
                ret.put("rabits_fence_v_end_u", new HashMap<Integer, String[]>());
                ret.get("rabits_fence_v_end_u").put(1, new String[]{"2", "rabits_fence_v_end_u"});
                ret.get("rabits_fence_v_end_u").put(2, new String[]{"2", "rabits_fence_h_end_r"});
                ret.get("rabits_fence_v_end_u").put(3, new String[]{"2", "rabits_fence_v_end_d"});
                ret.get("rabits_fence_v_end_u").put(4, new String[]{"2", "rabits_fence_h_end_l"});

                // Справа
                ret.put("rabits_fence_h_end_r", new HashMap<Integer, String[]>());
                ret.get("rabits_fence_h_end_r").put(1, new String[]{"2", "rabits_fence_h_end_r"});
                ret.get("rabits_fence_h_end_r").put(2, new String[]{"2", "rabits_fence_v_end_d"});
                ret.get("rabits_fence_h_end_r").put(3, new String[]{"2", "rabits_fence_h_end_l"});
                ret.get("rabits_fence_h_end_r").put(4, new String[]{"2", "rabits_fence_v_end_u"});

                // Внизу
                ret.put("rabits_fence_v_end_d", new HashMap<Integer, String[]>());
                ret.get("rabits_fence_v_end_d").put(1, new String[]{"2", "rabits_fence_v_end_d"});
                ret.get("rabits_fence_v_end_d").put(2, new String[]{"2", "rabits_fence_h_end_l"});
                ret.get("rabits_fence_v_end_d").put(3, new String[]{"2", "rabits_fence_v_end_u"});
                ret.get("rabits_fence_v_end_d").put(4, new String[]{"2", "rabits_fence_h_end_r"});

            }

            // Углы
            {

                // Верх-лево
                ret.put("rabits_fence_angle_u_l", new HashMap<Integer, String[]>());
                ret.get("rabits_fence_angle_u_l").put(1, new String[]{"2", "rabits_fence_angle_u_l"});
                ret.get("rabits_fence_angle_u_l").put(2, new String[]{"2", "rabits_fence_angle_u_r"});
                ret.get("rabits_fence_angle_u_l").put(3, new String[]{"2", "rabits_fence_angle_d_r"});
                ret.get("rabits_fence_angle_u_l").put(4, new String[]{"2", "rabits_fence_angle_d_l"});

                // Верх-право
                ret.put("rabits_fence_angle_u_r", new HashMap<Integer, String[]>());
                ret.get("rabits_fence_angle_u_r").put(1, new String[]{"2", "rabits_fence_angle_u_r"});
                ret.get("rabits_fence_angle_u_r").put(2, new String[]{"2", "rabits_fence_angle_d_r"});
                ret.get("rabits_fence_angle_u_r").put(3, new String[]{"2", "rabits_fence_angle_d_l"});
                ret.get("rabits_fence_angle_u_r").put(4, new String[]{"2", "rabits_fence_angle_u_l"});

                // Низ-лево
                ret.put("rabits_fence_angle_d_l", new HashMap<Integer, String[]>());
                ret.get("rabits_fence_angle_d_l").put(1, new String[]{"2", "rabits_fence_angle_d_l"});
                ret.get("rabits_fence_angle_d_l").put(2, new String[]{"2", "rabits_fence_angle_u_l"});
                ret.get("rabits_fence_angle_d_l").put(3, new String[]{"2", "rabits_fence_angle_u_r"});
                ret.get("rabits_fence_angle_d_l").put(4, new String[]{"2", "rabits_fence_angle_d_r"});

                // Низ-право
                ret.put("rabits_fence_angle_d_r", new HashMap<Integer, String[]>());
                ret.get("rabits_fence_angle_d_r").put(1, new String[]{"2", "rabits_fence_angle_d_r"});
                ret.get("rabits_fence_angle_d_r").put(2, new String[]{"2", "rabits_fence_angle_d_l"});
                ret.get("rabits_fence_angle_d_r").put(3, new String[]{"2", "rabits_fence_angle_u_l"});
                ret.get("rabits_fence_angle_d_r").put(4, new String[]{"2", "rabits_fence_angle_u_r"});

            }

            // Тройники
            {

                // Нижний
                ret.put("rabits_fence_trio_d", new HashMap<Integer, String[]>());
                ret.get("rabits_fence_trio_d").put(1, new String[]{"2", "rabits_fence_trio_d"});
                ret.get("rabits_fence_trio_d").put(2, new String[]{"2", "rabits_fence_trio_l"});
                ret.get("rabits_fence_trio_d").put(3, new String[]{"2", "rabits_fence_trio_u"});
                ret.get("rabits_fence_trio_d").put(4, new String[]{"2", "rabits_fence_trio_r"});

                // Верхний
                ret.put("rabits_fence_trio_u", new HashMap<Integer, String[]>());
                ret.get("rabits_fence_trio_u").put(1, new String[]{"2", "rabits_fence_trio_u"});
                ret.get("rabits_fence_trio_u").put(2, new String[]{"2", "rabits_fence_trio_r"});
                ret.get("rabits_fence_trio_u").put(3, new String[]{"2", "rabits_fence_trio_d"});
                ret.get("rabits_fence_trio_u").put(4, new String[]{"2", "rabits_fence_trio_l"});

                // Правый
                ret.put("rabits_fence_trio_r", new HashMap<Integer, String[]>());
                ret.get("rabits_fence_trio_r").put(1, new String[]{"2", "rabits_fence_trio_r"});
                ret.get("rabits_fence_trio_r").put(2, new String[]{"2", "rabits_fence_trio_d"});
                ret.get("rabits_fence_trio_r").put(3, new String[]{"2", "rabits_fence_trio_l"});
                ret.get("rabits_fence_trio_r").put(4, new String[]{"2", "rabits_fence_trio_u"});

                // Левый
                ret.put("rabits_fence_trio_l", new HashMap<Integer, String[]>());
                ret.get("rabits_fence_trio_l").put(1, new String[]{"2", "rabits_fence_trio_l"});
                ret.get("rabits_fence_trio_l").put(2, new String[]{"2", "rabits_fence_trio_u"});
                ret.get("rabits_fence_trio_l").put(3, new String[]{"2", "rabits_fence_trio_r"});
                ret.get("rabits_fence_trio_l").put(4, new String[]{"2", "rabits_fence_trio_d"});

            }

            // Перекрестие
            {

                ret.put("rabits_fence_quad", new HashMap<Integer, String[]>());
                ret.get("rabits_fence_quad").put(1, new String[]{"2", "rabits_fence_quad"});
                ret.get("rabits_fence_quad").put(2, new String[]{"2", "rabits_fence_quad"});
                ret.get("rabits_fence_quad").put(3, new String[]{"2", "rabits_fence_quad"});
                ret.get("rabits_fence_quad").put(4, new String[]{"2", "rabits_fence_quad"});

            }

        }

        // Замок (1 этаж и башни)
        {

            // Центральный элемент
            {

                // Вниз
                ret.put("castle1_d", new HashMap<Integer, String[]>());
                ret.get("castle1_d").put(1, new String[]{"3", "castle1"});
                ret.get("castle1_d").put(2, new String[]{"3", "castle1"});
                ret.get("castle1_d").put(3, new String[]{"3", "castle1"});
                ret.get("castle1_d").put(4, new String[]{"3", "castle1"});

                // Вверх
                ret.put("castle1_u", new HashMap<Integer, String[]>());
                ret.get("castle1_u").put(1, new String[]{"3", "castle1"});
                ret.get("castle1_u").put(2, new String[]{"3", "castle1"});
                ret.get("castle1_u").put(3, new String[]{"3", "castle1"});
                ret.get("castle1_u").put(4, new String[]{"3", "castle1"});

                // Влево
                ret.put("castle1_l", new HashMap<Integer, String[]>());
                ret.get("castle1_l").put(1, new String[]{"3", "castle1"});
                ret.get("castle1_l").put(2, new String[]{"3", "castle1"});
                ret.get("castle1_l").put(3, new String[]{"3", "castle1"});
                ret.get("castle1_l").put(4, new String[]{"3", "castle1"});

                // Вправо
                ret.put("castle1_r", new HashMap<Integer, String[]>());
                ret.get("castle1_r").put(1, new String[]{"3", "castle1"});
                ret.get("castle1_r").put(2, new String[]{"3", "castle1"});
                ret.get("castle1_r").put(3, new String[]{"3", "castle1"});
                ret.get("castle1_r").put(4, new String[]{"3", "castle1"});

            }

            // Граница без окон
            {

                // Вниз
                ret.put("castle1_border_d", new HashMap<Integer, String[]>());
                ret.get("castle1_border_d").put(1, new String[]{"3", "castle1_border_d"});
                ret.get("castle1_border_d").put(2, new String[]{"3", "castle1_border_l"});
                ret.get("castle1_border_d").put(3, new String[]{"3", "castle1_border_u"});
                ret.get("castle1_border_d").put(4, new String[]{"3", "castle1_border_r"});

                // Вверх
                ret.put("castle1_border_u", new HashMap<Integer, String[]>());
                ret.get("castle1_border_u").put(1, new String[]{"3", "castle1_border_u"});
                ret.get("castle1_border_u").put(2, new String[]{"3", "castle1_border_r"});
                ret.get("castle1_border_u").put(3, new String[]{"3", "castle1_border_d"});
                ret.get("castle1_border_u").put(4, new String[]{"3", "castle1_border_l"});

                // Влево
                ret.put("castle1_border_l", new HashMap<Integer, String[]>());
                ret.get("castle1_border_l").put(1, new String[]{"3", "castle1_border_l"});
                ret.get("castle1_border_l").put(2, new String[]{"3", "castle1_border_u"});
                ret.get("castle1_border_l").put(3, new String[]{"3", "castle1_border_r"});
                ret.get("castle1_border_l").put(4, new String[]{"3", "castle1_border_d"});

                // Вправо
                ret.put("castle1_border_r", new HashMap<Integer, String[]>());
                ret.get("castle1_border_r").put(1, new String[]{"3", "castle1_border_r"});
                ret.get("castle1_border_r").put(2, new String[]{"3", "castle1_border_d"});
                ret.get("castle1_border_r").put(3, new String[]{"3", "castle1_border_l"});
                ret.get("castle1_border_r").put(4, new String[]{"3", "castle1_border_u"});

            }

            // Граница с окном
            {

                // Вниз
                ret.put("castle1_border_win_d", new HashMap<Integer, String[]>());
                ret.get("castle1_border_win_d").put(1, new String[]{"3", "castle1_border_d"});
                ret.get("castle1_border_win_d").put(2, new String[]{"3", "castle1_border_l"});
                ret.get("castle1_border_win_d").put(3, new String[]{"3", "castle1_border_win_u"});
                ret.get("castle1_border_win_d").put(4, new String[]{"3", "castle1_border_r"});

                // Вверх
                ret.put("castle1_border_win_u", new HashMap<Integer, String[]>());
                ret.get("castle1_border_win_u").put(1, new String[]{"3", "castle1_border_win_u"});
                ret.get("castle1_border_win_u").put(2, new String[]{"3", "castle1_border_r"});
                ret.get("castle1_border_win_u").put(3, new String[]{"3", "castle1_border_d"});
                ret.get("castle1_border_win_u").put(4, new String[]{"3", "castle1_border_l"});

                // Влево
                ret.put("castle1_border_win_l", new HashMap<Integer, String[]>());
                ret.get("castle1_border_win_l").put(1, new String[]{"3", "castle1_border_l"});
                ret.get("castle1_border_win_l").put(2, new String[]{"3", "castle1_border_win_u"});
                ret.get("castle1_border_win_l").put(3, new String[]{"3", "castle1_border_r"});
                ret.get("castle1_border_win_l").put(4, new String[]{"3", "castle1_border_d"});

                // Вправо
                ret.put("castle1_border_win_r", new HashMap<Integer, String[]>());
                ret.get("castle1_border_win_r").put(1, new String[]{"3", "castle1_border_r"});
                ret.get("castle1_border_win_r").put(2, new String[]{"3", "castle1_border_d"});
                ret.get("castle1_border_win_r").put(3, new String[]{"3", "castle1_border_l"});
                ret.get("castle1_border_win_r").put(4, new String[]{"3", "castle1_border_win_u"});

            }

            // Граница с аркой
            {

                // Вниз
                ret.put("castle1_border_ext_d", new HashMap<Integer, String[]>());
                ret.get("castle1_border_ext_d").put(1, new String[]{"3", "castle1_border_d"});
                ret.get("castle1_border_ext_d").put(2, new String[]{"3", "castle1_border_l"});
                ret.get("castle1_border_ext_d").put(3, new String[]{"3", "castle1_border_ext_u"});
                ret.get("castle1_border_ext_d").put(4, new String[]{"3", "castle1_border_r"});

                // Вверх
                ret.put("castle1_border_ext_u", new HashMap<Integer, String[]>());
                ret.get("castle1_border_ext_u").put(1, new String[]{"3", "castle1_border_ext_u"});
                ret.get("castle1_border_ext_u").put(2, new String[]{"3", "castle1_border_r"});
                ret.get("castle1_border_ext_u").put(3, new String[]{"3", "castle1_border_d"});
                ret.get("castle1_border_ext_u").put(4, new String[]{"3", "castle1_border_l"});

                // Влево
                ret.put("castle1_border_ext_l", new HashMap<Integer, String[]>());
                ret.get("castle1_border_ext_l").put(1, new String[]{"3", "castle1_border_l"});
                ret.get("castle1_border_ext_l").put(2, new String[]{"3", "castle1_border_ext_u"});
                ret.get("castle1_border_ext_l").put(3, new String[]{"3", "castle1_border_r"});
                ret.get("castle1_border_ext_l").put(4, new String[]{"3", "castle1_border_d"});

                // Вправо
                ret.put("castle1_border_ext_r", new HashMap<Integer, String[]>());
                ret.get("castle1_border_ext_r").put(1, new String[]{"3", "castle1_border_r"});
                ret.get("castle1_border_ext_r").put(2, new String[]{"3", "castle1_border_d"});
                ret.get("castle1_border_ext_r").put(3, new String[]{"3", "castle1_border_l"});
                ret.get("castle1_border_ext_r").put(4, new String[]{"3", "castle1_border_ext_u"});

            }

            // Граница с дверью
            {

                // Вниз
                ret.put("castle1_border_door_d", new HashMap<Integer, String[]>());
                ret.get("castle1_border_door_d").put(1, new String[]{"3", "castle1_border_d"});
                ret.get("castle1_border_door_d").put(2, new String[]{"3", "castle1_border_l"});
                ret.get("castle1_border_door_d").put(3, new String[]{"3", "castle1_border_door_u"});
                ret.get("castle1_border_door_d").put(4, new String[]{"3", "castle1_border_r"});

                // Вверх
                ret.put("castle1_border_door_u", new HashMap<Integer, String[]>());
                ret.get("castle1_border_door_u").put(1, new String[]{"3", "castle1_border_door_u"});
                ret.get("castle1_border_door_u").put(2, new String[]{"3", "castle1_border_r"});
                ret.get("castle1_border_door_u").put(3, new String[]{"3", "castle1_border_d"});
                ret.get("castle1_border_door_u").put(4, new String[]{"3", "castle1_border_l"});

                // Влево
                ret.put("castle1_border_door_l", new HashMap<Integer, String[]>());
                ret.get("castle1_border_door_l").put(1, new String[]{"3", "castle1_border_l"});
                ret.get("castle1_border_door_l").put(2, new String[]{"3", "castle1_border_door_u"});
                ret.get("castle1_border_door_l").put(3, new String[]{"3", "castle1_border_r"});
                ret.get("castle1_border_door_l").put(4, new String[]{"3", "castle1_border_d"});

                // Вправо
                ret.put("castle1_border_door_r", new HashMap<Integer, String[]>());
                ret.get("castle1_border_door_r").put(1, new String[]{"3", "castle1_border_r"});
                ret.get("castle1_border_door_r").put(2, new String[]{"3", "castle1_border_d"});
                ret.get("castle1_border_door_r").put(3, new String[]{"3", "castle1_border_l"});
                ret.get("castle1_border_door_r").put(4, new String[]{"3", "castle1_border_door_u"});

            }

            // Углы обычные
            {

                // Верх-лево
                ret.put("castle1_border3_u_l", new HashMap<Integer, String[]>());
                ret.get("castle1_border3_u_l").put(1, new String[]{"3", "castle1_border3_u_l"});
                ret.get("castle1_border3_u_l").put(2, new String[]{"3", "castle1_border3_u_r"});
                ret.get("castle1_border3_u_l").put(3, new String[]{"3", "castle1_border3_d_r"});
                ret.get("castle1_border3_u_l").put(4, new String[]{"3", "castle1_border3_d_l"});

                // Верх-право
                ret.put("castle1_border3_u_r", new HashMap<Integer, String[]>());
                ret.get("castle1_border3_u_r").put(1, new String[]{"3", "castle1_border3_u_r"});
                ret.get("castle1_border3_u_r").put(2, new String[]{"3", "castle1_border3_d_r"});
                ret.get("castle1_border3_u_r").put(3, new String[]{"3", "castle1_border3_d_l"});
                ret.get("castle1_border3_u_r").put(4, new String[]{"3", "castle1_border3_u_l"});

                // Низ-лево
                ret.put("castle1_border3_d_l", new HashMap<Integer, String[]>());
                ret.get("castle1_border3_d_l").put(1, new String[]{"3", "castle1_border3_d_l"});
                ret.get("castle1_border3_d_l").put(2, new String[]{"3", "castle1_border3_u_l"});
                ret.get("castle1_border3_d_l").put(3, new String[]{"3", "castle1_border3_u_r"});
                ret.get("castle1_border3_d_l").put(4, new String[]{"3", "castle1_border3_d_r"});

                // Низ-право
                ret.put("castle1_border3_d_r", new HashMap<Integer, String[]>());
                ret.get("castle1_border3_d_r").put(1, new String[]{"3", "castle1_border3_d_r"});
                ret.get("castle1_border3_d_r").put(2, new String[]{"3", "castle1_border3_d_l"});
                ret.get("castle1_border3_d_r").put(3, new String[]{"3", "castle1_border3_u_l"});
                ret.get("castle1_border3_d_r").put(4, new String[]{"3", "castle1_border3_u_r"});

            }

            // Углы башни
            {

                // Верх-лево
                ret.put("castle1_border1_u_l", new HashMap<Integer, String[]>());
                ret.get("castle1_border1_u_l").put(1, new String[]{"3", "castle1_border1_u_l"});
                ret.get("castle1_border1_u_l").put(2, new String[]{"3", "castle1_border1_u_r"});
                ret.get("castle1_border1_u_l").put(3, new String[]{"3", "castle1_border1_d_r"});
                ret.get("castle1_border1_u_l").put(4, new String[]{"3", "castle1_border1_d_l"});

                // Верх-право
                ret.put("castle1_border1_u_r", new HashMap<Integer, String[]>());
                ret.get("castle1_border1_u_r").put(1, new String[]{"3", "castle1_border1_u_r"});
                ret.get("castle1_border1_u_r").put(2, new String[]{"3", "castle1_border1_d_r"});
                ret.get("castle1_border1_u_r").put(3, new String[]{"3", "castle1_border1_d_l"});
                ret.get("castle1_border1_u_r").put(4, new String[]{"3", "castle1_border1_u_l"});

                // Низ-лево
                ret.put("castle1_border1_d_l", new HashMap<Integer, String[]>());
                ret.get("castle1_border1_d_l").put(1, new String[]{"3", "castle1_border1_d_l"});
                ret.get("castle1_border1_d_l").put(2, new String[]{"3", "castle1_border1_u_l"});
                ret.get("castle1_border1_d_l").put(3, new String[]{"3", "castle1_border1_u_r"});
                ret.get("castle1_border1_d_l").put(4, new String[]{"3", "castle1_border1_d_r"});

                // Низ-право
                ret.put("castle1_border1_d_r", new HashMap<Integer, String[]>());
                ret.get("castle1_border1_d_r").put(1, new String[]{"3", "castle1_border1_d_r"});
                ret.get("castle1_border1_d_r").put(2, new String[]{"3", "castle1_border1_d_l"});
                ret.get("castle1_border1_d_r").put(3, new String[]{"3", "castle1_border1_u_l"});
                ret.get("castle1_border1_d_r").put(4, new String[]{"3", "castle1_border1_u_r"});

            }

            // Углы башни (отзеркаленные)
            {

                // Верх-лево
                ret.put("castle1_border2_u_l", new HashMap<Integer, String[]>());
                ret.get("castle1_border2_u_l").put(1, new String[]{"3", "castle1_border2_u_l"});
                ret.get("castle1_border2_u_l").put(2, new String[]{"3", "castle1_border2_u_r"});
                ret.get("castle1_border2_u_l").put(3, new String[]{"3", "castle1_border2_d_r"});
                ret.get("castle1_border2_u_l").put(4, new String[]{"3", "castle1_border2_d_l"});

                // Верх-право
                ret.put("castle1_border2_u_r", new HashMap<Integer, String[]>());
                ret.get("castle1_border2_u_r").put(1, new String[]{"3", "castle1_border2_u_r"});
                ret.get("castle1_border2_u_r").put(2, new String[]{"3", "castle1_border2_d_r"});
                ret.get("castle1_border2_u_r").put(3, new String[]{"3", "castle1_border2_d_l"});
                ret.get("castle1_border2_u_r").put(4, new String[]{"3", "castle1_border2_u_l"});

                // Низ-лево
                ret.put("castle1_border2_d_l", new HashMap<Integer, String[]>());
                ret.get("castle1_border2_d_l").put(1, new String[]{"3", "castle1_border2_d_l"});
                ret.get("castle1_border2_d_l").put(2, new String[]{"3", "castle1_border2_u_l"});
                ret.get("castle1_border2_d_l").put(3, new String[]{"3", "castle1_border2_u_r"});
                ret.get("castle1_border2_d_l").put(4, new String[]{"3", "castle1_border2_d_r"});

                // Низ-право
                ret.put("castle1_border2_d_r", new HashMap<Integer, String[]>());
                ret.get("castle1_border2_d_r").put(1, new String[]{"3", "castle1_border2_d_r"});
                ret.get("castle1_border2_d_r").put(2, new String[]{"3", "castle1_border2_d_l"});
                ret.get("castle1_border2_d_r").put(3, new String[]{"3", "castle1_border2_u_l"});
                ret.get("castle1_border2_d_r").put(4, new String[]{"3", "castle1_border2_u_r"});

            }

            // Коридор-забор
            {

                // Вниз
                ret.put("castle1_fence1_d", new HashMap<Integer, String[]>());
                ret.get("castle1_fence1_d").put(1, new String[]{"3", "castle1_fence1_h"});
                ret.get("castle1_fence1_d").put(2, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence1_d").put(3, new String[]{"3", "castle1_fence1_h"});
                ret.get("castle1_fence1_d").put(4, new String[]{"3", "castle1_fence1_v"});

                // Вверх
                ret.put("castle1_fence1_u", new HashMap<Integer, String[]>());
                ret.get("castle1_fence1_u").put(1, new String[]{"3", "castle1_fence1_h"});
                ret.get("castle1_fence1_u").put(2, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence1_u").put(3, new String[]{"3", "castle1_fence1_h"});
                ret.get("castle1_fence1_u").put(4, new String[]{"3", "castle1_fence1_v"});

                // Влево
                ret.put("castle1_fence1_l", new HashMap<Integer, String[]>());
                ret.get("castle1_fence1_l").put(1, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence1_l").put(2, new String[]{"3", "castle1_fence1_h"});
                ret.get("castle1_fence1_l").put(3, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence1_l").put(4, new String[]{"3", "castle1_fence1_h"});

                // Вправо
                ret.put("castle1_fence1_r", new HashMap<Integer, String[]>());
                ret.get("castle1_fence1_r").put(1, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence1_r").put(2, new String[]{"3", "castle1_fence1_h"});
                ret.get("castle1_fence1_r").put(3, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence1_r").put(4, new String[]{"3", "castle1_fence1_h"});

            }

            // Коридор-забор с окном
            {

                // Вниз
                ret.put("castle1_fence2_d", new HashMap<Integer, String[]>());
                ret.get("castle1_fence2_d").put(1, new String[]{"3", "castle1_fence2_h"});
                ret.get("castle1_fence2_d").put(2, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence2_d").put(3, new String[]{"3", "castle1_fence2_h"});
                ret.get("castle1_fence2_d").put(4, new String[]{"3", "castle1_fence1_v"});

                // Вверх
                ret.put("castle1_fence2_u", new HashMap<Integer, String[]>());
                ret.get("castle1_fence2_u").put(1, new String[]{"3", "castle1_fence2_h"});
                ret.get("castle1_fence2_u").put(2, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence2_u").put(3, new String[]{"3", "castle1_fence2_h"});
                ret.get("castle1_fence2_u").put(4, new String[]{"3", "castle1_fence1_v"});

                // Влево
                ret.put("castle1_fence2_l", new HashMap<Integer, String[]>());
                ret.get("castle1_fence2_l").put(1, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence2_l").put(2, new String[]{"3", "castle1_fence2_h"});
                ret.get("castle1_fence2_l").put(3, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence2_l").put(4, new String[]{"3", "castle1_fence2_h"});

                // Вправо
                ret.put("castle1_fence2_r", new HashMap<Integer, String[]>());
                ret.get("castle1_fence2_r").put(1, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence2_r").put(2, new String[]{"3", "castle1_fence2_h"});
                ret.get("castle1_fence2_r").put(3, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence2_r").put(4, new String[]{"3", "castle1_fence2_h"});

            }

            // Коридор-забор с аркой
            {

                // Вниз
                ret.put("castle1_fence3_d", new HashMap<Integer, String[]>());
                ret.get("castle1_fence3_d").put(1, new String[]{"3", "castle1_fence3_h"});
                ret.get("castle1_fence3_d").put(2, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence3_d").put(3, new String[]{"3", "castle1_fence3_h"});
                ret.get("castle1_fence3_d").put(4, new String[]{"3", "castle1_fence1_v"});

                // Вверх
                ret.put("castle1_fence3_u", new HashMap<Integer, String[]>());
                ret.get("castle1_fence3_u").put(1, new String[]{"3", "castle1_fence3_h"});
                ret.get("castle1_fence3_u").put(2, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence3_u").put(3, new String[]{"3", "castle1_fence3_h"});
                ret.get("castle1_fence3_u").put(4, new String[]{"3", "castle1_fence1_v"});

                // Влево
                ret.put("castle1_fence3_l", new HashMap<Integer, String[]>());
                ret.get("castle1_fence3_l").put(1, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence3_l").put(2, new String[]{"3", "castle1_fence3_h"});
                ret.get("castle1_fence3_l").put(3, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence3_l").put(4, new String[]{"3", "castle1_fence3_h"});

                // Вправо
                ret.put("castle1_fence3_r", new HashMap<Integer, String[]>());
                ret.get("castle1_fence3_r").put(1, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence3_r").put(2, new String[]{"3", "castle1_fence3_h"});
                ret.get("castle1_fence3_r").put(3, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence3_r").put(4, new String[]{"3", "castle1_fence3_h"});

            }

            // Коридор-забор с дверью
            {

                // Вниз
                ret.put("castle1_fence4_d", new HashMap<Integer, String[]>());
                ret.get("castle1_fence4_d").put(1, new String[]{"3", "castle1_fence4_h"});
                ret.get("castle1_fence4_d").put(2, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence4_d").put(3, new String[]{"3", "castle1_fence4_h"});
                ret.get("castle1_fence4_d").put(4, new String[]{"3", "castle1_fence1_v"});

                // Вверх
                ret.put("castle1_fence4_u", new HashMap<Integer, String[]>());
                ret.get("castle1_fence4_u").put(1, new String[]{"3", "castle1_fence4_h"});
                ret.get("castle1_fence4_u").put(2, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence4_u").put(3, new String[]{"3", "castle1_fence4_h"});
                ret.get("castle1_fence4_u").put(4, new String[]{"3", "castle1_fence1_v"});

                // Влево
                ret.put("castle1_fence4_l", new HashMap<Integer, String[]>());
                ret.get("castle1_fence4_l").put(1, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence4_l").put(2, new String[]{"3", "castle1_fence4_h"});
                ret.get("castle1_fence4_l").put(3, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence4_l").put(4, new String[]{"3", "castle1_fence4_h"});

                // Вправо
                ret.put("castle1_fence4_r", new HashMap<Integer, String[]>());
                ret.get("castle1_fence4_r").put(1, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence4_r").put(2, new String[]{"3", "castle1_fence4_h"});
                ret.get("castle1_fence4_r").put(3, new String[]{"3", "castle1_fence1_v"});
                ret.get("castle1_fence4_r").put(4, new String[]{"3", "castle1_fence4_h"});

            }

            // Фрагмент конца коридора-забора
            {

                // Слева
                ret.put("castle1_fence_end_l", new HashMap<Integer, String[]>());
                ret.get("castle1_fence_end_l").put(1, new String[]{"3", "castle1_fence_end_l"});
                ret.get("castle1_fence_end_l").put(2, new String[]{"3", "castle1_fence_end_u"});
                ret.get("castle1_fence_end_l").put(3, new String[]{"3", "castle1_fence_end_r"});
                ret.get("castle1_fence_end_l").put(4, new String[]{"3", "castle1_fence_end_d"});

                // Вверху
                ret.put("castle1_fence_end_u", new HashMap<Integer, String[]>());
                ret.get("castle1_fence_end_u").put(1, new String[]{"3", "castle1_fence_end_u"});
                ret.get("castle1_fence_end_u").put(2, new String[]{"3", "castle1_fence_end_r"});
                ret.get("castle1_fence_end_u").put(3, new String[]{"3", "castle1_fence_end_d"});
                ret.get("castle1_fence_end_u").put(4, new String[]{"3", "castle1_fence_end_l"});

                // Справа
                ret.put("castle1_fence_end_r", new HashMap<Integer, String[]>());
                ret.get("castle1_fence_end_r").put(1, new String[]{"3", "castle1_fence_end_r"});
                ret.get("castle1_fence_end_r").put(2, new String[]{"3", "castle1_fence_end_d"});
                ret.get("castle1_fence_end_r").put(3, new String[]{"3", "castle1_fence_end_l"});
                ret.get("castle1_fence_end_r").put(4, new String[]{"3", "castle1_fence_end_u"});

                // Внизу
                ret.put("castle1_fence_end_d", new HashMap<Integer, String[]>());
                ret.get("castle1_fence_end_d").put(1, new String[]{"3", "castle1_fence_end_d"});
                ret.get("castle1_fence_end_d").put(2, new String[]{"3", "castle1_fence_end_l"});
                ret.get("castle1_fence_end_d").put(3, new String[]{"3", "castle1_fence_end_u"});
                ret.get("castle1_fence_end_d").put(4, new String[]{"3", "castle1_fence_end_r"});

            }

        }

        // Здание
        {

            // Фрагмент стены
            {

                // Вниз
                ret.put("build_m_wall_d", new HashMap<Integer, String[]>());
                ret.get("build_m_wall_d").put(1, new String[]{"2", "build_m_wall_d"});
                ret.get("build_m_wall_d").put(2, new String[]{"2", "build_m_wall_l"});
                ret.get("build_m_wall_d").put(3, new String[]{"2", "build_m_wall_u"});
                ret.get("build_m_wall_d").put(4, new String[]{"2", "build_m_wall_r"});

                // Вверх
                ret.put("build_m_wall_u", new HashMap<Integer, String[]>());
                ret.get("build_m_wall_u").put(1, new String[]{"2", "build_m_wall_u"});
                ret.get("build_m_wall_u").put(2, new String[]{"2", "build_m_wall_r"});
                ret.get("build_m_wall_u").put(3, new String[]{"2", "build_m_wall_d"});
                ret.get("build_m_wall_u").put(4, new String[]{"2", "build_m_wall_l"});

                // Влево
                ret.put("build_m_wall_l", new HashMap<Integer, String[]>());
                ret.get("build_m_wall_l").put(1, new String[]{"2", "build_m_wall_l"});
                ret.get("build_m_wall_l").put(2, new String[]{"2", "build_m_wall_u"});
                ret.get("build_m_wall_l").put(3, new String[]{"2", "build_m_wall_r"});
                ret.get("build_m_wall_l").put(4, new String[]{"2", "build_m_wall_d"});

                // Вправо
                ret.put("build_m_wall_r", new HashMap<Integer, String[]>());
                ret.get("build_m_wall_r").put(1, new String[]{"2", "build_m_wall_r"});
                ret.get("build_m_wall_r").put(2, new String[]{"2", "build_m_wall_d"});
                ret.get("build_m_wall_r").put(3, new String[]{"2", "build_m_wall_l"});
                ret.get("build_m_wall_r").put(4, new String[]{"2", "build_m_wall_u"});

            }

            // Фрагмент с дверью
            {

                // Вниз
                ret.put("build_m_door_d", new HashMap<Integer, String[]>());
                ret.get("build_m_door_d").put(1, new String[]{"2", "build_m_door_d"});
                ret.get("build_m_door_d").put(2, new String[]{"2", "build_m_wall_l"});
                ret.get("build_m_door_d").put(3, new String[]{"2", "build_m_wall_u"});
                ret.get("build_m_door_d").put(4, new String[]{"2", "build_m_wall_r"});

                // Вверх
                ret.put("build_m_door_u", new HashMap<Integer, String[]>());
                ret.get("build_m_door_u").put(1, new String[]{"2", "build_m_wall_u"});
                ret.get("build_m_door_u").put(2, new String[]{"2", "build_m_wall_r"});
                ret.get("build_m_door_u").put(3, new String[]{"2", "build_m_door_d"});
                ret.get("build_m_door_u").put(4, new String[]{"2", "build_m_wall_l"});

                // Влево
                ret.put("build_m_door_l", new HashMap<Integer, String[]>());
                ret.get("build_m_door_l").put(1, new String[]{"2", "build_m_wall_l"});
                ret.get("build_m_door_l").put(2, new String[]{"2", "build_m_wall_u"});
                ret.get("build_m_door_l").put(3, new String[]{"2", "build_m_wall_r"});
                ret.get("build_m_door_l").put(4, new String[]{"2", "build_m_door_d"});

                // Вправо
                ret.put("build_m_door_r", new HashMap<Integer, String[]>());
                ret.get("build_m_door_r").put(1, new String[]{"2", "build_m_wall_r"});
                ret.get("build_m_door_r").put(2, new String[]{"2", "build_m_door_d"});
                ret.get("build_m_door_r").put(3, new String[]{"2", "build_m_wall_l"});
                ret.get("build_m_door_r").put(4, new String[]{"2", "build_m_wall_u"});

            }

            // Фрагмент с дверью и окнами
            {

                // Вниз
                ret.put("build_m_doorw_d", new HashMap<Integer, String[]>());
                ret.get("build_m_doorw_d").put(1, new String[]{"2", "build_m_doorw_d"});
                ret.get("build_m_doorw_d").put(2, new String[]{"2", "build_m_wall_l"});
                ret.get("build_m_doorw_d").put(3, new String[]{"2", "build_m_wall_u"});
                ret.get("build_m_doorw_d").put(4, new String[]{"2", "build_m_wall_r"});

                // Вверх
                ret.put("build_m_doorw_u", new HashMap<Integer, String[]>());
                ret.get("build_m_doorw_u").put(1, new String[]{"2", "build_m_wall_u"});
                ret.get("build_m_doorw_u").put(2, new String[]{"2", "build_m_wall_r"});
                ret.get("build_m_doorw_u").put(3, new String[]{"2", "build_m_doorw_d"});
                ret.get("build_m_doorw_u").put(4, new String[]{"2", "build_m_wall_l"});

                // Влево
                ret.put("build_m_doorw_l", new HashMap<Integer, String[]>());
                ret.get("build_m_doorw_l").put(1, new String[]{"2", "build_m_wall_l"});
                ret.get("build_m_doorw_l").put(2, new String[]{"2", "build_m_wall_u"});
                ret.get("build_m_doorw_l").put(3, new String[]{"2", "build_m_wall_r"});
                ret.get("build_m_doorw_l").put(4, new String[]{"2", "build_m_doorw_d"});

                // Вправо
                ret.put("build_m_doorw_r", new HashMap<Integer, String[]>());
                ret.get("build_m_doorw_r").put(1, new String[]{"2", "build_m_wall_r"});
                ret.get("build_m_doorw_r").put(2, new String[]{"2", "build_m_doorw_d"});
                ret.get("build_m_doorw_r").put(3, new String[]{"2", "build_m_wall_l"});
                ret.get("build_m_doorw_r").put(4, new String[]{"2", "build_m_wall_u"});

            }

            // Фрагмент с окнами
            {

                // Вниз
                ret.put("build_m_win_d", new HashMap<Integer, String[]>());
                ret.get("build_m_win_d").put(1, new String[]{"2", "build_m_win_d"});
                ret.get("build_m_win_d").put(2, new String[]{"2", "build_m_wall_l"});
                ret.get("build_m_win_d").put(3, new String[]{"2", "build_m_wall_u"});
                ret.get("build_m_win_d").put(4, new String[]{"2", "build_m_wall_r"});

                // Вверх
                ret.put("build_m_win_u", new HashMap<Integer, String[]>());
                ret.get("build_m_win_u").put(1, new String[]{"2", "build_m_wall_u"});
                ret.get("build_m_win_u").put(2, new String[]{"2", "build_m_wall_r"});
                ret.get("build_m_win_u").put(3, new String[]{"2", "build_m_win_d"});
                ret.get("build_m_win_u").put(4, new String[]{"2", "build_m_wall_l"});

                // Влево
                ret.put("build_m_win_l", new HashMap<Integer, String[]>());
                ret.get("build_m_win_l").put(1, new String[]{"2", "build_m_wall_l"});
                ret.get("build_m_win_l").put(2, new String[]{"2", "build_m_wall_u"});
                ret.get("build_m_win_l").put(3, new String[]{"2", "build_m_wall_r"});
                ret.get("build_m_win_l").put(4, new String[]{"2", "build_m_win_d"});

                // Вправо
                ret.put("build_m_win_r", new HashMap<Integer, String[]>());
                ret.get("build_m_win_r").put(1, new String[]{"2", "build_m_wall_r"});
                ret.get("build_m_win_r").put(2, new String[]{"2", "build_m_win_d"});
                ret.get("build_m_win_r").put(3, new String[]{"2", "build_m_wall_l"});
                ret.get("build_m_win_r").put(4, new String[]{"2", "build_m_wall_u"});

            }

            // Пустые углы
            {

                // Верх-лево
                ret.put("build_m_angle1_u_l", new HashMap<Integer, String[]>());
                ret.get("build_m_angle1_u_l").put(1, new String[]{"2", "build_m_angle1_u_l"});
                ret.get("build_m_angle1_u_l").put(2, new String[]{"2", "build_m_angle1_u_r"});
                ret.get("build_m_angle1_u_l").put(3, new String[]{"2", "build_m_angle1_d_r"});
                ret.get("build_m_angle1_u_l").put(4, new String[]{"2", "build_m_angle1_d_l"});

                // Верх-право
                ret.put("build_m_angle1_u_r", new HashMap<Integer, String[]>());
                ret.get("build_m_angle1_u_r").put(1, new String[]{"2", "build_m_angle1_u_r"});
                ret.get("build_m_angle1_u_r").put(2, new String[]{"2", "build_m_angle1_d_r"});
                ret.get("build_m_angle1_u_r").put(3, new String[]{"2", "build_m_angle1_d_l"});
                ret.get("build_m_angle1_u_r").put(4, new String[]{"2", "build_m_angle1_u_l"});

                // Низ-лево
                ret.put("build_m_angle1_d_l", new HashMap<Integer, String[]>());
                ret.get("build_m_angle1_d_l").put(1, new String[]{"2", "build_m_angle1_d_l"});
                ret.get("build_m_angle1_d_l").put(2, new String[]{"2", "build_m_angle1_u_l"});
                ret.get("build_m_angle1_d_l").put(3, new String[]{"2", "build_m_angle1_u_r"});
                ret.get("build_m_angle1_d_l").put(4, new String[]{"2", "build_m_angle1_d_r"});

                // Низ-право
                ret.put("build_m_angle1_d_r", new HashMap<Integer, String[]>());
                ret.get("build_m_angle1_d_r").put(1, new String[]{"2", "build_m_angle1_d_r"});
                ret.get("build_m_angle1_d_r").put(2, new String[]{"2", "build_m_angle1_d_l"});
                ret.get("build_m_angle1_d_r").put(3, new String[]{"2", "build_m_angle1_u_l"});
                ret.get("build_m_angle1_d_r").put(4, new String[]{"2", "build_m_angle1_u_r"});

            }

            // Углы с окнами
            {

                // Верх-лево
                ret.put("build_m_angle2_u_l", new HashMap<Integer, String[]>());
                ret.get("build_m_angle2_u_l").put(1, new String[]{"2", "build_m_angle1_u_l"});
                ret.get("build_m_angle2_u_l").put(2, new String[]{"2", "build_m_angle1_u_r"});
                ret.get("build_m_angle2_u_l").put(3, new String[]{"2", "build_m_angle2_d_r"});
                ret.get("build_m_angle2_u_l").put(4, new String[]{"2", "build_m_angle2_d_l"});

                // Верх-право
                ret.put("build_m_angle2_u_r", new HashMap<Integer, String[]>());
                ret.get("build_m_angle2_u_r").put(1, new String[]{"2", "build_m_angle1_u_r"});
                ret.get("build_m_angle2_u_r").put(2, new String[]{"2", "build_m_angle2_d_r"});
                ret.get("build_m_angle2_u_r").put(3, new String[]{"2", "build_m_angle2_d_l"});
                ret.get("build_m_angle2_u_r").put(4, new String[]{"2", "build_m_angle1_u_l"});

                // Низ-лево
                ret.put("build_m_angle2_d_l", new HashMap<Integer, String[]>());
                ret.get("build_m_angle2_d_l").put(1, new String[]{"2", "build_m_angle2_d_l"});
                ret.get("build_m_angle2_d_l").put(2, new String[]{"2", "build_m_angle1_u_l"});
                ret.get("build_m_angle2_d_l").put(3, new String[]{"2", "build_m_angle1_u_r"});
                ret.get("build_m_angle2_d_l").put(4, new String[]{"2", "build_m_angle2_d_r"});

                // Низ-право
                ret.put("build_m_angle2_d_r", new HashMap<Integer, String[]>());
                ret.get("build_m_angle2_d_r").put(1, new String[]{"2", "build_m_angle2_d_r"});
                ret.get("build_m_angle2_d_r").put(2, new String[]{"2", "build_m_angle2_d_l"});
                ret.get("build_m_angle2_d_r").put(3, new String[]{"2", "build_m_angle1_u_l"});
                ret.get("build_m_angle2_d_r").put(4, new String[]{"2", "build_m_angle1_u_r"});

            }

            // Углы с окнами с одной стороны
            {

                // Верх-лево
                ret.put("build_m_angle3_u_l", new HashMap<Integer, String[]>());
                ret.get("build_m_angle3_u_l").put(1, new String[]{"2", "build_m_angle1_u_l"});
                ret.get("build_m_angle3_u_l").put(2, new String[]{"2", "build_m_angle1_u_r"});
                ret.get("build_m_angle3_u_l").put(3, new String[]{"2", "build_m_angle2_d_r"});
                ret.get("build_m_angle3_u_l").put(4, new String[]{"2", "build_m_angle1_d_l"});

                // Верх-право
                ret.put("build_m_angle3_u_r", new HashMap<Integer, String[]>());
                ret.get("build_m_angle3_u_r").put(1, new String[]{"2", "build_m_angle1_u_r"});
                ret.get("build_m_angle3_u_r").put(2, new String[]{"2", "build_m_angle2_d_r"});
                ret.get("build_m_angle3_u_r").put(3, new String[]{"2", "build_m_angle1_d_l"});
                ret.get("build_m_angle3_u_r").put(4, new String[]{"2", "build_m_angle1_u_l"});

                // Низ-лево
                ret.put("build_m_angle3_d_l", new HashMap<Integer, String[]>());
                ret.get("build_m_angle3_d_l").put(1, new String[]{"2", "build_m_angle1_d_l"});
                ret.get("build_m_angle3_d_l").put(2, new String[]{"2", "build_m_angle1_u_l"});
                ret.get("build_m_angle3_d_l").put(3, new String[]{"2", "build_m_angle1_u_r"});
                ret.get("build_m_angle3_d_l").put(4, new String[]{"2", "build_m_angle2_d_r"});

                // Низ-право
                ret.put("build_m_angle3_d_r", new HashMap<Integer, String[]>());
                ret.get("build_m_angle3_d_r").put(1, new String[]{"2", "build_m_angle2_d_r"});
                ret.get("build_m_angle3_d_r").put(2, new String[]{"2", "build_m_angle1_d_l"});
                ret.get("build_m_angle3_d_r").put(3, new String[]{"2", "build_m_angle1_u_l"});
                ret.get("build_m_angle3_d_r").put(4, new String[]{"2", "build_m_angle1_u_r"});

            }

            // Углы с окнами с одной стороны (наоборот)
            {

                // Верх-лево
                ret.put("build_m_angle4_u_l", new HashMap<Integer, String[]>());
                ret.get("build_m_angle4_u_l").put(1, new String[]{"2", "build_m_angle1_u_l"});
                ret.get("build_m_angle4_u_l").put(2, new String[]{"2", "build_m_angle1_u_r"});
                ret.get("build_m_angle4_u_l").put(3, new String[]{"2", "build_m_angle1_d_r"});
                ret.get("build_m_angle4_u_l").put(4, new String[]{"2", "build_m_angle2_d_l"});

                // Верх-право
                ret.put("build_m_angle4_u_r", new HashMap<Integer, String[]>());
                ret.get("build_m_angle4_u_r").put(1, new String[]{"2", "build_m_angle1_u_r"});
                ret.get("build_m_angle4_u_r").put(2, new String[]{"2", "build_m_angle1_d_r"});
                ret.get("build_m_angle4_u_r").put(3, new String[]{"2", "build_m_angle2_d_l"});
                ret.get("build_m_angle4_u_r").put(4, new String[]{"2", "build_m_angle1_u_l"});

                // Низ-лево
                ret.put("build_m_angle4_d_l", new HashMap<Integer, String[]>());
                ret.get("build_m_angle4_d_l").put(1, new String[]{"2", "build_m_angle2_d_l"});
                ret.get("build_m_angle4_d_l").put(2, new String[]{"2", "build_m_angle1_u_l"});
                ret.get("build_m_angle4_d_l").put(3, new String[]{"2", "build_m_angle1_u_r"});
                ret.get("build_m_angle4_d_l").put(4, new String[]{"2", "build_m_angle1_d_r"});

                // Низ-право
                ret.put("build_m_angle4_d_r", new HashMap<Integer, String[]>());
                ret.get("build_m_angle4_d_r").put(1, new String[]{"2", "build_m_angle1_d_r"});
                ret.get("build_m_angle4_d_r").put(2, new String[]{"2", "build_m_angle2_d_l"});
                ret.get("build_m_angle4_d_r").put(3, new String[]{"2", "build_m_angle1_u_l"});
                ret.get("build_m_angle4_d_r").put(4, new String[]{"2", "build_m_angle1_u_r"});

            }

            // Центральные фрагменты
            {

                // Общий блок
                ret.put("build_m_center", new HashMap<Integer, String[]>());
                ret.get("build_m_center").put(1, new String[]{"2", "build_m_center"});
                ret.get("build_m_center").put(2, new String[]{"2", "build_m_center"});
                ret.get("build_m_center").put(3, new String[]{"2", "build_m_center"});
                ret.get("build_m_center").put(4, new String[]{"2", "build_m_center"});

                // Вертикальное окно на крыше
                ret.put("build_m_center_cwin_v", new HashMap<Integer, String[]>());
                ret.get("build_m_center_cwin_v").put(1, new String[]{"2", "build_m_center_cwin_v"});
                ret.get("build_m_center_cwin_v").put(2, new String[]{"2", "build_m_center_cwin_h"});
                ret.get("build_m_center_cwin_v").put(3, new String[]{"2", "build_m_center_cwin_v"});
                ret.get("build_m_center_cwin_v").put(4, new String[]{"2", "build_m_center_cwin_h"});

                // Горизонтальное окно на крыше
                ret.put("build_m_center_cwin_h", new HashMap<Integer, String[]>());
                ret.get("build_m_center_cwin_h").put(1, new String[]{"2", "build_m_center_cwin_h"});
                ret.get("build_m_center_cwin_h").put(2, new String[]{"2", "build_m_center_cwin_v"});
                ret.get("build_m_center_cwin_h").put(3, new String[]{"2", "build_m_center_cwin_h"});
                ret.get("build_m_center_cwin_h").put(4, new String[]{"2", "build_m_center_cwin_v"});

            }

        }

        // Киоск
        {

            // Вниз
            ret.put("magazine_d", new HashMap<Integer, String[]>());
            ret.get("magazine_d").put(1, new String[]{"2", "magazine_d"});
            ret.get("magazine_d").put(2, new String[]{"2", "magazine_h"});
            ret.get("magazine_d").put(3, new String[]{"2", "magazine_u"});
            ret.get("magazine_d").put(4, new String[]{"2", "magazine_h"});

            // Вверх
            ret.put("magazine_u", new HashMap<Integer, String[]>());
            ret.get("magazine_u").put(1, new String[]{"2", "magazine_u"});
            ret.get("magazine_u").put(2, new String[]{"2", "magazine_h"});
            ret.get("magazine_u").put(3, new String[]{"2", "magazine_d"});
            ret.get("magazine_u").put(4, new String[]{"2", "magazine_h"});

            // Влево
            ret.put("magazine_l", new HashMap<Integer, String[]>());
            ret.get("magazine_l").put(1, new String[]{"2", "magazine_h"});
            ret.get("magazine_l").put(2, new String[]{"2", "magazine_u"});
            ret.get("magazine_l").put(3, new String[]{"2", "magazine_h"});
            ret.get("magazine_l").put(4, new String[]{"2", "magazine_d"});

            // Вправо
            ret.put("magazine_r", new HashMap<Integer, String[]>());
            ret.get("magazine_r").put(1, new String[]{"2", "magazine_h"});
            ret.get("magazine_r").put(2, new String[]{"2", "magazine_d"});
            ret.get("magazine_r").put(3, new String[]{"2", "magazine_h"});
            ret.get("magazine_r").put(4, new String[]{"2", "magazine_u"});

        }

        // Кустарники
        {

            // Куст 1
            ret.put("bush1", new HashMap<Integer, String[]>());
            ret.get("bush1").put(1, new String[]{"1", "bush1"});
            ret.get("bush1").put(2, new String[]{"1", "bush1"});
            ret.get("bush1").put(3, new String[]{"1", "bush1"});
            ret.get("bush1").put(4, new String[]{"1", "bush1"});

            // Куст 2
            ret.put("bush2", new HashMap<Integer, String[]>());
            ret.get("bush2").put(1, new String[]{"1", "bush2"});
            ret.get("bush2").put(2, new String[]{"1", "bush2"});
            ret.get("bush2").put(3, new String[]{"1", "bush2"});
            ret.get("bush2").put(4, new String[]{"1", "bush2"});

            // Куст 3
            ret.put("bush3", new HashMap<Integer, String[]>());
            ret.get("bush3").put(1, new String[]{"1", "bush3"});
            ret.get("bush3").put(2, new String[]{"1", "bush3"});
            ret.get("bush3").put(3, new String[]{"1", "bush3"});
            ret.get("bush3").put(4, new String[]{"1", "bush3"});

            // Куст 4
            ret.put("bush4", new HashMap<Integer, String[]>());
            ret.get("bush4").put(1, new String[]{"1", "bush4"});
            ret.get("bush4").put(2, new String[]{"1", "bush4"});
            ret.get("bush4").put(3, new String[]{"1", "bush4"});
            ret.get("bush4").put(4, new String[]{"1", "bush4"});

            // Куст 5
            ret.put("bush5", new HashMap<Integer, String[]>());
            ret.get("bush5").put(1, new String[]{"1", "bush5"});
            ret.get("bush5").put(2, new String[]{"1", "bush5"});
            ret.get("bush5").put(3, new String[]{"1", "bush5"});
            ret.get("bush5").put(4, new String[]{"1", "bush5"});

            // Куст 6
            ret.put("bush6", new HashMap<Integer, String[]>());
            ret.get("bush6").put(1, new String[]{"1", "bush6"});
            ret.get("bush6").put(2, new String[]{"1", "bush6"});
            ret.get("bush6").put(3, new String[]{"1", "bush6"});
            ret.get("bush6").put(4, new String[]{"1", "bush6"});

        }

        // Маяк
        {

            // Вниз
            ret.put("lighthouse_d", new HashMap<Integer, String[]>());
            ret.get("lighthouse_d").put(1, new String[]{"4", "lighthouse_d"});
            ret.get("lighthouse_d").put(2, new String[]{"4", "lighthouse_l"});
            ret.get("lighthouse_d").put(3, new String[]{"4", "lighthouse_u"});
            ret.get("lighthouse_d").put(4, new String[]{"4", "lighthouse_r"});

            // Вверх
            ret.put("lighthouse_u", new HashMap<Integer, String[]>());
            ret.get("lighthouse_u").put(1, new String[]{"4", "lighthouse_u"});
            ret.get("lighthouse_u").put(2, new String[]{"4", "lighthouse_r"});
            ret.get("lighthouse_u").put(3, new String[]{"4", "lighthouse_d"});
            ret.get("lighthouse_u").put(4, new String[]{"4", "lighthouse_l"});

            // Влево
            ret.put("lighthouse_l", new HashMap<Integer, String[]>());
            ret.get("lighthouse_l").put(1, new String[]{"4", "lighthouse_l"});
            ret.get("lighthouse_l").put(2, new String[]{"4", "lighthouse_u"});
            ret.get("lighthouse_l").put(3, new String[]{"4", "lighthouse_r"});
            ret.get("lighthouse_l").put(4, new String[]{"4", "lighthouse_d"});

            // Вправо
            ret.put("lighthouse_r", new HashMap<Integer, String[]>());
            ret.get("lighthouse_r").put(1, new String[]{"4", "lighthouse_r"});
            ret.get("lighthouse_r").put(2, new String[]{"4", "lighthouse_d"});
            ret.get("lighthouse_r").put(3, new String[]{"4", "lighthouse_l"});
            ret.get("lighthouse_r").put(4, new String[]{"4", "lighthouse_u"});

        }

        // Машины
        {

            // Синяя машина
            {

                // Вниз
                ret.put("blue_car_d", new HashMap<Integer, String[]>());
                ret.get("blue_car_d").put(1, new String[]{"1", "blue_car_d"});
                ret.get("blue_car_d").put(2, new String[]{"1", "blue_car_l"});
                ret.get("blue_car_d").put(3, new String[]{"1", "blue_car_u"});
                ret.get("blue_car_d").put(4, new String[]{"1", "blue_car_r"});

                // Вверх
                ret.put("blue_car_u", new HashMap<Integer, String[]>());
                ret.get("blue_car_u").put(1, new String[]{"1", "blue_car_u"});
                ret.get("blue_car_u").put(2, new String[]{"1", "blue_car_r"});
                ret.get("blue_car_u").put(3, new String[]{"1", "blue_car_d"});
                ret.get("blue_car_u").put(4, new String[]{"1", "blue_car_l"});

                // Влево
                ret.put("blue_car_l", new HashMap<Integer, String[]>());
                ret.get("blue_car_l").put(1, new String[]{"1", "blue_car_l"});
                ret.get("blue_car_l").put(2, new String[]{"1", "blue_car_u"});
                ret.get("blue_car_l").put(3, new String[]{"1", "blue_car_r"});
                ret.get("blue_car_l").put(4, new String[]{"1", "blue_car_d"});

                // Вправо
                ret.put("blue_car_r", new HashMap<Integer, String[]>());
                ret.get("blue_car_r").put(1, new String[]{"1", "blue_car_r"});
                ret.get("blue_car_r").put(2, new String[]{"1", "blue_car_d"});
                ret.get("blue_car_r").put(3, new String[]{"1", "blue_car_l"});
                ret.get("blue_car_r").put(4, new String[]{"1", "blue_car_u"});

            }

            // Красная машина
            {

                // Вниз
                ret.put("red_car_d", new HashMap<Integer, String[]>());
                ret.get("red_car_d").put(1, new String[]{"1", "red_car_d"});
                ret.get("red_car_d").put(2, new String[]{"1", "red_car_l"});
                ret.get("red_car_d").put(3, new String[]{"1", "red_car_u"});
                ret.get("red_car_d").put(4, new String[]{"1", "red_car_r"});

                // Вверх
                ret.put("red_car_u", new HashMap<Integer, String[]>());
                ret.get("red_car_u").put(1, new String[]{"1", "red_car_u"});
                ret.get("red_car_u").put(2, new String[]{"1", "red_car_r"});
                ret.get("red_car_u").put(3, new String[]{"1", "red_car_d"});
                ret.get("red_car_u").put(4, new String[]{"1", "red_car_l"});

                // Влево
                ret.put("red_car_l", new HashMap<Integer, String[]>());
                ret.get("red_car_l").put(1, new String[]{"1", "red_car_l"});
                ret.get("red_car_l").put(2, new String[]{"1", "red_car_u"});
                ret.get("red_car_l").put(3, new String[]{"1", "red_car_r"});
                ret.get("red_car_l").put(4, new String[]{"1", "red_car_d"});

                // Вправо
                ret.put("red_car_r", new HashMap<Integer, String[]>());
                ret.get("red_car_r").put(1, new String[]{"1", "red_car_r"});
                ret.get("red_car_r").put(2, new String[]{"1", "red_car_d"});
                ret.get("red_car_r").put(3, new String[]{"1", "red_car_l"});
                ret.get("red_car_r").put(4, new String[]{"1", "red_car_u"});

            }

            // Черная машина
            {

                // Вниз
                ret.put("black_car_d", new HashMap<Integer, String[]>());
                ret.get("black_car_d").put(1, new String[]{"1", "black_car_d"});
                ret.get("black_car_d").put(2, new String[]{"1", "black_car_l"});
                ret.get("black_car_d").put(3, new String[]{"1", "black_car_u"});
                ret.get("black_car_d").put(4, new String[]{"1", "black_car_r"});

                // Вверх
                ret.put("black_car_u", new HashMap<Integer, String[]>());
                ret.get("black_car_u").put(1, new String[]{"1", "black_car_u"});
                ret.get("black_car_u").put(2, new String[]{"1", "black_car_r"});
                ret.get("black_car_u").put(3, new String[]{"1", "black_car_d"});
                ret.get("black_car_u").put(4, new String[]{"1", "black_car_l"});

                // Влево
                ret.put("black_car_l", new HashMap<Integer, String[]>());
                ret.get("black_car_l").put(1, new String[]{"1", "black_car_l"});
                ret.get("black_car_l").put(2, new String[]{"1", "black_car_u"});
                ret.get("black_car_l").put(3, new String[]{"1", "black_car_r"});
                ret.get("black_car_l").put(4, new String[]{"1", "black_car_d"});

                // Вправо
                ret.put("black_car_r", new HashMap<Integer, String[]>());
                ret.get("black_car_r").put(1, new String[]{"1", "black_car_r"});
                ret.get("black_car_r").put(2, new String[]{"1", "black_car_d"});
                ret.get("black_car_r").put(3, new String[]{"1", "black_car_l"});
                ret.get("black_car_r").put(4, new String[]{"1", "black_car_u"});

            }

            // Зеленая машина
            {

                // Вниз
                ret.put("green_car_d", new HashMap<Integer, String[]>());
                ret.get("green_car_d").put(1, new String[]{"1", "green_car_d"});
                ret.get("green_car_d").put(2, new String[]{"1", "green_car_l"});
                ret.get("green_car_d").put(3, new String[]{"1", "green_car_u"});
                ret.get("green_car_d").put(4, new String[]{"1", "green_car_r"});

                // Вверх
                ret.put("green_car_u", new HashMap<Integer, String[]>());
                ret.get("green_car_u").put(1, new String[]{"1", "green_car_u"});
                ret.get("green_car_u").put(2, new String[]{"1", "green_car_r"});
                ret.get("green_car_u").put(3, new String[]{"1", "green_car_d"});
                ret.get("green_car_u").put(4, new String[]{"1", "green_car_l"});

                // Влево
                ret.put("green_car_l", new HashMap<Integer, String[]>());
                ret.get("green_car_l").put(1, new String[]{"1", "green_car_l"});
                ret.get("green_car_l").put(2, new String[]{"1", "green_car_u"});
                ret.get("green_car_l").put(3, new String[]{"1", "green_car_r"});
                ret.get("green_car_l").put(4, new String[]{"1", "green_car_d"});

                // Вправо
                ret.put("green_car_r", new HashMap<Integer, String[]>());
                ret.get("green_car_r").put(1, new String[]{"1", "green_car_r"});
                ret.get("green_car_r").put(2, new String[]{"1", "green_car_d"});
                ret.get("green_car_r").put(3, new String[]{"1", "green_car_l"});
                ret.get("green_car_r").put(4, new String[]{"1", "green_car_u"});

            }

            // Коричневая машина
            {

                // Вниз
                ret.put("brown_car_d", new HashMap<Integer, String[]>());
                ret.get("brown_car_d").put(1, new String[]{"1", "brown_car_d"});
                ret.get("brown_car_d").put(2, new String[]{"1", "brown_car_l"});
                ret.get("brown_car_d").put(3, new String[]{"1", "brown_car_u"});
                ret.get("brown_car_d").put(4, new String[]{"1", "brown_car_r"});

                // Вверх
                ret.put("brown_car_u", new HashMap<Integer, String[]>());
                ret.get("brown_car_u").put(1, new String[]{"1", "brown_car_u"});
                ret.get("brown_car_u").put(2, new String[]{"1", "brown_car_r"});
                ret.get("brown_car_u").put(3, new String[]{"1", "brown_car_d"});
                ret.get("brown_car_u").put(4, new String[]{"1", "brown_car_l"});

                // Влево
                ret.put("brown_car_l", new HashMap<Integer, String[]>());
                ret.get("brown_car_l").put(1, new String[]{"1", "brown_car_l"});
                ret.get("brown_car_l").put(2, new String[]{"1", "brown_car_u"});
                ret.get("brown_car_l").put(3, new String[]{"1", "brown_car_r"});
                ret.get("brown_car_l").put(4, new String[]{"1", "brown_car_d"});

                // Вправо
                ret.put("brown_car_r", new HashMap<Integer, String[]>());
                ret.get("brown_car_r").put(1, new String[]{"1", "brown_car_r"});
                ret.get("brown_car_r").put(2, new String[]{"1", "brown_car_d"});
                ret.get("brown_car_r").put(3, new String[]{"1", "brown_car_l"});
                ret.get("brown_car_r").put(4, new String[]{"1", "brown_car_u"});

            }

            // Белая машина
            {

                // Вниз
                ret.put("white_car_d", new HashMap<Integer, String[]>());
                ret.get("white_car_d").put(1, new String[]{"1", "white_car_d"});
                ret.get("white_car_d").put(2, new String[]{"1", "white_car_l"});
                ret.get("white_car_d").put(3, new String[]{"1", "white_car_u"});
                ret.get("white_car_d").put(4, new String[]{"1", "white_car_r"});

                // Вверх
                ret.put("white_car_u", new HashMap<Integer, String[]>());
                ret.get("white_car_u").put(1, new String[]{"1", "white_car_u"});
                ret.get("white_car_u").put(2, new String[]{"1", "white_car_r"});
                ret.get("white_car_u").put(3, new String[]{"1", "white_car_d"});
                ret.get("white_car_u").put(4, new String[]{"1", "white_car_l"});

                // Влево
                ret.put("white_car_l", new HashMap<Integer, String[]>());
                ret.get("white_car_l").put(1, new String[]{"1", "white_car_l"});
                ret.get("white_car_l").put(2, new String[]{"1", "white_car_u"});
                ret.get("white_car_l").put(3, new String[]{"1", "white_car_r"});
                ret.get("white_car_l").put(4, new String[]{"1", "white_car_d"});

                // Вправо
                ret.put("white_car_r", new HashMap<Integer, String[]>());
                ret.get("white_car_r").put(1, new String[]{"1", "white_car_r"});
                ret.get("white_car_r").put(2, new String[]{"1", "white_car_d"});
                ret.get("white_car_r").put(3, new String[]{"1", "white_car_l"});
                ret.get("white_car_r").put(4, new String[]{"1", "white_car_u"});

            }

            // Такси
            {

                // Вниз
                ret.put("taxi_car_d", new HashMap<Integer, String[]>());
                ret.get("taxi_car_d").put(1, new String[]{"1", "taxi_car_d"});
                ret.get("taxi_car_d").put(2, new String[]{"1", "taxi_car_l"});
                ret.get("taxi_car_d").put(3, new String[]{"1", "taxi_car_u"});
                ret.get("taxi_car_d").put(4, new String[]{"1", "taxi_car_r"});

                // Вверх
                ret.put("taxi_car_u", new HashMap<Integer, String[]>());
                ret.get("taxi_car_u").put(1, new String[]{"1", "taxi_car_u"});
                ret.get("taxi_car_u").put(2, new String[]{"1", "taxi_car_r"});
                ret.get("taxi_car_u").put(3, new String[]{"1", "taxi_car_d"});
                ret.get("taxi_car_u").put(4, new String[]{"1", "taxi_car_l"});

                // Влево
                ret.put("taxi_car_l", new HashMap<Integer, String[]>());
                ret.get("taxi_car_l").put(1, new String[]{"1", "taxi_car_l"});
                ret.get("taxi_car_l").put(2, new String[]{"1", "taxi_car_u"});
                ret.get("taxi_car_l").put(3, new String[]{"1", "taxi_car_r"});
                ret.get("taxi_car_l").put(4, new String[]{"1", "taxi_car_d"});

                // Вправо
                ret.put("taxi_car_r", new HashMap<Integer, String[]>());
                ret.get("taxi_car_r").put(1, new String[]{"1", "taxi_car_r"});
                ret.get("taxi_car_r").put(2, new String[]{"1", "taxi_car_d"});
                ret.get("taxi_car_r").put(3, new String[]{"1", "taxi_car_l"});
                ret.get("taxi_car_r").put(4, new String[]{"1", "taxi_car_u"});

            }

            // Полицейская машина
            {

                // Вниз
                ret.put("police_car_d", new HashMap<Integer, String[]>());
                ret.get("police_car_d").put(1, new String[]{"1", "police_car_d"});
                ret.get("police_car_d").put(2, new String[]{"1", "police_car_l"});
                ret.get("police_car_d").put(3, new String[]{"1", "police_car_u"});
                ret.get("police_car_d").put(4, new String[]{"1", "police_car_r"});

                // Вверх
                ret.put("police_car_u", new HashMap<Integer, String[]>());
                ret.get("police_car_u").put(1, new String[]{"1", "police_car_u"});
                ret.get("police_car_u").put(2, new String[]{"1", "police_car_r"});
                ret.get("police_car_u").put(3, new String[]{"1", "police_car_d"});
                ret.get("police_car_u").put(4, new String[]{"1", "police_car_l"});

                // Влево
                ret.put("police_car_l", new HashMap<Integer, String[]>());
                ret.get("police_car_l").put(1, new String[]{"1", "police_car_l"});
                ret.get("police_car_l").put(2, new String[]{"1", "police_car_u"});
                ret.get("police_car_l").put(3, new String[]{"1", "police_car_r"});
                ret.get("police_car_l").put(4, new String[]{"1", "police_car_d"});

                // Вправо
                ret.put("police_car_r", new HashMap<Integer, String[]>());
                ret.get("police_car_r").put(1, new String[]{"1", "police_car_r"});
                ret.get("police_car_r").put(2, new String[]{"1", "police_car_d"});
                ret.get("police_car_r").put(3, new String[]{"1", "police_car_l"});
                ret.get("police_car_r").put(4, new String[]{"1", "police_car_u"});

            }

        }

        // Могильная плита
        {

            // Вниз
            ret.put("monument_d", new HashMap<Integer, String[]>());
            ret.get("monument_d").put(1, new String[]{"2", "monument_d"});
            ret.get("monument_d").put(2, new String[]{"2", "monument_h"});
            ret.get("monument_d").put(3, new String[]{"2", "monument_u"});
            ret.get("monument_d").put(4, new String[]{"2", "monument_h"});

            // Вверх
            ret.put("monument_u", new HashMap<Integer, String[]>());
            ret.get("monument_u").put(1, new String[]{"2", "monument_u"});
            ret.get("monument_u").put(2, new String[]{"2", "monument_h"});
            ret.get("monument_u").put(3, new String[]{"2", "monument_d"});
            ret.get("monument_u").put(4, new String[]{"2", "monument_h"});

            // Влево
            ret.put("monument_l", new HashMap<Integer, String[]>());
            ret.get("monument_l").put(1, new String[]{"2", "monument_h"});
            ret.get("monument_l").put(2, new String[]{"2", "monument_u"});
            ret.get("monument_l").put(3, new String[]{"2", "monument_h"});
            ret.get("monument_l").put(4, new String[]{"2", "monument_d"});

            // Вправо
            ret.put("monument_r", new HashMap<Integer, String[]>());
            ret.get("monument_r").put(1, new String[]{"2", "monument_h"});
            ret.get("monument_r").put(2, new String[]{"2", "monument_d"});
            ret.get("monument_r").put(3, new String[]{"2", "monument_h"});
            ret.get("monument_r").put(4, new String[]{"2", "monument_u"});

        }

        // Остановка
        {

            // Вниз
            ret.put("bus_stop_d", new HashMap<Integer, String[]>());
            ret.get("bus_stop_d").put(1, new String[]{"2", "bus_stop_d"});
            ret.get("bus_stop_d").put(2, new String[]{"2", "bus_stop_l"});
            ret.get("bus_stop_d").put(3, new String[]{"2", "bus_stop_u"});
            ret.get("bus_stop_d").put(4, new String[]{"2", "bus_stop_r"});

            // Вверх
            ret.put("bus_stop_u", new HashMap<Integer, String[]>());
            ret.get("bus_stop_u").put(1, new String[]{"2", "bus_stop_u"});
            ret.get("bus_stop_u").put(2, new String[]{"2", "bus_stop_r"});
            ret.get("bus_stop_u").put(3, new String[]{"2", "bus_stop_d"});
            ret.get("bus_stop_u").put(4, new String[]{"2", "bus_stop_l"});

            // Влево
            ret.put("bus_stop_l", new HashMap<Integer, String[]>());
            ret.get("bus_stop_l").put(1, new String[]{"2", "bus_stop_l"});
            ret.get("bus_stop_l").put(2, new String[]{"2", "bus_stop_u"});
            ret.get("bus_stop_l").put(3, new String[]{"2", "bus_stop_r"});
            ret.get("bus_stop_l").put(4, new String[]{"2", "bus_stop_d"});

            // Вправо
            ret.put("bus_stop_r", new HashMap<Integer, String[]>());
            ret.get("bus_stop_r").put(1, new String[]{"2", "bus_stop_r"});
            ret.get("bus_stop_r").put(2, new String[]{"2", "bus_stop_d"});
            ret.get("bus_stop_r").put(3, new String[]{"2", "bus_stop_l"});
            ret.get("bus_stop_r").put(4, new String[]{"2", "bus_stop_u"});

        }

        // Памятники
        {

            // Желтая пирамида
            ret.put("pyramid1", new HashMap<Integer, String[]>());
            ret.get("pyramid1").put(1, new String[]{"2", "pyramid1"});
            ret.get("pyramid1").put(2, new String[]{"2", "pyramid1"});
            ret.get("pyramid1").put(3, new String[]{"2", "pyramid1"});
            ret.get("pyramid1").put(4, new String[]{"2", "pyramid1"});

            // Серая пирамида
            ret.put("pyramid2", new HashMap<Integer, String[]>());
            ret.get("pyramid2").put(1, new String[]{"2", "pyramid2"});
            ret.get("pyramid2").put(2, new String[]{"2", "pyramid2"});
            ret.get("pyramid2").put(3, new String[]{"2", "pyramid2"});
            ret.get("pyramid2").put(4, new String[]{"2", "pyramid2"});

            // Сфинкс
            ret.put("sphinx", new HashMap<Integer, String[]>());
            ret.get("sphinx").put(1, new String[]{"2", "sphinx"});
            ret.get("sphinx").put(2, new String[]{"2", "sphinx"});
            ret.get("sphinx").put(3, new String[]{"2", "sphinx"});
            ret.get("sphinx").put(4, new String[]{"2", "sphinx"});

            // Памятник с мечом
            ret.put("monument1", new HashMap<Integer, String[]>());
            ret.get("monument1").put(1, new String[]{"2", "monument1"});
            ret.get("monument1").put(2, new String[]{"2", "monument1"});
            ret.get("monument1").put(3, new String[]{"2", "monument1"});
            ret.get("monument1").put(4, new String[]{"2", "monument1"});

            // Женский памятник
            ret.put("monument2", new HashMap<Integer, String[]>());
            ret.get("monument2").put(1, new String[]{"2", "monument2"});
            ret.get("monument2").put(2, new String[]{"2", "monument2"});
            ret.get("monument2").put(3, new String[]{"2", "monument2"});
            ret.get("monument2").put(4, new String[]{"2", "monument2"});

            // Уличный фонарь
            ret.put("lamppost", new HashMap<Integer, String[]>());
            ret.get("lamppost").put(1, new String[]{"2", "lamppost"});
            ret.get("lamppost").put(2, new String[]{"2", "lamppost"});
            ret.get("lamppost").put(3, new String[]{"2", "lamppost"});
            ret.get("lamppost").put(4, new String[]{"2", "lamppost"});

        }

        // Знак пешеходного перехода
        {

            // Вниз
            ret.put("pedestrian_sign_d", new HashMap<Integer, String[]>());
            ret.get("pedestrian_sign_d").put(1, new String[]{"2", "pedestrian_sign_d"});
            ret.get("pedestrian_sign_d").put(2, new String[]{"2", "pedestrian_sign_l"});
            ret.get("pedestrian_sign_d").put(3, new String[]{"2", "pedestrian_sign_u"});
            ret.get("pedestrian_sign_d").put(4, new String[]{"2", "pedestrian_sign_r"});

            // Вверх
            ret.put("pedestrian_sign_u", new HashMap<Integer, String[]>());
            ret.get("pedestrian_sign_u").put(1, new String[]{"2", "pedestrian_sign_u"});
            ret.get("pedestrian_sign_u").put(2, new String[]{"2", "pedestrian_sign_r"});
            ret.get("pedestrian_sign_u").put(3, new String[]{"2", "pedestrian_sign_d"});
            ret.get("pedestrian_sign_u").put(4, new String[]{"2", "pedestrian_sign_l"});

            // Влево
            ret.put("pedestrian_sign_l", new HashMap<Integer, String[]>());
            ret.get("pedestrian_sign_l").put(1, new String[]{"2", "pedestrian_sign_l"});
            ret.get("pedestrian_sign_l").put(2, new String[]{"2", "pedestrian_sign_u"});
            ret.get("pedestrian_sign_l").put(3, new String[]{"2", "pedestrian_sign_r"});
            ret.get("pedestrian_sign_l").put(4, new String[]{"2", "pedestrian_sign_d"});

            // Вправо
            ret.put("pedestrian_sign_r", new HashMap<Integer, String[]>());
            ret.get("pedestrian_sign_r").put(1, new String[]{"2", "pedestrian_sign_r"});
            ret.get("pedestrian_sign_r").put(2, new String[]{"2", "pedestrian_sign_d"});
            ret.get("pedestrian_sign_r").put(3, new String[]{"2", "pedestrian_sign_l"});
            ret.get("pedestrian_sign_r").put(4, new String[]{"2", "pedestrian_sign_u"});

        }

        // Рынок
        {

            // Мясо
            {

                // Вниз
                ret.put("market1_d", new HashMap<Integer, String[]>());
                ret.get("market1_d").put(1, new String[]{"2", "market1_d"});
                ret.get("market1_d").put(2, new String[]{"2", "market_l"});
                ret.get("market1_d").put(3, new String[]{"2", "market_u"});
                ret.get("market1_d").put(4, new String[]{"2", "market_r"});

                // Вверх
                ret.put("market_u", new HashMap<Integer, String[]>());
                ret.get("market_u").put(1, new String[]{"2", "market_u"});
                ret.get("market_u").put(2, new String[]{"2", "market_r"});
                ret.get("market_u").put(3, new String[]{"2", "market1_d"});
                ret.get("market_u").put(4, new String[]{"2", "market_l"});

                // Влево
                ret.put("market_l", new HashMap<Integer, String[]>());
                ret.get("market_l").put(1, new String[]{"2", "market_l"});
                ret.get("market_l").put(2, new String[]{"2", "market_u"});
                ret.get("market_l").put(3, new String[]{"2", "market_r"});
                ret.get("market_l").put(4, new String[]{"2", "market1_d"});

                // Вправо
                ret.put("market_r", new HashMap<Integer, String[]>());
                ret.get("market_r").put(1, new String[]{"2", "market_r"});
                ret.get("market_r").put(2, new String[]{"2", "market1_d"});
                ret.get("market_r").put(3, new String[]{"2", "market_l"});
                ret.get("market_r").put(4, new String[]{"2", "market_u"});

            }

            // Овощи
            {

                // Вниз
                ret.put("market2_d", new HashMap<Integer, String[]>());
                ret.get("market2_d").put(1, new String[]{"2", "market2_d"});
                ret.get("market2_d").put(2, new String[]{"2", "market_l"});
                ret.get("market2_d").put(3, new String[]{"2", "market_u"});
                ret.get("market2_d").put(4, new String[]{"2", "market_r"});

                // Вверх
                ret.put("market_u", new HashMap<Integer, String[]>());
                ret.get("market_u").put(1, new String[]{"2", "market_u"});
                ret.get("market_u").put(2, new String[]{"2", "market_r"});
                ret.get("market_u").put(3, new String[]{"2", "market2_d"});
                ret.get("market_u").put(4, new String[]{"2", "market_l"});

                // Влево
                ret.put("market_l", new HashMap<Integer, String[]>());
                ret.get("market_l").put(1, new String[]{"2", "market_l"});
                ret.get("market_l").put(2, new String[]{"2", "market_u"});
                ret.get("market_l").put(3, new String[]{"2", "market_r"});
                ret.get("market_l").put(4, new String[]{"2", "market2_d"});

                // Вправо
                ret.put("market_r", new HashMap<Integer, String[]>());
                ret.get("market_r").put(1, new String[]{"2", "market_r"});
                ret.get("market_r").put(2, new String[]{"2", "market2_d"});
                ret.get("market_r").put(3, new String[]{"2", "market_l"});
                ret.get("market_r").put(4, new String[]{"2", "market_u"});

            }

            // Ягоды
            {

                // Вниз
                ret.put("market3_d", new HashMap<Integer, String[]>());
                ret.get("market3_d").put(1, new String[]{"2", "market3_d"});
                ret.get("market3_d").put(2, new String[]{"2", "market_l"});
                ret.get("market3_d").put(3, new String[]{"2", "market_u"});
                ret.get("market3_d").put(4, new String[]{"2", "market_r"});

                // Вверх
                ret.put("market_u", new HashMap<Integer, String[]>());
                ret.get("market_u").put(1, new String[]{"2", "market_u"});
                ret.get("market_u").put(2, new String[]{"2", "market_r"});
                ret.get("market_u").put(3, new String[]{"2", "market3_d"});
                ret.get("market_u").put(4, new String[]{"2", "market_l"});

                // Влево
                ret.put("market_l", new HashMap<Integer, String[]>());
                ret.get("market_l").put(1, new String[]{"2", "market_l"});
                ret.get("market_l").put(2, new String[]{"2", "market_u"});
                ret.get("market_l").put(3, new String[]{"2", "market_r"});
                ret.get("market_l").put(4, new String[]{"2", "market3_d"});

                // Вправо
                ret.put("market_r", new HashMap<Integer, String[]>());
                ret.get("market_r").put(1, new String[]{"2", "market_r"});
                ret.get("market_r").put(2, new String[]{"2", "market3_d"});
                ret.get("market_r").put(3, new String[]{"2", "market_l"});
                ret.get("market_r").put(4, new String[]{"2", "market_u"});

            }

            // Рыба
            {

                // Вниз
                ret.put("market4_d", new HashMap<Integer, String[]>());
                ret.get("market4_d").put(1, new String[]{"2", "market4_d"});
                ret.get("market4_d").put(2, new String[]{"2", "market_l"});
                ret.get("market4_d").put(3, new String[]{"2", "market_u"});
                ret.get("market4_d").put(4, new String[]{"2", "market_r"});

                // Вверх
                ret.put("market_u", new HashMap<Integer, String[]>());
                ret.get("market_u").put(1, new String[]{"2", "market_u"});
                ret.get("market_u").put(2, new String[]{"2", "market_r"});
                ret.get("market_u").put(3, new String[]{"2", "market4_d"});
                ret.get("market_u").put(4, new String[]{"2", "market_l"});

                // Влево
                ret.put("market_l", new HashMap<Integer, String[]>());
                ret.get("market_l").put(1, new String[]{"2", "market_l"});
                ret.get("market_l").put(2, new String[]{"2", "market_u"});
                ret.get("market_l").put(3, new String[]{"2", "market_r"});
                ret.get("market_l").put(4, new String[]{"2", "market4_d"});

                // Вправо
                ret.put("market_r", new HashMap<Integer, String[]>());
                ret.get("market_r").put(1, new String[]{"2", "market_r"});
                ret.get("market_r").put(2, new String[]{"2", "market4_d"});
                ret.get("market_r").put(3, new String[]{"2", "market_l"});
                ret.get("market_r").put(4, new String[]{"2", "market_u"});

            }

            // Одежда
            {

                // Вниз
                ret.put("market5_d", new HashMap<Integer, String[]>());
                ret.get("market5_d").put(1, new String[]{"2", "market5_d"});
                ret.get("market5_d").put(2, new String[]{"2", "market_l"});
                ret.get("market5_d").put(3, new String[]{"2", "market_u"});
                ret.get("market5_d").put(4, new String[]{"2", "market_r"});

                // Вверх
                ret.put("market_u", new HashMap<Integer, String[]>());
                ret.get("market_u").put(1, new String[]{"2", "market_u"});
                ret.get("market_u").put(2, new String[]{"2", "market_r"});
                ret.get("market_u").put(3, new String[]{"2", "market5_d"});
                ret.get("market_u").put(4, new String[]{"2", "market_l"});

                // Влево
                ret.put("market_l", new HashMap<Integer, String[]>());
                ret.get("market_l").put(1, new String[]{"2", "market_l"});
                ret.get("market_l").put(2, new String[]{"2", "market_u"});
                ret.get("market_l").put(3, new String[]{"2", "market_r"});
                ret.get("market_l").put(4, new String[]{"2", "market5_d"});

                // Вправо
                ret.put("market_r", new HashMap<Integer, String[]>());
                ret.get("market_r").put(1, new String[]{"2", "market_r"});
                ret.get("market_r").put(2, new String[]{"2", "market5_d"});
                ret.get("market_r").put(3, new String[]{"2", "market_l"});
                ret.get("market_r").put(4, new String[]{"2", "market_u"});

            }

            // Табак
            {

                // Вниз
                ret.put("market6_d", new HashMap<Integer, String[]>());
                ret.get("market6_d").put(1, new String[]{"2", "market6_d"});
                ret.get("market6_d").put(2, new String[]{"2", "market_l"});
                ret.get("market6_d").put(3, new String[]{"2", "market_u"});
                ret.get("market6_d").put(4, new String[]{"2", "market_r"});

                // Вверх
                ret.put("market_u", new HashMap<Integer, String[]>());
                ret.get("market_u").put(1, new String[]{"2", "market_u"});
                ret.get("market_u").put(2, new String[]{"2", "market_r"});
                ret.get("market_u").put(3, new String[]{"2", "market6_d"});
                ret.get("market_u").put(4, new String[]{"2", "market_l"});

                // Влево
                ret.put("market_l", new HashMap<Integer, String[]>());
                ret.get("market_l").put(1, new String[]{"2", "market_l"});
                ret.get("market_l").put(2, new String[]{"2", "market_u"});
                ret.get("market_l").put(3, new String[]{"2", "market_r"});
                ret.get("market_l").put(4, new String[]{"2", "market6_d"});

                // Вправо
                ret.put("market_r", new HashMap<Integer, String[]>());
                ret.get("market_r").put(1, new String[]{"2", "market_r"});
                ret.get("market_r").put(2, new String[]{"2", "market6_d"});
                ret.get("market_r").put(3, new String[]{"2", "market_l"});
                ret.get("market_r").put(4, new String[]{"2", "market_u"});

            }

        }

        // Сельское хозяйство
        {

            // Стог сена
            ret.put("haystack1", new HashMap<Integer, String[]>());
            ret.get("haystack1").put(1, new String[]{"2", "haystack1"});
            ret.get("haystack1").put(2, new String[]{"2", "haystack1"});
            ret.get("haystack1").put(3, new String[]{"2", "haystack1"});
            ret.get("haystack1").put(4, new String[]{"2", "haystack1"});

            // Стог сена с вилами
            ret.put("haystack2", new HashMap<Integer, String[]>());
            ret.get("haystack2").put(1, new String[]{"2", "haystack2"});
            ret.get("haystack2").put(2, new String[]{"2", "haystack2"});
            ret.get("haystack2").put(3, new String[]{"2", "haystack2"});
            ret.get("haystack2").put(4, new String[]{"2", "haystack2"});

            // Пугало
            ret.put("scarecrow", new HashMap<Integer, String[]>());
            ret.get("scarecrow").put(1, new String[]{"2", "scarecrow"});
            ret.get("scarecrow").put(2, new String[]{"2", "scarecrow"});
            ret.get("scarecrow").put(3, new String[]{"2", "scarecrow"});
            ret.get("scarecrow").put(4, new String[]{"2", "scarecrow"});

            // Колодец
            ret.put("pit1", new HashMap<Integer, String[]>());
            ret.get("pit1").put(1, new String[]{"1", "pit1"});
            ret.get("pit1").put(2, new String[]{"1", "pit1"});
            ret.get("pit1").put(3, new String[]{"1", "pit1"});
            ret.get("pit1").put(4, new String[]{"1", "pit1"});

            // Колодец с ведром
            ret.put("pit2", new HashMap<Integer, String[]>());
            ret.get("pit2").put(1, new String[]{"2", "pit2"});
            ret.get("pit2").put(2, new String[]{"2", "pit2"});
            ret.get("pit2").put(3, new String[]{"2", "pit2"});
            ret.get("pit2").put(4, new String[]{"2", "pit2"});

            // Пенек
            ret.put("stump1", new HashMap<Integer, String[]>());
            ret.get("stump1").put(1, new String[]{"1", "stump1"});
            ret.get("stump1").put(2, new String[]{"1", "stump1"});
            ret.get("stump1").put(3, new String[]{"1", "stump1"});
            ret.get("stump1").put(4, new String[]{"1", "stump1"});

            // Пенек с топором
            ret.put("stump2", new HashMap<Integer, String[]>());
            ret.get("stump2").put(1, new String[]{"1", "stump2"});
            ret.get("stump2").put(2, new String[]{"1", "stump2"});
            ret.get("stump2").put(3, new String[]{"1", "stump2"});
            ret.get("stump2").put(4, new String[]{"1", "stump2"});

            // Кукуруза
            ret.put("corn", new HashMap<Integer, String[]>());
            ret.get("corn").put(1, new String[]{"2", "corn"});
            ret.get("corn").put(2, new String[]{"2", "corn"});
            ret.get("corn").put(3, new String[]{"2", "corn"});
            ret.get("corn").put(4, new String[]{"2", "corn"});

            // Пшеница
            ret.put("wheat", new HashMap<Integer, String[]>());
            ret.get("wheat").put(1, new String[]{"2", "wheat"});
            ret.get("wheat").put(2, new String[]{"2", "wheat"});
            ret.get("wheat").put(3, new String[]{"2", "wheat"});
            ret.get("wheat").put(4, new String[]{"2", "wheat"});

            // Подсолнух
            ret.put("sunflower", new HashMap<Integer, String[]>());
            ret.get("sunflower").put(1, new String[]{"2", "sunflower"});
            ret.get("sunflower").put(2, new String[]{"2", "sunflower"});
            ret.get("sunflower").put(3, new String[]{"2", "sunflower"});
            ret.get("sunflower").put(4, new String[]{"2", "sunflower"});

            // Тыква
            ret.put("pumpkin", new HashMap<Integer, String[]>());
            ret.get("pumpkin").put(1, new String[]{"2", "pumpkin"});
            ret.get("pumpkin").put(2, new String[]{"2", "pumpkin"});
            ret.get("pumpkin").put(3, new String[]{"2", "pumpkin"});
            ret.get("pumpkin").put(4, new String[]{"2", "pumpkin"});

        }

        // Скамейка без спинки
        {

            // Вниз
            ret.put("bench2_d", new HashMap<Integer, String[]>());
            ret.get("bench2_d").put(1, new String[]{"1", "bench2_h"});
            ret.get("bench2_d").put(2, new String[]{"1", "bench2_v"});
            ret.get("bench2_d").put(3, new String[]{"1", "bench2_h"});
            ret.get("bench2_d").put(4, new String[]{"1", "bench2_v"});

            // Вверх
            ret.put("bench2_u", new HashMap<Integer, String[]>());
            ret.get("bench2_u").put(1, new String[]{"1", "bench2_h"});
            ret.get("bench2_u").put(2, new String[]{"1", "bench2_v"});
            ret.get("bench2_u").put(3, new String[]{"1", "bench2_h"});
            ret.get("bench2_u").put(4, new String[]{"1", "bench2_v"});

            // Влево
            ret.put("bench2_l", new HashMap<Integer, String[]>());
            ret.get("bench2_l").put(1, new String[]{"1", "bench2_v"});
            ret.get("bench2_l").put(2, new String[]{"1", "bench2_h"});
            ret.get("bench2_l").put(3, new String[]{"1", "bench2_v"});
            ret.get("bench2_l").put(4, new String[]{"1", "bench2_h"});

            // Вправо
            ret.put("bench2_r", new HashMap<Integer, String[]>());
            ret.get("bench2_r").put(1, new String[]{"1", "bench2_v"});
            ret.get("bench2_r").put(2, new String[]{"1", "bench2_h"});
            ret.get("bench2_r").put(3, new String[]{"1", "bench2_v"});
            ret.get("bench2_r").put(4, new String[]{"1", "bench2_h"});

        }

        // Скамейка со спинкой
        {

            // Вниз
            ret.put("bench_d", new HashMap<Integer, String[]>());
            ret.get("bench_d").put(1, new String[]{"2", "bench_d"});
            ret.get("bench_d").put(2, new String[]{"2", "bench_l"});
            ret.get("bench_d").put(3, new String[]{"2", "bench_u"});
            ret.get("bench_d").put(4, new String[]{"2", "bench_r"});

            // Вверх
            ret.put("bench_u", new HashMap<Integer, String[]>());
            ret.get("bench_u").put(1, new String[]{"2", "bench_u"});
            ret.get("bench_u").put(2, new String[]{"2", "bench_r"});
            ret.get("bench_u").put(3, new String[]{"2", "bench_d"});
            ret.get("bench_u").put(4, new String[]{"2", "bench_l"});

            // Влево
            ret.put("bench_l", new HashMap<Integer, String[]>());
            ret.get("bench_l").put(1, new String[]{"2", "bench_l"});
            ret.get("bench_l").put(2, new String[]{"2", "bench_u"});
            ret.get("bench_l").put(3, new String[]{"2", "bench_r"});
            ret.get("bench_l").put(4, new String[]{"2", "bench_d"});

            // Вправо
            ret.put("bench_r", new HashMap<Integer, String[]>());
            ret.get("bench_r").put(1, new String[]{"2", "bench_r"});
            ret.get("bench_r").put(2, new String[]{"2", "bench_d"});
            ret.get("bench_r").put(3, new String[]{"2", "bench_l"});
            ret.get("bench_r").put(4, new String[]{"2", "bench_u"});

        }

        // Ступеньки из дерева
        {

            // Вниз
            ret.put("step_beton_d", new HashMap<Integer, String[]>());
            ret.get("step_beton_d").put(1, new String[]{"2", "step_beton_d"});
            ret.get("step_beton_d").put(2, new String[]{"2", "step_beton_l"});
            ret.get("step_beton_d").put(3, new String[]{"2", "step_beton_u"});
            ret.get("step_beton_d").put(4, new String[]{"2", "step_beton_r"});

            // Вверх
            ret.put("step_beton_u", new HashMap<Integer, String[]>());
            ret.get("step_beton_u").put(1, new String[]{"2", "step_beton_u"});
            ret.get("step_beton_u").put(2, new String[]{"2", "step_beton_r"});
            ret.get("step_beton_u").put(3, new String[]{"2", "step_beton_d"});
            ret.get("step_beton_u").put(4, new String[]{"2", "step_beton_l"});

            // Влево
            ret.put("step_beton_l", new HashMap<Integer, String[]>());
            ret.get("step_beton_l").put(1, new String[]{"2", "step_beton_l"});
            ret.get("step_beton_l").put(2, new String[]{"2", "step_beton_u"});
            ret.get("step_beton_l").put(3, new String[]{"2", "step_beton_r"});
            ret.get("step_beton_l").put(4, new String[]{"2", "step_beton_d"});

            // Вправо
            ret.put("step_beton_r", new HashMap<Integer, String[]>());
            ret.get("step_beton_r").put(1, new String[]{"2", "step_beton_r"});
            ret.get("step_beton_r").put(2, new String[]{"2", "step_beton_d"});
            ret.get("step_beton_r").put(3, new String[]{"2", "step_beton_l"});
            ret.get("step_beton_r").put(4, new String[]{"2", "step_beton_u"});

        }

        // Ступеньки из бетона
        {

            // Вниз
            ret.put("step_wood_d", new HashMap<Integer, String[]>());
            ret.get("step_wood_d").put(1, new String[]{"2", "step_wood_d"});
            ret.get("step_wood_d").put(2, new String[]{"2", "step_wood_l"});
            ret.get("step_wood_d").put(3, new String[]{"2", "step_wood_u"});
            ret.get("step_wood_d").put(4, new String[]{"2", "step_wood_r"});

            // Вверх
            ret.put("step_wood_u", new HashMap<Integer, String[]>());
            ret.get("step_wood_u").put(1, new String[]{"2", "step_wood_u"});
            ret.get("step_wood_u").put(2, new String[]{"2", "step_wood_r"});
            ret.get("step_wood_u").put(3, new String[]{"2", "step_wood_d"});
            ret.get("step_wood_u").put(4, new String[]{"2", "step_wood_l"});

            // Влево
            ret.put("step_wood_l", new HashMap<Integer, String[]>());
            ret.get("step_wood_l").put(1, new String[]{"2", "step_wood_l"});
            ret.get("step_wood_l").put(2, new String[]{"2", "step_wood_u"});
            ret.get("step_wood_l").put(3, new String[]{"2", "step_wood_r"});
            ret.get("step_wood_l").put(4, new String[]{"2", "step_wood_d"});

            // Вправо
            ret.put("step_wood_r", new HashMap<Integer, String[]>());
            ret.get("step_wood_r").put(1, new String[]{"2", "step_wood_r"});
            ret.get("step_wood_r").put(2, new String[]{"2", "step_wood_d"});
            ret.get("step_wood_r").put(3, new String[]{"2", "step_wood_l"});
            ret.get("step_wood_r").put(4, new String[]{"2", "step_wood_u"});

        }



        return ret;
    }

}
