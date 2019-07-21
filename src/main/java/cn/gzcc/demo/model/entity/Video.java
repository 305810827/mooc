package cn.gzcc.demo.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Video {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String videoname;
        private String videopicture;
        private String video;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getVideopicture() {
        return videopicture;
    }

    public void setVideopicture(String videopicture) {
        this.videopicture = videopicture;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
