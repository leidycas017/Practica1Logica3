/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matricesvalidades;

/**
 *
 * @author usuario
 */
public class NewClass {
    public static void main(String[] args) {
        String bfRead="12345";
                            char[] list = new char[bfRead.length()];
                    list=bfRead.toCharArray();
                    int[] num = new int[bfRead.length()];
                    for(int i=0; i<bfRead.length(); i++){
                        num[i]=Character.getNumericValue(list[i]);
                    }
                    System.out.println(num[0]);
                    System.out.println(num[1]);
                    System.out.println(num[2]);
                    System.out.println(num[3]);
    }
}
