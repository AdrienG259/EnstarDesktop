package serverFiles;

import common.User;

import java.lang.reflect.Field;
import java.util.*;

public class InstanceFromFileTests {
    public static void main(String[] args) {
        new InstanceFromFileTests(User.class);
    }

    public InstanceFromFileTests(Class c){
        Field[] f = c.getDeclaredFields();
        try {
            System.out.println(c.getConstructor(String[].class));
            System.out.println(c.getConstructor(String[].class).getName());
        }
        catch (NoSuchMethodException e){e.printStackTrace();}

        for (Field ff:f){

            System.out.println(ff.getType().toString());
            String[] s1 = ff.toString().split(" ");
            String[] ss = s1[s1.length-1].split("\\.");
            System.out.println("protection : " + s1[0] + "; type "+ s1[1] +"; field_name = "+ss[ss.length-1]);
            try {
                System.out.println(s1[1] + " " + Class.forName(s1[1]));
                Class cc = Class.forName(s1[1]);
                ArrayList<Integer> aa;
                aa = new ArrayList<Integer>(3);
                aa.add(1);
                aa.add(2);
                aa.add(3);
                System.out.println("AA =" + aa);
//            aa.toString().split("[");
//            Arrays.asList(aa.toString().split())
            }
            catch(ClassNotFoundException e){
                System.err.println(s1[1] + " class not found");
                e.printStackTrace();}
//            ff.get()
        }

    }
}
