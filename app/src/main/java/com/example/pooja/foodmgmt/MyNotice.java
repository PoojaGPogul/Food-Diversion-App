package com.example.pooja.foodmgmt;



public class MyNotice {
    private String title_notice;
    private String author_notice;
    private String date_notice;
    private String date_upload;
    private String food_id;
    private String flag;
    private String color;
    private String capacity;
    private String details;


    public String getTitle_notice() {
        return title_notice;
    }

    public void setTitle_notice(String title_notice) {
        this.title_notice = title_notice;
    }

    public String getAuthor_notice() {
        return author_notice;
    }

    public void setAuthor_notice(String author_notice) {
        this.author_notice = author_notice;
    }

    public String getDate_notice() {
        return date_notice;
    }

    public void setDate_notice(String date_notice) {
        this.date_notice = date_notice;
    }

    public String getFid_notice() {
        return food_id;
    }

    public String getFlag_notice() {return flag;     }

    public String getDate_upload_notice() {return date_upload;     }

    public String getDetails_notice() {return details;     }
    
    public String getCapacity_notice() {return capacity;     }


    MyNotice(String title_notice,String author_notice,String fg,String fid,String up,String date_notice)
    {
        setAuthor_notice(author_notice);
        setDate_notice(date_notice);
        setTitle_notice(title_notice);
        this.date_upload=up;
        this.food_id=fid;
        this.flag=fg;
    }

    MyNotice(String author_notice,String fid,String upload,String cap,String dtl)
    {
        setAuthor_notice(author_notice);
        this.date_upload=upload;
        this.food_id=fid;
        this.details=dtl;
        this.capacity=cap;
    }
}

