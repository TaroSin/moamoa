package com.javaproject.employinfo;

import android.view.View;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Simple POJO model for example
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class Item {

    private String name; // 회사이름
    private String title; // 제목
    private String loclong; // 지역(요약)
    private String locshort; // 지역(자세)
    private String exp; // 경력
    private String work; // 고용형태
    private String date; // 기한
    private String edu; // 학력
    private String link; // 공고링크
    private String etc; // 그외 설명

    private View.OnClickListener requestBtnClickListener;

    public Item() {
    }

    public Item(String name, String title, String loclong, String locshort, String exp, String work, String date, String edu, String link, String etc) {
        this.name = name;
        this.title = title;
        this.loclong = loclong;
        this.locshort = locshort;
        this.exp = exp;
        this.work = work;
        this.date = date;
        this.edu = edu;
        this.link = link;
        this.etc = etc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLoclong() {
        return loclong;
    }

    public void setLoclong(String loclong) {
        this.loclong = loclong;
    }

    public String getLocshort() {
        return locshort;
    }

    public void setLocshort(String locshort) {
        this.locshort = locshort;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }


    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;

        return Objects.equals(name, item.name) && Objects.equals(title, item.title) && Objects.equals(loclong, item.loclong) && Objects.equals(locshort, item.locshort) && Objects.equals(exp, item.exp) && Objects.equals(work, item.work) && Objects.equals(date, item.date) && Objects.equals(edu, item.edu) && Objects.equals(link, item.link) && Objects.equals(etc, item.etc);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (loclong != null ? loclong.hashCode() : 0);
        result = 31 * result + (locshort != null ? locshort.hashCode() : 0);
        result = 31 * result + (exp != null ? exp.hashCode() : 0);
        result = 31 * result + (work != null ? work.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (edu != null ? edu.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (etc != null ? etc.hashCode() : 0);
        return result;
//        return Objects.hash(name, title, loclong, locshort, exp, work, date, edu, link, etc);
    }

    /**
     * @return List of elements prepared for tests
     */
    public static ArrayList<Item> getTestingList() {
        ArrayList<Item> items = new ArrayList<>();
//        items.add(new Item("$14", "$270", "W 79th St, NY, 10024", "W 139th St, NY, 10030", 3, "TODAY", "05:10 PM"));
//        items.add(new Item("$23", "$116", "W 36th St, NY, 10015", "W 114th St, NY, 10037", 10, "TODAY", "11:10 AM"));
//        items.add(new Item("$63", "$350", "W 36th St, NY, 10029", "56th Ave, NY, 10041", 0, "TODAY", "07:11 PM"));
//        items.add(new Item("$19", "$150", "12th Ave, NY, 10012", "W 57th St, NY, 10048", 8, "TODAY", "4:15 AM"));
//        items.add(new Item("$5", "$300", "56th Ave, NY, 10041", "W 36th St, NY, 10029", 0, "TODAY", "06:15 PM"));
        return items;

    }



}
