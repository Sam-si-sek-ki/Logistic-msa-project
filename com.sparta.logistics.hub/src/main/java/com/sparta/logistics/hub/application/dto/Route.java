package com.sparta.logistics.hub.application.dto;

public class Route implements Comparable<Route> {
    private String nextId;
    private int duration;

    public Route(String nextId, int duration) {
        this.nextId = nextId;
        this.duration = duration;
    }

    @Override
    public int compareTo(Route o) {
        return this.duration - o.duration;
    }
}
