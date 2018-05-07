/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transpositioncipher;
import java.util.*;

/**
 *
 * @author user
 */
/*
   authors by hafiq
   
   not very efficient algo for columnar transpose..LOL..
   
   any expert can advise me to improve this code...tq..
*/

public class TranspositionCipher {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("1. Encryt 2.Decrypt : ");
        String option = scan.nextLine();
        switch (option) {
            case "1":
                System.out.print("Enter String:");
                String text = scan.nextLine();

                System.out.print("Enter Key:");
                String key = scan.nextLine();

                System.out.println(encryptCT(key, text).toUpperCase());
                break;
            case "2":
                System.out.print("Enter Encrypted String:");
                text = scan.next();

                System.out.print("Enter Key:");
                key = scan.next();

                System.out.println(decryptCT(key, text));
                break;
            default:
                break;
        }
    }

    public static String encryptCT(String key, String text) {
        int[] arrange = arrangeKey(key);

        int lenkey = arrange.length;
        int lentext = text.length();

        int row = (int) Math.ceil((double) lentext / lenkey);
//        int row = 4;
    
        char[][] grid = new char[row][lenkey];
        int z = 0;
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < lenkey; y++) {
                if (lentext == z) {
                    // at random alpha for trailing null grid
                    grid[x][y] = RandomAlpha();
                    z--;
                } else {
                    grid[x][y] = text.charAt(z);
                }

                z++;
            }
        }
        String enc = "";
        for (int x = 0; x < lenkey; x++) {
            for (int y = 0; y < lenkey; y++) {
                if (x == arrange[y]) {
                    for (int a = 0; a < row; a++) {
                        enc = enc + grid[a][y];
                    }
                }
            }
        }
        return enc;
    }

    public static String decryptCT(String key, String text) {
        int[] arrange = arrangeKey(key);
        int lenkey = arrange.length;
        int lentext = text.length();

        int row = (int) Math.ceil((double) lentext / lenkey);

        String regex = "(?<=\\G.{" + row + "})";
        String[] get = text.split(regex);

        char[][] grid = new char[row][lenkey];

        for (int x = 0; x < lenkey; x++) {
            for (int y = 0; y < lenkey; y++) {
                if (arrange[x] == y) {
                    for (int z = 0; z < row; z++) {
                        grid[z][y] = get[arrange[y]].charAt(z);
                    }
                }
            }
        }

        String dec = "";
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < lenkey; y++) {
                dec = dec + grid[x][y];
            }
        }

        return dec;
    }

    public static char RandomAlpha() {
        //generate random alpha for null space
        Random r = new Random();
        return (char)(r.nextInt(26) + 'a');
    }

    public static int[] arrangeKey(String key) {
        //arrange position of grid
        String[] keys = key.split("");
        
        
        Arrays.sort(keys);
        int[] num = new int[key.length()];
        for (int x = 0; x < keys.length; x++) {
            for (int y = 0; y < key.length(); y++) {
                if (keys[x].equals(key.charAt(y) + "")) {
                    num[y] = x;
//                    System.out.println(x);
                    break;
                }
            }
        }
        for (int i=0;i<num.length;i++) {
            System.out.println(num[i]);
        }
        return num;
    }

}