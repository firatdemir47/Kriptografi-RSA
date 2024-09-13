/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.bilgiguvenligi;

import java.util.Scanner;
import java.math.BigInteger;

public class Bilgiguvenligi {

    static int ortakbölen(int a, int h) {//İki sayının en büyük ortak bölenini hesaplar. 
        int temp;   
        while (true) {
            temp = a % h;
            if (temp == 0) {
                return h;
            }
            a = h;
            h = temp;
        }
    }

    static int privated(int phi, int e) {//RSA algoritmasında özel anahtarın (d) değerini buluruz
        int i;
        for (i = 1; i < phi; i++) {
            if ((i * e) % phi == 1) {//(i * e) % phi == 1 koşulu sağlandığında, i değeri özel anahtar olarak döndürülür.
                break;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       
        String name = "Fır"; 

        
        int toplam = 0;

        // İsim baş harflerinin ASCII değerlerini toplamlayalım
        for (int i = 0; i < 3 && i < name.length(); i++) {
            toplam += (int) name.charAt(i);
        
        }
        
        // Seçilecek asal sayıları belirleyelim
        int q = ÖncekiAsal(toplam);
        int p = ÖncekiAsal(q-1);
        
        System.out.println("Seçilen asal sayılar: " + p + ", " + q);
       
        int n = p * q;
        System.out.println("N değeri: " + n);
        
        
        int phi_n = (p - 1) * (q - 1);
        System.out.println("Phi(N) değeri: " + phi_n);

        int e = 2;
        while (e < phi_n) {
            if (ortakbölen(e, phi_n) == 1) {
                break;
            } else {
                e++;
            }
        }
        System.out.println("E değeri: " + e);

        int d = privated(phi_n, e);
        System.out.println("D değeri: " + d);

        // Şifreleme işlemi
        System.out.println("Şifrelenecek metni girin:");
        String plainText = scanner.nextLine();

        StringBuilder encryptedText = new StringBuilder();

        for (char c : plainText.toCharArray()) {
            int plainTextInt = c;

            BigInteger biPlainText = BigInteger.valueOf(plainTextInt);
            BigInteger biN = BigInteger.valueOf(n); 

            BigInteger encryptedChar = biPlainText.modPow(BigInteger.valueOf(e), biN);
             
           encryptedText.append(encryptedChar).append(" ");
        }
     
        System.out.println("Şifreli Metin: " + encryptedText);

        // Şifre çözme işlemi
        StringBuilder decryptedText = new StringBuilder();
        for (String encryptedChar : encryptedText.toString().split(" ")) {
            BigInteger biEncryptedChar = new BigInteger(encryptedChar);
            BigInteger biN = BigInteger.valueOf(n); // burada da biN değişkeni tanımlandı
            BigInteger decryptedChar = biEncryptedChar.modPow(BigInteger.valueOf(d), biN);
            decryptedText.append((char) decryptedChar.intValue());
        }
        System.out.println("Çözülmüş Metin: " + decryptedText);
    }

    static boolean Asal(int num) {//asal olup olmadığı kontrol edilir 
        if (num <= 1) {
            return false;
        }
        if (num <= 3) {
            return true;
        }

        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    static int ÖncekiAsal(int num) { //Asal olup olmadığı 
        while (!Asal(num)) {
            num--;
        }
        return num;
    }

}
