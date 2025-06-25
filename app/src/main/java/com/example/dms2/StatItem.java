package com.example.dms2;

public class StatItem {
    private int id;
    private String label;
    private String value;

    public StatItem(int id, String label, String value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    // Optional: toString for debugging
    @Override
    public String toString() {
        return "StatItem{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
