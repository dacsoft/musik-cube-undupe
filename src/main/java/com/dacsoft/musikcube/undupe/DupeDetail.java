package com.dacsoft.musikcube.undupe;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DupeDetail implements Cloneable {
    int songid;//= rs.getInt("songid");
    String title;// = rs.getString("title");
    String artist;// = rs.getString("artist");
    String album;// = rs.getString("album");
    int rowct;// = rs.getInt("rowct");
    String filename;

    public DupeDetail() { }

    public DupeDetail(ResultSet rs) throws SQLException {
        songid = rs.getInt("songid");
        title = rs.getString("title");
        artist = rs.getString("artist");
        album = rs.getString("album");
        rowct = rs.getInt("rowct");
        filename = rs.getString("filename");
    }

    public DupeDetail(int songid, String title, String artist, String album, int rowct, String filename) {
        this.songid = songid;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.rowct = rowct;
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "" + rowct + " of " + songid + ":" + title + "/" + artist + "/" + album;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}


