package cn.gzcc.demo.model.entity;

import javax.persistence.*;

@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String section_name;
    private String section_content;
    private String link;
    private String picture;

    @ManyToOne
    private  Chapter chapter;

    public Section()
    {

    }
    public  Section(String section_name,String section_content,Chapter chapter,String link){
        this.setChapter(chapter);
        this.setSection_name(section_name);
        this.setLink(link);
        this.setSection_content(section_content);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public String getSection_content() {
        return section_content;
    }

    public void setSection_content(String section_content) {
        this.section_content = section_content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
