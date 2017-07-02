package com.swagscan.mobile.redemption.Model;

/**
 * Created by emerald on 7/2/2017.
 */
public class OrganizationModel {
    private int id;
    private String name;
    private int max_user_count;
    private String registration_code;
    private String logo;
    private String timezone;
    private int deleted_at;
    private int created_at;
    private int updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMax_user_count() {
        return max_user_count;
    }

    public void setMax_user_count(int max_user_count) {
        this.max_user_count = max_user_count;
    }

    public String getRegistration_code() {
        return registration_code;
    }

    public void setRegistration_code(String registration_code) {
        this.registration_code = registration_code;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(int deleted_at) {
        this.deleted_at = deleted_at;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }
}
