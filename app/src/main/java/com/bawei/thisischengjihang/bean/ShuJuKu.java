package com.bawei.thisischengjihang.bean;

import java.util.List;

public class ShuJuKu {
    public List<Data> data;
    public static class Data{
        private String name;

        public Data( ) {

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
