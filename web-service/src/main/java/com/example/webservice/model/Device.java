package com.example.webservice.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity

public class Device {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Integer battery_power;
        private Integer blue;
        private Double clock_speed;
        private Integer dual_sim;
        private Integer fc;
        private Integer four_g;
        private Integer int_memory;
        private Double m_dep;
        private Integer mobile_wt;
        private Integer n_cores;
        private Integer pc;
        private Integer px_height;
        private Integer px_width;
        private Integer ram;
        private Integer sc_h;
        private Integer sc_w;
        private Integer talk_time;
        private Integer three_g;
        private Integer touch_screen;
        private Integer wifi;
        private Integer price_range;
    
        public Device() {
        }
    
        // Getters and setters
        public Long getId() {
            return id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
    
        public Integer getBattery_power() {
            return battery_power;
        }
    
        public void setBattery_power(Integer battery_power) {
            this.battery_power = battery_power;
        }
    
        public Integer getBlue() {
            return blue;
        }
    
        public void setBlue(Integer blue) {
            this.blue = blue;
        }
    
        public Double getClock_speed() {
                return clock_speed;
        }

        public void setClock_speed(Double clock_speed) {
                this.clock_speed = clock_speed;
        }

        public Integer getDual_sim() {
                return dual_sim;
        }

        public void setDual_sim(Integer dual_sim) {
                this.dual_sim = dual_sim;
        }

        public Integer getFc() {
                return fc;
        }

        public void setFc(Integer fc) {
                this.fc = fc;
        }

        public Integer getFour_g() {
                return four_g;
        }

        public void setFour_g(Integer four_g) {
                this.four_g = four_g;
        }

        public Integer getInt_memory() {
                return int_memory;
        }

        public void setInt_memory(Integer int_memory) {
                this.int_memory = int_memory;
        }

        public Double getM_dep() {
                return m_dep;
        }

        public void setM_dep(Double m_dep) {
                this.m_dep = m_dep;
        }

        public Integer getMobile_wt() {
                return mobile_wt;
        }

        public void setMobile_wt(Integer mobile_wt) {
                this.mobile_wt = mobile_wt;
        }

        public Integer getN_cores() {
                return n_cores;
        }

        public void setN_cores(Integer n_cores) {
                this.n_cores = n_cores;
        }

        public Integer getPc() {
                return pc;
        }

        public void setPc(Integer pc) {
                this.pc = pc;
        }

        public Integer getPx_height() {
                return px_height;
        }

        public void setPx_height(Integer px_height) {
                this.px_height = px_height;
        }

        public Integer getPx_width() {
                return px_width;
        }

        public void setPx_width(Integer px_width) {
                this.px_width = px_width;
        }

        public Integer getRam() {
                return ram;
        }

        public void setRam(Integer ram) {
                this.ram = ram;
        }

        public Integer getSc_h() {
                return sc_h;
        }

        public void setSc_h(Integer sc_h) {
                this.sc_h = sc_h;
        }

        public Integer getSc_w() {
                return sc_w;
        }

        public void setSc_w(Integer sc_w) {
                this.sc_w = sc_w;
        }

        public Integer getTalk_time() {
                return talk_time;
        }

        public void setTalk_time(Integer talk_time) {
                this.talk_time = talk_time;
        }

        public Integer getThree_g() {
                return three_g;
        }

        public void setThree_g(Integer three_g) {
                this.three_g = three_g;
        }

        public Integer getTouch_screen() {
                return touch_screen;
        }

        public void setTouch_screen(Integer touch_screen) {
                this.touch_screen = touch_screen;
        }

        public Integer getWifi() {
                return wifi;
        }

        public void setWifi(Integer wifi) {
                this.wifi = wifi;
        }

        public Integer getPrice_range() {
                return price_range;
        }

        public void setPrice_range(Integer price_range) {
                this.price_range = price_range;
        }
    }