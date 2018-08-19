package com.ls.gameserver.entity;

import lombok.Data;

/**
 * 所用英雄必须有的参数
 */
@Data
public class Hero {

    /**
     * 血量 Hit Point
     */
    private int hit_point;
    /**
     * 蓝量 Magic Point
     */
    private int magic_point;
    /**
     * 体力  physical strength
     */
    private int physical_strength;

    /**
     * 移速 Shift speed
     */
    private int shift_speed;

    /**
     * 攻击速度
     */
    private int attack_speed;

    /**
     * 攻击免疫
     */
    private int attack_immunity;
    /**
     * 魔法免疫
     */
    private int magic_immunity;

    /**
     * 灵魂强度
     */
    private int soul_intensity;

    /**
     * 幸运强度
     */
    private int lucky_intensity;

    /**
     * 意志强度
     */
    private int will_intensity;

    /**
     * 精准度
     */
    private int accuracy;

}
