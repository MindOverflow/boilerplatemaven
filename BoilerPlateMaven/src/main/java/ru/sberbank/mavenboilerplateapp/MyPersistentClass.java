package ru.sberbank.mavenboilerplateapp;

import org.garret.perst.Persistent;

// All persistence-capable classes should be derived from
// Persistent base class
public class MyPersistentClass extends Persistent
{
    public int intKey;    // integer key
    public String strKey; // string key
    public String body;   // non-indexed field

    public MyPersistentClass() {
        // TODO: Default ctor
    }
    public MyPersistentClass(int intKey, String strKey, String body) {
        this.intKey = intKey;
        this.strKey = strKey;
        this.body = body;
    }
    public String toString() {
        return intKey + ":" + strKey + ":" + body;
    }
}
