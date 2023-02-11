package iss.entity;

public class staff_preferences {
    private String preference_type;
    private String staffId;
    private String preference_value;

    public String getPreference_type() {
        return preference_type;
    }

    public void setPreference_type(String preference_type) {
        this.preference_type = preference_type;
    }


    public String getPreference_value() {
        return preference_value;
    }

    public void setPreference_value(String preference_value) {
        this.preference_value = preference_value;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }


}

