package ua.org.evgen.funstatusgen;

import android.content.Context;
import java.util.Date;
import java.util.Random;

public class FunStatusGuide {

    public enum ForWho {
        Man, Woman
    }

    private static Context context;
    private static Random random;
    private static ForWho forwho;
    private final int randomSize;

    public static void setContext(Context inContext) {
        if (context == null) { context = inContext; }
    }

    public static void setForwho(ForWho forwho) {
        FunStatusGuide.forwho = forwho;
    }

    public FunStatusGuide(int randomSize) {
        this.randomSize = randomSize - 1;
        random = new Random(new Date().getTime());
    }

    private String getStatus(ForWho forwho, int partid1, int partid2, int partid3) {
        String[] partstatus1 = new String[0], partstatus2 = new String[0], partstatus3 = new String[0];
        String retResult = "";
        switch (forwho) {
            case Man:
                partstatus1 = context.getResources().getStringArray(R.array.man_part1);
                partstatus2 = context.getResources().getStringArray(R.array.man_part2);
                partstatus3 = context.getResources().getStringArray(R.array.man_part3);
                break;
            case Woman:
                partstatus1 = context.getResources().getStringArray(R.array.woman_part1);
                partstatus2 = context.getResources().getStringArray(R.array.woman_part2);
                partstatus3 = context.getResources().getStringArray(R.array.woman_part3);
                break;
        }
        retResult += partstatus1[partid1];
        retResult += " ";
        retResult += partstatus2[partid2];
        retResult += " ";
        retResult += partstatus3[partid3];
        retResult += ".";

        return retResult;
    }

    private int getRandom() {
        int pause = random.nextInt(20);
        try {
            Thread.currentThread().sleep(pause);
        } catch (InterruptedException ignored) {}
        random.setSeed(new Date().getTime());
        return random.nextInt(this.randomSize);
    }

    public String generate () {
        return getStatus(forwho,
                getRandom(),
                getRandom(),
                getRandom());
    }
}
