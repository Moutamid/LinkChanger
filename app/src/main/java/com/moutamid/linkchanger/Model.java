package com.moutamid.linkchanger;

public class Model {
    String link;
    boolean rotate;

    public Model() {
    }

    public Model(String link) {
        this.link = link;
    }

    public Model(String link, boolean rotate) {
        this.link = link;
        this.rotate = rotate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isRotate() {
        return rotate;
    }

    public void setRotate(boolean rotate) {
        this.rotate = rotate;
    }
}
