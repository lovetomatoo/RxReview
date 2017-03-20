package com.example.guo.rxreview.model;

import java.util.List;

/**
 * Created by guo_hx on 2017/3/20 17:20.
 */

public class TestJsonBean {

    public List<RoomBean> room;
    public List<?> ad;

    public static class RoomBean {

        public int id;
        public String name;
        public int is_default;
        public String icon;
        public String slug;
        public String category_more;
        public int type;
        public int screen;
        public List<ListBean> list;

        public static class ListBean {

            public String beauty_cover;
            public int no;
            public String first_play_at;
            public String category_name;
            public String thumb;
            public String last_play_at;
            public int screen;
            public String video;
            public String title;
            public String recommend_image;
            public boolean is_shield;
            public String nick;
            public int uid;
            public String view;
            public int category_id;
            public String stream;
            public String slug;
            public String love_cover;
            public int level;
            public int like;
            public String video_quality;
            public int weight;
            public int starlight;
            public boolean check;
            public String avatar;
            public int follow;
            public int play_count;
            public int play_true;
            public int fans;
            public int max_view;
            public String default_image;
            public String last_end_at;
            public String position;
            public String create_at;
            public String last_thumb;
            public int landscape;
            public String category_slug;
            public int anniversary;
            public boolean play_status;
            public int status;
            public int coin;
            public String link;
            public String app_shuffling_image;
        }
    }

}
