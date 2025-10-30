package com.activity.data;

import java.util.Arrays;
import java.util.List;

public class TestData {
    // --- Home Page ---

    // --- Settings Page ---

    // Settings Sliders Page ---
    public static final int ROUND_LENGTH_MIN = 10;
    public static final int ROUND_LENGTH_MAX = 60;
    public static final int ROUND_LENGTH_DEFAULT = 24;
    public static final int WORDS_AMOUNT_MIN = 2;
    public static final int WORDS_AMOUNT_MAX = 10;
    public static final int WORDS_AMOUNT_DEFAULT = 4;

    // --- Add Players Page ---
    public static final int DEFAULT_PLAYER_COUNT = 9;
    public static final int INVALID_PLAYER_COUNT = 3;
    public static final int DEFAULT_TEAM_COUNT = 3;
    public static final int INVALID_TEAM_COUNT = 1;
    public static final List<String> DEFAULT_TEAM_NAMES = Arrays.asList("Team 1", "Team2", "Team 3");
    public static final String TEAM_NAME_EDIT = "The W1nners";
    public static final List<String> PLAYER_NAMES = Arrays.asList(
            "Alice", "Bob", "Charlie", "David",
            "Eve", "Frank", "Grace", "Hannah",
            "Ian", "Jack", "Karen", "Leo"
    );

    public static final List<String> WORDS = Arrays.asList(
            "apple", "banana", "cat", "dog", "egg",
            "fish", "goat", "hat", "ice", "jam",
            "kite", "lamp", "moon", "nest", "owl",
            "pen", "queen", "rose", "sun", "tree",
            "car", "book", "chair", "desk", "flower",
            "guitar", "house", "key", "lion", "map",
            "note", "orange", "plate", "river", "star",
            "cloud", "window", "shoe", "ball", "clock",
            "code", "java", "python", "loop", "array",
            "function", "variable", "class", "object", "thread",
            "server", "client", "database", "query", "cache",
            "network", "router", "firewall", "api", "endpoint",
            "script", "debug", "login", "logout", "token",
            "stack", "heap", "branch", "commit", "merge",
            "keyboard", "mouse", "screen", "monitor", "button",
            "package", "module", "file", "folder", "notebook",
            "lamp", "moon", "sun", "tiger", "zebra",
            "rain", "snow", "storm", "wind", "beach",
            "river", "mountain", "valley", "island", "forest",
            "castle", "bridge", "cave", "road", "path"
    );

    // --- Game Page ---

    public static List<String> ROUND_TYPES = Arrays.asList("Describe", "One Word", "Show");

}

