# PulseTrace (Core Java)

A small console app for creating, viewing, searching, updating, and deleting pulse readings. It uses only the Java standard library. Readings stay in memory and are cleared when the program exits.

## Run it

Open PowerShell or a terminal in this folder, then run:

```text
javac PulseCategory.java PulseReading.java PulseTraceApp.java
java PulseTraceApp
```

## Build it step by step

1. `PulseCategory.java` defines an `enum` that maps BPM values to categories.
2. `PulseReading.java` models one record with an ID, name, BPM, and `LocalDateTime` timestamp.
3. `PulseTraceApp.java` owns a fixed `PulseReading[]` and displays a `Scanner` menu.
4. The menu routes to Create, Read (list/search), Update, and Delete methods.
5. A `Random` number makes a unique four-digit ID; helper methods validate input and search the array.

This is a learning example, not a medical diagnostic tool. Category ranges are simple demo thresholds.
