package com.flamexander.multithreading.p_temp;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.*;

public class TempApp6 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50_000_000; i++) {
            sb.append("A");
        }
        System.out.println(1);
    }
}
