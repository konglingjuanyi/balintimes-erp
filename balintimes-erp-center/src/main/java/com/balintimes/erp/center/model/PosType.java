package com.balintimes.erp.center.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2015/10/29.
 */
public class PosType implements Serializable{
    private String uid ;
    private String  kid;
    private String  name;
    private int postypeorder;
    private boolean deleted;
    private String comment;
    private String creatorid;
    private String creatorname;
    private Date createtime;
    private String editorid;
    private String editorname;
    private Date edittime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostypeorder() {
        return postypeorder;
    }

    public void setPostypeorder(int postypeorder) {
        this.postypeorder = postypeorder;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(String creatorid) {
        this.creatorid = creatorid;
    }

    public String getCreatorname() {
        return creatorname;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getEditorid() {
        return editorid;
    }

    public void setEditorid(String editorid) {
        this.editorid = editorid;
    }

    public String getEditorname() {
        return editorname;
    }

    public void setEditorname(String editorname) {
        this.editorname = editorname;
    }

    public Date getEdittime() {
        return edittime;
    }

    public void setEdittime(Date edittime) {
        this.edittime = edittime;
    }
}
